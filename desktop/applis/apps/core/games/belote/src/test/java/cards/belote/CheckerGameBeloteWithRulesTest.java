package cards.belote;

import code.maths.montecarlo.DefaultGenerator;
import code.util.CustList;
import code.util.core.BoolVal;
import org.junit.Test;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.GameType;
import cards.consts.Suit;


public class CheckerGameBeloteWithRulesTest extends EquallableBeloteUtil {
    @Test
    public void loadGame1() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.loadGame();
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getTricks().size());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void loadGame2() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.annoncer((byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.loadGame();
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(2, game_.getTricks().size());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }
    @Test
    public void loadGame3() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.loadGame();
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getTricks().size());
        assertEq(0, game_.getEntameur());
    }

    @Test
    public void loadGame4() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.annoncer((byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.loadGame();
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getTricks().size());
        assertEq(1, game_.getEntameur());
    }

    @Test
    public void loadGame5() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.loadGame();
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getTricks().size());
        assertEq(0, game_.getEntameur());
    }

    @Test
    public void loadGame6() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.loadGame();
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getBid().getBid());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getTricks().size());
        assertEq(0, game_.getEntameur());
    }
    @Test
    public void check1Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getBid().getBid());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getBid().getBid());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check3Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getBid().getBid());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check4Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getBid().getBid());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    private static DealBelote deal1Classic(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    @Test
    public void check5Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check6Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.OTHER_SUIT);
        bid_.setSuit(Suit.CLUB);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.OTHER_SUIT, game_.getBid().getBid());
        assertEq(Suit.CLUB, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check7Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(80, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check8Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(90, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check9Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(90, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check10Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(90, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check11Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.NO_TRUMP, game_.getBid().getBid());
        assertEq(Suit.UNDEFINED, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check12Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.NO_TRUMP, game_.getBid().getBid());
        assertEq(Suit.UNDEFINED, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check13Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.NO_TRUMP, game_.getBid().getBid());
        assertEq(Suit.UNDEFINED, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check14Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setSuit(Suit.SPADE);
        bid_.setBid(BidBelote.SUIT);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.NO_TRUMP, game_.getBid().getBid());
        assertEq(Suit.UNDEFINED, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check15Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    private static DealBelote deal1(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.HEART_8);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_10);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    @Test
    public void check16Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(80, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check17Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(80, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check18Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check19Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_8);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check20Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.HEART_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(80, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    private static DealBelote deal2Classic(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.SPADE_1);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    private static DealBelote deal3Classic(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_7);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.SPADE_1);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    @Test
    public void check21Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        assertTrue(!game_.autoriseBeloteRebelote());
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        assertTrue(game_.autoriseBeloteRebelote((byte)1));
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check211Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal3Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        assertTrue(!game_.autoriseBeloteRebelote((byte)1));
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.DIAMOND_7);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }
    @Test
    public void check22Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }
    @Test
    public void check23Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getTricks().size());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }
    @Test
    public void check24Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        assertTrue(game_.autoriseBeloteRebelote((byte)1));
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        assertTrue(!game_.autoriseBeloteRebelote((byte)2));
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        assertTrue(game_.autoriseBeloteRebelote((byte)1));
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check25Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check26Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(162);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
//        game_.setEntameur(game_.getPreneur());
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(162, game_.getBid().getPoints());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
    }

    @Test
    public void check27Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(162);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
