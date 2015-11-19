package ru.etu.mdp.family.exeption;

public enum ApplicationErrors {

    CREATING_ONTOLOGY_ERROR("Ошибка создания онтологии"),
    READING_ONTOLOGY_ERROR("Ошибка считывания онтологии"),
    SAVING_ONTOLOGY_ERROR("Ошибка сохранения онтологии"),
    INCORRECT_CONVERT("Ошибка приведения типов!");

    ApplicationErrors(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

}
