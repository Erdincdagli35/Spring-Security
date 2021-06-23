package com.ed.springsecurity.controller;

import com.ed.springsecurity.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StudentManagementController {

    private static final List<Student> students = Arrays.asList(
            new Student(1, "Leonardo Di Caprio"),
            new Student(2, "Kate Winslet"),
            new Student(3, "Johnny Deep")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<Student> getAll() {
        return students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void create(@RequestBody Student student) {
        System.out.println(student);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void delete(@PathVariable("id") Integer id) {
        System.out.println(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void edit(@PathVariable Integer id, @RequestBody Student student) {
        System.out.println(String.format("%s %s", student, student));
    }
}
