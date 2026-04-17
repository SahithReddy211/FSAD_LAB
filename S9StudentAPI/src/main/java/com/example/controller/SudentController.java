package com.example.controller;

import org.springframework.web.bind.annotation.*;
import com.example.exception.InvalidInputException;
import com.example.model.Student;
import com.example.service.StudentService;

@RestController
@RequestMapping("/student")
public class SudentController {

    private StudentService service;

    public SudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {

        if (!id.matches("\\d+")) {
            throw new InvalidInputException("Student ID must be numeric");
        }

        int studentId = Integer.parseInt(id);
        return service.getStudentById(studentId);
    }
}

