package ru.etu.mdp.family.presentation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.etu.mdp.family.domain.OntologyForm;
import ru.etu.mdp.family.servises.OntologyClassesService;
import ru.etu.mdp.family.servises.OntologyServis;

@Controller("/")
@EnableAutoConfiguration
@Configuration
@ComponentScan("ru.etu.mdp.family.servises")
public class WebAppConfig{
 
	@Autowired
	private OntologyServis ontologyServis;
	
	@Autowired
	private OntologyClassesService ontologyClassesService;
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(WebAppConfig.class, args);
    }
	
	@RequestMapping("/")
    public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("index");
		OntologyForm ontologyForm = new OntologyForm();
		modelAndView.addObject("ontologyForm", ontologyForm);
		return modelAndView;
    }
	
	@RequestMapping("/succes")
    public ModelAndView sucsess() {
		ModelAndView modelAndView = new ModelAndView("succes");
		return modelAndView;
    }
	
	@RequestMapping(value = "/createOntology", method = RequestMethod.POST)
	public String createOntology(@ModelAttribute("ontologyForm") OntologyForm ontologyForm){
		ontologyServis.createEmptyOntology(ontologyForm);
		return "redirect:/succes";
	}
	
	@RequestMapping(value = "/createClasses", method = RequestMethod.POST)
	public String createClasses(){
		ontologyClassesService.createClasses();
		return "redirect:/";
	}
 
}
