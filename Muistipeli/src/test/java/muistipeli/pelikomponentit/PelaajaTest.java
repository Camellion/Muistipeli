/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.pelikomponentit;

import muistipeli.pelikomponentit.Pelaaja;
import muistipeli.logiikka.Pelilogiikka;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testaa pelaajan nimeä sekä löydettyjä pareja ja yritysten määrää.
 *
 * @author Camellion
 */
public class PelaajaTest {

    Pelaaja pelaaja;
    Pelilogiikka peli;

    @Before
    public void setUp() {
        peli = new Pelilogiikka();
        pelaaja = peli.getPelaaja();
    }

    @Test
    public void pelaajallaTyhjäNimi() {
        assertEquals(pelaaja.getNimi(), "");
    }

    @Test
    public void nimenAsettaminenToimii() {
        pelaaja.asetaNimi("testinimi");
        assertEquals("testinimi", pelaaja.getNimi());
    }

    @Test
    public void alussaLoydetytNolla() {
        assertEquals(pelaaja.getloydetytParit(), 0);
    }

    @Test
    public void alussaYrityksetNolla() {
        assertEquals(pelaaja.getYritystenMaara(), 0);
    }

    @Test
    public void loydettyjenMaaraKasvaa() {
        pelaaja.loydettyjenParienMaaraKasvaa();
        assertEquals(pelaaja.getloydetytParit(), 1);
    }

    @Test
    public void yritystenMaaraKasvaa() {
        pelaaja.yritystenMaaraKasvaa();
        assertEquals(pelaaja.getYritystenMaara(), 1);
    }

    @Test
    public void yritystenNollausToimii() {
        pelaaja.yritystenMaaraKasvaa();
        pelaaja.nollaaYritysLaskuri();
        assertEquals(pelaaja.getYritystenMaara(), 0);
    }

    @Test
    public void loydettyjenNollausToimii() {
        pelaaja.loydettyjenParienMaaraKasvaa();
        pelaaja.nollaaPariLaskuri();
        assertEquals(pelaaja.getloydetytParit(), 0);
    }

}
