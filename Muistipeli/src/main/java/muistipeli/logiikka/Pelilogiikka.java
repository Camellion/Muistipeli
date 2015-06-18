package muistipeli.logiikka;

import muistipeli.pelikomponentit.Pelaaja;
import muistipeli.pelikomponentit.Pelilauta;
import java.util.ArrayList;

/**
 * Luokka, jossa on kortin kääntämisen toiminnot
 */
public class Pelilogiikka {

    /**
     * Vuoron ensimmäinen nostettu kortti.
     */
    private int ekaKortti;

    /**
     * Vuoron toinen nostettu kortti.
     */
    private int tokaKortti;

    /**
     * Kertoo onko kortti ensimmäinen vai toinen nostovuorossa oleva kortti.
     */
    private boolean ekaNostovuorossa;

    /**
     * Pelaajaa ilmentävä pelaaja-olio, joka sisältää pelaajan tunnuksen sekä
     * yritysten ja löydettyjen parien määrän.
     */
    private Pelaaja pelaaja;

    /**
     * Pelilauta, sisältää kortti-olioita, joita pelaaja kääntää.
     */
    private Pelilauta lauta;

    /**
     * kertoo, että on yksi vai kaksi korttia käännettynä.
     */
    private boolean kaksiKorttiaKaannetty;

    /**
     * Konstruktori luo uuden pelaajan ja käyttää alustus-metodia
     */
    public Pelilogiikka() {
        pelaaja = new Pelaaja();
        alustus();

    }

    /**
     * Metodi luo uuden pelilaudan ja alustaa tarvittavat alkuarvot uuden pelin
     * aloittamista varten
     */
    public void alustus() {
        lauta = new Pelilauta();
        ekaNostovuorossa = true;
        ekaKortti = -1;
        kaksiKorttiaKaannetty = false;
    }

    /**
     * Kääntämisen suorittava metodi. Tarkistaa onko kortti ensimmäinen vai
     * toinen kääntövuorossa oleva kortti. Tarkistaa myös, että onko kortti jo
     * käännettynä. Jos kortti on ensimmäinen käännettävä, sen järjestys
     * taulussa talletetaan.
     *
     * @param   moneskoKortti   kertoo monesko käännetty kortti on.
     * @return  kommentti kertoo mitä on tapahtuu missäkin vaiheessa.
     */
    public String kaanto(int moneskoKortti) {
        if (ekaNostovuorossa) {
            asetaEkaKortti(moneskoKortti);
            asetaEnsimmainenNostovuorossa(false);
            return "Ensimmäinen kääntö";
        } else {
            if (moneskoKortti == ekaKortti) {
                return "Sama kortti";
            } else {
                kaksiKorttiaKaannetty = true;
                asetaEnsimmainenNostovuorossa(true);
                asetaTokaKortti(moneskoKortti);
                return toinenKaanto(moneskoKortti, pelaaja);
            }
        }
    }

    /**
     * Lisää pelaajan yritysten ja parien määrää(jos löytyy kaksi samaa).
     * Palauttaa käyttöliittymälle merkkijonon, joka kertoo onko kaksi
     * käännettyä korttia pari vai ei.
     *
     * @param   pelaaja Pelaamassa oleva pelaaja
     * @param   moneskoKortti   kertoo monesko kortti on taulussa.
     * @return  kommentti kertoo mitä tapahtuu missäkin vaiheessa.
     *
     */
    private String toinenKaanto(int moneskoKortti, Pelaaja pelaaja) {
        pelaaja.yritystenMaaraKasvaa();
        if (tarkistaOnkoNostetutKortitPari(ekaKortti, moneskoKortti)) {
            pelaaja.loydettyjenParienMaaraKasvaa();
            return "Löysit parin";
        } else {
            return "Ei ollut pari";
        }
    }

    /**
     * Tarkistaa ovatko nostetut kaksi korttia pari.
     *
     * @param   ensimmainen ensimmäinen nostettu
     * @param   toinen  toinen nostettu
     * @return  tosi tai epätosi
     */
    public boolean tarkistaOnkoNostetutKortitPari(int ensimmainen, int toinen) {
        if (lauta.getListanArvot().get(ensimmainen).equals(lauta.getListanArvot().get(toinen))) {
            return true;
        }
        return false;
    }

    /**
     * Seuraavat metodit ovat kaikki set- tai get-metodeita.
     */

    public void asetaOnkoKaksiKaannetty(boolean onkoKaksiKaannetty) {
        kaksiKorttiaKaannetty = onkoKaksiKaannetty;
    }

    public void asetaEnsimmainenNostovuorossa(boolean a) {
        ekaNostovuorossa = a;
    }

    public void asetaEkaKortti(int i) {
        ekaKortti = i;
    }

    public void asetaTokaKortti(int i) {
        tokaKortti = i;
    }

    public int getEkaKortti() {
        return ekaKortti;
    }

    public int getTokaKortti() {
        return tokaKortti;
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public Pelilauta getPelilauta() {
        return lauta;
    }

    public boolean getKaksiKorttiaKaannetty() {
        return kaksiKorttiaKaannetty;
    }

    public boolean getEnsimmainenNostovuorossa() {
        return ekaNostovuorossa;
    }

    //public void asetaEnsimmainenNostovuorossaUudenPelinAlkuun(boolean x) {
    //    ekaNostovuorossa = x;
    //}

}
