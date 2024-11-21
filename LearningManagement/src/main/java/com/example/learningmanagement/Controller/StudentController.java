package com.example.learningmanagement.Controller;

import com.example.learningmanagement.ApiResponse.ApiResponse;
import com.example.learningmanagement.Model.Student;
import com.example.learningmanagement.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student has been added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity getStudents() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (studentService.updateStudent(id, student))
            return ResponseEntity.status(200).body(new ApiResponse("Student with ID: " + id + " has been updated successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("Student with ID: " + id + " was not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        if (studentService.deleteStudent(id))
            return ResponseEntity.status(200).body(new ApiResponse("Student with ID: " + id + " has been deleted successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("Student with ID: " + id + " was not found"));
    }

    //--------------------------------------------------------------------------------

    @GetMapping("/{id}/courses")
    public ResponseEntity getStudentCourses(@PathVariable String id) {
        if (studentService.getStudentCourses(id) == null)
            return ResponseEntity.status(400).body(new ApiResponse("Student with ID: " + id + " was not found"));

        return ResponseEntity.status(200).body(studentService.getStudentCourses(id));
    }

    @GetMapping("/by-course/{course}")
    public ResponseEntity getStudentsByCourse(@PathVariable String course) {
        if (studentService.getStudentsByCourse(course) == null)
            return ResponseEntity.status(400).body(new ApiResponse("There are no students enrolled in this course"));

        return ResponseEntity.status(200).body(studentService.getStudentsByCourse(course));
    }

    @PutMapping("/{studentId}/change-course/{course}")
    public ResponseEntity changeStudentCourse(@PathVariable String studentId, @PathVariable String course) {
        if (studentService.changeStudentCourse(studentId, course))
            return ResponseEntity.status(200).body(new ApiResponse("Course for student: " + studentId + " has been changed successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Student with ID: " + studentId + " was not found"));
    }
}
