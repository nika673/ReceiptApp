package ge.bog.ReceiptApp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import ge.bog.ReceiptApp.domain.model.Receipt
import ge.bog.ReceiptApp.domain.model.Result
import ge.bog.ReceiptApp.presentation.MealRecipeApp

class ReceiptViewModel(application: Application) : AndroidViewModel(application) {

    private val getReceiptList = (application as MealRecipeApp).getReceiptUseCase()

    private val _mealsIdLiveData: MutableLiveData<String> = MutableLiveData()
    val mealsIdLiveData: LiveData<String>
        get() = _mealsIdLiveData

    val receiptResultLiveData: LiveData<Result<List<Receipt>>> =
        Transformations.switchMap(_mealsIdLiveData) { mealsId ->
            val mealsListResponse = MutableLiveData<Result<List<Receipt>>>()
            getReceiptList(mealsId) {
                mealsListResponse.value = it
            }
            mealsListResponse
        }

    fun loadMeals(mealsId: String) {
        _mealsIdLiveData.value = mealsId
    }

}