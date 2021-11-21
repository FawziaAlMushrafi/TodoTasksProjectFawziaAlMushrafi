package com.yameen.todotasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.yameen.todotasks.databinding.FragmentShowBinding


class ShowFragment : Fragment() {

    private lateinit var _binding: FragmentShowBinding
    private val binding get() = _binding
    private var mtask:task_model? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (requireArguments().containsKey("task")){

            mtask = arguments?.getParcelable<task_model>("task")!!
        }

        setui()

        binding.delete.setOnClickListener {

            val db= Sqliteopenhelper(requireActivity())

           val success = db.deletetask(mtask!!)
            if (success >= 0){
                Toast.makeText(requireActivity(),"task deleted successfully..",Toast.LENGTH_LONG).show()

                 activity?.onBackPressed()

            }

        }
        binding.update.setOnClickListener {

            val sql = Sqliteopenhelper(requireActivity())

            val title = binding.title.text.toString()
            val task = binding.task.text.toString()
            val model = task_model(mtask!!._id,"",title,task)

            val success = sql.updatetask(model)
            if (success >= 0){

                Toast.makeText(requireActivity(),"task updated successfully..",Toast.LENGTH_LONG).show()
            }
        }
        return root
    }



    private fun setui(){

        if (mtask != null){

            binding.task.setText(mtask!!.task)
            binding.title.setText(mtask!!.title)
        }

    }
}