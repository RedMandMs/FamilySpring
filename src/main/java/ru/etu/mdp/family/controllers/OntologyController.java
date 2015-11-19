package ru.etu.mdp.family.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.etu.mdp.family.domain.OntologyForm;
import ru.etu.mdp.family.exeption.ApplicationException;
import ru.etu.mdp.family.servises.OntologyService;

@Controller("/ontology/")
public class OntologyController {

    @Autowired
    private OntologyService ontologyService;

    @RequestMapping(value = "/createEmptyOntology", method = RequestMethod.POST)
    public String createOntology(
        @ModelAttribute("ontologyForm") OntologyForm ontologyForm)
            throws ApplicationException {
        ontologyService.createEmptyOntology(ontologyForm);
        return "redirect:/succes";
    }

    @RequestMapping(value = "/readOntology", method = RequestMethod.POST)
    public String readOntology() throws ApplicationException {
        ontologyService.readOntology();
        return "redirect:/";
    }

}
