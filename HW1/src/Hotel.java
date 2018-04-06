import java.security.InvalidParameterException;

/**
 * The hotel holds only rooms.
 */
public class Hotel {

    /**
     * Holds all rooms in hotel
     */
    private Room []rooms;
    private String name;
    private int numberOfRooms;

    /**
     * Creates rooms according to number of rooms
     * @param name hotel name
     * @param numberOfRooms number of rooms in hotel
     * @throws InvalidParameterException invalid room number
     */
    public Hotel(String name,int numberOfRooms)throws InvalidParameterException{
        this.name = name;
        if(numberOfRooms < 5 || numberOfRooms > 1000)
            throw new InvalidParameterException("Invalid room number,try between 5-1000");
        this.numberOfRooms = numberOfRooms;
        rooms = new Room[getNumberOfRooms()];
        for (int i = 0; i<getNumberOfRooms(); ++i)
            rooms[i] = new Room(i);
    }

    /**
     *
     * @return hotel name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return all rooms
     */
    public Room[] getRooms() {
        return rooms;
    }

    /**
     *
     * @return number of rooms in hotel
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Checks available rooms for user according to room type
     * @param roomType Single or Double
     * @return true if hotel has not available room for user,false otherwise
     */
    public boolean isHotelFull(String roomType){
        for (int i = 0; i<getNumberOfRooms(); ++i)
            if(!rooms[i].isReserved && !rooms[i].isFull && rooms[i].roomType.equals(roomType))
                return false;
        return true;
    }

    /**
     *
     * @return hotel information
     */
    @Override
    public String toString() {
        return name+','+numberOfRooms;
    }

    /**
     *
     * @param obj Hotel
     * @return true if equals,otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        Hotel hotel = (Hotel) obj;
        return name.equals(hotel.name) && numberOfRooms == hotel.numberOfRooms;
    }

    /**
     * Room is a inner class in hotel
     * Holds each room's information
     */
    public class Room{

        /**
         * Status of room.Reserved or not
         */
        private boolean isReserved;
        /**
         * Status of room.Full or not
         */
        private boolean isFull;
        private int roomNum;
        /**
         * Single or Double
         */
        private String roomType;
        /**
         * Single 120$,Double 200$
         */
        private int price;

        /**
         * Create rooms with room type,room number and room price
         * @param roomNum
         */
        private Room(int roomNum){
            isReserved = false;
            this.roomNum = roomNum + 1;

            if(this.roomNum % 2 == 1){
                roomType = "Single";
                price = 120;
            } else{
                roomType = "Double";
                price = 200;
            }
        }

        /**
         *
         * @return room type Single or Double
         */
        public String getRoomType(){
            return roomType;
        }

        /**
         *
         * @return true if room reserved,false otherwise
         */
        public boolean getRoomStatus(){
            return isReserved;
        }

        /**
         *
         * @return true if room full,false otherwise
         */
        public boolean getRoomCheck(){
            return isFull;
        }

        /**
         *
         * @return room number
         */
        public int getRoomNum(){
            return roomNum;
        }

        /**
         *
         * @return room price
         */
        public int getPrice(){
            return price;
        }

        /**
         *
         * @param status reserved or not
         */
        public void setRoomStatus(boolean status){
            isReserved = status;
        }

        /**
         *
         * @param status full or empty
         */
        public void setRoomCheck(boolean status){
            isFull = status;
        }

        /**
         *
         * @return Room information
         */
        @Override
        public String toString() {
            return roomType+','+roomNum+','+price;
        }

        /**
         *
         * @param obj Room
         * @return true if equals,otherwise false
         */
        @Override
        public boolean equals(Object obj) {
            Room room = (Room) obj;
            return roomType.equals(room.roomType) && roomNum == room.roomNum
                    && room.price == price;
        }
    }
}
