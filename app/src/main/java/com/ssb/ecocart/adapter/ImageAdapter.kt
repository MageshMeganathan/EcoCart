package com.ssb.ecocart.adapter

import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import android.view.ViewGroup
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.ssb.ecocart.R


class ImageAdapter internal constructor(context: Context) : PagerAdapter() {
    private var mContext: Context = context
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ImageView
    }

    private val sliderImageId = intArrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(mContext)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(sliderImageId[position])
        (container as ViewPager).addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as ImageView)
    }

    override fun getCount(): Int {
        return sliderImageId.size
    }

}