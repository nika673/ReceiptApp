package ge.bog.ReceiptApp.data.Api

import ge.bog.ReceiptApp.data.model.BrowseByCategoryModel

interface BrowseByCategoryApi {
    fun fetchMeals(meals: String): List<BrowseByCategoryModel>
}