package ru.etu.mdp.family.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.etu.mdp.family.exeption.ApplicationErrors;
import ru.etu.mdp.family.exeption.ApplicationException;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;

/**
 *
 * @author Vilgodskiy Sergey
 *
 */
@Service("dataTypePropertyService")
public class DataTypePropertyService {

    @Autowired
    private OntologyService ontologyService;

    public void setProperty(String nameIndividual, String nameProperty, String value)
        throws ApplicationException {

        OWLDatatypeProperty property = OntologyService.owlModel
            .getOWLDatatypeProperty(OntologyService.OWL_URI + nameProperty);

        OWLIndividual individual = OntologyService.owlModel
            .getOWLIndividual(OntologyService.OWL_URI + nameIndividual);

        TypeProperty typeProperty = TypeProperty
            .findByLocalName(property.getRange().getLocalName());

        try {
            switch (typeProperty) {
                case BOOLEAN:
                    individual.setPropertyValue(property, Boolean.valueOf(value));
                    break;
                case INTEGER:
                    individual.setPropertyValue(property, Integer.valueOf(value));
                    break;
                case STRING:
                    individual.setPropertyValue(property, value);
                    break;
                case FLOAT:
                    individual.setPropertyValue(property, Float.valueOf(value));
                    break;
                default:
                    individual.setPropertyValue(property, value);
                    break;
            }
        } catch (NumberFormatException ex) {
            throw new ApplicationException(ApplicationErrors.INCORRECT_CONVERT);
        }

        ontologyService.saveOntology();
    }

    public String getTypeProperty(String nameProperty) {

        OWLDatatypeProperty property = OntologyService.owlModel
            .getOWLDatatypeProperty(OntologyService.OWL_URI + nameProperty);

        return property.getRange().getLocalName();

    }

    public enum TypeProperty {
        BOOLEAN("boolean"), INTEGER("int"), STRING("string"), FLOAT("float");

        private final String localName;

        TypeProperty(String localName) {
            this.localName = localName;
        }

        public static TypeProperty findByLocalName(String localName) {
            for (TypeProperty typeProperty : values()) {
                if (typeProperty.localName.equals(localName)) {
                    return typeProperty;
                }
            }
            return null;
        }
    }
}
