package org.mibrahim.movie.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_movie.view.*
import org.mibrahim.movie.core.R
import org.mibrahim.movie.core.domain.model.Movie
import org.mibrahim.movie.core.utils.Constants.Companion.BASE_IMAGE_URL
import java.util.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Movie) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(BASE_IMAGE_URL + data.backgroundPath)
                    .into(iv_item_image)
                tv_item_title.text = data.title
                tv_item_rating.text = data.rating.toString()
                Log.d("TAG", "bind: $BASE_IMAGE_URL${data.backgroundPath}")
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}