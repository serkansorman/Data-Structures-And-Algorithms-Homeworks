package Q2;

public class Main {

    public static void main(String arg[]){

        RecursiveChainingHashSet<Integer> hashSet = new RecursiveChainingHashSet<>(11);
        RecursiveChainingHashSet<Integer> hashSet2 = new RecursiveChainingHashSet<>(5);

        System.out.println("\n\t\t################# FIRST TABLE ##################");
        if(hashSet.add(22))
            System.out.println("Key 22 added");
        if(hashSet.add(11))
            System.out.println("Key 11 added");
        if(hashSet.add(33))
            System.out.println("Key 33 added");
        if(hashSet.add(4))
            System.out.println("Key 4 added");
        if(hashSet.add(15))
            System.out.println("Key 15 added");
        if(hashSet.add(7))
            System.out.println("Key 7 added");
        if(hashSet.add(18))
            System.out.println("Key 18 added");
        if(hashSet.add(29))
            System.out.println("Key 29 added");
        if(hashSet.add(46))
            System.out.println("Key 46 added");
        if(hashSet.add(30))
            System.out.println("Key 30 added");

        hashSet.showTable();
        System.out.println("Table size: "+hashSet.size());

        if(hashSet.contains(22))
            System.out.println("Table contains Key 22");
        if(hashSet.remove(22))
            System.out.println("Key 22 is removed");
        if( ! hashSet.contains(22))
            System.out.println("Key 22 can not found");
        if(!hashSet.add(33))
            System.out.println("Key 33 has already added to table");

        hashSet.showTable();
        System.out.println("Table size: "+hashSet.size());



        System.out.println("\n\t\t################# SECOND TABLE ##################");
        if(hashSet2.add(12))
            System.out.println("Key 12 added");
        if(hashSet2.add(22))
            System.out.println("Key 22 added");
        if(hashSet2.add(42))
            System.out.println("Key 42 added");
        if(hashSet2.add(112))
            System.out.println("Key 112 added");
        if(hashSet2.add(15))
            System.out.println("Key 15 added");
        if(hashSet2.add(20))
            System.out.println("Key 20 added");
        if(hashSet2.add(10))
            System.out.println("Key 10 added");
        if(hashSet2.add(9))
            System.out.println("Key 9 added");
        if(hashSet2.add(24))
            System.out.println("Key 24 added");
        if(hashSet2.add(64))
            System.out.println("Key 64 added");


        hashSet2.showTable();
        System.out.println("Table size: "+hashSet2.size());


        if(hashSet2.remove(9))
            System.out.println("Key 9 is removed");
        if(hashSet2.remove(42))
            System.out.println("Key 42 is removed");
        if(hashSet2.add(19))
            System.out.println("Key 19 added");
        if(!hashSet2.add(12))
            System.out.println("Key 12 has already added to table");


        hashSet2.showTable();
        System.out.println("Table size: "+hashSet2.size());

    }
}
