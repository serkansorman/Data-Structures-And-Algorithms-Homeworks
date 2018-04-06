package Part2;

import Part1.Course;
import Part2.GTULinkedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class GTULinkedListTest {

    GTULinkedList<Course> testList = new GTULinkedList<>();

    @AfterEach
    void tearDown() {
        testList.clear();
    }

    @Test
    void disableTest() {

        testList.add(new Course(1, "MATH 101", "Calculus I", 7, 5, "5+0+0"));
        testList.add(new Course(1, "PHYS 121", "Physics I", 6, 4, "3+0+0"));
        testList.add(new Course(1, "PHYS 151", "Physics Laboratory I", 1, 1, "0+0+2"));

        boolean expected = true;
        boolean actual;

        actual = testList.disable(0);   //Disable olayının gerçekleştiğinin testi
        assertEquals(expected, actual);

        expected = false;
        actual = testList.disable(0); //Daha önceden disable edilmiş bir elemanın tekrar disable edilmeye çalışılması
        assertEquals(expected, actual);

        //DisableByElement i test et
        expected = true;
        actual = testList.disable(new Course(1, "PHYS 151", "Physics Laboratory I", 1, 1, "0+0+2"));
        assertEquals(expected,actual);

        assertEquals(1, testList.size());  //iki eleman disabled edildikten sonra size ı test et

        //Disable edilen elemanlar için çalışmaması gereken tüm methodların testi
        try {
            testList.get(0);
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }

        try {
            testList.remove(0);
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }

        try {
            testList.remove(new Course(1, "MATH 101", "Calculus I", 7, 5, "5+0+0"));
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }

        try {
            testList.set(0, new Course(7, "CSE 495", "Graduation Project I", 6, 1, "4+0+0"));
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }

        try{
            Iterator itr = testList.listIterator(2);
        }catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void enableTest() {

        testList.add(new Course(7, "CSE 495", "Graduation Project I", 6, 1, "4+0+0"));
        testList.add(new Course(5, "CSE 341", "Programming Languages", 6, 3, "3+0+0"));
        testList.add(new Course(2, "TUR 102", "Turkish II", 2, 2, "2+0+0"));
        testList.add(new Course(1, "PHYS 121", "Physics I", 6, 4, "3+0+0"));
        testList.add(new Course(1, "PHYS 151", "Physics Laboratory I", 1, 1, "0+0+2"));

        int expectedSize = 4;
        boolean expected = true;
        boolean actual;

        testList.disable(2); // TUR 102
        testList.disable(4); // PHYS 151

        //Enable metodunun çalışma testi
        actual = testList.enable(new Course(1, "PHYS 151", "Physics Laboratory I", 1, 1, "0+0+2"));
        assertEquals(expected, actual);

        //EnableByIndex i test et
        expected = true;
        actual = testList.enable(2); // TUR 102
        assertEquals(expected,actual);

        //Enable edilen bir elemanın tekrar enable edilmesinin denenmesi
        expected = false;
        actual = testList.enable(new Course(2, "TUR 102", "Turkish II", 2, 2, "2+0+0"));
        assertEquals(expected, actual);

    }
}
