package com.larix.my.robotfight

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.larix.my.robotfight.databinding.ActivityMainBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arg = intent.extras
        val id = arg?.get("yourId")
        val url = ""

        binding.stream.loadUrl("https://robot-fight.ru/video/")
        binding.stream.setInitialScale(150)



        binding.btnW.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {

                    }

                    MotionEvent.ACTION_UP -> {

                    }

                }

                return v?.onTouchEvent(event) ?: true
            }
        })


    }
}