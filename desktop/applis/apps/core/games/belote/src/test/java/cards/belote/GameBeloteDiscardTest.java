package cards.belote;

import cards.belote.enumerations.*;
import cards.consts.*;
import code.util.*;
import org.junit.Test;

public final class GameBeloteDiscardTest extends EquallableBeloteUtil {
    @Test
    public void cartesNonMaitressesDebut1Test() {
        HandBelote handSuit_ = new HandBelote();
        handSuit_.ajouter(CardBelote.HEART_1);
        handSuit_.ajouter(CardBelote.HEART_KING);
        handSuit_.ajouter(CardBelote.HEART_QUEEN);
        handSuit_.ajouter(CardBelote.SPADE_JACK);
        handSuit_.ajouter(CardBelote.SPADE_9);
        handSuit_.ajouter(CardBelote.SPADE_1);
        handSuit_.ajouter(CardBelote.SPADE_10);
        handSuit_.ajouter(CardBelote.SPADE_QUEEN);
        handSuit_.ajouter(CardBelote.SPADE_8);
        handSuit_.ajouter(CardBelote.CLUB_KING);
        HandBelote h_ = cartesNonMaitressesDebut(handSuit_, bidSuit(Suit.SPADE, 0, BidBelote.SUIT));
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardBelote.HEART_KING));
        assertTrue(h_.contient(CardBelote.HEART_QUEEN));
        assertTrue(h_.contient(CardBelote.CLUB_KING));
    }

    @Test
    public void cartesNonMaitressesDebut2Test() {
        HandBelote handSuit_ = new HandBelote();
        handSuit_.ajouter(CardBelote.HEART_1);
        handSuit_.ajouter(CardBelote.HEART_KING);
        handSuit_.ajouter(CardBelote.HEART_QUEEN);
        handSuit_.ajouter(CardBelote.SPADE_1);
        handSuit_.ajouter(CardBelote.SPADE_10);
        handSuit_.ajouter(CardBelote.SPADE_QUEEN);
        handSuit_.ajouter(CardBelote.SPADE_JACK);
        handSuit_.ajouter(CardBelote.SPADE_9);
        handSuit_.ajouter(CardBelote.SPADE_8);
        handSuit_.ajouter(CardBelote.CLUB_KING);
        HandBelote h_ = cartesNonMaitressesDebut(handSuit_, bidSuit(Suit.UNDEFINED, 0, BidBelote.NO_TRUMP));
        assertEq(3, h_.total());
        assertTrue(h_.contient(CardBelote.HEART_KING));
        assertTrue(h_.contient(CardBelote.HEART_QUEEN));
        assertTrue(h_.contient(CardBelote.CLUB_KING));
    }

    @Test
    public void nullToEmpty() {
        assertEq(0,GameBeloteDiscard.nullToEmpty(null).total());
    }
    @Test
    public void cartesNonMaitressesDebut3Test() {
        HandBelote handSuit_ = new HandBelote();
        handSuit_.ajouter(CardBelote.HEART_1);
        handSuit_.ajouter(CardBelote.HEART_KING);
        handSuit_.ajouter(CardBelote.HEART_QUEEN);
        handSuit_.ajouter(CardBelote.SPADE_JACK);
        handSuit_.ajouter(CardBelote.SPADE_9);
        handSuit_.ajouter(CardBelote.SPADE_10);
        handSuit_.ajouter(CardBelote.SPADE_QUEEN);
        handSuit_.ajouter(CardBelote.SPADE_8);
        handSuit_.ajouter(CardBelote.CLUB_KING);
        HandBelote h_ = cartesNonMaitressesDebut(handSuit_, bidSuit(Suit.SPADE, 0, BidBelote.SUIT));
        assertEq(6, h_.total());
        assertTrue(h_.contient(CardBelote.HEART_KING));
        assertTrue(h_.contient(CardBelote.HEART_QUEEN));
        assertTrue(h_.contient(CardBelote.SPADE_10));
        assertTrue(h_.contient(CardBelote.SPADE_QUEEN));
        assertTrue(h_.contient(CardBelote.SPADE_8));
        assertTrue(h_.contient(CardBelote.CLUB_KING));
    }

    @Test
    public void cartesNonMaitressesDebut4Test() {
        HandBelote handSuit_ = new HandBelote();
        handSuit_.ajouter(CardBelote.HEART_1);
        handSuit_.ajouter(CardBelote.HEART_KING);
        handSuit_.ajouter(CardBelote.HEART_QUEEN);
        handSuit_.ajouter(CardBelote.SPADE_1);
        handSuit_.ajouter(CardBelote.SPADE_10);
        handSuit_.ajouter(CardBelote.SPADE_JACK);
        handSuit_.ajouter(CardBelote.SPADE_9);
        handSuit_.ajouter(CardBelote.SPADE_8);
        handSuit_.ajouter(CardBelote.CLUB_KING);
        HandBelote h_ = cartesNonMaitressesDebut(handSuit_, bidSuit(Suit.UNDEFINED, 0, BidBelote.NO_TRUMP));
        assertEq(6, h_.total());
        assertTrue(h_.contient(CardBelote.HEART_KING));
        assertTrue(h_.contient(CardBelote.HEART_QUEEN));
        assertTrue(h_.contient(CardBelote.CLUB_KING));
        assertTrue(h_.contient(CardBelote.SPADE_JACK));
        assertTrue(h_.contient(CardBelote.SPADE_9));
        assertTrue(h_.contient(CardBelote.SPADE_8));
    }

    @Test
    public void strategieEcart1() {
        HandBelote h_ = newGameBeloteCallDiscard(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_KING,CardBelote.HEART_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_9),
                create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                        CardBelote.CLUB_7,CardBelote.DIAMOND_8,CardBelote.DIAMOND_7,CardBelote.SPADE_10), bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        assertEq(8, h_.total());
        assertTrue(h_.contient(CardBelote.SPADE_10));
        assertTrue(h_.contient(CardBelote.CLUB_KING));
        assertTrue(h_.contient(CardBelote.CLUB_QUEEN));
        assertTrue(h_.contient(CardBelote.CLUB_8));
        assertTrue(h_.contient(CardBelote.CLUB_7));
        assertTrue(h_.contient(CardBelote.DIAMOND_10));
        assertTrue(h_.contient(CardBelote.DIAMOND_KING));
        assertTrue(h_.contient(CardBelote.DIAMOND_QUEEN));
    }

    @Test
    public void strategieEcart2() {
        HandBelote h_ = newGameBeloteCallDiscard(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                        CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10),
                create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                        CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1), bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        assertEq(8, h_.total());
        assertTrue(h_.contient(CardBelote.SPADE_KING));
        assertTrue(h_.contient(CardBelote.SPADE_QUEEN));
        assertTrue(h_.contient(CardBelote.CLUB_KING));
        assertTrue(h_.contient(CardBelote.CLUB_QUEEN));
        assertTrue(h_.contient(CardBelote.CLUB_9));
        assertTrue(h_.contient(CardBelote.CLUB_8));
        assertTrue(h_.contient(CardBelote.DIAMOND_7));
        assertTrue(h_.contient(CardBelote.DIAMOND_8));
    }

    @Test
    public void strategieEcart3() {
        HandBelote h_ = newGameBeloteCallDiscard(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_KING,CardBelote.HEART_QUEEN,
                        CardBelote.DIAMOND_10,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.HEART_10),
                create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                        CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1), bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        assertEq(8, h_.total());
        assertTrue(h_.contient(CardBelote.DIAMOND_10));
        assertTrue(h_.contient(CardBelote.CLUB_1));
        assertTrue(h_.contient(CardBelote.DIAMOND_1));
        assertTrue(h_.contient(CardBelote.SPADE_1));
        assertTrue(h_.contient(CardBelote.CLUB_9));
        assertTrue(h_.contient(CardBelote.CLUB_8));
        assertTrue(h_.contient(CardBelote.CLUB_QUEEN));
        assertTrue(h_.contient(CardBelote.CLUB_KING));
    }

    @Test
    public void strategieEcart4() {
        HandBelote h_ = newGameBeloteCallDiscard(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_KING,CardBelote.HEART_QUEEN,
                        CardBelote.DIAMOND_1,CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_9),
                create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                        CardBelote.CLUB_7,CardBelote.DIAMOND_8,CardBelote.DIAMOND_7,CardBelote.SPADE_8), bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        assertEq(8, h_.total());
        assertTrue(h_.contient(CardBelote.SPADE_8));
        assertTrue(h_.contient(CardBelote.CLUB_KING));
        assertTrue(h_.contient(CardBelote.CLUB_QUEEN));
        assertTrue(h_.contient(CardBelote.CLUB_8));
        assertTrue(h_.contient(CardBelote.CLUB_7));
        assertTrue(h_.contient(CardBelote.CLUB_9));
        assertTrue(h_.contient(CardBelote.DIAMOND_8));
        assertTrue(h_.contient(CardBelote.DIAMOND_7));
    }

    @Test
    public void strategieEcart5() {
        HandBelote h_ = newGameBeloteCallDiscard(create(CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.SPADE_8,CardBelote.SPADE_7,
                        CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10),
                create(CardBelote.CLUB_10,CardBelote.SPADE_10,CardBelote.CLUB_7,CardBelote.CLUB_8,
                        CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1), bidSuit(Suit.UNDEFINED, 0, BidBelote.NO_TRUMP));
        assertEq(8, h_.total());
        assertTrue(h_.contient(CardBelote.HEART_7));
        assertTrue(h_.contient(CardBelote.HEART_8));
        assertTrue(h_.contient(CardBelote.SPADE_8));
        assertTrue(h_.contient(CardBelote.SPADE_7));
        assertTrue(h_.contient(CardBelote.CLUB_7));
        assertTrue(h_.contient(CardBelote.CLUB_8));
        assertTrue(h_.contient(CardBelote.DIAMOND_7));
        assertTrue(h_.contient(CardBelote.DIAMOND_8));
    }

    @Test
    public void annoncerUnChelem1() {
        assertEq(0, newGameBeloteCallDiscardSlam(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_KING,CardBelote.HEART_QUEEN,
                        CardBelote.DIAMOND_10,CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_9),
                create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                        CardBelote.CLUB_7,CardBelote.DIAMOND_8,CardBelote.DIAMOND_7,CardBelote.SPADE_10), bidSuit(Suit.HEART, 0, BidBelote.SUIT)));
    }

    @Test
    public void annoncerUnChelem2() {
        assertEq(162, newGameBeloteCallDiscardSlam(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                        CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10),
                create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                        CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1), bidSuit(Suit.HEART, 0, BidBelote.SUIT)));
    }

    @Test
    public void completerDonneTest() {
        assertEq(0, completerDonne());
    }
    @Test
    public void user1() {
        GameBelote g_ = newGameBeloteCallDiscardUser(create(CardBelote.HEART_JACK, CardBelote.HEART_9, CardBelote.SPADE_KING, CardBelote.SPADE_QUEEN,
                        CardBelote.DIAMOND_10, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8, CardBelote.HEART_10),
                create(CardBelote.CLUB_KING, CardBelote.CLUB_QUEEN, CardBelote.CLUB_9, CardBelote.CLUB_8,
                        CardBelote.CLUB_1, CardBelote.HEART_1, CardBelote.DIAMOND_1, CardBelote.SPADE_1), bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        g_.ajouterCartesUtilisateur();
        assertEq(16, g_.getDeal().hand().total());
        assertEq(0, g_.getProgressingTrick().getCartes().total());
    }
    @Test
    public void user2() {
        GameBelote g_ = newGameBeloteCallDiscardUser(create(CardBelote.HEART_JACK, CardBelote.HEART_9, CardBelote.SPADE_KING, CardBelote.SPADE_QUEEN,
                        CardBelote.DIAMOND_10, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8, CardBelote.HEART_10),
                create(CardBelote.CLUB_KING, CardBelote.CLUB_QUEEN, CardBelote.CLUB_9, CardBelote.CLUB_8,
                        CardBelote.CLUB_1, CardBelote.HEART_1, CardBelote.DIAMOND_1, CardBelote.SPADE_1), bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        g_.ajouterCartesUtilisateur();
        g_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        assertEq(15, g_.getDeal().hand().total());
        assertEq(1, g_.getProgressingTrick().getCartes().total());
        assertEq(CardBelote.CLUB_8, g_.getProgressingTrick().getCartes().carte(0));
    }
    @Test
    public void user3() {
        GameBelote g_ = newGameBeloteCallDiscardUser(create(CardBelote.HEART_JACK, CardBelote.HEART_9, CardBelote.SPADE_KING, CardBelote.SPADE_QUEEN,
                        CardBelote.DIAMOND_10, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8, CardBelote.HEART_10),
                create(CardBelote.CLUB_KING, CardBelote.CLUB_QUEEN, CardBelote.CLUB_9, CardBelote.CLUB_8,
                        CardBelote.CLUB_1, CardBelote.HEART_1, CardBelote.DIAMOND_1, CardBelote.SPADE_1), bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        g_.ajouterCartesUtilisateur();
        DefGameBelote d_ = new DefGameBelote();
        g_.ajouterUneCarteDansPliEnCoursPreneur(d_.discard(CardBelote.CLUB_8));
        g_.invaliderAjoutCarteEcart(d_.restore(CardBelote.CLUB_8));
        assertEq(16, g_.getDeal().hand().total());
        assertEq(0, g_.getProgressingTrick().getCartes().total());
    }
    @Test
    public void user4() {
        GameBelote g_ = newGameBeloteCallDiscardUser(create(CardBelote.HEART_JACK, CardBelote.HEART_9, CardBelote.SPADE_KING, CardBelote.SPADE_QUEEN,
                        CardBelote.DIAMOND_10, CardBelote.DIAMOND_7, CardBelote.DIAMOND_8, CardBelote.HEART_10),
                create(CardBelote.CLUB_KING, CardBelote.CLUB_QUEEN, CardBelote.CLUB_9, CardBelote.CLUB_8,
                        CardBelote.CLUB_1, CardBelote.HEART_1, CardBelote.DIAMOND_1, CardBelote.SPADE_1), bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        g_.ajouterCartesUtilisateur();
        g_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        g_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        g_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        g_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        g_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        g_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        g_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        g_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        g_.ajouterChelemUtilisateur();
        assertEq(0,g_.validateDiscard());
    }
    private HandBelote cartesNonMaitressesDebut(HandBelote _hand, BidBeloteSuit _bid) {
        IdMap<Suit, HandBelote> seq_ = _hand.couleurs(_bid);
        IdMap<Suit, HandBelote> lead_ = GameBeloteCommon.cartesMaitresses(seq_, new HandBelote().couleurs(_bid), _bid);
        return GameBeloteDiscard.cartesNonMaitressesDebut(_hand,lead_, _bid);
    }
    private static BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }

    private static HandBelote newGameBeloteCallDiscard(HandBelote _h, HandBelote _l,BidBeloteSuit _b) {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 2);
        db_.getDeal().add(_h);
        db_.getDeal().add(new HandBelote());
        db_.getDeal().add(new HandBelote());
        db_.getDeal().add(_l);
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        GameBelote gb_ = new GameBelote(GameType.RANDOM, db_, r_);
        gb_.ajouterContrat(_b);
        gb_.ecarter(new DefGameBelote());
        return gb_.getProgressingTrick().getCartes();
    }

    private static GameBelote newGameBeloteCallDiscardUser(HandBelote _h, HandBelote _l,BidBeloteSuit _b) {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(_h);
        db_.getDeal().add(new HandBelote());
        db_.getDeal().add(new HandBelote());
        db_.getDeal().add(_l);
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        GameBelote gb_ = new GameBelote(GameType.RANDOM, db_, r_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(_b);
        return gb_;
    }

    private static int newGameBeloteCallDiscardSlam(HandBelote _h, HandBelote _l,BidBeloteSuit _b) {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 2);
        db_.getDeal().add(_h);
        db_.getDeal().add(new HandBelote());
        db_.getDeal().add(new HandBelote());
        db_.getDeal().add(_l);
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        GameBelote gb_ = new GameBelote(GameType.RANDOM, db_, r_);
        gb_.ajouterContrat(_b);
        gb_.ecarter(new DefGameBelote());
        return gb_.getBid().getPoints();
    }

    private static int completerDonne() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        RulesBelote r_ = new RulesBelote();
        r_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        GameBelote gb_ = new GameBelote(GameType.RANDOM, db_, r_);
        gb_.ajouterContrat(bidSuit(Suit.UNDEFINED, 0, BidBelote.FOLD));
        gb_.ajouterContrat(bidSuit(Suit.HEART, 0, BidBelote.SUIT));
        gb_.ecarter(new DefGameBelote());
        return gb_.completerDonne();
    }
}
