package Part1;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;

public class part1Main {

    public static void main(String arg[]){
        try {
            GTUCourseStructure gtu = new GTUCourseStructure();
            System.out.println("-----------### PART 1 SCENARIO ###------------------\n");

            System.out.println(gtu.getByCode("XXX XXX"));
            System.out.println(gtu.listSemesterCourses(3));
            System.out.println(gtu.getByRange(2,4));
            //Diğer tüm hata durumları unit testlerde test edilmiştir.
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
