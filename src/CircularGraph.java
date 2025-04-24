import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import java.util.*;

public class CircularGraph {
    // Helper class to store vertex and its jump distance
    static class Vertex {
        String label;
        int distance;
        Vertex(String label, int distance) {
            this.label = label;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "swing");

        // Input string
        String input = "[ (I, 2) , (A, 5) , (E, 4) , (F,2) , (T, 2) , (S, 3) ]";

        // Parse the input
        List<Vertex> vertices = parseInput(input);
        int n = vertices.size();

        // Create the graph
        Graph graph = new SingleGraph("Circular Directed Graph");
        graph.setStrict(false);
        graph.setAutoCreate(true);
        graph.setAttribute("ui.stylesheet", "node { fill-color: #77c; size: 35px; text-size: 20px; } edge { arrow-shape: arrow; }");

        // Add nodes
        for (Vertex v : vertices) {
            Node node = graph.addNode(v.label);
            node.setAttribute("ui.label", v.label);
        }

        // Add directed circular edges
        for (int i = 0; i < n; i++) {
            Vertex current = vertices.get(i);
            int dist = current.distance;
            int rightIndex = (i + dist) % n;
            int leftIndex = (i - dist + n) % n;
            String from = current.label;
            String toRight = vertices.get(rightIndex).label;
            String toLeft = vertices.get(leftIndex).label;

            // Create unique edge IDs and avoid duplicates
            if (graph.getEdge(from + "->" + toRight) == null)
                graph.addEdge(from + "->" + toRight, from, toRight, true);
            if (graph.getEdge(from + "->" + toLeft) == null)
                graph.addEdge(from + "->" + toLeft, from, toLeft, true);
        }

        // Display the graph
        graph.display();
    }

    // Parses the formatted input string into Vertex objects
    public static List<Vertex> parseInput(String input) {
        List<Vertex> list = new ArrayList<>();
        input = input.replaceAll("[\\[\\]()]", "").trim();
        if (input.isEmpty()) return list;

        String[] parts = input.split(",");
        for (int i = 0; i < parts.length; i += 2) {
            String label = parts[i].trim().replaceAll("[^A-Za-z]", "");
            int distance = Integer.parseInt(parts[i + 1].trim().replaceAll("[^0-9]", ""));
            list.add(new Vertex(label, distance));
        }
        return list;
    }
}
