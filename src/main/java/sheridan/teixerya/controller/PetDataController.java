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

    @GetMapping("/AddStudent")
    public ModelAndView addStudent(){
        logger.trace("addStudent() is called");
        ModelAndView modelAndView =
                new ModelAndView("AddPet",
                        "form", new PetForm());
        modelAndView.addObject("programs", programs);
        return modelAndView;
    }

    @PostMapping("/InsertStudent")
    public String insertStudent(
            @Validated @ModelAttribute("form") PetForm form,
            BindingResult bindingResult,
            Model model){
        logger.trace("insertStudent() is called");
        // checking for the input validation errors
        if (bindingResult.hasErrors()) {
            logger.trace("input validation errors");
            //model.addAttribute("form", form);
            model.addAttribute("programs", programs);
            return "AddPet";
        } else {
            logger.trace("the user inputs are correct");
            petDataService.insertStudentForm(form);
            return "redirect:ConfirmInsert/" + form.getId();
        }
    }

    @GetMapping("/ConfirmInsert/{id}")
    public String confirmInsert(@PathVariable(name = "id") String strId, Model model){
        logger.trace("confirmInsert() is called");
        try {
            int id = Integer.parseInt(strId);
            logger.trace("looking for the data in the database");
            PetForm form = petDataService.getStudentForm(id);
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

    @GetMapping("/ListStudents")
    public ModelAndView listStudents() {
        logger.trace("listStudents() is called");
        List<PetForm> list = petDataService.getAllStudentForms();
        return new ModelAndView("ListPets",
                "pets", list);
    }

    @GetMapping("/DeleteAll")
    public String deleteAll(){
        logger.trace("deleteAll() is called");
        petDataService.deleteAllStudentForms();
        return "redirect:ListStudents";
    }

    @GetMapping("StudentDetails/{id}")
    public String studentDetails(@PathVariable String id, Model model){
        logger.trace("studentDetails() is called");
        try {
            PetForm form = petDataService.getStudentForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("pet", form);
                return "PetDetails"; // show the student data in the form to edit
            } else {
                logger.trace("no data for this id=" + id);
                return "DataNotFound";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
            return "DataNotFound";
        }
    }

    // a user clicks "Delete" link (in the table) to "DeleteStudent"
    @GetMapping("/DeleteStudent")
    public String deleteStudent(@RequestParam String id, Model model) {
        logger.trace("deleteStudent() is called");
        try {
            PetForm form = petDataService.getStudentForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("pet", form);
                return "DeletePet"; // ask "Do you really want to remove?"
            } else {
                return "redirect:ListStudents";
            }
        } catch (NumberFormatException e) {
            return "redirect:ListStudents";
        }
    }

    // a user clicks "Remove Record" button in "DeleteStudent" page,
    // the form submits the data to "RemoveStudent"
    @PostMapping("/RemoveStudent")
    public String removeStudent(@RequestParam String id) {
        logger.trace("removeStudent() is called");
        try {
            petDataService.deleteStudentForm(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
        }
        return "redirect:ListStudents";
    }

    // a user clicks "Edit" link (in the table) to "EditStudent"
    @GetMapping("/EditStudent")
    public String editStudent(@RequestParam String id, Model model) {
        logger.trace("editStudent() is called");
        try {
            PetForm form = petDataService.getStudentForm(Integer.parseInt(id));
            if (form != null) {
                model.addAttribute("form", form);
                model.addAttribute("programs", programs);
                return "EditPet";
            } else {
                logger.trace("no data for this id=" + id);
                return "redirect:ListStudents";
            }
        } catch (NumberFormatException e) {
            logger.trace("the id is missing or not an integer");
            return "redirect:ListStudents";
        }
    }

    // the form submits the data to "UpdateStudent"
    @PostMapping("/UpdateStudent")
    public String updateStudent(
            @Validated @ModelAttribute("form") PetForm form,
            BindingResult bindingResult,
            Model model) {
        logger.trace("updateStudent() is called");
        // checking for the input validation errors
        if (bindingResult.hasErrors()) {
            logger.trace("input validation errors");
            //model.addAttribute("form", form);
            model.addAttribute("programs", programs);
            return "EditPet";
        } else {
            logger.trace("the user inputs are correct");
            petDataService.updateStudentForm(form);
            logger.debug("id = " + form.getId());
            return "redirect:StudentDetails/" + form.getId();
        }
    }
}
