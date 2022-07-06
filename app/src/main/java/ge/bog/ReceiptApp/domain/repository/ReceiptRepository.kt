package ge.bog.ReceiptApp.domain.repository

import ge.bog.ReceiptApp.domain.model.Receipt
import ge.bog.ReceiptApp.domain.model.Result

interface ReceiptRepository {
    fun getReceipt(meals: String): Result<List<Receipt>>
}