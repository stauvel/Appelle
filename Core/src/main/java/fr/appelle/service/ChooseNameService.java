package fr.appelle.service;

import fr.appelle.entity.Evaluation;
import fr.appelle.entity.Name;
import fr.appelle.entity.Sexe;
import fr.appelle.entity.User;

import java.util.Optional;

/**
 * Created by Seb on 08/05/2016.
 */
public interface ChooseNameService {

    /**
     * Provides a new name that the user did not evaluate yet
     * @param user
     * @return
     */
    Optional<Name> randomizeName(User user, Sexe sexe);

    /**
     * Remember a name evaluation by the given user
     * @param user
     * @param name
     * @param evaluation
     */
    void evaluateName(User user, Name name, Evaluation evaluation);

}
