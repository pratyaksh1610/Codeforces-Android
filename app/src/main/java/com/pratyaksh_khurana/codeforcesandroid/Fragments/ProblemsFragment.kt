package com.pratyaksh_khurana.codeforcesandroid.Fragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratyaksh_khurana.codeforcesandroid.Adapters.ProblemsAdapter
import com.pratyaksh_khurana.codeforcesandroid.Adapters.ProblemsFragmentListener
import com.pratyaksh_khurana.codeforcesandroid.DataClass.codeforces_problem
import com.pratyaksh_khurana.codeforcesandroid.Interface.ApiInterface
import com.pratyaksh_khurana.codeforcesandroid.R
import kotlinx.android.synthetic.main.fragment_contests.*
import kotlinx.android.synthetic.main.fragment_problems.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProblemsFragment : Fragment(), ProblemsFragmentListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_problems, container, false)

        // load problems on fragment
        loadProblems()

        return view
    }

    private fun loadProblems() {
        val retrofit = ApiInterface.Helper.initialiseRetrofitBuilderObjectProblem()
        showToast()
        val retrofitData = retrofit.getAllProblems()

        retrofitData.enqueue(object : Callback<codeforces_problem> {
            override fun onFailure(call: Call<codeforces_problem>, t: Throwable) {
                showToast()
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
        val href = "https://codeforces.com/contest/$id/problem/$index"
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(href))
    }

    private fun showToast() {
        Toast.makeText(requireContext(), R.string.loading, Toast.LENGTH_SHORT).show()
    }
}
