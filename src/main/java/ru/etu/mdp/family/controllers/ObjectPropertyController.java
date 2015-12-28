package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.ObjectPropertyService;

@Controller("objectProperty/")
public class ObjectPropertyController {

    @Autowired
    private ObjectPropertyService objectPropertyService;

    @RequestMapping(value = "/setObjectPropertyValue/", method = RequestMethod.POST)
    public String setObjectPropertyValue(
        @ModelAttribute("changePropertyForm") ChangeForm changePropertyForm)
            throws ApplicationException {
        objectPropertyService.getAllObjectProperties(changePropertyForm);
        objectPropertyService.setPropertyValue(changePropertyForm);
        return "redirect:/";
    }

    @RequestMapping(value = "/addObjectPropertyValue/", method = RequestMethod.POST)
    public String addObjectPropertyValue(
        @ModelAttribute("changePropertyForm") ChangeForm changePropertyForm)
            throws ApplicationException {
        objectPropertyService.addPropertyValue(changePropertyForm);
        return "redirect:/";
    }

    @RequestMapping(value = "/removeOneObjectPropertyValue/", method = RequestMethod.POST)
    public String removeOneObjectPropertyValue(
        @ModelAttribute("changePropertyForm") ChangeForm changePropertyForm)
            throws ApplicationException {
        objectPropertyService.removeOnePropertyValue(changePropertyForm);
        return "redirect:/";
    }

    @RequestMapping(value = "/clearObjectPropertyValue/", method = RequestMethod.POST)
    public String clearObjectPropertyValue(
        @ModelAttribute("changePropertyForm") ChangeForm changePropertyForm)
            throws ApplicationException {
        objectPropertyService.clearPropertyValue(changePropertyForm);
        return "redirect:/";
    }
}
