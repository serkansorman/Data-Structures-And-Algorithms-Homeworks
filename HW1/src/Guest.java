import java.io.*;
import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * Holds guest information's;
 * Guest can book or cancel room
 */
public class Guest extends User {

    /**
     * guest room number
     */
    private int roomNum;
    private String roomType;
    private int stayDay;
    /**
     * guest's payment amount for room = StayingDay x room price
     */
    private int paymentAmount;

    /**
     *
     * @param name guest name
     * @param citizenshipNum guest citizenship number
     * @param roomType guest room type,single or double
     * @param stayDay guest staying time in room
     * @throws InvalidParameterException invalid day
     */
    public Guest(String name,String citizenshipNum,String roomType,int stayDay)throws InvalidParameterException{
        super(name, citizenshipNum);
        this.roomType = roomType;
        if(stayDay < 1 || stayDay > 30)//Check validity of stayDay
            throw new InvalidParameterException("Staying day must be between 1-30");
        this.stayDay = stayDay;
        paymentAmount = 0;
    }

    /**
     *
     * @return guest room number
     */
    public int getRoomNum(){
        return roomNum;
    }

    /**
     *
     * @return guest room type
     */
    public String getRoomType() {
        return roomType;
    }

    public int getStayDay() {
        return stayDay;
    }

    /**
     *
     * @return guest payment amount
     */
    public int getPaymentAmount(){
        return paymentAmount;
    }

    /**
     * set guest room number
     * @param num guest room number
     */
    public void setRoomNum(int num){
        roomNum = num;
    }

    /**
     * set guest payment amount
     * @param pAmount guest payment amount
     */
    public void setPaymentAmount(int pAmount){
        paymentAmount = pAmount;
    }

    /**
     *
     * @return guest information
     */
    @Override
    public String toString() {
        return name+','+citizenshipNum;
    }

    /**
     * compare two guests
     * @param obj guest
     * @return true if equals,otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        Guest guest = (Guest) obj;
        return name.equals(guest.name) && citizenshipNum.equals(guest.citizenshipNum)
            && roomNum == guest.roomNum && paymentAmount == guest.paymentAmount && guest.stayDay == stayDay;
    }

    /**
     * write guest reservation record to file
     * @param room guest room
     * @throws IOException
     */
    private void writeRecords(Hotel.Room room) throws IOException {
        FileWriter pw = new FileWriter("records.csv",true);
        StringBuilder sb = new StringBuilder();
        sb.append(toString()+','+room.getRoomNum()+','+room.getRoomType()+','+"Reserved"+','+'0'+'\n');
        pw.write(sb.toString());
        pw.close();
    }

    /**
     * read guest information from file
     * @param hotel holds room and provide accessing all rooms
     * @throws FileNotFoundException
     */
    private void readRecords(Hotel hotel) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("records.csv"));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] lineArray = line.split(",");
            if(getctNum().equals(lineArray[1]))
                setRoomNum(Integer.parseInt(lineArray[2]));
            if(lineArray[4].equals("Reserved"))
                hotel.getRooms()[Integer.parseInt(lineArray[2]) - 1].setRoomStatus(true);
            else if(lineArray[4].equals("Checked-in"))
                hotel.getRooms()[Integer.parseInt(lineArray[2]) - 1].setRoomCheck(true);
        }
        scan.close();
    }

    /**
     * Book room by guest
     * @param hotel holds room and provide accessing all rooms
     * @return true if book successfully false,otherwise
     * @throws IOException
     */
    @Override
    public boolean book(Hotel hotel) throws IOException {

        readRecords(hotel);
        if(hotel.isHotelFull(roomType))
            return false;

        for (int i = 0; i<hotel.getNumberOfRooms(); ++i) {//Set single room to guest
            if (roomType.equals("Single") && !hotel.getRooms()[i].getRoomStatus() && !hotel.getRooms()[i].getRoomCheck() &&
                    hotel.getRooms()[i].getRoomType().equals("Single")) {
                setRoomNum(i+1);
                hotel.getRooms()[i].setRoomStatus(true);
                writeRecords(hotel.getRooms()[i]);
                return true;
            }
            else if(roomType.equals("Double") && !hotel.getRooms()[i].getRoomStatus() && !hotel.getRooms()[i].getRoomCheck() &&
                    hotel.getRooms()[i].getRoomType().equals("Double")){//Set double room to guest
                setRoomNum(i+1);
                hotel.getRooms()[i].setRoomStatus(true);
                writeRecords(hotel.getRooms()[i]);
                return true;
            }
        }

        return false;
    }

    /**
     * Cancel reservation by guest
     * @param hotel holds room and provide accessing all rooms
     * @return true if cancel successfully,false otherwise
     * @throws IOException
     */
    @Override
    public boolean cancel(Hotel hotel) throws IOException {

        readRecords(hotel);
        if(findGuest(toString()+','+roomNum) && !hotel.getRooms()[roomNum - 1].getRoomCheck()){
            removeGuest(toString()+','+roomNum);
            return true;
        }
        return  false;
    }
}
