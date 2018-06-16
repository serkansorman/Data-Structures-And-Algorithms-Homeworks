package part2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static part2.part2Main.addItems;

class MultiDimensionalSearchTreeTest {

    MultiDimensionalSearchTree<Integer> intTree = new MultiDimensionalSearchTree(2);

    @AfterEach
    void tearDown() {
        intTree = new MultiDimensionalSearchTree(2);
    }

    @Test
    void add() {

        boolean expected;
        boolean actual;

        //Try to add element with 3 dimension
        try{
            intTree.add(addItems(40,45,24));
        }catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }

        //Try to add element
        if(intTree.add(addItems(20,12)))
            System.out.println("Element (20,12) added as Root!");

        if(intTree.add(addItems(18,12)))
            System.out.println("Element (18,12) added left of root!");

        if(intTree.add(addItems(24,15)))
            System.out.println("Element (24,15) added right of the (18,12)");

        //Try to add same element
        expected = false;
        actual = intTree.add(addItems(20,12));
        assertEquals(expected,actual);


        System.out.println(intTree);

    }

    @Test
    void contains() {

        boolean expected = true;
        boolean actual;

        intTree.add(addItems(20,12));
        intTree.add(addItems(18,12));
        intTree.add(addItems(24,15));

        //Try to find  element
        actual = intTree.contains(addItems(24,15));
        assertEquals(expected,actual);

        //Try to find element that is not in tree
        expected = false;
        actual = intTree.contains(addItems(55,75));
        assertEquals(expected,actual);



    }

    @Test
    void find() {
        intTree.add(addItems(20,12));
        intTree.add(addItems(18,12));
        intTree.add(addItems(24,15));

        //Try to find  element
        if(intTree.find(addItems(18,12)) != null)
            System.out.println("Element (18,12) is found !");

        //Try to find element that is not in tree
        if(intTree.find(addItems(30,20)) == null)
            System.out.println("Element (30,20) can not found !");

    }

    @Test
    void delete() {

        intTree.add(addItems(20,12));
        intTree.add(addItems(18,12));
        intTree.add(addItems(24,15));

        //Try delete element
        if(intTree.delete(addItems(18,12)) != null) {
            System.out.println("Element (18,12) deleted !");
            System.out.println(intTree);
        }

        //Try delete element that is not in tree
        if(intTree.delete(addItems(30,20)) == null){
            System.out.println("Element (30,20) can not found !");
            System.out.println(intTree);
        }
    }

    @Test
    void remove() {

        boolean expected = true;
        boolean actual;

        intTree.add(addItems(20,12));
        intTree.add(addItems(18,12));
        intTree.add(addItems(24,15));

        //Try remove element
        actual = intTree.remove(addItems(24,15));
        assertEquals(expected,actual);

        //Try remove element that is not in tree
        expected = false;
        actual = intTree.remove(addItems(55,75));
        assertEquals(expected,actual);
    }
}