package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat


class QuizQuestionActivity : AppCompatActivity(),View.OnClickListener {

    private var currentposition:Int =1
    private var questionList:ArrayList<Questions> ?= null
    private var SelectedOption:Int = 0
    private var Correctanswers:Int = 0
    private var username:String ?= null


    private var progressBar:ProgressBar ?= null
    private var questionview:TextView ?=null
    private var imgview:ImageView ?=null
    private var tvprogress:TextView ?=null

    private var optionone:TextView ?=null
    private var optionTwo:TextView ?=null
    private var optionThree:TextView ?=null
    private var optionFour:TextView ?=null

    private var button:Button ?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

supportActionBar?.hide()

        progressBar = findViewById(R.id.progressbarinfo)
        questionview =findViewById(R.id.questionView)
        imgview =findViewById(R.id.imgView)
        tvprogress =findViewById(R.id.progressbarMain)
        optionone =findViewById(R.id.optionOne)
        optionTwo =findViewById(R.id.optionTwo)
        optionThree =findViewById(R.id.OptionThree)
        optionFour =findViewById(R.id.OptionFour)
        button = findViewById(R.id.SubmitButton)

        optionone?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        button?.setOnClickListener(this)



        username = intent.getStringExtra(Constants.Username)
        questionList = Constants.getQuestions()


        setQuestion()

    }

    private fun setQuestion() {

        defaultoptionsview()
        val question: Questions = questionList!![currentposition - 1]
        imgview?.setImageResource(question.image)
        questionview?.text = question.questions
        progressBar?.progress = currentposition
        tvprogress?.text = "${currentposition}/${questionList?.size}"
        optionone?.text = question.optionone
        optionTwo?.text = question.optiontwo
        optionThree?.text = question.optionthree
        optionFour?.text = question.optionfour

        if (currentposition<= questionList?.size!!){

            button?.text = "SUBMIT"

        }

    }

    private fun defaultoptionsview(){


        val options = ArrayList<TextView>()
        optionone?.let {

            options.add(0,it)

        }
        optionTwo?.let {

            options.add(1,it)

        }
        optionThree?.let {

            options.add(2,it)

        }
        optionFour?.let {

            options.add(3,it)

        }
        for (option in options){

            option.isEnabled = true
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(

                this@QuizQuestionActivity,  R.drawable.background_bg )


        }



    }


    private fun selectedOptionView(tv:TextView, selectedOptionNum:Int){
            defaultoptionsview()
            SelectedOption = selectedOptionNum
            tv.setTextColor(Color.parseColor("#363a43"))
            tv.setTypeface(tv.typeface,Typeface.BOLD)
            tv.background = ContextCompat.getDrawable(

                this,  R.drawable.selected_option_coverage)

    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.optionOne ->
                optionone?.let {

                    selectedOptionView(it,1)
                }

            R.id.optionTwo ->
                optionTwo?.let {

                    selectedOptionView(it,2)
                }
            R.id.OptionThree ->
                optionThree?.let {

                    selectedOptionView(it,3)
                }
            R.id.OptionFour ->
                optionFour?.let {

                    selectedOptionView(it,4)
                }
            R.id.SubmitButton ->
               if (SelectedOption == 0){
                   currentposition++



                   when {
                       currentposition <= questionList!!.size ->{
                           setQuestion()
                       }
                       else->{

                          val intent = Intent(this@QuizQuestionActivity,ResultActivity::class.java)
                           intent.putExtra(Constants.Username,username)
                           intent.putExtra(Constants.TotalQuestions,questionList?.size)
                           intent.putExtra(Constants.CorrectAnswers,Correctanswers)
                           startActivity(intent)
                           finish()

                       }

                   }

               }
               else{
                   val question = questionList?.get(currentposition - 1)
                   if (question!!.correctanswer != SelectedOption){

                       answerview(SelectedOption,R.drawable.wronganswer_background_bg)
                   }else
                   {
                       Correctanswers++

                   }
                   answerview(question.correctanswer,R.drawable.correctanswer_background_bg)

                   if (currentposition == questionList!!.size){
                       button?.text ="FINISH"

                   }else
                   {
                       button?.text ="NEXT QUESTION"
                       optionone?.isEnabled = false
                       optionTwo?.isEnabled = false
                       optionThree?.isEnabled = false
                       optionFour?.isEnabled = false



                   }
                   SelectedOption = 0

               }






        }
    }

    private fun answerview(answer:Int,drawableView:Int){

        when(answer){

            1-> optionone?.background =  ContextCompat.getDrawable(

                this@QuizQuestionActivity,drawableView

            )
            2-> optionTwo?.background =  ContextCompat.getDrawable(

                this@QuizQuestionActivity,drawableView

            )
            3-> optionThree?.background =  ContextCompat.getDrawable(

                this@QuizQuestionActivity,drawableView

            )
            4-> optionFour?.background =  ContextCompat.getDrawable(

                this@QuizQuestionActivity,drawableView

            )




        }

    }

}