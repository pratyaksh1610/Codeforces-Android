package com.pratyaksh_khurana.codeforcesandroid.Fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratyaksh_khurana.codeforcesandroid.Adapters.ContestAdapter
import com.pratyaksh_khurana.codeforcesandroid.Adapters.ContestFragmentListener
import com.pratyaksh_khurana.codeforcesandroid.DataClass.contest
import com.pratyaksh_khurana.codeforcesandroid.Interface.ApiInterface
import com.pratyaksh_khurana.codeforcesandroid.R
import kotlinx.android.synthetic.main.fragment_contests.*
import kotlinx.android.synthetic.main.fragment_contests.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ContestsFragment : Fragment(), ContestFragmentListener {

    private lateinit var contestHost: String

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contests, container, false)

        // load contest data when loading this fragment
        loadAllContests()

        view.fragment_contest_websites.visibility = View.GONE

        view.fragment_contests_menu_icon.setOnClickListener {
            if (view.fragment_contest_websites.isVisible) {
                view.fragment_contest_websites.visibility = View.GONE
            } else {
                view.fragment_contest_websites.visibility = View.VISIBLE
            }
        }

        view.fragment_contests_codeforces.setOnClickListener {
            setContestHost(getString(R.string.codeforces))
            loadAllContests(true, contestHost)
        }
        view.fragment_contests_codechef.setOnClickListener {
            setContestHost(getString(R.string.codechef))
            loadAllContests(true, contestHost)
        }

        view.fragment_contests_atcoder.setOnClickListener {
            setContestHost(getString(R.string.atcoder))
            loadAllContests(true, contestHost)
        }
        view.fragment_contests_leetcode.setOnClickListener {
            setContestHost(getString(R.string.leetcode))
            loadAllContests(true, contestHost)
        }
        view.fragment_contests_hackerearth.setOnClickListener {
            setContestHost(getString(R.string.hackerearth))
            loadAllContests(true, contestHost)
        }
        view.fragment_contest_lightoj.setOnClickListener {
            setContestHost(getString(R.string.lightoj))
            loadAllContests(true, contestHost)
        }
        view.fragment_contests_coding_ninjas.setOnClickListener {
            setContestHost(getString(R.string.codingNinjas))
            loadAllContests(true, contestHost)
        }
        view.fragment_contests_gfg.setOnClickListener {
            setContestHost(getString(R.string.gfg))
            loadAllContests(true, contestHost)
        }
        view.fragment_contests_kaggle.setOnClickListener {
            setContestHost(getString(R.string.kaggle))
            loadAllContests(true, contestHost)
        }
        view.fragment_contests_project_euler.setOnClickListener {
            setContestHost(getString(R.string.projecteuler))
            loadAllContests(true, contestHost)
        }

        view.fragment_contests_eolymp.setOnClickListener {
            setContestHost(getString(R.string.eolymp))
            loadAllContests(true, contestHost)
        }

        view.fragment_contests_google_kickstart_codejam.setOnClickListener {
            setContestHost(getString(R.string.google_kickstart))
            loadAllContests(true, contestHost)
        }

        view.fragment_contests_prepbytes.setOnClickListener {
            setContestHost(getString(R.string.prepbytes))
            loadAllContests(true, contestHost)
        }

        view.fragment_contests_topcoder.setOnClickListener {
            setContestHost(getString(R.string.topcoder))
            loadAllContests(true, contestHost)
        }

        view.fragment_contests_ctftime.setOnClickListener {
            setContestHost(getString(R.string.ctftime))
            loadAllContests(true, contestHost)
        }

        view.fragment_contests_fab.setOnClickListener {
            openChromeCustomTabAllContestList()
        }
        view.filter_none.setOnClickListener {
            loadAllContests()
        }

        return view
    }

    private fun loadAllContests(flag: Boolean = false, host: String = "") {
        val retrofit = ApiInterface.Helper.initialiseRetrofitBuilderObjectContest()
        showToast()
        if (flag) {
            val retrofitData = retrofit.getContestsByHost(host)

            retrofitData.enqueue(object : Callback<contest> {
                override fun onFailure(call: Call<contest>, t: Throwable) {
                    showToast()
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<contest>, response: Response<contest>) {
                    val data = response.body()
                    if (data != null) {
                        contest_rv?.layoutManager = LinearLayoutManager(context)
                        val adapter = ContestAdapter(context, data.objects, this@ContestsFragment)
                        contest_rv?.adapter = adapter
                        adapter?.notifyDataSetChanged()
                    }
                }
            })
        } else {
            val retrofitData = retrofit.getContests()

            retrofitData.enqueue(object : Callback<contest> {
                override fun onFailure(call: Call<contest>, t: Throwable) {
                    showToast()
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<contest>, response: Response<contest>) {
                    val data = response.body()
                    if (data != null) {
                        contest_rv?.layoutManager = LinearLayoutManager(context)
                        val adapter = ContestAdapter(context, data.objects, this@ContestsFragment)
                        contest_rv?.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
            })
        }
    }

    private fun setContestHost(host: String) {
        contestHost = host
    }

    private fun openChromeCustomTabAllContestList() {
        val url = getString(R.string.clist_url)
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

    companion object {
    }

    private fun showToast() {
        Toast.makeText(requireContext(), R.string.loading, Toast.LENGTH_SHORT).show()
    }
}
