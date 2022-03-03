package feladat03Indianok;

import java.util.ArrayList;

public class Indian {
    private String nev;
    private String torzs;
    private String nem;
    private int eletkor;
    private ArrayList<String> targyakTulajdonban;

    public Indian(String sor) {
        String[] actualSor = sor.split(";");
        this.nev = actualSor[0];
        this.torzs = actualSor[1];
        this.nem = actualSor[2].equals("n") ? "nő" : "férfi";
        this.eletkor = Integer.parseInt(actualSor[3]);
        this.targyakTulajdonban = new ArrayList<>();
        for (int i = 4; i < actualSor.length; i++) {
            this.targyakTulajdonban.add(actualSor[i]);
        }
    }

    public String getNev() {
        return nev;
    }

    public String getTorzs() {
        return torzs;
    }

    public String getNem() {
        return nem;
    }

    public int getEletkor() {
        return eletkor;
    }

    public ArrayList<String> getTargyakTulajdonban() {
        return targyakTulajdonban;
    }

    @Override
    public String toString() {
        String s = "";
        for (String item : this.targyakTulajdonban) {
            s += item + ", ";
        }
        s = s.trim();
        if (s.endsWith(",")) {
            s = s.substring(0, s.length() - 1) + ".";
        }
        return String.format("%s, %s törzshöz tartozik, neme %s, %d évet élt. Tárgyai: %s", nev, torzs, nem, eletkor, s);
    }
}
