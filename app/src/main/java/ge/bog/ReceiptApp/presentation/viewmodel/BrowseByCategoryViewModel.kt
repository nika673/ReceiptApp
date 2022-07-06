package ge.bog.ReceiptApp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import ge.bog.ReceiptApp.domain.model.BrowseByCategory
import ge.bog.ReceiptApp.domain.model.Result
import ge.bog.ReceiptApp.presentation.MealRecipeApp

class BrowseByCategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val getMealsList = (application as MealRecipeApp).getBrowseByCategoryUseCase()

    val ingredients = mutableListOf<Pair<String, String>>()

    private val _categoryNameLiveData: MutableLiveData<String> = MutableLiveData()
    val categoryNameLiveData: LiveData<String>
        get() = _categoryNameLiveData

    val browseByCategoryResultLiveData: LiveData<Result<List<BrowseByCategory>>> =
        Transformations.switchMap(_categoryNameLiveData) { meal ->
            val categoryListResponse = MutableLiveData<Result<List<BrowseByCategory>>>()
            getMealsList(meal) {
                categoryListResponse.value = it
            }
            categoryListResponse
        }

    fun loadMeals(meal: String) {
        _categoryNameLiveData.value = meal
    }
}