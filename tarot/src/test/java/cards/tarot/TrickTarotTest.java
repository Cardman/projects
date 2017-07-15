package cards.tarot;
import static code.util.opers.EquallableUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import code.util.Numbers;
import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;

@SuppressWarnings("static-method")
@RunWith(JUnitParamsRunner.class)
public class TrickTarotTest {

    Object[] entameursValeurs() {
        //new Description().getMethodName();
        return $($(0), $(1), $(2), $(3));
    }

    Object[] entameursCouleursValeurs() {
        return $($(0, Suit.TRUMP), $(1, Suit.TRUMP), $(2, Suit.TRUMP), $(3, Suit.TRUMP),
                $(0, Suit.HEART), $(1, Suit.HEART), $(2, Suit.HEART), $(3, Suit.HEART), $(0, Suit.SPADE),
                $(1, Suit.SPADE), $(2, Suit.SPADE), $(3, Suit.SPADE), $(0, Suit.DIAMOND), $(1, Suit.DIAMOND),
                $(2, Suit.DIAMOND), $(3, Suit.DIAMOND), $(0, Suit.CLUB), $(1, Suit.CLUB), $(2, Suit.CLUB),
                $(3, Suit.CLUB));
    }


    @Parameters(method = "entameursValeurs")
    @Test
    public void new_PliTarot_byte_Boolean_1Test(int _entameur) {
        TrickTarot pli_ = new TrickTarot((byte) _entameur,true);
        //nombreTotal++;
//        assertEq(0, pli_.getNumero());
        assertTrue(pli_.estVide());
        pli_ = new TrickTarot((byte) _entameur,true);
//        assertEq(1, pli_.getNumero());
        assertTrue(pli_.estVide());
    }

    @Test
    public void new_PliTarot_MainTarot_byte_Boolean_1Test() {
        TrickTarot pli_ = new TrickTarot(new HandTarot(), (byte) 0,true);
        //nombreTotal++;
//        assertEq(0, pli_.getNumero());
        assertTrue(pli_.estVide());
        pli_ = new TrickTarot(new HandTarot(), (byte) 0,true);
        //nombreTotal++;
//        assertEq(1, pli_.getNumero());
        assertTrue(pli_.estVide());
        HandTarot main_ = new HandTarot();
        main_.ajouter(CardTarot.DIAMOND_1);
        pli_ = new TrickTarot(main_, (byte) 0,true);
        //nombreTotal++;
//        assertEq(2, pli_.getNumero());
        assertEq(1, pli_.total() );
        assertTrue(pli_.contient(CardTarot.DIAMOND_1));
    }

