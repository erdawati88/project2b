package com.sddevops.jenkins_project2.eclipse;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ClassGroupTest {

    @Test
    // Purpose: Verify that the constructor correctly initializes the class group.
    // Scenario: Positive
    void testConstructor() {

        int capacity = 5;
        ClassGroup group = new ClassGroup(capacity);

        assertEquals(capacity, group.getCapacity());
        assertEquals(0, group.getSize());
        assertNotNull(group.getStudents());
        assertEquals(capacity, group.getStudents().length);
    }

    @Test
    // Purpose: Verify that a student can be added successfully when capacity is available.
    // Scenario: Positive
    void testAddStudentWithinCapacity() {

        ClassGroup group = new ClassGroup(2);

        Student s1 = new Student(1, "Alice",
                LocalDate.of(2000, Month.JANUARY, 1));

        Student s2 = new Student(2, "Bob",
                LocalDate.of(2000, Month.FEBRUARY, 1));

        assertTrue(group.addStudent(s1));
        assertTrue(group.addStudent(s2));

        assertEquals(2, group.getSize());
    }

    @Test
    // Purpose: Verify that addStudent() returns false when the class group is full.
    // Scenario: Boundary
    void testAddStudentExceedsCapacity() {

        ClassGroup group = new ClassGroup(1);

        Student s1 = new Student(1, "Alice",
                LocalDate.of(2000, Month.JANUARY, 1));

        Student s2 = new Student(2, "Bob",
                LocalDate.of(2000, Month.FEBRUARY, 1));

        assertTrue(group.addStudent(s1));
        assertFalse(group.addStudent(s2));

        assertEquals(1, group.getSize());
    }

    @Test
    // Purpose: Verify that removing the first student shifts the remaining students correctly.
    // Scenario: Positive
    void testRemoveFirstStudent() {

        ClassGroup group = new ClassGroup(3);

        Student s1 = new Student(1, "Alice",
                LocalDate.of(2000, Month.JANUARY, 1));

        Student s2 = new Student(2, "Bob",
                LocalDate.of(2000, Month.FEBRUARY, 1));

        Student s3 = new Student(3, "Charlie",
                LocalDate.of(2000, Month.MARCH, 1));

        group.addStudent(s1);
        group.addStudent(s2);
        group.addStudent(s3);

        assertTrue(group.removeStudent(1));

        assertEquals(2, group.getSize());
        assertEquals(2, group.getStudents()[0].getId());
        assertEquals(3, group.getStudents()[1].getId());
        assertNull(group.getStudents()[2]);
    }

    @Test
    // Purpose: Verify that removing a student from the middle shifts the remaining students correctly.
    // Scenario: Positive
    void testRemoveMiddleStudent() {

        ClassGroup group = new ClassGroup(3);

        Student s1 = new Student(1, "Alice",
                LocalDate.of(2000, Month.JANUARY, 1));

        Student s2 = new Student(2, "Bob",
                LocalDate.of(2000, Month.FEBRUARY, 1));

        Student s3 = new Student(3, "Charlie",
                LocalDate.of(2000, Month.MARCH, 1));

        group.addStudent(s1);
        group.addStudent(s2);
        group.addStudent(s3);

        assertTrue(group.removeStudent(2));

        assertEquals(2, group.getSize());
        assertEquals(1, group.getStudents()[0].getId());
        assertEquals(3, group.getStudents()[1].getId());
        assertNull(group.getStudents()[2]);
    }

    @Test
    // Purpose: Verify that removing the only student leaves the group empty.
    // Scenario: Boundary
    void testRemoveOnlyStudent() {

        ClassGroup group = new ClassGroup(1);

        Student s1 = new Student(1, "Alice",
                LocalDate.of(2000, Month.JANUARY, 1));

        group.addStudent(s1);

        assertTrue(group.removeStudent(1));

        assertEquals(0, group.getSize());
        assertNull(group.getStudents()[0]);
    }

    @Test
    // Purpose: Verify that removeStudent() returns false when the student ID does not exist.
    // Scenario: Negative
    void testRemoveStudentNotFound() {

        ClassGroup group = new ClassGroup(2);

        group.addStudent(new Student(1, "Alice",
                LocalDate.of(2000, Month.JANUARY, 1)));

        assertFalse(group.removeStudent(999));

        assertEquals(1, group.getSize());
    }

    @Test
    // Purpose: Verify that getTheOldestStudent() returns the student with the earliest birthday.
    // Scenario: Positive
    void testGetOldestStudent() {

        ClassGroup group = new ClassGroup(3);

        Student s1 = new Student(1, "Alice",
                LocalDate.of(2001, Month.JANUARY, 1));

        Student s2 = new Student(2, "Bob",
                LocalDate.of(1999, Month.JANUARY, 1));

        Student s3 = new Student(3, "Charlie",
                LocalDate.of(2000, Month.JANUARY, 1));

        group.addStudent(s1);
        group.addStudent(s2);
        group.addStudent(s3);

        Student oldest = group.getTheOldestStudent();

        assertEquals(s2, oldest);
    }

    @Test
    // Purpose: Verify that getTheOldestStudent() returns null when the class group is empty.
    // Scenario: Negative
    void testGetOldestStudentEmptyGroup() {

        ClassGroup group = new ClassGroup(5);

        assertNull(group.getTheOldestStudent());
    }

    @Test
    // Purpose: Verify that when students have the same birthday,
    // the first student added is returned as the oldest.
    // Scenario: Boundary
    void testGetOldestStudentWithSameBirthday() {

        ClassGroup group = new ClassGroup(2);

        Student s1 = new Student(1, "Alice",
                LocalDate.of(2000, Month.JANUARY, 1));

        Student s2 = new Student(2, "Bob",
                LocalDate.of(2000, Month.JANUARY, 1));

        group.addStudent(s1);
        group.addStudent(s2);

        Student oldest = group.getTheOldestStudent();

        assertEquals(s1, oldest);
    }

}