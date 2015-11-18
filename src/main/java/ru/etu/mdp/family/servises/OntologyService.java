package ru.etu.mdp.family.servises;

import java.io.File;

import org.springframework.stereotype.Service;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import ru.etu.mdp.family.domain.OntologyForm;

/**
 * 
 * @author Vilgodskiy Sergey
 *
 */
@Service("ontologyService")
public class OntologyService{
	
	public static final String FILE_NAME = "myOntology.owl";
	
	public static final String OWL_URI = "http://www.owl-ontologies.com/family.owl#";

	public static JenaOWLModel owlModel;
	
	String owlFileName = "family.owl";
    // a variable of new file of ontology
    File owlFile = new File(owlFileName);

    String testOwlFileName = "test.owl";
    // a variable of new file of ontology
    File testOwlFile = new File(testOwlFileName);
	
	public void createEmptyOntology(OntologyForm ontologyForm) {
		// a variable of ontology
	    owlModel = null;
	    // a namespace of ontology "http://www.owl-ontologies.com/FamilySpring.owl#"
	    String owlURI = ontologyForm.getUri();
	    try
	    {
	      System.out.println("Creating an empty ontology...");
	      System.out.println("");
	      owlModel = ProtegeOWL.createJenaOWLModel();
	    }
	    catch(OntologyLoadException e)
	    {
	      System.out.println("ERROR in creating ontology!");
	      return;
	    }
	    
	    owlModel.getNamespaceManager().setDefaultNamespace(owlURI);
	    System.out.println("Ontology URI: " + owlURI);
	    
	        
	    saveOntology();
	}
	
	public void readOntology(){
		try {
			owlModel = ProtegeOWL.createJenaOWLModelFromURI(owlFile.toURI().toString());
		} catch (OntologyLoadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveOntology(){
		// save the new ontology
	    try
	    {
	      owlModel.save(owlFile.toURI());
	    }
	    catch(Exception e)
	    {
	    	System.out.println("ERROR in saving new ontology!");
	    }
	}

	public void saveInNewOntology() {
		// save the new ontology
	    try
	    {
	      owlModel.save(testOwlFile.toURI());
	    }
	    catch(Exception e)
	    {
	    	System.out.println("ERROR in saving new ontology!");
	    }
	}
	
}
