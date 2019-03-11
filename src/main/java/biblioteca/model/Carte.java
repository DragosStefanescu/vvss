package biblioteca.model;


import java.util.ArrayList;
import java.util.List;

public class Carte {

    private String titlu;
    private List<String> referenti;
    private String anAparitie;
    private List<String> cuvinteCheie;

    public Carte() {
        titlu = "";
        referenti = new ArrayList<String>();
        anAparitie = "";
        cuvinteCheie = new ArrayList<String>();
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public List<String> getReferenti() {
        return referenti;
    }

    public void setReferenti(List<String> ref) {
        this.referenti = ref;
    }

    public String getAnAparitie() {
        return anAparitie;
    }

    public void setAnAparitie(String anAparitie) {
        this.anAparitie = anAparitie;
    }

    public List<String> getCuvinteCheie() {
        return cuvinteCheie;
    }

    public void setCuvinteCheie(List<String> cuvinteCheie) {
        this.cuvinteCheie = cuvinteCheie;
    }

    @Override
    public String toString() {
        String ref = "";
        String cuvCheie = "";

        for (int i = 0; i < referenti.size(); i++) {
            if (i == referenti.size() - 1)
                ref += referenti.get(i);
            else
                ref += referenti.get(i) + ",";
        }

        for (int i = 0; i < cuvinteCheie.size(); i++) {
            if (i == cuvinteCheie.size() - 1)
                cuvCheie += cuvinteCheie.get(i);
            else
                cuvCheie += cuvinteCheie.get(i) + ",";
        }

        return titlu + ";" + ref + ";" + anAparitie + ";" + cuvCheie;
    }

}
