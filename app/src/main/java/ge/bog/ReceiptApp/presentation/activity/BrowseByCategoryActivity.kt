package ge.bog.ReceiptApp.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ge.bog.ReceiptApp.databinding.ActivityBrowseByCategoryBinding
import ge.bog.ReceiptApp.databinding.ActivityCategoriesPageBinding
import ge.bog.ReceiptApp.domain.model.Result
import ge.bog.ReceiptApp.presentation.adapter.BrowseByCategoryAdapter
import ge.bog.ReceiptApp.presentation.viewmodel.BrowseByCategoryViewModel

class BrowseByCategoryActivity : AppCompatActivity() {

    private val adapter = BrowseByCategoryAdapter()

    private val binding: ActivityBrowseByCategoryBinding by lazy {
        ActivityBrowseByCategoryBinding.inflate(layoutInflater)
    }

    private val viewModel: BrowseByCategoryViewModel by lazy {
        ViewModelProvider(this).get(BrowseByCategoryViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val linearLayoutManager = LinearLayoutManager(this)
        val text: String? = intent.extras?.getString("text")
        val imageUrl: String? = intent.extras?.getString("imageUrl")

        binding.secondRecycler.layoutManager = linearLayoutManager
        binding.textView2.text = text
        adapter.categortText = text.toString()

        Glide.with(this)
            .load(imageUrl)
            .into(binding.image2)

        binding.secondRecycler.adapter = adapter
        adapter.onClicked2 = {
            val intent = Intent(this, ReceiptActivity::class.java)
            intent.putExtra("mealId", it.idMeal)
            intent.putExtra("mealName", it.strMeal)
            intent.putExtra("mealImage", it.strMealThumb)
            startActivity(intent)
        }
        viewModel.categoryNameLiveData.observe(this) {
            text.toString()
        }
        viewModel.loadMeals(text.toString())

        viewModel.browseByCategoryResultLiveData.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    adapter.submitList(result.data)
                }
                is Result.Error -> {
                    Snackbar.make(
                        ActivityCategoriesPageBinding.inflate(layoutInflater).root, "ar chaitvirta",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

