package com.hfad.tasks.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.tasks.R
import com.hfad.tasks.model.Task

class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {
    //region Для данных recycler view
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    //endregion

    override fun getItemCount() = data.size //количество элементов

    //onCreateViewHolder вызывается, когда необходимо создать viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder =
        TaskItemViewHolder.inflateFrom(parent)

    //onBindViewHolder вызывается всякий раз, когда в recycler view требуется отобразить данные.
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class TaskItemViewHolder(val rootView: TextView) : RecyclerView.ViewHolder(rootView) {
        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.task_item, parent, false) as TextView
                return TaskItemViewHolder(view)
            }
        }

        fun bind(item: Task) {
            rootView.text = item.taskName
        }
    }

}