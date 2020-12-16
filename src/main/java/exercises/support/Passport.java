package exercises.support;

public class Passport {
    private String byr;
    private String iyr;
    private String eyr;
    private String hgt;
    private String hcl;
    private String ecl;
    private String pid;
    private String cid;
    private boolean valid;

    public Passport() {
        this.byr = "";
        this.iyr = "";
        this.eyr = "";
        this.hgt = "";
        this.hcl = "";
        this.ecl = "";
        this.pid = "";
        this.cid = "";
        this.valid = false;
    }

    public void addData(String field, String value) {
        switch (field) {
            case "byr" -> byr = value;
            case "iyr" -> iyr = value;
            case "eyr" -> eyr = value;
            case "hgt" -> hgt = value;
            case "hcl" -> hcl = value;
            case "ecl" -> ecl = value;
            case "pid" -> pid = value;
            case "cid" -> cid = value;
            default -> throw new IllegalArgumentException("Invalid field specified: " + field);
        }
        validate();
    }

    private void validate() {
        valid = !byr.equals("") && !iyr.equals("") && !eyr.equals("") && !hgt.equals("") && !hcl.equals("") &&
                !ecl.equals("") && !pid.equals("");
    }

    public boolean isValid() {
        return valid;
    }
}
