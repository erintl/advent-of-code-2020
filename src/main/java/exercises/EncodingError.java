package exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EncodingError {
    public static final String FILENAME = "input/encoding_error.txt";
    public static final int PREAMBLE_SIZE = 25;
    private long weaknessMin;
    private long weaknessMax;
    private List<Long> values;

    public EncodingError() {
        weaknessMin = 0;
        weaknessMax = 0;
    }

    public void printFirstError() {
        readFile();
        long firstError = findFirstError();
        System.out.println("The first error value is: " + firstError);
    }

    public void printWeaknessSum() {
        readFile();
        long firstError = findFirstError();
        findMinMaxErrorValues(firstError);
        long sum = weaknessMin + weaknessMax;
        System.out.println("The first error value is: " + firstError);
        System.out.println("Weakness min: " + weaknessMin +  ", Weakness max: " + weaknessMax +  ", Sum: " + sum);
    }

    private void readFile() {
        values = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILENAME));

            while (scanner.hasNextLine()) {
                Long value = Long.parseLong(scanner.nextLine());
                values.add(value);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private long findFirstError() {
        List<Long> errors = new ArrayList<>();
        for (int i = PREAMBLE_SIZE; i < values.size(); i++) {
            boolean valid = false;
            for (int j = i - PREAMBLE_SIZE; j < i; j++) {
                for (int k = i - PREAMBLE_SIZE; k < i; k++) {
                    if (k != j) {
                        if ((values.get(k) + values.get(j)) == values.get(i)) {
                            valid = true;
                        }
                    }
                }
            }
            if (!valid) {
                errors.add(values.get(i));
            }
        }
        return  errors.get(0);
    }

    public void findMinMaxErrorValues(long errorValue) {
        boolean errorFound = false;
        List<Long> weaknessValues = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            long sum = 0;
            int count = 0;
            for (int j = i; j < values.size(); j++) {
                count++;
                weaknessValues.add(values.get(j));
                sum += values.get(j);
                if (!errorFound && sum == errorValue && count > 1) {
                    System.out.println("found");
                    setMinMax(weaknessValues);
                    errorFound = true;
                }
            }
            weaknessValues.clear();
        }
    }

    private void setMinMax(List<Long> weaknessValues) {
        for (Long val : weaknessValues) {
            System.out.println(val);
        }
        weaknessMax = weaknessValues
            .stream()
            .mapToLong(v -> v)
            .max().orElseThrow(NoSuchElementException::new);
        weaknessMin = weaknessValues
                .stream()
                .mapToLong(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
    }
}
