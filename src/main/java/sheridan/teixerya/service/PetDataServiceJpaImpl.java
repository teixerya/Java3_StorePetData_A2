package sheridan.teixerya.service;

import sheridan.teixerya.repository.PetDataRepositoryJpa;
import sheridan.teixerya.repository.PetEntityJpa;
import sheridan.teixerya.model.PetForm;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetDataServiceJpaImpl implements PetDataService {

    private final PetDataRepositoryJpa petDataRepositoryJpa;

    PetDataServiceJpaImpl(PetDataRepositoryJpa petDataRepositoryJpa){
        this.petDataRepositoryJpa = petDataRepositoryJpa;
    }

    private static void copyFormToEntity(PetForm form, PetEntityJpa student){
        //student.setId(form.getId());
        student.setFirstName(form.getFirstName().trim());
        student.setProgramName(form.getProgramName());
        student.setProgramYear(form.getProgramYear());
        student.setProgramCoop(form.isProgramCoop());
        student.setProgramInternship(form.isProgramInternship());
    }

    private static void copyEntityToForm(PetEntityJpa student, PetForm form){
        form.setId(student.getId());
        form.setFirstName(student.getFirstName().trim());
        form.setProgramName(student.getProgramName());
        form.setProgramYear(student.getProgramYear());
        form.setProgramCoop(student.isProgramCoop());
        form.setProgramInternship(student.isProgramInternship());
    }

    public void insertStudentForm(PetForm form) {
        PetEntityJpa student = new PetEntityJpa();
        copyFormToEntity(form, student);
        student = petDataRepositoryJpa.save(student);
        form.setId(student.getId());
    }

    public List<PetForm> getAllStudentForms() {
        List<PetForm> formList = new ArrayList<>();
        List<PetEntityJpa> studentList = petDataRepositoryJpa.findAll();
        for(PetEntityJpa student: studentList){
            PetForm form = new PetForm();
            copyEntityToForm(student, form);
            formList.add(form);
        }
        return formList;
    }

    public void deleteAllStudentForms() {
        petDataRepositoryJpa.deleteAll();
    }

    public void deleteStudentForm(int id) {
        petDataRepositoryJpa.deleteById(id);
    }

    public PetForm getStudentForm(int id) {
        Optional<PetEntityJpa> result = petDataRepositoryJpa.findById(id);
        if(result.isPresent()){
            PetForm form = new PetForm();
            PetEntityJpa student = result.get();
            copyEntityToForm(student, form);
            return form;
        }
        return null;
    }

    public void updateStudentForm(PetForm form) {
        Optional<PetEntityJpa> result = petDataRepositoryJpa.findById(form.getId());
        if(result.isPresent()){
            PetEntityJpa student = result.get();
            copyFormToEntity(form, student);
            petDataRepositoryJpa.save(student);
            //studentRepository.flush();
        }
    }
}

