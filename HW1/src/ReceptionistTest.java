import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Serkan Sorman
 */
class ReceptionistTest {

    Guest guest1 = new Guest("Serkan Sorman","56845892","Double",5);
    Guest guest2 = new Guest("Ayşe Kirmizi","84698521","Double",9);
    Guest guest3 = new Guest("Burak Yılmaz","52694258","Single",3);
    Hotel hotel = new Hotel("Sorman Hotel",30);
    Receptionist recptnst;


    @BeforeEach
    public void setUp() throws Exception {
       GuestTest.clearRecords();
    }

    @Test
    void testBook() throws IOException {

        boolean expected=true;
        boolean actual;

        //Book room
        recptnst = new Receptionist(guest1,"1234");
        actual = recptnst.book(hotel);
        assertEquals(expected,actual);

        //Reserve all double room
        for(int i=0; i<14;++i)
            recptnst.book(hotel);

        //Try book room after all double rooms are reserved
        actual = recptnst.book(hotel);
        expected=false;
        assertEquals(expected,actual);
        if(!actual)
            System.out.println("Sorry we have not any available "+guest1.getRoomType() +" room for you.");
    }

    @Test
    void testCancel() throws IOException {

        boolean expected=true;
        boolean actual;

        //Book room
        recptnst = new Receptionist(guest1,"1234");
        recptnst.book(hotel);

        //Cancel room
        actual = recptnst.cancel(hotel);
        assertEquals(expected,actual);

        //Try cancel not reserved room
        recptnst = new Receptionist(guest2,"1234");
        actual = recptnst.cancel(hotel);
        expected=false;
        assertEquals(expected,actual);
    }

    @Test
    void testCheckIn() throws IOException {

        boolean expected=true;
        boolean actual;

        //Book room
        recptnst = new Receptionist(guest3,"1234");
        recptnst.book(hotel);

        //Check ın room
        actual = recptnst.checkIn(hotel);
        assertEquals(expected,actual);

        //Retry check in same guest room
        actual = recptnst.checkIn(hotel);
        expected=false;
        assertEquals(expected,actual);

        //Try check in not reserved room
        recptnst = new Receptionist(guest2,"1234");
        actual = recptnst.checkIn(hotel);
        expected=false;
        assertEquals(expected,actual);
    }

    @Test
    void testCheckOut() throws IOException {

        boolean expected=true;
        boolean actual;

        //Book room
        recptnst = new Receptionist(guest2,"1234");
        recptnst.book(hotel);

        //Check ın room
        recptnst.checkIn(hotel);

        //Check out room
        actual = recptnst.checkOut(hotel);
        assertEquals(expected,actual);

       //Book room
        recptnst = new Receptionist(guest1,"1234");
        recptnst.book(hotel);

        //Try check out reserved room
        actual = recptnst.checkOut(hotel);
        expected=false;
        assertEquals(expected,actual);


    }
}