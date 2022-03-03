package feladat03Indianok;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class KliensIndian {
    public static void main(String[] args) {
        try {
            Socket kapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertolAdat = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szervernekAdat = new DataOutputStream(kapcsolat.getOutputStream());

            System.out.println("Indiánok\n");
            Scanner sc = new Scanner(System.in);
            while (true) {
                int menu;
                do {
                    System.out.println("Válasszon a menüpontok közül!");
                    System.out.println("1.: Listázás\n2.: Hányan vannak");
                    menu = sc.nextInt();
                    szervernekAdat.writeInt(menu);
                    szervernekAdat.flush();
                    System.out.println(szervertolAdat.readUTF());
                }while (menu != -1);
            }
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
