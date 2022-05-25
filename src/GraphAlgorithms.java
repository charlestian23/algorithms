import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GraphAlgorithms
{
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

    public static void main(String[] args)
    {
        Graph<Character> graph = new Graph<Character>(true);
        char[] nodes = {'S', 'A', 'B', 'C', 'D', 'E'};
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
    }
}
