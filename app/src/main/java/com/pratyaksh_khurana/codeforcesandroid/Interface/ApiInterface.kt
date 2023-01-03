package com.pratyaksh_khurana.codeforcesandroid.Interface

import com.pratyaksh_khurana.codeforcesandroid.DataClass.codeforces_problem
import com.pratyaksh_khurana.codeforcesandroid.DataClass.contest
import com.pratyaksh_khurana.codeforcesandroid.DataClass.news_json
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/api/v2/contest/?username=pratyaksh&api_key=b5389d660473a204bfe80c1a7ee38d5579e521f0&upcoming=true")
    fun getContests(): Call<contest>

    @GET("/api/v2/contest/?username=pratyaksh&api_key=b5389d660473a204bfe80c1a7ee38d5579e521f0&upcoming=true")
    fun getContestsByHost(@Query("host") host: String): Call<contest>

    @GET("/api/problemset.problems/")
    fun getAllProblems(): Call<codeforces_problem>

    @GET("/api/recentActions?maxCount=100")
    fun getAllRecentActions(): Call<news_json>

    object Helper {
        private val BASE_URL_CONTESTS = "https://clist.by:443/"
        private val BASE_URL_PROBLEMS = "https://codeforces.com/"

        fun initialiseRetrofitBuilderObjectContest(): ApiInterface {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_CONTESTS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }

        fun initialiseRetrofitBuilderObjectProblem(): ApiInterface {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_PROBLEMS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}
