package com.example.lab18

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.lab18.MainActivity
import com.example.lab18.R
import com.squareup.picasso.Picasso
class SubActivity : AppCompatActivity() {
    companion object {
        private const val KEY_MASO = "maso"
        fun start(ctx: Context, maso: String) {
            ctx.startActivity(Intent(ctx, SubActivity::class.java).putExtra(KEY_MASO, maso))
        }
    }

    private val URL_LIKE   = "https://cdn-icons-png.flaticon.com/512/833/833472.png"
    private val URL_UNLIKE = "https://cdn-icons-png.flaticon.com/512/1077/1077035.png"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sub)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sub)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val masoView = findViewById<TextView>(R.id.txtmaso)
        val baihat = findViewById<TextView>(R.id.txtbaihat)
        val loibh = findViewById<TextView>(R.id.txtloibaihat)
        val tacgia = findViewById<TextView>(R.id.txttacgia)
        val btnLike = findViewById<ImageButton>(R.id.btnthich)
        val btnUnlike = findViewById<ImageButton>(R.id.btnkhongthich)

        Picasso.get().load(URL_LIKE).into(btnLike)
        Picasso.get().load(URL_UNLIKE).into(btnUnlike)

        val maso = intent.getStringExtra(KEY_MASO) ?: return
        masoView.text = maso

        val c = MainActivity.database.rawQuery(
            "SELECT TENBH1, LOIBH, TACGIA, YEUTHICH FROM ArirangSongList WHERE MABH=?",
            arrayOf(maso)
        )
        var thich = 0
        c.use {
            if (it.moveToFirst()) {
                baihat.text = it.getString(0)
                loibh.text = it.getString(1)
                tacgia.text = it.getString(2)
                thich = it.getInt(3)
            }
        }
        fun render(t: Int) {
            if (t == 1) { btnLike.visibility = View.VISIBLE; btnUnlike.visibility = View.INVISIBLE }
            else        { btnLike.visibility = View.INVISIBLE; btnUnlike.visibility = View.VISIBLE }
        }
        render(thich)

        btnLike.setOnClickListener {
            updateLike(maso, 0); render(0)
        }
        btnUnlike.setOnClickListener {
            updateLike(maso, 1); render(1)
        }
    }
    private fun updateLike(maso: String, value: Int) {
        val cv = ContentValues().apply { put("YEUTHICH", value) }
        MainActivity.database.update("ArirangSongList", cv, "MABH=?", arrayOf(maso))
    }
}