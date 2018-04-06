import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Serkan Sorman
 */
class GuestTest {


    Guest guest1;
    Guest guest2;
    Hotel hotel = new Hotel("Sorman Hotel",30);

    @BeforeEach
    public void setUp() throws Exception {
        clearRecords();
        guest1 = new Guest("Betül Küçükşahin","56845892","Single",5);
        guest2 = new Guest("Fatma Aslan","12546987","Single",10);
    }

    @AfterEach
    public void tearDown(){
        guest1=null;
        guest2=null;
    }

    //Clear file before each test run
    public static void clearRecords() throws IOException {
        FileWriter fwOb = new FileWriter("records.csv", false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }

    @Test
    void testBook() throws IOException {

        boolean expected=true;
        boolean actual;

        //Book room
        actual = guest1.book(hotel);
        assertEquals(expected,actual);

        //Reserve all single room
        for(int i=0; i<14;++i)
            guest1.book(hotel);

        //Try book room after all single rooms are reserved
        actual = guest1.book(hotel);
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
        guest1.book(hotel);

        //Cancel room
        actual = guest1.cancel(hotel);
        assertEquals(expected,actual);

        //Try cancel not reserved room
        actual = guest2.cancel(hotel);
        expected=false;
        assertEquals(expected,actual);
    }
}