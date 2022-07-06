package ge.bog.ReceiptApp.data.Api.retrofit.browseByCategory

import ge.bog.ReceiptApp.data.Api.BrowseByCategoryApi
import ge.bog.ReceiptApp.data.ApiError
import ge.bog.ReceiptApp.data.model.BrowseByCategoryModel

class BrowseByCategoryApiImpl(private val browseByCategoryRetrofitApi: BrowseByCategoryRetrofitApi) :
    BrowseByCategoryApi {

    override fun fetchMeals(meals: String): List<BrowseByCategoryModel> {
        val call = browseByCategoryRetrofitApi.loadMeals(meals)
        val result = call.execute()

        if (result.isSuccessful) {
            return result.body()?.meals ?: emptyList()
        } else {
            throw ApiError(
                result.errorBody()?.string() ?: "api Error: responseCode=${result.code()}"
            )

        }
    }

}