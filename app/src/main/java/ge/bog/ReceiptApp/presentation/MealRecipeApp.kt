package ge.bog.ReceiptApp.presentation

import android.app.Application
import android.os.Handler
import android.os.Looper
import ge.bog.ReceiptApp.data.Api.retrofit.browseByCategory.BrowseByCategoryApiImpl
import ge.bog.ReceiptApp.data.Api.retrofit.browseByCategory.BrowseByCategoryRetrofitApi
import ge.bog.ReceiptApp.data.Api.retrofit.receipt.ReceiptRetrofitApi
import ge.bog.ReceiptApp.data.Api.retrofit.receipt.ReceiptRetrofitApiImpl
import ge.bog.ReceiptApp.data.BrowseByCategoryRepositoryImpl
import ge.bog.ReceiptApp.data.ReceiptRepositoryImpl
import ge.bog.ReceiptApp.domain.iteractor.GetBrowseByCategoryUseCase
import ge.bog.ReceiptApp.domain.iteractor.GetReceiptUseCase
import ge.bog.ReceiptApp.domain.repository.BrowseByCategoryRepository
import ge.bog.ReceiptApp.domain.repository.ReceiptRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MealRecipeApp : Application() {

    private val browseByCategoryRepository: BrowseByCategoryRepository by lazy {
        BrowseByCategoryRepositoryImpl(
            BrowseByCategoryApiImpl(
                Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(BrowseByCategoryRetrofitApi::class.java)
            )
        )
    }

    fun getBrowseByCategoryUseCase(): GetBrowseByCategoryUseCase {
        return GetBrowseByCategoryUseCase(
            browseByCategoryRepository,
            executor,
            handler
        )
    }

    private val receiptPageRepository: ReceiptRepository by lazy {
        ReceiptRepositoryImpl(
            ReceiptRetrofitApiImpl(
                Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ReceiptRetrofitApi::class.java)
            )
        )
    }

    fun getReceiptUseCase(): GetReceiptUseCase {
        return GetReceiptUseCase(
            receiptPageRepository,
            executor,
            handler
        )
    }

    private val executor: Executor by lazy {
        Executors.newCachedThreadPool()
    }
    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

}