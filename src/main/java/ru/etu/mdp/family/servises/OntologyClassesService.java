package ru.etu.mdp.family.servises;

import java.io.File;

import org.springframework.stereotype.Service;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;

@Service("ontologyClassesService")
public class OntologyClassesService {

	public void createClasses(){
		// a variable of ontology
	    JenaOWLModel owlModel = null;
	    // a namespace of ontology
	    String owlURI = "http://www.owl-ontologies.com/FamilySpring.owl#";
	    // a name of old file of ontology
	    String owlOLDFileName = "FamilySpring.owl";
	    // a variable of old file of ontology
	    File owlOLDFile = new File(owlOLDFileName);
	    // a name of new file of ontology
	    String owlNEWFileName = "FamilySpringNew.owl";
	    // a variable of new file of ontology
	    File owlNEWFile = new File(owlNEWFileName);
	    
	    // a name of class of ontology
	    String Man_class_name = "Man";
	    // a variable of class of ontology
	    OWLNamedClass Man_class = null;
	    
	    // a name of class of ontology
	    String Woman_class_name = "Woman";
	    // a variable of class of ontology
	    OWLNamedClass Woman_class = null;
	    
	 // load an old ontology
	    try
	    {
	      System.out.println("Loading the old ontology...");
	      System.out.println("");
	      owlModel = ProtegeOWL.createJenaOWLModelFromURI(owlOLDFile.toURI().toString());
	    }
	    catch(OntologyLoadException e)
	    {
	      System.out.println("ERROR in loading old ontology!");
	      return;
	    }
	    
	    // get namespace of ontology
	    owlURI = owlModel.getNamespaceManager().getDefaultNamespace();
	    System.out.println("Ontology URI: " + owlURI);
	    System.out.println("");
	    
	    Man_class = owlModel.createOWLNamedClass(owlURI + Man_class_name);
	    System.out.println("Class URI: " + Man_class.getURI());
	    System.out.println("");
	    
	    Woman_class = owlModel.createOWLNamedClass(owlURI + Woman_class_name);
	    Woman_class.addSuperclass(Man_class);
	    System.out.println("Class URI: " + Woman_class.getURI());
	    System.out.println("");
	    
	    // save the new ontology
	    try
	    {
	      System.out.println("Saving the new ontology...");
	      System.out.println("");
	      owlModel.save(owlNEWFile.toURI());
	    }
	    catch(Exception e)
	    {
	      System.out.println("ERROR in saving new ontology!");
	      return;
	    }
	}
	
}
