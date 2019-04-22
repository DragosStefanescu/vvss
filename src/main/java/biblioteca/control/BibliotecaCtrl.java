package biblioteca.control;


import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.util.Validator;

import java.util.List;

public class BibliotecaCtrl {

    private CartiRepoInterface cr;

    public BibliotecaCtrl(CartiRepoInterface cr) {
        this.cr = cr;
    }

    public void adaugaCarte(Carte c) {
        Validator.validateCarte(c);
        cr.adaugaCarte(c);
    }

    public List<Carte> cautaCarteDupaAutor(String autor) {
        Validator.isStringOK(autor);
        return cr.cautaCarteDupaAutor(autor);
    }

    public List<Carte> getCarti() {
        return cr.getCarti();
    }

    public List<Carte> getCartiOrdonateDinAnul(String an) {
        if (!Validator.isNumber(an))
            throw new RuntimeException("Nu e numar!");
        return cr.getCartiOrdonateDinAnul(an);
    }


}
