package com.example.task.manager.controller;


import com.example.task.manager.dto.CountType;
import com.example.task.manager.entity.Task;
import com.example.task.manager.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    // get all tasks from the repository
    @GetMapping("/task")
    public List<Task> getTask(){
        return taskService.getAllTasks();
    }

    @GetMapping("/task/vData/percentagecounttype")
    public List<CountType> getPercentageByType(){
        return taskService.getPercentageGroupByType();
    }


    //save task
    @PostMapping("/task")
    public Task saveTask(@RequestBody Task task){
        return taskService.save(task);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(@RequestBody Task task, @PathVariable Long id){
        if(taskService.existById(id)){
            Task task_data = taskService.getTaskById(id).orElseThrow(()-> new EntityNotFoundException("Requested task does not exists"));
            task_data.setDescription(task.getDescription());
            task_data.setType(task.getType());
            task_data.setTitle(task.getTitle());
            task_data.setDueDate(task.getDueDate());
            taskService.save(task_data);
            return ResponseEntity.ok().body(task_data);
        }
        else{
            HashMap<String, String> message= new HashMap<>();
            message.put("message", id+ "task not found or matched");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }
    }

    //get task by ID
    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id).orElseThrow(()-> new EntityNotFoundException("Requested task does not exists"));
    }


    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        if(taskService.existById(id)){
           taskService.deleteTask(id);
            HashMap<String ,String> message = new HashMap<>();
            message.put("message", id+ "task removed");
            return ResponseEntity.ok().body(message);
        }
        else{
            HashMap<String ,String > message = new HashMap<>();
            message.put("message", +id +"task not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

        }
    }



}
