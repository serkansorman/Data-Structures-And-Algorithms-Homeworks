package Part3;

import Part1.Course;
import Part3.GTUCourseLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GTUCourseLinkedListTest {

    GTUCourseLinkedList testList;
    GTUCourseLinkedList expectedList;
    GTUCourseLinkedList.LinkedListIterator itr;

    @BeforeEach
    void setUp() {
       testList = new GTUCourseLinkedList();
       expectedList = new GTUCourseLinkedList();

       testList.add(new Course(7,"CSE 495","Graduation Project I",6,1,"4+0+0"));
       testList.add(new Course(5,"CSE 341","Programming Languages",6,3,"3+0+0"));
       testList.add(new Course(2,"TUR 102","Turkish II",2,2,"2+0+0"));

    }

    @Test
    void addByElementTest() {

        expectedList.add(new Course(7,"CSE 495","Graduation Project I",6,1,"4+0+0"));
        expectedList.add(new Course(5,"CSE 341","Programming Languages",6,3,"3+0+0"));
        expectedList.add(new Course(2,"TUR 102","Turkish II",2,2,"2+0+0"));

        assertEquals(3,testList.size());
        assertEquals(expectedList,testList);
    }

    @Test
    void addByIndexTest() {
        expectedList.add(new Course(7,"CSE 495","Graduation Project I",6,1,"4+0+0"));
        expectedList.add(new Course(5,"CSE 341","Programming Languages",6,3,"3+0+0"));
        expectedList.add(new Course(1,"PHYS 121","Physics I",6,4,"3+0+0"));
        expectedList.add(new Course(2,"TUR 102","Turkish II",2,2,"2+0+0"));

        testList.add(2,new Course(1,"PHYS 121","Physics I",6,4,"3+0+0"));
        assertEquals(expectedList,testList);

        try {
            testList.add(10,new Course(1,"PHYS 151","Physics Laboratory I",1,1,"0+0+2"));
        }catch (IndexOutOfBoundsException e){
           System.out.println(e.getMessage());
        }
    }

    @Test
    void removeByElementTest() {

        Course expectedCourse = new Course(7,"CSE 495","Graduation Project I",6,1,"4+0+0");

        expectedList.add(new Course(5,"CSE 341","Programming Languages",6,3,"3+0+0"));
        expectedList.add(new Course(2,"TUR 102","Turkish II",2,2,"2+0+0"));

        assertEquals(expectedCourse,testList.remove(expectedCourse));

        assertEquals(expectedList,testList);

    }

    @Test
    void removeByIndexTest() {

        Course expectedCourse = new Course(2,"TUR 102","Turkish II",2,2,"2+0+0");

        expectedList.add(new Course(7,"CSE 495","Graduation Project I",6,1,"4+0+0"));
        expectedList.add(new Course(5,"CSE 341","Programming Languages",6,3,"3+0+0"));

        assertEquals(expectedCourse,testList.remove(expectedCourse));

        assertEquals(expectedList,testList);

    }

    @Test
    void nextInSemesterTest(){

        testList = new GTUCourseLinkedList();

        testList.add(new Course(3,"XXX XXX","Teknik Olmayan Seçmeli (SSB)",3,2,"2+0+0"));
        testList.add(new Course(7,"CSE 495","Graduation Project I",6,1,"4+0+0"));
        testList.add(new Course(3,"EN 111","English For Business Life",2,2,"2+0+0"));
        testList.add(new Course(2,"TUR 102","Turkish II",2,2,"2+0+0"));
        testList.add(new Course(3,"CSE 211","Discrete Mathematics",6,3,"3+0+0"));
        testList.add(new Course(1,"PHYS 121","Physics I",6,4,"3+0+0"));

        itr = testList.new LinkedListIterator();

        int expectedSemester = 3;
        int actualSemester;
        do{
            actualSemester = itr.nextInSemester().getSemester(); // Her nextInSemester yapıldığında sonraki kursun döneminin 3 olması beklenir.
            assertEquals(expectedSemester,actualSemester);
        }
        while (itr.hasNextSemester());
    }

    @Test
    void nextTest(){

        itr = testList.new LinkedListIterator();
        while (itr.hasNext())
            System.out.print(itr.next());
    }
}