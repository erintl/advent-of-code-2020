package exercises;

import exercises.support.PasswordInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2PasswordPhilosophy {
    public static final String FILENAME = "input/password_philosophy.txt";
    private List<PasswordInfo> passwordInfos;
    private int validPasswordCount1;
    private int validPasswordCount2;

    public Day2PasswordPhilosophy() {
    }

    public void processPasswords() {
        passwordInfos = new ArrayList<>();
        readFile();
        checkPasswordsPart1();
        checkPasswordsPart2();
        System.out.printf("Valid part 1 password count: %d\n", validPasswordCount1);
        System.out.printf("Valid part 2 password count: %d\n", validPasswordCount2);
    }

    private void readFile() {
        try {
            Scanner scanner = new Scanner(new File(FILENAME));

            while (scanner.hasNextLine()) {
                processLine(scanner.nextLine());
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(" ");
        String[] minMaxPart = parts[0].split("-");
        char letter = parts[1].charAt(0);
        String targetString = parts[2];
        int min = Integer.parseInt(minMaxPart[0]);
        int max = Integer.parseInt(minMaxPart[1]);
        PasswordInfo passwordInfo1 = new PasswordInfo(targetString, letter, min, max);
        passwordInfos.add(passwordInfo1);
    }

    private void checkPasswordsPart1() {
        validPasswordCount1 = 0;
        for (PasswordInfo passwordInfo : passwordInfos) {
            int letterCount = 0;
            for (char i : passwordInfo.getPassword().toCharArray()) {
                if (i == passwordInfo.getLetter()) {
                    letterCount++;
                }
            }
            if ( letterCount >= passwordInfo.getVal1() && letterCount <= passwordInfo.getVal2()) {
                validPasswordCount1++;
            }
        }
    }

    private void checkPasswordsPart2() {
        validPasswordCount2 = 0;
        for (PasswordInfo passwordInfo : passwordInfos) {
            char[] passwordChars = passwordInfo.getPassword().toCharArray();

            if ( passwordChars[passwordInfo.getVal1() - 1] == passwordInfo.getLetter() ^
                 passwordChars[passwordInfo.getVal2() - 1] == passwordInfo.getLetter()) {
                validPasswordCount2++;
            }
        }
    }
}
