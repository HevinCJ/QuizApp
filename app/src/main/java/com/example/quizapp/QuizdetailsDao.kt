package com.example.quizapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow



@Dao()
interface QuizdetailsDao {



    @Insert
    suspend fun insert(quizdetails: QuizdetailsEntity)

    @Update
    suspend fun update(quizdetails:  QuizdetailsEntity)

    @Delete
      suspend fun delete(quizdetails:  QuizdetailsEntity)

    @Query("Select * from `quizdetails-table`")
    fun fetchalldetails():Flow<List<QuizdetailsEntity>>

    @Query("Select * from `quizdetails-table` where id=:id")
    fun fetchalldetailsById(id:Int):Flow<QuizdetailsEntity>


}