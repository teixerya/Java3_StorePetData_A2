package sheridan.teixerya.controller;

import sheridan.teixerya.model.PetForm;
import sheridan.teixerya.service.PetDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class PetDataController {

    private final Logger logger = LoggerFactory.getLogger(PetDataController.class);

    private static final String[] programs = {

            "Cat", "Dog",
            "Rabbit"};

    private final PetDataService petDataService;

    public PetDataController(PetDataService petDataService){
        this.petDataService = petDataService;
    }

    @GetMapping(value={"/", "/Index"})
    public String index(){
        logger.trace("index() is called");
        return "Index";
    }

    @GetMapping("/AddPet")
    public ModelAndView addPet(){
        logger.trace("addPet() is called");
        ModelAndView modelAndView =
                new ModelAndView("AddPet",
                        "form", new PetForm());
        modelAndView.addObject("programs", programs);
        return modelAndView;
    }

    @PostMapping("/InsertPet")
    public String insertPet(
            @Validated @ModelAttribute("form") PetForm form,
            BindingResult bindingResult,
            Model model){
        logger.trace("insertPet() is called");
        // checking for the input validation errors
        if (bindingResult.hasErrors()) {
            logger.trace("input validation errors");
            //model.addAttribute("form", form);
            model.addAttribute("programs", programs);
            return "AddPet";
        } else {
            logger.trace("the user inputs are correct");
            petDataService.insertPetForm(form);
            return "redirect:ConfirmInsert/" + form.getId();
        }
    }

    @GetMapping("/ConfirmInsert/{id}")
    public String confirmInsert(@PathVariable(name = "id") String strId, Model model){
        logger.trace("confirmInsert() is called");
        try {
            int id = Integer.parseInt(strId);
            logger.trace("looking for the data in the database");
            PetForm form = petDataService.getPetForm(id);
            if (form == null) {
                logger.trace("no data for this id=" + id);
                return "DataNotFound";
            } else {
                logger.trace("showing the data");
                model.addAttribute("form", form);
                return "ConfirmInsert";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id in not an integer");
            return "DataNotFound";
        }
    }

    @GetMapping("/ListPets")
    public ModelAndView listPets() {
        logger.trace("listPets() is called");
        List<PetForm> list = petDataService.getAllPetForms();
        return new ModelAndView("ListPets",
                "pets", list);
    }

    @GetMapping("/DeleteAll")
    public String deleteAll(){
        logger.trace("deleteAll() is called");
        petDataService.deleteAllPetForms();
        return "redirect:ListPets";
    }

    @GetMapping("PetDetails/{id}")
    public String petDetails(@PathVariable String id, Model model){
        logger.trace("petDetails() is called");
        try {
            PetForm form = petDataService.getPetForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("pet", form);
                return "PetDetails"; // show the pet data in the form to edit
            } else {
                logger.trace("no data for this id=" + id);
                return "DataNotFound";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
            return "DataNotFound";
        }
    }

    // a user clicks "Delete" link (in the table) to "DeletePet"
    @GetMapping("/DeletePet")
    public String deletePet(@RequestParam String id, Model model) {
        logger.trace("deletePet() is called");
        try {
            PetForm form = petDataService.getPetForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("pet", form);
                return "DeletePet"; // ask "Do you really want to remove?"
            } else {
                return "redirect:ListPets";
            }
        } catch (NumberFormatException e) {
            return "redirect:ListPets";
        }
    }

    // a user clicks "Remove Record" button in "DeletePet" page,
    // the form submits the data to "RemovePet"
    @PostMapping("/RemovePet")
    public String removePet(@RequestParam String id) {
        logger.trace("removePet() is called");
        try {
            petDataService.deletePetForm(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
        }
        return "redirect:ListPets";
    }

    // a user clicks "Edit" link (in the table) to "EditPet"
    @GetMapping("/EditPet")
    public String editPet(@RequestParam String id, Model model) {
        logger.trace("editPet() is called");
        try {
            PetForm form = petDataService.getPetForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("form", form);
                model.addAttribute("programs", programs);
                return "EditPet";
            } else {
                logger.trace("no data for this id=" + id);
                return "redirect:ListPets";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
            return "redirect:ListPets";
        }
    }

    // the form submits the data to "UpdatePet"
    @PostMapping("/UpdatePet")
    public String updatePet(
            @Validated @ModelAttribute("form") PetForm form,
            BindingResult bindingResult,
            Model model) {
        logger.trace("updatePet() is called");
        // checking for the input validation errors
        if (bindingResult.hasErrors()) {
            logger.trace("input validation errors");
            //model.addAttribute("form", form);
            model.addAttribute("programs", programs);
            return "EditPet";
        } else {
            logger.trace("the user inputs are correct");
            petDataService.updatePetForm(form);
            logger.debug("id = " + form.getId());
            return "redirect:StudentDetails/" + form.getId();
        }
    }
}
