package ru.etu.mdp.family.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationErrors;
import ru.etu.mdp.family.exeption.ApplicationException;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;

@Service("individualService")
public class IndividualService {

    @Autowired
    private OntologyService ontologyService;

    /**
     * Получение экземпляра по имени
     *
     * @param changeForm
     * @return
     */
    public OWLIndividual getIndividual(ChangeForm changeForm) {

        getNeсessaryData(changeForm);
        return changeForm.getIndividual();

    }

    public void createIndividual(ChangeForm changeForm) throws ApplicationException {

        try {
            getNeсessaryData(changeForm);
            changeForm.getIndividualClass().createOWLIndividual(
                OntologyService.OWL_URI + changeForm.getNameIndividual());
        } catch (Exception ex) {
            throw new ApplicationException(ApplicationErrors.CREATING_INDIVIDUAL_ERROR);
        }

        ontologyService.saveOntology();
    }

    public void deleteIndividual(ChangeForm changeForm) throws ApplicationException {
        try {
            getNeсessaryData(changeForm);
            changeForm.getIndividual().delete();
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.REMOVING_INDIVIDUAL_ERROR);
        }

        ontologyService.saveOntology();
    }

    public void renameIndividual(ChangeForm changeForm) throws ApplicationException {
        try {
            getNeсessaryData(changeForm);
            changeForm.getIndividual().rename(changeForm.getNewValue());
        } catch (Exception e) {
            throw new ApplicationException(ApplicationErrors.RENAMING_INDIVIDUAL_ERROR);
        }

        ontologyService.saveOntology();
    }

    /**
     *
     * Подгрузка необходимых данных для выполнения операций с объектом
     *
     */
    private void getNeсessaryData(ChangeForm changeForm) {

        if (changeForm.getIndividualClassName() != null
            && !changeForm.getIndividualClassName().isEmpty()) {
            changeForm.setIndividualClass(OntologyService.owlModel.getOWLNamedClass(
                OntologyService.OWL_URI + changeForm.getIndividualClassName()));
        }

        if (changeForm.getNameIndividual() != null
            && !changeForm.getNameIndividual().isEmpty()) {
            changeForm.setIndividual(OntologyService.owlModel.getOWLIndividual(
                OntologyService.OWL_URI + changeForm.getNameIndividual()));
        }

    }

}
