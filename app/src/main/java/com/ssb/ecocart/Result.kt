package com.ssb.ecocart

import com.google.gson.annotations.SerializedName
import com.ssb.ecocart.Product

data class Result(
    @SerializedName("products")
    var products: List<Product> = listOf()
)