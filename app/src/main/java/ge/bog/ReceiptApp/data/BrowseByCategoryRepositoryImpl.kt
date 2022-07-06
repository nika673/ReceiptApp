package ge.bog.ReceiptApp.data

import ge.bog.ReceiptApp.data.Api.BrowseByCategoryApi
import ge.bog.ReceiptApp.domain.model.BrowseByCategory
import ge.bog.ReceiptApp.domain.model.Result
import ge.bog.ReceiptApp.domain.repository.BrowseByCategoryRepository

class BrowseByCategoryRepositoryImpl(
    private val browseByCategoryApi: BrowseByCategoryApi
) : BrowseByCategoryRepository {
    override fun getMealsList(meals: String): Result<List<BrowseByCategory>> {
        return try {
            Result.Success(browseByCategoryApi.fetchMeals(meals).map {
                BrowseByCategory(
                    it.strMeal,
                    it.strMealThumb,
                    it.idMeal
                )
            })
        } catch (e: ApiError) {
            Result.Error(e)
        }
    }
}