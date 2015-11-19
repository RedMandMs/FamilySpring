package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.etu.mdp.family.domain.ChangePropertyForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.ObjectPropertyService;

@Controller("/objectProperty/")
public class ObjectPropertyController {

    @Autowired
    private ObjectPropertyService objectPropertyService;

    @RequestMapping(value = "/changeObjectProperty", method = RequestMethod.POST)
    public String changeObjectProperty(
        @ModelAttribute("changePropertyForm") ChangePropertyForm changePropertyTestForm)
            throws ApplicationException {
        objectPropertyService.setProperty(changePropertyTestForm.getNameIndividual(),
            changePropertyTestForm.getNameProperty(),
            changePropertyTestForm.getNewValue());
        return "redirect:/";
    }
}
