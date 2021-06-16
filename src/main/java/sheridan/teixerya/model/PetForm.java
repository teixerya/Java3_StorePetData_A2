package sheridan.teixerya.model;

import javax.validation.constraints.*;
import java.io.Serializable;

public class PetForm implements Serializable {

    private int id = 0;

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z]*")
    private String firstName = "";

    @NotBlank
    @Pattern(regexp = "(Cat|Dog|Rabbit)?")
    private String programName = "Dog";

    private String programYear = "Female";

    private boolean programCoop = false;

    private boolean programInternship = false;

    public PetForm() {
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



    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String program) {
        this.programName = program;
    }

    public String getProgramYear() {
        return programYear;
    }

    public void setProgramYear(String year) {
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


