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

        Graph g1 = new Graph();
        g1.addEdge(0, 1, 1);
        g1.addEdge(1, 2, 1);
        g1.addEdge(2, 3, 1);
        g1.addEdge(3, 4, 1);
        g1.addEdge(4, 5, 1);
        g1.addEdge(5, 6, 1);
        g1.addEdge(6, 7, 1);

        System.out.println("=== Test Case 1 ===");
        g1.findPaths(0, 7);

        Graph g2 = new Graph();
        g2.addEdge(0, 1, 1);
        g2.addEdge(1, 2, 1);
        g2.addEdge(2, 3, 1);

        System.out.println("\n=== Test Case 2 ===");
        g2.findPaths(0, 3);

        Graph g3 = new Graph();
        g3.addEdge(0, 1, 1);
        g3.addEdge(0, 2, 1);
        g3.addEdge(1, 3, 1);
        g3.addEdge(2, 3, 1);
        g3.addEdge(3, 4, 1);
        g3.addEdge(4, 5, 1);
        g3.addEdge(5, 6, 1);
        g3.addEdge(6, 7, 1);
        g3.addEdge(1, 2, 1);
        g3.addEdge(2, 4, 1);
        g3.addEdge(4, 6, 1);
        g3.addEdge(6, 3, 1); // cyclic option but should skip revisits

        System.out.println("\n=== Test Case 3 ===");
        g3.findPaths(0, 7);

        Graph g4 = new Graph();
        g4.addEdge(0, 1, 1);
        g4.addEdge(1, 2, 1);
        g4.addEdge(2, 3, 1);
        g4.addEdge(3, 4, 1);
        g4.addEdge(4, 5, 1);
        g4.addEdge(5, 6, 1);
        g4.addEdge(6, 7, 1);
        g4.addEdge(7, 8, 1);

        System.out.println("\n=== Test Case 4 ===");
        g4.findPaths(0, 8); // This path has 8 edges, so it should not print

        Graph g5 = new Graph();
        g5.addEdge(0, 1, 1);
        g5.addEdge(1, 2, 1);
        g5.addEdge(2, 3, 1);
        g5.addEdge(3, 1, 1); // cycle
        g5.addEdge(3, 4, 1);
        g5.addEdge(4, 5, 1);
        g5.addEdge(5, 6, 1);
        g5.addEdge(6, 7, 1);
        g5.addEdge(7, 8, 1);

        System.out.println("\n=== Test Case 5 ===");
        g5.findPaths(0, 7);

    }
}
