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
public class OntologyServiceImpl implements OntologyServis{

	@Override
	public void createEmptyOntology(OntologyForm ontologyForm) {
		// a variable of ontology
	    JenaOWLModel owlModel = null;
	    // a namespace of ontology "http://www.owl-ontologies.com/FamilySpring.owl#"
	    String owlURI = ontologyForm.getUri();
	    // a name of new file of ontology
	    String owlNEWFileName = "FamilySpring.owl";
	    // a variable of new file of ontology
	    File owlNEWFile = new File(owlNEWFileName);
	    
	    // create an empty ontology
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
	    
	        
	    // save the new ontology
	    try
	    {
	      owlModel.save(owlNEWFile.toURI());
	    }
	    catch(Exception e)
	    {
	      System.out.println("ERROR in saving new ontology!");
	      return;
	    }
	}
	
}
