package cards.tarot;

import cards.consts.Suit;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import code.util.CustList;
import code.util.EnumList;
import code.util.EnumMap;
import org.junit.Test;

import static cards.tarot.EquallableTarotUtil.assertEq;
import static org.junit.Assert.assertSame;
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
    public void couleursPseudosMaitres1Test() {
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
        EnumMap<Suit, HandTarot> suits_ = handSuit_.couleurs();
        EnumMap<Suit, HandTarot> pseudo_ = GameTarotBid.cartesPseudoMaitresses(suits_, other_, played_.couleurs());
        EnumList<Suit> out_ = GameTarotBid.couleursPseudosMaitres(suits_, pseudo_);
        assertEq(1, out_.size());
        assertTrue(out_.containsObj(Suit.CLUB));
    }
    @Test
    public void couleursPseudosMaitres2Test() {
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
        EnumMap<Suit, HandTarot> suits_ = handSuit_.couleurs();
        EnumMap<Suit, HandTarot> pseudo_ = GameTarotBid.cartesPseudoMaitresses(suits_, other_, played_.couleurs());
        EnumList<Suit> out_ = GameTarotBid.couleursPseudosMaitres(suits_, pseudo_);
        assertEq(2, out_.size());
        assertTrue(out_.containsObj(Suit.SPADE));
        assertTrue(out_.containsObj(Suit.CLUB));
    }
    @Test
    public void nbCouleursPseudoMaitresses1Test() {
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
        EnumMap<Suit, HandTarot> suits_ = handSuit_.couleurs();
        assertEq(0,GameTarotBid.nbCouleursPseudoMaitresses(suits_,other_, (byte) 6));
    }
    @Test
    public void nbCouleursPseudoMaitresses2Test() {
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
        EnumMap<Suit, HandTarot> suits_ = handSuit_.couleurs();
        assertEq(1,GameTarotBid.nbCouleursPseudoMaitresses(suits_,other_, (byte) 6));
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
    @Test
    public void estUnJeuDeChelem3Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        HandTarot plSuit_ = new HandTarot();
        RulesTarot r_ = new RulesTarot((byte) 3);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        assertTrue(!GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_,new HandTarot()));
    }
    @Test
    public void estUnJeuDeChelem4Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        HandTarot plSuit_ = new HandTarot();
        RulesTarot r_ = new RulesTarot((byte) 3);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        assertTrue(GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_,new HandTarot()));
    }
    @Test
    public void estUnJeuDeChelem5Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        HandTarot plSuit_ = new HandTarot();
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        assertTrue(!GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_,new HandTarot()));
    }
    @Test
    public void estUnJeuDeChelem6Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        HandTarot plSuit_ = new HandTarot();
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        assertTrue(GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_,new HandTarot()));
    }
    @Test
    public void estUnJeuDeChelem7Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        HandTarot plSuit_ = new HandTarot();
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_KING);
        assertTrue(GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_, cartesAppeler_));
    }
    @Test
    public void estUnJeuDeChelem8Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_10);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        HandTarot plSuit_ = new HandTarot();
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_JACK);
        assertTrue(!GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_,cartesAppeler_));
    }
    @Test
    public void estUnJeuDeChelem9Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        HandTarot plSuit_ = new HandTarot();
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_QUEEN);
        assertTrue(GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_,cartesAppeler_));
    }
    @Test
    public void estUnJeuDeChelem10Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        HandTarot plSuit_ = new HandTarot();
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_JACK);
        assertTrue(GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_,cartesAppeler_));
    }
    @Test
    public void estUnJeuDeChelem11Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        HandTarot plSuit_ = new HandTarot();
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        HandTarot cartesAppeler_ = new HandTarot();
        cartesAppeler_.ajouter(CardTarot.HEART_KNIGHT);
        assertTrue(!GameTarotBid.estUnJeuDeChelem(handSuit_.couleurs(),plSuit_.couleurs(),r_,cartesAppeler_));
    }
    @Test
    public void cartesAppeler1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        HandTarot called_ = g_.cartesAppeler();
        assertEq(4, called_.total());
        assertTrue(called_.contient(CardTarot.HEART_KING));
        assertTrue(called_.contient(CardTarot.SPADE_KING));
        assertTrue(called_.contient(CardTarot.DIAMOND_KING));
        assertTrue(called_.contient(CardTarot.CLUB_KING));
    }
    @Test
    public void cartesAppeler2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        HandTarot called_ = g_.cartesAppeler();
        assertEq(4, called_.total());
        assertTrue(called_.contient(CardTarot.HEART_QUEEN));
        assertTrue(called_.contient(CardTarot.SPADE_QUEEN));
        assertTrue(called_.contient(CardTarot.DIAMOND_QUEEN));
        assertTrue(called_.contient(CardTarot.CLUB_QUEEN));
    }
    @Test
    public void cartesAppeler3Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        HandTarot called_ = g_.cartesAppeler();
        assertEq(4, called_.total());
        assertTrue(called_.contient(CardTarot.HEART_KNIGHT));
        assertTrue(called_.contient(CardTarot.SPADE_KNIGHT));
        assertTrue(called_.contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(called_.contient(CardTarot.CLUB_KNIGHT));
    }
    @Test
    public void cartesAppeler4Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        HandTarot called_ = g_.cartesAppeler();
        assertEq(4, called_.total());
        assertTrue(called_.contient(CardTarot.HEART_JACK));
        assertTrue(called_.contient(CardTarot.SPADE_JACK));
        assertTrue(called_.contient(CardTarot.DIAMOND_JACK));
        assertTrue(called_.contient(CardTarot.CLUB_JACK));
    }
    @Test
    public void cartesAppeler5Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        HandTarot called_ = g_.cartesAppeler();
        assertEq(3, called_.total());
        assertTrue(called_.contient(CardTarot.TRUMP_21));
        assertTrue(called_.contient(CardTarot.EXCUSE));
        assertTrue(called_.contient(CardTarot.TRUMP_1));
    }
    @Test
    public void cartesAppeler6Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_CHAR);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        HandTarot called_ = g_.cartesAppeler();
        assertEq(19, called_.total());
        assertTrue(called_.contient(CardTarot.TRUMP_21));
        assertTrue(called_.contient(CardTarot.EXCUSE));
        assertTrue(called_.contient(CardTarot.TRUMP_1));
        assertTrue(called_.contient(CardTarot.HEART_JACK));
        assertTrue(called_.contient(CardTarot.SPADE_JACK));
        assertTrue(called_.contient(CardTarot.DIAMOND_JACK));
        assertTrue(called_.contient(CardTarot.CLUB_JACK));
        assertTrue(called_.contient(CardTarot.HEART_KNIGHT));
        assertTrue(called_.contient(CardTarot.SPADE_KNIGHT));
        assertTrue(called_.contient(CardTarot.DIAMOND_KNIGHT));
        assertTrue(called_.contient(CardTarot.CLUB_KNIGHT));
        assertTrue(called_.contient(CardTarot.HEART_QUEEN));
        assertTrue(called_.contient(CardTarot.SPADE_QUEEN));
        assertTrue(called_.contient(CardTarot.DIAMOND_QUEEN));
        assertTrue(called_.contient(CardTarot.CLUB_QUEEN));
        assertTrue(called_.contient(CardTarot.HEART_KING));
        assertTrue(called_.contient(CardTarot.SPADE_KING));
        assertTrue(called_.contient(CardTarot.DIAMOND_KING));
        assertTrue(called_.contient(CardTarot.CLUB_KING));
    }
    @Test
    public void cartesAppeler7Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        HandTarot called_ = g_.cartesAppeler();
        assertEq(0, called_.total());
    }
    @Test
    public void allowedBids1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        EnumList<BidTarot> called_ = g_.allowedBids();
        assertEq(5, called_.size());
        assertTrue(called_.containsObj(BidTarot.FOLD));
        assertTrue(called_.containsObj(BidTarot.TAKE));
        assertTrue(called_.containsObj(BidTarot.GUARD));
        assertTrue(called_.containsObj(BidTarot.GUARD_WITHOUT));
        assertTrue(called_.containsObj(BidTarot.GUARD_AGAINST));
    }
    @Test
    public void allowedBids2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KNIGHT);
        handSuit_.ajouter(CardTarot.DIAMOND_JACK);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KNIGHT);
        handSuit_.ajouter(CardTarot.CLUB_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        r_.getContrats().put(BidTarot.SLAM,true);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        EnumList<BidTarot> called_ = g_.allowedBids();
        assertEq(6, called_.size());
        assertTrue(called_.containsObj(BidTarot.FOLD));
        assertTrue(called_.containsObj(BidTarot.TAKE));
        assertTrue(called_.containsObj(BidTarot.GUARD));
        assertTrue(called_.containsObj(BidTarot.GUARD_WITHOUT));
        assertTrue(called_.containsObj(BidTarot.GUARD_AGAINST));
        assertTrue(called_.containsObj(BidTarot.SLAM));
    }
    @Test
    public void incrTest() {
        assertTrue(!GameTarotBid.incr(CardTarot.TRUMP_21,new CustList<HandTarot>(),0));
    }
    @Test
    public void strategieContrat1Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_AGAINST,bid_);
    }
    @Test
    public void strategieContrat2Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        r_.getContrats().put(BidTarot.SLAM,true);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.SLAM,bid_);
    }
    @Test
    public void strategieContrat3Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_3_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_AGAINST,bid_);
    }
    @Test
    public void strategieContrat4Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_3_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        r_.getContrats().put(BidTarot.SLAM,true);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.SLAM,bid_);
    }
    @Test
    public void strategieContrat5Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_3);
        handSuit_.ajouter(CardTarot.SPADE_2);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_2);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_2);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.FOLD,bid_);
    }
    @Test
    public void strategieContrat6Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_2);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_2);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.TAKE,bid_);
    }
    @Test
    public void strategieContrat7Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_2);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_2);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        r_.getContrats().put(BidTarot.TAKE,false);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD,bid_);
    }
    @Test
    public void strategieContrat8Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.EXCUSE);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD,bid_);
    }
    @Test
    public void strategieContrat9Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_2);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_2);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.FOLD);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_, bids_,BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD,bid_);
    }
    @Test
    public void strategieContrat10Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_2);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_2);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.FOLD);
        bids_.add(BidTarot.TAKE);
        bids_.add(BidTarot.FOLD);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_, bids_,BidTarot.TAKE);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.FOLD,bid_);
    }
    @Test
    public void strategieContrat11Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.EXCUSE);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat12Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.EXCUSE);
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_AGAINST,bid_);
    }
    @Test
    public void strategieContrat13Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.EXCUSE);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD_WITHOUT);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_, bids_,BidTarot.GUARD_WITHOUT);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.FOLD,bid_);
    }
    @Test
    public void strategieContrat14Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_1);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_AGAINST,bid_);
    }
    @Test
    public void strategieContrat15Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_2);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_AGAINST,bid_);
    }
    @Test
    public void strategieContrat16Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_21);
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_1);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.TAKE,bid_);
    }
    @Test
    public void strategieContrat17Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.TAKE,bid_);
    }
    @Test
    public void strategieContrat18Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_3);
        r_.setDealing(DealingTarot.DEAL_1_VS_3);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.TAKE,bid_);
    }
    @Test
    public void strategieContrat19Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_WITHOUT_CALL);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD,bid_);
    }
    @Test
    public void strategieContrat20Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_KNIGHT);
        handSuit_.ajouter(CardTarot.SPADE_JACK);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.TAKE,bid_);
    }
    @Test
    public void strategieContrat21Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_9);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_KNIGHT);
        handSuit_.ajouter(CardTarot.HEART_JACK);
        handSuit_.ajouter(CardTarot.HEART_3);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD,bid_);
    }
    @Test
    public void strategieContrat22Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_9);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_7);
        handSuit_.ajouter(CardTarot.TRUMP_6);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat23Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_9);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_7);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.TRUMP_3);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat24Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_9);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_7);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.TRUMP_3);
        handSuit_.ajouter(CardTarot.TRUMP_2);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat25Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_7);
        handSuit_.ajouter(CardTarot.TRUMP_6);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.TRUMP_3);
        handSuit_.ajouter(CardTarot.TRUMP_2);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat26Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_7);
        handSuit_.ajouter(CardTarot.TRUMP_6);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_3_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat27Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_7);
        handSuit_.ajouter(CardTarot.TRUMP_6);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_4);
        r_.setDealing(DealingTarot.DEAL_1_VS_4);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat28Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_3);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_2);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_2);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_4);
        r_.setDealing(DealingTarot.DEAL_1_VS_4);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.FOLD,bid_);
    }
    @Test
    public void strategieContrat29Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.TRUMP_4);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_4);
        r_.setDealing(DealingTarot.DEAL_1_VS_4);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.FOLD,bid_);
    }
    @Test
    public void strategieContrat30Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_CALL_KING);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat31Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat32Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_2);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.FOLD,bid_);
    }
    @Test
    public void strategieContrat33Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_2);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        r_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.FOLD,bid_);
    }
    @Test
    public void strategieContrat34Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_9);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_7);
        handSuit_.ajouter(CardTarot.TRUMP_6);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat35Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_9);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_7);
        handSuit_.ajouter(CardTarot.TRUMP_6);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.GUARD_WITHOUT,bid_);
    }
    @Test
    public void strategieContrat36Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.HEART_2);
        handSuit_.ajouter(CardTarot.HEART_1);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.SPADE_3);
        handSuit_.ajouter(CardTarot.SPADE_2);
        handSuit_.ajouter(CardTarot.SPADE_1);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.DIAMOND_3);
        handSuit_.ajouter(CardTarot.DIAMOND_2);
        handSuit_.ajouter(CardTarot.DIAMOND_1);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_4);
        handSuit_.ajouter(CardTarot.CLUB_3);
        handSuit_.ajouter(CardTarot.CLUB_2);
        handSuit_.ajouter(CardTarot.CLUB_1);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.FOLD,bid_);
    }
    @Test
    public void strategieContrat37Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_9);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_7);
        handSuit_.ajouter(CardTarot.TRUMP_6);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        handSuit_.ajouter(CardTarot.HEART_4);
        handSuit_.ajouter(CardTarot.HEART_3);
        handSuit_.ajouter(CardTarot.SPADE_KING);
        handSuit_.ajouter(CardTarot.SPADE_QUEEN);
        handSuit_.ajouter(CardTarot.SPADE_4);
        handSuit_.ajouter(CardTarot.SPADE_3);
        handSuit_.ajouter(CardTarot.DIAMOND_KING);
        handSuit_.ajouter(CardTarot.DIAMOND_QUEEN);
        handSuit_.ajouter(CardTarot.DIAMOND_4);
        handSuit_.ajouter(CardTarot.DIAMOND_3);
        handSuit_.ajouter(CardTarot.CLUB_KING);
        handSuit_.ajouter(CardTarot.CLUB_QUEEN);
        handSuit_.ajouter(CardTarot.CLUB_4);
        handSuit_.ajouter(CardTarot.CLUB_3);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_1_VS_2);
        r_.setDealing(DealingTarot.DEAL_1_VS_2);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_,new EnumList<BidTarot>(),BidTarot.FOLD);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.FOLD,bid_);
    }
    @Test
    public void strategieContrat38Test() {
        HandTarot handSuit_ = new HandTarot();
        handSuit_.ajouter(CardTarot.TRUMP_20);
        handSuit_.ajouter(CardTarot.TRUMP_19);
        handSuit_.ajouter(CardTarot.TRUMP_18);
        handSuit_.ajouter(CardTarot.TRUMP_17);
        handSuit_.ajouter(CardTarot.TRUMP_16);
        handSuit_.ajouter(CardTarot.TRUMP_15);
        handSuit_.ajouter(CardTarot.TRUMP_14);
        handSuit_.ajouter(CardTarot.TRUMP_13);
        handSuit_.ajouter(CardTarot.TRUMP_12);
        handSuit_.ajouter(CardTarot.TRUMP_11);
        handSuit_.ajouter(CardTarot.TRUMP_10);
        handSuit_.ajouter(CardTarot.TRUMP_9);
        handSuit_.ajouter(CardTarot.TRUMP_8);
        handSuit_.ajouter(CardTarot.TRUMP_7);
        handSuit_.ajouter(CardTarot.TRUMP_6);
        handSuit_.ajouter(CardTarot.TRUMP_5);
        handSuit_.ajouter(CardTarot.HEART_KING);
        handSuit_.ajouter(CardTarot.HEART_QUEEN);
        RulesTarot r_ = new RulesTarot(DealingTarot.DEAL_2_VS_2_CALL_KING);
        r_.setDealing(DealingTarot.DEAL_2_VS_2_CALL_KING);
        EnumList<BidTarot> bids_ = new EnumList<BidTarot>();
        bids_.add(BidTarot.GUARD_WITHOUT);
        GameTarotBid g_ = new GameTarotBid(handSuit_,r_, bids_,BidTarot.GUARD_WITHOUT);
        BidTarot bid_ = g_.strategieContrat();
        assertSame(BidTarot.FOLD,bid_);
    }
}
