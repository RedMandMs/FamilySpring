package ru.etu.mdp.family.servises;

import ru.etu.mdp.family.domain.OntologyForm;

public interface OntologyServis {

	public static final String FILE_NAME = "myOntology.owl";
	
	public static final String OWL_URI = "http://www.owl-ontologies.com/family.owl#";
	
	void createEmptyOntology(OntologyForm ontologyForm);
}
