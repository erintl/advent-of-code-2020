package exercises.support;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passport {
    public static final List<String> EYE_COLORS = new ArrayList<>(List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));

    private int byr;
    private int iyr;
    private int eyr;
    private String hgt;
    private String hcl;
    private String ecl;
    private String pid;
    private String cid;
    private boolean valid;
    private boolean complete;

    public Passport() {
        this.byr = 0;
        this.iyr = 0;
        this.eyr = 0;
        this.hgt = "";
        this.hcl = "";
        this.ecl = "";
        this.pid = "";
        this.cid = "";
        this.valid = false;
        this.complete = false;
    }

    public void addData(String field, String value) {
        switch (field) {
            case "byr" -> byr = Integer.parseInt(value);
            case "iyr" -> iyr = Integer.parseInt(value);
            case "eyr" -> eyr = Integer.parseInt(value);
            case "hgt" -> hgt = value;
            case "hcl" -> hcl = value;
            case "ecl" -> ecl = value;
            case "pid" -> pid = value;
            case "cid" -> cid = value;
            default -> throw new IllegalArgumentException("Invalid field specified: " + field);
        }
        checkComplete();
        if (complete) {
            validate();
        }
    }

    private void checkComplete() {
        complete = (byr != 0) && (iyr != 0) && (eyr != 0) && !hgt.equals("") && !hcl.equals("") &&
                   !ecl.equals("") && !pid.equals("");
    }

    private void validate() {
        valid  = isByrValid() && isIyrValid() && isEyrValid() && isHgtValid() && isEclValid() && isHclValid() &&
                 isPidValid();
    }

    private boolean isByrValid() {
        return (byr >= 1920) && (byr <= 2002);
    }

    private boolean isIyrValid() {
        return (iyr >= 2010) && (iyr <= 2020);
    }

    private boolean isEyrValid() {
        return (eyr >= 2020) && (eyr <= 2030);
    }

    public boolean isHgtValid() {
        boolean valid = false;
        int cmSuffixLocation = hgt.indexOf("cm");
        int inSuffixLocation = hgt.indexOf("in");
        if (cmSuffixLocation != -1) {
            int hgtCm = Integer.parseInt(hgt.substring(0, cmSuffixLocation));
            if ((hgtCm >= 150) && (hgtCm <= 193)) {
                valid = true;
            }
        } else if (inSuffixLocation != -1) {
            int hgtIn = Integer.parseInt(hgt.substring(0, inSuffixLocation));
            if ((hgtIn >= 59) && (hgtIn <= 76)) {
                valid = true;
            }
        }
        return valid;
    }

    public boolean isHclValid() {
        String match = "^#[0-9a-f]{6}$";
        return Pattern.compile(match).matcher(hcl).matches();
    }

    public boolean isPidValid() {
        String match = "^[0-9]{9}$";
        return Pattern.compile(match).matcher(pid).matches();
    }

    private boolean isEclValid() {
        return EYE_COLORS.contains(ecl);
    }

    public boolean isValid() {
        return valid;
    }

    public boolean isComplete() {
        return complete;
    }
}
