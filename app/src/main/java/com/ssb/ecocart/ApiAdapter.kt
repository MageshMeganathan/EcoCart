package com.ssb.ecocart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.productlist.view.*


class ApiAdapter(private val context: Context, private val ProductTitle: List<ProductDataItem> ) : RecyclerView.Adapter<ApiAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.productID
        var price: TextView = itemView.price
        var description: TextView = itemView.description

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.productlist, parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = ProductTitle[position].title
        holder.price.text = ProductTitle[position].price.toString()
        holder.description.text = ProductTitle[position].description


    }

    override fun getItemCount(): Int {
        return ProductTitle.size
    }
}