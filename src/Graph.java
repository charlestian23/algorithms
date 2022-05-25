// Implementation of a graph using an adjacency list

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<T extends Comparable<T>>
{
    private Map<T, Set<T>> graph;
    private boolean isUndirectedGraph;

    public Graph()
    {
        this.graph = new HashMap<T, Set<T>>();
        this.isUndirectedGraph = false;
    }

    public Graph(boolean isUndirectedGraph)
    {
        this.graph = new HashMap<T, Set<T>>();
        this.isUndirectedGraph = isUndirectedGraph;
    }

    public Set<T> getNodes()
    {
        return this.graph.keySet();
    }

    public boolean isNode(T node)
    {
        return this.graph.keySet().contains(node);
    }

    public void addNode(T node)
    {
        if (this.isNode(node))
            return;
        if (node == null)
            throw new NullPointerException("Cannot add a null node");
        this.graph.put(node, new HashSet<T>());
    }

    public Set<T> getChildren(T node)
    {
        if (node == null)
            throw new NullPointerException("Node cannot be null");
        if (!this.isNode(node))
            throw new IllegalArgumentException("Invalid node given");
        return this.graph.get(node);
    }

    public void addEdge(T node1, T node2)
    {
        if (node1 == null)
            throw new NullPointerException("Node 1 cannot be null");
        if (node2 == null)
            throw new NullPointerException("Node 2 cannot be null");
        if (!this.isNode(node1))
            throw new IllegalArgumentException("Node 1 must be in the graph");
        if (!this.isNode(node2))
            throw new IllegalArgumentException("Node 2 must in the graph");
        this.graph.get(node1).add(node2);
        if (this.isUndirectedGraph)
            this.graph.get(node2).add(node1);
    }
}
