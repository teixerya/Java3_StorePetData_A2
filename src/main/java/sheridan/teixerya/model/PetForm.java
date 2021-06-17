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
    private String petKind = "Dog";

    private String genderValue = "Female";

    private boolean vaccine = false;



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



    public String getPetKind() {
        return petKind;
    }

    public void setPetKind(String program) {
        this.petKind = program;
    }

    public String getGenderValue() {
        System.out.println("getGenderValue method was called " + genderValue );
        return genderValue;
    }

    public void setGenderValue(String genderInput) {
        this.genderValue = genderInput;
        System.out.println("The new gender is " + genderValue);
    }

    public boolean isVaccine() {
        return vaccine;
    }

    public void setVaccine(boolean coop) {
        this.vaccine = coop;
    }


}


