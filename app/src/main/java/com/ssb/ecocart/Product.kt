package com.ssb.ecocart

//import com.google.gson.annotations.Expose
import android.media.Rating
import com.google.gson.annotations.SerializedName

data class Product(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)