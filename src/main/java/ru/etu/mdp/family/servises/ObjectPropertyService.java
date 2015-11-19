package ru.etu.mdp.family.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.etu.mdp.family.exeption.ApplicationException;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;

@Service("objectPropertyService")
public class ObjectPropertyService {

    @Autowired
    private OntologyService ontologyService;

    public void setProperty(String nameIndividual, String nameProperty, String value)
        throws ApplicationException {

        OWLObjectProperty property = OntologyService.owlModel
            .getOWLObjectProperty(OntologyService.OWL_URI + nameProperty);

        OWLIndividual individual = OntologyService.owlModel
            .getOWLIndividual(OntologyService.OWL_URI + nameIndividual);

        OWLIndividual objectProperty = OntologyService.owlModel
            .getOWLIndividual(OntologyService.OWL_URI + value);

        individual.setPropertyValue(property, objectProperty);

        ontologyService.saveOntology();
    }

}
