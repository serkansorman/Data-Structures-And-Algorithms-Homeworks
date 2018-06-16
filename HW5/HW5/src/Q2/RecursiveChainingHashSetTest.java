package Q2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveChainingHashSetTest {

    RecursiveChainingHashSet<Integer> hashSet = new RecursiveChainingHashSet<>(11);

    @BeforeEach
    void setUp() {

        hashSet.add(4);
        hashSet.add(15);
        hashSet.add(7);

    }

    @AfterEach
    void tearDown() {

        hashSet = new RecursiveChainingHashSet<>(11);
    }

    @Test
    void add() {

        boolean expected = true;
        boolean actual;

        actual = hashSet.add(21);
        assertEquals(expected,actual);

        expected = false;
        actual = hashSet.add(21);
        assertEquals(expected,actual);
    }

    @Test
    void remove() {

        boolean expected = true;
        boolean actual;

        actual = hashSet.remove(7);
        assertEquals(expected,actual);

        expected = false;
        actual = hashSet.remove(7);
        assertEquals(expected,actual);

    }

    @Test
    void contains() {

        boolean expected = true;
        boolean actual;

        actual = hashSet.contains(4);
        assertEquals(expected,actual);

        expected = false;
        actual = hashSet.contains(99);
        assertEquals(expected,actual);
    }
}