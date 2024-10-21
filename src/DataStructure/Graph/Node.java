package DataStructure.Graph;

import java.util.List;

public class Node {
    public String dataNode;
    public List<Edge> connections;

    public Node(String dataNode, List<Edge> connections) {
        this.dataNode = dataNode;
        this.connections = connections;
    }

    public String getDataNode() {
        return dataNode;
    }
}
