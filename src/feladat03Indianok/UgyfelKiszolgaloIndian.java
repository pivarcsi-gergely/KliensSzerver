package feladat03Indianok;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class UgyfelKiszolgaloIndian implements Runnable {
    private Socket kapcsolat;
    private ArrayList<Indian> indianLista;

    public UgyfelKiszolgaloIndian(Socket kapcsolat) {
        this.kapcsolat = kapcsolat;
    }

    @Override
    public void run() {
        try {
            DataInputStream ugyfeltolAdat = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnekAdat = new DataOutputStream(kapcsolat.getOutputStream());

            int menu;
            beolvasas();
            do {
                menu = ugyfeltolAdat.readInt();
                switch (menu) {
                    case 1:
                        String szoveg = "";
                        for (Indian indian : indianLista) {
                            szoveg += indian + "\n";
                        }
                        ugyfelnekAdat.writeUTF(szoveg);
                        ugyfelnekAdat.flush();
                        break;
                    case 2:
                        ugyfelnekAdat.writeUTF(String.format("%d db indián van a listában.", indianLista.size()));
                }

            } while (menu != -1);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void beolvasas() {
        indianLista = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("indianok.txt"));
            String sor = br.readLine();
            while (sor != null) {
                indianLista.add(new Indian(sor));
                sor = br.readLine();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
