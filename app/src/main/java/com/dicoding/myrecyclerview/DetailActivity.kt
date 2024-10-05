package com.dicoding.myrecyclerview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val DATA = "data"
    }

    private lateinit var hero: Hero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_page)

        // Mendapatkan data dari Intent dengan cara yang diperbarui
        hero = intent.getParcelableExtra<Hero>(DATA) ?: return

        // Inisialisasi komponen UI
        if (hero != null) {
            val imageView: ImageView = findViewById(R.id.movies_image)
            val nameTextView: TextView = findViewById(R.id.movies_name)
            val descriptionTextView: TextView = findViewById(R.id.textView2)
            val ratingTextView: TextView = findViewById(R.id.rating)
            val btnShare: Button = findViewById(R.id.action_share)

            // Menampilkan gambar dari URL menggunakan Glide atau library serupa
            Glide.with(this)
                .load(hero.photo)
                .into(imageView)

            nameTextView.text = hero.name
            descriptionTextView.text = hero.description
            ratingTextView.text = hero.rating // Pastikan 'rating' ada di objek Hero

            btnShare.setOnClickListener(this)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.action_share -> shareMovieDetails()
        }
    }

    private fun shareMovieDetails() {
        // Buat pesan yang ingin dibagikan
        val shareText =
            "Check out this movie: ${hero.name}\nDescription: ${hero.description}"

        // Buat Intent untuk membagikan informasi
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        // Memulai aktivitas berbagi
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}