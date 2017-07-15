package cards.belote;
import static code.util.opers.EquallableUtil.assertEq;
import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import code.util.Numbers;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.consts.Suit;

@RunWith(JUnitParamsRunner.class)
@SuppressWarnings("static-method")
public class TrickBeloteTest {

    Object[] entameursValeurs() {
        return $($(0), $(1), $(2), $(3));
    }

    Object[] entameursCouleursValeurs() {
        return $($(0, Suit.HEART), $(1, Suit.HEART), $(2, Suit.HEART), $(3, Suit.HEART), $(0, Suit.SPADE),
                $(1, Suit.SPADE), $(2, Suit.SPADE), $(3, Suit.SPADE), $(0, Suit.DIAMOND), $(1, Suit.DIAMOND),
                $(2, Suit.DIAMOND), $(3, Suit.DIAMOND), $(0, Suit.CLUB), $(1, Suit.CLUB), $(2, Suit.CLUB),
                $(3, Suit.CLUB));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void new_PliBelote_byte_1Test(int _entameur) {
        TrickBelote pli_ = new TrickBelote((byte) _entameur);
        //nombreTotal++;
        //assertEq(0, pli_.getNumero());
        assertTrue(pli_.estVide());
        pli_ = new TrickBelote((byte) _entameur);
        //assertEq(1, pli_.getNumero());
        assertTrue(pli_.estVide());
    }

    @Test
    public void new_PliBelote_MainBelote_1Test() {
        TrickBelote pli_ = new TrickBelote(new HandBelote(), (byte) 0);
        //nombreTotal++;
        //assertEq(0, pli_.getNumero());
        assertTrue(pli_.estVide());
        pli_ = new TrickBelote(new HandBelote(), (byte) 0);
        //nombreTotal++;
        //assertEq(1, pli_.getNumero());
        assertTrue(pli_.estVide());
        HandBelote main_ = new HandBelote();
        main_.ajouter(CardBelote.DIAMOND_1);
        pli_ = new TrickBelote(main_, (byte) 0);
        //nombreTotal++;
        //assertEq(2, pli_.getNumero());
        assertEq(1, pli_.total() );
        assertTrue(pli_.contient(CardBelote.DIAMOND_1));
    }

    @Test
    public void couleurDemandee1Test() {
        TrickBelote pli_ = new TrickBelote((byte) 0);
        //nombreTotal++;
        assertEq(Suit.UNDEFINED, pli_.couleurDemandee());
        pli_ = new TrickBelote((byte) 0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(Suit.DIAMOND, pli_.couleurDemandee());
        pli_ = new TrickBelote((byte) 0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertEq(Suit.DIAMOND, pli_.couleurDemandee());
    }

    @Test
    @Parameters(method = "entameursValeurs")
    public void aJoue1Test(int _entameur) {
        TrickBelote pli_ = new TrickBelote((byte) _entameur);
        byte nombreJoueurs_ = 4;
        assertTrue(!pli_.aJoue((byte) 0, nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) 1, nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) 2, nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) 3, nombreJoueurs_));
    }

