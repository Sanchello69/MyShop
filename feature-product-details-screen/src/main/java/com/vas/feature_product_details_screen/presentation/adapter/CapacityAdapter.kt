package com.vas.feature_product_details_screen.presentation.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vas.feature_product_details_screen.R
import kotlinx.android.synthetic.main.capacity_item.view.*

class CapacityAdapter: RecyclerView.Adapter<CapacityItemViewHolder>() {

    var clickPosition = 0

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickListener: OnCapacityClickListener? = null

    interface OnCapacityClickListener {
        fun onCapacityClick(item: String, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapacityItemViewHolder =
        CapacityItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: CapacityItemViewHolder, position: Int) {
        val item = data[position]

        if (clickPosition==position){
            holder.itemView.capacityTextView.setTextColor(Color.parseColor("#FFFFFF"))

            holder.itemView.capacityCardView.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#FF6E4E"))
        }
        else{
            holder.itemView.capacityTextView.setTextColor(Color.parseColor("#8D8D8D"))

            holder.itemView.capacityCardView.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#FFFFFF"))
        }

        holder.bind(item, position, onClickListener!!)
    }

    override fun getItemCount() = data.size
}

class CapacityItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val capacityTextView: TextView = itemView.capacityTextView
    private val capacityCardView: CardView = itemView.capacityCardView

    fun bind(item: String, position: Int, capacityClickListener: CapacityAdapter.OnCapacityClickListener) {

        capacityTextView.text = item

        capacityCardView.setOnClickListener {
            capacityClickListener.onCapacityClick(item, position)
        }
    }

    companion object {
        fun from(parent: ViewGroup): CapacityItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.capacity_item, parent, false)
            return CapacityItemViewHolder(view)
        }
    }
}