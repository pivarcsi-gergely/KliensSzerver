package weather;

import feladat03Indianok.Indian;

import java.io.*;
import java.net.Socket;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class UgyfelKiszolgalo implements Runnable {
    private Socket kapcsolat;
    private HashMap<String, Weather> elorejelzesek;

    public UgyfelKiszolgalo(Socket kapcsolat) {
        elorejelzesek = new HashMap<>();
        this.kapcsolat = kapcsolat;
        beolvas();
    }

    @Override
    public void run() {
        try {
            DataInputStream ugyfeltolAdat = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream ugyfelnekAdat = new DataOutputStream(kapcsolat.getOutputStream());

            int menu;
            do {
                menu = ugyfeltolAdat.readInt();
                Comparator<Integer> descOrder = ((o1, o2) -> o2 - o1);
                switch (menu) {
                    case 1:
                        String szoveg = "";
                        for (Map.Entry<String, Weather> entry: elorejelzesek.entrySet()) {
                            szoveg += entry.getValue() + "\n";
                        }
                        ugyfelnekAdat.writeUTF(szoveg);
                        ugyfelnekAdat.flush();
                        break;
                    case 2:
                        String ugyfeltolMegye = ugyfeltolAdat.readUTF();
                        ugyfelnekAdat.writeUTF(String.valueOf(elorejelzesek.entrySet().stream().sorted((o1, o2) -> descOrder
                                .compare(o1.getValue().getMaiElorejelzes().getMax(), o2.getValue().getMaiElorejelzes().getMax()))));
                        ugyfelnekAdat.flush();
                        break;
                    case 3:
                        Integer legnagyobb = elorejelzesek.entrySet().stream()
                                .map(stringWeatherEntry -> stringWeatherEntry.getValue().getMaiElorejelzes().getMax()).max(Comparator.naturalOrder()).get();
                        ugyfelnekAdat.writeUTF("Legmagasabb hőmérséklet: " + legnagyobb);
                        break;
                    case 4:
                        Integer legkisebb = elorejelzesek.entrySet().stream()
                                .map(stringWeatherEntry -> stringWeatherEntry.getValue().getMaiElorejelzes().getMax()).min(Comparator.naturalOrder()).get();
                        ugyfelnekAdat.writeUTF("Legkisebb hőmérséklet: " + legkisebb);
                        break;
                    case 5:
                        long havasCount = elorejelzesek.entrySet().stream()
                                .map(stringWeatherEntry -> stringWeatherEntry.getValue().getMaiElorejelzes().equals("Snow")).count();
                        ugyfelnekAdat.writeUTF(String.format("%d db helyen fog esni hó ma", havasCount));
                        break;
                    case 6:
                        elorejelzesek.entrySet().stream().sorted((o1, o2) -> descOrder
                                .compare(o1.getValue().getMaiElorejelzes().getSzovegesElorejelzes(), o2.getValue().getMaiElorejelzes().getMax()));
                }
            } while (menu != -1);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void beolvas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("weather.txt"));
            br.readLine();
            String sor = br.readLine();
            while (sor != null) {
                Weather weather = new Weather(sor);
                String megye = weather.getMegye();
                elorejelzesek.put(megye, weather);
                sor = br.readLine();
            }
            br.close();
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}