    @Test
    @Parameters(method = "entameursValeurs")
    public void aJoue2Test(int _entameur) {
        TrickBelote pli_ = new TrickBelote((byte) _entameur);
        byte nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertTrue(pli_.aJoue((byte) 0, nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) 1, nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) 2, nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) 3, nombreJoueurs_));
    }

    @Test
    @Parameters(method = "entameursValeurs")
    public void aJoue3Test(int _entameur) {
        TrickBelote pli_ = new TrickBelote((byte) _entameur);
        byte nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertTrue(pli_.aJoue((byte) _entameur, nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+1)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+2)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+3)%nombreJoueurs_), nombreJoueurs_));
    }

    @Test
    @Parameters(method = "entameursValeurs")
    public void aJoue4Test(int _entameur) {
        TrickBelote pli_ = new TrickBelote((byte) _entameur);
        byte nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertTrue(pli_.aJoue((byte) _entameur, nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) ((_entameur+1)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+2)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+3)%nombreJoueurs_), nombreJoueurs_));
    }

    @Test
    @Parameters(method = "entameursValeurs")
    public void aJoue5Test(int _entameur) {
        TrickBelote pli_ = new TrickBelote((byte) _entameur);
        byte nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertTrue(pli_.aJoue((byte) _entameur, nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) ((_entameur+1)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(pli_.aJoue((byte) ((_entameur+2)%nombreJoueurs_), nombreJoueurs_));
        assertTrue(!pli_.aJoue((byte) ((_entameur+3)%nombreJoueurs_), nombreJoueurs_));
    }

    @Test
    public void carteDuJoueur1Test() {
        TrickBelote pli_ = new TrickBelote((byte) 0);
        //nombreTotal++;
        byte nombreJoueurs_ = 4;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur((byte) 0, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur((byte) 0, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur((byte) 0, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        assertEq(CardBelote.HEART_KING, pli_.carteDuJoueur((byte) 2, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur((byte) 0, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        assertEq(CardBelote.HEART_KING, pli_.carteDuJoueur((byte) 2, nombreJoueurs_));
        assertEq(CardBelote.CLUB_7, pli_.carteDuJoueur((byte) 3, nombreJoueurs_));
        pli_ = new TrickBelote((byte) 1);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur((byte) 2, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur((byte) 2, nombreJoueurs_));
        assertEq(CardBelote.HEART_KING, pli_.carteDuJoueur((byte) 3, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(CardBelote.DIAMOND_1, pli_.carteDuJoueur((byte) 1, nombreJoueurs_));
        assertEq(CardBelote.SPADE_JACK, pli_.carteDuJoueur((byte) 2, nombreJoueurs_));
        assertEq(CardBelote.HEART_KING, pli_.carteDuJoueur((byte) 3, nombreJoueurs_));
        assertEq(CardBelote.CLUB_7, pli_.carteDuJoueur((byte) 0, nombreJoueurs_));
    }

    @Test
    public void joueurAyantJoue1Test() {
        TrickBelote pli_ = new TrickBelote((byte) 0);
        //nombreTotal++;
        byte nombreJoueurs_ = 4;
        assertEq(-1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertEq(0, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJoue(CardBelote.HEART_KING, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(0, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(1, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJoue(CardBelote.HEART_KING, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJoue(CardBelote.CLUB_7, nombreJoueurs_));
        pli_ = new TrickBelote((byte) 1);
        //nombreTotal++;
        assertEq(-1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        assertEq(1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJoue(CardBelote.HEART_KING, nombreJoueurs_));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(1, pli_.joueurAyantJoue(CardBelote.DIAMOND_1, nombreJoueurs_));
        assertEq(2, pli_.joueurAyantJoue(CardBelote.SPADE_JACK, nombreJoueurs_));
        assertEq(3, pli_.joueurAyantJoue(CardBelote.HEART_KING, nombreJoueurs_));
        assertEq(0, pli_.joueurAyantJoue(CardBelote.CLUB_7, nombreJoueurs_));
    }

    @Test
    public void joueursAyantJoueAvant1Test() {
        TrickBelote pli_ = new TrickBelote((byte) 0);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        Numbers<Byte> joueurs_ = pli_.joueursAyantJoueAvant((byte) 1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(0,pli_.joueursAyantJoueAvant((byte) 0).size());
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
        pli_ = new TrickBelote((byte) 1);
        //nombreTotal++;
//        assertEq(0,pli_.joueursAyantJoueAvant((byte) 1).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(0,pli_.joueursAyantJoueAvant((byte) 1).size());
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
        pli_ = new TrickBelote((byte) 2);
        //nombreTotal++;
        //assertEq(0,pli_.joueursAyantJoueAvant((byte) 2).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
//        assertEq(0,pli_.joueursAyantJoueAvant((byte) 2).size());
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 3);
//        assertEq(1, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 2));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
//        assertEq(0,pli_.joueursAyantJoueAvant((byte) 2).size());
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 3);
//        assertEq(1, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 2));
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 0);
//        assertEq(2, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 2));
//        assertTrue(joueurs_.contains((byte) 3));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
//        assertEq(0,pli_.joueursAyantJoueAvant((byte) 2).size());
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 3);
//        assertEq(1, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 2));
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 0);
//        assertEq(2, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 2));
//        assertTrue(joueurs_.contains((byte) 3));
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 1);
//        assertEq(3, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 2));
//        assertTrue(joueurs_.contains((byte) 3));
//        assertTrue(joueurs_.contains((byte) 0));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(0,pli_.joueursAyantJoueAvant((byte) 2).size());
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
        pli_ = new TrickBelote((byte) 3);
        //nombreTotal++;
        //assertEq(0,pli_.joueursAyantJoueAvant((byte) 3).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
//        assertEq(0,pli_.joueursAyantJoueAvant((byte) 3).size());
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 0);
//        assertEq(1, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 3));
        pli_.getCartes().ajouter(CardBelote.SPADE_JACK);
//        assertEq(0,pli_.joueursAyantJoueAvant((byte) 3).size());
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 0);
//        assertEq(1, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 3));
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 1);
//        assertEq(2, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 3));
//        assertTrue(joueurs_.contains((byte) 0));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
//        assertEq(0,pli_.joueursAyantJoueAvant((byte) 3).size());
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 0);
//        assertEq(1, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 3));
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 1);
//        assertEq(2, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 3));
//        assertTrue(joueurs_.contains((byte) 0));
//        joueurs_ = pli_.joueursAyantJoueAvant((byte) 2);
//        assertEq(3, joueurs_.size());
//        assertTrue(joueurs_.contains((byte) 3));
//        assertTrue(joueurs_.contains((byte) 0));
//        assertTrue(joueurs_.contains((byte) 1));
        pli_.getCartes().ajouter(CardBelote.CLUB_7);
        assertEq(0,pli_.joueursAyantJoueAvant((byte) 3).size());
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
    public void getRamasseurPliEnCours_BeloteToutAtout1Test(int _entameur) {
        BidBelote enchere_ = BidBelote.ALL_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(enchere_);
        byte nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote((byte) _entameur);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(_entameur, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(_entameur, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(_entameur, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        byte j_ = (byte) ((_entameur + 3) % nombreDeJoueurs_);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseurPliEnCours_BeloteSansAtout2Test(int _entameur) {
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(enchere_);
        byte nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote((byte) _entameur);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(_entameur, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(_entameur, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(_entameur, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(_entameur, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }


    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur3Test(int _j) {
        byte nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(e);
        enchereCouleur_.setCouleur(Suit.HEART);
        byte jTwo_ = (byte) ((_j + 2) % nombreDeJoueurs_);

        TrickBelote pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jTwo_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur4Test(int _j) {
        byte nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(e);
        enchereCouleur_.setCouleur(Suit.SPADE);
        byte j_ = (byte) ((_j + 1) % nombreDeJoueurs_);

        TrickBelote pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(j_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur5Test(int _j) {
        byte nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(e);
        enchereCouleur_.setCouleur(Suit.DIAMOND);
        byte jThree_ = (byte) ((_j + 3) % nombreDeJoueurs_);

        TrickBelote pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jThree_, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseurPliEnCours_BeloteCouleur6Test(int _j) {
        byte nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(e);
        enchereCouleur_.setCouleur(Suit.CLUB);

        TrickBelote pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(_j, pli_.getRamasseurPliEnCours(nombreDeJoueurs_, enchereCouleur_));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseur_BeloteToutAtout1Test(int _entameur) {
        BidBelote enchere_ = BidBelote.ALL_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(enchere_);
        byte nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote((byte) _entameur);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        byte j_ = (byte) ((_entameur + 3) % nombreDeJoueurs_);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseur_BeloteSansAtout2Test(int _entameur) {
        BidBelote enchere_ = BidBelote.NO_TRUMP;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(enchere_);
        TrickBelote pli_ = new TrickBelote((byte) _entameur);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(_entameur, pli_.getRamasseur(enchereCouleur_));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseur_BeloteCouleur3Test(int _j) {
        byte nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(e);
        enchereCouleur_.setCouleur(Suit.HEART);
        byte jTwo_ = (byte) ((_j + 2) % nombreDeJoueurs_);

        TrickBelote pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(jTwo_, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jTwo_, pli_.getRamasseur(enchereCouleur_));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseur_BeloteCouleur4Test(int _j) {
        byte nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(e);
        enchereCouleur_.setCouleur(Suit.SPADE);
        byte j_ = (byte) ((_j + 1) % nombreDeJoueurs_);

        TrickBelote pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(j_, pli_.getRamasseur(enchereCouleur_));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseur_BeloteCouleur5Test(int _j) {
        byte nombreDeJoueurs_ = 4;
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(e);
        enchereCouleur_.setCouleur(Suit.DIAMOND);
        byte jThree_ = (byte) ((_j + 3) % nombreDeJoueurs_);

        TrickBelote pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(_j, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(jThree_, pli_.getRamasseur(enchereCouleur_));
    }

    @Parameters(method = "entameursValeurs")
    @Test
    public void getRamasseur_BeloteCouleur6Test(int _j) {
        BidBelote e = BidBelote.SUIT;
        BidBeloteSuit enchereCouleur_ = new BidBeloteSuit();
        enchereCouleur_.setEnchere(e);
        enchereCouleur_.setCouleur(Suit.CLUB);

        TrickBelote pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        assertEq(_j, pli_.getRamasseur(enchereCouleur_));
        pli_ = new TrickBelote((byte) _j);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(_j, pli_.getRamasseur(enchereCouleur_));
    }

//    Object[] bidsGr() {
//        return $($(BidBelote.NO_TRUMP),$(BidBelote.ALL_TRUMP));
//    }

//    @Parameters(method="bidsGr")
//    public void joueursCoupes1Test(BidBelote _bid) {
//
//    }
    @Test
    public void joueursCoupes1Test() {
        TrickBelote pli_ = new TrickBelote((byte) 0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_ = new TrickBelote((byte) 1);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_ = new TrickBelote((byte) 2);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_ = new TrickBelote((byte) 3);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        assertEq(0,pli_.joueursCoupes(Suit.UNDEFINED).size());
    }

    @Test
    public void joueursCoupes2Test() {
        byte nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote((byte) 0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        Numbers<Byte> joueurs_ = pli_.joueursCoupes(Suit.HEART);
        byte j_ = (byte) ((0 + 2) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote((byte) 1);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(Suit.HEART);
        j_ = (byte) ((1 + 2) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote((byte) 2);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(Suit.HEART);
        j_ = (byte) ((2 + 2) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote((byte) 3);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursCoupes(Suit.HEART);
        j_ = (byte) ((3 + 2) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
    }

    @Test
    public void joueursDefausses1Test() {
        byte nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote((byte) 0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        Numbers<Byte> joueurs_ = pli_.joueursDefausses(Suit.UNDEFINED);
        byte j_ = (byte) ((0 + 1) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        j_ = (byte) ((j_ + 1) % nombreDeJoueurs_);
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote((byte) 1);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        joueurs_ = pli_.joueursDefausses(Suit.UNDEFINED);
        j_ = (byte) ((1 + 1) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        j_ = (byte) ((j_ + 1) % nombreDeJoueurs_);
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote((byte) 2);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        joueurs_ = pli_.joueursDefausses(Suit.UNDEFINED);
        j_ = (byte) ((2 + 1) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        j_ = (byte) ((j_ + 1) % nombreDeJoueurs_);
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote((byte) 3);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_JACK);
        joueurs_ = pli_.joueursDefausses(Suit.UNDEFINED);
        j_ = (byte) ((3 + 1) % nombreDeJoueurs_);
        assertEq(2, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        j_ = (byte) ((j_ + 1) % nombreDeJoueurs_);
        assertTrue(joueurs_.containsObj(j_));
    }

    @Test
    public void joueursDefausses2Test() {
        byte nombreDeJoueurs_ = 4;
        TrickBelote pli_ = new TrickBelote((byte) 0);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        Numbers<Byte> joueurs_ = pli_.joueursDefausses(Suit.HEART);
        byte j_ = (byte) ((0 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote((byte) 1);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(Suit.HEART);
        j_ = (byte) ((1 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote((byte) 2);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(Suit.HEART);
        j_ = (byte) ((2 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
        pli_ = new TrickBelote((byte) 3);
        //nombreTotal++;
        pli_.getCartes().ajouter(CardBelote.DIAMOND_1);
        pli_.getCartes().ajouter(CardBelote.SPADE_7);
        pli_.getCartes().ajouter(CardBelote.HEART_KING);
        pli_.getCartes().ajouter(CardBelote.DIAMOND_8);
        joueurs_ = pli_.joueursDefausses(Suit.HEART);
        j_ = (byte) ((3 + 1) % nombreDeJoueurs_);
        assertEq(1, joueurs_.size());
        assertTrue(joueurs_.containsObj(j_));
    }
}
