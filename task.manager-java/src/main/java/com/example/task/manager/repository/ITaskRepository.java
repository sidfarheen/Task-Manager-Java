package com.example.task.manager.repository;

import com.example.task.manager.dto.CountType;
import com.example.task.manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ITaskRepository  extends JpaRepository<Task,Long> {

    @Query(value="Select * from task order by due_date desc", nativeQuery = true)
    public List<Task> getAllTaskDueDateDesc();

    @Query(value= "Select new com.example.task.manager.dto.CountType(COUNT(*)/(select COUNT(*) from Task) * 100, type) from Task GROUP BY type")
    public List<CountType> getGroupByType();
}
