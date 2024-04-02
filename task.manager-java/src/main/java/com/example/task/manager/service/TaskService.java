package com.example.task.manager.service;

import com.example.task.manager.dto.CountType;
import com.example.task.manager.repository.ITaskRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.task.manager.entity.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private ITaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> getAllTasks(){
        return taskRepository.getAllTaskDueDateDesc();
    }


    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public boolean existById(Long id) {
        return taskRepository.existsById(id);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<CountType> getPercentageGroupByType(){
        return taskRepository.getGroupByType();
    }
}
