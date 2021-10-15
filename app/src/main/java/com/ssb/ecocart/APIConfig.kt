package com.ssb.ecocart

import android.content.Context
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import retrofit2.converter.scalars.ScalarsConverterFactory


object APIConfig {

    private const val BASE_URL = "https://fakestoreapi.com/"

    private var retrofit: Retrofit? = null

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    fun getRetrofitClient(context: Context): Retrofit {

        //    File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
        //    Cache cache = new Cache(httpCacheDirectory, 70 * 1024 * 1024);

        val okHttpClient = OkHttpClient.Builder()
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                        .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }
}
