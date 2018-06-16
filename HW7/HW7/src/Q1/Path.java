package Q1;

import java.util.Comparator;
import java.util.Vector;

public class Path implements Comparator<Path>{
    private Vector<Integer> path;
    private double totalWeight;

    Path(){
        path = new Vector<>();
        totalWeight = 0;
    }

    Path(Vector<Integer> path, double weight) {
        this.path = (Vector<Integer>) path.clone();
        this.totalWeight = weight;
    }

    public double getWeight() {
        return totalWeight;
    }

    public Vector<Integer> getPath() {
        return path;
    }

    @Override
    public int compare(Path o1, Path o2) {
        return (int) (o1.getWeight() - o2.getWeight());
    }


}
