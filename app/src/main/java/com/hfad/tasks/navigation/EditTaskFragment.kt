package com.hfad.tasks.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hfad.tasks.R

class EditTaskFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_task, container, false)
        val textView = view.findViewById<TextView>(R.id.task_id)
        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId
        textView.text = taskId.toString()
        return view
    }

}