package com.sddevops.jenkins_project2.eclipse;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    // Purpose: Verify that the constructor correctly initializes a student without a best friend.
    // Scenario: Positive
    void testConstructorWithoutBestFriend() {
        Student s = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1));

     // Intentionally changed from 1 to 999 to demonstrate a failing JUnit test in the CI pipeline.
        assertEquals(999, s.getId());
        assertEquals("Alice", s.getName());
        assertEquals(LocalDate.of(2025, Month.JANUARY, 1), s.getBirthday());
        assertNull(s.getFriend());
    }

    @Test
    // Purpose: Verify that the constructor correctly initializes a student with a best friend.
    // Scenario: Positive
    void testConstructorWithBestFriend() {
        Student friend = new Student(2, "Bob", LocalDate.of(2024, Month.MAY, 10));
        Student s = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1), friend);

        assertEquals(friend, s.getFriend());
    }

    @Test
    // Purpose: Verify that all getter methods return the values assigned during object creation.
    // Scenario: Positive
    void testGetters() {
        Student friend = new Student(2, "Bob", LocalDate.of(2024, Month.MAY, 10));
        Student s = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1), friend);

        assertEquals(1, s.getId());
        assertEquals("Alice", s.getName());
        assertEquals(LocalDate.of(2025, Month.JANUARY, 1), s.getBirthday());
        assertEquals(friend, s.getFriend());
    }

    @Test
    // Purpose: Verify that all setter methods correctly update the student's attributes.
    // Scenario: Positive
    void testSetters() {
        Student friend = new Student(2, "Bob", LocalDate.of(2024, Month.MAY, 10));
        Student s = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1));

        s.setId(10);
        s.setName("NewName");
        s.setBirthday(LocalDate.of(2000, Month.DECEMBER, 12));
        s.setFriend(friend);

        assertEquals(10, s.getId());
        assertEquals("NewName", s.getName());
        assertEquals(LocalDate.of(2000, Month.DECEMBER, 12), s.getBirthday());
        assertEquals(friend, s.getFriend());
    }

    @Test
    // Purpose: Verify that equals() returns true when comparing the object with itself.
    // Scenario: Positive
    void testEqualsSameObject() {
        Student s = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1));

        assertEquals(s, s);
    }

    @Test
    // Purpose: Verify that equals() and hashCode() return consistent results
    // for two Student objects with identical values.
    // Scenario: Positive
    void testEqualsAndHashCode() {
        Student s1 = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1));
        Student s2 = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1));

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    // Purpose: Verify that equals() returns false when student IDs are different.
    // Scenario: Negative
    void testEqualsDifferentId() {
        Student s1 = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1));
        Student s2 = new Student(2, "Alice", LocalDate.of(2025, Month.JANUARY, 1));

        assertNotEquals(s1, s2);
    }

    @Test
    // Purpose: Verify that equals() returns false when student names are different.
    // Scenario: Negative
    void testEqualsDifferentName() {
        Student s1 = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1));
        Student s2 = new Student(1, "Bob", LocalDate.of(2025, Month.JANUARY, 1));

        assertNotEquals(s1, s2);
    }

    @Test
    // Purpose: Verify that equals() returns false when birthdays are different.
    // Scenario: Negative
    void testEqualsDifferentBirthday() {
        Student s1 = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1));
        Student s2 = new Student(1, "Alice", LocalDate.of(2024, Month.JANUARY, 1));

        assertNotEquals(s1, s2);
    }

    @Test
    // Purpose: Verify that equals() returns false when compared with null
    // or an object of a different type.
    // Scenario: Negative
    void testEqualsNullAndDifferentClass() {
        Student s = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1));

        assertNotEquals(s, null);
        assertNotEquals(s, "Student");
    }

    @Test
    // Purpose: Verify that compareByName() correctly compares names
    // and ignores letter case.
    // Scenario: Boundary
    void testCompareByName() {
        Student s1 = new Student(1, "Charlie", LocalDate.of(2025, Month.JANUARY, 1));
        Student s2 = new Student(2, "Alice", LocalDate.of(2025, Month.JANUARY, 1));
        Student s3 = new Student(3, "alice", LocalDate.of(2025, Month.JANUARY, 1));

        assertTrue(Student.compareByName.compare(s1, s2) > 0);
        assertTrue(Student.compareByName.compare(s2, s1) < 0);
        assertEquals(0, Student.compareByName.compare(s2, s3));
    }

    @Test
    // Purpose: Verify that compareByBirthday() correctly compares birthdays,
    // including students with the same birthday.
    // Scenario: Boundary
    void testCompareByBirthday() {
        Student s1 = new Student(1, "A", LocalDate.of(1999, Month.JANUARY, 1));
        Student s2 = new Student(2, "B", LocalDate.of(2000, Month.JANUARY, 1));
        Student s3 = new Student(3, "C", LocalDate.of(2000, Month.JANUARY, 1));

        assertTrue(Student.compareByBirthday.compare(s1, s2) < 0);
        assertTrue(Student.compareByBirthday.compare(s2, s1) > 0);
        assertEquals(0, Student.compareByBirthday.compare(s2, s3));
    }

    @Test
    // Purpose: Verify that assignRandomUsername() generates a predictable
    // username using a stubbed Random object.
    // Scenario: Positive (Stub)
    void testAssignRandomUsernameUsingStubbedRandom() {
        Student s = new Student(1, "Original", LocalDate.of(2025, Month.JANUARY, 1));

        Random stubRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return 0;
            }
        };

        s.assignRandomUsername(stubRandom);

        // Stub always returns 0:
        // Username length = 5
        // Character selected = 'A'
        assertEquals("AAAAA", s.getName());
    }

    @Test
    // Purpose: Verify that assignRandomUsername() generates a username
    // with a valid length and only supported characters.
    // Scenario: Boundary
    void testAssignRandomUsernameUsingSeededRandom() {
        Student s = new Student(1, "Original", LocalDate.of(2025, Month.JANUARY, 1));

        s.assignRandomUsername(new Random(12345));

        String username = s.getName();

        assertTrue(username.length() >= 5);
        assertTrue(username.length() <= 10);
        assertTrue(username.matches("[A-Za-z0-9_]+"));
    }

    @Test
    // Purpose: Verify that toString() contains the student's information
    // and the assigned best friend's name.
    // Scenario: Positive
    void testToStringContainsFriend() {
        Student friend = new Student(2, "Bob", LocalDate.of(2024, Month.MAY, 10));
        Student s = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1), friend);

        String output = s.toString();

        assertTrue(output.contains("Alice"));
        assertTrue(output.contains("Bob"));
        assertTrue(output.contains("birthday"));
    }

    @Test
    // Purpose: Verify that toString() displays "no best friend"
    // when the student has no assigned best friend.
    // Scenario: Boundary
    void testToStringWithoutFriend() {
        Student s = new Student(1, "Alice", LocalDate.of(2025, Month.JANUARY, 1));

        String output = s.toString();

        assertTrue(output.contains("Alice"));
        assertTrue(output.contains("no best friend"));
    }
}