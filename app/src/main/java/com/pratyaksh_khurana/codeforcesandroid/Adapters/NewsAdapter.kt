package com.pratyaksh_khurana.codeforcesandroid.Adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.pratyaksh_khurana.codeforcesandroid.DataClass.Result_1
import com.pratyaksh_khurana.codeforcesandroid.R

class NewsAdapter(
    private val context: Context,
    private val data: List<Result_1>,
    private val listener: NewsFragmentListener
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.each_news_item_layout, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val random = (20..50).random()
        val random1 = (1..19).random()
        holder.blog_title.text = data[position]?.blogEntry?.title
        holder.createdTime.text = "created $random minutes ago"
        holder.modified_time.text = "modified $random1 minutes ago"
        holder.userHandle.text = data[position]?.blogEntry?.authorHandle
        holder.commentHandle.text = data[position]?.comment?.commentatorHandle
        holder.commentOfCommentHandle.text = data[position]?.comment?.text
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val blog_title: TextView = itemView.findViewById(R.id.blog_title)
        val createdTime: TextView = itemView.findViewById(R.id.created_time)
        val modified_time: TextView = itemView.findViewById(R.id.modified_time)
        val userHandle: TextView = itemView.findViewById(R.id.user_handle)
        val commentHandle: TextView = itemView.findViewById(R.id.comment_handle)
        val commentOfCommentHandle: TextView = itemView.findViewById(R.id.comment_of_comment_handle)
    }
}

interface NewsFragmentListener {
    fun onClick()
}
