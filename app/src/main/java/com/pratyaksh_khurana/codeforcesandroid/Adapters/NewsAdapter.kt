package com.pratyaksh_khurana.codeforcesandroid.Adapters

import android.content.Context
import android.os.Build
import android.text.Html
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

        holder.blogTitle.text = Html.fromHtml(data[position]?.blogEntry?.title.toString()).toString()
        holder.createdTime.text = "$random minutes ago"
        holder.modifiedTime.text = "$random1 minutes ago"
        holder.userHandle.text = data[position]?.blogEntry?.authorHandle.toString()
        holder.commentHandle.text = data[position]?.comment?.commentatorHandle.toString()
        holder.commentOfCommentHandle.text = Html.fromHtml(data[position]?.comment?.text.toString()).toString()

        val blogRating = data[position]?.blogEntry?.rating?.toString()?.toInt()
        val blogCommentRating = data[position]?.comment?.rating?.toString()?.toInt()
        if (blogRating != null) {
            if (blogRating < 0) {
                holder.blogRating.setTextColor(context.resources.getColor(R.color.gray))
                holder.blogRating.text = data[position]?.blogEntry?.rating?.toString()
            } else {
                holder.blogRating.setTextColor(context.resources.getColor(R.color.green))
                holder.blogRating.text = "+" + data[position]?.blogEntry?.rating?.toString()
            }
        }

        if (blogCommentRating != null) {
            if (blogCommentRating < 0) {
                holder.commentRating.setTextColor(context.resources.getColor(R.color.gray))
                holder.commentRating.text = data[position]?.comment?.rating?.toString()
            } else {
                holder.commentRating.setTextColor(context.resources.getColor(R.color.green))
                holder.commentRating.text = "+" + data[position]?.comment?.rating?.toString()
            }
        }

        holder.itemView.setOnClickListener {
            listener.onClickOpenBlog(data[position]?.blogEntry?.id.toString())
        }

        holder.seeAllComments.setOnClickListener {
            listener.onClickOpenComment(
                data[position]?.blogEntry?.id.toString(),
                data[position]?.comment?.parentCommentId.toString()
            )
        }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val blogTitle: TextView = itemView.findViewById(R.id.blog_title)
        val createdTime: TextView = itemView.findViewById(R.id.created_time)
        val modifiedTime: TextView = itemView.findViewById(R.id.modified_time)
        val userHandle: TextView = itemView.findViewById(R.id.user_handle)
        val commentHandle: TextView = itemView.findViewById(R.id.comment_handle)
        val commentOfCommentHandle: TextView = itemView.findViewById(R.id.comment_of_comment_handle)
        val blogRating: TextView = itemView.findViewById(R.id.blog_rating_points)
        val commentRating: TextView = itemView.findViewById(R.id.comment_on_blog_rating_points)
        val seeAllComments: TextView = itemView.findViewById(R.id.see_all_comments)
    }
}

interface NewsFragmentListener {
    fun onClickOpenComment(blogId: String, commentId: String)
    fun onClickOpenBlog(blogId: String)
}
