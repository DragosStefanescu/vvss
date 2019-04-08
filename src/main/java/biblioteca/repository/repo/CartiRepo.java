package biblioteca.repository.repo;


import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.util.CarteUtil;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CartiRepo implements CartiRepoInterface {

    private String file = "out/cartiBD.txt";

    public CartiRepo() {
        URL location = CartiRepo.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
    }

    @Override
    public void adaugaCarte(Carte c) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(c.toString());
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Carte> getCarti() {
        List<Carte> lc = new ArrayList<Carte>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                lc.add(CarteUtil.getCarteFromString(line));
            }

            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lc;
    }
/*
    @Override
    public List<Carte> cautaCarteDupaAutor2(String ref) {
        List<Carte> carti = getCarti();
        List<Carte> cartiGasite = new ArrayList<Carte>();
        int i = 0;
        while (i <= carti.size()) {
            boolean flag = false;
            List<String> lref = carti.get(i).getReferenti();
            int j = 0;
            while (j < lref.size()) {
                if (lref.get(j).toLowerCase().contains(ref.toLowerCase())) {
                    flag = true;
                    break;
                }
                j++;
            }
            if (flag == true) {
                cartiGasite.add(carti.get(i));
            }
            i++;
        }
        return cartiGasite;
    }
*/
    @Override
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
    public List<Carte> getCartiOrdonateDinAnul(String an) {
        List<Carte> lc = getCarti();
        List<Carte> lca = new ArrayList<Carte>();
        for (Carte c : lc) {
            if (c.getAnAparitie().equals(an)) {
                lca.add(c);
            }
        }

        lca.sort((a, b) -> {
            if (a.getTitlu().compareTo(b.getTitlu()) == 0) {
                return a.getReferenti().get(0).compareTo(b.getReferenti().get(0));
            }

            return a.getTitlu().compareTo(b.getTitlu());
        });

        return lca;
    }

}
