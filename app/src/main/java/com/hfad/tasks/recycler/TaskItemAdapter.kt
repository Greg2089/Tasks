package com.hfad.tasks.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.tasks.R
import com.hfad.tasks.diff_util.TaskDiffItemCallback
import com.hfad.tasks.model.Task

class TaskItemAdapter : ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()){

    //onCreateViewHolder вызывается, когда необходимо создать viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder =
        TaskItemViewHolder.inflateFrom(parent)

    //onBindViewHolder вызывается всякий раз, когда в recycler view требуется отобразить данные.
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TaskItemViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {
        //ссылки на TextView и CheckBox
        val taskName = rootView.findViewById<TextView>(R.id.task_name)
        val taskDone = rootView.findViewById<CheckBox>(R.id.task_done)

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.task_item, parent, false) as CardView
                return TaskItemViewHolder(view)
            }
        }

        // добавление данных в макет view holder
        fun bind(item: Task) {
            taskName.text = item.taskName
            taskDone.isChecked = item.taskDone
        }
    }

}