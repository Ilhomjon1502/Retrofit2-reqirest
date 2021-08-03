package com.ilhomjon.reqirestr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ilhomjon.reqirestr.Models.Created.ReqUser
import com.ilhomjon.reqirestr.Models.Created.ResUser
import com.ilhomjon.reqirestr.Models.ListUsers.UserResponce
import com.ilhomjon.reqirestr.Models.SingleUsers.SingleUserResponse
import com.ilhomjon.reqirestr.Models.Update.ResUpdateUser
import com.ilhomjon.reqirestr.databinding.ActivityMainBinding
import com.ilhomjon.reqirestr.retrofit.Common
import com.ilhomjon.reqirestr.retrofit.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    //documentation: https://square.github.io/retrofit/
    //ip olish uchun: https://reqres.in/

    lateinit var binding: ActivityMainBinding
    lateinit var retrofitService: RetrofitService

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofitService = Common.retrofitService

        retrofitService.getUsers().enqueue(object : Callback<UserResponce> {
            override fun onResponse(call: Call<UserResponce>, response: Response<UserResponce>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "response: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<UserResponce>, t: Throwable) {

            }
        })

        //id orqali bitta user ma'lumotini olib kelish tanlab
        retrofitService.getSingleUser(23).enqueue(object : Callback<SingleUserResponse> {
            override fun onResponse(call: Call<SingleUserResponse>, response: Response<SingleUserResponse>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "responseSingle: ${response.body()?.data}")
                }
            }

            override fun onFailure(call: Call<SingleUserResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })

        //post
        val reqUser = ReqUser("Developer", "Ilhomjon")
        retrofitService.createUser(reqUser).enqueue(object : Callback<ResUser> {
            override fun onResponse(call: Call<ResUser>, response: Response<ResUser>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ResUser>, t: Throwable) {

            }
        })

        //update
        val reqUser2 = ReqUser("Ikromjon", "Developer")
        retrofitService.updateUser(2, reqUser2).enqueue(object : Callback<ResUpdateUser> {
            override fun onResponse(call: Call<ResUpdateUser>, response: Response<ResUpdateUser>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "update: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ResUpdateUser>, t: Throwable) {

            }
        })

        //delete
        retrofitService.deleteUser(1).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "delete: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

        })
    }
}
