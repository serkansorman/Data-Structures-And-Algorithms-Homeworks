package Q1;

public class Main {

    public static void main(String arg[]){
        
        DoubleHashingMap<Integer,String> tableOne = new DoubleHashingMap<>(7);
        DoubleHashingMap<Integer,Character> tableTwo = new DoubleHashingMap<>(11);

        System.out.println("\n########### Table One ##########");
        tableOne.put(14,"Ali");
        tableOne.put(21,"Fatma");
        tableOne.put(15,"Ayse");
        tableOne.put(29,"Burak");
        tableOne.put(12,"Mert");

        tableOne.showTable();
        System.out.println("Table size: "+tableOne.size());

        String value1 = tableOne.get(15);
        String value2 = tableOne.get(29);
        if(tableOne.remove(15).equals(value1))
            System.out.println("\nKey 15 is removed");
        if(tableOne.remove(29).equals(value2))
            System.out.println("Key 29 is removed");

        tableOne.showTable();
        System.out.println("Table size: "+tableOne.size());

        if(tableOne.put(33,"Mustafa") == null)
            System.out.println("\nKey 33 is added and Table is rehashing...");

        tableOne.showTable();
        System.out.println("Table size: "+tableOne.size());

        if(tableOne.remove(21).equals("Fatma"))
            System.out.println("\nKey 21 is removed");
        if(tableOne.remove(12).equals("Mert"))
            System.out.println("Key 12 is removed");
        if(tableOne.remove(14).equals("Ali"))
            System.out.println("Key 14 is removed");
        if(tableOne.remove(33).equals("Mustafa"))
            System.out.println("Key 33 is removed");


        tableOne.showTable();
        System.out.println("Table size: "+tableOne.size());

        if(tableOne.isEmpty())
            System.out.println("Table is empty !!!");




        System.out.println("\n########### Table Two ##########");
        tableTwo.put(25,'A');
        tableTwo.put(47,'B');
        tableTwo.put(12,'C');
        tableTwo.put(56,'D');
        tableTwo.put(36,'E');
        tableTwo.put(33,'F');
        tableTwo.put(22,'G');


        tableTwo.showTable();
        System.out.println("Table size: "+tableTwo.size());

        if(tableTwo.remove(22).equals('G'))
            System.out.println("\nKey 22 is removed");
        if(tableTwo.put(77,'X') == null)
            System.out.println("Key 77 is added");
        if(tableTwo.put(47,'Z').equals('B'))
            System.out.println("New value added to key 47");

        tableTwo.showTable();
        System.out.println("Table size: "+tableTwo.size());

    }
}
