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

    @RequestMapping(value = "/setObjectPropertyValue/", method = RequestMethod.POST)
    public String setObjectPropertyValue(
        @ModelAttribute("changePropertyForm") ChangePropertyForm changePropertyForm)
            throws ApplicationException {
        objectPropertyService.setPropertyValue(changePropertyForm);
        return "redirect:/";
    }

    @RequestMapping(value = "/removeObjectPropertyValue/", method = RequestMethod.POST)
    public String removeObjectPropertyValue(
        @ModelAttribute("changePropertyForm") ChangePropertyForm changePropertyForm)
            throws ApplicationException {
        objectPropertyService.removeOnePropertyValue(changePropertyForm);
        return "redirect:/";
    }

    @RequestMapping(value = "/clearObjectPropertyValue/", method = RequestMethod.POST)
    public String clearObjectPropertyValue(
        @ModelAttribute("changePropertyForm") ChangePropertyForm changePropertyForm)
            throws ApplicationException {
        objectPropertyService.clearPropertyValue(changePropertyForm);
        return "redirect:/";
    }
}
