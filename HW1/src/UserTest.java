
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Serkan Sorman
 */
class UserTest {

    Guest guest1 = new Guest("Fatih Beyaz","21564899","Double",30);
    Hotel hotel = new Hotel("Hilton Hotel",30);


    @Test
    void testFindAndRemoveGuest() throws IOException {

        boolean expected=false;
        boolean actual;

        guest1.book(hotel);

        //Try to find guest with wrong citizenship number
        actual = guest1.findGuest("Fatih Beyaz"+','+"11111111");
        assertEquals(expected,actual);

        //Try to remove guest with wrong citizenship number
        actual = guest1.removeGuest("Fatih Beyaz"+','+"11111111");
        assertEquals(expected,actual);

        //Try to find guest with true ctNum
        actual = guest1.findGuest("Fatih Beyaz"+','+"21564899");
        expected = true;
        assertEquals(expected,actual);

        //Try to remove guest with true ctNum
        actual = guest1.removeGuest("Fatih Beyaz"+','+"21564899");
        assertEquals(expected,actual);

    }

}