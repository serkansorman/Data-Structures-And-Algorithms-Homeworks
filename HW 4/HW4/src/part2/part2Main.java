package part2;

import java.security.InvalidParameterException;
import java.util.Vector;

public class part2Main {


    //Helper add vector methods
    public static Vector<Integer> addItems(int x,int y){
        Vector<Integer> data = new Vector<>();
        data.add(x);
        data.add(y);
        return data;
    }

    public static Vector<Double> addItems(double x,double y,double z){
        Vector<Double> data = new Vector<>();
        data.add(x);
        data.add(y);
        data.add(z);
        return data;
    }

    public static void main(String Arg[]){

        try {

            MultiDimensionalSearchTree<Integer> intTree = new MultiDimensionalSearchTree(2);
            MultiDimensionalSearchTree<Double> doubleTree = new MultiDimensionalSearchTree(); //Default dimension is 3

            System.out.println("############ Double Three Dimension Tree #############");
            doubleTree.add(addItems(40,45,30));
            doubleTree.add(addItems(15,72.2,24.8));
            doubleTree.add(addItems(70,10,18));
            doubleTree.add(addItems(18,72.4,26.7));
            doubleTree.add(addItems(66,9.8,29));
            doubleTree.add(addItems(85,90,12));
            doubleTree.add(addItems(20,80,25));
            doubleTree.add(addItems(52,5.5,29.5));

            System.out.println(doubleTree);

            System.out.println("############ Integer Two Dimension Tree #############");
            intTree.add(addItems(40,45));
            intTree.add(addItems(15,70));
            intTree.add(addItems(70,10));
            intTree.add(addItems(69,50));
            intTree.add(addItems(66,85));
            intTree.add(addItems(85,90));

            System.out.println(intTree);


            if(intTree.contains(addItems(66,85)))
                System.out.println("Check (66,85): "+intTree.find(addItems(66,85)));

            if( ! intTree.contains(addItems(99,99)))
                System.out.println("Check (99,99): "+intTree.find(addItems(99,99)));

            System.out.println("Deleted Node: "+intTree.delete(addItems(15,70)));
            System.out.println("############ Integer Two Dimension Tree After (15,70) deleted #############");
            System.out.print(intTree);

            if(intTree.remove(addItems(40,45))) {
                System.out.println("############ Integer Two Dimension Tree After Root (40,45) deleted #############");
                System.out.print(intTree);
            }
        }catch (InvalidParameterException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
