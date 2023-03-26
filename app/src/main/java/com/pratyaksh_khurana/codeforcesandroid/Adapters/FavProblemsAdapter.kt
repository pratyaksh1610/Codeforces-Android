package com.pratyaksh_khurana.codeforcesandroid.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.pratyaksh_khurana.codeforcesandroid.Entities.EachProblem
import com.pratyaksh_khurana.codeforcesandroid.R

class FavProblemsAdapter(
    private val context: Context,
    private val data: List<EachProblem>,
    private val listener: FavouriteProblemsFragmentListener
) :
    RecyclerView.Adapter<FavProblemsAdapter.FavViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.each_problem_item_layout, parent, false)
        return FavViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.contestIdIndex.text =
            data[position].contestId.toString() + data[position].index + ":"
        holder.problemName.text = data[position].name
        holder.problemDetails.text =
            "Difficulty: " + data[position].rating.toString() + ", " + " Tags: " + data[position].t

        holder.addToFavourites.setOnClickListener {
            listener.removeFromFav(data[position])
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
        holder.addToFavourites.setColorFilter(Color.parseColor("#FFC107"))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contestIdIndex: TextView = itemView.findViewById(R.id.contestId_index)
        val problemName: TextView = itemView.findViewById(R.id.problem_name)
        val problemDetails: TextView = itemView.findViewById(R.id.problem_details)
        val addToFavourites: ImageView = itemView.findViewById(R.id.add_to_favourites)
    }
}

interface FavouriteProblemsFragmentListener {
    fun onClick(id: String, index: String)
    fun removeFromFav(data: EachProblem)
}
