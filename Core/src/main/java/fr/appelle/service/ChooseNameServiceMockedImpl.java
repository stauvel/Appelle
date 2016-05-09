package fr.appelle.service;

import fr.appelle.entity.Evaluation;
import fr.appelle.entity.Name;
import fr.appelle.entity.Sexe;
import fr.appelle.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Mono-thread mocked imlementation
 * Created by Seb on 08/05/2016.
 */
@Component
public class ChooseNameServiceMockedImpl implements ChooseNameService {

    private static Set<Name> namesRepository;
    {
        namesRepository = new LinkedHashSet<>();
        namesRepository.add(new Name("Henri", Sexe.MAN));
        namesRepository.add(new Name("Pierre", Sexe.MAN));
        namesRepository.add(new Name("Jean", Sexe.MAN));
        namesRepository.add(new Name("Marc", Sexe.MAN));
        namesRepository.add(new Name("Hélène", Sexe.WOMAN));
        namesRepository.add(new Name("Bérénice", Sexe.WOMAN));
        namesRepository.add(new Name("Monique", Sexe.WOMAN));
        namesRepository.add(new Name("Suzette", Sexe.WOMAN));
    }

    private static Set<User> usersRepository;
    {
        usersRepository = new LinkedHashSet<>();
    }

    @Override
    public Optional<Name> randomizeName(User user, Sexe sexe) {
        return namesRepository.stream()
                .filter(n -> n.getSexe() == sexe)
                .filter(n -> user.getEvaluations().values().stream().flatMap(Collection::stream).noneMatch(m -> m.equals(n)))
                .findAny();
    }

    @Override
    public void evaluateName(User user, Name name, Evaluation evaluation) {
        user.addEvaluation(evaluation, name);
        usersRepository.add(user);
    }

}
