package biblioteca;

import biblioteca.control.BibliotecaCtrl;
import biblioteca.model.Carte;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BibliotecaBigBangTests {
    Carte carte;
    Carte carte2;
    CartiRepoMock cr;
    BibliotecaCtrl bc = new BibliotecaCtrl(new CartiRepoMock());

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
    public void cautaCarteDupaAutor_noResults() {
        carte = new Carte();

        List<String> keyword = new ArrayList<>();
        keyword.add("keyword");
        List<String> referenti = new ArrayList<>(Arrays.asList("Ion Caragiale", "Ion Creanga", "Mihai Eminescu"));
        carte.setAnAparitie("1915");

        carte.setCuvinteCheie(keyword);
        carte.setReferenti(referenti);
        carte.setTitlu("Povesti");

        cr.adaugaCarte(carte);

        cr.cautaCarteDupaAutor("ajutor");
    }


    @Test
    public void cautaCarteDinAnul_success() {
        carte = new Carte();

        List<String> keyword = new ArrayList<>();
        keyword.add("keyword");
        List<String> referenti = new ArrayList<>(Arrays.asList("Ion Caragiale", "Ion Creanga", "Mihai Eminescu"));
        carte.setAnAparitie("1915");

        carte.setCuvinteCheie(keyword);
        carte.setReferenti(referenti);
        carte.setTitlu("Povesti");

        cr.adaugaCarte(carte);

        Assert.assertEquals(cr.getCartiOrdonateDinAnul("1915").size(), 1);
    }


    @Test
    public void integrationTest() {
        try {
            carte = new Carte();
            carte.setAnAparitie("1915");
            carte.setCuvinteCheie(new ArrayList<>(Collections.singletonList("keyword")));
            carte.setReferenti(new ArrayList<>(Arrays.asList("Ion Caragiale", "Ion Creanga", "Mihai Eminescu")));
            carte.setTitlu("Povesti");

            bc.adaugaCarte(carte);

            carte2 = new Carte();
            carte2.setAnAparitie("1914");
            carte.setCuvinteCheie(new ArrayList<>(Collections.singletonList("keyword")));
            carte2.setReferenti(new ArrayList<>(Collections.singletonList("Mihai Eminescu")));
            carte2.setTitlu("Povestiri");

            bc.adaugaCarte(carte2);

            Assert.assertEquals(bc.cautaCarteDupaAutor("Mihai Eminescu").size(), 2);
            Assert.assertEquals(bc.cautaCarteDupaAutor("Ion Caragiale").size(), 1);


            Assert.assertEquals(bc.getCartiOrdonateDinAnul("1915").size(), 1);
            Assert.assertEquals(bc.getCartiOrdonateDinAnul("1917").size(), 0);
        } finally {
            bc.getCarti().remove(carte);
            bc.getCarti().remove(carte2);
        }
    }
}
