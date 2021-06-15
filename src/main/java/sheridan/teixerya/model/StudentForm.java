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
    @Pattern(regexp = "(Cat|Dog|Rabbit)?")
    private String programName = "Dog";

//    @NotNull
//    @Min(1)
//    @Max(2)
    private String programYear = "Female";

    private String gender = "Male";

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

    public String getProgramYear() {
        return programYear;
    }

    public void setProgramYear(String year) {
        this.programYear = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(int year) {
        if(year == 1){
            gender = "Male";
        }else{
            gender = "Female";
        }

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


