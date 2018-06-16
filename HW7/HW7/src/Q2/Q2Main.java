package Q2;

import Q1.Edge;
import Q1.Graph;
import Q1.ListGraph;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Queue;

import static Q1.Q1Main.*;

public class Q2Main {

    /**
     * İki nodun arasında bir yol üzerinden erişim sağlanıp
     * sağlanamayacığını kontrol eder BFS yaparak kontrol eder.
     * @param graph üzerinde gezilecek graph
     * @param source başlangıç nodeu
     * @param des bitiş nodeu
     * @return eğer iki node connected ise true return edilir.
     */
    public static boolean is_connected(Graph graph, int source, int des){

        // Geçersiz vertex
        if(source < 0 || source >= graph.getNumV() || des < 0 || des >= graph.getNumV())
            throw new InvalidParameterException("Node can not found");

        // Vertexler zaten edge oluşturuyorsa
        if(graph.isEdge(source,des))
            return true;

        ListGraph listGraph = (ListGraph) graph;
        boolean visited[] = new boolean[listGraph.getNumV()];
        Queue<Integer> queue = new LinkedList<>();

        visited[source] = true; // Vertexin ziyaret edildiğini belirt
        queue.add(source);

        while (!queue.isEmpty()) {
            source = queue.poll();
            for(Edge edge : listGraph.getEdges()[source]) { // Vertexin komşularında gezin
                int current = edge.getDest();
                if (current == des)     // bitiş vertexine ulaşıldı mı
                    return true;
                if (!visited[current]) {    // vertex daha önceden ziyaret edilmediyse queuya ekle
                    visited[current] = true;
                    queue.add(current);
                }
            }
        }
        return false;
    }

    /**
     * Undirected Graphın acyclic olup olmadığını DFS
     * yaparak kontrol eder .
     * @param graph check edilecek graph
     * @return eğer graph acyclic ise true return edilir.
     */
    public static boolean is_acyclic_graph(Graph graph) {

        boolean visited[] = new boolean[graph.getNumV()];
        for (int i = 0; i < graph.getNumV(); i++)
            visited[i] = false;

        for (int u = 0; u < graph.getNumV(); u++)
            if (!visited[u])    // Daha önceden ulaşılmış noda tekrar DFS yi engelle
                if (hasCycleUndirected(graph,u, visited, -1)) // Başlangıçta olmayan source olarak -1 yollanır.
                    return false;
        return true;
    }


    /**
     * Her node için DFS yaparak cyclic kontrolü yapan DFS metodu
     * @param graph check edilecek graph
     * @param v başlangıç nodeu
     * @param visited daha önce üzerinden geçilmiş olan nodeları belirten array
     * @param currentSource üzerinde gezinilen güncel vertexin source vertexini belirtir
     * @return eğer belirtilen başlangıç vertexi cycle oluşturuyorsa true return edilir.
     */
    private static boolean hasCycleUndirected(Graph graph,int v, boolean visited[], int currentSource) {

        ListGraph lg = (ListGraph) graph;
        visited[v] = true;

        for (Edge e : lg.getEdges()[v]) {
            if (!visited[e.getDest()]) {
                if (hasCycleUndirected(lg, e.getDest(), visited, v))
                    return true;
            }
            // Vertex daha önce erişilmemiş
            // eğer adjacent vertex güncel vertexin sourcesi değilse cycle bulunmuş olur
           else if (e.getDest() != currentSource)
                return true;
        }

        return false;
    }


    public static void main(String[] arg){

        ListGraph graph = new ListGraph(15,false);

        graph.insert(new Edge(0,1));
        graph.insert(new Edge(0,2));
        graph.insert(new Edge(0,3));
        graph.insert(new Edge(0,4));
        graph.insert(new Edge(1,5));
        graph.insert(new Edge(1,6));
        graph.insert(new Edge(2,7));
        graph.insert(new Edge(2,8));
        graph.insert(new Edge(2,9));
        graph.insert(new Edge(4,10));
        graph.insert(new Edge(5,13));
        graph.insert(new Edge(6,12));
        graph.insert(new Edge(11,14));

        plot_graph(graph);

        if(is_undirected(graph))
            System.out.println("Undirected Graph");
        else
            System.out.println("Directed Graph");

        if(is_acyclic_graph(graph))
            System.out.println("Acyclic Graph\n");
        else
            System.out.println("Cyclic Graph\n");


        try {

            if(is_connected(graph,0,12))
                System.out.println("Node 0 and 12 are connected");
            if(is_connected(graph,4,7))
                System.out.println("Node 4 and 7 are connected");
            if(is_connected(graph,5,10))
                System.out.println("Node 5 and 10 are connected");
            if(!is_connected(graph,2,11))
                System.out.println("Node 2 and 11 are not connected");
            if(is_connected(graph,10,20))
                System.out.println("Node 15 and 20 are connected");

        }catch (InvalidParameterException e){
            System.out.print(e.getMessage());
        }


    }
}
