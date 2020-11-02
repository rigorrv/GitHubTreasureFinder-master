package com.pany.withrooms.model.repos

import androidx.room.PrimaryKey

data class RepoData(
        val incomplete_results: Boolean,
        val items: List<Item>,
        val total_count: Int
)