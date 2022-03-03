package feladat03Indianok;

import feladat2.UgyfelKiszolgalo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SzerverIndian {
    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();
        System.out.println("A szerver indul");
        try {
            ServerSocket Ssocket = new ServerSocket(8080);

            while (true) {
                Socket kapcsolat = Ssocket.accept();
                InetAddress ugyfelInet = kapcsolat.getInetAddress();
                System.out.println("Ügyfél címe: " + ugyfelInet.getHostAddress());
                UgyfelKiszolgaloIndian ugyfelKiszolgaloIndian = new UgyfelKiszolgaloIndian(kapcsolat);
                exe.submit(ugyfelKiszolgaloIndian);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
