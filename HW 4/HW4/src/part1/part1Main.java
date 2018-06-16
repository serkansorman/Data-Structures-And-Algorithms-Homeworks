package part1;

public class part1Main {

    public static void main(String Arg[]){

       GeneralTree<Integer> tree = new GeneralTree<>();
       //Add element to tree
        if(tree.add(1,2))  // 1 added as a root
            System.out.println("Root and child added");
        if(tree.add(1,4))
            System.out.println("Child added");
        if(tree.add(1,7))
            System.out.println("Child added");
        if(tree.add(1,10))
            System.out.println("Child added");
        if(tree.add(2,3))
            System.out.println("Child added");
        if(tree.add(2,6))
            System.out.println("Child added");
        if(tree.add(2,12))
            System.out.println("Child added");
        if(tree.add(3,5))
            System.out.println("Child added");
        if(tree.add(3,11))
            System.out.println("Child added");
        if(tree.add(3,13))
            System.out.println("Child added");
        if(tree.add(5,8))
            System.out.println("Child added");
        if(tree.add(7,9))
            System.out.println("Child added");
        if(tree.add(7,14))
            System.out.println("Child added");
        if( ! tree.add(24,15))  //treede olmayan parenta child ekleme denemesi
            System.out.println("Parent can not found");

        StringBuilder sb = new StringBuilder();

        // Eleman bulunana kadar level order gezilir ve eleman bulunduğunda return edilir
        if(tree.levelOrderSearch(6,sb).toString().equals("6")) {
            System.out.println("############ Level Order Traverse for 6 #############");
            System.out.println(sb);
        }

        sb = new StringBuilder();
        // Eleman bulunamadığı için son node a kadar level order gezilir ve ardından null return edilir.
        if(tree.levelOrderSearch(99,sb) == null) {
            System.out.println("############ Full Level Order Traverse #############");
            System.out.println(sb);
        }

        sb = new StringBuilder();
        // Eleman bulunamadığı için roota a kadar post order gezilir ve ardından null return edilir.
        if(tree.postOrderSearch(25,sb) == null) {
            System.out.println("############ Full Post Order Traverse #############");
            System.out.println(sb);
        }

        System.out.println("############   Pre order Traverse   #############");
        System.out.println(tree);

    }
}
