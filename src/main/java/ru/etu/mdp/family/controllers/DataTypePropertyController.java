package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.etu.mdp.family.domain.ChangePropertyForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.DataTypePropertyService;

@Controller("/dataTypeProperty/")
public class DataTypePropertyController {

    @Autowired
    private DataTypePropertyService dataTypePropertyService;

    @RequestMapping(value = "/changeDateTypeProperty", method = RequestMethod.POST)
    public String changeDataTypeProperty(
        @ModelAttribute("changePropertyForm") ChangePropertyForm changePropertyTestForm)
            throws ApplicationException {
        dataTypePropertyService.setPropertyValue(changePropertyTestForm);
        return "redirect:/";

    }

}
