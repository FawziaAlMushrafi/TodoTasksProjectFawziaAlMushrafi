package com.yameen.todotasks


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.RecyclerView
import com.yameen.todotasks.databinding.MytaskItemBinding

class mytaskAdapter (val context: TaskFragment,  val tasks:ArrayList<task_model>):
    RecyclerView.Adapter<mytaskAdapter.viewholder>() {


    inner class viewholder(val binding: MytaskItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:task_model, position: Int){

            binding.date.text = item.date
            binding.title.text = item.title
            binding.task.text = item.task

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(MytaskItemBinding.inflate(LayoutInflater.from(context.context),parent,false))

    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        holder.bind(tasks[position],position)

        holder.itemView.setOnClickListener {


            val args = Bundle()
            args.putParcelable("task", tasks[position])
            val eventfr: Fragment = ShowFragment()
            eventfr.arguments = args
            val fragmentmanegar = context.activity?.supportFragmentManager?.beginTransaction()
            fragmentmanegar!!.replace(R.id.fragment_container, eventfr).addToBackStack("2")
                .commit()


        }
    }
 // pass list size to adapter..
    override fun getItemCount(): Int {
        return tasks.size
    }
}