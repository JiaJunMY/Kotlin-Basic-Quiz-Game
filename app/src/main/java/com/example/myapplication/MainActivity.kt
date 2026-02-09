package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var btnStart = findViewById<Button>(R.id.btnJoin)

        var easyDifficulty = findViewById<RadioButton>(R.id.rdoEasy)
        var hardDifficulty = findViewById<RadioButton>(R.id.rdoHard)

        var seconds10time = findViewById<RadioButton>(R.id.rdo10sec)
        var seconds7time = findViewById<RadioButton>(R.id.rdo7sec)
        var seconds5time = findViewById<RadioButton>(R.id.rdo5sec)

        btnStart.setOnClickListener {
            val quizPage = Intent(this, Quiz_Page::class.java)

            if(easyDifficulty.isChecked)
            {
                quizPage.putExtra("Difficulty", "0.5")
            }
            else if(hardDifficulty.isChecked)
            {
                quizPage.putExtra("Difficulty", "2")
            }

            if(seconds10time.isChecked)
            {
                quizPage.putExtra("Time", "10")
            }
            else if(seconds7time.isChecked)
            {
                quizPage.putExtra("Time", "7")
            }
            else
            {
                quizPage.putExtra("Time", "5")
            }

            if(easyDifficulty.isChecked)
            {
                quizPage.putExtra("Lives", "3")
            }
            else if(hardDifficulty.isChecked)
            {
                quizPage.putExtra("Lives", "1")
            }

            startActivity(quizPage)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}