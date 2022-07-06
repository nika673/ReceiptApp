package ge.bog.ReceiptApp.data.Api

import ge.bog.ReceiptApp.data.model.CategoriesModel

interface CategoriesApi {
    fun fetchCategory(categories: String): List<CategoriesModel>
}