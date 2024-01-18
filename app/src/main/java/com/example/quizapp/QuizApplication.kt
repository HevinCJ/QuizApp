package com.example.quizapp

import android.app.Application

class QuizApplication:Application() {
    val db by lazy {
        Quizdatabase.getinstance(this)
    }
}