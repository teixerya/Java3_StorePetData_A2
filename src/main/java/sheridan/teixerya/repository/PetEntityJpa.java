package sheridan.teixerya.repository;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pet")
public class PetEntityJpa {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName = ""
    @Column(name = "pet_kind")
    private String petKind = "";

    @Column(name = "gender")
    private String gender = "Female";

    @Column(name = "vaccine")
    private Boolean vaccine = false;


    public PetEntityJpa(){
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setPetKind(String petKind) {
        this.petKind = petKind;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String programYear) {
        this.gender = gender;
    }

    public Boolean isVaccine() {
        return vaccine;
    }

    public void setVaccine(Boolean vaccine) {
        this.vaccine = vaccine;
    }

}

