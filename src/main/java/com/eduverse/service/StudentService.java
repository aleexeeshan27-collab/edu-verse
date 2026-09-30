package com.eduverse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Manages student enrollment records for the edu-verse education
 * management system.
 */
public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public Student enroll(String name, String email, String gradeLevel) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name is required");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("A valid email is required");
        }
        Student student = new Student(students.size() + 1, name, email, gradeLevel);
        students.add(student);
        return student;
    }

    public Optional<Student> findById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst();
    }

    public List<Student> findByGradeLevel(String gradeLevel) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getGradeLevel().equalsIgnoreCase(gradeLevel)) {
                result.add(s);
            }
        }
        return result;
    }

    public boolean withdraw(int id) {
        return students.removeIf(s -> s.getId() == id);
    }

    public int count() {
        return students.size();
    }

    public static class Student {
        private final int id;
        private final String name;
        private final String email;
        private final String gradeLevel;

        public Student(int id, String name, String email, String gradeLevel) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.gradeLevel = gradeLevel;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getGradeLevel() {
            return gradeLevel;
        }
    }
}
