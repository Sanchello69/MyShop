package com.vas.feature_main_screen.presentation.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.vas.feature_main_screen.R
import com.vas.feature_main_screen.domain.model.HomeStore
import kotlinx.android.synthetic.main.hot_sales_item.view.*

class HotSalesAdapter(var context: Context): RecyclerView.Adapter<ItemHotSalesViewHolder>() {

    var data = listOf<HomeStore>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickListener: OnHotSalesClickListener? = null

    interface OnHotSalesClickListener {
        fun onBuyNowClick(homeStore: HomeStore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHotSalesViewHolder =
        ItemHotSalesViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemHotSalesViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context, onClickListener!!)
    }

    override fun getItemCount() = data.size
}

class ItemHotSalesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val imagePhone: ImageView = itemView.picturePhone
    private val newImageView: ImageView = itemView.newImageView
    private val phoneNameTextView: TextView = itemView.phoneNameTextView
    private val descriptionTextView: TextView = itemView.descriptionTextView
    private val buyNowButton: Button = itemView.buyNowButton

    fun bind(item: HomeStore, context: Context, onHotSalesClickListener: HotSalesAdapter.OnHotSalesClickListener) {

        Glide
            .with(context)
            .load(item.picture)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(imagePhone)

        phoneNameTextView.text = item.title
        descriptionTextView.text = item.subtitle

        if (item.isNew) newImageView.visibility = View.VISIBLE

        buyNowButton.setOnClickListener {
            onHotSalesClickListener.onBuyNowClick(item)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ItemHotSalesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.hot_sales_item, parent, false)
            return ItemHotSalesViewHolder(view)
        }
    }
}