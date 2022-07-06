package ge.bog.ReceiptApp.data.Api

import ge.bog.ReceiptApp.data.model.ReceiptPageModel

interface ReceiptPageApi {
    fun fetchReceipt(meals: String): List<ReceiptPageModel>
}