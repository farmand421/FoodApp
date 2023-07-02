package com.farmand.foodapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.farmand.foodapp.R
import com.farmand.foodapp.data.model.home.ResponseCategoresList.Category
import com.farmand.foodapp.databinding.ItemCategoresBinding
import javax.inject.Inject

class CategoriesAdapter @Inject constructor() : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    lateinit var binding: ItemCategoresBinding
    private var moviesList = emptyList<Category>()
    private var selectedItem = -1

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun setData(item: Category){
            binding.apply {
                itemCategoriesImg.load(item.strCategoryThumb){
                    crossfade(true)
                    crossfade(500)
                }
                itemCategoriesTxt.text = item.strCategory
                //Click
                root.setOnClickListener {
                    selectedItem = adapterPosition
                    notifyDataSetChanged()
                    onItemClickListener?.let {
                        it(item)
                    }
                }
                //ChangeColor
                if (selectedItem == adapterPosition){
                    root.setBackgroundResource(R.drawable.bg_rounded_selected)
                }else{
                    root.setBackgroundResource(R.drawable.bg_rounded_white)
                }
            }
        }

    }
    private var onItemClickListener:((Category) -> Unit)? = null
    fun setOnClickListener(listener: (Category) -> Unit){
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemCategoresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(moviesList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = moviesList.size

    fun setData(data: List<Category>){
        val moviesDiffutils = MoviesDiffutils(moviesList,data)
        val diffutils = DiffUtil.calculateDiff(moviesDiffutils)
        moviesList = data
        diffutils.dispatchUpdatesTo(this)
    }

    class MoviesDiffutils(private val oldItem : List<Category>, private val newItem:List<Category>):DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

    }

}

