package muistipeli.pelikomponentit;

import java.util.ArrayList;
import java.util.Collections;

public class Pelilauta {

    /**
     * Korttien arvot ArrayListissa.
     */
    private ArrayList<Integer> arvot;

    /**
     * Luo Pelilauta-olion, uuden ArrayListin ja laskee korttien määrän
     * pelilaudan koon mukaan.
     */
    public Pelilauta() {
        this.arvot = new ArrayList<>();
    }

    /**
     * Jakaa korttien määrän kahdella ja lisää jokaisen arvon kaksi kertaa
     * listaan. Lopuksi shufflella sekoittaa arvojen järjestyksen
     * sattumanvaraiseksi.
     */
    public void arvotListaan(int korttienmaara) {
        for (int i = 0; i < korttienmaara / 2; i++) {
            arvot.add(i);
            arvot.add(i);
        }
        Collections.shuffle(arvot);
    }

    /**
     * Seuraavat metodit ovat kaikki get-metodeita.
     */
    public int getArvojenMaara() {
        return arvot.size();
    }

    public ArrayList getListanArvot() {
        return arvot;
    }

    public int getKortinArvo(int monesko) {
        return arvot.get(monesko);
    }

    public String getKorttiMerkkijonona(int kortinJarjestysnumero) {
        return Integer.toString(arvot.get(kortinJarjestysnumero));
    }

    public void asetaKorteilleArvot(ArrayList<Integer> eriArvot) {
        this.arvot = eriArvot;
    }

}
