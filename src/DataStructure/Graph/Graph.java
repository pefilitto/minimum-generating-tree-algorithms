package DataStructure.Graph;

import DataStructure.Matrix.Matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void MatrixToGraph(Matrix matrix) {
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                if (matrix.matrix[i][j] != 0) {
                    Node startNode = this.nodes.get(i);
                    Node endNode = this.nodes.get(j);

                    if (!VerifyEdgeExistence(startNode, endNode)) {
                        Edge newEdge = new Edge(matrix.matrix[i][j], startNode, endNode);
                        startNode.connections.add(newEdge);
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
            if((edge.startNode == startNode && edge.endNode == endNode) || (edge.startNode == endNode && edge.endNode == startNode)){
                return true;
            }
        }
        return false;
    }

    public void PrintGraph(){
        for (Node node : this.nodes) {
            System.out.println("Vértice: " + node.dataNode);
            for (Edge edge : node.connections) {
                System.out.println("Aresta: " + edge.value + " De: " + edge.startNode.getDataNode() + " Para: " + edge.endNode.getDataNode());
            }
        }
    }

    public void SortConnections(){
        List <Edge> connectionValues = new ArrayList<>();
        for (Node node : this.nodes) {
            connectionValues.addAll(node.connections);
        }

        connectionValues.sort((o1, o2) -> {
            if(o1.value > o2.value){
                return 1;
            } else if(o1.value < o2.value){
                return -1;
            } else {
                return 0;
            }
        });

        StartKruskalAlgorithm(connectionValues);
    }

    public void StartKruskalAlgorithm(List<Edge> connectionValues) {
        List<Edge> mst = new ArrayList<>();
        Map<Node, Node> parent = new HashMap<>();
        int count = 0;

        for (Node node : this.nodes) {
            parent.put(node, node);
        }

        for (Edge edge : connectionValues) {
            Node root1 = find(parent, edge.startNode);
            Node root2 = find(parent, edge.endNode);

            if (!root1.equals(root2)) {
                mst.add(edge);
                union(parent, root1, root2);
            }
        }

        System.out.println("AGM Valores Arestas:");
        for (Edge edge : mst) {
            System.out.println("Aresta: " + edge.value + " De: " + edge.startNode.getDataNode() + " Para: " + edge.endNode.getDataNode());
            count += edge.value;
        }

        System.out.println("Custo Total: " + count);
    }

    private Node find(Map<Node, Node> parent, Node node) {
        if (parent.get(node) != node) {
            parent.put(node, find(parent, parent.get(node)));
        }
        return parent.get(node);
    }

    private void union(Map<Node, Node> parent, Node root1, Node root2) {
        parent.put(root1, root2);
    }
}
