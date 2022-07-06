package ge.bog.ReceiptApp.domain.iteractor

import android.os.Handler
import ge.bog.ReceiptApp.domain.model.BrowseByCategory
import ge.bog.ReceiptApp.domain.model.Result
import ge.bog.ReceiptApp.domain.repository.BrowseByCategoryRepository
import java.util.concurrent.Executor

class GetBrowseByCategoryUseCase(
    private val repository: BrowseByCategoryRepository,
    private val executor: Executor,
    private val handler: Handler
) {
    operator fun invoke(
        meals: String,
        callback: (mealsList: Result<List<BrowseByCategory>>) -> Unit
    ) {
        return executor.execute {
            val mealsList = repository.getMealsList(meals)
            handler.post {
                callback(mealsList)
            }
        }
    }
}