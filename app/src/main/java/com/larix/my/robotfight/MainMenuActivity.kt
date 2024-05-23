package com.larix.my.robotfight

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {
    var yourId: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)

        btn1.setOnClickListener {
            yourId = 1
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("yourId", yourId.toString())
            startActivity(intent)
        }
        btn2.setOnClickListener {
            yourId = 2
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("yourId", yourId.toString())
            startActivity(intent)
        }
        btn3.setOnClickListener {
            yourId = 3
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("yourId", yourId.toString())
            startActivity(intent)
        }
        btn4.setOnClickListener {
            yourId = 4
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("yourId", yourId.toString())
            startActivity(intent)
        }
    }
}