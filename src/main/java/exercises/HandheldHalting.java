package exercises;

import exercises.support.Instruction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandheldHalting {
    public static final String FILENAME = "input/handheld_halting.txt";
    List<Instruction> instructions;
    int ip;
    int acc;

    public HandheldHalting() {
        ip = 0;
        acc = 0;
    }

    public void printAccValue() {
        readFile();
//        runProgram();
        findDuplicate();
    }

    private void readFile() {
        instructions = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILENAME));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processLine(line);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        String[] parts = line.split(" ");
        Instruction instruction = new Instruction(parts[0], Integer.parseInt(parts[1]));
        instructions.add(instruction);
    }

    private void runProgram() {
        List<Integer> exeInstructions = new ArrayList<>();
        boolean dup = false;
        while (ip >= 0 && ip < instructions.size() && !dup) {
            Instruction instruction = instructions.get(ip);
            exeInstructions.add(ip);
            runInstruction(instruction);
            if (exeInstructions.contains(ip)) {
                dup = true;
                System.out.println("acc: " + acc);
            }
        }
    }

    private void runInstruction(Instruction instruction) {
        String type = instruction.getType();
        int value = instruction.getValue();

        System.out.println(instruction + ", ip = " + ip + ", acc = " + acc);
        switch (type) {
//            case "nop" -> ip++;
            case "acc" -> {
                ip++;
                acc += value;
            }
            case "jmp" -> ip += value;
            default -> ip++;
        }
    }

    private void findDuplicate() {
        for (int i = 0; i < instructions.size(); i++) {
            ip = 0;
            acc = 0;
            List<Integer> exeInstructions = new ArrayList<>();
            boolean dup = false;
            while (ip >= 0 && ip < instructions.size() && !dup) {
                Instruction instruction = instructions.get(ip);
                exeInstructions.add(ip);
                runInstructionSwap(instruction, i);
                if (exeInstructions.contains(ip)) {
                    dup = true;
                }
            }
            if (!dup) {
                System.out.println("ran to completion, acc: " + acc);
            }
        }
    }

    private void runInstructionSwap(Instruction instruction, int iSwap) {
        String type = instruction.getType();
        int value = instruction.getValue();

        if (ip == iSwap) {
            if (type.equals("nop")) {
                type = "jmp";
            } else if (type.equals("jmp")) {
                type = "nop";
            }
        }

        switch (type) {
            case "acc" -> {
                ip++;
                acc += value;
            }
            case "jmp" -> ip += value;
            default -> ip++;
        }
    }
}
