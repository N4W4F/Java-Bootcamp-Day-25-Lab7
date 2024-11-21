package com.example.learningmanagement.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Assignment {
    @NotEmpty(message = "Assignment ID cannot be empty")
    @Size(min = 1, max = 2, message = "Assignment ID must be 1 or 2 digits")
    private String id;

    @NotEmpty(message = "Assignment Title cannot be empty")
    private String title;

    @NotEmpty(message = "Assignment Description cannot be empty")
    private String description;

    @NotNull(message = "Assignment Due Date cannot be empty")
    @Future(message = "Assignment Due Date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dueDate;

    @AssertFalse(message = "Assignment isComplete must be initially false")
    private boolean isComplete;

    @NotEmpty(message = "Course ID for Assignment cannot be empty")
    private String courseId;
}
