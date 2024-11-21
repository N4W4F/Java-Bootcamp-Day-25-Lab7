package com.example.learningmanagement.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    @NotEmpty(message = "Teacher ID cannot be empty")
    @Size(min = 9, max = 9, message = "Teacher ID must be exactly 9 numbers")
    private String id;

    @NotEmpty(message = "Teacher Name cannot be empty")
    private String name;

    @NotNull(message = "Teacher Age cannot be empty")
    @Min(value = 25, message = "Teacher must be 25 or above to enroll")
    private int age;

    @NotEmpty(message = "Teacher Email cannot be empty")
    @Email(message = "Teacher Email must be in valid email format")
    private String email;

    @NotEmpty(message = "Bootcamp cannot be empty")
    @Pattern(regexp = "^(Java|Python|PHP)$", message = "Course must be either Java, Python or PHP")
    private String course;
}
