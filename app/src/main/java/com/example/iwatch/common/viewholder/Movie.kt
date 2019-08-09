package com.example.iwatch.common.viewholder

import com.example.iwatch.R
import com.example.iwatch.common.Model.top_rated_movies_model.Result
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.movie_layout.view.*

class Movie(val response:Result):Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.movie_layout
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+response.poster_path).into(viewHolder.itemView.posterImage)

    }

}