package ru.etu.mdp.family.servises;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationErrors;
import ru.etu.mdp.family.exeption.ApplicationException;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;

@Service("objectPropertyService")
public class ObjectPropertyService {

    @Autowired
    private OntologyService ontologyService;

    /**
     * Получить значение поля
     *
     * @param changePropertyForm
     * @throws ApplicationException
     */
    public OWLIndividual getPropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);
            return (OWLIndividual) changePropertyForm.getIndividual()
                .getPropertyValue(changePropertyForm.getObjectProperty());
        } catch (Exception ex) {
            throw new ApplicationException(ApplicationErrors.READING_PROPERTY_ERROR);
        }

    }

    /**
     * Получить все значения поля
     *
     * @param changePropertyForm
     * @throws ApplicationException
     */
    @SuppressWarnings("unchecked")
    public Collection<OWLIndividual> getAllPropertyValues(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);
            return changePropertyForm.getIndividual()
                .getPropertyValues(changePropertyForm.getObjectProperty());
        } catch (Exception ex) {
            throw new ApplicationException(ApplicationErrors.READING_PROPERTY_ERROR);
        }

    }

    /**
     * Установить значение поля
     *
     * @param changePropertyForm
     * @throws ApplicationException
     */
    public void setPropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            changePropertyForm.getIndividual().setPropertyValue(
                changePropertyForm.getObjectProperty(),
                changePropertyForm.getObjectPropertyValue());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.RECORDING_PROPERTY_ERROR);
        }

        ontologyService.saveOntology();
    }

    /**
     * Добавить одно значение - При мульти-значении
     *
     * @param changePropertyForm
     * @throws ApplicationException
     */
    public void addPropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            changePropertyForm.getIndividual().addPropertyValue(
                changePropertyForm.getObjectProperty(),
                changePropertyForm.getObjectPropertyValue());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.ADDING_PROPERTY_ERROR);
        }

        ontologyService.saveOntology();
    }

    @SuppressWarnings("unchecked")
    public Collection<OWLObjectProperty> getAllObjectProperties(
        ChangeForm changePropertyForm) throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            Collection<OWLObjectProperty> result = changePropertyForm.getIndividual()
                .getRDFType().getUnionDomainProperties();

            //OntologyService.owlModel.getRDFResourceByNameOrBrowserText();

            /*
             * for (OWLNamedClass clazz : OntologyService.owlModel .getOWLNamedClass(
             * OntologyService.OWL_URI + changePropertyForm.getIndividualClassName())
             * .getInferredSuperclasses()) {
             *
             * }
             */

            return result;
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.READING_PROPERTY_ERROR);
        }

    }

    /**
     * Удалить одно значение - При мульти-значении
     *
     * @param changePropertyForm
     * @throws ApplicationException
     */
    public void removeOnePropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            changePropertyForm.getIndividual().removePropertyValue(
                changePropertyForm.getObjectProperty(),
                changePropertyForm.getObjectPropertyValue());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.REMOVING_PROPERTY_ERROR);
        }

        ontologyService.saveOntology();
    }

    /**
     * Удалить все значения - При мульти-значении
     *
     * @param changePropertyForm
     * @throws ApplicationException
     */
    public void clearPropertyValue(ChangeForm changePropertyForm)
        throws ApplicationException {

        try {
            getNeсessaryData(changePropertyForm);

            changePropertyForm.getIndividual()
                .setPropertyValue(changePropertyForm.getObjectProperty(), null);
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.REMOVING_PROPERTY_ERROR);
        }

        ontologyService.saveOntology();
    }

    /**
     * Подгрузка необходимых данных для выполнения операции изменением полей
     *
     * @param changePropertyForm
     */
    private void getNeсessaryData(ChangeForm changePropertyForm) {

        if (changePropertyForm.getNameProperty() != null
            && !changePropertyForm.getNameProperty().isEmpty()) {
            changePropertyForm
                .setObjectProperty(OntologyService.owlModel.getOWLObjectProperty(
                    OntologyService.OWL_URI + changePropertyForm.getNameProperty()));
        }

        if (changePropertyForm.getNameIndividual() != null
            && !changePropertyForm.getNameIndividual().isEmpty()) {
            changePropertyForm.setIndividual(OntologyService.owlModel.getOWLIndividual(
                OntologyService.OWL_URI + changePropertyForm.getNameIndividual()));
        }

        if (changePropertyForm.getNewValue() != null
            && !changePropertyForm.getNewValue().isEmpty()) {
            changePropertyForm
                .setObjectPropertyValue(OntologyService.owlModel.getOWLIndividual(
                    OntologyService.OWL_URI + changePropertyForm.getNewValue()));
        }

    }

}
