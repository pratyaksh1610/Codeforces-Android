package com.pratyaksh_khurana.codeforcesandroid.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pratyaksh_khurana.codeforcesandroid.DataClass.contest_obj
import com.pratyaksh_khurana.codeforcesandroid.R

class ContestAdapter(
    private val context: Context?,
    private val objects: List<contest_obj>,
    private val listener: ContestFragmentListener
) :
    RecyclerView.Adapter<ContestAdapter.ContestViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.each_contest_item_layout, parent, false)
        return ContestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return objects.size
    }

    override fun onBindViewHolder(holder: ContestViewHolder, position: Int) {
        holder.name.text = objects[position].event
        holder.detail.text = objects[position].start + ", " + objects[position].host
        if (objects[position].host.startsWith("codingninjas")) {
            holder.icon.setImageResource(R.drawable.cn)
        } else if (objects[position].host.startsWith("atcoder")) {
            holder.icon.setImageResource(R.drawable.atcoder)
        } else if (objects[position].host.startsWith("kaggle")) {
            holder.icon.setImageResource(R.drawable.kaggle)
        } else if (objects[position].host.startsWith("codechef")) {
            holder.icon.setImageResource(R.drawable.codechef)
        } else if (objects[position].host.startsWith("codeforces")) {
            holder.icon.setImageResource(R.drawable.codeforces)
        } else if (objects[position].host.startsWith("leetcode")) {
            holder.icon.setImageResource(R.drawable.leetcode)
        } else if (objects[position].host.startsWith("hackerrank")) {
            holder.icon.setImageResource(R.drawable.hackerrank)
        } else if (objects[position].host.startsWith("hackerearth")) {
            holder.icon.setImageResource(R.drawable.hackerearth)
        } else if (objects[position].host.startsWith("projecteuler")) {
            holder.icon.setImageResource(R.drawable.projecteuler)
        } else if (objects[position].host.startsWith("newtonschool")) {
            holder.icon.setImageResource(R.drawable.newton_school)
        } else if (objects[position].host.startsWith("geeksforgeeks")) {
            holder.icon.setImageResource(R.drawable.gfg)
        } else if (objects[position].host.startsWith("newtonschool")) {
            holder.icon.setImageResource(R.drawable.newton_school)
        } else if (objects[position].host.startsWith("mycode.prepbytes.com")) {
            holder.icon.setImageResource(R.drawable.prepbytes)
        } else if (objects[position].host.startsWith("topcoder")) {
            holder.icon.setImageResource(R.drawable.topcoder)
        } else {
            holder.icon.setImageResource(R.drawable.google)
        }

        holder.icon.setOnClickListener {
            listener.onClickOfContest(objects[position].href)
        }
        holder.name.setOnClickListener {
            listener.onClickOfContest(objects[position].href)
        }
        holder.detail.setOnClickListener {
            listener.onClickOfContest(objects[position].href)
        }

        holder.add.setOnClickListener {
            listener.addToCalender(objects[position])
        }
    }

    class ContestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.contest_name)
        val detail: TextView = itemView.findViewById(R.id.contest_details)
        val add: ImageView = itemView.findViewById(R.id.add_to_calender)
        val icon: ImageView = itemView.findViewById(R.id.contest_icon)
    }
}

interface ContestFragmentListener {
    fun onClickOfContest(href: String)
    fun addToCalender(contestObj: contest_obj)
}
