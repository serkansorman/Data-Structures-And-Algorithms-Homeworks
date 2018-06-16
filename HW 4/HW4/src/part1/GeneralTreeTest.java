package part1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralTreeTest {

    GeneralTree<Integer> tree = new GeneralTree<>();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

        tree = new GeneralTree<>();
    }

    @Test
    void add() {

        boolean expected = true;
        boolean actual;

        actual = tree.add(1,2);
        assertEquals(expected,actual);

        expected = false;
        actual = tree.add(3,4);
        assertEquals(expected,actual);

    }

    @Test
    void levelOrderSearch() {

        tree.add(24,12);
        tree.add(12,6);
        tree.add(12,3);
        tree.add(6,4);
        tree.add(6,8);

        StringBuilder sb = new StringBuilder();
        if(tree.levelOrderSearch(4,sb) != null){
            System.out.println("Element 4 is found !");
            System.out.println(sb);
        }

        sb = new StringBuilder();
        if(tree.levelOrderSearch(5,sb) == null)
            System.out.println("Element 5 can not found");
    }

    @Test
    void postOrderSearch() {

        tree.add(24,12);
        tree.add(12,6);
        tree.add(12,3);
        tree.add(6,4);
        tree.add(6,8);

        StringBuilder sb = new StringBuilder();
        if(tree.postOrderSearch(24,sb) != null){
            System.out.println("Element 24 is found !");
            System.out.println(sb);
        }

        sb = new StringBuilder();
        if(tree.postOrderSearch(5,sb) == null)
            System.out.println("Element 5 can not found");
    }

    @Test
    void preOrderTraverse() {

        tree.add(24,12);
        tree.add(12,6);
        tree.add(12,3);
        tree.add(6,4);
        tree.add(6,8);

        System.out.println(tree);
    }
}