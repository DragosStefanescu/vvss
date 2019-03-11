package biblioteca.repository.repoInterfaces;


import biblioteca.model.Carte;

import java.util.List;

public interface CartiRepoInterface {
	void adaugaCarte(Carte c);
	List<Carte> cautaCarteDupaAutor(String ref);
	List<Carte> getCarti();
	List<Carte> getCartiOrdonateDinAnul(String an);
}
