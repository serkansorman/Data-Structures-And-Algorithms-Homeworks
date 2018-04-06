import java.io.*;
import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * Holds receptionist information's;
 * Receptionist can book,cancel,check in and check out room
 */
public class Receptionist extends User {

    /**
     * Receptionist makes process for this guest
     */
    private Guest guest;
    /**
     * Receptionist enter the system with this password
     */
    private String passWord = "1234";

    /**
     * @param guest new guest
     * @param passWord receptionist password
     * @throws InvalidParameterException invalid password
     */
    public Receptionist(Guest guest,String passWord)throws InvalidParameterException {
        super("Ahmet Celik","01010101");
        this.guest = guest;
        if(! this.passWord.equals(passWord))
            throw new InvalidParameterException("Invalid Receptionist password");
    }

    /**
     * read guest information from file
     * @param hotel holds room and provide accessing all rooms
     * @throws FileNotFoundException
     */
    private void readRecords(Hotel hotel) throws IOException {
        Scanner scan = new Scanner(new File("records.csv"));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] lineArray = line.split(",");
            if (guest.getctNum().equals(lineArray[1])) {
                guest.setRoomNum(Integer.parseInt(lineArray[2]));
                guest.setPaymentAmount(Integer.parseInt(lineArray[5]));
            }
            if (lineArray[4].equals("Reserved"))
                hotel.getRooms()[Integer.parseInt(lineArray[2]) - 1].setRoomStatus(true);
            else if (lineArray[4].equals("Checked-in"))
                hotel.getRooms()[Integer.parseInt(lineArray[2]) - 1].setRoomCheck(true);
        }
        scan.close();
    }

    /**
     * write guest information record to file
     * @param room guest room
     * @throws IOException
     */
    private void writeRecords(Hotel.Room room) throws IOException {
        FileWriter pw = new FileWriter("records.csv",true);
        StringBuilder sb = new StringBuilder();
        String roomStatus = "Reserved";
        if(room.getRoomCheck())
            roomStatus = "Checked-in";
        sb.append(guest.toString()+','+guest.getRoomNum()+','+guest.getRoomType()+','+
                roomStatus+','+guest.getPaymentAmount()+'\n');
        pw.write(sb.toString());
        pw.close();
    }

    /**
     * Book room for guest by Receptionist
     * @param hotel holds room and provide accessing all rooms
     * @return true if book successfully,false otherwise
     * @throws IOException
     */
    @Override
    public boolean book(Hotel hotel) throws IOException {

        readRecords(hotel);
        if(hotel.isHotelFull(guest.getRoomType()))
            return false;

        for (int i = 0; i<hotel.getNumberOfRooms(); ++i) {
            if (guest.getRoomType().equals("Single") && !hotel.getRooms()[i].getRoomStatus() && !hotel.getRooms()[i].getRoomCheck() &&
                    hotel.getRooms()[i].getRoomType().equals("Single")) {
                guest.setRoomNum(i+1);
                hotel.getRooms()[i].setRoomStatus(true);
                writeRecords(hotel.getRooms()[i]);
                return true;
            }
            else if(guest.getRoomType().equals("Double") &&!hotel.getRooms()[i].getRoomStatus() && !hotel.getRooms()[i].getRoomCheck() &&
                    hotel.getRooms()[i].getRoomType().equals("Double")){
                guest.setRoomNum(i+1);
                hotel.getRooms()[i].setRoomStatus(true);
                writeRecords(hotel.getRooms()[i]);
                return true;
            }
        }
        return false;
    }


    /**
     * Cancel reservation for guest by Receptionist
     * @param hotel holds room and provide accessing all rooms
     * @return true if cancel successfully,false otherwise
     * @throws IOException
     */
    @Override
    public boolean cancel(Hotel hotel) throws IOException {
        readRecords(hotel);
        if(findGuest( guest.toString()+','+guest.getRoomNum()) && !hotel.getRooms()[guest.getRoomNum() - 1].getRoomCheck()){
            removeGuest(guest.toString()+','+guest.getRoomNum());
            return  true;
        }
        return false;
    }

    /**
     * Check-in reserved room by receptionist
     * @param hotel holds room and provide accessing all rooms
     * @return true if check in successfully,false otherwise
     * @throws IOException
     */
    public boolean checkIn(Hotel hotel) throws IOException {
        readRecords(hotel);
        if (findGuest(guest.toString()+','+guest.getRoomNum())) {
            if(hotel.getRooms()[guest.getRoomNum() - 1].getRoomCheck())
                return false;
            else {
                hotel.getRooms()[guest.getRoomNum() - 1].setRoomCheck(true);
                removeGuest(guest.toString()+','+guest.getRoomNum());
                //Calculate payment amount staying day * room price
                guest.setPaymentAmount(guest.getStayDay() * hotel.getRooms()[guest.getRoomNum() - 1].getPrice());
                writeRecords(hotel.getRooms()[guest.getRoomNum() - 1]);
                return true;
            }
        }
        return false;
    }

    /**
     * Check-out full room by receptionist
     * @param hotel holds room and provide accessing all rooms
     * @return true if check out successfully,false otherwise
     * @throws IOException
     */
    public boolean checkOut(Hotel hotel) throws IOException {
        readRecords(hotel);
        if(findGuest( guest.toString()+','+guest.getRoomNum()) && hotel.getRooms()[guest.getRoomNum() - 1].getRoomCheck()){
            removeGuest(guest.toString()+','+guest.getRoomNum());
            return true;
        }
        return false;
    }

    /**
     *
     * @return receptionist information
     */
    @Override
    public String toString() {
        return name+','+citizenshipNum;
    }

    /**
     * compare two receptionist
     * @param obj receptionist
     * @return true if equals,otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        Receptionist receptionist = (Receptionist) obj;
        return name.equals(receptionist.name) && citizenshipNum.equals(receptionist.citizenshipNum)
                && guest.equals(receptionist.guest);
    }

}
