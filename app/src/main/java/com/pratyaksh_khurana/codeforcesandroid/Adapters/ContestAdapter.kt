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
        val contestDate = objects[position].start.indexOf('T')
        val correctddMMYYYYFormat = objects[position].start.substring(0, contestDate)
        val correctDateFormat = context?.resources?.getString(
            R.string.correctDateFormat,
            correctddMMYYYYFormat[8].toString(),
            correctddMMYYYYFormat[9].toString(),
            correctddMMYYYYFormat[5].toString(),
            correctddMMYYYYFormat[6].toString(),
            correctddMMYYYYFormat[0].toString(),
            correctddMMYYYYFormat[1].toString(),
            correctddMMYYYYFormat[2].toString(),
            correctddMMYYYYFormat[3].toString()
        )
        val contestName = objects[position]?.event.toString()
        val contestWebsiteName = objects[position]?.host.toString()
        val contestDuration = (objects[position].duration.toString().toLong() / 3600).toString()

        holder.contestName.text = contestName
        holder.contestDetails.text = context?.resources?.getString(
            R.string.contestDetails,
            correctDateFormat,
            contestDuration,
            contestWebsiteName
        )

        if (context?.resources?.let { objects[position].host.startsWith(it.getString(R.string.codingNinjas)) } == true) {
            holder.contestIcon.setImageResource(R.drawable.cn)
        } else if (context?.resources?.let { objects[position].host.startsWith(it.getString(R.string.atcoder)) } == true) {
            holder.contestIcon.setImageResource(R.drawable.atcoder)
        } else if (context?.resources?.getString(R.string.kaggle)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.kaggle)
        } else if (context?.resources?.getString(R.string.codechef)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.codechef)
        } else if (context?.resources?.getString(R.string.codeforces)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.codeforces)
        } else if (context?.resources?.getString(R.string.leetcode)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.leetcode)
        } else if (context?.resources?.getString(R.string.lightoj)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.lightoj)
        } else if (context?.resources?.getString(R.string.hackerearth)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.hackerearth)
        } else if (context?.resources?.getString(R.string.projecteuler)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.projecteuler)
        } else if (context?.resources?.getString(R.string.gfg)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.gfg)
        } else if (context?.resources?.getString(R.string.eolymp)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.eolymp)
        } else if (context?.resources?.getString(R.string.prepbytes)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.prepbytes)
        } else if (context?.resources?.getString(R.string.topcoder)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.topcoder)
        } else if (context?.resources?.getString(R.string.algotester)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.algotester)
        } else if (context?.resources?.getString(R.string.ctftime)
            ?.let { objects[position].host.startsWith(it) } == true
        ) {
            holder.contestIcon.setImageResource(R.drawable.ctftime)
        } else {
            holder.contestIcon.setImageResource(R.drawable.google)
        }

        holder.contestIcon.setOnClickListener {
            listener.onClickOfContest(objects[position].href)
        }
        holder.contestName.setOnClickListener {
            listener.onClickOfContest(objects[position].href)
        }
        holder.contestDetails.setOnClickListener {
            listener.onClickOfContest(objects[position].href)
        }

        holder.addToCalender.setOnClickListener {
            listener.addToCalender(correctDateFormat.toString(), contestName, contestWebsiteName)
        }
    }

    class ContestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contestName: TextView = itemView.findViewById(R.id.contest_name)
        val contestDetails: TextView = itemView.findViewById(R.id.contest_details)
        val addToCalender: ImageView = itemView.findViewById(R.id.add_to_calender)
        val contestIcon: ImageView = itemView.findViewById(R.id.contest_icon)
    }
}

interface ContestFragmentListener {
    fun onClickOfContest(href: String)
    fun addToCalender(date: String, contestName: String, contestWebsiteName: String)
}