    @Test
    public void couleurDemandee1Test() {
        TrickTarot pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        assertEq(Suit.UNDEFINED, pli_.couleurDemandee());
        pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(Suit.DIAMOND, pli_.couleurDemandee());
        pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(Suit.DIAMOND, pli_.couleurDemandee());
        pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_12);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(Suit.TRUMP, pli_.couleurDemandee());
        pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(Suit.SPADE, pli_.couleurDemandee());
        pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        pli_.getCartes().ajouter(CardTarot.TRUMP_20);
        assertEq(Suit.TRUMP, pli_.couleurDemandee());
        pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(Suit.UNDEFINED, pli_.couleurDemandee());
    }

    @Test
    @Parameters(method = "entameursValeurs")
    public void aJoue1Test(int _entameur) {
        TrickTarot pli_ = new TrickTarot((byte) _entameur, true);
        byte nombreJoueurs_ = 4;
        assertTrue(!pli_.aJoue((byte) 0, nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) 1, nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) 2, nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) 3, nombreJoueurs_));
    }

    @Test
    @Parameters(method = "entameursValeurs")
    public void aJoue2Test(int _entameur) {
        TrickTarot pli_ = new TrickTarot((byte) _entameur, true);
        byte nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertTrue(pli_.aJoue((byte) _entameur, nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+1)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+2)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+3)%nombreJoueurs_), nombreJoueurs_));
    }

    @Test
    @Parameters(method = "entameursValeurs")
    public void aJoue3Test(int _entameur) {
        TrickTarot pli_ = new TrickTarot((byte) _entameur, true);
        byte nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertTrue(pli_.aJoue((byte) _entameur, nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) ((_entameur+1)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+2)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+3)%nombreJoueurs_), nombreJoueurs_));
    }

    @Test
    @Parameters(method = "entameursValeurs")
    public void aJoue4Test(int _entameur) {
        TrickTarot pli_ = new TrickTarot((byte) _entameur, true);
        byte nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertTrue(pli_.aJoue((byte) _entameur, nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) ((_entameur+1)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) ((_entameur+2)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+3)%nombreJoueurs_), nombreJoueurs_));
    }

    @Test
    @Parameters(method = "entameursValeurs")
    public void aJoue5Test(int _entameur) {
        TrickTarot pli_ = new TrickTarot((byte) _entameur, true);
        byte nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertTrue(pli_.aJoue((byte) _entameur, nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) ((_entameur+1)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) ((_entameur+2)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) ((_entameur+3)%nombreJoueurs_), nombreJoueurs_));
    }

    @Test
    public void carteDuJoueur1Test() {
        TrickTarot pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        byte nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur((byte) 0, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur((byte) 0, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur((byte) 0, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        assertEq(CardTarot.HEART_KING, pli_.carteDuJoueur((byte) 2, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur((byte) 0, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        assertEq(CardTarot.HEART_KING, pli_.carteDuJoueur((byte) 2, nombreJoueurs_));
        assertEq(CardTarot.CLUB_7, pli_.carteDuJoueur((byte) 3, nombreJoueurs_));
        pli_ = new TrickTarot((byte) 1,true);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur((byte) 2, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur((byte) 2, nombreJoueurs_));
        assertEq(CardTarot.HEART_KING, pli_.carteDuJoueur((byte) 3, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(CardTarot.DIAMOND_1, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        assertEq(CardTarot.SPADE_JACK, pli_.carteDuJoueur((byte) 2, nombreJoueurs_));
        assertEq(CardTarot.HEART_KING, pli_.carteDuJoueur((byte) 3, nombreJoueurs_));
        assertEq(CardTarot.CLUB_7, pli_.carteDuJoueur((byte) 0, nombreJoueurs_));
    }

    @Test
    public void joueurAyantJouePliEnCours1Test() {
        TrickTarot pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        byte nombreJoueurs_ = 4;
        assertEq(-1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(0, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(0, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(0, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJouePliEnCours(CardTarot.HEART_KING, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(0, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJouePliEnCours(CardTarot.HEART_KING, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJouePliEnCours(CardTarot.CLUB_7, nombreJoueurs_));
        pli_ = new TrickTarot((byte) 1, true);
        //nombreTotal++;
        assertEq(-1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJouePliEnCours(CardTarot.HEART_KING, nombreJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(1, pli_.joueurAyantJouePliEnCours(CardTarot.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJouePliEnCours(CardTarot.SPADE_JACK, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJouePliEnCours(CardTarot.HEART_KING, nombreJoueurs_));
        assertEq(0, pli_.joueurAyantJouePliEnCours(CardTarot.CLUB_7, nombreJoueurs_));
    }

    @Test
    public void joueursAyantJoueAvant1Test() {
        TrickTarot pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        Numbers<Byte> joueurs_ = pli_.joueursAyantJoueAvant((byte) 1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(0, pli_.joueursAyantJoueAvant((byte) 0).size());
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 1);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 0));
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 2);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 0));
        assertTrue(joueurs_.containsObj((byte) 1));
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 3);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 0));
        assertTrue(joueurs_.containsObj((byte) 1));
        assertTrue(joueurs_.containsObj((byte) 2));
        pli_ = new TrickTarot((byte) 1,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(0, pli_.joueursAyantJoueAvant((byte) 1).size());
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 2);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 1));
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 3);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 1));
        assertTrue(joueurs_.containsObj((byte) 2));
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 0);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 1));
        assertTrue(joueurs_.containsObj((byte) 2));
        assertTrue(joueurs_.containsObj((byte) 3));
        pli_ = new TrickTarot((byte) 2,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(0, pli_.joueursAyantJoueAvant((byte) 2).size());
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 3);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 2));
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 0);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 2));
        assertTrue(joueurs_.containsObj((byte) 3));
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 1);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 2));
        assertTrue(joueurs_.containsObj((byte) 3));
        assertTrue(joueurs_.containsObj((byte) 0));
        pli_ = new TrickTarot((byte) 3,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_JACK);
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        pli_.getCartes().ajouter(CardTarot.CLUB_7);
        assertEq(0, pli_.joueursAyantJoueAvant((byte) 3).size());
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 0);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 3));
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 1);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 3));
        assertTrue(joueurs_.containsObj((byte) 0));
        joueurs_ = pli_.joueursAyantJoueAvant((byte) 2);
        assertEq(3, joueurs_.size());
        assertTrue(joueurs_.containsObj((byte) 3));
        assertTrue(joueurs_.containsObj((byte) 0));
        assertTrue(joueurs_.containsObj((byte) 1));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseur1Test(int _j) {
        byte nombreDeJoueurs_ = 4;
        byte j_ = (byte) ((_j + 1) % nombreDeJoueurs_);
        byte jTwo_ = (byte) ((_j + 2) % nombreDeJoueurs_);
        byte jThree_ = (byte) ((_j + 3) % nombreDeJoueurs_);

        TrickTarot pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_KING);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_7);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_KING);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_QUEEN);
        assertEq(jThree_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_4);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.HEART_KING);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.CLUB_QUEEN);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(jTwo_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_16);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.EXCUSE);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_ = new TrickTarot((byte) _j,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_10);
        assertEq(_j, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_16);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        assertEq(j_, pli_.getRamasseur(nombreDeJoueurs_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_18);
        assertEq(jThree_, pli_.getRamasseur(nombreDeJoueurs_));
    }

    @Test
    public void joueursCoupes1Test() {
        byte nombreDeJoueurs_ = 4;

        TrickTarot pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        Numbers<Byte> joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        byte j_ = (byte) ((0 + 2) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot((byte) 1,true);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (byte) ((1 + 2) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot((byte) 2,true);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (byte) ((2 + 2) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot((byte) 3,true);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (byte) ((3 + 2) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
    }

    @Test
    public void joueursCoupes2Test() {
        byte nombreDeJoueurs_ = 4;

        TrickTarot pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        Numbers<Byte> joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        byte j_ = (byte) ((0 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        byte jTwo_ = (byte) ((0 + 3) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot((byte) 1,true);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (byte) ((1 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        jTwo_ = (byte) ((1 + 3) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot((byte) 2,true);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (byte) ((2 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        jTwo_ = (byte) ((2 + 3) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot((byte) 3,true);
        //nombreTotal++;
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(0,joueurs_.size());
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        j_ = (byte) ((3 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(nombreDeJoueurs_);
        jTwo_ = (byte) ((3 + 3) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
    }

    @Test
    public void joueursDefausses1Test() {
        byte nombreDeJoueurs_ = 4;

        TrickTarot pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        Numbers<Byte> joueurs_ = pli_.joueursDefausses();
        byte j_ = (byte) ((0 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot((byte) 1,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses();
        j_ = (byte) ((1 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot((byte) 2,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses();
        j_ = (byte) ((2 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickTarot((byte) 3,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.DIAMOND_1);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses();
        j_ = (byte) ((3 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
    }

    @Test
    public void joueursDefausses2Test() {
        byte nombreDeJoueurs_ = 4;
        TrickTarot pli_ = new TrickTarot((byte) 0,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        Numbers<Byte> joueurs_ = pli_.joueursDefausses();
        byte j_ = (byte) ((0 + 1) % nombreDeJoueurs_);
        byte jTwo_ = (byte) ((0 + 3) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot((byte) 1,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses();
        j_ = (byte) ((1 + 1) % nombreDeJoueurs_);
        jTwo_ = (byte) ((1 + 3) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot((byte) 2,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses();
        j_ = (byte) ((2 + 1) % nombreDeJoueurs_);
        jTwo_ = (byte) ((2 + 3) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
        pli_ = new TrickTarot((byte) 3,true);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardTarot.TRUMP_14);
        pli_.getCartes().ajouter(CardTarot.SPADE_7);
        pli_.getCartes().ajouter(CardTarot.TRUMP_11);
        pli_.getCartes().ajouter(CardTarot.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses();
        j_ = (byte) ((3 + 1) % nombreDeJoueurs_);
        jTwo_ = (byte) ((3 + 3) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        assertTrue(joueurs_.containsObj(jTwo_));
    }
}
