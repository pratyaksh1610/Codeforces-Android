package com.pratyaksh_khurana.codeforcesandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pratyaksh_khurana.codeforcesandroid.Fragments.ContestsFragment
import com.pratyaksh_khurana.codeforcesandroid.Fragments.NewsFragment
import com.pratyaksh_khurana.codeforcesandroid.Fragments.ProblemsFragment
import com.pratyaksh_khurana.codeforcesandroid.Fragments.UsersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contests_bottom_bar_image.setColorFilter(resources.getColor(R.color.dark_gray))
        contests_bottom_bar_text.setTextColor(resources.getColor(R.color.dark_gray))

        // Default navigation
        supportFragmentManager.beginTransaction()
            ?.replace(R.id.fragment_container, ContestsFragment())
            ?.addToBackStack(R.string.fragment_contests_tag.toString())
            ?.commit()

        contests_bottom_bar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ContestsFragment())
                .addToBackStack(R.string.fragment_contests_tag.toString())
                .commit()
            setBottomBarIconAndTextColor(getString(R.string.fragment_contests_tag))
        }

        problems_bottom_bar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProblemsFragment())
                .addToBackStack(R.string.fragment_problems_tag.toString())
                .commit()
            setBottomBarIconAndTextColor(getString(R.string.fragment_problems_tag))
        }

        users_bottom_bar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, UsersFragment())
                .addToBackStack(R.string.fragment_users_tag.toString())
                .commit()
            setBottomBarIconAndTextColor(getString(R.string.fragment_users_tag))
        }

        news_bottom_bar.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewsFragment())
                .addToBackStack(R.string.fragment_news_tag.toString())
                .commit()
            setBottomBarIconAndTextColor(getString(R.string.fragment_news_tag))
        }
    }

    private fun setBottomBarIconAndTextColor(tag: String) {
        when (tag) {
            getString(R.string.fragment_contests_tag) -> {
                contests_bottom_bar_image.setColorFilter(resources.getColor(R.color.dark_gray))
                contests_bottom_bar_text.setTextColor(resources.getColor(R.color.dark_gray))

                users_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                users_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))

                news_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                news_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))

                problems_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                problems_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))
            }
            getString(R.string.fragment_users_tag) -> {
                contests_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                contests_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))

                users_bottom_bar_image.setColorFilter(resources.getColor(R.color.dark_gray))
                users_bottom_bar_text.setTextColor(resources.getColor(R.color.dark_gray))

                news_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                news_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))

                problems_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                problems_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))
            }
            getString(R.string.fragment_news_tag) -> {
                contests_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                contests_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))

                users_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                users_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))

                news_bottom_bar_image.setColorFilter(resources.getColor(R.color.dark_gray))
                news_bottom_bar_text.setTextColor(resources.getColor(R.color.dark_gray))

                problems_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                problems_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))
            }
            getString(R.string.fragment_problems_tag) -> {
                contests_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                contests_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))

                users_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                users_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))

                news_bottom_bar_image.setColorFilter(resources.getColor(R.color.gray))
                news_bottom_bar_text.setTextColor(resources.getColor(R.color.gray))

                problems_bottom_bar_image.setColorFilter(resources.getColor(R.color.dark_gray))
                problems_bottom_bar_text.setTextColor(resources.getColor(R.color.dark_gray))
            }
        }
    }
}
