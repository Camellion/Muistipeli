/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli.pelikomponentit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import muistipeli.pelikomponentit.Pelilauta;
import java.util.ArrayList;

/**
 * testaa pelilaudan toimintoja
 * 
 * @author Camellion
 */
public class PelilautaTest {

    Pelilauta lauta;

    @Before
    public void setUp() {
        lauta = new Pelilauta();

    }

    @Test
    public void palauttaaArvojenMaaranOikein() {
        lauta.arvotListaan(16);
        assertEquals(lauta.getArvojenMaara(), 16);
    }

    @Test
    public void listaSekoittaaItsensa() {
        lauta.arvotListaan(36);
    }

    @Test
    public void JokaistaKorttiaVainYksi() {
        lauta.arvotListaan(16);
        assertEquals(16, lauta.getArvojenMaara());
    }

    @Test
    public void kortinArvoPalautetaanOikeanaLukuna() {
        ArrayList<Integer> eriArvot = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            eriArvot.add(i);
        }
        lauta.asetaKorteilleArvot(eriArvot);
        assertEquals(lauta.getKortinArvo(5), 6);

    }

    @Test
    public void kortinArvoPalautetaanOikeanaArvoMerkkijonona() {
        ArrayList<Integer> eriArvot = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            eriArvot.add(i);
        }
        lauta.asetaKorteilleArvot(eriArvot);
        assertEquals(lauta.getKorttiMerkkijonona(1), "2");
    }
    
    
}