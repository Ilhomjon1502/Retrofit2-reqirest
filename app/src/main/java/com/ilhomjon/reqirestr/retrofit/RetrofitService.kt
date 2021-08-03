package com.ilhomjon.reqirestr.retrofit

import com.ilhomjon.reqirestr.Models.Created.ReqUser
import com.ilhomjon.reqirestr.Models.Created.ResUser
import com.ilhomjon.reqirestr.Models.ListUsers.UserResponce
import com.ilhomjon.reqirestr.Models.SingleUsers.SingleUserResponse
import com.ilhomjon.reqirestr.Models.Update.ResUpdateUser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("users")
    fun getUsers():Call<UserResponce>

    @GET("users")
    fun getUsers(@Query("delay") delay:Int):Call<UserResponce>
    // delay parametri shuncha sekuntdan keyin ma'lumot kelishi uchun

    @GET("users/{id}")
    fun getSingleUser(@Path("id") id:Int):Call<SingleUserResponse>

    @POST("users")
    fun createUser(@Body reqUser: ReqUser):Call<ResUser>

    @PUT("users/{id}") //@PUT bilan @PATCH bir xil narsa
    fun updateUser(@Path("id") id:Int, @Body reqUser: ReqUser):Call<ResUpdateUser>

    @DELETE("user/{id}")
    fun deleteUser(@Path("id") id:Int):Call<ResponseBody>
}