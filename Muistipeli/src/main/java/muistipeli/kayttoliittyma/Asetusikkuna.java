package muistipeli.kayttoliittyma;

import javax.swing.*;

/**
 * Luokka tekee ikkunoita, joissa kysytään käyttäjältä kysymyksiä.
 */
public class Asetusikkuna {

    /**
     * Kysyy käyttäjältä nimen.
     *
     * @param   kysymys näkyvissä oleva kysymys.
     * @return  JOptionPane.showInputDialog(kysymys)    merkkijono, joka on käyttäjän antama vastaus kysymykseen
     */
    public static String kysyNimi(String kysymys) {
        return JOptionPane.showInputDialog(kysymys);
    }

    /**
     * Kysyy käyttäjältä korttiparien määrän. Ainoastaan 2, 8, ja 18 kelpaavat.
     *
     * @param   kysymys näkyvissä oleva kysymys.
     *
     * @return  luku kokonaisluku, joka on käyttäjän antama vastaus kysymykseen
     */
    public static int kysyTaso(String kysymys) {
        String tarjottuLuku;
        int luku = 0;
        boolean onOikeaTaso = false;
        while (!onOikeaTaso) {
            try {
                tarjottuLuku = JOptionPane.showInputDialog(kysymys);
                luku = Integer.parseInt(tarjottuLuku);
                onOikeaTaso = true;
            } catch (Exception e) {
            }
        }
        return luku;
    }
}
