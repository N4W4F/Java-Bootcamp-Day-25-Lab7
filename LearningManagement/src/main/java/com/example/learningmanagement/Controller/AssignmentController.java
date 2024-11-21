package com.example.learningmanagement.Controller;

import com.example.learningmanagement.ApiResponse.ApiResponse;
import com.example.learningmanagement.Model.Assignment;
import com.example.learningmanagement.Service.AssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("/add")
    public ResponseEntity addAssignment(@RequestBody @Valid Assignment assignment, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        assignmentService.addAssignment(assignment);
        return ResponseEntity.status(200).body(new ApiResponse("Assignment has been added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity getAllAssignments() {
        if (assignmentService.getAssignments() == null)
            return ResponseEntity.status(404).body(new ApiResponse("There is no assignments yet"));

        return ResponseEntity.status(200).body(assignmentService.getAssignments());
    }

    @PutMapping("/update/{assignmentId}")
    public ResponseEntity updateAssignment(@PathVariable String assignmentId, @RequestBody @Valid Assignment assignment, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        if (assignmentService.updateAssignment(assignmentId, assignment))
            return ResponseEntity.status(200).body(new ApiResponse("Assignment with ID: " + assignmentId + " has been updated successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("Assignment with ID: " + assignmentId + " was not found"));
    }

    @DeleteMapping("/delete/{assignmentId}")
    public ResponseEntity deleteAssignment(@PathVariable String assignmentId) {
        if (assignmentService.deleteAssignment(assignmentId))
            return ResponseEntity.status(200).body(new ApiResponse("Assignment with ID: " + assignmentId + " has been deleted successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("Assignment with ID: " + assignmentId + " was not found"));
    }

    //--------------------------------------------------------------------------------

    @GetMapping("/by-course/{courseId}")
    public ResponseEntity getAssignmentByCourse(@PathVariable String courseId) {
        if (assignmentService.getAssignmentByCourse(courseId) == null)
            return ResponseEntity.status(404).body(new ApiResponse("Assignment with ID: " + courseId + " was not found"));

        return ResponseEntity.status(200).body(assignmentService.getAssignmentByCourse(courseId));
    }

    @GetMapping("/completed")
    public ResponseEntity getCompletedAssignments() {
        if (assignmentService.getCompletedAssignments() == null)
            return ResponseEntity.status(404).body(new ApiResponse("There is no completed assignments yet"));

        return ResponseEntity.status(200).body(assignmentService.getCompletedAssignments());
    }

    @GetMapping("/pending")
    public ResponseEntity getPendingAssignments() {
        if (assignmentService.getPendingAssignments() == null)
            return ResponseEntity.status(404).body(new ApiResponse("All assignments are completed"));

        return ResponseEntity.status(200).body(assignmentService.getPendingAssignments());
    }

    @PutMapping("/mark-as-completed/{assignmentId}")
    public ResponseEntity markAsCompleted(@PathVariable String assignmentId) {
        if (assignmentService.markAsCompleted(assignmentId))
            return ResponseEntity.status(200).body(new ApiResponse("Assignment with ID: " + assignmentId + " has been completed successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("Assignment with ID: " + assignmentId + " was not found"));
    }

    @PutMapping("{assignmentId}/change-course/{newCourseId}")
    public ResponseEntity changeCourse(@PathVariable String assignmentId, @PathVariable String newCourseId) {
        if (assignmentService.changeCourse(assignmentId, newCourseId))
            return ResponseEntity.status(200).body(new ApiResponse("Course assignment has been changed successfully"));

        return ResponseEntity.status(404).body(new ApiResponse("Assignment with ID: " + assignmentId + " was not found"));
    }

}
