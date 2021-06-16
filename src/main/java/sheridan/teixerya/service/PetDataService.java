package sheridan.teixerya.service;

import sheridan.teixerya.model.PetForm;

import java.util.List;

public interface PetDataService {

    void insertStudentForm(PetForm form);

    List<PetForm> getAllStudentForms();

    void deleteAllStudentForms();

    void deleteStudentForm(int id);

    PetForm getStudentForm(int id);

    void updateStudentForm(PetForm form);
}
