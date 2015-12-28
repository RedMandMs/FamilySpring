package ru.etu.mdp.family.servises;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.etu.mdp.family.domain.ChangeForm;
import ru.etu.mdp.family.exeption.ApplicationErrors;
import ru.etu.mdp.family.exeption.ApplicationException;

import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.RDFObject;
import edu.stanford.smi.protegex.owl.model.query.QueryResults;

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

    /**
     * Получить все экземпляры по названию класса
     */
    public List<OWLIndividual> getAllIndividualByClassName(String className)
        throws Exception {
        List<OWLIndividual> result = new ArrayList<>();
        String sparql_text = "PREFIX f: <http://www.owl-ontologies.com/family.owl#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?individual WHERE { ?individual rdf:type f:"
            + className + ". }";
        QueryResults results = OntologyService.owlModel.executeSPARQLQuery(sparql_text);
        while (results.hasNext()) {
            Map map = results.next();
            RDFObject value = (RDFObject) map.get("individual");
            result.add(OntologyService.owlModel.getOWLIndividual(value.getBrowserText()));
        }
        return result;
    }

    /**
     * Получить всех людей системе
     */
    public List<OWLIndividual> getAllHumans() throws Exception {
        List<OWLIndividual> result = new ArrayList<>();
        result.addAll(getAllWomen());
        result.addAll(getAllMen());
        return result;
    }

    /**
     * Получить всех людей женщин в системе
     */
    public List<OWLIndividual> getAllWomen() throws Exception {
        return getAllIndividualByClassName("Woman");
    }

    /**
     * Получить всех людей мужчин в системе
     */
    public List<OWLIndividual> getAllMen() throws Exception {
        return getAllIndividualByClassName("Man");
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
