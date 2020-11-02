package com.pany.withrooms.model.users

data class UserData(
        val incomplete_results: Boolean,
        val items: List<Items>,
        val total_count: Int
)