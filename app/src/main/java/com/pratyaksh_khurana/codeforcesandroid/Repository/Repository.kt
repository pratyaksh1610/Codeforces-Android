package com.pratyaksh_khurana.codeforcesandroid.Repository

import androidx.lifecycle.LiveData
import com.pratyaksh_khurana.codeforcesandroid.Dao.ProblemDao
import com.pratyaksh_khurana.codeforcesandroid.Entities.EachProblem

class Repository(private val dao: ProblemDao) {

    fun getAllFavouriteProblems(): LiveData<List<EachProblem>> {
        return dao.getAllFavouriteProblems()
    }
    fun addToFavourites(problemEntity: EachProblem) {
        dao.addToFavourites(problemEntity)
    }

    fun removeFromFavourites(problemEntity: EachProblem) {
        dao.removeFromFavourites(problemEntity)
    }
}
