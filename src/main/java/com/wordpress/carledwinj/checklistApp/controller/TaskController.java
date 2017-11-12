package com.wordpress.carledwinj.checklistApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordpress.carledwinj.checklistApp.model.Task;
import com.wordpress.carledwinj.checklistApp.repository.TaskRepository;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class TaskController {

	@Autowired
	TaskRepository repository;
	
	@GetMapping("/tasks")
	public List<Task> findAllTasks(){
		
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		
		return repository.findAll(sortByCreatedAtDesc);
	}
	
	@PostMapping("/task")
	public Task create(@Valid @RequestBody Task task) {
		
		task.setDone(false);
		
		return repository.save(task);
	}
	
	@GetMapping("/task/{id}")
	public ResponseEntity<Task> findTask(@PathVariable("id") Integer id){
		
		Task task = repository.findOne(id);
		
		if(task == null) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Task>(task, HttpStatus.OK);
		}
	}
	
	@PutMapping("/task/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable("id") Integer id, @Valid @RequestBody Task task){
		
		Task taskData = repository.findOne(id);
		
		if(taskData == null) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		taskData.setTitle(task.getTitle());
		taskData.setDone(task.getDone());
		
		taskData = repository.save(taskData);
		
		return new ResponseEntity<Task>(taskData, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/task/{id}")
	public ResponseEntity<Task> deleteTask(@PathVariable("id") Integer id){
		
		repository.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
