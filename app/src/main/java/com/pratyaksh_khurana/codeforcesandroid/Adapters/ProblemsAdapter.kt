package com.pratyaksh_khurana.codeforcesandroid.Adapters

import android.annotation.SuppressLint
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProblemsViewHolder, position: Int) {
        holder.contestIdIndex.text =
            data[position].contestId.toString() + data[position].index + ":"
        holder.problemName.text = data[position].name
        holder.problemDetails.text =
            "Difficulty: " + data[position].rating.toString() + ", " + " Tags: " + data[position].tags

        holder.addToFavourites.setOnClickListener {
            Toast.makeText(context, "// TODO - Added to favourites", Toast.LENGTH_SHORT).show()
        }

        holder.problemName.setOnClickListener {
            listener.onClick(data[position].contestId.toString(), data[position].index)
        }

        holder.problemDetails.setOnClickListener {
            listener.onClick(data[position].contestId.toString(), data[position].index)
        }
        holder.problemDetails.setOnClickListener {
            listener.onClick(data[position].contestId.toString(), data[position].index)
        }
    }

    class ProblemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contestIdIndex: TextView = itemView.findViewById(R.id.contestId_index)
        val problemName: TextView = itemView.findViewById(R.id.problem_name)
        val problemDetails: TextView = itemView.findViewById(R.id.problem_details)
        val addToFavourites: ImageView = itemView.findViewById(R.id.add_to_favourites)
    }
}

interface ProblemsFragmentListener {
    fun onClick(id: String, index: String)
}
