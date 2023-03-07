package com.pratyaksh_khurana.codeforcesandroid.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pratyaksh_khurana.codeforcesandroid.Database.database
import com.pratyaksh_khurana.codeforcesandroid.Entities.EachProblem
import com.pratyaksh_khurana.codeforcesandroid.Repository.Repository

// /it is lifecycle aware, knows when to supply data to activity
class ProblemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    private val allProblems: LiveData<List<EachProblem>>

    init {
        val dao = database.getDatabase(application).problemDao()
        repository = Repository(dao)
        allProblems = repository.getAllFavouriteProblems()
    }

    fun getAllFavouriteProblems(): LiveData<List<EachProblem>> {
        return repository.getAllFavouriteProblems()
    }

    fun addToFavourites(problemEntity: EachProblem) {
        repository.addToFavourites(problemEntity)
    }

     fun removeFromFavourites(problemEntity: EachProblem) {
        repository.removeFromFavourites(problemEntity)
    }
}
