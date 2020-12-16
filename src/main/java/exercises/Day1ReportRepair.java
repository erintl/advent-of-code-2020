package exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1ReportRepair {
    private static final String FILENAME = "input/report_repair.txt";
    private List<Integer> reportValues;

    public Day1ReportRepair() {
    }

    public void repairReport() {
        readFile();
        findValues1();
        findValues2();
    }

    private void readFile() {
        try {
            Scanner scanner = new Scanner(new File(FILENAME));
            reportValues = new ArrayList<>();
            while (scanner.hasNextLine()) {
                reportValues.add(Integer.parseInt(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void findValues1() {
        for (Integer value1 : reportValues) {
            for (Integer value2 : reportValues) {
                if ((value1 + value2) == 2020) {
                    System.out.printf("target value 1: %d\n", value1 * value2);
                    return;
                }
            }
        }
    }

    public void findValues2() {
        for (Integer value1 : reportValues) {
            for (Integer value2 : reportValues) {
                for (Integer value3 : reportValues) {
                    if ((value1 + value2 + value3) == 2020) {
                        System.out.printf("target value 2: %d\n", value1 * value2 * value3);
                        return;
                    }
                }
            }
        }
    }
}
