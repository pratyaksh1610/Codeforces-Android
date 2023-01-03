package com.pratyaksh_khurana.codeforcesandroid.DataClass

data class Problem(
    val contestId: Int,
    val index: String,
    val name: String,
    val points: Int,
    val rating: Int,
    val tags: List<String>,
    val type: String
)