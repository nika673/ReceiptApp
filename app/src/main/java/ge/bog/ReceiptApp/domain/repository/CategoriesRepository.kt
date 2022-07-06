package ge.bog.ReceiptApp.domain.repository

import ge.bog.ReceiptApp.data.model.CategoriesModel
import ge.bog.ReceiptApp.domain.model.Result

interface CategoriesRepository {
    fun getCategoryList(
        category: String,
        callback: (categoryList: Result<List<CategoriesModel>>) -> Unit
    )
}