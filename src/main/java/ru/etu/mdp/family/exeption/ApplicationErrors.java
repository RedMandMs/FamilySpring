package ru.etu.mdp.family.exeption;

public enum ApplicationErrors {

    CREATING_ONTOLOGY_ERROR("Ошибка создания онтологии"),
    READING_ONTOLOGY_ERROR("Ошибка считывания онтологии"),
    SAVING_ONTOLOGY_ERROR("Ошибка сохранения онтологии"),
    READING_PROPERTY_ERROR("Ошибка считывания поля объекта"),
    RECORDING_PROPERTY_ERROR("Ошибка записи поля объекта"),
    REMOVING_PROPERTY_ERROR("Ошибка удаления поля объекта"),
    ADDING_PROPERTY_ERROR("Ошибка добавления поля объекта"),
    CREATING_INDIVIDUAL_ERROR("Ошибка создания экземпляра класса"),
    REMOVING_INDIVIDUAL_ERROR("Ошибка удаления экземпляра класса"),
    RENAMING_INDIVIDUAL_ERROR("Ошибка переименования экземпляра класса"),
    INCORRECT_CONVERT("Ошибка приведения типов!");

    ApplicationErrors(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

}
