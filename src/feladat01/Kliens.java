package feladat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Kliens {
    public static void main(String[] args) {
        try {
            Socket kliensKapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertolKapottAdat = new DataInputStream(kliensKapcsolat.getInputStream());
            DataOutputStream szerverreKuldendoAdat = new DataOutputStream(kliensKapcsolat.getOutputStream());

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Kérem a kör sugarát:");
                int sugar = sc.nextInt();
                szerverreKuldendoAdat.writeInt(sugar);
                szerverreKuldendoAdat.flush();
                System.out.printf("Szervertől a kerület: %.2f\n", szervertolKapottAdat.readDouble());
                System.out.printf("Szervertől a terület: %.2f\n", szervertolKapottAdat.readDouble());
            }
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
