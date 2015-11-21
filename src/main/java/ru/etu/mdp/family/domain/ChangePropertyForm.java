package ru.etu.mdp.family.domain;

import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;

public class ChangePropertyForm {

    private String nameIndividual;

    private String nameProperty;

    private String newValue;

    private OWLIndividual individual;

    private OWLObjectProperty objectProperty;

    private OWLIndividual objectPropertyValue;

    private OWLDatatypeProperty dataTypeProperty;

    private Object dataTypePropertyValue;

    public String getNameIndividual() {
        return nameIndividual;
    }

    public void setNameIndividual(String nameIndividual) {
        this.nameIndividual = nameIndividual;
    }

    public String getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public OWLIndividual getIndividual() {
        return individual;
    }

    public void setIndividual(OWLIndividual individual) {
        this.individual = individual;
    }

    public OWLObjectProperty getObjectProperty() {
        return objectProperty;
    }

    public void setObjectProperty(OWLObjectProperty objectProperty) {
        this.objectProperty = objectProperty;
    }

    public OWLIndividual getObjectPropertyValue() {
        return objectPropertyValue;
    }

    public void setObjectPropertyValue(OWLIndividual objectPropertyValue) {
        this.objectPropertyValue = objectPropertyValue;
    }

    public OWLDatatypeProperty getDataTypeProperty() {
        return dataTypeProperty;
    }

    public void setDataTypeProperty(OWLDatatypeProperty dataTypeProperty) {
        this.dataTypeProperty = dataTypeProperty;
    }

    public Object getDataTypePropertyValue() {
        return dataTypePropertyValue;
    }

    public void setDataTypePropertyValue(Object dataTypePropertyValue) {
        this.dataTypePropertyValue = dataTypePropertyValue;
    }

}
