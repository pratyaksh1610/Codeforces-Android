package com.pratyaksh_khurana.codeforcesandroid.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pratyaksh_khurana.codeforcesandroid.DataClass.Problem
import com.pratyaksh_khurana.codeforcesandroid.R

class ProblemsAdapter(
    private val context: Context,
    private val data: List<Problem>,
    private val listener: ProblemsFragmentListener
) :
    RecyclerView.Adapter<ProblemsAdapter.ProblemsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProblemsViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.each_problem_item_layout, parent, false)
        return ProblemsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProblemsViewHolder, position: Int) {
        holder.index.text = data[position].contestId.toString() + data[position].index + ":"
        holder.name.text = data[position].name
        holder.detail.text =
            "Rating: " + data[position].rating.toString() + ", " + " Tags: " + data[position].tags

        holder.fav.setOnClickListener {
            Toast.makeText(context, "// TODO - Added to favourites", Toast.LENGTH_SHORT).show()
        }

        holder.name.setOnClickListener {
            listener.onClick(data[position].contestId.toString(), data[position].index)
        }

        holder.detail.setOnClickListener {
            listener.onClick(data[position].contestId.toString(), data[position].index)
        }
        holder.detail.setOnClickListener {
            listener.onClick(data[position].contestId.toString(), data[position].index)
        }
    }

    class ProblemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val index: TextView = itemView.findViewById(R.id.contestId_index)
        val name: TextView = itemView.findViewById(R.id.problem_name)
        val detail: TextView = itemView.findViewById(R.id.problem_details)
        val fav: ImageView = itemView.findViewById(R.id.add_to_favourites)
    }
}

interface ProblemsFragmentListener {
    fun onClick(id: String, index: String)
}
