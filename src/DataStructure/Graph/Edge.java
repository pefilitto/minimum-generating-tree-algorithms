package DataStructure.Graph;

public class Edge {
    public int value;
    public Node startNode;
    public Node endNode;

    public Edge(int value, Node startNode, Node endNode) {
        this.value = value;
        this.startNode = startNode;
        this.endNode = endNode;
    }
}
