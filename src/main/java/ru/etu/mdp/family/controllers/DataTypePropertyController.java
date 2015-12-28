package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.DataTypePropertyService;

@Controller("/dataTypeProperty/")
public class DataTypePropertyController {

    @Autowired
    private DataTypePropertyService dataTypePropertyService;

    @RequestMapping(value = "/setDateTypeProperty/", method = RequestMethod.POST)
    public String setDataTypeProperty(
        @ModelAttribute("nameIndividual") String nameIndividual,
        @ModelAttribute("nameProperty") String nameProperty,
        @ModelAttribute("valueProperty") String valueProperty)
            throws ApplicationException {
        ChangeForm changeForm = new ChangeForm();
        changeForm.setNameIndividual(nameIndividual);
        changeForm.setNameProperty(nameProperty);
        changeForm.setNewValue(valueProperty);
        dataTypePropertyService.setPropertyValue(changeForm);
        return "redirect:/getIndividual/" + nameIndividual;

    }

    @RequestMapping(value = "/deleteDateTypeProperty", method = RequestMethod.POST)
    public String deleteDataTypeProperty(
        @ModelAttribute("changePropertyForm") ChangeForm changePropertyTestForm)
            throws ApplicationException {
        dataTypePropertyService.deletePropertyValue(changePropertyTestForm);
        return "redirect:/";
    }

}
