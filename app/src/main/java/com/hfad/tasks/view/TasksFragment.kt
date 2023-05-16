package com.hfad.tasks.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hfad.tasks.databinding.FragmentTasksBinding
import com.hfad.tasks.model.TaskDatabase
import com.hfad.tasks.recycler.TaskItemAdapter
import com.hfad.tasks.viewmodel.TasksViewModel
import com.hfad.tasks.viewmodel.TasksViewModelFactory


class TasksFragment : Fragment() {
    /*Объявил _binding и свойство binding.
    * Получаем доступ к _binding, используя свойство привязки.
    *  Если _binding не равен нулю, он возвращает _binding,
    * если _binding равен нулю, попытка его использования вызывает null pointer exception*/
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root/*12)получение объекта TaskDao и создание TasksViewModelFactory.
        Затем код передает фабрику поставщику модели представления,
        который использует ее для получения экземпляра TasksViewModel.*/
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstence(application).taskDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(TasksViewModel::class.java)
        binding.viewModel = viewModel
        //15)Макет реагирует на текущие обновления данных (data binding)
        binding.lifecycleOwner = viewLifecycleOwner

        //recyclerView
        val adapter = TaskItemAdapter {
            //17.2) вызываем метод для вывода тоста
                taskId ->
            viewModel.onTaskClicked(taskId)
        }
        binding.taskList.adapter = adapter

        //livedata. Наблюдаем view model's tasks property, передаем данные адаптеру
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer { taskId ->
            taskId?.let {
                val action = TasksFragmentDirections.actionTasksFragmentToEditTaskFragment(taskId)
                this.findNavController().navigate(action)
                viewModel.onTaskNavigated()
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
