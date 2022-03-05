package com.vas.feature_cart_screen.presentation.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.vas.feature_cart_screen.R
import com.vas.feature_cart_screen.domain.model.BasketModel
import kotlinx.android.synthetic.main.cart_item.view.*

class CartAdapter(var context: Context): RecyclerView.Adapter<ItemCartViewHolder>() {

    var data = listOf<BasketModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCartViewHolder =
        ItemCartViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemCartViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun getItemCount() = data.size
}

class ItemCartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val imagePhones: ImageView = itemView.imageView
    private val titleTextView: TextView = itemView.titleTextView
    private val priceTextView: TextView = itemView.priceTextView

    fun bind(item: BasketModel, context: Context) {

        titleTextView.text = item.title
        priceTextView.text = "$${item.price}"

        Glide
            .with(context)
            .load(item.images)
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
            .into(imagePhones)
    }

    companion object {
        fun from(parent: ViewGroup): ItemCartViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.cart_item, parent, false)
            return ItemCartViewHolder(view)
        }
    }
}