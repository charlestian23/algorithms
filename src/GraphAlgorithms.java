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

    /**
     * Reverses all the edges of a given directed graph
     * @param graph A directed graph
     * @return      The given directed graph with the edges reversed
     * @param <T>
     */
    public static <T extends Comparable<T>> Graph<T> reverse(Graph<T> graph)
    {
        Graph<T> reverse = new Graph<T>(false);
        for (T node : graph.getNodes())
            reverse.addNode(node);
        for (T node : graph.getNodes())
            for (T child : graph.getChildren(node))
                reverse.addEdge(child, node);
        return reverse;
    }

    /**
     * Kosaraju's Algorithm for Strongly Connected Components
     *
     * The time complexity is O(V+E), where V is the number of vertices and E is the
     * number of edges in the graph.
     *
     * Reference: https://www.topcoder.com/thrive/articles/kosarajus-algorithm-for-strongly-connected-components
     *
     * @param graph A directed graph
     * @return      A list of the strongly connected components (which are stored as sets of nodes)
     * @param <T>
     */
    public static <T extends Comparable<T>> List<Set<T>> kosaraju(Graph<T> graph)
    {
        Map<T, Boolean> visited = new HashMap<T, Boolean>();
        for (T node : graph.getNodes())
            visited.put(node, false);

        Stack<T> stack = new Stack<T>();
        for (T node : graph.getNodes())
            if (!visited.get(node))
            {
                Set<T> dfsResult = GraphAlgorithms.modifiedDFSForKosaraju(graph, node, stack);
                for (T resultNode : dfsResult)
                    visited.put(resultNode, true);
            }

        for (T node : graph.getNodes())
            visited.put(node, false);

        Graph<T> reverseGraph = GraphAlgorithms.reverse(graph);
        List<Set<T>> components = new LinkedList<Set<T>>();
        while (!stack.isEmpty())
        {
            T topNode = stack.pop();
            if (!visited.get(topNode))
            {
                Set<T> dfsResult = GraphAlgorithms.modifiedDFSForKosaraju2(reverseGraph, topNode, visited);
                components.add(dfsResult);

                for (T resultNode : dfsResult)
                    visited.put(resultNode, true);
            }
        }

        return components;
    }

    public static <T extends Comparable<T>> Set<T> modifiedDFSForKosaraju(Graph<T> graph, T sourceNode, Stack<T> stack)
    {
        Set<T> visited = new HashSet<T>();
        GraphAlgorithms.modifiedDFSHelperForKosaraju(graph, sourceNode, visited, stack);
        return visited;
    }

    private static <T extends Comparable<T>> void modifiedDFSHelperForKosaraju(Graph<T> graph, T node, Set<T> visited, Stack<T> stack)
    {
        // Mark the current node as visited
        visited.add(node);

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : graph.getChildren(node))
            if (!visited.contains(neighbor))
                GraphAlgorithms.modifiedDFSHelperForKosaraju(graph, neighbor, visited, stack);

        stack.push(node);
    }

    public static <T extends Comparable<T>> Set<T> modifiedDFSForKosaraju2(Graph<T> graph, T sourceNode, Map<T, Boolean> visited)
    {
        Set<T> component = new HashSet<T>();
        GraphAlgorithms.modifiedDFSHelperForKosaraju2(graph, sourceNode, visited, component);
        return component;
    }

    private static <T extends Comparable<T>> void modifiedDFSHelperForKosaraju2(Graph<T> graph, T node, Map<T, Boolean> visited, Set<T> component)
    {
        // Mark the current node as visited
        component.add(node);
        visited.put(node, true);

        // Recur for all the vertices adjacent to this vertex
        for (T neighbor : graph.getChildren(node))
            if (!visited.get(neighbor) && !component.contains(neighbor))
                GraphAlgorithms.modifiedDFSHelperForKosaraju2(graph, neighbor, visited, component);
    }

    /**
     * Topological Sort
     *
     * The time complexity is O(V+E), where V is the number of vertices and E is the
     * number of edges in the graph.
     *
     * References: Personal college lecture notes and https://www.geeksforgeeks.org/topological-sorting/
     *
     * @param graph
     * @return
     * @param <T>
     */
    public static <T extends Comparable<T>> Stack<T> topologicalSort(Graph<T> graph)
    {
        Map<T, Boolean> visited = new HashMap<T, Boolean>();
        for (T node : graph.getNodes())
            visited.put(node, false);

        Stack<T> stack = new Stack<T>();
        for (T node : graph.getNodes())
            if (!visited.get(node))
                GraphAlgorithms.topologicalSortHelper(graph, node, visited, stack);

        return stack;
    }

    public static <T extends Comparable<T>> void topologicalSortHelper(Graph<T> graph, T vertex, Map<T, Boolean> visited, Stack<T> stack)
    {
        visited.put(vertex, true);

        for (T child : graph.getChildren(vertex))
            if (!visited.get(child))
                topologicalSortHelper(graph, child, visited, stack);
        stack.push(vertex);
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

        Graph<Character> directedGraph = new Graph<Character>(false);
        for (char node : nodes)
            directedGraph.addNode(node);
        directedGraph.addEdge('S', 'A');
        directedGraph.addEdge('S', 'C');
        directedGraph.addEdge('S', 'D');
        directedGraph.addEdge('S', 'E');
        directedGraph.addEdge('A', 'B');
        directedGraph.addEdge('B', 'S');
        List<Set<Character>> stronglyConnectedComponents = GraphAlgorithms.kosaraju(directedGraph);
        int counter = 1;
        for (Set<Character> component : stronglyConnectedComponents)
            System.out.println("SCC " + (counter++) + ": " + component);

        Graph<Integer> topologicalSortTest = new Graph<Integer>(false);
        for (int i = 0; i < 6; i++)
            topologicalSortTest.addNode(i);
        topologicalSortTest.addEdge(5, 0);
        topologicalSortTest.addEdge(4, 0);
        topologicalSortTest.addEdge(4, 1);
        topologicalSortTest.addEdge(3, 1);
        topologicalSortTest.addEdge(2, 3);
        topologicalSortTest.addEdge(5, 2);
        Stack<Integer> stack = GraphAlgorithms.topologicalSort(topologicalSortTest);
        System.out.println("Topologically Sorted: " + stack);
    }
}
