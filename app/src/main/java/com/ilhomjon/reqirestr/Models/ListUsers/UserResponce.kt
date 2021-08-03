package com.ilhomjon.reqirestr.Models.ListUsers

data class UserResponce(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
)