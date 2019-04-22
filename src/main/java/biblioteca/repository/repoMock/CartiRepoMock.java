package biblioteca.repository.repoMock;


import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.util.CarteUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CartiRepoMock implements CartiRepoInterface {

    private List<Carte> carti;

    public CartiRepoMock() {
        carti = new ArrayList<Carte>();

    }

    @Override
    public void adaugaCarte(Carte c) {
        carti.add(c);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<Carte> cautaCarteDupaAutor(String ref) {
        List<Carte> carti = getCarti();
        List<Carte> cartiGasite = new ArrayList<>();

        for (Carte c : carti) {
            for (String autor : c.getReferenti()) {
                if (autor.contains(ref)) {
                    cartiGasite.add(c);
                    break;
                }
            }
        }

        if(cartiGasite.size() == 0)
            throw new RuntimeException("No books found");

        return cartiGasite;

        //   return carti.stream().filter(x-> x.getReferenti().stream().anyMatch(refs -> refs.equals(ref))).collect(Collectors.toList());
    }


    @Override
    public List<Carte> getCarti() {
        return carti;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public List<Carte> getCartiOrdonateDinAnul(String an) {
        return getCarti().stream().filter(book -> book.getAnAparitie().equals(an)).sorted(Comparator.comparing(Carte::getTitlu).thenComparing(carte->carte.getReferenti().get(0))).collect(Collectors.toList());
    }

}
