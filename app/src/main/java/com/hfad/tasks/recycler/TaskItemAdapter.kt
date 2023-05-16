package com.hfad.tasks.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.tasks.databinding.TaskItemBinding
import com.hfad.tasks.diff_util.TaskDiffItemCallback
import com.hfad.tasks.model.Task

//17.1) TaskItemAdapter принимает лямбду
class TaskItemAdapter(val clickListener: (taskId: Long) -> Unit) :
    ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

    //onCreateViewHolder вызывается, когда необходимо создать viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder =
        TaskItemViewHolder.inflateFrom(parent)

    //onBindViewHolder вызывается всякий раз, когда в recycler view требуется отобразить данные.
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener) //17.1) передаю лямбду в метод bind ()
    }

    class TaskItemViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {


        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskItemViewHolder(binding)
            }
        }

        // добавление данных в макет view holder
        //17.1) метод может принимать лямбду
        fun bind(item: Task, clickListener: (taskId: Long) -> Unit) {
            binding.task = item
            //17.1) значения становятся кликабельными, лямбда выполняется при нажатии
            binding.root.setOnClickListener{clickListener(item.taskId)}
        }
    }

}