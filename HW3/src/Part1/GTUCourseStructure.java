package Part1;

import Part1.Course;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Holds the list of courses
 */
public class GTUCourseStructure {
    /**
     * Holds all courses
     */
    private LinkedList<Course> courses;


    public GTUCourseStructure() throws FileNotFoundException {
        courses = new LinkedList<>();
        readCourses();
    }

    /**
     * Read courses information from csv file and add the courses which are created with this information
     * @throws FileNotFoundException
     */
    private void readCourses() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("Courses.csv"));
        String line = scan.nextLine(); //First line is unimportant
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            String[] lineArray = line.split(";");
            Course course = new Course(Integer.parseInt(lineArray[0]),lineArray[1],lineArray[2],
                    Integer.parseInt(lineArray[3]),Integer.parseInt(lineArray[4]),lineArray[5]);
            courses.add(course);
        }
        scan.close();
    }

    /**
     * Search course which has given code
     * @param code code of course
     * @return all courses which have given course code.
     * @throws InvalidParameterException if there are no matched course.
     */
    public LinkedList<Course> getByCode(String code)throws InvalidParameterException{
        int size = courses.size() - 1;
        LinkedList<Course> sameCodeCourses = new LinkedList<>();
        while (size >= 0){
            if(courses.get(size).getCode().equals(code))
                sameCodeCourses.add(courses.get(size));
            --size;
        }
        if(sameCodeCourses.size() == 0)
            throw new InvalidParameterException("There are no matched course with this code");

        return sameCodeCourses;

    }

    /**
     * Search course which has in given semester
     * @param semester semester of course
     * @return all courses on given semester.
     * @throws InvalidParameterException if there are no matched course.
     */
    public LinkedList<Course> listSemesterCourses(int semester) throws InvalidParameterException {
        int size = courses.size() - 1;
        LinkedList<Course> sameSemesterCourses = new LinkedList<>();
        while (size >= 0){
            if(courses.get(size).getSemester() == semester)
                sameSemesterCourses.add(courses.get(size));
            --size;
        }

        if(sameSemesterCourses.size() == 0)
            throw new InvalidParameterException("There are no matched course in this semester");

        return sameSemesterCourses;
    }

    /**
     * Get courses from list in given range
     * @param start_index
     * @param last_index
     * @return all courses from given start index to last index.
     * @throws InvalidParameterException
     */
    public LinkedList<Course> getByRange(int start_index, int last_index) throws InvalidParameterException {

        if(start_index < courses.size() && last_index < courses.size() &&
                start_index >= 0 && last_index >= 0 && start_index <= last_index){
            LinkedList<Course> coursesInRange = new LinkedList<>();
            for(int i = start_index; i <= last_index; ++i)
                coursesInRange.add(courses.get(i));
            return coursesInRange;
        }
        throw new InvalidParameterException("There are not any course in this range");
    }

    /**
     * This method writing for accessing all courses in main
     * @return list of courses
     */
    public LinkedList<Course> getCourses() {
        return courses;
    }
}
