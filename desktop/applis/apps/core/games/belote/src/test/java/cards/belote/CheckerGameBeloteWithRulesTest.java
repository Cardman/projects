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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
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
        game_.annoncer();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
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
        game_.annoncer();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
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
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertTrue(game_.keepBidding());
        assertEq(1, game_.getPreneur());
        assertEq(1, game_.getBids().size());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(80, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertTrue(game_.noPlayedClassic());
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
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(80, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(80, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        assertTrue(!game_.autoriseBeloteRebelote());
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        assertTrue(game_.autoriseBeloteRebelote());
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        assertTrue(!game_.autoriseBeloteRebelote());
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getTricks().size());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        assertTrue(game_.autoriseBeloteRebelote());
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        assertTrue(!game_.autoriseBeloteRebelote());
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        assertTrue(game_.autoriseBeloteRebelote());
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertFalse(game_.keepBidding());
        assertEq(1, game_.getRamasseur());
        assertFalse(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(162, game_.getBid().getPoints());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(162, game_.getBid().getPoints());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        game_.annoncer();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
    }

    @Test
    public void check35Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertTrue(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check36Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        slam(game_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check37Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        slam(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check38Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        game_.ajouterCartesUtilisateur();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check39Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check40Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayersSec();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        slam(game_);
        game_.validateDiscard();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check41Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        slam(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check42Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayersSec();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        slam(game_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1,game_.getTricks().size());
        assertEq(8,game_.getTricks().first().total());
        assertFalse(game_.keepBidding());
    }
    @Test
    public void check43Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        slam(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.getTricks().add(game_.getProgressingTrick());
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2,game_.getTricks().size());
        assertEq(0,game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
    }

    @Test
    public void check44Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        slam(game_);
        game_.validateDiscard();
        CheckerGameBeloteWithRules.check(game_);
        assertFalse(game_.noPlayedClassic());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check45Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertTrue(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check46Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slam(game_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check47Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slam(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check48Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterCartesUtilisateur();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check49Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check50Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayersSec();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slam(game_);
        game_.validateDiscard();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check51Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slam(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check52Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayersSec();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slam(game_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1,game_.getTricks().size());
        assertEq(8,game_.getTricks().first().total());
        assertFalse(game_.keepBidding());
    }
    @Test
    public void check53Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slam(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.getTricks().add(game_.getProgressingTrick());
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2,game_.getTricks().size());
        assertEq(0,game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
    }

    @Test
    public void check54Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slam(game_);
        game_.validateDiscard();
        CheckerGameBeloteWithRules.check(game_);
        assertFalse(game_.noPlayedClassic());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check55Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertTrue(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check56Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        slam(game_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check57Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        slam(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check58Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterCartesUtilisateur();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check59Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check60Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayersSec();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        slam(game_);
        game_.validateDiscard();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check61Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        slam(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check62Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayersSec();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        slam(game_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1,game_.getTricks().size());
        assertEq(8,game_.getTricks().first().total());
        assertFalse(game_.keepBidding());
    }
    @Test
    public void check63Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        slam(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.getTricks().add(game_.getProgressingTrick());
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2,game_.getTricks().size());
        assertEq(0,game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
    }

    @Test
    public void check64Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        slam(game_);
        game_.validateDiscard();
        CheckerGameBeloteWithRules.check(game_);
        assertFalse(game_.noPlayedClassic());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check65Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertTrue(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check66Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slamFiveCards(game_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check67Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slamFiveCards(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check68Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterCartesUtilisateur();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check69Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterCartesUtilisateur();
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertTrue(game_.getTricks().isEmpty());
    }

    @Test
    public void check70Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersSecFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slamFiveCards(game_);
        game_.validateDiscard();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check71Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slamFiveCards(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }

    @Test
    public void check72Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersSecFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slamFiveCards(game_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1,game_.getTricks().size());
        assertEq(5,game_.getTricks().first().total());
        assertFalse(game_.keepBidding());
    }
    @Test
    public void check73Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slamFiveCards(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.getTricks().add(game_.getProgressingTrick());
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(2,game_.getTricks().size());
        assertEq(0,game_.getProgressingTrick().total());
        assertFalse(game_.keepBidding());
    }

    @Test
    public void check74Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        slamFiveCards(game_);
        game_.validateDiscard();
        CheckerGameBeloteWithRules.check(game_);
        assertFalse(game_.noPlayedClassic());
        assertFalse(game_.keepBidding());
        assertFalse(game_.getTricks().isEmpty());
    }
    @Test
    public void check75Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2_24);
        DealBelote deal_ = dealThreePlayers24Classic();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getBid().getBid());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
    }
    @Test
    public void check76Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_24);
        DealBelote deal_ = dealThreePlayers24Coinche();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getBid().getBid());
        assertEq(2, game_.getEntameur());
        assertEq(2, game_.getRamasseur());
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
    }
    @Test
    public void check77Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2_24);
        DealBelote deal_ = dealThreePlayers24Classic();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.SUIT));
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
    }
    @Test
    public void check78Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_24);
        DealBelote deal_ = dealThreePlayers24Coinche();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,162,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterDixDeDerPliEnCours();
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
    }

    @Test
    public void check79Test() {
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
//        bid_ = new BidBeloteSuit();
//        first_ = game_.playerAfter((byte) first_);
//        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(1, game_.getPreneur());
        assertEq(4, game_.getBids().size());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
    }
    @Test
    public void check80Test() {
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.getTricks().add(game_.getProgressingTrick());
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertEq(Suit.SPADE, game_.getBid().getSuit());
        assertEq(0, game_.getBid().getPoints());
        assertEq(1, game_.getEntameur());
        assertEq(1, game_.getRamasseur());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
        assertEq(1, game_.getTricks().size());
        assertEq(0, game_.getProgressingTrick().total());
        assertEq(1, game_.getProgressingTrick().getEntameur());
    }
    @Test
    public void check81Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_1);
        DealBelote deal_ = dealTwoPlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getBid().getBid());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
    }
    @Test
    public void check82Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_1);
        DealBelote deal_ = dealTwoPlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(-1, game_.getPreneur());
        assertEq(BidBelote.FOLD, game_.getBid().getBid());
        assertEq(0, game_.getEntameur());
        assertEq(0, game_.getRamasseur());
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
    }
    @Test
    public void check83Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_1);
        DealBelote deal_ = dealTwoPlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.SUIT));
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterDixDeDerPliEnCours();

        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_8);
        game_.ajouterDixDeDerPliEnCours();

        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();

        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterDixDeDerPliEnCours();

        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
    }
    @Test
    public void check84Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_1);
        DealBelote deal_ = dealTwoPlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.HEART,162,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.completerDonne();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        game_.setAnnoncesBeloteRebelote(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterDixDeDerPliEnCours();

        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_8);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_7);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_8);
        game_.ajouterDixDeDerPliEnCours();

        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_9);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();

        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_QUEEN);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.CLUB_KING);
        game_.ajouterDixDeDerPliEnCours();

        CheckerGameBeloteWithRules.check(game_);
        assertTrue(game_.getError().isEmpty());
        assertEq(0, game_.getPreneur());
        assertEq(BidBelote.SUIT, game_.getBid().getBid());
        assertFalse(game_.keepBidding());
        assertFalse(game_.noPlayedClassic());
    }
    @Test
    public void check0FailTest() {
        RulesBelote rules_ = new RulesBelote();
        DealBelote deal_ = deal1Classic((byte) 0);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        BidBeloteSuit bid_;
        bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.SPADE);
        game_.ajouterContrat(bid_);
        game_.completerDonne();
        game_.getDeal().derniereMain().getCards().swapIndexes(8,11);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        quick(game_);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_10);
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
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
        game_.annoncer();
        game_.getAnnonce((byte) 0).getHand().supprimerCartes();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
