package com.example.learningmanagement.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "Student ID cannot be empty")
    @Size(min = 9, max = 9, message = "Student ID must be exactly 9 numbers")
    private String id;

    @NotEmpty(message = "Student Name cannot be empty")
    private String name;

    @NotNull(message = "Student Age cannot be empty")
    @Min(value = 18, message = "Student must be 18 or above to enroll")
    private int age;

    @NotEmpty(message = "Student Email cannot be empty")
    @Email(message = "Student Email must be in valid email format")
    private String email;

    @NotEmpty(message = "Course cannot be empty")
    @Pattern(regexp = "^(Java|Python|PHP)$", message = "Course must be either Java, Python or PHP")
    private String course;
}
