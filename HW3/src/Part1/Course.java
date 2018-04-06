package Part1;

/**
 * Includes all informations of course
 */
public class Course {
    private String name;
    private String code;
    private int semester;
    private int ECTScredits;
    private int GTUcredits;
    private String otherInfo;

    public Course(int semester,String code,String name,int ECTScredits,int GTUcredits,String otherInfo){
        this.name = name;
        this.code = code;
        this.semester = semester;
        this.ECTScredits = ECTScredits;
        this.GTUcredits = GTUcredits;
        this.otherInfo = otherInfo;
    }

    public String getCode() {
        return code;
    }

    public int getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return semester+","+code+","+name+","+ECTScredits+","+GTUcredits+","+otherInfo+"\n";
    }

    /**
     * Compare two courses
     * @param obj course
     * @return true if two course are equal
     */
    @Override
    public boolean equals(Object obj) {
        Course c = (Course) obj;
        if(c != null)
            return this.name.equals(c.name) && this.code.equals(c.code) && this.semester == c.semester
                    && this.ECTScredits == c.ECTScredits && this.otherInfo.equals(c.otherInfo);
        return false;
    }
}
