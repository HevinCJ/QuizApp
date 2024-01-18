package com.example.quizapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.ItemsrowBinding

class ItemsAdapter(private val items:ArrayList<QuizdetailsEntity>,
private val updatelistener:(id:Int) ->Unit,
private val deletelistener:(id: Int) ->Unit
):RecyclerView.Adapter<ItemsAdapter.Viewholder>(){


    class Viewholder(binding: ItemsrowBinding):RecyclerView.ViewHolder(binding.root){
        val layoutmain = binding.recyclelayouthistory
        val idmain = binding.tvid
        val namemain = binding.textviewname
        val emailmain = binding.textviewemail
        val imageviewedit = binding.imgviewedit
        val deleteview = binding.imgviewdelete

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(ItemsrowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val context = holder.itemView.context
        val item = items[position]

        holder.namemain.text =item.name
        holder.emailmain.text = item.email
        holder.idmain.text = item.id.toString()

        holder.imageviewedit.setOnClickListener {
            updatelistener.invoke(item.id)
        }
        holder.deleteview.setOnClickListener {
            deletelistener.invoke(item.id)
        }

        if (position%2 == 0){
            holder.layoutmain.setBackgroundColor(ContextCompat.getColor(context,R.color.white))
        }else{
            holder.layoutmain.setBackgroundColor(ContextCompat.getColor(context,R.color.colorgrey))
        }
    }



}