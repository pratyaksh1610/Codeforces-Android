package com.pratyaksh_khurana.codeforcesandroid.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pratyaksh_khurana.codeforcesandroid.R
import com.pratyaksh_khurana.codeforcesandroid.Viewmodel.ProblemViewModel
import kotlinx.android.synthetic.main.fragment_favourite_problems.view.*

class FavouriteProblemsFragment : Fragment() {
    private val viewModel: ProblemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite_problems, container, false)

        viewModel.getAllFavouriteProblems().observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it[0].name, Toast.LENGTH_SHORT).show()
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
}
