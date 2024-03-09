package cards.belote;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import code.maths.montecarlo.DefaultGenerator;
import code.util.Bytes;
import code.util.core.BoolVal;
import org.junit.Test;

public final class GameBeloteSimulateTest extends EquallableBeloteUtil {
    @Test
    public void bidSimulate1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0);
        deal_.setDealer((byte) 0);
        DisplayingBelote display_ = new DisplayingBelote();
        deal_.initDonne(rules_, DefaultGenerator.oneElt(), HandBelote.pileBase());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        assertEq(5, game_.mainUtilisateurTriee(display_).total());
        game_.bidSimulate(s_);
        assertEq(1,game_.getBids().size());
    }
    @Test
    public void bidSimulate2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(deal_.derniereMain().premiereCarte().getId().getCouleur());
        b_.setBid(BidBelote.SUIT);
        game_.setBid(b_);
        game_.bidSimulate(s_);
        assertEq(0,game_.getBids().size());
    }

    @Test
    public void bidRoundSimulate1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        Bytes players_ = game_.getDeal().orderedPlayersBegin(game_.getRegles());
        assertTrue(game_.bidRoundSimulate(players_,s_));
    }
    @Test
    public void bidRoundSimulate2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbruptBid s_ = new SimulatingBeloteAbruptBid();
        Bytes players_ = game_.getDeal().orderedPlayersBegin(game_.getRegles());
        assertTrue(!game_.bidRoundSimulate(players_,s_));
    }
    @Test
    public void secRoundSimulate1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbruptBid s_ = new SimulatingBeloteAbruptBid();
        Bytes players_ = game_.getDeal().orderedPlayersBegin(game_.getRegles());
        boolean en_ = game_.bidRoundSimulate(players_, s_);
        assertTrue(!game_.secRoundSimulate(en_,players_,s_));
    }
    @Test
    public void secRoundSimulate2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        Bytes players_ = game_.getDeal().orderedPlayersBegin(game_.getRegles());
        boolean en_ = game_.bidRoundSimulate(players_, s_);
        assertTrue(game_.secRoundSimulate(en_,players_,s_));
    }
    @Test
    public void secRoundSimulate3Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(deal_.derniereMain().premiereCarte().getId().getCouleur());
        bid(game_,bid_);
        Bytes players_ = game_.getDeal().orderedPlayersBegin(game_.getRegles());
        boolean en_ = game_.bidRoundSimulate(players_, s_);
        assertTrue(game_.secRoundSimulate(en_,players_,s_));
    }
    @Test
    public void simuPlayCards1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.HEART);
        bid_.setPoints(80);
        bid(game_,bid_);
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(deal_.derniereMain().premiereCarte().getId().getCouleur());
        bid(game_,bid_);
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards3Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbrupt s_ = new SimulatingBeloteAbrupt();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.HEART);
        bid_.setPoints(80);
        bid(game_,bid_);
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        game_.simuPlayCards(s_);
        assertTrue(!game_.isEnded());
    }
    @Test
    public void simuPlayCards4Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbrupt s_ = new SimulatingBeloteAbrupt();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(deal_.derniereMain().premiereCarte().getId().getCouleur());
        bid(game_,bid_);
        game_.simuPlayCards(s_);
        assertTrue(!game_.isEnded());
    }
    @Test
    public void simuPlayCards5Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbrupt s_ = new SimulatingBeloteAbrupt();
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards6Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbrupt s_ = new SimulatingBeloteAbrupt();
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
//        game_.finEncherePremierTour();
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        bid(game_,new BidBeloteSuit());
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards7Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        bid(game_,bid_);
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards8Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.ALL_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        bid(game_,bid_);
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards9Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.HEART);
        bid_.setPoints(162);
        bid(game_,bid_);
        game_.simuPlayCards(s_);
        assertEq(0,game_.mainUtilisateurTriee(s_.getDisplaying()).total());
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuler1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbruptBid s_ = new SimulatingBeloteAbruptBid();
        game_.simuler(s_);
        assertTrue(!game_.isEnded());
    }
    @Test
    public void simuler2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbruptBid s_ = new SimulatingBeloteAbruptBid();
        game_.simuler(s_);
        assertTrue(!game_.isEnded());
    }
    @Test
    public void simuler3Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuler4Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = deal(rules_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }

    @Test
    public void simuler5Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }

    @Test
    public void simuler6Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2);
        DealBelote deal_ = dealThreePlayers();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }

    @Test
    public void simuler7Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }

    @Test
    public void simuler8Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_5);
        DealBelote deal_ = dealThreePlayersFiveCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }

    @Test
    public void simuler9Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.CLASSIC_1_VS_2_2);
        DealBelote deal_ = dealThreePlayersTwoCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,0,BidBelote.OTHER_SUIT));
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }

    @Test
    public void simuler10Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.setDealing(DealingBelote.COINCHE_1_VS_2_2);
        DealBelote deal_ = dealThreePlayersTwoCards();
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.HEART,80,BidBelote.SUIT));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        game_.ajouterContrat(bidSuit(Suit.UNDEFINED,0,BidBelote.FOLD));
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }
    private DealBelote deal(RulesBelote _rules) {
        DealBelote deal_ = new DealBelote(0);
        deal_.setDealer((byte) 0);
        deal_.initDonne(_rules, DefaultGenerator.oneElt(), HandBelote.pileBase());
        return deal_;
    }

    private static BidBeloteSuit bidSuit(Suit _suit, int _pts, BidBelote _bid) {
        BidBeloteSuit suit_ = new BidBeloteSuit();
        suit_.setSuit(_suit);
        suit_.setPoints(_pts);
        suit_.setBid(_bid);
        return suit_;
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
                CardBelote.DIAMOND_10,CardBelote.CLUB_10,CardBelote.DIAMOND_8,CardBelote.HEART_10,CardBelote.HEART_1));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.SPADE_10,
                CardBelote.DIAMOND_7,CardBelote.SPADE_9,CardBelote.CLUB_7,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_9));
        db_.getDeal().add(create(CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9,CardBelote.CLUB_8));
        db_.getDeal().add(create(CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,
                CardBelote.CLUB_1,CardBelote.DIAMOND_1,CardBelote.SPADE_1));
        return db_;
    }

    private DealBelote dealThreePlayersTwoCards() {
        DealBelote db_ = new DealBelote();
        db_.setDealer((byte) 1);
        db_.getDeal().add(create(CardBelote.HEART_JACK,CardBelote.HEART_9,CardBelote.SPADE_KING,CardBelote.SPADE_QUEEN,
                CardBelote.DIAMOND_10,CardBelote.CLUB_10,CardBelote.SPADE_10,CardBelote.HEART_10,CardBelote.HEART_1,CardBelote.DIAMOND_1));
        db_.getDeal().add(create(CardBelote.HEART_KING,CardBelote.HEART_QUEEN,CardBelote.DIAMOND_8,
                CardBelote.DIAMOND_7,CardBelote.CLUB_KING,CardBelote.CLUB_QUEEN,CardBelote.HEART_8,CardBelote.HEART_7,CardBelote.CLUB_9,CardBelote.CLUB_7));
        db_.getDeal().add(create(CardBelote.SPADE_9,CardBelote.DIAMOND_KING,CardBelote.DIAMOND_QUEEN,CardBelote.DIAMOND_JACK,CardBelote.SPADE_JACK,
                CardBelote.CLUB_JACK,CardBelote.SPADE_8,CardBelote.SPADE_7,CardBelote.DIAMOND_9,CardBelote.CLUB_8));
        db_.getDeal().add(create(CardBelote.CLUB_1,CardBelote.SPADE_1));
        return db_;
    }
}
