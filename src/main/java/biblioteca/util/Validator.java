package biblioteca.util;

import biblioteca.model.Carte;

public class Validator {

    public static boolean isStringOK(String s) {
        boolean flag = s.matches("[a-zA-Z ]+");
        if (!flag)
            throw new RuntimeException("String invalid");
        return flag;
    }

    public static void validateCarte(Carte c) {
        if (c.getCuvinteCheie() == null) {
            throw new RuntimeException("Lista cuvinte cheie vida!");
        }
        if (c.getReferenti() == null) {
            throw new RuntimeException("Lista autori vida!");
        }
        if (!isOKString(c.getTitlu()))
            throw new RuntimeException("Titlu invalid!");
        for (String s : c.getReferenti()) {
            if (!isOKString(s))
                throw new RuntimeException("Autor invalid!");
        }
        for (String s : c.getCuvinteCheie()) {
            if (!isOKString(s))
                throw new RuntimeException("Cuvant cheie invalid!");
        }
        if (!Validator.isNumber(c.getAnAparitie()))
            throw new RuntimeException("An aparitie invalid!");
    }

    public static boolean isNumber(String s) {
       boolean result = s.matches("[0-9]+");

       if(!result)
           return false;

       if(Integer.valueOf(s) > 2020 || Integer.valueOf(s) < 1900)
           return false;

        return s.matches("[0-9]+");
    }

    public static boolean isOKString(String s) {
        if (s.length() > 255 || s.length() == 0)
            return false;

        String[] t = s.split(" ");
        if (t.length == 2) {
            boolean ok1 = t[0].matches("[a-zA-Z]+");
            boolean ok2 = t[1].matches("[a-zA-Z]+");
            if (ok1 == ok2 && ok1) {
                return true;
            }
            return false;
        }
        return s.matches("[a-zA-Z]+");
    }

}
