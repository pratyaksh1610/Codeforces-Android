package com.pratyaksh_khurana.codeforcesandroid.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pratyaksh_khurana.codeforcesandroid.Adapters.NewsAdapter
import com.pratyaksh_khurana.codeforcesandroid.Adapters.NewsFragmentListener
import com.pratyaksh_khurana.codeforcesandroid.DataClass.news_json
import com.pratyaksh_khurana.codeforcesandroid.Interface.ApiInterface
import com.pratyaksh_khurana.codeforcesandroid.R
import kotlinx.android.synthetic.main.fragment_contests.*
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment(), NewsFragmentListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        loadNews()
        return view
    }

    private fun loadNews() {
        val retrofit = ApiInterface.Helper.initialiseRetrofitBuilderObjectProblem()
        showToast()
        val retrofitData = retrofit.getAllRecentActions()

        retrofitData.enqueue(object : Callback<news_json> {
            override fun onFailure(call: Call<news_json>, t: Throwable) {
//                fragment_contests_winner.visibility = View.VISIBLE
//                fragment_contest_error_msg.visibility = View.VISIBLE
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<news_json>,
                response: Response<news_json>
            ) {
//                fragment_contests_winner?.visibility = View.GONE
//                fragment_contest_error_msg?.visibility = View.GONE

                val data = response.body()
                if (data != null) {
                    news_rv?.layoutManager = LinearLayoutManager(context)
                    val adapter = context?.let {
                        NewsAdapter(it, data.result, this@NewsFragment)
                    }
                    news_rv?.adapter = adapter
                    adapter?.notifyDataSetChanged()
                } else {
//                    contest_rv?.visibility = View.GONE
//                    fragment_contests_winner?.visibility = View.VISIBLE
//                    fragment_contest_error_msg?.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showToast() {
        Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
    }

    companion object {
    }

    override fun onClick() {
    }
}
