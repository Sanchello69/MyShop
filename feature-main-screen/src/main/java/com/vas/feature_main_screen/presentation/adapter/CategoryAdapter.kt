package com.vas.feature_main_screen.presentation.adapter

import android.content.res.ColorStateList
import android.graphics.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vas.feature_main_screen.R
import kotlinx.android.synthetic.main.category_item.view.*


class CategoryAdapter: RecyclerView.Adapter<ItemViewHolder>() {

    var clickPosition = 0

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickListener: OnCategoryClickListener? = null

    interface OnCategoryClickListener {
        fun onCategoryClick(category: String, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = data[position]

        if (clickPosition==position){
            holder.itemView.categoryTextView.setTextColor(Color.parseColor("#FF6E4E"))
            holder.itemView.categoryImageView.setColorFilter(Color.parseColor("#FFFFFF"))
            holder.itemView.categoryImageView.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#FF6E4E"))
        }
        else{
            holder.itemView.categoryTextView.setTextColor(Color.parseColor("#010035"))
            holder.itemView.categoryImageView.setColorFilter(Color.parseColor("#B3B3C3"))
            holder.itemView.categoryImageView.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#FFFFFF"))
        }

        holder.bind(item, position, onClickListener!!)
    }

    override fun getItemCount() = data.size
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val categoryTextView: TextView = itemView.categoryTextView
    private val categoryImageView: ImageButton = itemView.categoryImageView

    fun bind(item: String, position: Int, categoryClickListener: CategoryAdapter.OnCategoryClickListener) {

        categoryTextView.text = item
        itemView.categoryImageView.setImageResource(when (item) {
            "Phones" -> R.drawable.ic_phone
            "Books" -> R.drawable.ic_books
            "Computer" -> R.drawable.ic_computer
            "Health" -> R.drawable.ic_health
            else -> Color.WHITE
        })

        categoryImageView.setOnClickListener {
            categoryClickListener.onCategoryClick(item, position)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.category_item, parent, false)
            return ItemViewHolder(view)
        }
    }
}