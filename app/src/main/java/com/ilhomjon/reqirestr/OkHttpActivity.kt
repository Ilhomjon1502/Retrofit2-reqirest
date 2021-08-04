package com.ilhomjon.reqirestr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class OkHttpActivity : AppCompatActivity() {

    var okHttpClient = OkHttpClient()
    var url = "https://api.icndb.com/jokes/random"
    private val TAG = "OkHttpActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)

        val request = Request.Builder().url(url).build()

        okHttpClient.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful){
                    val srt = response.body?.string()
                    val txt = (JSONObject(srt).getJSONObject("value").get("joke")).toString()
                    Log.d(TAG, "response: $txt")
                }
            }
        })
    }
}