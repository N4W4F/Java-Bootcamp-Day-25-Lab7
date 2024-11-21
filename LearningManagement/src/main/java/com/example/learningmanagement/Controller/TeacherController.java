package com.example.learningmanagement.Controller;

import com.example.learningmanagement.ApiResponse.ApiResponse;
import com.example.learningmanagement.Model.Teacher;
import com.example.learningmanagement.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher has been added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity getTeachers() {
        if (teacherService.getTeachers() == null)
            return ResponseEntity.status(404).body(new ApiResponse("There is no teachers yet"));
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable String id, @RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (teacherService.updateTeacher(id, teacher))
            return ResponseEntity.status(200).body(new ApiResponse("Teacher has been updated successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("Teacher with ID: " + id + " was not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id) {
        if (teacherService.deleteTeacher(id))
            return ResponseEntity.status(200).body(new ApiResponse("Teacher with ID: " + id + " has been deleted successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("Teacher with ID: " + id + " was not found"));
    }

    //--------------------------------------------------------------------------------

    @GetMapping("/by-course/{course}")
    public ResponseEntity getTeachersByCourse(@PathVariable String course) {
        if (teacherService.getTeachersByCourse(course) == null)
            return ResponseEntity.status(404).body(new ApiResponse("No teachers found for course: " + course));

        return ResponseEntity.status(200).body(teacherService.getTeachersByCourse(course));
    }

    @PutMapping("/assign/{teacherId}/to-course/{course}")
    public ResponseEntity assignTeacherToCourse(@PathVariable String teacherId, @PathVariable String course) {
        if (teacherService.assignTeacherToCourse(teacherId, course))
            return ResponseEntity.status(200).body(new ApiResponse("Teacher with ID: " + teacherId + " has been assigned successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Teacher with ID: " + teacherId + " was not found"));
    }

    @PutMapping("/remove-course-from/{teacherId}")
    public ResponseEntity removeCourseFromTeacher(@PathVariable String teacherId) {
        if (teacherService.removeCourseFromTeacher(teacherId))
            return ResponseEntity.status(200).body(new ApiResponse("Course has been remove from Teacher with ID: " + teacherId));

        return ResponseEntity.status(404).body(new ApiResponse("Teacher with ID: " + teacherId + " was not found"));
    }
}
