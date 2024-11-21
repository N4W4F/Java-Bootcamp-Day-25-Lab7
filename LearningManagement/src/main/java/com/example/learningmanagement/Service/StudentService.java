package com.example.learningmanagement.Service;

import com.example.learningmanagement.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public ArrayList<Student> getStudents() {
        if (students.isEmpty())
            return null;
        return students;
    }

    public boolean updateStudent(String id, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

//--------------------------------------------------------------------------------

    public String getStudentCourses(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student.getCourse();
            }
        }
        return null;
    }

    public ArrayList<Student> getStudentsByCourse(String course) {
        ArrayList<Student> newStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getCourse().equalsIgnoreCase(course)) {
                newStudents.add(student);
            }
        }
        if (newStudents.isEmpty())
            return null;
        return newStudents;
    }

    public boolean changeStudentCourse(String studentId, String course) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                student.setCourse(course);
                return true;
            }
        }
        return false;
    }
}
