/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import muistipeli.pelikomponentit.Pelilauta;
import muistipeli.logiikka.Pelilogiikka;
import java.util.ArrayList;

/**
 *
 * testaa yleistä käyttölogiikkaa
 *
 * @author Camellion
 */
public class PelilogiikkaTest {

    Pelilogiikka peli;

    @Before
    public void setUp() {
        peli = new Pelilogiikka();
    }

    @Test
    public void kaksiKorttiaKaannettynaTotuusarvonVaihtaminen() {
        peli.asetaOnkoKaksiKaannetty(true);
        assertTrue(peli.getKaksiKorttiaKaannetty());
    }

    @Test
    public void yhdenKaannonJalkeenPalauttaaTotuusarvoFalse() {
        peli.getPelilauta().arvotListaan(16);
        peli.kaanto(1);
        assertFalse(peli.getKaksiKorttiaKaannetty());
    }

    @Test
    public void kahdenKaannonJalkeenTotuusarvoTrue() {
        peli.getPelilauta().arvotListaan(36);
        peli.kaanto(3);
        peli.kaanto(4);
        assertTrue(peli.getKaksiKorttiaKaannetty());
    }

    @Test
    public void kortitOvatPariKunOvatPari() {
        ArrayList<Integer> samatArvot = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            samatArvot.add(2);
        }
        peli.getPelilauta().asetaKorteilleArvot(samatArvot);
        assertTrue(peli.tarkistaOnkoNostetutKortitPari(0, 1));
    }

    @Test
    public void kortitEivatOlePariKunEivätOlePari() { //selkeää, eikö
        ArrayList<Integer> eriArvot = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            eriArvot.add(i);
        }
        peli.getPelilauta().asetaKorteilleArvot(eriArvot);
        assertFalse(peli.tarkistaOnkoNostetutKortitPari(0, 1));
    }

    @Test
    public void josPainetaanYhta() {
        assertEquals(peli.kaanto(4), "Ensimmäinen kääntö");
    }

    @Test
    public void josPainetaanSamaa() {
        peli.kaanto(4);
        assertEquals(peli.kaanto(4), "Sama kortti");
    }

    @Test
    public void ekaKorttiOnOikein() {
        peli.kaanto(6);
        assertEquals(6, peli.getEkaKortti());

    }

    @Test
    public void tokaKorttiOnOikein() {
        ArrayList<Integer> eriArvot = new ArrayList<>();
        for (int i = 1; i <= 36; i++) {
            eriArvot.add(i);
        }
        peli.getPelilauta().asetaKorteilleArvot(eriArvot);
        peli.kaanto(2);
        peli.kaanto(3);
        assertEquals(peli.getTokaKortti(), 3);

    }

    @Test
    public void kortitTunnistetaanSamoiksiKunKortitOvatSamoja() {
        ArrayList<Integer> samatArvot = new ArrayList<>();
        for (int i = 1; i <= 36; i++) {
            samatArvot.add(3);
        }
        peli.getPelilauta().asetaKorteilleArvot(samatArvot);
        peli.kaanto(2);
        assertEquals(peli.kaanto(19), "Löysit parin");
    }

    @Test
    public void kortitTunnistetaanEriKorteiksiKunKortitEivatOleSamoja() {
        ArrayList<Integer> eriArvot = new ArrayList<>();
        for (int i = 1; i <= 36; i++) {
            eriArvot.add(i);
        }
        peli.getPelilauta().asetaKorteilleArvot(eriArvot);
        peli.kaanto(2);
        assertEquals(peli.kaanto(5), "Ei ollut pari");
    }
}
