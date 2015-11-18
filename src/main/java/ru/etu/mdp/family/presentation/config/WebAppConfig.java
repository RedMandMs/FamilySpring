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

import ru.etu.mdp.family.domain.ChangePropertyForm;
import ru.etu.mdp.family.domain.OntologyForm;
import ru.etu.mdp.family.servises.DataTypePropertyService;
import ru.etu.mdp.family.servises.ObjectPropertyService;
import ru.etu.mdp.family.servises.OntologyClassesService;
import ru.etu.mdp.family.servises.OntologyService;

@Controller("/")
@EnableAutoConfiguration
@Configuration
@ComponentScan("ru.etu.mdp.family.servises")
public class WebAppConfig{
 
	@Autowired
	private OntologyService ontologyServis;
	
	@Autowired
	private DataTypePropertyService dataTypePropertyService;
	
	@Autowired
	private OntologyClassesService ontologyClassesService;
	
	@Autowired
	private ObjectPropertyService objectPropertyService;
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(WebAppConfig.class, args);
    }
	
	@RequestMapping("/")
    public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("index");
		OntologyForm ontologyForm = new OntologyForm();
		modelAndView.addObject("ontologyForm", ontologyForm);
		modelAndView.addObject("changePropertyForm", new ChangePropertyForm());		
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

	@RequestMapping(value = "/readOntology", method = RequestMethod.POST)
	public String readOntology(){
		ontologyServis.readOntology();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/createClasses", method = RequestMethod.POST)
	public String createClasses(){
		ontologyClassesService.createClasses();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/changeDateTypeProperty", method = RequestMethod.POST)
	public String changeDataTypeProperty(@ModelAttribute("changePropertyForm") ChangePropertyForm changePropertyTestForm){
		dataTypePropertyService.setProperty( 
				changePropertyTestForm.getNameIndividual(), 
				changePropertyTestForm.getNameProperty(), 
				changePropertyTestForm.getNewValue());
		return "redirect:/";
	}
	
	@RequestMapping(value = "/changeObjectProperty", method = RequestMethod.POST)
	public String changeObjectProperty(@ModelAttribute("changePropertyForm") ChangePropertyForm changePropertyTestForm){
		objectPropertyService.setProperty( 
				changePropertyTestForm.getNameIndividual(), 
				changePropertyTestForm.getNameProperty(), 
				changePropertyTestForm.getNewValue());
		return "redirect:/";
	}
 
}
