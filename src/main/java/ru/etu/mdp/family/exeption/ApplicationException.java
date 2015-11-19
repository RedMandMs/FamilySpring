package ru.etu.mdp.family.exeption;

public class ApplicationException extends Exception {

    public ApplicationException(ApplicationErrors applicationError) {
        super(applicationError.getMessage());
    }

}
