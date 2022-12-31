package com.pratyaksh_khurana.codeforcesandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pratyaksh_khurana.codeforcesandroid.Fragments.ContestsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ContestsFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
    }
}
