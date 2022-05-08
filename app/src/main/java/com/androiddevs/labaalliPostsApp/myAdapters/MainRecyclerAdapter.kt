package com.androiddevs.labaalliPostsApp.myAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.labaalliPostsApp.R
import com.androiddevs.labaalliPostsApp.data.Post
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.post_grid_item.view.*

class MainRecyclerAdapter: RecyclerView.Adapter<MainRecyclerAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object: DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }


    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post_grid_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(data.image).into(image)
            Glide.with(this).load(data.owner?.picture).into(picture)
            text.text=data.text
            tag1.text=data.tags?.get(0)
            tag2.text=data.tags?.get(1)
            tag3.text=data.tags?.get(2)
            publishDate.text=data.publishDate
            firstname.text=data.owner?.firstName
            lastname.text=data.owner?.lastName
            title.text=data.owner?.title

            setOnClickListener{
                onItemClickListener?.let{it(data)}
            }
            setOnLongClickListener {
                onItemLongClickListener.let { it  }
                return@setOnLongClickListener true
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Post) -> Unit)? = null


    private var onItemLongClickListener: ((Post) -> Unit)? = null


    fun setOnItemClickListener(listener: (Post) -> Unit) {
        onItemClickListener = listener
    }
    fun setOnItemLongClickListener(listener: (Post) -> Unit) {
        onItemLongClickListener = listener
    }
}
