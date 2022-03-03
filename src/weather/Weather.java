package weather;

public class Weather {
    private String megye;
    private Elorejelzes maiElorejelzes;
    private Elorejelzes holnapiElorejelzes;

    Weather(String sor) {
        String[] adatok = sor.split("\\t+", -1);
        this.megye = adatok[0].trim();
        String e = adatok[1].trim();
        String minMax = adatok[2].trim();
        this.maiElorejelzes = new Elorejelzes(e, minMax);
        e = adatok[3].trim();
        minMax = adatok[4].trim();
        this.holnapiElorejelzes = new Elorejelzes(e, minMax);
    }

    public String getMegye() {
        return megye;
    }

    public void setMegye(String megye) {
        this.megye = megye;
    }

    public Elorejelzes getMaiElorejelzes() {
        return maiElorejelzes;
    }

    public void setMaiElorejelzes(Elorejelzes maiElorejelzes) {
        this.maiElorejelzes = maiElorejelzes;
    }

    public Elorejelzes getHolnapiElorejelzes() {
        return holnapiElorejelzes;
    }

    public void setHolnapiElorejelzes(Elorejelzes holnapiElorejelzes) {
        this.holnapiElorejelzes = holnapiElorejelzes;
    }

    @Override
    public String toString() {
        return megye + ":\n\tmai: " + maiElorejelzes + ":\n\tholnapi: " + holnapiElorejelzes;
    }
}
