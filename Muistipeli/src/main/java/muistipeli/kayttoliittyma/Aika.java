package muistipeli.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Luokka hoitaa ajan kulkua pelissä.
 */
public class Aika extends Timer implements ActionListener {

    /**
     * Käyttöliittymä-luokka
     */
    private GUI kayttis;

    /**
     * Luo timerin
     *
     * @param kayttis = käyttöliittymä
     * @param aika on määritelty aika jonka timer pyörii ennen kuin käyttää
     * metodiaan
     */
    public Aika(GUI kayttis, int aika) {
        super(aika, null);
        this.kayttis = kayttis;
        this.addActionListener(this);
        this.setRepeats(false);

    }

    /**
     * ajan loputtua kutsuu metodia, joka suorittaa kääntämistoiminnot
     *
     * @param e , kun aika loppuu
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        kayttis.timerinAjanPaatteeksiSuoritettavatKaannot();

    }

}
