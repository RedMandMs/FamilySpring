package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.OntologyService;

@Controller("/")
public class MainController {

    @Autowired
    private OntologyService ontologyService;

    @RequestMapping("/")
    public ModelAndView home() throws ApplicationException {
        if (OntologyService.owlModel == null) {
            ontologyService.readDefoultOntology();
        }
        ModelAndView modelAndView = new ModelAndView("index");
        ChangeForm changeForm = new ChangeForm();
        modelAndView.addObject("changeForm", changeForm);
        return modelAndView;
    }

    @RequestMapping("/succes")
    public ModelAndView sucsess() {
        ModelAndView modelAndView = new ModelAndView("succes");
        return modelAndView;
    }

}
