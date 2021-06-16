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

    private static void copyFormToEntity(PetForm form, PetEntityJpa pet){
        //pet.setId(form.getId());
        pet.setFirstName(form.getFirstName().trim());
        pet.setProgramName(form.getPetKind());
        pet.setProgramYear(form.getGender());
        pet.setProgramCoop(form.isVaccine());
        pet.setProgramInternship(form.isProgramInternship());
    }

    private static void copyEntityToForm(PetEntityJpa pet, PetForm form){
        form.setId(pet.getId());
        form.setFirstName(pet.getFirstName().trim());
        form.setPetKind(pet.getProgramName());
        form.setGender(pet.getProgramYear());
        form.setVaccine(pet.isProgramCoop());
        form.setProgramInternship(pet.isProgramInternship());
    }

    public void insertPetForm(PetForm form) {
        PetEntityJpa pet = new PetEntityJpa();
        copyFormToEntity(form, pet);
        pet = petDataRepositoryJpa.save(pet);
        form.setId(pet.getId());
    }

    public List<PetForm> getAllPetForms() {
        List<PetForm> formList = new ArrayList<>();
        List<PetEntityJpa> petList = petDataRepositoryJpa.findAll();
        for(PetEntityJpa pet: petList){
            PetForm form = new PetForm();
            copyEntityToForm(pet, form);
            formList.add(form);
        }
        return formList;
    }

    public void deleteAllPetForms() {
        petDataRepositoryJpa.deleteAll();
    }

    public void deletePetForm(int id) {
        petDataRepositoryJpa.deleteById(id);
    }

    public PetForm getPetForm(int id) {
        Optional<PetEntityJpa> result = petDataRepositoryJpa.findById(id);
        if(result.isPresent()){
            PetForm form = new PetForm();
            PetEntityJpa pet = result.get();
            copyEntityToForm(pet, form);
            return form;
        }
        return null;
    }

    public void updatePetForm(PetForm form) {
        Optional<PetEntityJpa> result = petDataRepositoryJpa.findById(form.getId());
        if(result.isPresent()){
            PetEntityJpa pet = result.get();
            copyFormToEntity(form, pet);
            petDataRepositoryJpa.save(pet);
            //petRepository.flush();
        }
    }
}

