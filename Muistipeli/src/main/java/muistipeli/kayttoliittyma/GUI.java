package muistipeli.kayttoliittyma;

import muistipeli.logiikka.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Luokka muodostaa graafisen käyttöliittymän. Luokka hoitaa pelilaudan
 * kokoamisen ja reagoi pelaajan tekemiin valintoihin pelilaudalla.
 */
public final class GUI extends JPanel implements ActionListener {

    /**
     * Pelilauta
     */
    private JFrame lauta;

    /**
     * Yritysten määrä.
     */
    private JLabel yritykset;

    /**
     * Löydettyjen parien määrä.
     */
    private JLabel loydetyt;

    /**
     * Yläpalkkiin tuleva pelaajan nimi.
     */
    private JLabel pelaajanNimi;

    /**
     * Korttien painikkeet
     */
    private JButton[] kortit;

    /**
     * Laudassa oleva lopetuspainike.
     */
    private JButton lopetusPainike;

    /**
     * Laudassa oleva uuden pelin painike.
     */
    private JButton uusipeliPainike;

    /**
     * Paneeli säilyttää kortit, jotka tulevat laudalle.
     */
    private Panel paneeli;

    /**
     * Pelaajan syöttämä parien määrä.
     */
    private int korttiParienMaara;

    /**
     * Pelilogiikka-luokka
     */
    private Pelilogiikka peli;

    /**
     * Pelilogiikka-luokan kaanto-metodista tuleva ilmoitus, että mitä
     * havaittiin kahta korttia painettaessa.
     */
    private String kaannetytKortit;

    /**
     * Ajan kulkua operoiva Aika-luokka.
     */
    private Aika timer;

    public GUI() {
        peli = new Pelilogiikka();
        aloitaPeli();
        timer = new Aika(this, 1000);

    }

    /**
     * Tekee pelilaudan kysymällä pelaajalta nimeä ja parien määrää.
     */
    public void aloitaPeli() {
        kysyPelaajanNimi();
        kysyParienMaara();
        lauta = new JFrame();
        lauta.setSize(360, 420);
        lauta.setTitle("Muistipeli");
        lauta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        teeKortit();
        teeNimiJaLaskurit();
        teePainikkeet();
        laitaKortitLaudalle();
        laitaPainikkeetLaudalle();
        laitaNimiJaLaskuritLaudalle();
        lauta.setVisible(true);
        pelinPaatteeksiKerroKuinkaMonellaYrityksellaPeliLoppui();

    }

    /**
     * Kysyy pelaajalta nimen asetusikkunan avulla.
     */
    private void kysyPelaajanNimi() {
        peli.getPelaaja().asetaNimi(Asetusikkuna.kysyNimi("Anna nimesi: "));

    }

    /**
     * Kysyy pelattavien parien määrän.
     */
    private void kysyParienMaara() {
        int parienMaara = Asetusikkuna.kysyTaso("Anna pelattavien korttiparien määrä: (2, 8 tai 18)");
        while (!(parienMaara == 2
                || parienMaara == 8
                || parienMaara == 18)) {
            parienMaara = Asetusikkuna.kysyTaso("Vain 2, 8 tai 18 ovat sallittuja! Anna korttiparien määrä:");
        }
        asetaKorttienMaara(parienMaara);
        korttiParienMaara = parienMaara;
    }

    /**
     * Tekee oikean määrän kortteja.
     *
     * @param   parienMaara
     */
    public void asetaKorttienMaara(int parienMaara) {
        kortit = new JButton[2 * parienMaara];

    }

    /**
     * Tekee kortit.
     */
    private void teeKortit() {
        for (int i = 0; i < kortit.length; i++) {
            kortit[i] = new JButton("");
            kortit[i].addActionListener(this);
        }
        peli.getPelilauta().arvotListaan(kortit.length);
    }

    /**
     * Tekee lopetus- ja uusipeli-painikkeet.
     */
    private void teePainikkeet() {
        lopetusPainike = new JButton("Lopeta");
        lopetusPainike.addActionListener(this);
        uusipeliPainike = new JButton("Uusi peli");
        uusipeliPainike.addActionListener(this);

    }

    /**
     * Tekee laskuri -ja nimiotsikot.
     */
    private void teeNimiJaLaskurit() {
        loydetyt = new JLabel("Löydetyt parit: ");
        yritykset = new JLabel("Yritykset: ");
        pelaajanNimi = new JLabel("Nimi: " + peli.getPelaaja().getNimi());
    }

    /**
     * Laittaa kortit laudalle.
     */
    private void laitaKortitLaudalle() {
        paneeli = new Panel();
        if (kortit.length == 4) {
            paneeli.setLayout(new GridLayout(2, (kortit.length) / 2));
        } else if (kortit.length == 16) {
            paneeli.setLayout(new GridLayout(4, (kortit.length) / 4));
        } else {
            paneeli.setLayout(new GridLayout(6, (kortit.length) / 6));
        }
        for (int i = 0; i < kortit.length; i++) {
            paneeli.add(kortit[i]);
        }
        lauta.add(paneeli);
    }

    /**
     * Laittaa tehdyt painikkeet laudalle.
     *
     */
    private void laitaPainikkeetLaudalle() {
        Panel painikePaneeli = new Panel();
        painikePaneeli.setLayout(new GridLayout(1, 2));
        painikePaneeli.add(uusipeliPainike);
        painikePaneeli.add(lopetusPainike);
        lauta.add(painikePaneeli, BorderLayout.SOUTH);
    }

