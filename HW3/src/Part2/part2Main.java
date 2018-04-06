package Part2;

import Part1.Course;
import Part1.GTUCourseStructure;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;

public class part2Main {

    public  static void main(String arg[]){
        try {
            GTUCourseStructure gtu = new GTUCourseStructure();
            System.out.println("----------### PART 2 SCENARIO ###------------------\n");
            GTULinkedList<Course> list1 = new GTULinkedList<>();

            //Tüm kurslar part1 deki GTUCourseStructure classından getter ile rastgele alınmıştır
            list1.add(gtu.getCourses().get(1));
            list1.add(gtu.getCourses().get(10));
            list1.add(gtu.getCourses().get(15));
            list1.add(gtu.getCourses().get(25));
            list1.add(gtu.getCourses().get(30));
            list1.add(gtu.getCourses().get(38));
            list1.add(gtu.getCourses().get(18));

            System.out.println("\t------- Elements of list --------\n"+list1);
            System.out.println("List Size: "+list1.size());
            if(list1.disable(2));
                System.out.println("Index 2 disabled"); // TUR 102
            System.out.println("List Size: "+list1.size());
            if(list1.disable(gtu.getCourses().get(18)))
                System.out.println("CSE 231 disabled");
            System.out.println("List Size: "+list1.size());
            System.out.println("\t------- Elements of list --------\n"+list1);

            System.out.println("----Disabled elements----");
            list1.showDisabled();
            System.out.println();

            if(list1.enable(gtu.getCourses().get(15)));
                System.out.println("TUR 102 Enabled");

            if(list1.enable(6))
                System.out.println("CSE 231 Enabled");

            System.out.println("List Size: "+list1.size());
            System.out.println("\t------- Elements of list --------\n"+list1);


        }
        catch (IndexOutOfBoundsException | FileNotFoundException | InvalidParameterException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
