package exercises;

import exercises.support.Passport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PassportProcessing {
    public static final String FILENAME = "input/passport_data.txt";
    public List<Passport> passports;

    public PassportProcessing() {
    }

    public void checkPassportsPart1() {
        readFile();
        int validCount = 0;
        for (Passport passport : passports) {
            if (passport.isValid()) {
                validCount++;
            }
        }
        System.out.printf("Valid passport count: %d\n", validCount);
    }

    private void readFile() {
        passports = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILENAME));

            Passport passport = new Passport();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("")) {
                    passports.add(passport);
                    passport = new Passport();
                } else {
                    processLine(passport, line);
                }
            }
            passports.add(passport);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void processLine(Passport passport, String line) {
        String[] parts = line.split(" ");
        for (String part : parts) {
            String[] targetValue = part.split(":");
            String field = targetValue[0];
            String value = targetValue[1];
            passport.addData(field, value);
        }
    }
}
