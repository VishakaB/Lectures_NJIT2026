package com.example.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class RequestHandler {
    
//create a object to store task data
static Task task = new Task(1, "Cleaning", "Weekends");
//create an arraylist to store each task data
static List<Task> tasklist = new ArrayList<>();


@GetMapping("/server-check")
public String ServerCheck(){
    return "all okay";
}

@GetMapping("/task-check")
public Task TaskCheck(){
    return task;
}

@GetMapping("/task-check/id")
public int TaskCheckID(){
    return task.getId();
}


/* @GetMapping("/task-check/task-name")
public String TaskCheckTaskName(){
    return task.getTaskName();
}

@GetMapping("/task-check/task-description")
public String TaskCheckTaskDescription(){
    return task.getTaskDescription();
} */

//use @PathVariable annotation to change the end point data using a variable, 
//this is valid for for variablels of same data type
@GetMapping("/task-check/{tasknamedescription}")
public String getTaskname_description(@PathVariable String tasknamedescription){
    if (tasknamedescription.equalsIgnoreCase("task-name")){
        return task.getTaskName();
    }
    if (tasknamedescription.equalsIgnoreCase("task-description")){
        return task.getTaskDescription();
    }
    
    return "null";
}

@GetMapping("/task-check/task-list")
public List<Task> TaskListCheck(){
    return tasklist;
}

@PostMapping("/task-post")
public String TaskPost(@RequestBody Task newTask){
    Task t = new Task();
    t.setId(newTask.getId());
    t.setTaskName(newTask.getTaskName());
    t.setTaskDescription(newTask.getTaskDescription());
    tasklist.add(t);
    return "Task added successfully";
}

@DeleteMapping("/task-delete/{taskid}")
public String deleteTask(@PathVariable int taskid){
    try{
        tasklist.remove(taskid);
        return "Task removed";
    } finally {
        System.out.println("error");
    }
}

}
