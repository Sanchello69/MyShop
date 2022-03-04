package com.vas.feature_product_details_screen.presentation.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.vas.feature_product_details_screen.R
import kotlinx.android.synthetic.main.color_item.view.*

class ColorAdapter: RecyclerView.Adapter<ColorItemViewHolder>() {

    var clickPosition = 0

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickListener: OnColorClickListener? = null

    interface OnColorClickListener {
        fun onColorClick(item: String, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorItemViewHolder =
        ColorItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: ColorItemViewHolder, position: Int) {
        val item = data[position]

        if (clickPosition==position){
            holder.itemView.colorImageView.setImageResource(R.drawable.ic_selected)
        }
        else{
            holder.itemView.colorImageView.setImageResource(android.R.color.transparent)
        }

        holder.bind(item, position, onClickListener!!)
    }

    override fun getItemCount() = data.size
}

class ColorItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val colorImageView: ImageView = itemView.colorImageView

    fun bind(item: String, position: Int, colorClickListener: ColorAdapter.OnColorClickListener) {

        colorImageView.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor(item))

        colorImageView.setOnClickListener {
            colorClickListener.onColorClick(item, position)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ColorItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.color_item, parent, false)
            return ColorItemViewHolder(view)
        }
    }
}