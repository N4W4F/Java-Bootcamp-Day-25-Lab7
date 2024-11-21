package com.example.learningmanagement.Controller;

import com.example.learningmanagement.ApiResponse.ApiResponse;
import com.example.learningmanagement.Model.Course;
import com.example.learningmanagement.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course has been added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity getCourses() {
        if (courseService.getCourses() == null)
            return ResponseEntity.status(400).body(new ApiResponse("There is no courses yet"));

        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity updateCourse(@PathVariable String courseId, @RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (courseService.updateCourse(courseId, course))
            return ResponseEntity.status(200).body(new ApiResponse("Course with ID " + courseId + " has been updated successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Course with ID " + courseId + " was not found"));
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable String courseId) {
        if (courseService.deleteCourse(courseId))
            return ResponseEntity.status(200).body(new ApiResponse("Course with ID " + courseId + " was deleted successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Course with ID " + courseId + " was not found"));
    }

    //--------------------------------------------------------------------------------

    @GetMapping("/by-teacher/{teacherId}")
    public ResponseEntity getCourseByTeacher(@PathVariable String teacherId) {
        if (courseService.getCoursesByTeacher(teacherId) == null)
            return ResponseEntity.status(400).body(new ApiResponse("Teacher with ID: " + teacherId + " has no courses yet"));

        return ResponseEntity.status(200).body(courseService.getCoursesByTeacher(teacherId));
    }

    @GetMapping("/by-classroom/{classroom}")
    public ResponseEntity getCoursesByClassroom(@PathVariable String classroom) {
        if (courseService.getCoursesByClassroom(classroom) == null)
            return ResponseEntity.status(400).body(new ApiResponse("Classroom with ID: " + classroom + " has no courses yet"));

        return ResponseEntity.status(200).body(courseService.getCoursesByClassroom(classroom));
    }

    @GetMapping("/by-title/{title}")
    public ResponseEntity getCourseByTitle(@PathVariable String title) {
        if (courseService.getCourseByTitle(title) == null)
            return ResponseEntity.status(400).body(new ApiResponse("There is no course with title " + title));

        return ResponseEntity.status(200).body(courseService.getCourseByTitle(title));
    }

    @PutMapping("/{courseId}/change-classroom/{newClassroom}")
    public ResponseEntity changeClassroom(@PathVariable String courseId, @PathVariable String newClassroom) {
        if (courseService.changeClassroom(courseId, newClassroom))
            return ResponseEntity.status(200).body(new ApiResponse("Classroom has been changed successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Course with ID: " + courseId + " was not found"));
    }

    @PutMapping("/{courseId}/change-teacher/{newTeacher}")
    public ResponseEntity changeTeacher(@PathVariable String courseId, @PathVariable String newTeacher) {
        if (courseService.changeTeacher(courseId, newTeacher))
            return ResponseEntity.status(200).body(new ApiResponse("Teacher has been changed successfully"));

        return ResponseEntity.status(400).body(new ApiResponse("Course not found"));
    }
}
