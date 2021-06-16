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
    private String firstName = "";

    @Column(name = "program_name")
    private String programName = "";

    @Column(name = "program_year")
    private String programYear = "Female";

    @Column(name = "program_coop")
    private Boolean programCoop = false;

    @Column(name = "program_internship")
    private Boolean programInternship = false;

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

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramYear() {
        return programYear;
    }

    public void setProgramYear(String programYear) {
        this.programYear = programYear;
    }

    public Boolean isProgramCoop() {
        return programCoop;
    }

    public void setProgramCoop(Boolean programCoop) {
        this.programCoop = programCoop;
    }

    public Boolean isProgramInternship() {
        return programInternship;
    }

    public void setProgramInternship(Boolean programInternship) {
        this.programInternship = programInternship;
    }
}

