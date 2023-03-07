package com.pratyaksh_khurana.codeforcesandroid.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO add tags list to show in favourites
// table formed in SQLite
@Entity(tableName = "problem_table")
data class EachProblem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    val contestId: Int,
    val index: String,
    val name: String,
    val points: Int,
    val rating: Int,
    val type: String,
    val problemLink: String,
    val systemDate: String,
    val t: String
)
