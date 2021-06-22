package com.ed.springsecurity.controller;

import com.ed.springsecurity.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students/management")
public class StudentManagementController {

    private static final List<Student> students = Arrays.asList(
            new Student(1, "Leonardo Di Caprio"),
            new Student(2, "Kate Winslet"),
            new Student(3, "Johnny Deep")
    );

    @GetMapping
    public List<Student> getAllStudent() {
        return students;
    }

    @PostMapping
    public void registerAStudent(@RequestBody Student student){
        System.out.println(student);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
        System.out.println(id);
    }

    @PutMapping("{id}")
    public void edit(@PathVariable Integer id,@RequestBody Student student){
        System.out.println(String.format("%s %s",student,student));
    }
}
