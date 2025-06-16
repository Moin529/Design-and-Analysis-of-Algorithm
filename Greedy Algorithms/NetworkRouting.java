import java.util.*;

public class NetworkRouting {
    static class Edge {
        int target, weight;

        Edge(int t, int w) {
            target = t;
            weight = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of nodes and edges: ");
        int n = sc.nextInt(), m = sc.nextInt();
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        System.out.println("Enter edges (u v w):");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));  // if undirected
        }

        System.out.print("Enter source node: ");
        int source = sc.nextInt();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue;

            for (Edge e : graph.get(u)) {
                if (dist[u] + e.weight < dist[e.target]) {
                    dist[e.target] = dist[u] + e.weight;
                    pq.offer(new int[]{e.target, dist[e.target]});
                }
            }
        }

        System.out.println("Shortest distances from source:");
        for (int i = 0; i < n; i++)
            System.out.println("To node " + i + ": " + dist[i]);
    }
}
