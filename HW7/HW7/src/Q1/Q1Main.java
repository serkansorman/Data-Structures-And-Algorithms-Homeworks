package Q1;
import java.security.InvalidParameterException;
import java.util.*;

import static Q2.Q2Main.is_connected;


public class Q1Main {

    /**
     * Unweighted ya da weighted grapha göre graphı ekrana list
     * olarak bastırır.
     * @param g print edilecek graph
     */
    public static void plot_graph(Graph g){

        for(int i=0; i<g.getNumV(); ++i){
            System.out.print(i);
            for(int j=0; j<g.getNumV(); ++j) {
                if (g.isEdge(i, j) ){
                    if(g.isDirected())
                        System.out.print(" -" + g.getEdge(i, j).getWeight() + "-> " + g.getEdge(i, j).getDest());
                    else
                        System.out.print(" -> " + g.getEdge(i, j).getDest());
                }
            }
            System.out.println();
        }
        System.out.println();

    }

    /**
     * Graphın undirected olup olmadığını zıt yönde ve aynı weighte
     * edgeler bulunup bulunmadığına bakarak kontrol eder.
     * @param g check edilecek graph
     * @return undirected ise true return edilir.
     */
    public static boolean is_undirected(Graph g){

        for(int i=0; i<g.getNumV(); ++i){
            for(int j=0; j<g.getNumV(); ++j) {
                // var olan edgenin tersinin olup olmadığına bak,varsa weighti aynı mı diye kontrol et
                if (g.isEdge(i, j)) {
                    if(!g.isEdge(j, i) || ( g.getEdge(i, j).getWeight() != g.getEdge(j, i).getWeight() ) )
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Directed Graphın acyclic olup olmadığını DFS
     * yaparak kontrol eder .
     * @param graph check edilecek graph
     * @return eğer graph acyclic ise true return edilir.
     */
    private static boolean is_acyclic_graph(Graph graph) {

        boolean []visited = new boolean[graph.getNumV()];
        for (int i = 0; i < graph.getNumV(); ++i)
            visited[i] = false;

        for (int i = 0; i < graph.getNumV(); ++i) // Tüm vertexler için DFS yap
            if (hasCycleDirected(graph,i, visited))
                return false;
        return true;


    }

    /**
     * Directed graph için her node için DFS yaparak cyclic
     * kontrolü yapan DFS metodu
     * @param graph check edilecek graph
     * @param v başlangıç nodeu
     * @param visited daha önce üzerinden geçilmiş olan nodeları belirten array
     * @return eğer belirtilen başlangıç nodeu cycle oluşturuyorsa true return edilir.
     */
    private static boolean hasCycleDirected(Graph graph,int v, boolean []visited) {
        ListGraph lg = (ListGraph) graph;
        if (visited[v]) // Daha önceden ulaşılmış bir node gelirse cycle vardır.
            return true;

        visited[v] = true;
        for (Edge nextNode : lg.getEdges()[v])
            if (hasCycleDirected(lg,nextNode.getDest(), visited))
                return true;
        /* Farklı yönlerde edgelerden oluşan loopun cycle
           olarak görülmesini engellemek için son gezinilen vertexi gezilmemiş yap*/
        visited[v] = false;
        return false;
    }

    /**
     * BFS yapılarak iki node arasındaki en kısa yolu bulur
     * ve toplam weighti ekrana basar.
     * @param graph üzerinde traverse yapılacak graph
     * @param s başlangıç nodeu
     * @param d bitiş nodeu
     * @return eğer nodelar bağlıysa iki node arasında bulunan yolu vector olarak return eder
     * yoksa boş vector return edilir.
     */
    public static Vector<Integer> shortest_path(ListGraph graph,int s, int d) {

        boolean[] isVisited = new boolean[graph.getNumV()];
        ArrayList<Integer> pathList = new ArrayList<>();
        Vector<Path> paths = new Vector<>();
        double sum = 0.0;

        if(!is_connected(graph,s,d)) // Nodelar connected değilse boş vector return et
            return new Vector<>();

        pathList.add(s);
        getPaths(graph,s, d, isVisited, pathList,paths,sum);// BFS ile iki node arasındaki tüm pathleri elde et

        paths.sort(new Path());// Pathleri sırala
        System.out.println("Total weight: "+paths.get(0).getWeight());// En kısa yolu göster
        return paths.get(0).getPath();
    }

    /**
     * BFS ile belirtilen nodelar arasındaki yolları bulur. weightlerini toplar
     * ve bunları bir arrayliste ekler
     * @param graph üzerinde gezinilen graph
     * @param current üzerinde gezinilen güncel vertex
     * @param end   varılacak vertex
     * @param isVisited daha önce üzerinden geçilmiş olan nodeları belirten array
     * @param localPathList gezinilen tüm nodeların tutulup silindiği arraylist
     * @param paths belirtilen iki node arasındaki tüm pathleri içeren array
     * @param totalWeight iki node arasında bulunan bir pathin toplam weighti
     */
    private static void getPaths(ListGraph graph,Integer current, Integer end, boolean[] isVisited,
                                      ArrayList<Integer> localPathList, Vector<Path> paths, double totalWeight) {
        isVisited[current] = true;
        if (current.equals(end))
            paths.add(new Path(new Vector<>(localPathList),totalWeight));

        for (Edge e : graph.getEdges()[current]) {
            if (!isVisited[e.getDest()]) {

                localPathList.add(e.getDest());
                totalWeight += e.getWeight(); // iki node arasındaki yolun weightini hesapla
                getPaths(graph,e.getDest(),end, isVisited, localPathList,paths,totalWeight);

                /*Başka bir yola geçildiğinde gezilen nodeun devamında bitiş nodu yoksa
                    o nodun weight değerini çıkar ve gidilen yoldan sil
                 */
                totalWeight -= e.getWeight();
                localPathList.remove(new Integer(e.getDest()));
            }
        }
        isVisited[current] = false;
    }


    public static void main(String[] arg){

        ListGraph graph = new ListGraph(10,true);

        Random randWeight = new Random();

        graph.insert(new Edge(0,1,randWeight.nextInt(10)+2));
        graph.insert(new Edge(0,2,randWeight.nextInt(10)+2));
        graph.insert(new Edge(0,8,randWeight.nextInt(10)+2));
        graph.insert(new Edge(1,2,randWeight.nextInt(10)+2));
        graph.insert(new Edge(1,3,randWeight.nextInt(10)+2));
        graph.insert(new Edge(1,4,randWeight.nextInt(10)+2));
        graph.insert(new Edge(2,8,randWeight.nextInt(10)+2));
        graph.insert(new Edge(2,4,randWeight.nextInt(10)+2));
        graph.insert(new Edge(3,4,randWeight.nextInt(10)+2));
        graph.insert(new Edge(3,5,randWeight.nextInt(10)+2));
        graph.insert(new Edge(3,6,randWeight.nextInt(10)+2));
        graph.insert(new Edge(4,5,randWeight.nextInt(10)+2));
        graph.insert(new Edge(4,8,randWeight.nextInt(10)+2));
        graph.insert(new Edge(4,9,randWeight.nextInt(10)+2));
        graph.insert(new Edge(5,7,randWeight.nextInt(10)+2));
        graph.insert(new Edge(5,9,randWeight.nextInt(10)+2));
        graph.insert(new Edge(6,5,randWeight.nextInt(10)+2));
        graph.insert(new Edge(6,7,randWeight.nextInt(10)+2));
        graph.insert(new Edge(8,9,randWeight.nextInt(10)+2));
        graph.insert(new Edge(9,7,randWeight.nextInt(10)+2));

        plot_graph(graph);


        try {
            if(is_undirected(graph))
                System.out.println("Undirected Graph");
            else
                System.out.println("Directed Graph");

            if(is_acyclic_graph(graph))
                System.out.println("Acyclic Graph\n");
            else
                System.out.println("Cyclic Graph\n");

            System.out.println("Shortest path between 1 - 9: "+shortest_path(graph,1,9)+'\n');
            System.out.println("Shortest path between 0 - 7: "+shortest_path(graph,0,7)+'\n');
            System.out.println("Shortest path between 1 - 5: "+shortest_path(graph,1,5)+'\n');

        } catch (InvalidParameterException e){
            System.out.print(e.getMessage());
        }



    }
}
