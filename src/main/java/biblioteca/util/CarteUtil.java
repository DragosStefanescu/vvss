package biblioteca.util;

import biblioteca.model.Carte;

import java.util.List;

public class CarteUtil {
    public static void deleteCuvantCheie(Carte carte, String cuvant) {
        for (int i = 0; i < carte.getCuvinteCheie().size(); i++) {
            if (carte.getCuvinteCheie().get(i).equals(cuvant)) {
                carte.getCuvinteCheie().remove(i);
                return;
            }
        }
    }

    public static void deleteReferent(Carte carte, String ref) {
        for (int i = 0; i < carte.getReferenti().size(); i++) {
            if (carte.getReferenti().get(i).equals(ref)) {
                carte.getReferenti().remove(i);
                return;
            }
        }
    }

    public static void deleteTotiReferentii(Carte carte) {
        carte.getReferenti().clear();
    }

    public static void adaugaCuvantCheie(Carte carte, String cuvant) {
        carte.getCuvinteCheie().add(cuvant);
    }

    public static void adaugaReferent(Carte carte, String ref) {
        carte.getReferenti().add(ref);
    }

    public static boolean cautaDupaCuvinteCheie(Carte carte, List<String> cuvinte) {
        for (String c : carte.getCuvinteCheie()) {
            for (String cuv : cuvinte) {
                if (c.equals(cuv))
                    return true;
            }
        }
        return false;
    }

    public static boolean cautaDupaAutor(Carte carte, String autor) {
        for (String author : carte.getReferenti()) {
            if (author.contains(autor))
                return true;
        }
        return false;
    }

    public static Carte getCarteFromString(String carteFisier) {
        Carte c = new Carte();
        String[] atr = carteFisier.split(";");
        String[] referenti = atr[1].split(",");
        String[] cuvCheie = atr[4].split(",");

        c.setTitlu(atr[0]);
        for (String s : referenti) {
            adaugaReferent(c, s);
        }

        c.setAnAparitie(atr[2]);
        for (String s : cuvCheie) {
            adaugaCuvantCheie(c, s);
        }

        return c;
    }
}
