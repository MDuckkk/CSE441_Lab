package com.example.lab13_phonelistview_2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.lab13_phonelistview_2.R
import com.example.lab13_phonelistview_2.model.Phone
import java.text.NumberFormat
import java.util.Locale

class PhoneAdapter(
    context: Context,
    private val items: List<Phone>
) : ArrayAdapter<Phone>(context, 0, items) {

    private val nf = NumberFormat.getInstance(Locale("vi", "VN"))

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_phone, parent, false)

        val item = items[position]
        view.findViewById<ImageView>(R.id.imgPhone).setImageResource(item.imageRes)
        view.findViewById<TextView>(R.id.txtName).text = item.name
        view.findViewById<TextView>(R.id.txtPrice).text = "Giá bán: ${nf.format(item.price)}"

        return view
    }
}