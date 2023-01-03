package com.pratyaksh_khurana.codeforcesandroid.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratyaksh_khurana.codeforcesandroid.Adapters.ContestAdapter
import com.pratyaksh_khurana.codeforcesandroid.Adapters.ContestFragmentListener
import com.pratyaksh_khurana.codeforcesandroid.DataClass.contest
import com.pratyaksh_khurana.codeforcesandroid.DataClass.contest_obj
import com.pratyaksh_khurana.codeforcesandroid.Interface.ApiInterface
import com.pratyaksh_khurana.codeforcesandroid.R
import kotlinx.android.synthetic.main.fragment_contests.*
import kotlinx.android.synthetic.main.fragment_contests.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ContestsFragment : Fragment(), ContestFragmentListener {

    private lateinit var host: String

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contests, container, false)

        // load contest data
        loadContests()
        view.fragment_contest_websites.visibility = View.GONE

        view.fragment_contests_menu_icon.setOnClickListener {
            if (view.fragment_contest_websites.isVisible) {
                view.fragment_contest_websites.visibility = View.GONE
            } else {
                view.fragment_contest_websites.visibility = View.VISIBLE
            }
        }

        view.fragment_contests_codeforces.setOnClickListener {
            host = "codeforces.com"
            loadContests(true, host)
        }
        view.fragment_contests_codechef.setOnClickListener {
            host = "codechef.com"
            loadContests(true, host)
        }

        view.fragment_contests_atcoder.setOnClickListener {
            host = "atcoder.jp"
            loadContests(true, host)
        }
        view.fragment_contests_leetcode.setOnClickListener {
            host = "leetcode.com"
            loadContests(true, host)
        }
        view.fragment_contests_hackerearth.setOnClickListener {
            host = "hackerearth.com"
            loadContests(true, host)
        }
        view.fragment_contests_hackerrank.setOnClickListener {
            host = "codeforces.com"
            loadContests(true, host)
        }
        view.fragment_contests_coding_ninjas.setOnClickListener {
            host = "codingninjas.com/codestudio"
            loadContests(true, host)
        }
        view.fragment_contests_gfg.setOnClickListener {
            host = "geeksforgeeks.com"
            loadContests(true, host)
        }
        view.fragment_contests_kaggle.setOnClickListener {
            host = "kaggle.com"
            loadContests(true, host)
        }
        view.fragment_contests_project_euler.setOnClickListener {
            host = "codeforces.com"
            loadContests(true, host)
        }

        view.fragment_contests_newton_school.setOnClickListener {
            host = "codeforces.com"
            loadContests(true, host)
        }

        view.fragment_contests_google_kickstart_codejam.setOnClickListener {
            host = "codingcompetitions.withgoogle.com/kickstart"
            loadContests(true, host)
        }

        view.fragment_contests_prepbytes.setOnClickListener {
            host = "mycode.prepbytes.com"
            loadContests(true, host)
        }

        view.fragment_contests_topcoder.setOnClickListener {
            host = "topcoder.com"
            loadContests(true, host)
        }
        view.fragment_contests_fab.setOnClickListener {
            openChromeCustomTabContestList()
        }
        view.filter_none.setOnClickListener {
            loadContests()
//            view.fragment_contest_websites.visibility = View.GONE
        }

        return view
    }

    private fun loadContests(flag: Boolean = false, host: String = "") {
        val retrofit = ApiInterface.Helper.initialiseRetrofitBuilderObjectContest()
        showToast()
        if (flag) {
            val retrofitData = retrofit.getContestsByHost(host)

            retrofitData.enqueue(object : Callback<contest> {
                override fun onFailure(call: Call<contest>, t: Throwable) {
                    fragment_contests_winner.visibility = View.VISIBLE
                    fragment_contest_error_msg.visibility = View.VISIBLE
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<contest>, response: Response<contest>) {
                    fragment_contests_winner.visibility = View.GONE
                    fragment_contest_error_msg.visibility = View.GONE

                    val data = response.body()
                    if (data != null) {
                        contest_rv.layoutManager = LinearLayoutManager(context)
                        val adapter = ContestAdapter(context, data.objects, this@ContestsFragment)
                        contest_rv.adapter = adapter
                        adapter.notifyDataSetChanged()
                    } else {
                        contest_rv.visibility = View.GONE
                        fragment_contests_winner.visibility = View.VISIBLE
                        fragment_contest_error_msg.visibility = View.VISIBLE
                    }
                }
            })
        } else {
            val retrofitData = retrofit.getContests()

            retrofitData.enqueue(object : Callback<contest> {
                override fun onFailure(call: Call<contest>, t: Throwable) {
                    fragment_contests_winner.visibility = View.VISIBLE
                    fragment_contest_error_msg.visibility = View.VISIBLE
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<contest>, response: Response<contest>) {
                    fragment_contests_winner?.visibility = View.GONE
                    fragment_contest_error_msg?.visibility = View.GONE

                    val data = response.body()
                    if (data != null) {
                        contest_rv?.layoutManager = LinearLayoutManager(context)
                        val adapter = ContestAdapter(context, data.objects, this@ContestsFragment)
                        contest_rv?.adapter = adapter
                        adapter.notifyDataSetChanged()
                    } else {
                        contest_rv.visibility = View.GONE
                        fragment_contests_winner.visibility = View.VISIBLE
                        fragment_contest_error_msg.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun openChromeCustomTabContestList() {
        val url = "https://clist.by/"
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
    }

    // listener to ContestAdapter
    override fun onClickOfContest(href: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(href))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun addToCalender(contestObj: contest_obj) {
        val intent = Intent(Intent.ACTION_INSERT)
        intent.data = CalendarContract.Events.CONTENT_URI
        val date = getStartDate(contestObj.start)
        intent.putExtra(CalendarContract.Events.TITLE, contestObj.event)
        intent.putExtra(CalendarContract.Events.DESCRIPTION, contestObj.host)
        intent.putExtra(CalendarContract.Events.ALL_DAY, true)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, date)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, contestObj.end)
        startActivity(intent)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getStartDate(date: String): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = dateFormat.parse(date)
        val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(date)
    }

    companion object {
    }

    private fun showToast() {
        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
    }
}
