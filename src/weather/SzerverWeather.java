package weather;

import feladat03Indianok.UgyfelKiszolgaloIndian;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SzerverWeather {
    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();
        System.out.println("A szerver indul");
        try {
            ServerSocket Ssocket = new ServerSocket(8080);

            while (true) {
                Socket kapcsolat = Ssocket.accept();
                InetAddress ugyfelInet = kapcsolat.getInetAddress();
                System.out.println("Ügyfél címe: " + ugyfelInet.getHostAddress());
                UgyfelKiszolgalo ugyfelKiszolgalo = new UgyfelKiszolgalo(kapcsolat);
                exe.submit(ugyfelKiszolgalo);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