//        game_.ajouterPliEnCoursLoc();
        quick(game_);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
//        game_.ajouterPliEnCoursLoc();
        quick(game_);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
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
        game_.annoncer();
        game_.getAnnonce((byte) 0).getHand().supprimerCartes();
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_1);
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_KING);
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
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
        game_.annoncer();
        game_.getAnnonce((byte) 0).setPlayer((byte) 1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
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
        game_.annoncer();
        game_.getAnnonce((byte) 0).setDeclare(DeclaresBelote.THIRTY);
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_10);
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_9);
        game_.getAnnonce((byte) 0).getHand().ajouter(CardBelote.DIAMOND_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.getAnnoncesBeloteRebelote((byte) 1).jouer(CardBelote.SPADE_KING);
        game_.getAnnoncesBeloteRebelote((byte) 0).ajouter(CardBelote.SPADE_KING);
        CheckerGameBeloteWithRules.check(game_);
        assertTrue(!game_.getError().isEmpty());
    }

//    @Test
//    public void check20FailTest() {
//        RulesBelote rules_ = new RulesBelote();
//        DealBelote deal_ = deal2Classic((byte) 3);
//        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        BidBeloteSuit bid_;
//        bid_ = new BidBeloteSuit();
//        bid_.setBid(BidBelote.SUIT);
//        bid_.setSuit(Suit.SPADE);
//        game_.ajouterContrat(bid_);
//        game_.completerDonne();
//        game_.getPliEnCours().setEntameur(1);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
//        CheckerGameBeloteWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }
//
//    @Test
//    public void check21FailTest() {
//        RulesBelote rules_ = new RulesBelote();
//        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
//        DealBelote deal_ = deal2Classic((byte) 3);
//        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        BidBeloteSuit bid_;
//        bid_ = new BidBeloteSuit();
//        bid_.setBid(BidBelote.SUIT);
//        bid_.setSuit(Suit.SPADE);
//        game_.ajouterContrat(bid_);
//        game_.completerDonne();
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
//        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
//        game_.ajouterDixDeDerPliEnCours();
//        game_.getPliEnCours().setEntameur(2);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
//        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
//        game_.ajouterDixDeDerPliEnCours();
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
//        game_.ajouterDixDeDerPliEnCours();
//        CheckerGameBeloteWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }
//
//    @Test
//    public void check22FailTest() {
//        RulesBelote rules_ = new RulesBelote();
//        rules_.getAllowedDeclares().put(DeclaresBelote.THIRTY, BoolVal.TRUE);
//        DealBelote deal_ = deal2Classic((byte) 3);
//        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
//        int first_ = game_.playerAfter(deal_.getDealer());
//        BidBeloteSuit bid_;
//        bid_ = new BidBeloteSuit();
//        bid_.setBid(BidBelote.SUIT);
//        bid_.setSuit(Suit.SPADE);
//        game_.ajouterContrat(bid_);
//        game_.completerDonne();
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
//        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
//        game_.ajouterDixDeDerPliEnCours();
//        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
//        game_.ajouterDixDeDerPliEnCours();
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
//        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
//        game_.ajouterDixDeDerPliEnCours();
//        game_.getPliEnCours().setEntameur(2);
//        CheckerGameBeloteWithRules.check(game_);
//        assertTrue(!game_.getError().isEmpty());
//    }

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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        quickPlay(game_, 3, CardBelote.SPADE_1);
        quickPlay(game_, 0, CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterDixDeDerPliEnCours();
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
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_1);
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.setAnnoncesBeloteRebelote(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_1);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_JACK);
        game_.ajouterDixDeDerPliEnCours();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_10);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_8);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_QUEEN);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_KING);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.DIAMOND_9);
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
        assertFalse(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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
        assertTrue(game_.keepBidding());
        assertTrue(game_.noPlayedClassic());
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

    @Test
    public void check43FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        slam(game_);
        game_.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.HEART_JACK);
        CheckerGameBeloteWithRules.check(game_);
        assertFalse(game_.getError().isEmpty());
    }

    @Test
    public void check44FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        slam(game_);
        game_.validateDiscard();
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_JACK);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.SPADE_7);
        game_.ajouterUneCarteDansPliEnCoursJoue(CardBelote.HEART_9);
        CheckerGameBeloteWithRules.check(game_);
        assertFalse(game_.getError().isEmpty());
    }

    @Test
    public void check45FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.getDeal().hand().supprimer(0);
        CheckerGameBeloteWithRules.check(game_);
        assertFalse(game_.getError().isEmpty());
    }
    @Test
    public void check46FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2_24);
        DealBelote deal_ = dealThreePlayers24Classic();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.getDeal().derniereMain().getCards().swapIndexes(0,game_.getDeal().derniereMain().getCards().size()-1);
        CheckerGameBeloteWithRules.check(game_);
        assertFalse(game_.getError().isEmpty());
    }
    @Test
    public void check47FailTest() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_24);
        DealBelote deal_ = dealThreePlayers24Coinche();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        CardBelote player_ = game_.getDeal().hand().premiereCarte();
        game_.getDeal().hand().jouer(0);
        CardBelote discard_ = game_.getDeal().derniereMain().premiereCarte();
        game_.getDeal().derniereMain().jouer(0);
        game_.getDeal().hand().ajouter(discard_);
        game_.getDeal().derniereMain().ajouter(player_);
        CheckerGameBeloteWithRules.check(game_);
        assertFalse(game_.getError().isEmpty());
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

    private DealBelote dealThreePlayers() {
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
        return db_;
    }

    private DealBelote dealThreePlayersFiveCards() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10,CardBelote.HEART_1));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_9));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9,CardBelote.CLUB_8));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,
                CardBelote.CLUB_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }

    private DealBelote dealThreePlayersSec() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9));
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,CardBelote.CLUB_8,
                CardBelote.CLUB_1,CardBelote.HEART_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }

    private DealBelote dealThreePlayersSecFiveCards() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.CLUB_10,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_9));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9,CardBelote.CLUB_8));
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8,CardBelote.HEART_10,CardBelote.HEART_1));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,
                CardBelote.CLUB_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }
    private DealBelote dealThreePlayers24Classic() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 2);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.DIAMOND_9,CardBelote.SPADE_9));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK));
        db_.getDeal().add(create(CardBelote.HEART_1,
                CardBelote.CLUB_1,CardBelote.DIAMOND_10,CardBelote.CLUB_10,
                CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,
                CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_8,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8));
        return db_;
    }
    private DealBelote dealThreePlayers24Coinche() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.DIAMOND_1,CardBelote.SPADE_1,
                CardBelote.HEART_1,CardBelote.CLUB_1,CardBelote.DIAMOND_10));
        db_.getDeal().add(create(CardBelote.CLUB_9,
                CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.DIAMOND_9,CardBelote.SPADE_9));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,
                CardBelote.CLUB_10));
        db_.getDeal().add(create(
                CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_8,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8));
        return db_;
    }
    private DealBelote dealTwoPlayers() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.HEART_10,CardBelote.DIAMOND_1,CardBelote.SPADE_1,CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,
                CardBelote.DIAMOND_9,CardBelote.SPADE_10));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_9,CardBelote.SPADE_QUEEN,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.CLUB_9,
                CardBelote.SPADE_KING,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK));
        db_.getDeal().add(create(CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.HEART_1,
                CardBelote.CLUB_1,CardBelote.DIAMOND_10,CardBelote.CLUB_10,
                CardBelote.CLUB_7,CardBelote.CLUB_8,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_7,CardBelote.DIAMOND_8));
        return db_;
    }
    private DealBelote deal(RulesBelote _rules) {
        DealBelote deal_ = new DealBelote(0);
        deal_.setDealer((byte) 0);
        deal_.initDonne(_rules, DefaultGenerator.oneElt(), HandBelote.pileBase());
        return deal_;
    }

    private void quick(GameBelote _game) {
        TrickBelote cur_ = _game.getProgressingTrick();
        _game.getTricks().add(cur_);
        _game.setProgressingTrick(new TrickBelote(cur_.getRamasseur(_game.getBid())));
    }

    private void quickPlay(GameBelote _game, int _x, CardBelote _card) {
        _game.getDeal().hand((byte) _x).jouer(_card);
        _game.getProgressingTrick().ajouter(_card);
    }

    private void slam(GameBelote _game) {
        _game.ajouterCartesUtilisateur();
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_9);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_8);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_8);
        _game.ajouterChelemUtilisateur();
    }


    private void slamFiveCards(GameBelote _game) {
        _game.ajouterCartesUtilisateur();
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_KING);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.SPADE_QUEEN);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_KING);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.CLUB_QUEEN);
        _game.ajouterUneCarteDansPliEnCoursPreneur(CardBelote.DIAMOND_7);
        _game.ajouterChelemUtilisateur();
    }


    private static BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
    }
}
