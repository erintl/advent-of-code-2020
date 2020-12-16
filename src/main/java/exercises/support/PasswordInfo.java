package exercises.support;

public class PasswordInfo {
    private String password;
    private char letter;
    private int val1;
    private int val2;

    public PasswordInfo(String password, char letter, int val1, int val2) {
        this.password = password;
        this.letter = letter;
        this.val1 = val1;
        this.val2 = val2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getVal1() {
        return val1;
    }

    public void setVal1(int val1) {
        this.val1 = val1;
    }

    public int getVal2() {
        return val2;
    }

    public void setVal2(int val2) {
        this.val2 = val2;
    }
}
