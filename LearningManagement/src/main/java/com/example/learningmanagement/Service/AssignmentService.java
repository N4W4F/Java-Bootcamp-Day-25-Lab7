package com.example.learningmanagement.Service;

import com.example.learningmanagement.Model.Assignment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AssignmentService extends StudentService {

    ArrayList<Assignment> assignments = new ArrayList<>();

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public ArrayList<Assignment> getAssignments() {
        if (assignments.isEmpty())
            return null;

        return assignments;
    }

    public boolean updateAssignment(String assignmentId, Assignment assignment) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getId().equals(assignmentId)) {
                assignments.set(i, assignment);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAssignment(String assignmentId) {
        for (int i = 0; i < assignments.size(); i++) {
            if (assignments.get(i).getId().equals(assignmentId)) {
                assignments.remove(i);
                return true;
            }
        }
        return false;
    }

    //--------------------------------------------------------------------------------

    public ArrayList<Assignment> getAssignmentByCourse(String courseId) {
        ArrayList<Assignment> newAssignments = new ArrayList<>();
        for (Assignment assignment : assignments) {
            if (assignment.getCourseId().equals(courseId)) {
                newAssignments.add(assignment);
            }
        }
        if (newAssignments.isEmpty())
            return null;
        return newAssignments;
    }

    public ArrayList<Assignment> getCompletedAssignments() {
        ArrayList<Assignment> newAssignments = new ArrayList<>();
        for (Assignment assignment : assignments) {
            if (assignment.isComplete())
                newAssignments.add(assignment);
        }
        if (newAssignments.isEmpty())
            return null;
        return newAssignments;
    }

    public ArrayList<Assignment> getPendingAssignments() {
        ArrayList<Assignment> newAssignments = new ArrayList<>();
        for (Assignment assignment : assignments) {
            if (!assignment.isComplete())
                newAssignments.add(assignment);
        }
        if (newAssignments.isEmpty())
            return null;
        return newAssignments;
    }

    public boolean markAsCompleted(String assignmentId) {
        for (Assignment assignment : assignments) {
            if (assignment.getId().equals(assignmentId)) {
                assignment.setComplete(true);
                return true;
            }
        }
        return false;
    }

    public boolean changeCourse(String assignmentId, String newCourseId) {
        for (Assignment assignment : assignments) {
            if (assignment.getId().equals(assignmentId)) {
                assignment.setCourseId(newCourseId);
                return true;
            }
        }
        return false;
    }
}
