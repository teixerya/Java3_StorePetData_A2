package sheridan.teixerya.service;

import sheridan.teixerya.model.PetForm;

import java.util.List;

public interface PetDataService {

    void insertPetForm(PetForm form);

    List<PetForm> getAllPetForms();

    void deleteAllPetForms();

    void deleteStudentForm(int id);

    PetForm getPetForm(int id);

    void updateStudentForm(PetForm form);
}
