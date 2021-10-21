package com.ssb.ecocart.adapter

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.ssb.ecocart.R
import com.ssb.ecocart.api.modal.SliderModalClass


class SliderAdapter(
    private val Mcontext: Context,
    private val sliderModalClassList: List<SliderModalClass>
) :
    PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = Mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val sliderLayout: View = inflater.inflate(R.layout.items_layout, null)
        val featured_image: ImageView = sliderLayout.findViewById(R.id.my_featured_image)
        val caption_title = sliderLayout.findViewById<TextView>(R.id.my_caption_title)
        featured_image.setImageResource(sliderModalClassList[position].featured_image)
        caption_title.text = sliderModalClassList[position].the_caption_Title
        container.addView(sliderLayout)
        return sliderLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return sliderModalClassList.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }


}