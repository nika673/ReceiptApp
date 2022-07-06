package ge.bog.ReceiptApp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ge.bog.ReceiptApp.data.model.CategoriesModel
import ge.bog.ReceiptApp.databinding.CategoriesViewBinding

typealias onClicked = (v: CategoriesModel) -> Unit

class CategoriesAdapter :
    ListAdapter<CategoriesModel, CategoriesAdapter.ViewHolder>(DIFF_CALLBACK) {

    lateinit var onClicked: onClicked

    class ViewHolder(val binding: CategoriesViewBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CategoriesModel>() {
            override fun areItemsTheSame(
                oldItem: CategoriesModel,
                newItem: CategoriesModel
            ): Boolean {
                return oldItem.idCategory == newItem.idCategory
            }

            override fun areContentsTheSame(
                oldItem: CategoriesModel,
                newItem: CategoriesModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            CategoriesViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val categoryItem = getItem(position)
        holder.binding.textView.text = categoryItem.strCategory

        Glide.with(holder.binding.image)
            .load(categoryItem.strCategoryThumb)
            .into(holder.binding.image)

        holder.binding.card1.setOnClickListener {
            onClicked(categoryItem)
        }
    }
}

