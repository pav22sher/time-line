package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    private final Main main = new Main();

    @Test
    @DisplayName("Общая проверка")
    public void common () {
        //arrange - подготовка - Given
        List<User> arranges = new ArrayList<>();
        arranges.add(new User(10, 100, "Test1"));
        arranges.add(new User(50, 70, "Test2"));
        arranges.add(new User(60, 120, "Test3"));
        arranges.add(new User(150, 300, "Test4"));
        //act - действие - When
        List<WorkTime> actual = main.split(arranges);
        //assert - проверка - Then
        List<WorkTime> expected = new ArrayList<>();
        expected.add(new WorkTime(10, 49, List.of("Test1")));
        expected.add(new WorkTime(50, 59, List.of("Test1","Test2")));
        expected.add(new WorkTime(60, 70, List.of("Test1","Test2","Test3")));
        expected.add(new WorkTime(71, 100, List.of("Test1", "Test3")));
        expected.add(new WorkTime(101, 120, List.of("Test3")));
        expected.add(new WorkTime(150, 300, List.of("Test4")));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Обратный порядок")
    public void reverse () {
        //arrange - подготовка - Given
        List<User> arranges = new ArrayList<>();
        arranges.add(new User(150, 300, "Test4"));
        arranges.add(new User(60, 120, "Test3"));
        arranges.add(new User(50, 70, "Test2"));
        arranges.add(new User(10, 100, "Test1"));
        //act - действие - When
        List<WorkTime> actual = main.split(arranges);
        //assert - проверка - Then
        List<WorkTime> expected = new ArrayList<>();
        expected.add(new WorkTime(10, 49, List.of("Test1")));
        expected.add(new WorkTime(50, 59, List.of("Test1","Test2")));
        expected.add(new WorkTime(60, 70, List.of("Test1","Test2","Test3")));
        expected.add(new WorkTime(71, 100, List.of("Test1", "Test3")));
        expected.add(new WorkTime(101, 120, List.of("Test3")));
        expected.add(new WorkTime(150, 300, List.of("Test4")));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Не пересекаются")
    public void notIntersection () {
        //arrange - подготовка - Given
        List<User> arranges = new ArrayList<>();
        arranges.add(new User(10, 20, "Test1"));
        arranges.add(new User(30, 40, "Test2"));
        //act - действие - When
        List<WorkTime> actual = main.split(arranges);
        //assert - проверка - Then
        List<WorkTime> expected = new ArrayList<>();
        expected.add(new WorkTime(10, 20, List.of("Test1")));
        expected.add(new WorkTime(30, 40, List.of("Test2")));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Пересечение 1")
    public void intersection1 () {
        //arrange - подготовка - Given
        List<User> arranges = new ArrayList<>();
        arranges.add(new User(10, 20, "Test1"));
        arranges.add(new User(10, 40, "Test2"));
        //act - действие - When
        List<WorkTime> actual = main.split(arranges);
        //assert - проверка - Then
        List<WorkTime> expected = new ArrayList<>();
        expected.add(new WorkTime(10, 20, List.of("Test1", "Test2")));
        expected.add(new WorkTime(21, 40, List.of("Test2")));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Пересечение 2")
    public void intersection2 () {
        //arrange - подготовка - Given
        List<User> arranges = new ArrayList<>();
        arranges.add(new User(10, 40, "Test1"));
        arranges.add(new User(10, 20, "Test2"));
        //act - действие - When
        List<WorkTime> actual = main.split(arranges);
        //assert - проверка - Then
        List<WorkTime> expected = new ArrayList<>();
        expected.add(new WorkTime(10, 20, List.of("Test1", "Test2")));
        expected.add(new WorkTime(21, 40, List.of("Test1")));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Пересечение 3")
    public void intersection3 () {
        //arrange - подготовка - Given
        List<User> arranges = new ArrayList<>();
        arranges.add(new User(10, 40, "Test1"));
        arranges.add(new User(20, 30, "Test2"));
        //act - действие - When
        List<WorkTime> actual = main.split(arranges);
        //assert - проверка - Then
        List<WorkTime> expected = new ArrayList<>();
        expected.add(new WorkTime(10, 19, List.of("Test1")));
        expected.add(new WorkTime(20, 30, List.of("Test1", "Test2")));
        expected.add(new WorkTime(31, 40, List.of("Test1")));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Пересечение 4")
    public void intersection4 () {
        //arrange - подготовка - Given
        List<User> arranges = new ArrayList<>();
        arranges.add(new User(20, 30, "Test1"));
        arranges.add(new User(10, 40, "Test2"));
        //act - действие - When
        List<WorkTime> actual = main.split(arranges);
        //assert - проверка - Then
        List<WorkTime> expected = new ArrayList<>();
        expected.add(new WorkTime(10, 19, List.of("Test2")));
        expected.add(new WorkTime(20, 30, List.of("Test1", "Test2")));
        expected.add(new WorkTime(31, 40, List.of("Test2")));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Пересечение 5")
    public void intersection5 () {
        //arrange - подготовка - Given
        List<User> arranges = new ArrayList<>();
        arranges.add(new User(10, 30, "Test1"));
        arranges.add(new User(20, 40, "Test2"));
        //act - действие - When
        List<WorkTime> actual = main.split(arranges);
        //assert - проверка - Then
        List<WorkTime> expected = new ArrayList<>();
        expected.add(new WorkTime(10, 19, List.of("Test1")));
        expected.add(new WorkTime(20, 30, List.of("Test1", "Test2")));
        expected.add(new WorkTime(31, 40, List.of("Test2")));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Впритык 1")
    public void tight() {
        //arrange - подготовка - Given
        List<User> arranges = new ArrayList<>();
        arranges.add(new User(1, 5, "Test1"));
        arranges.add(new User(5, 10, "Test2"));
        //act - действие - When
        List<WorkTime> actual = main.split(arranges);
        //assert - проверка - Then
        List<WorkTime> expected = new ArrayList<>();
        expected.add(new WorkTime(1, 4, List.of("Test1")));
        expected.add(new WorkTime(5, 5, List.of("Test1","Test2")));
        expected.add(new WorkTime(6, 10, List.of("Test2")));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Впритык 2")
    public void singleTight() {
        //arrange - подготовка - Given
        List<User> arranges = new ArrayList<>();
        arranges.add(new User(1, 1, "Test1"));
        arranges.add(new User(1, 1, "Test2"));
        //act - действие - When
        List<WorkTime> actual = main.split(arranges);
        //assert - проверка - Then
        List<WorkTime> expected = new ArrayList<>();
        expected.add(new WorkTime(1, 1, List.of("Test1","Test2")));
        Assertions.assertEquals(expected, actual);
    }
}