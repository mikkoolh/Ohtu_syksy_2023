# Ohjelmistotuotanto syksy 2023
## Matleena Kankaanpää, Elmo Erla, Mikko Hänninen, Nikita Nossenko
Kodin automaatiojärjestelmä koneoppimista hyödyntäen.

Visio
Visiomme on tehdä älykodista helppokäyttöisempi ja saavutettavampi jokaiselle käyttäjälle. Haluamme tarjota keskitetyn ratkaisun, joka yksinkertaistaa ja automatisoi kodin laitteiden hallintaa.
Tavoitteemme on luoda yhtenäinen sovellus, joka yhdistää useiden älylaitteiden ohjauksen ja seurannan, tehostaen niiden käyttöä koneoppimisen avulla automatisoitujen rutiinien kautta.

Kehitysympäristö ja riippuvuudet
Automaatio sovellus on kehitetty Java-kielellä ja se käyttää seuraavia kirjastoja:
● Jackson:JSON-käsittelyyn.
● Hibernate:ORM-työkalutietokannanjaJava-objektienvälille.
● MySQLConnector:Tietokantayhteyksienmuodostamiseen.
● JavaFX:Graafisenkäyttöliittymänluomiseen.
● JakartaPersistence:JPAstandardiin.
● JUnit:Yksikkötestaukseen.
● JBcrypt:Salasanojensalaamiseen.
● ControlsFXjaGemsFX:Graafiseenkäyttöliittymäänlisäkomponentteja ja ominaisuuksia.

Asennus- ja konfigurointiohjeet
Riippuvuuksien asentaminen:
Käytä Mavenia asentaaksesi kaikki tarvittavat riippuvuudet: -mvn clean install

Tietokannan konfigurointi:
Varmista, että sinulla on MySQL-palvelin käynnissä ja konfiguroi tietokantayhteytesi sovelluksen konfiguraatiotiedostossa (persistence.xml).

Vaihda tämä rivin arvokenttään ”drop-and-create” kun ajat sovelluksen ensimmäisen kerran. Tämä luo sinulle tarvittavan tietokannan.
<property name="jakarta.persistence.schema-generation.database.action" value="none"/>

Kun olet ajanut ohjelman, vaihda kentän arvo takaisin ”none”. Näin tietokanta on luotu ja se on valmiina käytettäväksi.
Käynnistä sovellus: -mvn javafx:run

Työtuntien kirjanpito:<br>
https://docs.google.com/spreadsheets/d/1N_X0B_qjmJYBH5EtVJIKErYgwNxge8i1APWWq521nrM/edit#gid=0

Trello:<br>
https://trello.com/invite/ohtugroup8/ATTI11228c76dbb902159af8f3d1a1fb19ba0B0E2209

Project Planning Document:<br>
https://metropoliafi-my.sharepoint.com/:w:/g/personal/mikkoolh_metropolia_fi/EUJrjHKrXwlPhICn0DhlaPABUFJQvIkn1jvqfzJj2F9jow?rtime=t_j6hbLC20g
