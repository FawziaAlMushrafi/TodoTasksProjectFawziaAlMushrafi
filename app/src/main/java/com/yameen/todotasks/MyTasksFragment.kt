package com.yameen.todotasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.yameen.todotasks.databinding.FragmentMyTasksBinding
import java.util.*


class MyTasksFragment : Fragment() {

    private lateinit var _binding: FragmentMyTasksBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyTasksBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.add.setOnClickListener {
            addtask()
        }

        binding.showtasks.setOnClickListener {
            val eventfr: Fragment = TaskFragment()

            val fragmentmanegar = activity?.supportFragmentManager?.beginTransaction()
            fragmentmanegar!!.replace(R.id.fragment_container, eventfr).addToBackStack("2")
            .commit()
        }

        return root

    }


    private fun addtask(){



        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)

        val date = "${year}/${month}/${day}"

            var title = binding.title.text.toString()
            var task = binding.task.text.toString()

        if(title.isEmpty() || task.isEmpty()){

            Toast.makeText(requireActivity(),"please fill title and task place..",Toast.LENGTH_LONG).show()
             return
        }

            val databasehandler = Sqliteopenhelper(requireActivity())


            val success = databasehandler.adddate(date,title,task)

        if(success >= 0) {
            Toast.makeText(requireActivity(), "task added successfully", Toast.LENGTH_LONG).show()

            title = ""
            task = ""
            binding.title.setText("")
            binding.task.setText("")

        }else{
            Toast.makeText(requireActivity(), "task Not added", Toast.LENGTH_LONG).show()
        }



    }

}