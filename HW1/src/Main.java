import java.io.IOException;
import java.security.InvalidParameterException;

public class Main {

    public  static  void main(String argStr[]){
        try {
            Hotel hotel = new Hotel("Sorman Hotel",50);
            System.out.println("\n\t######\t\tWelcome to "+hotel.getName()+"\t\t######\t");

            //Creates guests
            User user1 = new Guest("Mehmet Demir","26452698","Single",2);
            User user2 = new Guest("Ayşe Kirmizi","84698521","Double",9);
            Guest guest1 = new Guest("Serkan Sorman","56845892","Double",5);
            Guest guest4 = new Guest("Ali Yilmaz","34562897","Double",1);
            Guest guest5 = new Guest("Fatma Aslan","12546987","Single",10);
            Guest guest6 = new Guest("Burak Yılmaz","52694258","Single",3);

            //Reserve room for guests
            System.out.println("\n\t######\tSCENARIO 1 - GUESTS BOOK ROOMS\t######\t");
            boolean isRoomReserved;
            isRoomReserved = guest1.book(hotel);
            if(isRoomReserved)
                System.out.println( guest1.getRoomType()+" Room " +  guest1.getRoomNum() +
                        " has been reserved for "+guest1+" "+guest1.getStayDay()+" Day");
            isRoomReserved = guest1.book(hotel);
            if(isRoomReserved)
                System.out.println( guest1.getRoomType()+" Room " +  guest1.getRoomNum() +
                        " has been reserved for "+guest1+" "+guest1.getStayDay()+" Day");
            isRoomReserved = user1.book(hotel);
            if(isRoomReserved) {
                Guest guest2 = (Guest) user1;
                System.out.println(guest2.getRoomType() + " Room " + guest2.getRoomNum() +
                        " has been reserved for " + guest2 + " " + guest2.getStayDay() + " Day");
            }
            isRoomReserved = user2.book(hotel);
            if(isRoomReserved) {
                Guest guest3 = (Guest) user2;
                System.out.println(guest3.getRoomType() + " Room " + guest3.getRoomNum() +
                        " has been reserved for " + guest3 + " " + guest3.getStayDay() + " Day");
            }
            isRoomReserved = guest4.book(hotel);
            if(isRoomReserved)
                System.out.println( guest4.getRoomType()+" Room " +  guest4.getRoomNum() +
                        " has been reserved for "+guest4+" "+guest4.getStayDay()+" Day");



            System.out.println("\n\t######\tSCENARIO 2 - GUESTS CANCEL RESERVATIONS\t######\t");
            boolean isReservationCancel;
            isReservationCancel = user1.cancel(hotel);
            if(isReservationCancel) {
                Guest guest2 = (Guest) user1;
                System.out.println(guest2.getName() + "'s Room " + guest2.getRoomNum() + " reservation cancelled");
            }
            isRoomReserved = user2.cancel(hotel);
            if(isRoomReserved) {
                Guest guest3 = (Guest) user2;
                System.out.println(guest3.getName() + "'s Room " + guest3.getRoomNum() + " reservation cancelled");
            }
            //Retry cancel cancelled reservation
            isRoomReserved = user2.cancel(hotel);
            if(! isRoomReserved) {
                Guest guest3 = (Guest) user2;
                System.out.println(guest3 + " has not any reservation to cancel");
            }


            System.out.println("\n\t######\tSCENARIO 3 - RECEPTIONIST BOOK ROOM FOR GUESTS\t######\t");
            User user3 = new Receptionist(guest5,"1234");
            isRoomReserved = user3.book(hotel);
            if(isRoomReserved)
                System.out.println( guest5.getRoomType()+" Room " +  guest5.getRoomNum() +
                        " has been reserved for "+guest5+" "+guest5.getStayDay()+" Day");
            user3 = new Receptionist(guest6,"1234");
            isRoomReserved = user3.book(hotel);
            if(isRoomReserved)
                System.out.println( guest6.getRoomType()+" Room " +  guest6.getRoomNum() +
                        " has been reserved for "+guest6+" "+guest6.getStayDay()+" Day");



            System.out.println("\n\t######\tSCENARIO 4 - RECEPTIONIST CANCEL RESERVATIONS\t######\t");
            user3 = new Receptionist(guest4,"1234");
            isReservationCancel = user3.cancel(hotel);
            if(isReservationCancel)
                System.out.println( guest4.getName()+"'s Room " +  guest4.getRoomNum() + " reservation cancelled");
            isRoomReserved = user3.cancel(hotel);
            //Retry cancel cancelled reservation
            if( ! isRoomReserved)
                System.out.println( guest4+" has not any reservation to cancel");



            System.out.println("\n\t######\tSCENARIO 5 - RECEPTIONIST CHECK-IN ROOMS\t######\t");
            boolean isCheckedIn;
            user3 = new Receptionist(guest1,"1234");
            Receptionist recptnst = (Receptionist) user3;
            isCheckedIn = recptnst.checkIn(hotel);
            if (isCheckedIn)
                System.out.println("Room " + guest1.getRoomNum() + " checked in for " + guest1);
            recptnst = new Receptionist(guest6,"1234");
            isCheckedIn = recptnst.checkIn(hotel);
            if (isCheckedIn)
                System.out.println("Room " + guest6.getRoomNum() + " checked in for " + guest6);
            isCheckedIn = recptnst.checkIn(hotel);
            //Try check-in for full room
            if (!isCheckedIn)
                System.out.println("Room " + guest6.getRoomNum() + " was previously checked-in by " + guest6);
            recptnst = new Receptionist(guest4,"1234");
            isCheckedIn = recptnst.checkIn(hotel);
            //Try check-in for non exist reservation record
            if (!isCheckedIn)
                System.out.println("Reservation record is not found for " + guest4);



            System.out.println("\n\t######\tSCENARIO 6 - RECEPTIONIST AND GUEST TRY CANCEL FULL ROOM\t######\t");
            boolean isCheckedCancel;
            recptnst = new Receptionist(guest6,"1234");
            isCheckedCancel = recptnst.cancel(hotel);
            if(! isCheckedCancel)
                System.out.println( guest6.getName()+"'s Room " +  guest6.getRoomNum() + " can not cancelled");
            isCheckedCancel = guest1.cancel(hotel);
            if(! isCheckedCancel)
                System.out.println( guest1.getName()+"'s Room " +  guest1.getRoomNum() + " can not cancelled");



            System.out.println("\n\t######\tSCENARIO 7 - RECEPTIONIST CHECK-OUT ROOMS\t######\t");
            boolean isCheckedOut;
            recptnst = new Receptionist(guest1,"1234");
            isCheckedOut = recptnst.checkOut(hotel);
            if(isCheckedOut) {
                System.out.println("Room " + guest1.getRoomNum() + " checked out for " + guest1);
                System.out.println("Payment amount is " + guest1.getPaymentAmount() + "$\n");
            }
            recptnst = new Receptionist(guest6,"1234");
            isCheckedOut = recptnst.checkOut(hotel);
            if(isCheckedOut) {
                System.out.println("Room " + guest6.getRoomNum() + " checked out for " + guest6);
                System.out.println("Payment amount is " + guest6.getPaymentAmount() + "$\n");
            }
            recptnst = new Receptionist(guest5,"1234");
            isCheckedOut = recptnst.checkOut(hotel);
            //Try check out for non exist check in record
            if( ! isCheckedOut ) {
                System.out.println("Check-in record not found for "+guest5);
            } System.out.println("\n\t######\t\tEND OF THE ALL TESTS\t\t######\t");

        }catch (IOException | InvalidParameterException e){
            System.out.print(e.getMessage());
            System.exit(1);
        }
    }
}
