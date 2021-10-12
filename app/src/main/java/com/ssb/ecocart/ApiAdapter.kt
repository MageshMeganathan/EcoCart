package com.ssb.ecocart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_list_layout.view.*

class ApiAdapter(private val ProductTitle: List<ProductDataItem>) : RecyclerView.Adapter<ApiAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.title
        var price: TextView = itemView.price
        val productImage: ImageView = itemView.productImage

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_list_layout, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = ProductTitle[position].title
        holder.price.text = ProductTitle[position].price.toString()
        Picasso.get().load(ProductTitle[position].image).into(holder.productImage)

    }

    override fun getItemCount(): Int {
        return ProductTitle.size
    }
}