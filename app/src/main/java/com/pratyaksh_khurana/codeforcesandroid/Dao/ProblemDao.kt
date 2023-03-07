package com.pratyaksh_khurana.codeforcesandroid.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.pratyaksh_khurana.codeforcesandroid.Entities.EachProblem

@Dao
interface ProblemDao {

    @androidx.room.Query("SELECT * FROM problem_table ORDER BY systemDate desc")
    fun getAllFavouriteProblems(): LiveData<List<EachProblem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavourites(eachProblem: EachProblem)

    @Delete
    fun removeFromFavourites(problemEntity: EachProblem)
}
