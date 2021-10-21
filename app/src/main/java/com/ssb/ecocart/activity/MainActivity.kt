package com.ssb.ecocart.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.ssb.ecocart.*
import com.ssb.ecocart.adapter.ProductAdapter
import com.ssb.ecocart.adapter.SliderAdapter
import com.ssb.ecocart.api.APIConfig
import com.ssb.ecocart.api.APIService
import com.ssb.ecocart.api.modal.SliderModalClass
import com.ssb.ecocart.databinding.ActivityMainBinding
import com.ssb.ecocart.util.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response
import io.paperdb.Paper


class MainActivity : AppCompatActivity() {
    private lateinit var apiService: APIService
    private lateinit var productAdapter: ProductAdapter

    private var products = listOf<Product>()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)

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
        val listItems: List<SliderModalClass?>
        val page: ViewPager = findViewById(R.id.my_pager)

        // Make a copy of the slides you'll be presenting.
        // Make a copy of the slides you'll be presenting.
        listItems = ArrayList()
        listItems.add(SliderModalClass(R.drawable.image2, ""))
        listItems.add(SliderModalClass(R.drawable.image1, ""))
        listItems.add(SliderModalClass(R.drawable.image3, ""))
        listItems.add(SliderModalClass(R.drawable.image4, ""))
        val itemsPager_adapter = SliderAdapter(
            this,
            listItems as List<SliderModalClass>
        )
        page.adapter = itemsPager_adapter


        basketButton.setOnClickListener {

            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)
        }

        products_recyclerview.setHasFixedSize(true)
        apiService = APIConfig.getRetrofitClient(this).create(APIService::class.java)


        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary))

        swipeRefreshLayout.isRefreshing = true

        swipeRefreshLayout.setOnRefreshListener {
            getProducts()
        }

//        val layoutManager = StaggeredGridLayoutManager(this, Lin)

        products_recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        cart_size.text = ShoppingCart.getShoppingCartSize().toString()


        getProducts()


    }

    private fun getProducts() {

        apiService.getProducts().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

                print(t.message)
                t.message?.let { Log.d("Data error", it) }
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {

                swipeRefreshLayout.isRefreshing = false
//                swipeRefreshLayout.isEnabled = false

                products = response.body()!!

                productAdapter = ProductAdapter(this@MainActivity, products)

                products_recyclerview.adapter = productAdapter

//                productAdapter.notifyDataSetChanged()

            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        menuInflater.inflate(R.menu.menu_bottom_navigation, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
