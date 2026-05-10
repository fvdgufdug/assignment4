public class Experiment {
    public static void runTraversals(Graph g, int startNode) {
        long startBfs = System.nanoTime();
        g.bfs(startNode);
        long endBfs = System.nanoTime();
        System.out.println("\nBFS Time: " + (endBfs - startBfs) + " ns");

        long startDfs = System.nanoTime();
        g.dfs(startNode);
        long endDfs = System.nanoTime();
        System.out.println("\nDFS Time: " + (endDfs - startDfs) + " ns");
    }

    public static void runMultipleTests() {
        int[] sizes = {10, 30, 100};
        for (int size : sizes) {
            System.out.println("\n--- Graph Size: " + size + " ---");
            Graph g = new Graph(false);
            for (int i = 0; i < size; i++) g.addVertex(new Vertex(i));
            for (int i = 0; i < size - 1; i++) {
                g.addEdge(i, i + 1);
                if (i + 2 < size) g.addEdge(i, i + 2);
            }
            runTraversals(g, 0);
        }
    }
}