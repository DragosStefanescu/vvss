package biblioteca.repository;


import biblioteca.model.Carte;
import biblioteca.repository.repo.CartiRepo;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartiRepoTest {
    Carte carte;
    Carte carte2;
    CartiRepoMock cr;

    @Before
    public void setUp() {
        cr = new CartiRepoMock();


    }

    @Test(expected = RuntimeException.class)
    public void adaugaCarte_noResults() {
        carte = new Carte();

        List<String> keyword = new ArrayList<String>();
        keyword.add("keyword");
        List<String> referenti = new ArrayList<>(Arrays.asList("Ion Caragiale", "Ion Creanga", "Mihai Eminescu"));
        carte.setAnAparitie("1915");

        carte.setCuvinteCheie(keyword);
        carte.setReferenti(referenti);
        carte.setTitlu("Povesti");

        cr.adaugaCarte(carte);

        cr.cautaCarteDupaAutor("ajutor");

    }


    @Test(expected = RuntimeException.class)
    public void adaugaCarte_noBooks() {
        cr.cautaCarteDupaAutor("Iom");
    }

    @Test(expected = RuntimeException.class)
    public void adaugaCarte_noMatching() {
        carte = new Carte();

        List<String> keyword = new ArrayList<String>();
        keyword.add("keyword");
        List<String> referenti = new ArrayList<>(Arrays.asList("Caragiale", "Creanga", "Mihai Eminescu"));
        carte.setAnAparitie("1915");

        carte.setCuvinteCheie(keyword);
        carte.setReferenti(referenti);
        carte.setTitlu("Povesti");

        cr.adaugaCarte(carte);

        cr.cautaCarteDupaAutor("Ion");

    }

    @Test
    public void adaugaCarte_matching() {
        carte = new Carte();
        carte2 = new Carte();

        List<String> keyword = new ArrayList<String>();
        keyword.add("keyword");
        List<String> referenti = new ArrayList<>(Arrays.asList("Ion Caragiale", "Ion Creanga", "Mihai Eminescu"));
        carte.setAnAparitie("1915");

        carte.setCuvinteCheie(keyword);
        carte.setReferenti(referenti);
        carte.setTitlu("Povesti");

        carte2.setCuvinteCheie(keyword);
        carte2.setReferenti(referenti);
        carte2.setTitlu("Poezii");

        cr.adaugaCarte(carte);
        cr.adaugaCarte(carte2);

        Assert.assertEquals(cr.cautaCarteDupaAutor("Ion").size(),2);

    }
}
