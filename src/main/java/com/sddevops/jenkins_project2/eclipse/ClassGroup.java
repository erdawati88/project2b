package com.sddevops.jenkins_project2.eclipse;

public class ClassGroup {

    private Student[] students;
    private int capacity;
    private int size;

    public ClassGroup(int capacity) {
        this.capacity = capacity;
        this.students = new Student[capacity];
        this.size = 0;
    }

    public boolean addStudent(Student student) {
        if (size >= capacity) return false;
        students[size++] = student;
        return true;
    }

    public boolean removeStudent(int studentId) {
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == studentId) {
                for (int j = i; j < size - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    public Student getTheOldestStudent() {
        if (size == 0) return null;

        Student oldest = students[0];
        for (int i = 1; i < size; i++) {
            if (students[i].getBirthday().isBefore(oldest.getBirthday())) {
                oldest = students[i];
            }
        }
        return oldest;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public Student[] getStudents() {
        return students;
    }
}