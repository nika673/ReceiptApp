package ge.bog.ReceiptApp.data.Api.retrofit.receipt

import ge.bog.ReceiptApp.data.ApiError
import ge.bog.ReceiptApp.data.Api.ReceiptPageApi
import ge.bog.ReceiptApp.data.model.ReceiptPageModel


class ReceiptRetrofitApiImpl(private val receiptRetrofitApi: ReceiptRetrofitApi) : ReceiptPageApi {

    override fun fetchReceipt(meals: String): List<ReceiptPageModel> {
        val call = receiptRetrofitApi.loadReceipt(meals)
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