package Part3;

import Part1.Course;
import Part1.GTUCourseStructure;
import Part2.GTULinkedList;
import Part3.GTUCourseLinkedList;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;

public class part3Main {

    public static void printListWithIterator(GTUCourseLinkedList list){
        GTUCourseLinkedList.LinkedListIterator itr  = list.new LinkedListIterator();
        while(itr.hasNext())
            System.out.print(itr.next());
        System.out.println();
    }

    public static  void printSameSemesterCourses(GTUCourseLinkedList list){
        GTUCourseLinkedList.LinkedListIterator itr  = list.new LinkedListIterator();
        do {
            System.out.print(itr.nextInSemester());
        } while (itr.hasNextSemester());
        System.out.println();
    }

    public  static void main(String arg[]){
        try {
            GTUCourseStructure gtu = new GTUCourseStructure();

            System.out.println("----------### PART 3 SCENARIO ###------------------\n");

            GTUCourseLinkedList list = new GTUCourseLinkedList();
            //Kursları rastgele sırayla listeye ekle
            list.add(gtu.getCourses().get(3));
            list.add(gtu.getCourses().get(49));
            list.add(gtu.getCourses().get(12));
            list.add(gtu.getCourses().get(5));
            list.add(gtu.getCourses().get(4));
            list.add(gtu.getCourses().get(15));
            list.add(gtu.getCourses().get(24));
            list.add(gtu.getCourses().get(2));

            System.out.println("------- Elements of list --------\n");
            System.out.println("List size: "+list.size());
            printListWithIterator(list);

            System.out.println("------- Circular List of Semester 1 --------\n");
            printSameSemesterCourses(list);

            System.out.println("------- Circular List of Semester 2 --------\n");
            GTUCourseLinkedList.LinkedListIterator itr  = list.new LinkedListIterator();
            itr.next();
            itr.next();
            do {
                System.out.print(itr.nextInSemester());
            } while (itr.hasNextSemester());
            System.out.println();

            list.remove(3);
            list.remove(new Course(4,"CSE 232","Logic Circuits And Design",6,3,"3+0+0"));

            System.out.println("------- List changed After Index 3 and CSE 232 removed--------\n");
            printListWithIterator(list);

            System.out.println("------- Circular List of Semester 1 After Index 3 removed --------\n");
            printSameSemesterCourses(list);

            System.out.println("------- CSE 331 added to index 2 --------\n");
            list.add(2,gtu.getCourses().get(30));
            printListWithIterator(list);
        }
        catch (IndexOutOfBoundsException | FileNotFoundException | InvalidParameterException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
