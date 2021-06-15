package sheridan.teixerya.model;

import javax.validation.constraints.*;
import java.io.Serializable;

public class StudentForm implements Serializable {

    private int id = 0;

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z]*")
    private String firstName = "";

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z]*")
    private String lastName = "";

    @NotBlank
    @Pattern(regexp = "(Computer Programmer|Systems Technology|Engineering Technician|Systems Technician)?")
    private String programName = "";

    @NotNull
    @Min(1)
    @Max(3)
    private int programYear = 1;

    private boolean programCoop = false;

    private boolean programInternship = false;

    public StudentForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String program) {
        this.programName = program;
    }

    public int getProgramYear() {
        return programYear;
    }

    public void setProgramYear(int year) {
        this.programYear = year;
    }

    public boolean isProgramCoop() {
        return programCoop;
    }

    public void setProgramCoop(boolean coop) {
        this.programCoop = coop;
    }

    public boolean isProgramInternship() {
        return programInternship;
    }

    public void setProgramInternship(boolean internship) {
        this.programInternship = internship;
    }

}


