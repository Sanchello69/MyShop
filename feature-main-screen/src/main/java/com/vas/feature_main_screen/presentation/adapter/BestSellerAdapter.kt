package com.vas.feature_main_screen.presentation.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.vas.feature_main_screen.R
import com.vas.feature_main_screen.domain.model.BestSeller
import kotlinx.android.synthetic.main.best_seller_item.view.*

class BestSellerAdapter(var context: Context): RecyclerView.Adapter<ItemBestSellerViewHolder>(){

    var data = listOf<BestSeller>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickListener: OnBestSellerClickListener? = null

    interface OnBestSellerClickListener {
        fun onLikeClick(bestSeller: BestSeller)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBestSellerViewHolder =
        ItemBestSellerViewHolder.from(parent)

    override fun onBindViewHolder(holder: ItemBestSellerViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context, onClickListener!!)
    }

    override fun getItemCount() = data.size


}

class ItemBestSellerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val picturePhone: ImageView = itemView.picturePhoneBest
    private val likeImage: ImageView = itemView.likeImageView
    private val priceDiscountTextView: TextView = itemView.priceDiscountTextView
    private val priceTextView: TextView = itemView.priceTextView
    private val nameTextView: TextView = itemView.nameBestTextView

    fun bind(item: BestSeller, context: Context, onBestSellerClickListener: BestSellerAdapter.OnBestSellerClickListener) {

        priceTextView.text = "$${item.price}"
        priceDiscountTextView.text = "$${item.priceDiscount}"
        nameTextView.text = item.title

        likeImage.setImageResource(
            if (item.isFavorites) R.drawable.ic_like
            else R.drawable.ic_no_like)

        Glide
            .with(context)
            .load(item.picture)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(picturePhone)

        likeImage.setOnClickListener {
            onBestSellerClickListener.onLikeClick(item)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ItemBestSellerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.best_seller_item, parent, false)
            return ItemBestSellerViewHolder(view)
        }
    }
}