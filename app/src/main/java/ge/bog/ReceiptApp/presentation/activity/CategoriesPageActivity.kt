package ge.bog.ReceiptApp.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import ge.bog.ReceiptApp.data.CategoriesRepositoryImpl
import ge.bog.ReceiptApp.data.Api.retrofit.categories.CategoriesApiImpl
import ge.bog.ReceiptApp.data.Api.retrofit.categories.CategoriesRetrofitApi
import ge.bog.ReceiptApp.databinding.ActivityCategoriesPageBinding
import ge.bog.ReceiptApp.domain.model.Result
import ge.bog.ReceiptApp.presentation.adapter.CategoriesAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

class CategoriesPageActivity : AppCompatActivity() {

    private val adapter = CategoriesAdapter()

    private val categoriesRepository = CategoriesRepositoryImpl(
        Executors.newCachedThreadPool(), Handler(
            Looper.getMainLooper()
        ),
        CategoriesApiImpl(
            Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(CategoriesRetrofitApi::class.java)
        )
    )

    private val binding: ActivityCategoriesPageBinding by lazy {
        ActivityCategoriesPageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recycler.layoutManager = staggeredGridLayoutManager
        binding.recycler.adapter = adapter

        loadInfo()

        adapter.onClicked = {
            val intent = Intent(this, BrowseByCategoryActivity::class.java)
            intent.putExtra("id", it.idCategory)
            intent.putExtra("text", it.strCategory)
            intent.putExtra("imageUrl", it.strCategoryThumb)
            startActivity(intent)
        }
    }

    private fun loadInfo() {
        categoriesRepository.getCategoryList("categories") { result ->
            when (result) {
                is Result.Success -> {
                    adapter.submitList(result.data)
                }
                is Result.Error -> {
                    Snackbar.make(
                        ActivityCategoriesPageBinding.inflate(layoutInflater).root,
                        "ar chaitvirta",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}