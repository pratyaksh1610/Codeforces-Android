package com.pratyaksh_khurana.codeforcesandroid.Fragments

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratyaksh_khurana.codeforcesandroid.Adapters.ProblemsAdapter
import com.pratyaksh_khurana.codeforcesandroid.Adapters.ProblemsFragmentListener
import com.pratyaksh_khurana.codeforcesandroid.DataClass.Problem
import com.pratyaksh_khurana.codeforcesandroid.DataClass.codeforces_problem
import com.pratyaksh_khurana.codeforcesandroid.Entities.EachProblem
import com.pratyaksh_khurana.codeforcesandroid.Interface.ApiInterface
import com.pratyaksh_khurana.codeforcesandroid.R
import com.pratyaksh_khurana.codeforcesandroid.Viewmodel.ProblemViewModel
import kotlinx.android.synthetic.main.fragment_contests.*
import kotlinx.android.synthetic.main.fragment_contests.view.*
import kotlinx.android.synthetic.main.fragment_contests.view.fragment_contests_fab
import kotlinx.android.synthetic.main.fragment_problems.*
import kotlinx.android.synthetic.main.fragment_problems.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ProblemsFragment : Fragment(), ProblemsFragmentListener {

    private val viewModel: ProblemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_problems, container, false)

        // load problems on fragment
        loadProblems()

        view.fragment_contests_fab.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, FavouriteProblemsFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        view.fragment_contest_problems_by_rating.visibility = View.GONE

        view.problemsByRating.setOnClickListener {
            if (view.fragment_contest_problems_by_rating.isVisible) {
                view.fragment_contest_problems_by_rating.visibility = View.GONE
            } else {
                view.fragment_contest_problems_by_rating.visibility = View.VISIBLE
            }
        }




        return view
    }

    private fun loadProblems() {
        val retrofit = ApiInterface.Helper.initialiseRetrofitBuilderObjectProblem()
        showToast("Loading...")
        val retrofitData = retrofit.getAllProblems()

        retrofitData.enqueue(object : Callback<codeforces_problem> {
            override fun onFailure(call: Call<codeforces_problem>, t: Throwable) {
                showToast("Loading...")
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<codeforces_problem>,
                response: Response<codeforces_problem>
            ) {
                val data = response.body()
                if (data != null) {
                    problems_rv?.layoutManager = LinearLayoutManager(context)
                    val adapter = context?.let {
                        ProblemsAdapter(
                            it,
                            data.result.problems,
                            this@ProblemsFragment
                        )
                    }
                    problems_rv?.adapter = adapter
                    adapter?.notifyDataSetChanged()
                }
            }
        })
    }

    companion object {
    }

    override fun onClick(id: String, index: String) {
        val href = getProblemLink(id, index)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(href))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun addToFav(data: Problem) {
        val problemLink = getProblemLink(data.contestId.toString(), data.index)
        // TODO add tags list to show in favourites
        val listOfTags = StringBuilder()
        for (i in data.tags.toString()) {
            listOfTags.append(i)
        }

        viewModel.addToFavourites(
            EachProblem(
                null,
                data.contestId,
                data.index,
                data.name,
                data.points,
                data.rating,
                data.type,
                problemLink,
                SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()).toString(),
                listOfTags.toString()
            )
        )
    }

    private fun getProblemLink(id: String, index: String): String {
        return getString(R.string.open_problem, id, index)
    }

    private fun showToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }
}
