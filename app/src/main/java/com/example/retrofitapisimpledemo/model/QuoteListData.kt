package com.example.retrofitapisimpledemo.model

// data class QuoteList
// according to JSON response


data class QuoteListData(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<ResultData>,
    val totalCount: Int,
    val totalPages: Int
)
