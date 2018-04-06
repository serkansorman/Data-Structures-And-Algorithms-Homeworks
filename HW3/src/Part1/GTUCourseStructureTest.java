package Part1;

import Part1.Course;
import Part1.GTUCourseStructure;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GTUCourseStructureTest {

    GTUCourseStructure gtu;

    GTUCourseStructureTest() throws FileNotFoundException {
        gtu = new GTUCourseStructure();
    }

    @Test
    void getByCodeTest() {
        LinkedList<Course> expected = new LinkedList<>();
        expected.add(new Course(4,"CSE 222","Data Structures And Algorithms",9,5,"4+2+0"));

        LinkedList<Course> actual = gtu.getByCode("CSE 222");
        assertEquals(expected,actual);

        actual = gtu.getByCode("XXX XXX");
        assertEquals(actual.size(),9);

        try {
            gtu.getByCode("CSE 999");  // Exception expected
        }
        catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }

    }
    @Test
    void listSemesterCoursesTest() {

        int expectedSize = 8;

        LinkedList<Course> actual = gtu.listSemesterCourses(1);
        assertEquals(actual.size(),expectedSize);

        expectedSize = 6;
        actual = gtu.listSemesterCourses(4);
        assertEquals(actual.size(),expectedSize);

        try {
            gtu.listSemesterCourses(10);  // Exception expected
        }
        catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    void getByRangeTest() {

        LinkedList<Course> expected = new LinkedList<>();
        expected.add(new Course(1,"MATH 101","Calculus I",7,5,"5+0+0"));
        expected.add(new Course(1,"PHYS 121","Physics I",6,4,"3+0+0"));
        expected.add(new Course(1,"PHYS 151","Physics Laboratory I",1,1,"0+0+2"));

        LinkedList<Course> actual = gtu.getByRange(3,5);
        assertEquals(expected,actual);

        try {
            gtu.getByRange(8,4);  // Exception expected
        }
        catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }

        try {
            gtu.getByRange(-10,5);  // Exception expected
        }
        catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }
    }

}