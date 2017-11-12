package com.wordpress.carledwinj.checklistApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wordpress.carledwinj.checklistApp.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
