package com.larix.my.robotfight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainMenuActivity : AppCompatActivity() {
    var yourId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val id = findViewById<EditText>(R.id.yourId)
        val btn = findViewById<Button>(R.id.postBtn)

        btn.setOnClickListener {
            yourId = id.text.toString().toInt()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}