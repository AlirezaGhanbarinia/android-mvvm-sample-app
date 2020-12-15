package com.aliasadi.mvvm.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aliasadi.mvvm.R
import com.aliasadi.mvvm.data.network.model.Movie
import com.aliasadi.mvvm.ui.main.MovieAdapter.MovieViewHolder
import com.bumptech.glide.Glide
import java.util.*

class MovieAdapter(private val listener: MovieListener) : RecyclerView.Adapter<MovieViewHolder>() {

    interface MovieListener {
        fun onMovieClicked(movie: Movie)
    }

    private var items: List<Movie> = ArrayList()

    fun setItems(items: List<Movie>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun getItem(position: Int): Movie {
        return items[position]
    }

    inner class MovieViewHolder(itemView: View) : ViewHolder(itemView), View.OnClickListener {

        private var image: AppCompatImageView = itemView.findViewById(R.id.image)
        private var title: TextView = itemView.findViewById(R.id.title)
        private var desc: TextView = itemView.findViewById(R.id.desc)

        fun bind(position: Int) {
            val movie = getItem(position)
            setClickListener(movie)
            setTitle(movie.title)
            setImage(movie.image)
            setDescription(movie.description)
        }

        private fun setTitle(title: String?) {
            this.title.text = title
        }

        private fun setImage(imageUrl: String?) {
            Glide.with(itemView.context).load(imageUrl).into(image)
        }

        private fun setDescription(description: String?) {
            desc.text = description
        }

        private fun setClickListener(movie: Movie) {
            itemView.tag = movie
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            listener.onMovieClicked(view.tag as Movie)
        }
    }
}