//        game_.setEntameur(game_.getPreneur());
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_7);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(162, game_.getBid().getPoints());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
    }

    @Test
    public void check28Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.annoncer((byte) 0);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check29Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
    }

    @Test
    public void check30Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    @Test
    public void check31Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        rules_.getAllowedBids().put(BidBelote.ALL_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.ALL_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.ALL_TRUMP, game_.getBid().getBid());
        assertEq(Suit.UNDEFINED, game_.getBid().getSuit());
        assertEq(80, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertEq(0, game_.cartesBeloteRebelote().total());
    }

    @Test
    public void check32Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getBid().getBid());
        assertEq(Suit.UNDEFINED, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
    }

    private static DealBelote deal1Slam(byte _dealer) {
        CustList<HandBelote> hands_ = new CustList<HandBelote>();
        HandBelote hand_;
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_1);
        hand_.ajouter(CardBelote.SPADE_1);
        hand_.ajouter(CardBelote.SPADE_JACK);
        hand_.ajouter(CardBelote.CLUB_KING);
        hand_.ajouter(CardBelote.DIAMOND_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.DIAMOND_9);
        hand_.ajouter(CardBelote.CLUB_9);
        hand_.ajouter(CardBelote.CLUB_JACK);
        hand_.ajouter(CardBelote.SPADE_KING);
        hand_.ajouter(CardBelote.HEART_10);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.CLUB_1);
        hand_.ajouter(CardBelote.DIAMOND_QUEEN);
        hand_.ajouter(CardBelote.DIAMOND_7);
        hand_.ajouter(CardBelote.SPADE_QUEEN);
        hand_.ajouter(CardBelote.SPADE_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.HEART_QUEEN);
        hand_.ajouter(CardBelote.HEART_9);
        hand_.ajouter(CardBelote.HEART_7);
        hand_.ajouter(CardBelote.CLUB_QUEEN);
        hand_.ajouter(CardBelote.CLUB_7);
        hands_.add(hand_);
        hand_ = new HandBelote();
        hand_.ajouter(CardBelote.SPADE_9);
        hand_.ajouter(CardBelote.SPADE_10);
        hand_.ajouter(CardBelote.HEART_1);
        hand_.ajouter(CardBelote.HEART_8);
        hand_.ajouter(CardBelote.DIAMOND_8);
        hand_.ajouter(CardBelote.CLUB_8);
        hand_.ajouter(CardBelote.SPADE_8);
        hand_.ajouter(CardBelote.CLUB_10);
        hand_.ajouter(CardBelote.HEART_KING);
        hand_.ajouter(CardBelote.DIAMOND_KING);
        hand_.ajouter(CardBelote.HEART_JACK);
        hand_.ajouter(CardBelote.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealBelote(hands_,_dealer);
    }

    @Test
    public void check33Test() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Slam((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_JACK);
        game_.getDistribution().jouer((byte) 1,CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_KING);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_9);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_8);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_7);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_9);
        game_.getDistribution().jouer((byte) 2,CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_9);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_9);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.SPADE_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_QUEEN);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_7);
        game_.getDistribution().jouer((byte) 3,CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_KING);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.DIAMOND_10);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_8);
        game_.getDistribution().jouer((byte) 2,CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_KING);
        game_.getDistribution().jouer((byte) 3,CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_JACK);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_1);
        game_.getDistribution().jouer((byte) 1,CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_10);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_1);
        game_.getDistribution().jouer((byte)3,CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.HEART_QUEEN);
        game_.ajouterPliEnCours();
        game_.setPliEnCours();
        game_.getDistribution().jouer((byte) 0,CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_KING);
        game_.getDistribution().jouer((byte) 1,CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_JACK);
        game_.getDistribution().jouer((byte) 2,CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_10);
        game_.getDistribution().jouer((byte) 3,CardBelote.CLUB_QUEEN);
        game_.ajouterUneCarteDansPliEnCours(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.setEntameur();
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
    }

    @Test
    public void check34Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
    }

    @Test
    public void check1FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.HEART);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check2FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(70);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check3FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.OTHER_SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check4FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check5FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check6FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check7FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check8FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedBids().put(BidBelote.ALL_TRUMP, BoolVal.TRUE);
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }


    @Test
    public void check9FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.OTHER_SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check10FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedBids().put(BidBelote.ALL_TRUMP, BoolVal.TRUE);
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.OTHER_SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check11FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_7);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check12FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check13FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        bid_ = new BidBeloteSuit();
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.HEART_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.HEART_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check14FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check15FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check151FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check16FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.annoncer((byte) 0);
        game_.getAnnonce((byte) 0).getHand().supprimerCartes();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check161FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.annoncer((byte) 0);
        game_.getAnnonce((byte) 0).getHand().supprimerCartes();
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_1);
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_KING);
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check162FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.annoncer((byte) 0);
        game_.getAnnonce((byte) 0).setPlayer((byte) 1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }
    @Test
    public void check17FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.annoncer((byte) 0);
        game_.getAnnonce((byte) 0).setDeclare(DeclaresBelote.THIRTY);
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_10);
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_9);
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check18FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.getAnnoncesBeloteRebelote((byte) 1).jouer(CardBelote.SPADE_KING);
        game_.getAnnoncesBeloteRebelote((byte) 1).ajouter(CardBelote.SPADE_QUEEN);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check19FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.getAnnoncesBeloteRebelote((byte) 1).jouer(CardBelote.SPADE_KING);
        game_.getAnnoncesBeloteRebelote((byte) 0).ajouter(CardBelote.SPADE_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check20FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.getPliEnCours().setEntameur(1);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check21FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.getPliEnCours().setEntameur(2);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check22FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.getPliEnCours().setEntameur(2);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check23FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.ALL_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check24FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.getDistribution().getDeal().add(new HandBelote());
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check25FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        HandBelote hand_ = game_.getDistribution().derniereMain();
        hand_.ajouter(game_.getDistribution().hand().jouer(0));
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check26FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check27FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.setAnnoncesBeloteRebelote((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setPliEnCours();
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCours((byte) 1, CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCours((byte) 2, CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCours((byte) 3, CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCours((byte) 0, CardBelote.DIAMOND_9);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check28FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        deal_.hand().jouer(0);
        deal_.hand().ajouter(CardBelote.HEART_1);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check29FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
    }

    @Test
    public void check30FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        DealBelote deal_ = deal2Classic((byte) 3);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
    }

    @Test
    public void check31FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(90);
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        game_.ajouterContrat(bid_);
        first_ = game_.playerAfter((byte) first_);
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        bid_.setPoints(100);
        game_.ajouterContrat(bid_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check32FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CardBelote c_ = deal_.getDeal().get(0).jouer(0);
        deal_.getDeal().get(1).ajouter(c_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check33FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CardBelote c_ = deal_.getDeal().get(0).jouer(0);
        deal_.getDeal().get(1).ajouter(c_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check34FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CardBelote c_ = deal_.getDeal().get(0).jouer(0);
        deal_.derniereMain().ajouter(c_);
        c_ = deal_.getDeal().get(1).jouer(0);
        deal_.derniereMain().ajouter(c_);
        c_ = deal_.getDeal().get(2).jouer(0);
        deal_.derniereMain().ajouter(c_);
        c_ = deal_.getDeal().get(3).jouer(0);
        deal_.derniereMain().ajouter(c_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check35FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        deal_.getDeal().get(1).ajouter(CardBelote.WHITE);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check36FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check37FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.getDeclares().clear();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check38FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.getDeclaresBeloteRebelote().clear();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check39FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.getScoresRef().clear();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check40FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.getWonLastTrick().clear();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check41FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
        assertTrue(!rules_.getListeAnnoncesAutorisees().isEmpty());
        game_.getRules().getAllowedBids().clear();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    @Test
    public void check42FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        assertTrue(!rules_.getListeEncheresAutorisees().isEmpty());
        game_.getRules().getAllowedDeclares().clear();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

    private DealBelote deal(RulesBelote _rules) {
        DealBelote deal_ = new DealBelote(0);
        deal_.setDealer((byte) 0);
        deal_.initDonne(_rules, new DisplayingBelote(), DefaultGenerator.oneElt(), HandBelote.pileBase());
        return deal_;
    }

}
