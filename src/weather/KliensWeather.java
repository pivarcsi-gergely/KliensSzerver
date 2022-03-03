package weather;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class KliensWeather {
    public static void main(String[] args) {
        try {
            Socket kapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertolAdat = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szervernekAdat = new DataOutputStream(kapcsolat.getOutputStream());

            System.out.println("Időjárás előrejelzések. To be as politically correct as possible.\n");
            Scanner sc = new Scanner(System.in);
            while (true) {
                int menu;
                do {
                    System.out.println("Válasszon a menüpontok közül!");
                    System.out.println("1.: Listázás\n" +
                                        "2.: Listázás, a mai előrejelzések csökkenő sorrendje alapján. (Nem működik for some reason)\n" +
                                        "3.: Legmagasabb hőmérsékletű előrejelzés adatai (mai)?\n" +
                                        "4.: Legkisebb hőmérsékletű előrejelzés adatai (holnapi)?\n" +
                                        "5.: Hol eshet hó ma?\n" +
                                        "6.: Hány helyen változott meg a napi előrejelzés?\n" +
                                        "7.: Kilépés.");
                    menu = sc.nextInt();
                    szervernekAdat.writeInt(menu);
                    szervernekAdat.flush();
                    System.out.println(szervertolAdat.readUTF());
                } while (menu != -1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
