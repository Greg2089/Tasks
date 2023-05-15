package com.hfad.tasks.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.hfad.tasks.model.Task

class TaskDiffItemCallback:DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean = (oldItem.taskId == newItem.taskId)

    override fun areContentsTheSame(oldItem: Task, newItem: Task)=(oldItem == newItem)

}