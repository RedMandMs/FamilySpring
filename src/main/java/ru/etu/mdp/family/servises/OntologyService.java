package ru.etu.mdp.family.servises;

import java.io.File;

import org.springframework.stereotype.Service;

import ru.etu.mdp.family.domain.OntologyForm;
import ru.etu.mdp.family.exeption.ApplicationErrors;
import ru.etu.mdp.family.exeption.ApplicationException;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;

/**
 *
 * @author Vilgodskiy Sergey
 *
 */
@Service("ontologyService")
public class OntologyService {

    public static final String OWL_FILE_NAME = "family.owl";

    public static final String OWL_URI = "http://www.owl-ontologies.com/family.owl#";

    public static JenaOWLModel owlModel;

    File owlFile = new File(OWL_FILE_NAME);

    String testOwlFileName = "newOntology.owl";

    File testOwlFile = new File(testOwlFileName);

    public void createEmptyOntology(OntologyForm ontologyForm)
        throws ApplicationException {

        String owlURI = ontologyForm.getUri();

        try {
            owlModel = ProtegeOWL.createJenaOWLModel();
        } catch (OntologyLoadException e) {
            throw new ApplicationException(ApplicationErrors.CREATING_ONTOLOGY_ERROR);
        }

        owlModel.getNamespaceManager().setDefaultNamespace(owlURI);

        saveOntology();
    }

    public void readOntology(OntologyForm ontologyForm) throws ApplicationException {
        try {
            owlModel = ProtegeOWL
                .createJenaOWLModelFromURI(ontologyForm.getUri().toString());
        } catch (OntologyLoadException e) {
            throw new ApplicationException(ApplicationErrors.READING_ONTOLOGY_ERROR);
        }
    }

    public void readDefoultOntology() throws ApplicationException {
        try {
            owlModel = ProtegeOWL.createJenaOWLModelFromURI(owlFile.toURI().toString());
        } catch (OntologyLoadException e) {
            throw new ApplicationException(ApplicationErrors.READING_ONTOLOGY_ERROR);
        }
    }

    public void saveOntology() throws ApplicationException {
        try {
            owlModel.save(owlFile.toURI());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.SAVING_ONTOLOGY_ERROR);
        }
    }

    public void saveInNewFile() throws ApplicationException {
        try {
            owlModel.save(testOwlFile.toURI());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.SAVING_ONTOLOGY_ERROR);
        }
    }

}
