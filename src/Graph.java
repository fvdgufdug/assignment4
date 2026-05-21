import java.util.*;

public class Graph {
    // ТЕПЕРЬ ХРАНИМ СПИСОК РЕБЕР (Edge), а не просто Vertex
    private Map<Integer, List<Edge>> adjList;
    private boolean undirected;

    public Graph(boolean undirected) {
        this.adjList = new HashMap<>();
        this.undirected = undirected;
    }

    public void addVertex(Vertex v) {
        adjList.putIfAbsent(v.getId(), new ArrayList<>());
    }

    // Изменили метод: теперь принимает вес ребра
    public void addEdge(int from, int to, int weight) {
        Vertex source = new Vertex(from);
        Vertex destination = new Vertex(to);

        adjList.get(from).add(new Edge(source, destination, weight));
        if (undirected) {
            adjList.get(to).add(new Edge(destination, source, weight));
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

            for (Edge edge : adjList.getOrDefault(current, new ArrayList<>())) {
                int neighborId = edge.getDestination().getId();
                if (!visited.contains(neighborId)) {
                    visited.add(neighborId);
                    queue.add(neighborId);
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

        for (Edge edge : adjList.getOrDefault(current, new ArrayList<>())) {
            int neighborId = edge.getDestination().getId();
            if (!visited.contains(neighborId)) {
                dfsRecursive(neighborId, visited);
            }
        }
    }

    public void dijkstra(int start) {
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        for (Integer vertexId : adjList.keySet()) {
            dist.put(vertexId, Integer.MAX_VALUE);
            visited.put(vertexId, false);
        }

        if (dist.containsKey(start)) {
            dist.put(start, 0);
        } else {
            System.out.println("Стартовая вершина не найдена в графе.");
            return;
        }

        for (int i = 0; i < adjList.size() - 1; i++) {
            int u = findMinDistanceVertex(dist, visited);
            if (u == -1) break; // Все оставшиеся вершины недостижимы

            visited.put(u, true);

            for (Edge edge : adjList.getOrDefault(u, new ArrayList<>())) {
                int v = edge.getDestination().getId();
                if (!visited.get(v) && dist.get(u) != Integer.MAX_VALUE) {
                    int newDist = dist.get(u) + edge.getWeight();
                    if (newDist < dist.get(v)) {
                        dist.put(v, newDist);
                    }
                }
            }
        }

        printDijkstraResults(start, dist);
    }

    private int findMinDistanceVertex(Map<Integer, Integer> dist, Map<Integer, Boolean> visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (Integer vertexId : adjList.keySet()) {
            if (!visited.get(vertexId) && dist.get(vertexId) <= min) {
                min = dist.get(vertexId);
                minIndex = vertexId;
            }
        }
        return minIndex;
    }

    private void printDijkstraResults(int start, Map<Integer, Integer> dist) {
        System.out.println("\n--- Dijkstra Shortest Path (Start from " + start + ") ---");
        for (Map.Entry<Integer, Integer> entry : dist.entrySet()) {
            String distanceStr = (entry.getValue() == Integer.MAX_VALUE) ? "Unreachable" : String.valueOf(entry.getValue());
            System.out.println("To vertex " + entry.getKey() + ": Shortest distance = " + distanceStr);
        }
    }
}