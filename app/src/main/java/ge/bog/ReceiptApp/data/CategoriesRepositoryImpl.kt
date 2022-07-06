package ge.bog.ReceiptApp.data


import android.os.Handler
import ge.bog.ReceiptApp.data.Api.CategoriesApi
import ge.bog.ReceiptApp.data.model.CategoriesModel
import ge.bog.ReceiptApp.domain.model.Result
import ge.bog.ReceiptApp.domain.repository.CategoriesRepository
import java.util.concurrent.Executor

class CategoriesRepositoryImpl(
    private val executor: Executor,
    private val handler: Handler,
    private val categoriesApi: CategoriesApi
) : CategoriesRepository {
    override fun getCategoryList(
        category: String,
        callback: (categoryList: Result<List<CategoriesModel>>) -> Unit
    ) {
        executor.execute {
            val categoryList = try {
                Result.Success(categoriesApi.fetchCategory(category))
            } catch (e: ApiError) {
                Result.Error(e)
            }
            handler.post {
                callback(categoryList)
            }
        }
    }
}