package com.example.quizapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.databinding.ActivityQuizHistoryBinding
import com.example.quizapp.databinding.CustomDialogueforupdateBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class quiz_history : AppCompatActivity() {
    private var binding:ActivityQuizHistoryBinding ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarquizhistory)
        supportActionBar?.title = "HISTORY"

        if (supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarquizhistory?.setNavigationOnClickListener{
            onBackPressed()
        }
        val quizdetailsdao = (application as QuizApplication).db.quizdetailsDao()


        lifecycleScope.launch{
         quizdetailsdao.fetchalldetails().collect{
             val list = ArrayList(it)
             setuprecyclerview(list,quizdetailsdao)
         }
        }


    }


    private fun setuprecyclerview( quizlist:ArrayList<QuizdetailsEntity>,Quizdao:QuizdetailsDao){
        if (quizlist.isNotEmpty()){
            val itemsAdapter = ItemsAdapter(quizlist,{
                updateid->updaterecord(updateid,Quizdao)
            }){ deleteId->
                            deleterecord(deleteId,Quizdao)
            }


            binding?.rcviewquizhistory?.layoutManager = LinearLayoutManager(this)
            binding?.rcviewquizhistory?.adapter = itemsAdapter
            binding?.txtviewnorecordsavailable?.visibility = View.INVISIBLE
            binding?.rcviewquizhistory?.visibility = View.VISIBLE
        }else{
            binding?.txtviewnorecordsavailable?.visibility = View.VISIBLE
            binding?.rcviewquizhistory?.visibility = View.INVISIBLE

        }
    }
    private fun updaterecord(id:Int,Quizdao: QuizdetailsDao){
        val customdialogue = Dialog(this)
        customdialogue.setCanceledOnTouchOutside(false)


        val binding = CustomDialogueforupdateBinding.inflate(layoutInflater)
        customdialogue.setContentView(binding.root)

        lifecycleScope.launch{
            Quizdao.fetchalldetailsById(id).collect{
                binding.etupdatename.setText(it.name)
                binding.etemaildid.setText(it.email)
            }
        }


        binding.btnupdate.setOnClickListener{

            val name = binding.etupdatename.text.toString()
            val emailid = binding.etemaildid.text.toString()

            lifecycleScope.launch{
                if (name.isNotEmpty()&&emailid.isNotEmpty()){
                    Quizdao.update(QuizdetailsEntity(id, name,emailid))
                    Toast.makeText(applicationContext, "RECORD UPDATED", Toast.LENGTH_LONG).show()
                    customdialogue.dismiss()
                }else{
                    Toast.makeText(applicationContext, "INPUT CANNOT BE BLANK", Toast.LENGTH_LONG).show()
                }
            }

        }
        binding.btncancel.setOnClickListener {
            customdialogue.dismiss()
        }
        customdialogue.show()
    }

    private fun deleterecord(id: Int,quizdao:QuizdetailsDao){
        val builder =AlertDialog.Builder(this,R.style.Theme_Dialogue)
        builder.setTitle("Delete Record?")
        builder.setMessage("Do you want to delete record")


        builder.setPositiveButton("Yes"){
            dialogueinterface,_ ->
            lifecycleScope.launch{
                quizdao.delete(QuizdetailsEntity(id))
                Toast.makeText(applicationContext, "RECORD DELETED", Toast.LENGTH_LONG).show()
            }
            dialogueinterface.dismiss()
        }

        builder.setNegativeButton("No"){
            dialogueinterface,_ ->
            dialogueinterface.dismiss()
        }
        val alertDialog:AlertDialog = builder.create()
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.show()
    }




}