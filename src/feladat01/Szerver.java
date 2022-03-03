package feladat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Szerver {
    public static void main(String[] args) {
        System.out.println("Szerver indul...");
        try {
            ServerSocket Ssocket = new ServerSocket(8080);
            Socket kapcsolat = Ssocket.accept();
            DataInputStream ugyfeltolJovoAdat = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnekMenoAdat = new DataOutputStream(kapcsolat.getOutputStream());
            InetAddress ugyfel = kapcsolat.getInetAddress();
            System.out.println("Az ügyfél neve: " + ugyfel.getHostName());
            System.out.println("Az ügyfél neve: " + ugyfel.getHostAddress());

            while(true) {
                int sugar = ugyfeltolJovoAdat.readInt();
                double kerulet = 2*sugar * Math.PI;
                ugyfelnekMenoAdat.writeDouble(kerulet);
                System.out.println("Kerület kiküldve");
                double terulet = Math.pow(sugar, 2) * Math.PI;
                ugyfelnekMenoAdat.writeDouble(terulet);
                System.out.println("Terület kiküldve");
            }
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
