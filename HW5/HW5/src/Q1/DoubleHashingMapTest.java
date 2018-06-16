package Q1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleHashingMapTest {

    DoubleHashingMap<Integer,String> table = new DoubleHashingMap<>(7);

    @BeforeEach
    void setUp() {

        table.put(19,"Ali");
        table.put(21,"Fatma");
        table.put(15,"Ayse");
    }

    @AfterEach
    void tearDown() {

        table = new DoubleHashingMap<>(7);
    }


    @Test
    void get() {

        String expected = "Fatma";
        String actual;

        actual = table.get(21);
        assertEquals(expected,actual);


        actual = table.get(5);
        assertEquals(null,actual);
    }

    @Test
    void put() {

        String actual;
        String expected = "Ali";

        actual = table.put(19,"Burak");
        assertEquals(expected,actual);

        actual = table.put(5,"Mustafa");
        assertEquals(null,actual);


    }

    @Test
    void remove() {
        String actual;
        String expected = "Ayse";

        actual = table.remove(15);
        assertEquals(expected,actual);

        actual = table.remove(99);
        assertEquals(null,actual);

    }

}