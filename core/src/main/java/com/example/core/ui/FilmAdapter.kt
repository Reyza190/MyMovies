package com.example.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemListFilmBinding
import com.example.core.domain.model.Film
import com.example.core.utils.Url
import com.squareup.picasso.Picasso

class FilmAdapter(private val onItemClick: (Film) -> Unit) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private var listData = ArrayList<Film>()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Film>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.FilmViewHolder  =
        FilmViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_film, parent, false))

    override fun onBindViewHolder(holder: FilmAdapter.FilmViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
    inner class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListFilmBinding.bind(itemView)
        fun bind(data: Film){
            with(binding){
                tvTitle.text = data.title
                Picasso.get()
                    .load(Url.image + data.image)
                    .into(ivFilm)
                root.setOnClickListener {
                    onItemClick(data)
                }
            }
        }
    }
}