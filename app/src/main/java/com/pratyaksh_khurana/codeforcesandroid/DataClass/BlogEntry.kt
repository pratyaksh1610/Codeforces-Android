package com.pratyaksh_khurana.codeforcesandroid.DataClass

data class BlogEntry(
    val allowViewHistory: Boolean,
    val authorHandle: String,
    val creationTimeSeconds: Int,
    val id: Int,
    val locale: String,
    val modificationTimeSeconds: Int,
    val originalLocale: String,
    val rating: Int,
    val tags: List<String>,
    val title: String
)