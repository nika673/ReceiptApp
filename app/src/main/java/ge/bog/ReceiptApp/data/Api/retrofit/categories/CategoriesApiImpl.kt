package ge.bog.ReceiptApp.data.Api.retrofit.categories

import ge.bog.ReceiptApp.data.Api.CategoriesApi
import ge.bog.ReceiptApp.data.ApiError
import ge.bog.ReceiptApp.data.model.CategoriesModel

class CategoriesApiImpl(private val categoriesRetrofitApi: CategoriesRetrofitApi) : CategoriesApi {
    override fun fetchCategory(category: String): List<CategoriesModel> {
        val call = categoriesRetrofitApi.loadCategory(category)
        val result = call.execute()

        if (result.isSuccessful) {
            return result.body()?.categories ?: emptyList()
        } else {
            throw ApiError(
                result.errorBody()?.string() ?: "api Error: responseCode=${result.code()}"
            )

        }

    }
}