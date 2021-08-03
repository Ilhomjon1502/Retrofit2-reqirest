package com.ilhomjon.reqirestr.retrofit

object Common {
    var BASE_URL = "https://reqres.in/api/"

    val retrofitService: RetrofitService
    get() = RetrofitClient.getRetrofit(BASE_URL).create(RetrofitService::class.java)
}