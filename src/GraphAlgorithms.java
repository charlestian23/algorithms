import java.util.*;

public class GraphAlgorithms
{
    /**
     * Breadth First Search
     *
     * The time complexity is O(V+E), where V is the number of vertices and E is the
     * number of edges in the graph.
     *
     * @param graph         The graph to run depth first search on
     * @param sourceNode    The source node
     * @return              A map where the key is the node and the value is the
     *                      distance of that node from the source node
     * @param <T>
     */
    public static <T extends Comparable<T>> Map<T, Integer> bfs(Graph<T> graph, T sourceNode)
    {
        Map<T, Integer> distances = new HashMap<T, Integer>();
        for (T node : graph.getNodes())
            distances.put(node, -1);
        distances.put(sourceNode, 0);
        Queue<T> queue = new LinkedList<T>();
        queue.add(sourceNode);
        while (!queue.isEmpty())
        {
            T node = queue.remove();
            int nodeDistance = distances.get(node);
            for (T child : graph.getChildren(node))
                if (distances.get(child) == -1)
                {
                    queue.add(child);
                    distances.put(child, nodeDistance + 1);
                }
        }
        return distances;
    }

    /**
     * Depth First Search
     *
     * The time complexity is O(V+E), where V is the number of vertices and E is the
     * number of edges in the graph.
     *
     * Reference: https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
     *
     * @param graph         The graph to run depth first search on
     * @param sourceNode    The source node
     * @return              A set containing all the visited nodes from the source node
     * @param <T>
     */
    public static <T extends Comparable<T>> Set<T> dfs(Graph<T> graph, T sourceNode)
    {
        Set<T> visited = new HashSet<T>();
        dfsHelper(graph, sourceNode, visited);
        return visited;
    }

    private static <T extends Comparable<T>> void dfsHelper(Graph<T> graph, T node, Set<T> visited)
    {
        // Mark the current node as visited
        visited.add(node);

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : graph.getChildren(node))
            if (!visited.contains(neighbor))
                dfsHelper(graph, neighbor, visited);
    }

    public static void main(String[] args)
    {
        Graph<Character> graph = new Graph<Character>(true);
        char[] nodes = {'S', 'A', 'B', 'C', 'D', 'E', 'F'};
        for (char node : nodes)
            graph.addNode(node);
        graph.addEdge('S', 'A');
        graph.addEdge('S', 'C');
        graph.addEdge('S', 'D');
        graph.addEdge('S', 'E');
        graph.addEdge('A', 'B');
        Map<Character, Integer> distances = bfs(graph, 'S');
        for (char node : nodes)
            System.out.println(node + ": " + distances.get(node));
        System.out.println("DFS from node S: " + dfs(graph, 'S'));
        System.out.println("DFS from node F: " + dfs(graph, 'F'));
    }
}
