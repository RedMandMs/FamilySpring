package ru.etu.mdp.family.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.domain.OntologyForm;

@Controller("/")
public class MainController {

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("index");
        OntologyForm ontologyForm = new OntologyForm();
        modelAndView.addObject("ontologyForm", ontologyForm);
        modelAndView.addObject("changePropertyForm", new ChangeForm());
        return modelAndView;
    }

    @RequestMapping("/succes")
    public ModelAndView sucsess() {
        ModelAndView modelAndView = new ModelAndView("succes");
        return modelAndView;
    }

}
