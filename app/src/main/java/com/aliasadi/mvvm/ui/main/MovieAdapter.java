package com.aliasadi.mvvm.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aliasadi.mvvm.data.network.model.Movie;
import com.aliasadi.mvvm.databinding.ItemMovieBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali Asadi on 24/03/2018.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public interface MovieListener {
        void onMovieClicked(Movie movie);
    }

    private List<Movie> items;
    private MovieListener listener;

    public MovieAdapter(MovieListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    public void setItems(List<Movie> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private Movie getItem(int position) {
        return items.get(position);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemMovieBinding binding;

        MovieViewHolder(ItemMovieBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        void bind(int position) {
            Movie movie = getItem(position);

            setClickListener(movie);
            setTitle(movie.getTitle());
            setImage(movie.getImage());
            setDescription(movie.getDescription());
        }

        private void setTitle(String title) {
            this.binding.title.setText(title);
        }

        private void setImage(String imageUrl) {
            Glide.with(itemView.getContext()).load(imageUrl).into(binding.image);
        }

        private void setDescription(String description) {
            binding.desc.setText(description);
        }

        private void setClickListener(Movie movie) {
            itemView.setTag(movie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onMovieClicked((Movie) view.getTag());
        }
    }
}