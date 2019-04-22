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
import java.util.stream.Collectors;

public class CartiRepo implements CartiRepoInterface {

    private String filename = "cartiBD.dat";
    private File file;

    public CartiRepo() {
        URL location = CartiRepo.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
        file = new File(
                getClass().getClassLoader().getResource(filename).getFile()
        );
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
        List<Carte> lc = new ArrayList<>();
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

        if (cartiGasite.size() == 0)
            throw new RuntimeException("No books found");

        return cartiGasite;

        //   return carti.stream().filter(x-> x.getReferenti().stream().anyMatch(refs -> refs.equals(ref))).collect(Collectors.toList());
    }


    @Override
    @SuppressWarnings("Duplicates")
    public List<Carte> getCartiOrdonateDinAnul(String an) {
       return getCarti().stream().filter(book -> book.getAnAparitie().equals(an)).sorted(Comparator.comparing(Carte::getTitlu).thenComparing(carte->carte.getReferenti().get(0))).collect(Collectors.toList());
    }


}
