package com.dicoding.myrecyclerview

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.myrecyclerview.databinding.ItemRowHeroBinding
import java.util.ArrayList

class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowHeroBinding) : RecyclerView.ViewHolder(binding.root)
//        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
//        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
//        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo, rating) = listHero[position]
//        holder.imgPhoto.setImageResource(photo)
        Glide.with(holder.itemView.context)
            .load(photo) // URL Gambar
            .into(holder.binding.imgItemPhoto) // imageView mana yang akan diterapkan

        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }


    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }
}