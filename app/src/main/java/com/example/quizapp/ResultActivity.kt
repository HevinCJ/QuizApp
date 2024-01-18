package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private var finishbutton:Button ?= null
    private var usernameview:TextView ?=null
    private var scoreview:TextView ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()


        finishbutton = findViewById(R.id.btn_finish)
        usernameview = findViewById(R.id.tv_name)
        scoreview = findViewById(R.id.tv_score)

        val username = intent.getStringExtra(Constants.Username)
        usernameview?.text = username

        val totalScore = intent.getIntExtra(Constants.TotalQuestions,0)
        val correctAns = intent.getIntExtra(Constants.CorrectAnswers,0)

        scoreview?.text ="Your Score is ${correctAns} out of ${totalScore}"
        finishbutton?.setOnClickListener{

            startActivity(Intent(this@ResultActivity,MainActivity::class.java))
            finish()


        }



    }
}