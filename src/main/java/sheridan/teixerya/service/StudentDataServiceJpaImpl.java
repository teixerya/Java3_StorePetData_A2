package sheridan.teixerya.service;

import org.springframework.stereotype.Service;
import sheridan.teixerya.model.StudentForm;
import sheridan.teixerya.repository.StudentDataRepositoryJpa;
import sheridan.teixerya.repository.StudentEntityJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentDataServiceJpaImpl implements StudentDataService {

    private final StudentDataRepositoryJpa studentDataRepositoryJpa;

    StudentDataServiceJpaImpl(StudentDataRepositoryJpa studentDataRepositoryJpa){
        this.studentDataRepositoryJpa = studentDataRepositoryJpa;
    }

    private static void copyFormToEntity(StudentForm form, StudentEntityJpa student){
        //student.setId(form.getId());
        student.setFirstName(form.getFirstName());
        student.setLastName(form.getLastName());
        student.setProgramName(form.getProgramName());
        student.setProgramYear(form.getProgramYear());
        student.setProgramCoop(form.isProgramCoop());
        student.setProgramInternship(form.isProgramInternship());
    }

    private static void copyEntityToForm(StudentEntityJpa student, StudentForm form){
        form.setId(student.getId());
        form.setFirstName(student.getFirstName());
        form.setLastName(student.getLastName());
        form.setProgramName(student.getProgramName());
        form.setProgramYear(student.getProgramYear());
        form.setProgramCoop(student.isProgramCoop());
        form.setProgramInternship(student.isProgramInternship());
    }

    public void insertStudentForm(StudentForm form) {
        StudentEntityJpa student = new StudentEntityJpa();
        copyFormToEntity(form, student);
        student = studentDataRepositoryJpa.save(student);
        form.setId(student.getId());
    }

    public List<StudentForm> getAllStudentForms() {
        List<StudentForm> formList = new ArrayList<>();
        List<StudentEntityJpa> studentList = studentDataRepositoryJpa.findAll();
        for(StudentEntityJpa student: studentList){
            StudentForm form = new StudentForm();
            copyEntityToForm(student, form);
            formList.add(form);
        }
        return formList;
    }

    public void deleteAllStudentForms() {
        studentDataRepositoryJpa.deleteAll();
    }

    public void deleteStudentForm(int id) {
        studentDataRepositoryJpa.deleteById(id);
    }

    public StudentForm getStudentForm(int id) {
        Optional<StudentEntityJpa> result = studentDataRepositoryJpa.findById(id);
        if(result.isPresent()){
            StudentForm form = new StudentForm();
            StudentEntityJpa student = result.get();
            copyEntityToForm(student, form);
            return form;
        }
        return null;
    }

    public void updateStudentForm(StudentForm form) {
        Optional<StudentEntityJpa> result = studentDataRepositoryJpa.findById(form.getId());
        if(result.isPresent()){
            StudentEntityJpa student = result.get();
            copyFormToEntity(form, student);
            studentDataRepositoryJpa.save(student);
            //studentRepository.flush();
        }
    }
}

