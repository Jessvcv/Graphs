import java.util.*;

public class PathsOfLength7 {
    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Graph {
        Map<Integer, List<Edge>> adjList = new HashMap<>();

        void addEdge(int from, int to, int weight) {
            adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
        }

        void findPaths(int u, int w) {
            List<Integer> path = new ArrayList<>();
            Set<Integer> visited = new HashSet<>();
            path.add(u);
            visited.add(u);
            dfs(u, w, 0, path, visited);
        }

        void dfs(int current, int target, int depth, List<Integer> path, Set<Integer> visited) {
            if (depth == 7) {
                if (current == target) {
                    System.out.println(path);
                }
                return;
            }

            if (!adjList.containsKey(current)) return;

            for (Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.to)) {
                    path.add(edge.to);
                    visited.add(edge.to);
                    dfs(edge.to, target, depth + 1, path, visited);
                    visited.remove(edge.to);
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();

        System.out.print("Enter number of edges: ");
        int edgeCount = scanner.nextInt();

        System.out.println("Enter edges in the format: from to weight");
        for (int i = 0; i < edgeCount; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(from, to, weight);
        }

        System.out.print("Enter starting vertex (u): ");
        int u = scanner.nextInt();

        System.out.print("Enter ending vertex (w): ");
        int w = scanner.nextInt();

        System.out.println("\n=== Paths of length 7 from " + u + " to " + w + " ===");
        graph.findPaths(u, w);
    }
}
