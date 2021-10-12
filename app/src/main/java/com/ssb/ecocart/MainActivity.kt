package com.ssb.ecocart

import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.ssb.ecocart.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://fakestoreapi.com/"
class MainActivity : AppCompatActivity() {
    lateinit var ApiAdapter : ApiAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Slider Function
        val listItems: List<The_Slide_Items_Model_Class?>
        val page: ViewPager = findViewById(R.id.my_pager)

        // Make a copy of the slides you'll be presenting.
        // Make a copy of the slides you'll be presenting.
        listItems = ArrayList()
        listItems.add(The_Slide_Items_Model_Class(R.drawable.image2, ""))
        listItems.add(The_Slide_Items_Model_Class(R.drawable.image1, ""))
        listItems.add(The_Slide_Items_Model_Class(R.drawable.image3, ""))
        listItems.add(The_Slide_Items_Model_Class(R.drawable.image4, ""))
        val itemsPager_adapter = The_Slide_items_Pager_Adapter(
            this,
            listItems as List<The_Slide_Items_Model_Class>
        )
        page.adapter = itemsPager_adapter


        recyclerView.setHasFixedSize(true)
        linearLayoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager = linearLayoutManager


        getProductData()

    }

    private fun getProductData() {
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<ProductDataItem>?> {
            override fun onResponse(
                call: Call<List<ProductDataItem>?>,
                response: Response<List<ProductDataItem>?>
            ) {
                val responseBody = response.body()!!
                ApiAdapter = ApiAdapter(responseBody)
                recyclerView.adapter = ApiAdapter

            }

            override fun onFailure(call: Call<List<ProductDataItem>?>, t: Throwable) {
                d("MainActivity", "onFailure" +t.message)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}



