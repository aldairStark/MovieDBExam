package com.example.viewmodel.ui.movies

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.viewmodel.R
import com.example.viewmodel.common.Constans
import com.example.viewmodel.retrofit.models.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.view.*


class MovieRecyclerViewAdapter() : RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {
private var movies:List<Movie> = ArrayList()


    private val mOnclickListener: View.OnClickListener
    init{
        mOnclickListener= View.OnClickListener { v ->
            val item = v.tag as Movie
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.tvTitle.text = item.title
        holder.tvRanting.text = item.vote_average.toString()
        holder.ivPotho.load(Constans.IMAGE_URL+item.poster_path){
            crossfade(true)
            placeholder(R.drawable.ic_movies)
            transformations(CircleCropTransformation())
        }

        with(holder.itemView){
            tag=item
            setOnClickListener(mOnclickListener)

        }
    }

    override fun getItemCount(): Int =movies.size
    fun setData(popularMovies: List<Movie>?) {
        movies = popularMovies!!
        notifyDataSetChanged()

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = itemView.txtVw_Title
        val ivPotho: ImageView = itemView.IV_photho
        var tvRanting: TextView = itemView.txtVw_ranting

        override fun toString(): String {
            return super.toString() + " '" + tvTitle.text + "'"
        }
    }
}