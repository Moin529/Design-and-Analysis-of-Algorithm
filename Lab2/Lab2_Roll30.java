import java.util.*;
import java.io.*;

class Graph{
    int vertices;
    List<List<Integer>> adjList;

    public Graph(String filename) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String[] first = br.readLine().split(" ");
        vertices = Integer.parseInt(first[0]);
        int edges = Integer.parseInt(first[1]);

        adjList = new ArrayList <>();
        for(int i=0; i<vertices; i++){
            adjList.add(new ArrayList <>());
        }

        for(int i=0; i<edges; i++){
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            addEdge(u, v);
        }

        br.close();
    }

    public void addVertex(int n){
        for(int i=0; i<n; i++){
            adjList.add(new ArrayList<>());
        }
        vertices += n;
    }

    public void addEdge(int u, int v){
        while(Math.max(u, v) >= vertices){
            addVertex(1);
        }
        adjList.get(u).add(v);
    }

    public int getNumberOfVertices(){
        return vertices;
    }

    public List<Integer> getAdjacentVertices(int v){
        if(v >= vertices || v < 0){
            throw new IllegalArgumentException("Vertex is out of bounds");
        }
        return adjList.get(v);
    }

    public void displayGraph(){
        for(int i=0; i<adjList.size(); i++){
            System.out.print(i + " -> ");
            for(int adjacent : adjList.get(i)){
                System.out.print(adjacent + " ");
            }
            System.out.println();
        }
    }

    public void DFS(int v){
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS traversal starting from vertex " + v + "is : ");
        dfs(v, visited);
        System.out.println();
    }

    public void dfs(int v, boolean[] visited){
        visited[v] = true;
        System.out.print(v + " ");
        for(int adjacent: adjList.get(v)){
            if(!visited[adjacent]){
                dfs(adjacent, visited);
            }
        }
    }

    public List<Integer> topologicalSort(){
        boolean[] visited = new boolean[vertices];
        Stack <Integer> st = new Stack<>();

        for(int i=0; i<vertices; i++){
            if(!visited[i]){
                topo(i, visited, st);
            }
        }
        
        List <Integer> result = new ArrayList<>();
        while(!st.isEmpty()){
            result.add(st.pop());
        }
        return result;
    }

    public void topo(int v, boolean[] visited, Stack<Integer> st){
        visited[v] = true;
        for(int adjacent: adjList.get(v)){
            if(!visited[adjacent]){
                topo(adjacent, visited, st);
            }
        }
        st.push(v);
    }
}

public class Lab2_Roll30 {
    public static void main(String[] args) throws IOException {

        Graph graph = new Graph("input.txt");

        System.out.println("Graph adjacency list:");
        graph.displayGraph();

        System.out.println("\nPerforming DFS starting from vertex 5:");
        graph.DFS(5);

        System.out.println("\nPerforming Topological Sort:");
        List<Integer> topoOrder = graph.topologicalSort();
        System.out.println("Topological Sort order: " + topoOrder);
    }
}
