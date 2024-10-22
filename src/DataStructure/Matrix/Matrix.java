package DataStructure.Matrix;

import DataStructure.Graph.Graph;
import File.File;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Matrix {
    public int rows;
    public int cols;
    public int[][] matrix;

    private Graph graph = new Graph();

    public Matrix(File file) throws IOException {
        this.rows = file.getNumberOfLines();
        this.cols = this.rows;
        this.matrix = new int[rows][cols];

        this.ReadMatrix(file);
    }

    public void ReadMatrix(File file) {
        try (RandomAccessFile raf = new RandomAccessFile(file.pathFile, "r")) {
            String line;

            line = raf.readLine();

            graph.CreateNodes(line);

            for (int i = 0; i < this.rows; i++) {
                line = raf.readLine();
                String[] values = line.trim().split("\\s+");

                for (int j = 0; j < this.cols; j++) {
                    this.matrix[i][j] = Integer.parseInt(values[j]);
                }
            }

            graph.MatrixToGraph(this);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
