package exercises;

import exercises.support.BoardingPass;
import exercises.support.Passport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryBoarding {
    public static final String FILENAME = "input/boarding_passes.txt";
    List<BoardingPass> boardingPasses;
    List<Integer> seatIds;

    public BinaryBoarding() {
    }

    public void getMaxSeatId() {
        boardingPasses = new ArrayList<>();
        seatIds = new ArrayList<>();
        readFile();
        int maxSeatId = findMaxSeatId();
        System.out.println("The max seat id is: " + maxSeatId);
    }

    public void getMissingSeatId() {
        boardingPasses = new ArrayList<>();
        seatIds = new ArrayList<>();
        readFile();
        int missingSeatId = findMissingSeatId();

        System.out.println("Missing seat id: " + missingSeatId);

    }

    private int findMissingSeatId() {
        int missingSeatId = 0;
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 8; j++) {
                int seatId = i * 8 + j;
                if (!seatIds.contains(seatId) && seatIds.contains(seatId + 1) && seatIds.contains(seatId - 1)) {
                    missingSeatId = seatId;
                }
            }
        }
        return  missingSeatId;
    }

    private void readFile() {
        boardingPasses = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILENAME));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                BoardingPass boardingPass = new BoardingPass(line);
                boardingPasses.add(boardingPass);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        setSeatIds();
    }

    private int findMaxSeatId() {
        int maxSeatId = 0;

        for (BoardingPass boardingPass : boardingPasses) {
            int seatId = boardingPass.getSeatId();
            if (seatId > maxSeatId) {
                maxSeatId = seatId;
            }
        }
        return maxSeatId;
    }

    private void setSeatIds() {
        for (BoardingPass boardingPass : boardingPasses) {
            seatIds.add(boardingPass.getSeatId());
        }
    }
}
