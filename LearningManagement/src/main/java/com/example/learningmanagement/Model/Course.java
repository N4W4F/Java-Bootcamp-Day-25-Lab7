package com.example.learningmanagement.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "Course ID cannot be empty")
    @Size(min = 3, max = 3, message = "Course ID must be exactly 3 numbers")
    private String id;

    @NotEmpty(message = "Course Title cannot be empty")
    private String title;

    @NotEmpty(message = "Course Description cannot be empty")
    private String description;

    @NotEmpty(message = "Course Classroom cannot be empty")
    @Pattern(regexp = "^(101|102|103|104)$", message = "Classroom must be either 101, 102, 103 or 104")
    private String classroom;

    @NotEmpty(message = "Teacher ID cannot be empty")
    private String teacherId;
}
