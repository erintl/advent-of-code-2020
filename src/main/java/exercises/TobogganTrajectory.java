package exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TobogganTrajectory {
    public static final String FILENAME = "input/toboggan.txt";
    private List<char[]> map;

    public TobogganTrajectory() {
    }

    public void runToboggan1() {
        readFile();
        long treeCount = countTrees(3, 1);
        System.out.printf("The tree count for part 1 is %d\n", treeCount);
    }

    public void runToboggan2() {
        readFile();
        List<Long> treeCounts = new ArrayList<>();
        treeCounts.add(countTrees(1, 1));
        treeCounts.add(countTrees(3, 1));
        treeCounts.add(countTrees(5, 1));
        treeCounts.add(countTrees(7, 1));
        treeCounts.add(countTrees(1, 2));
        long totalValue = 0;
        totalValue = treeCounts.stream().reduce(1L, (a, b) -> a * b);

        System.out.printf("The tree count for part 2 is %d\n", totalValue);
    }

    private void readFile() {
        map = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILENAME));

            while (scanner.hasNextLine()) {
                processLine(scanner.nextLine());
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String nextLine) {
        char[] lineInfo = nextLine.toCharArray();
        map.add(lineInfo);
    }

    private long countTrees(int step, int depth) {
        int treeCount = 0;
        int lineSize = map.get(0).length;
        for (int i = 0; i < map.size(); i += depth) {
            char[] line = map.get(i);
            int target = ((i / depth) * step) % lineSize;
            if (line[target] == '#') {
                treeCount++;
            }
        }
        return treeCount;
    }
}
