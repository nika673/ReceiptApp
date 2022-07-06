package ge.bog.ReceiptApp.domain.iteractor

import android.os.Handler
import ge.bog.ReceiptApp.domain.model.Receipt
import ge.bog.ReceiptApp.domain.model.Result
import ge.bog.ReceiptApp.domain.repository.ReceiptRepository
import java.util.concurrent.Executor

class GetReceiptUseCase(
    private val repository: ReceiptRepository,
    private val executor: Executor,
    private val handler: Handler
) {

    operator fun invoke(
        meals: String,
        callback: (mealsList: Result<List<Receipt>>) -> Unit
    ) {
        return executor.execute {
            val mealsList = repository.getReceipt(meals)
            handler.post {
                callback(mealsList)
            }
        }
    }
}