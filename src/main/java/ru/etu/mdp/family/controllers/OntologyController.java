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

    @RequestMapping(value = "/readDefoultOntology", method = RequestMethod.POST)
    public String readDefoultOntology() throws ApplicationException {
        ontologyService.readDefoultOntology();
        return "redirect:/";
    }

    @RequestMapping(value = "/readOntology", method = RequestMethod.POST)
    public String readOntology(@ModelAttribute("OntologyForm") OntologyForm ontologyForm)
        throws ApplicationException {
        ontologyService.readOntology(ontologyForm);
        return "redirect:/";
    }

    @RequestMapping(value = "/saveOntology", method = RequestMethod.POST)
    public String saveOntology() throws ApplicationException {
        ontologyService.saveOntology();
        return "redirect:/";
    }

    @RequestMapping(value = "/saveInNewFile", method = RequestMethod.POST)
    public String saveInNewFile() throws ApplicationException {
        ontologyService.saveInNewFile();
        return "redirect:/";
    }

}
