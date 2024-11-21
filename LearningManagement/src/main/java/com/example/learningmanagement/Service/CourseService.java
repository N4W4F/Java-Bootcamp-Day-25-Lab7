package com.example.learningmanagement.Service;

import com.example.learningmanagement.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService extends StudentService{

    ArrayList<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Course> getCourses() {
        if (courses.isEmpty())
            return null;

        return courses;
    }

    public boolean updateCourse(String courseId, Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(courseId)) {
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCourse(String courseId) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(courseId)) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    //--------------------------------------------------------------------------------

    public ArrayList<Course> getCoursesByTeacher(String teacherId) {
        ArrayList<Course> coursesByTeacher = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTeacherId().equals(teacherId)) {
                coursesByTeacher.add(course);
            }
        }
        if (coursesByTeacher.isEmpty())
            return null;
        return coursesByTeacher;
    }

    public ArrayList<Course> getCoursesByClassroom(String classroom) {
        ArrayList<Course> coursesByClassroom = new ArrayList<>();
        for (Course course : courses) {
            if (course.getClassroom().equals(classroom)) {
                coursesByClassroom.add(course);
            }
        }
        if (coursesByClassroom.isEmpty())
            return null;
        return coursesByClassroom;
    }

    public Course getCourseByTitle(String title) {
        for (Course course : courses) {
            if (course.getTitle().equalsIgnoreCase(title)) {
                return course;
            }
        }
        return null;
    }

    public boolean changeClassroom(String courseId, String newClassroom) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                course.setClassroom(newClassroom);
                return true;
            }
        }
        return false;
    }

    public boolean changeTeacher(String courseId, String newTeacherId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                course.setTeacherId(newTeacherId);
                return true;
            }
        }
        return false;
    }
}
