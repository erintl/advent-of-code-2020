package exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CustomCustoms {
    public static final String FILENAME = "input/customs_data.txt";
    List<Set<Character>> customForms;

    public CustomCustoms() {
    }

    public void printAnswerPart1() {
        int sum = 0;

        readFilePart1();
        sum = getAnswerSum();
        System.out.println("The answer sum is: " + sum);
    }

    public void printAnswerPart2() {
        int sum = 0;

        readFilePart2();
        sum = getAnswerSum();
        System.out.println("The answer sum is: " + sum);
    }

    private void readFilePart1() {
        customForms = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILENAME));

            Set<Character> set = new HashSet<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("")) {
                    customForms.add(set);
                    set = new HashSet<>();
                } else {
                    processLine(set, line);
                }
            }
            customForms.add(set);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readFilePart2() {
        customForms = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILENAME));

            Set<Character> set = new HashSet<>();
            processLine(set, scanner.nextLine());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("")) {
                    customForms.add(set);
                    set = new HashSet<>();
                    processLine(set, scanner.nextLine());
                } else {
                    processLineIntersection(set, line);
                }
            }
            customForms.add(set);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void processLine(Set<Character> set, String line) {
        for (char c : line.toCharArray()) {
            set.add(c);
        }
    }

    private void processLineIntersection(Set<Character> set, String line) {
        Set<Character> set2 = new HashSet<>();
        for (char c : line.toCharArray()) {
            set2.add(c);
        }
        set.retainAll(set2);
    }

    private int getAnswerSum(){
        int sum = 0;

        for (Set<Character> set : customForms) {
            sum += set.size();
        }
        return sum;
    }
}

