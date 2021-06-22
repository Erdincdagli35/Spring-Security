package com.ed.springsecurity.controller;

import com.ed.springsecurity.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final List<Student> students = Arrays.asList(
            new Student(1, "Leonardo Di Caprio"),
            new Student(2, "Kate Winslet"),
            new Student(3, "Johnny Deep")
    );

    @GetMapping("{id}")
    public Student getStudent(@PathVariable("id") Integer id) {
        return students.stream()
                .filter(student -> id.equals(student.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + id + " does not exists"));
    }
}