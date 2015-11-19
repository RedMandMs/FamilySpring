package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.etu.mdp.family.servises.OntologyClassesService;

@Controller("/classesOntology/")
public class ClassesOntologyController {

    @Autowired
    private OntologyClassesService ontologyClassesService;

    @RequestMapping(value = "/createClasses", method = RequestMethod.POST)
    public String createClasses() {
        ontologyClassesService.createClasses();
        return "redirect:/";
    }

}
