public class Main {
    public static void main(String[] args) {
        Graph smallGraph = new Graph(false);
        for (int i = 0; i < 10; i++) {
            smallGraph.addVertex(new Vertex(i));
        }

        smallGraph.addEdge(0, 1, 4);
        smallGraph.addEdge(0, 2, 1);
        smallGraph.addEdge(1, 3, 5);
        smallGraph.addEdge(2, 4, 3);
        smallGraph.addEdge(3, 5, 2);
        smallGraph.addEdge(4, 5, 1);

        System.out.println("--- Graph Structure ---");
        smallGraph.printGraph();

        System.out.println("\n--- BFS Traversal (Start from 0) ---");
        smallGraph.bfs(0);
        System.out.println();

        System.out.println("\n--- DFS Traversal (Start from 0) ---");
        smallGraph.dfs(0);
        System.out.println();

        smallGraph.dijkstra(0);

        System.out.println("\n--- Performance Tests ---");
        Experiment.runMultipleTests();
    }
}