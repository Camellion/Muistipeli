Aihe: Muistipeli. Totetutetaan perinteinen jo lapsena pelattu muistipeli, jossa etsitään pareja kääntelemällä kortteja kaksi kerrallaan. Pyrkimyksenä on kääntää parit esiin samaan aikaan.

Käyttäjä: Pelaaja

Toiminnot:

Alustus
1. Pelaaja syöttää nimensä.
2. Pelaaja valitsee muistipelin korttiparien määrän.

Peli:
Pelaaja etsii pareja pelilaudalta klikkaamalla kahta valitsemaansa korttia kerrallaan. Jos pari löytyy, poistaa peli parin laudalta.
Muuten peli käntää parin takaisin väärinpäin.

Muuta:
"Lopetus" -painike päättää pelin ja "Uusi peli" -painike aloittaa uuden pelin.


Rakennekuvaus:

Muistipelissä käyttöliittymä luokka GUI käyttää luokkaa Pelilogiikka, joka sisältää pelin käyttölogiikan. Pelilogiikka puolestaan tuntee luokan Pelilauta, joka sisältää pelikortit ja luokan Pelaaja. Luokka Pelaaja puolestaan pitää kirjaa pelaajan tiedoista, kuten nimestä, yrityksistä ja löydetyistä pareista.
