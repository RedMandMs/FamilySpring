package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.IndividualService;

@Controller("/individualController/")
public class IndividualController {

    @Autowired
    private IndividualService individualService;

    @RequestMapping(value = "/createIndividual/", method = RequestMethod.POST)
    public String createIndividual(@ModelAttribute("changeForm") ChangeForm changeForm)
        throws ApplicationException {

        individualService.createIndividual(changeForm);

        return "redirect:/";
    }

    @RequestMapping(value = "/deleteIndividual/", method = RequestMethod.POST)
    public String deleteIndividual(@ModelAttribute("changeForm") ChangeForm changeForm)
        throws ApplicationException {

        individualService.deleteIndividual(changeForm);

        return "redirect:/";
    }

    @RequestMapping(value = "/renameIndividual/", method = RequestMethod.POST)
    public String renameIndividual(@ModelAttribute("changeForm") ChangeForm changeForm)
        throws ApplicationException {

        individualService.renameIndividual(changeForm);

        return "redirect:/";
    }

}
