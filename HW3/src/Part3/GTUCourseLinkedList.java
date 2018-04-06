package Part3;

import Part1.Course;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

/**
 * Includes courses which are link together as single linked list and
 * courses in same semester are also linked together as circular list
 */
public class GTUCourseLinkedList {

    /**
     * head of list
     */
    private Node head;
    private int size;

    GTUCourseLinkedList(){
        head = null;
        size = 0;
    }

    /**
     *
     * @return size of list
     */
    public int size(){
        return size;
    }

    /**
     * Add given course to the end of the list
     * @param data Part1.Course
     */
    public void add(Course data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
        }
        else{
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
            linkSameSemesterNode(); //Her eleman eklemenin ardından aynı dönem kurlslar birbirine bağlanır.
        }
        ++size;
    }

    /**
     * Add given course to the given index
     * @param index index of list
     * @param c Part1.Course
     * @throws InvalidParameterException if index is invalid
     */
    public void add(int index,Course c)throws IndexOutOfBoundsException{
        if(index <= size && index >=0){
            Node current = head;
            Node newNode = new Node(c);
            if(index == 0){
                newNode.next = head;
                head = newNode;
                linkSameSemesterNode();
                return;
            }
            while (index - 1 > 0) {
                current = current.next;
                --index;
            }
            newNode.next = current.next;
            current.next = newNode;
            linkSameSemesterNode();
            return;

        }
        throw new IndexOutOfBoundsException("Invalid Index");
    }

    /**
     * Remove course on given index
     * @param index index of element to be removed
     * @return to be deleted element
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Course remove(int index) throws IndexOutOfBoundsException {
        if(head == null)
            throw new InvalidParameterException("Empty List Error");
        else if(index < size && index >=0){
            Node current = head;
            if(index == 0){
                head = current.next;
                linkSameSemesterNode();
                return current.data;
            }
            while (index - 1 > 0) {
                current = current.next;
                --index;
            }
            Node toBeDeleted = current.next;
            current.next= current.next.next;
            linkSameSemesterNode();
            return toBeDeleted.data;
        } else
            throw new IndexOutOfBoundsException("Invalid Index");
    }

    /**
     * Remove given course from the list
     * @param c Part1.Course
     * @return course to be removed
     */
    public Course remove(Course c){
        if(head == null)
            throw new InvalidParameterException("Empty List Error");
        else if(! contains(c))
            throw new NoSuchElementException();
        Node current = head;
        if(head.data.equals(c)){
            head = current.next;
            linkSameSemesterNode();
            return current.data;
        }
        while ( ! current.next.data.equals(c)) {
            current = current.next;
        }
        Node toBeDeleted = current.next;
        current.next= current.next.next;
        linkSameSemesterNode();
        return toBeDeleted.data;
    }

    /**
     * Checks the given course is in list
     * @param c Part1.Course
     * @return true if finds the course
     */
    private boolean contains(Course c) {
        Node current = head;
        while(current != null){
            if(current.data.equals(c))
                return true;
            current = current.next;
        }
        return false;
    }

    /**
     * Links courses in same semester as a circular list
     */
    private void linkSameSemesterNode(){
        boolean isFirstSameNode = true;
        Node current = head;
        Node temp = new Node();
        Node sameSemesterHead = new Node();

        for(int i=1; i<=8; ++i) {  // 8 dönem var
            while (current != null) {
                if (current.data.getSemester() == i) { //Her dönem için tüm derslerin dönemini kontrol et
                    if (isFirstSameNode) {
                        temp = current;
                        sameSemesterHead = current;  // Dönem derslerinin ilk elemanını o dönemin circular listi için head olarak tut
                        sameSemesterHead.isHeadSemester = true; // Bu nodun dönem başı olduğunu belirt
                        isFirstSameNode = false;
                    }
                    else{ // Geriye kalan aynı dönemdeki dersleri birbirine bağla
                        temp.nextSemesterNode = current;
                        temp = current; //Son kaldığın dönem kursunu tut
                    }
                }
                current = current.next;
            }
            temp.nextSemesterNode = sameSemesterHead; // Aynı dönemde olan kursların son elemanını ilk elemana bağla(Circular)
            current = head; //Her dönem kontrolünün sonunda baştan başlamak için currenti head yap
            isFirstSameNode = true;
        }
    }

    @Override
    public String toString() {
        String result = "";
        Node current = head;
        while(current != null){
            result += current.data;
            current = current.next;
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        GTUCourseLinkedList list = (GTUCourseLinkedList) obj;
        return list.toString().equals(this.toString());
    }

    /**
     * Part3.GTUCourseLinkedList consists of Nodes and Nodes are linked together
     */
    private class Node{
        /**
         * next linked node of current node
         */
        private Node next;
        /**
         * next same samester course node
         */
        private Node nextSemesterNode;
        /**
         * Indicates that node is head of semester circular list
         */
        private boolean isHeadSemester;
        /**
         * Data of course
         */
        private Course data;

        Node(){
            next = null;
            nextSemesterNode = null;
            isHeadSemester = false;
        }

        /**
         *
         * @param data Part1.Course to be inserted to node
         */
        Node(Course data){
            this.data = data;
            next = null;
            nextSemesterNode = null;
            isHeadSemester = false;
        }
    }

    /**
     * Provides traversing on list
     */
    public class LinkedListIterator{
        /**
         * Holds next node of current node
         */
        private Node nextNode;

        /**
         * Next node points that head of list after the ıterator created
         */
        LinkedListIterator() {
            nextNode = head;
        }

        /**
         * Checks the end of the list
         * @return true if next node is not null
         */
        public boolean hasNext(){
            return nextNode != null;
        }

        /**
         * Checks the end of the circular semester list.
         * @return true if next node is not head of circular list
         */
        public boolean hasNextSemester(){
            return ! nextNode.isHeadSemester;
        }

        /**
         * Gets next course in list
         * @return next course on the list
         * @throws NoSuchElementException if iterator reaches end of the list
         */
        public Course next() throws NoSuchElementException {
            if (!hasNext())
                throw new NoSuchElementException();
            Course val = nextNode.data;
            nextNode = nextNode.next;
            return val;
        }

        /**
         * Gets next course in same semester
         * @return next course in same semester
         */
        public Course nextInSemester(){
            Course val = nextNode.data;
            nextNode = nextNode.nextSemesterNode;
            return val;
        }
    }
}
