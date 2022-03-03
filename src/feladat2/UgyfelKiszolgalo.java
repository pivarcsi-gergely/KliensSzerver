package feladat2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UgyfelKiszolgalo implements Runnable {
    private Socket kapcsolat;

    public UgyfelKiszolgalo(Socket kapcsolat) {
        this.kapcsolat = kapcsolat;
    }

    @Override
    public void run() {
        try {
            DataInputStream ugyfeltolAdat = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnekAdat = new DataOutputStream(kapcsolat.getOutputStream());

            while (true) {
                int a = ugyfeltolAdat.readInt();
                int b = ugyfeltolAdat.readInt();

                int menuPontKivalasztva;
                do {
                    menuPontKivalasztva = ugyfeltolAdat.readInt();

                    switch (menuPontKivalasztva) {
                        case 1:
                            ugyfelnekAdat.writeUTF(kerulet(a, b));
                            break;
                        case 2:
                            ugyfelnekAdat.writeUTF(terulet(a, b));
                            break;
                        case 3:
                            ugyfelnekAdat.writeUTF(negyzetE(a, b));
                            break;
                        case 4:
                            ugyfelnekAdat.writeUTF(atlo(a, b));
                            break;
                        case 5: ugyfelnekAdat.writeUTF("Ön a kilépést választotta");
                    }
                }
                while (menuPontKivalasztva != 5);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private String kerulet(int a, int b) {
        return String.format("A téglalap kerülete: %d", 2 * (a + b));
    }

    private String terulet(int a, int b) {
        return String.format("A téglalap területe: %d", a * b);
    }

    private String negyzetE(int a, int b) {
        return a == b ? "This téglalap is made out of négyzet." : "This téglalap is made out of téglalap.";
    }

    private String atlo(int a, int b) {
        return String.format("A téglalap átlójának mérete: %.2f", Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)));
    }
}
