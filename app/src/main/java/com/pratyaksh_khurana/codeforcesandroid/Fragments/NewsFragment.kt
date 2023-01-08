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
                showToast()
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<news_json>,
                response: Response<news_json>
            ) {
                val data = response.body()
                if (data != null) {
                    news_rv?.layoutManager = LinearLayoutManager(context)
                    val adapter = context?.let {
                        NewsAdapter(it, data.result, this@NewsFragment)
                    }
                    news_rv?.adapter = adapter
                    adapter?.notifyDataSetChanged()
                }
            }
        })
    }

    private fun showToast() {
        Toast.makeText(requireContext(), R.string.loading, Toast.LENGTH_SHORT).show()
    }

    companion object {
    }

    override fun onClickOpenComment(blogId: String, commentId: String) {
        val href = getString(R.string.open_blog_comment, blogId, commentId)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(href))
    }

    override fun onClickOpenBlog(blogId: String) {
        val href = getString(R.string.open_blog, blogId)
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(requireContext(), Uri.parse(href))
    }
}
