package fr.appelle.entity;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Seb on 08/05/2016.
 */
public class User {

    /*For tests*/
    public User(String login) {
        this.login = login;
        this.evaluations = new HashMap<>();
    }

    public User() {
    }

    private String login;

    private Map<Evaluation, List<Name>> evaluations;

    public Map<Evaluation, List<Name>> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(Map<Evaluation, List<Name>> evaluations) {
        this.evaluations = evaluations;
    }

    /**
     * Add a name evaluation into evaluations map
     * @param evaluation
     * @param name
     */
    public void addEvaluation(Evaluation evaluation, Name name) {
        evaluations.computeIfAbsent(evaluation, k ->  new LinkedList<>()).add(name);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
