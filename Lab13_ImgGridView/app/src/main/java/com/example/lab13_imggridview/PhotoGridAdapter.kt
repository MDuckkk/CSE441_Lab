package com.example.lab13_imggridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class PhotoGridAdapter(
    private val ctx: Context,
    private val data: List<Int> // resource ids
) : BaseAdapter() {

    private val inflater = LayoutInflater.from(ctx)

    override fun getCount() = data.size
    override fun getItem(position: Int) = data[position]
    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater.inflate(R.layout.item_photo, parent, false)
        val img = view.findViewById<ImageView>(R.id.imgThumb)
        img.setImageResource(data[position])
        return view
    }
}
