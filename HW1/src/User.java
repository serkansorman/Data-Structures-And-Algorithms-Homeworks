import java.io.*;
import java.security.InvalidParameterException;

/**

 * This is an abstract class. All users will extended from USER CLASS.
 * This class implements UserInterface for common properties
 * Class has user information; name,citizenship number
 *
 */
public abstract class User implements UserInterface {

    protected  String name;
    protected  String citizenshipNum;

    /**
     * @param name user name
     * @param citizenshipNum user citizenship number
     * @throws  InvalidParameterException invalid citizenship number
     */
    public User(String name,String citizenshipNum) throws InvalidParameterException{
        try{Integer.parseInt(citizenshipNum);
        }catch (Exception e){
            throw new InvalidParameterException("Citizenship number must consist of numbers");
        }
        if(citizenshipNum.length() != 8)
            throw new InvalidParameterException("Citizenship number length must be 8");
        this.name = name;
        this.citizenshipNum = citizenshipNum;
    }

    /**
     * @return user name
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return user citizenship number
     */
    public String getctNum(){
        return citizenshipNum;
    }

    /**
     * Remove information of guest
     * @param guestInfo guest information that will be removed from records
     * @throws IOException
     */
    protected boolean removeGuest(String guestInfo) throws IOException {

        boolean isRemoved = false;
        File inputFile = new File("records.csv");
        File tempFile = new File("newRecords.txt");

        String currentLine;
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        while((currentLine = reader.readLine()) != null) {
            if(!currentLine.startsWith(guestInfo))
                writer.write(currentLine + System.getProperty("line.separator"));
            else
                isRemoved = true;
        }
        writer.close();
        reader.close();

        inputFile.delete();
        tempFile.renameTo(inputFile);

        return  isRemoved;

    }

    /**
     * Search guest in hotel records file
     * @param targetGuest searched guest information
     * @return true if find guest,false otherwise
     * @throws IOException
     */
    protected boolean findGuest(String targetGuest) throws IOException {
        File inputFile = new File("records.csv");
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String currentLine;
        while((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            if(trimmedLine.startsWith(targetGuest)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    /**
     *
     * @return User information
     */
    @Override
    public String toString() {
        return name+','+citizenshipNum;
    }

    /**
     *
     * @param obj User
     * @return true if equals,otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return name.equals(user.name) && citizenshipNum.equals(user.citizenshipNum);
    }
}
