import java.util.*;

public class Graph {
    private Map<Integer, List<Vertex>> adjList;
    private boolean undirected;

    public Graph(boolean undirected) {
        this.adjList = new HashMap<>();
        this.undirected = undirected;
    }

    public void addVertex(Vertex v) {
        adjList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        adjList.get(from).add(new Vertex(to));
        if (undirected) {
            adjList.get(to).add(new Vertex(from));
        }
    }

    public void printGraph() {
        for (var entry : adjList.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (adjList.size() <= 10) System.out.print(current + " ");
            for (Vertex neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor.getId())) {
                    visited.add(neighbor.getId());
                    queue.add(neighbor.getId());
                }
            }
        }
    }

    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(start, visited);
    }

    private void dfsRecursive(int current, Set<Integer> visited) {
        visited.add(current);
        if (adjList.size() <= 10) System.out.print(current + " ");
        for (Vertex neighbor : adjList.getOrDefault(current, new ArrayList<>())) {
            if (!visited.contains(neighbor.getId())) {
                dfsRecursive(neighbor.getId(), visited);
            }
        }
    }
}