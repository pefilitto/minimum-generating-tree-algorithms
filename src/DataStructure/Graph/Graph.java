package DataStructure.Graph;

import DataStructure.Matrix.Matrix;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public List<Node> nodes;
    public Graph() {}

    public void CreateNodes(String nodes){
        String[] nodesArray = nodes.split(" ");
        List<Node> nodesList = new ArrayList<>();
        for (String node : nodesArray) {
            Node newNode = new Node(node, new ArrayList<>());
            nodesList.add(newNode);
        }
        this.nodes = nodesList;
    }

    public void MatrixToGraph(Matrix matrix){
        for (int i = 0; i < matrix.cols - 1; i++) {
            for (int j = 0; j < matrix.rows - 1; j++) {
                if(matrix.matrix[i][j] != 0){
                    if(!VerifyEdgeExistence(this.nodes.get(i), this.nodes.get(j))){
                        Node startNode = this.nodes.get(i);
                        Node endNode = this.nodes.get(j);
                        Edge newEdge = new Edge(matrix.matrix[i][j], startNode, endNode);
                        startNode.connections.add(newEdge);

                        Edge reverseEdge = new Edge(matrix.matrix[i][j], endNode, startNode);
                        endNode.connections.add(reverseEdge);
                    }
                }
            }
        }

        PrintGraph();
        System.out.println();
        SortConnections();
    }

    public boolean VerifyEdgeExistence(Node startNode, Node endNode){
        for (Edge edge : startNode.connections) {
            if(edge.startNode == startNode && edge.endNode == endNode){
                return true;
            }
        }
        return false;
    }

    public void PrintGraph(){
        for (Node node : this.nodes) {
            System.out.println("Node: " + node.dataNode);
            for (Edge edge : node.connections) {
                System.out.println("Edge: " + edge.value + " From: " + edge.startNode.getDataNode() + " To: " + edge.endNode.getDataNode());
            }
        }
    }

    public void SortConnections(){
        List <Integer> connectionValues = new ArrayList<>();
        for (Node node : this.nodes) {
            for (Edge edge : node.connections) {
                connectionValues.add(edge.value);
            }
        }

        connectionValues.sort(Integer::compareTo);

        for (int i = 0; i < connectionValues.size(); i++) {
            System.out.printf(connectionValues.get(i) + " ");
        }
    }

}
