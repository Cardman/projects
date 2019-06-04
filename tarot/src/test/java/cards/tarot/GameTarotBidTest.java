package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.CardTarot;
import code.util.EnumMap;
import org.junit.Test;

import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertTrue;

public final class GameTarotBidTest {
    @Test
    public void nbCartesMaitresses1Test() {
        EnumMap<Suit,HandTarot> played_ = new EnumMap<Suit, HandTarot>();
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_7);
        plSuit_.ajouter(CardTarot.HEART_4);
        played_.put(Suit.HEART,plSuit_);
        EnumMap<Suit,HandTarot> hand_ = new EnumMap<Suit,HandTarot>();
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_10);
        handSuit_.ajouter(CardTarot.HEART_6);
        hand_.put(Suit.HEART,handSuit_);
        assertEq(0,GameTarotBid.nbCartesMaitresses(hand_,played_,Suit.HEART));
    }
    @Test
    public void nbCartesMaitresses2Test() {
        EnumMap<Suit,HandTarot> played_ = new EnumMap<Suit, HandTarot>();
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_7);
        plSuit_.ajouter(CardTarot.HEART_4);
        played_.put(Suit.HEART,plSuit_);
        EnumMap<Suit,HandTarot> hand_ = new EnumMap<Suit,HandTarot>();
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_6);
        hand_.put(Suit.HEART,handSuit_);
        assertEq(1,GameTarotBid.nbCartesMaitresses(hand_,played_,Suit.HEART));
    }
    @Test
    public void nbCartesMaitresses3Test() {
        EnumMap<Suit,HandTarot> played_ = new EnumMap<Suit, HandTarot>();
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_KING);
        plSuit_.ajouter(CardTarot.HEART_4);
        played_.put(Suit.HEART,plSuit_);
        EnumMap<Suit,HandTarot> hand_ = new EnumMap<Suit,HandTarot>();
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_6);
        hand_.put(Suit.HEART,handSuit_);
        assertEq(1,GameTarotBid.nbCartesMaitresses(hand_,played_,Suit.HEART));
    }
    @Test
    public void nbCartesMaitresses4Test() {
        EnumMap<Suit,HandTarot> played_ = new EnumMap<Suit, HandTarot>();
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_KING);
        plSuit_.ajouter(CardTarot.HEART_KNIGHT);
        plSuit_.ajouter(CardTarot.HEART_4);
        played_.put(Suit.HEART,plSuit_);
        EnumMap<Suit,HandTarot> hand_ = new EnumMap<Suit,HandTarot>();
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_9);
        handSuit_.ajouter(CardTarot.HEART_6);
        hand_.put(Suit.HEART,handSuit_);
        assertEq(2,GameTarotBid.nbCartesMaitresses(hand_,played_,Suit.HEART));
    }
    @Test
    public void nbCartesMaitresses5Test() {
        EnumMap<Suit,HandTarot> played_ = new EnumMap<Suit, HandTarot>();
        HandTarot plSuit_ = new HandTarot();
        played_.put(Suit.HEART,plSuit_);
        EnumMap<Suit,HandTarot> hand_ = new EnumMap<Suit,HandTarot>();
        HandTarot handSuit_ = new HandTarot();
        hand_.put(Suit.HEART,handSuit_);
        assertEq(0,GameTarotBid.nbCartesMaitresses(hand_,played_,Suit.HEART));
    }
    @Test
    public void nbCartesMaitresses6Test() {
        EnumMap<Suit,HandTarot> played_ = new EnumMap<Suit, HandTarot>();
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouterCartes(HandTarot.couleurComplete(Suit.HEART));
        played_.put(Suit.HEART,plSuit_);
        EnumMap<Suit,HandTarot> hand_ = new EnumMap<Suit,HandTarot>();
        HandTarot handSuit_ = new HandTarot();
        hand_.put(Suit.HEART,handSuit_);
        assertEq(0,GameTarotBid.nbCartesMaitresses(hand_,played_,Suit.HEART));
    }
    @Test
    public void maitreDansUneCouleur1Test() {
        EnumMap<Suit,HandTarot> played_ = new EnumMap<Suit, HandTarot>();
        HandTarot plSuit_ = new HandTarot();
        played_.put(Suit.HEART,plSuit_);
        EnumMap<Suit,HandTarot> hand_ = new EnumMap<Suit,HandTarot>();
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_10);
        handSuit_.ajouter(CardTarot.HEART_9);
        handSuit_.ajouter(CardTarot.HEART_8);
        hand_.put(Suit.HEART,handSuit_);
        assertTrue(GameTarotBid.maitreDansUneCouleur(hand_,played_,Suit.HEART));
    }
    @Test
    public void maitreDansUneCouleur2Test() {
        EnumMap<Suit,HandTarot> played_ = new EnumMap<Suit, HandTarot>();
        HandTarot plSuit_ = new HandTarot();
        played_.put(Suit.HEART,plSuit_);
        EnumMap<Suit,HandTarot> hand_ = new EnumMap<Suit,HandTarot>();
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_10);
        handSuit_.ajouter(CardTarot.HEART_9);
        handSuit_.ajouter(CardTarot.HEART_8);
        handSuit_.ajouter(CardTarot.HEART_1);
        hand_.put(Suit.HEART,handSuit_);
        assertTrue(GameTarotBid.maitreDansUneCouleur(hand_,played_,Suit.HEART));
    }
    @Test
    public void maitreDansUneCouleur3Test() {
        EnumMap<Suit,HandTarot> played_ = new EnumMap<Suit, HandTarot>();
        HandTarot plSuit_ = new HandTarot();
        played_.put(Suit.HEART,plSuit_);
        EnumMap<Suit,HandTarot> hand_ = new EnumMap<Suit,HandTarot>();
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_10);
        handSuit_.ajouter(CardTarot.HEART_9);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.HEART_1);
        hand_.put(Suit.HEART,handSuit_);
        assertTrue(GameTarotBid.maitreDansUneCouleur(hand_,played_,Suit.HEART));
    }
    @Test
    public void maitreDansUneCouleur4Test() {
        EnumMap<Suit,HandTarot> played_ = new EnumMap<Suit, HandTarot>();
        HandTarot plSuit_ = new HandTarot();
        played_.put(Suit.HEART,plSuit_);
        EnumMap<Suit,HandTarot> hand_ = new EnumMap<Suit,HandTarot>();
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_10);
        handSuit_.ajouter(CardTarot.HEART_9);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.HEART_1);
        hand_.put(Suit.HEART,handSuit_);
        assertTrue(!GameTarotBid.maitreDansUneCouleur(hand_,played_,Suit.HEART));
    }
    @Test
    public void estUnJeuDeChelemSur1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_4);
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_QUEEN);
        plSuit_.ajouter(CardTarot.HEART_JACK);
        plSuit_.ajouter(CardTarot.HEART_9);
        plSuit_.ajouter(CardTarot.HEART_6);
        assertTrue(!GameTarotBid.estUnJeuDeChelemSur(handSuit_.couleurs(),plSuit_.couleurs()));
    }
    @Test
    public void estUnJeuDeChelemSur2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_4);
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_QUEEN);
        plSuit_.ajouter(CardTarot.HEART_JACK);
        plSuit_.ajouter(CardTarot.HEART_9);
        plSuit_.ajouter(CardTarot.HEART_6);
        assertTrue(!GameTarotBid.estUnJeuDeChelemSur(handSuit_.couleurs(),plSuit_.couleurs()));
    }
    @Test
    public void estUnJeuDeChelemSur3Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_4);
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_QUEEN);
        plSuit_.ajouter(CardTarot.HEART_JACK);
        plSuit_.ajouter(CardTarot.HEART_9);
        plSuit_.ajouter(CardTarot.HEART_6);
        plSuit_.ajouterCartes(HandTarot.atoutsSansExcuse());
        plSuit_.supprimerCartes(handSuit_.couleur(Suit.TRUMP));
        assertTrue(!GameTarotBid.estUnJeuDeChelemSur(handSuit_.couleurs(),plSuit_.couleurs()));
    }
    @Test
    public void estUnJeuDeChelemSur4Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_4);
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_QUEEN);
        plSuit_.ajouter(CardTarot.HEART_JACK);
        plSuit_.ajouter(CardTarot.HEART_9);
        plSuit_.ajouter(CardTarot.HEART_6);
        plSuit_.ajouterCartes(HandTarot.atoutsSansExcuse());
        plSuit_.supprimerCartes(handSuit_.couleur(Suit.TRUMP));
        assertTrue(!GameTarotBid.estUnJeuDeChelemSur(handSuit_.couleurs(),plSuit_.couleurs()));
    }
    @Test
    public void estUnJeuDeChelemSur5Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_4);
        HandTarot plSuit_ = new HandTarot();
        assertTrue(!GameTarotBid.estUnJeuDeChelemSur(handSuit_.couleurs(),plSuit_.couleurs()));
    }
    @Test
    public void estUnJeuDeChelemSur6Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_QUEEN);
        plSuit_.ajouter(CardTarot.SPADE_QUEEN);
        plSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        plSuit_.ajouter(CardTarot.CLUB_QUEEN);
        plSuit_.ajouterCartes(HandTarot.atoutsSansExcuse());
        plSuit_.supprimerCartes(handSuit_.couleur(Suit.TRUMP));
        assertTrue(GameTarotBid.estUnJeuDeChelemSur(handSuit_.couleurs(),plSuit_.couleurs()));
    }
    @Test
    public void maitreAtoutPourChelem1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_4);
        handSuit_.ajouter(CardTarot.CLUB_3);
        handSuit_.ajouter(CardTarot.CLUB_2);
        assertTrue(GameTarotBid.maitreAtoutPourChelem(handSuit_.couleurs(), (byte) 3));
    }
    @Test
    public void maitreAtoutPourChelem2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_4);
        handSuit_.ajouter(CardTarot.CLUB_3);
        handSuit_.ajouter(CardTarot.CLUB_2);
        assertTrue(!GameTarotBid.maitreAtoutPourChelem(handSuit_.couleurs(), (byte) 3));
    }
    @Test
    public void maitreAtoutPourChelem3Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        assertTrue(GameTarotBid.maitreAtoutPourChelem(handSuit_.couleurs(), (byte) 4));
    }
    @Test
    public void maitreAtoutPourChelem4Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        assertTrue(GameTarotBid.maitreAtoutPourChelem(handSuit_.couleurs(), (byte) 5));
    }
    @Test
    public void maitreAtoutPourChelem5Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        assertTrue(GameTarotBid.maitreAtoutPourChelem(handSuit_.couleurs(), (byte) 6));
    }
    @Test
    public void nbCouleursLargementMaitressesTest() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        assertEq(3, GameTarotBid.nbCouleursLargementMaitresses(handSuit_.couleurs(), (byte) 6));
    }
    @Test
    public void cartesPseudoMaitresses1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        HandTarot other_ = new HandTarot();
        other_.ajouter(CardTarot.SPADE_KING);
        HandTarot played_ = new HandTarot();
        EnumMap<Suit, HandTarot> out_ = GameTarotBid.cartesPseudoMaitresses(handSuit_.couleurs(), other_, played_.couleurs());
        assertEq(4, out_.size());
        assertEq(3, out_.getVal(Suit.HEART).total());
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_KING));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_QUEEN));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_KNIGHT));
        assertEq(6, out_.getVal(Suit.SPADE).total());
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_QUEEN));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_KNIGHT));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_JACK));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_10));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_9));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_8));
        assertEq(0, out_.getVal(Suit.DIAMOND).total());
        assertEq(1, out_.getVal(Suit.CLUB).total());
        assertTrue(out_.getVal(Suit.CLUB).contient(CardTarot.CLUB_KING));
    }
    @Test
    public void cartesPseudoMaitresses2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_7);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        HandTarot other_ = new HandTarot();
        other_.ajouter(CardTarot.SPADE_KING);
        HandTarot played_ = new HandTarot();
        EnumMap<Suit, HandTarot> out_ = GameTarotBid.cartesPseudoMaitresses(handSuit_.couleurs(), other_, played_.couleurs());
        assertEq(4, out_.size());
        assertEq(2, out_.getVal(Suit.HEART).total());
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_KING));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_QUEEN));
        assertEq(8, out_.getVal(Suit.SPADE).total());
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_QUEEN));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_KNIGHT));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_JACK));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_10));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_9));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_8));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_7));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_1));
        assertEq(0, out_.getVal(Suit.DIAMOND).total());
        assertEq(1, out_.getVal(Suit.CLUB).total());
        assertTrue(out_.getVal(Suit.CLUB).contient(CardTarot.CLUB_KING));
    }
    @Test
    public void cartesPseudoMaitresses3Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        HandTarot other_ = new HandTarot();
        other_.ajouter(CardTarot.SPADE_KING);
        HandTarot played_ = new HandTarot();
        EnumMap<Suit, HandTarot> out_ = GameTarotBid.cartesPseudoMaitresses(handSuit_.couleurs(), other_, played_.couleurs());
        assertEq(4, out_.size());
        assertEq(2, out_.getVal(Suit.HEART).total());
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_KING));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_QUEEN));
        assertEq(8, out_.getVal(Suit.SPADE).total());
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_KING));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_QUEEN));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_KNIGHT));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_JACK));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_10));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_9));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_8));
        assertTrue(out_.getVal(Suit.SPADE).contient(CardTarot.SPADE_1));
        assertEq(0, out_.getVal(Suit.DIAMOND).total());
        assertEq(1, out_.getVal(Suit.CLUB).total());
        assertTrue(out_.getVal(Suit.CLUB).contient(CardTarot.CLUB_KING));
    }
    @Test
    public void cartesPseudoMaitresses4Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.SPADE_10);
        handSuit_.ajouter(CardTarot.SPADE_9);
        handSuit_.ajouter(CardTarot.SPADE_8);
        handSuit_.ajouter(CardTarot.SPADE_7);
        handSuit_.ajouter(CardTarot.SPADE_2);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        HandTarot other_ = new HandTarot();
        other_.ajouter(CardTarot.SPADE_KING);
        HandTarot played_ = new HandTarot();
        EnumMap<Suit, HandTarot> out_ = GameTarotBid.cartesPseudoMaitresses(handSuit_.couleurs(), other_, played_.couleurs());
        assertEq(4, out_.size());
        assertEq(2, out_.getVal(Suit.HEART).total());
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_KING));
        assertTrue(out_.getVal(Suit.HEART).contient(CardTarot.HEART_QUEEN));
        assertEq(0, out_.getVal(Suit.SPADE).total());
        assertEq(0, out_.getVal(Suit.DIAMOND).total());
        assertEq(1, out_.getVal(Suit.CLUB).total());
        assertTrue(out_.getVal(Suit.CLUB).contient(CardTarot.CLUB_KING));
    }
    @Test
    public void estUnJeuDeChelem1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_QUEEN);
        plSuit_.ajouter(CardTarot.SPADE_QUEEN);
        plSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        plSuit_.ajouter(CardTarot.CLUB_QUEEN);
        plSuit_.ajouterCartes(HandTarot.atoutsSansExcuse());
        plSuit_.supprimerCartes(handSuit_.couleur(Suit.TRUMP));
        RulesTarot r_ = new RulesTarot();
        assertTrue(GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_,new HandTarot()));
    }
    @Test
    public void estUnJeuDeChelem2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_4);
        HandTarot plSuit_ = new HandTarot();
        plSuit_.ajouter(CardTarot.HEART_QUEEN);
        plSuit_.ajouter(CardTarot.HEART_JACK);
        plSuit_.ajouter(CardTarot.HEART_9);
        plSuit_.ajouter(CardTarot.HEART_6);
        RulesTarot r_ = new RulesTarot();
        assertTrue(!GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_,new HandTarot()));
    }

}
