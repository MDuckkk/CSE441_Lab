package com.example.lab13_imggridview_2.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.lab13_imggridview_2.model.Image
import com.example.lab13_imggridview_2.R
import com.squareup.picasso.Picasso

class MyArrayAdapter (
    private val context: Activity,
    private val layoutId: Int,
    private val myArray: List<Image>
) : ArrayAdapter<Image>(context, layoutId, myArray) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row = convertView ?: LayoutInflater.from(context).inflate(layoutId, parent, false)
        val imgitem = row.findViewById<ImageView>(R.id.imageView1)
        val myname = row.findViewById<TextView>(R.id.textView1)
        val image = myArray[position]
        myname.text = image.name
        Picasso.get().load(image.url).into(imgitem)
        return row
    }
}