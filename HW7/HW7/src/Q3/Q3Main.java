package Q3;

import Q1.Edge;
import Q1.Graph;
import Q1.ListGraph;

import java.util.*;

import static Q1.Q1Main.is_undirected;
import static Q1.Q1Main.plot_graph;
import static Q2.Q2Main.is_acyclic_graph;


public class Q3Main {

    /**
     * Verilen graphın belirtilen başlangıç noktasından
     * itibaren BFS yapar gerekli nodelar silinerek spanning tree elde edilir.
     * @param graph spanning treesi elde edilecek graph
     * @param v başlangıç vertexi
     */
    public static void getSpanningTreeBFS(Graph graph,int v) {
        ListGraph listGraph = (ListGraph) graph;
        Queue<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[graph.getNumV()];
        ArrayList<Edge> backUpVisited = new ArrayList<>();

        visited[v] = true;
        queue.add(v);

        while (!queue.isEmpty()){
            v = queue.poll();
            for(Edge e : new ArrayList<>(listGraph.getEdges()[v])) { // Vertexin komşuları içinde gezin
                if (!visited[e.getDest()]) {
                    visited[e.getDest()] = true;
                    queue.add(e.getDest()); // Geçilen vertexi queuya ekle
                    backUpVisited.add((new Edge(e.getDest(),v)));  //Eklenen vertexlerin zıt yönlü olan hallerini kaydet

                    // Eğer vertexe daha önceden erişilmiş ve o vertexe bağlı edgeden daha önceden geçilmediyse edgeyi sil
                } else if(!backUpVisited.contains(new Edge(v,e.getDest())))
                    listGraph.remove(new Edge(v,e.getDest()));
            }
        }
    }

    /**
     * Verilen graphın belirtilen başlangıç noktasından
     * itibaren DFS yapar gerekli nodelar silinerek spanning tree elde edilir.
     * @param graph spanning treesi elde edilecek graph
     * @param v başlangıç vertexi
     */
    public static void  getSpanningTreeDFS(ListGraph graph,int v) {

        boolean[] visited = new boolean[graph.getNumV()];
        ArrayList<Edge> backUpVisited = new ArrayList<>();
        for (int i = 0; i < graph.getNumV(); i++)
            visited[i] = false;

        Stack<Integer> stack = new Stack<>();
        stack.push(v);

        while(!stack.isEmpty()) {
            v = stack.pop();
            ArrayList<Edge> neighbours = new ArrayList<>(graph.getEdges()[v]);
            for (Edge edge : neighbours) { // Vertexin komşuları içinde gezin
                if (!visited[edge.getDest()]) {
                    visited[edge.getDest()] = true;
                    stack.push(edge.getDest()); //Geçilen vertexi stacke ekle
                    backUpVisited.add(new Edge(edge.getDest(), v)); //Eklenen vertexlerin zıt yönlü olan hallerini kaydet
                }
                /*Eğer vertexe daha önceden erişilmiş ve o vertexe bağlı edgeden
                    daha önceden geçilmediyse ve vertexin silinecek edgeden başka edgesi varsa edgeyi sil*/
                else if(!backUpVisited.contains(new Edge(v,edge.getDest()) ) && graph.getEdges()[edge.getDest()].size() != 1 &&
                        neighbours.size() != 1)
                    graph.remove(new Edge(v,edge.getDest()));
            }
        }
    }

    public static void main(String[] arg){

        ListGraph graph = new ListGraph(10,false);

        graph.insert(new Edge(0,1));
        graph.insert(new Edge(0,2));
        graph.insert(new Edge(0,3));
        graph.insert(new Edge(1,4));
        graph.insert(new Edge(1,6));
        graph.insert(new Edge(1,2));
        graph.insert(new Edge(1,5));
        graph.insert(new Edge(1,3));
        graph.insert(new Edge(2,6));
        graph.insert(new Edge(2,7));
        graph.insert(new Edge(3,7));
        graph.insert(new Edge(3,2));
        graph.insert(new Edge(3,8));
        graph.insert(new Edge(3,9));
        graph.insert(new Edge(4,5));
        graph.insert(new Edge(5,6));
        graph.insert(new Edge(5,2));
        graph.insert(new Edge(7,8));
        graph.insert(new Edge(8,2));
        graph.insert(new Edge(8,9));

        System.out.println("\n\t############ ORIGINAL GRAPH ############");
        plot_graph(graph);

        if(is_undirected(graph))
            System.out.println("Undirected Graph");
        else
            System.out.println("Directed Graph");

        if(is_acyclic_graph(graph))
            System.out.println("Acyclic Graph");
        else
            System.out.println("Cyclic Graph");

        ListGraph copyGraph = new ListGraph(graph);
        ListGraph copyGraph2 = new ListGraph(graph);

        System.out.println("\n\t############ BFS SPANNING TREE  ############");
        getSpanningTreeBFS(graph,4);
        plot_graph(graph);


        if(is_acyclic_graph(graph))
            System.out.println("Acyclic Graph");
        else
            System.out.println("Cyclic Graph");


        System.out.println("\n\t############ DFS SPANNING TREE  ############");
        getSpanningTreeDFS(copyGraph,9);
        plot_graph(copyGraph);

        if(is_acyclic_graph(copyGraph))
            System.out.println("Acyclic Graph");
        else
            System.out.println("Cyclic Graph");


        for(int i=0; i<graph.getNumV(); ++i){

            ListGraph newGraph = new ListGraph(copyGraph2);
            System.out.println("\n\t############ SPANNING TREE "+(i+1)+" ############");
            getSpanningTreeBFS(newGraph,i);
            plot_graph(newGraph);

            if(is_acyclic_graph(newGraph))
                System.out.println("Acyclic Graph");
            else
                System.out.println("Cyclic Graph");
        }
    }
}
