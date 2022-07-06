package ge.bog.ReceiptApp.presentation.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ge.bog.ReceiptApp.R
import ge.bog.ReceiptApp.databinding.ActivityCategoriesPageBinding
import ge.bog.ReceiptApp.domain.model.Result
import ge.bog.ReceiptApp.presentation.viewmodel.ReceiptViewModel
import ge.bog.ReceiptApp.databinding.ActivityReceiptBinding

class ReceiptActivity : AppCompatActivity() {

    private val binding: ActivityReceiptBinding by lazy {
        ActivityReceiptBinding.inflate(layoutInflater)
    }

    private val viewModel: ReceiptViewModel by lazy {
        ViewModelProvider(this).get(ReceiptViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val text1: String? = intent.extras?.getString("mealName")
        val imageReceiptUrl: String? = intent.extras?.getString("mealImage")
        val id: String? = intent.extras?.getString("mealId")

        binding.text1.text = text1

        Glide.with(this)
            .load(imageReceiptUrl)
            .into(binding.imageReceipt)

        viewModel.mealsIdLiveData.observe(this) {
            id.toString()
        }
        viewModel.loadMeals(id.toString())
        val ingredients = mutableListOf<Pair<String, String>>()

        viewModel.receiptResultLiveData.observe(this) { result ->
            when (result) {
                is Result.Success -> {
                    val receipt = result.data[0]
                    binding.text3.text = receipt.strInstructions
                    binding.text4.text = receipt.strYoutube

                    if (receipt.strIngredient1.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient1 to receipt.strMeasure1)
                    }
                    if (receipt.strIngredient2.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient2 to receipt.strMeasure2)
                    }
                    if (receipt.strIngredient3.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient3 to receipt.strMeasure3)
                    }
                    if (receipt.strIngredient4.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient4 to receipt.strMeasure4)
                    }
                    if (receipt.strIngredient5.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient5 to receipt.strMeasure5)
                    }
                    if (receipt.strIngredient6.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient6 to receipt.strIngredient6)
                    }
                    if (receipt.strIngredient7.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient7 to receipt.strIngredient7)
                    }
                    if (receipt.strIngredient8.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient8 to receipt.strIngredient8)
                    }
                    if (receipt.strIngredient9.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient9 to receipt.strIngredient9)
                    }
                    if (receipt.strIngredient10.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient10 to receipt.strIngredient10)
                    }
                    if (receipt.strIngredient11.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient11 to receipt.strMeasure11)
                    }
                    if (receipt.strIngredient12.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient12 to receipt.strMeasure12)
                    }
                    if (receipt.strIngredient13.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient13 to receipt.strMeasure13)
                    }
                    if (receipt.strIngredient14.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient14 to receipt.strMeasure14)
                    }
                    if (receipt.strIngredient15.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient15 to receipt.strMeasure15)
                    }
                    if (receipt.strIngredient16.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient16 to receipt.strMeasure16)
                    }
                    if (receipt.strIngredient17.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient17 to receipt.strMeasure17)
                    }
                    if (receipt.strIngredient18.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient18 to receipt.strMeasure18)
                    }
                    if (receipt.strIngredient19.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient19 to receipt.strMeasure19)
                    }
                    if (receipt.strIngredient20.isNotEmpty()) {
                        ingredients.add(receipt.strIngredient20 to receipt.strIngredient20)
                    }

                    for (i in ingredients) {
                        val newText = TextView(this).apply {
                            text = "${i.second} ${i.first}"
                        }
                        newText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.circle, 0, 0, 0)
                        newText.setTextColor(this.resources.getColor(R.color.neutral_black))
                        newText.compoundDrawablePadding =
                            this.resources.getDimensionPixelSize(R.dimen.linear3_text_drawablePadding)
                        binding.linear3.addView(newText)
                    }

                    val text = this.resources.getText(R.string.ingredients)
                    binding.text2.text = ingredients.size.toString() + "  ${text}"

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