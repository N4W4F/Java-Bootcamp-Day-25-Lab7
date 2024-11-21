package com.example.learningmanagement.Service;

import com.example.learningmanagement.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {

    ArrayList<Teacher> teachers = new ArrayList<>();

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public ArrayList<Teacher> getTeachers() {
        if (teachers.isEmpty())
            return null;
        return teachers;
    }

    public boolean updateTeacher(String id, Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(id)) {
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(String id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(id)) {
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    //--------------------------------------------------------------------------------

    public ArrayList<Teacher> getTeachersByCourse(String course) {
        ArrayList<Teacher> newTeachers = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getCourse().equalsIgnoreCase(course)) {
                newTeachers.add(teacher);
            }
        }
        if (newTeachers.isEmpty())
            return null;
        return newTeachers;
    }

    public boolean assignTeacherToCourse(String teacherId, String course) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(teacherId)) {
                teacher.setCourse(course);
                return true;
            }
        }
        return false;
    }

    public boolean removeCourseFromTeacher(String teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(teacherId)) {
                teacher.setCourse(null);
                return true;
            }
        }
        return false;
    }
}
