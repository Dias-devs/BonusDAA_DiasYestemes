package mst;

import model.Edge;
import model.Subset;

import java.util.*;

public class MST {
    int V;
    List<Edge> edges = new ArrayList<>();
    List<Edge> mstEdges = new ArrayList<>();

    public MST(int vertices) {
        this.V = vertices;
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    // Find and Union (Disjoint Set)
    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // Build MST using Kruskal
    public void buildMST() {
        Collections.sort(edges);
        Subset[] subsets = new Subset[V];
        for (int v = 0; v < V; v++) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        for (Edge e : edges) {
            int x = find(subsets, e.src);
            int y = find(subsets, e.dest);
            if (x != y) {
                mstEdges.add(e);
                union(subsets, x, y);
            }
        }
    }

    public void printMST() {
        System.out.println("MST edges:");
        for (Edge e : mstEdges)
            System.out.println("  " + e);
    }

    public void removeEdge(int index) {
        if (index >= 0 && index < mstEdges.size()) {
            Edge removed = mstEdges.remove(index);
            System.out.println("\nRemoved edge: " + removed);
        }
    }

    public void findReplacementEdge() {
        boolean[] visited = new boolean[V];
        List<Integer> compA = new ArrayList<>();
        dfs(mstEdges.getFirst().src, visited, compA);

        Edge best = null;
        for (Edge e : edges) {
            boolean inMST = mstEdges.contains(e);
            if (!inMST &&
                    ((compA.contains(e.src) && !compA.contains(e.dest)) ||
                            (compA.contains(e.dest) && !compA.contains(e.src)))) {
                if (best == null || e.weight < best.weight)
                    best = e;
            }
        }

        if (best != null) {
            mstEdges.add(best);
            System.out.println("Added replacement edge: " + best);
        } else {
            System.out.println("No replacement edge found!");
        }
    }

    private void dfs(int node, boolean[] visited, List<Integer> comp) {
        visited[node] = true;
        comp.add(node);
        for (Edge e : mstEdges) {
            if (e.src == node && !visited[e.dest])
                dfs(e.dest, visited, comp);
            else if (e.dest == node && !visited[e.src])
                dfs(e.src, visited, comp);
        }
    }
}