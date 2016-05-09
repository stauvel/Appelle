package fr.appelle.service;

import fr.appelle.entity.Evaluation;
import fr.appelle.entity.Name;
import fr.appelle.entity.Sexe;
import fr.appelle.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ChooseNameServiceTest {

    @Autowired
    ChooseNameService chooseNameService;

    @Test
    public void testRandomName() throws Exception {
        Optional<Name> nameM = chooseNameService.randomizeName(new User(""), Sexe.MAN);
        assertTrue(nameM.isPresent());
        assertTrue(nameM.get().getSexe() == Sexe.MAN);
        Optional<Name> nameF = chooseNameService.randomizeName(new User(""), Sexe.WOMAN);
        assertTrue(nameF.isPresent());
        assertTrue(nameF.get().getSexe() == Sexe.WOMAN);
    }

    @Test
    public void testEvaluateName() throws Exception {
        User user1 = new User("User1");
        Optional<Name> nameM;
        while ((nameM = chooseNameService.randomizeName(user1, Sexe.MAN)).isPresent()) {
            Name name = nameM.get();
            if(name.getLabel().matches("\\w+[aeiou]$")) {
                chooseNameService.evaluateName(user1, name, Evaluation.LIKE);
            } else {
                chooseNameService.evaluateName(user1, name,Evaluation.UNLIKE);
            }
        }
        List<Name> likedNamesByUser1 = user1.getEvaluations().get(Evaluation.LIKE);
        assertTrue(likedNamesByUser1.size() == 2);
        assertTrue(likedNamesByUser1.stream().anyMatch(n -> "Henri".equals(n.getLabel())));
        assertTrue(likedNamesByUser1.stream().anyMatch(n -> "Pierre".equals(n.getLabel())));

        User user2 = new User("User2");
        Optional<Name> nameF;
        while((nameF = chooseNameService.randomizeName(user2, Sexe.WOMAN)).isPresent()) {
            Name name = nameF.get();
            if(name.getLabel().contains("i")) {
                chooseNameService.evaluateName(user2, name, Evaluation.LIKE);
            } else {
                chooseNameService.evaluateName(user2, name,Evaluation.UNLIKE);
            }
        }
        List<Name> likedNamesByUser2 = user2.getEvaluations().get(Evaluation.LIKE);
        assertTrue(likedNamesByUser2.size() == 2);
        assertTrue(likedNamesByUser2.stream().anyMatch(n -> "Bérénice".equals(n.getLabel())));
        assertTrue(likedNamesByUser2.stream().anyMatch(n -> "Monique".equals(n.getLabel())));

    }
}