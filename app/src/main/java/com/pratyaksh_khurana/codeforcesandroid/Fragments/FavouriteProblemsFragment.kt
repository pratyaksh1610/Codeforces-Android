package com.pratyaksh_khurana.codeforcesandroid.Fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratyaksh_khurana.codeforcesandroid.Adapters.FavProblemsAdapter
import com.pratyaksh_khurana.codeforcesandroid.Adapters.FavouriteProblemsFragmentListener
import com.pratyaksh_khurana.codeforcesandroid.Entities.EachProblem
import com.pratyaksh_khurana.codeforcesandroid.R
import com.pratyaksh_khurana.codeforcesandroid.Viewmodel.ProblemViewModel
import kotlinx.android.synthetic.main.fragment_favourite_problems.*
import kotlinx.android.synthetic.main.fragment_favourite_problems.view.*

class FavouriteProblemsFragment : Fragment(), FavouriteProblemsFragmentListener {
    private val viewModel: ProblemViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite_problems, container, false)

        viewModel.getAllFavouriteProblems().observe(viewLifecycleOwner) {
            problems_rv.layoutManager = LinearLayoutManager(context)
            val adapter = context?.let { it1 ->
                FavProblemsAdapter(
                    it1,
                    it.distinctBy { it.problemLink },
                    this
                )
            }
            problems_rv.adapter = adapter
            adapter?.notifyDataSetChanged()
        }

        view.fragment_favourite_problem_fab.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, ProblemsFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
        return view
    }

    companion object {
    }

    override fun onClick(id: String, index: String) {
        val href = getProblemLink(id, index)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(href))
    }

    private fun getProblemLink(id: String, index: String): String {
        return getString(R.string.open_problem, id, index)
    }

    override fun removeFromFav(data: EachProblem) {
        viewModel.removeFromFavourites(data)
    }
}
