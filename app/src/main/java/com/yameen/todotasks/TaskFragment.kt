package com.yameen.todotasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yameen.todotasks.databinding.FragmentMyTasksBinding
import com.yameen.todotasks.databinding.FragmentTaskBinding


class TaskFragment : Fragment() {
    private lateinit var _binding: FragmentTaskBinding
    private val binding get() = _binding!!
    lateinit var list:ArrayList<task_model>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setui ()

        return root

    }

    private fun setui (){


        val db =  Sqliteopenhelper(requireActivity())
        list = db.getalltaskelist()
           //0==isEmpty  ---- if list size = 0 so it is empty
        if (list.size > 0) {

            //make textview (no task found) gone if list is not empty...and make recycler visible..
            binding.notasks.visibility = View.GONE
            binding.recycle.visibility = View.VISIBLE
            val adapter = mytaskAdapter(this, list)

            binding.recycle.layoutManager = LinearLayoutManager(requireActivity())
            binding.recycle.adapter = adapter
        }else{

            binding.notasks.visibility = View.VISIBLE
            binding.recycle.visibility = View.GONE
        }
    }

}