    /**
     * Laittaa tehdyt laskurit sekä nimen laudan yläosaan.
     */
    private void laitaNimiJaLaskuritLaudalle() {
        Panel nimiJalaskuriPaneeli = new Panel();
        nimiJalaskuriPaneeli.setLayout(new GridLayout(1, 3));
        nimiJalaskuriPaneeli.add(loydetyt);
        nimiJalaskuriPaneeli.add(yritykset);
        nimiJalaskuriPaneeli.add(pelaajanNimi);
        lauta.add(nimiJalaskuriPaneeli, BorderLayout.NORTH);
    }

    /**
     * Metodi ohjaa eteenpäin käskyt käännöistä sen perusteella, että onko kaksi
     * korttia pari vai ei. Jos on pari, kortit poistetaan. Jos ei ole pari,
     * kortit käännetään takaisin väärin päin.
     */
    public void timerinAjanPaatteeksiSuoritettavatKaannot() {
        if (kaannetytKortit.equals("Ei ollut pari")) {
            kaannaKaannetytTakaisinJotkaEivatOlleetPari(peli.getEkaKortti(), peli.getTokaKortti());

        } else if (kaannetytKortit.equals("Löysit parin")) {
            poistaKortitPelilautaltaNiidenOllessaSamat(peli.getEkaKortti(), peli.getTokaKortti());
        }
        peli.asetaOnkoKaksiKaannetty(false);
        tarkastaTuloksetNostovuoronPaatteeksi();
        pelinPaatteeksiKerroKuinkaMonellaYrityksellaPeliLoppui();
    }

    /**
     * Poistaa pelilaudalta löydetyn parin kokonaan.
     *
     * @param   ensimmainen
     * @param   toinen
     */
    private void poistaKortitPelilautaltaNiidenOllessaSamat(int ensimmainen, int toinen) {
        kortit[ensimmainen].setVisible(false);
        kortit[toinen].setVisible(false);
    }

    /**
     * Kääntää kortit takaisin väärin päin.
     *
     * @param   ensimmainen käännetty kortti
     * @param   toinen  käännetty kortti
     */
    public void kaannaKaannetytTakaisinJotkaEivatOlleetPari(int ensimmainen, int toinen) {
        kortit[ensimmainen].setText("");
        kortit[toinen].setText("");

    }

    /**
     * Tarkastaa nostojen ja yritysten määrän.
     */
    public void tarkastaTuloksetNostovuoronPaatteeksi() {
        loydetyt.setText("Löydetyt: " + peli.getPelaaja().getloydetytParit());
        yritykset.setText("Yritykset: " + peli.getPelaaja().getYritystenMaara());

    }

    /**
     * Nollaa pelaajalla olevat laskurit yrityksistä ja nostetuista.
     */
    public void nollaaNostettujenJaYritystenLaskurit() {
        peli.getPelaaja().nollaaPariLaskuri();
        peli.getPelaaja().nollaaYritysLaskuri();
        tarkastaTuloksetNostovuoronPaatteeksi();
    }

    /**
     * Tarkistaa onko pelaaja kääntänyt jo kaikki kortit. Jos on, metodi
     * ilmoittaa yläpalkissa, kuinka monella siirrolla peli päättyi.
     *
     */
    public void pelinPaatteeksiKerroKuinkaMonellaYrityksellaPeliLoppui() {
        if (peli.getPelaaja().getloydetytParit() == korttiParienMaara) {
            lauta.setTitle("Läpäisit pelin " + peli.getPelaaja().getYritystenMaara()
                    + " yrityksellä.");
        }
    }

    /**
     * Aloittaa uuden pelin. Kun pelaaja haluaa aloittaa tason alusta tai
     * vaihtaa tasoa niin metodi "jakaa" uudet kortit samalle pelialustalle
     * ilman että pelaajan tarvitsee vaihtaa enää nimeään.
     */
    private void aloitaUusiPeliPainikkeenKautta() {
        peli.alustus();
        kysyParienMaara();
        teeKortit();
        nollaaNostettujenJaYritystenLaskurit();
        pelinPaatteeksiKerroKuinkaMonellaYrityksellaPeliLoppui();
        lauta.remove(paneeli);
        laitaKortitLaudalle();
        lauta.setVisible(true);
    }

    /**
     * Operoi hiiren klikkausten mukaisesti. Lopetuspainike lopettaa pelin.
     * UusiPeliPainike aloittaa uuden peli, pelaajan haluaman tason mukaisesti.
     *
     * Kun kortteja on käännetty kaksi kappaletta, metodi käynnistää timerin,
     * joka laittaa eteenpäin joko kutsun poistaa kortit laudalta tai kääntää ne
     * takaisin väärin päin.
     *
     * @param   e   mistä painetaan
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (peli.getKaksiKorttiaKaannetty() == false) {
            for (int i = 0; i < kortit.length; i++) {
                if (kortit[i] == e.getSource()) {
                    kortit[i].setText(peli.getPelilauta().getKorttiMerkkijonona(i));
                    kaannetytKortit = peli.kaanto(i);
                    if (kaannetytKortit.equals("Löysit parin") || kaannetytKortit.equals("Ei ollut pari")) {
                        timer.start();
                    }
                }
            }
        }

        if (e.getSource() == lopetusPainike) {
            System.exit(0);
        }

        if (e.getSource() == uusipeliPainike) {
            aloitaUusiPeliPainikkeenKautta();
        }
    }
}
