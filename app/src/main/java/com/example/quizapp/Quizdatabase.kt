package com.example.quizapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[QuizdetailsEntity::class], version = 1)
 abstract  class Quizdatabase:RoomDatabase() {

  abstract fun quizdetailsDao():QuizdetailsDao

  companion object{
   @Volatile
   private var INSTANCE:Quizdatabase ?=null

   fun getinstance(context:Context):Quizdatabase{

    synchronized(this){

     var instance = INSTANCE

     if (instance==null){
      instance = Room.databaseBuilder(context.applicationContext,Quizdatabase::class.java,"quiz-database").fallbackToDestructiveMigration().build()
      INSTANCE = instance
     }
     return instance
    }
   }


  }


}