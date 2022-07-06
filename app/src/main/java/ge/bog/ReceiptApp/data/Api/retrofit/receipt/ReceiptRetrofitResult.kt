package ge.bog.ReceiptApp.data.Api.retrofit.receipt

import ge.bog.ReceiptApp.data.model.ReceiptPageModel

data class ReceiptRetrofitResult(
    val meals: List<ReceiptPageModel>
)
