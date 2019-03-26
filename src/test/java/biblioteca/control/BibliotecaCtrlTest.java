package biblioteca.control;

import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class BibliotecaCtrlTest {
    private Carte carte;
    private BibliotecaCtrl bibliotecaCtrl;
    CartiRepoInterface cr;

    @Before
    public void setUp() {
        cr = new CartiRepoMock();
        bibliotecaCtrl = new BibliotecaCtrl(cr);
        carte = new Carte();

        List<String> keyword = new ArrayList<String>();
        keyword.add("keyword");

        List<String> referenti = new ArrayList<String>();
        referenti.add("Franz Kafka");

        carte.setAnAparitie("1915");
        carte.setCuvinteCheie(keyword);
        carte.setReferenti(referenti);
        carte.setTitlu("Metamorphosis");

    }

    @Test
    public void adaugaCarte_withSucces() throws Exception {
        bibliotecaCtrl.adaugaCarte(carte);
    }

    @Test
    public void adaugaCarte_blankTitle() {
        carte.setTitlu("");
        try {
            bibliotecaCtrl.adaugaCarte(carte);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void adaugaCarte_validYear1() throws Exception {
        carte.setAnAparitie("1900");
        bibliotecaCtrl.adaugaCarte(carte);
    }

    @Test
    public void adaugaCarte_validYear2() throws Exception {
        carte.setAnAparitie("2020");
        bibliotecaCtrl.adaugaCarte(carte);
    }

    @Test
    public void adaugaCarte_invalidYear1() {
        carte.setAnAparitie("1899");
        try {
            bibliotecaCtrl.adaugaCarte(carte);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void adaugaCarte_invalidYear2() {
        carte.setAnAparitie("2021");
        try {
            bibliotecaCtrl.adaugaCarte(carte);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void adaugaCarte_invalidYear3() {
        carte.setAnAparitie("an aparitie");
        try {
            bibliotecaCtrl.adaugaCarte(carte);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void adaugaCarte_invalidYear4() {
        carte.setAnAparitie("-1");
        try {
            bibliotecaCtrl.adaugaCarte(carte);
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}