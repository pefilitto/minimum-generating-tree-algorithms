import DataStructure.Matrix.Matrix;
import File.File;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Pedro Filitto\\Desktop\\6 Termo\\TG\\minimum-generating-tree-algorithms\\src\\Tests\\EntryTests.txt");
        new Matrix(file);
    }
}