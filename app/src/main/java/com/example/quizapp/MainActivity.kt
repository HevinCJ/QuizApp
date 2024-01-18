package com.example.quizapp

import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.quizapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()

        val quizdetailsdao = (application as QuizApplication).db.quizdetailsDao()



        binding?.buttonStart?.setOnClickListener {

            if (binding?.EditTextName?.text!!.isEmpty() && binding?.EditTextemail?.text!!.isEmpty()) {

                Toast.makeText(this, "PLEASE ENTER THE DETAILS", Toast.LENGTH_LONG).show()

            } else {
                addrecord(quizdetailsdao)
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.Username, binding?.EditTextName?.text.toString())
                startActivity(intent)
                finish()


            }


        }

        binding?.buttonshowhistory?.setOnClickListener {
            val intent = Intent(this, quiz_history::class.java)
            startActivity(intent)
        }

    }

    private fun addrecord(quidetailsdao: QuizdetailsDao) {
        val name = binding?.EditTextName?.text.toString()
        val emailid = binding?.EditTextemail?.text.toString()

        lifecycleScope.launch {
            quidetailsdao.insert(QuizdetailsEntity(name = name, email = emailid))
            Toast.makeText(applicationContext, "RECORD SAVED", Toast.LENGTH_LONG).show()
            binding?.EditTextName?.text?.clear()
            binding?.EditTextemail?.text?.clear()

        }
    }
}
