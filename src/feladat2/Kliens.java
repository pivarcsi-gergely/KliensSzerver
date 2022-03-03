package feladat2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Kliens {
    public static void main(String[] args) {
        try {
            Socket kapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertolAdat = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szervernekAdat = new DataOutputStream(kapcsolat.getOutputStream());

            Scanner sc = new Scanner(System.in);
            System.out.println("Téglalap");
            while (true) {
                System.out.print("Kérem az 'a' oldalt: ");
                int a = sc.nextInt();
                szervernekAdat.writeInt(a);
                szervernekAdat.flush();

                System.out.print("Kérem a 'b' oldalt: ");
                int b = sc.nextInt();
                szervernekAdat.writeInt(b);
                szervernekAdat.flush();

                int menuPontKivalasztva;
                do {
                    System.out.println("Válasszon az alábbi lehetőségek közül!");
                    System.out.println("1.: Kerület\n2.: Terület\n3.: Négyzet-e\n4.: Átló mérete\n5.: Kilépés");
                    menuPontKivalasztva = sc.nextInt();
                    szervernekAdat.writeInt(menuPontKivalasztva);
                    szervernekAdat.flush();
                    System.out.println(szervertolAdat.readUTF());
                }
                while (menuPontKivalasztva != -1);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
