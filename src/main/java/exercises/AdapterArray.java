package exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AdapterArray {
    public static final String FILENAME = "input/adapter_array.txt";
    private List<Integer> values;
    private Map<Integer, Integer> diffs;

    public void printPart1Answer() {
        readFile();
        computeDiffs();
        System.out.println(diffs.get(1) * diffs.get(3));
    }

    private void readFile() {
        values = new ArrayList<>();
        values.add(0);
        try {
            Scanner scanner = new Scanner(new File(FILENAME));
            while (scanner.hasNextLine()) {
                Integer value = Integer.parseInt(scanner.nextLine());
                values.add(value);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        sortValues();
    }

    private void sortValues() {
        Collections.sort(values);
        int max = values.get(values.size() - 1);
        values.add(max + 3);
    }

    private void computeDiffs() {
        diffs = new HashMap<>();
        int first = 0;
        int second = 0;
        int result = 0;

        for (int i = 1; i < values.size(); i++) {
            first = values.get(i - 1);
            second = values.get(i);
            result = second - first;

            if (diffs.containsKey(result)) {
                int resultCount = diffs.get(result);
                diffs.put(result, ++resultCount);
            } else {
                diffs.put(result, 1);
            }
        }
    }
}
