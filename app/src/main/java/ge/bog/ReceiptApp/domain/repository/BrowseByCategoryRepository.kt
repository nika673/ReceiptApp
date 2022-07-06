package ge.bog.ReceiptApp.domain.repository

import ge.bog.ReceiptApp.domain.model.BrowseByCategory
import ge.bog.ReceiptApp.domain.model.Result

interface BrowseByCategoryRepository {
    fun getMealsList(meals: String): Result<List<BrowseByCategory>>
}