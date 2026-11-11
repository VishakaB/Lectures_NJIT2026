package com.example.springbootapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//annotation used to handle requests 
@RestController 
@CrossOrigin 
public class RequestHandler {

    //create task data
    static Task task = new Task(1, "Cleaning", "Weekends");
    //create a list of tasks
    static List<Task> tasklist = new ArrayList<>();

    //set a GET request handler that works under /server-check endpoint
    @GetMapping("/server-check")
    //define a method to 
    public String serverCheck(){
        return "server okay now";
    }

    @GetMapping("/task-check")
    //define a method to 
    public Task taskCheck(){
        return task;
    }

    @GetMapping("/task-check/tasklist")
    //define a method to 
    public List<Task> tasklist(){
        return tasklist;
    }

    //handle POST Requests
    @PostMapping("/task-post")
    //define the method
    //get data from clients using the @RequestBody
    //save that data as a Task object called newTask
    public String taskPost(@RequestBody Task newtTask){
        task.setId(newtTask.getId());
        task.setTaskName(newtTask.getTaskName());
        task.setTaskDescription(newtTask.getTaskDescription());
        tasklist.add(task);
        return "task successfully added!";
    }




    
}
