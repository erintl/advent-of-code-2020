package exercises.support;

import java.util.LinkedList;
import java.util.List;

public class BoardingPass {
    private final String seatSpecification;

    public BoardingPass(String seatSpecification) {
        this.seatSpecification = seatSpecification;
    }

    public String getSeatSpecification() {
        return seatSpecification;
    }

    public String getRowPart() {
        return seatSpecification.substring(0, 7);
    }

    public String getColumnPart() {
        return seatSpecification.substring(7);
    }

    public int getRow() {
        List<Character> rowSpecification = getCharacterList(getRowPart());
        return findNumber(rowSpecification, 0, 127);
    }

    public int getColumn() {
        List<Character> columnSpecification = getCharacterList(getColumnPart());
        return findNumber(columnSpecification, 0, 7);
    }
    public int getSeatId() {
        return getRow() * 8 + getColumn();
    }

    private int findNumber(List<Character> specification, int min, int max) {
        char target = specification.remove(0);
        if (specification.isEmpty()) {
            if (target == 'F' || target == 'L') {
                return min;
            } else {
                return max;
            }
        } else {
            if (target == 'F' || target == 'L') {
                return findNumber(specification, min, ((max - min) / 2) + min);
            } else {
                return findNumber(specification, ((max - min) / 2) + min + 1, max);
            }
        }
    }

    public static List<Character> getCharacterList(String s) {
        List<Character> characterList =  new LinkedList<>();

        for (char c : s.toCharArray()) {
            characterList.add(c);
        }
        return  characterList;
    }

    @Override
    public String toString() {
        return seatSpecification + ": row " + getRow() + ", column " + getColumn() + ", seat id " + getSeatId();
    }
}
