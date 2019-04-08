package biblioteca.repository.repoMock;


import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.util.CarteUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    public List<Carte> getCartiOrdonateDinAnul(String an) {
        List<Carte> lc = getCarti();
        List<Carte> lca = new ArrayList<Carte>();
        for (Carte c : lc) {
            if (c.getAnAparitie().equals(an)) {
                lca.add(c);
            }
        }

        Collections.sort(lca, new Comparator<Carte>() {

            @Override
            public int compare(Carte a, Carte b) {
                if (a.getTitlu().compareTo(b.getTitlu()) == 0) {
                    return a.getReferenti().get(0).compareTo(b.getReferenti().get(0));
                }

                return a.getTitlu().compareTo(b.getTitlu());
            }

        });

        return lca;
    }

}
