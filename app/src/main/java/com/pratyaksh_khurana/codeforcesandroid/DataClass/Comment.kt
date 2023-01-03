package com.pratyaksh_khurana.codeforcesandroid.DataClass

data class Comment(
    val commentatorHandle: String,
    val creationTimeSeconds: Int,
    val id: Int,
    val locale: String,
    val parentCommentId: Int,
    val rating: Int,
    val text: String
)
