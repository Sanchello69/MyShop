package com.vas.feature_product_details_screen.presentation.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.vas.feature_product_details_screen.R
import kotlinx.android.synthetic.main.image_item.view.*


class ImageAdapter(var context: Context): RecyclerView.Adapter<ItemImageViewHolder>() {

    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemImageViewHolder =
        ItemImageViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemImageViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun getItemCount() = data.size
}

class ItemImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val imagePhones: ImageView = itemView.phonesImageView

    fun bind(item: String, context: Context) {

        Glide
            .with(context)
            .load(item)
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
        fun from(parent: ViewGroup): ItemImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.image_item, parent, false)
            return ItemImageViewHolder(view)
        }
    }
}