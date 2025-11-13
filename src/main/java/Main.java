import mst.MST;

public class Main {
    public static void main(String[] args) {
        MST graph = new MST(6);

        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 5);
        graph.addEdge(2, 4, 11);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 7);

        graph.buildMST();
        graph.printMST();

        graph.removeEdge(1);
        graph.findReplacementEdge();

        System.out.println("\nFinal MST:");
        graph.printMST();
    }
}