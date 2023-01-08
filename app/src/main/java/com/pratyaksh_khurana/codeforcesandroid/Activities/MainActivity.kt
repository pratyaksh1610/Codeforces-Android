package com.pratyaksh_khurana.codeforcesandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pratyaksh_khurana.codeforcesandroid.Fragments.ContestsFragment
import com.pratyaksh_khurana.codeforcesandroid.Fragments.NewsFragment
import com.pratyaksh_khurana.codeforcesandroid.Fragments.ProblemsFragment
import com.pratyaksh_khurana.codeforcesandroid.Fragments.UsersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Default navigation
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ContestsFragment())
            .addToBackStack(R.string.fragment_contests_tag.toString())
            .commit()

        contests_bottom_bar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ContestsFragment())
                .addToBackStack(R.string.fragment_contests_tag.toString())
                .commit()
        }

        problems_bottom_bar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProblemsFragment())
                .addToBackStack(R.string.fragment_problems_tag.toString())
                .commit()
        }

        users_bottom_bar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UsersFragment())
                .addToBackStack(R.string.fragment_users_tag.toString())
                .commit()
        }

        news_bottom_bar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewsFragment())
                .addToBackStack(R.string.fragment_news_tag.toString())
                .commit()
        }
    }
}
