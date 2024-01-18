package com.example.quizapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quizdetails-table")
data class QuizdetailsEntity(
   @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
   val name:String = "",
   @ColumnInfo(name = "email-id")
    val email:String = ""
)
