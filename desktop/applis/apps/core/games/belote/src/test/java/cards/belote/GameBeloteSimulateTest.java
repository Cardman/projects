package cards.belote;

import cards.belote.enumerations.BidBelote;
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
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        DisplayingBelote display_ = new DisplayingBelote();
        deal_.initDonne(rules_, display_, DefaultGenerator.oneElt());
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        assertEq(5, game_.mainUtilisateurTriee(display_).total());
        game_.bidSimulate(game_.playerHavingToBid(),s_);
        assertEq(1,game_.getBids().size());
    }
    @Test
    public void bidSimulate2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit b_ = new BidBeloteSuit();
        b_.setSuit(deal_.derniereMain().premiereCarte().getId().getCouleur());
        b_.setBid(BidBelote.SUIT);
        game_.setBid(b_);
        game_.bidSimulate(game_.playerHavingToBid(),s_);
        assertEq(0,game_.getBids().size());
    }

    private static void initDonneLoc(RulesBelote _rules, DealBelote _deal) {
        _deal.initDonne(_rules, new DisplayingBelote(), DefaultGenerator.oneElt());
    }

    @Test
    public void bidRoundSimulate1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        Bytes players_ = game_.orderedPlayers(game_.playerAfter(game_.getDistribution().getDealer()));
        assertTrue(game_.bidRoundSimulate(players_,s_));
    }
    @Test
    public void bidRoundSimulate2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbruptBid s_ = new SimulatingBeloteAbruptBid(game_);
        Bytes players_ = game_.orderedPlayers(game_.playerAfter(game_.getDistribution().getDealer()));
        assertTrue(!game_.bidRoundSimulate(players_,s_));
    }
    @Test
    public void secRoundSimulate1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbruptBid s_ = new SimulatingBeloteAbruptBid(game_);
        Bytes players_ = game_.orderedPlayers(game_.playerAfter(game_.getDistribution().getDealer()));
        boolean en_ = game_.bidRoundSimulate(players_, s_);
        assertTrue(!game_.secRoundSimulate(en_,players_,s_));
    }
    @Test
    public void secRoundSimulate2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        Bytes players_ = game_.orderedPlayers(game_.playerAfter(game_.getDistribution().getDealer()));
        boolean en_ = game_.bidRoundSimulate(players_, s_);
        assertTrue(game_.secRoundSimulate(en_,players_,s_));
    }
    @Test
    public void secRoundSimulate3Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(deal_.derniereMain().premiereCarte().getId().getCouleur());
        game_.ajouterContrat(bid_,game_.playerHavingToBid());
        Bytes players_ = game_.orderedPlayers(game_.playerAfter(game_.getDistribution().getDealer()));
        boolean en_ = game_.bidRoundSimulate(players_, s_);
        assertTrue(game_.secRoundSimulate(en_,players_,s_));
    }
    @Test
    public void simuPlayCards1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.HEART);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_,game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(deal_.derniereMain().premiereCarte().getId().getCouleur());
        game_.ajouterContrat(bid_,game_.playerHavingToBid());
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards3Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbrupt s_ = new SimulatingBeloteAbrupt(game_);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(Suit.HEART);
        bid_.setPoints(80);
        game_.ajouterContrat(bid_,game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.simuPlayCards(s_);
        assertTrue(!game_.isEnded());
    }
    @Test
    public void simuPlayCards4Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbrupt s_ = new SimulatingBeloteAbrupt(game_);
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.SUIT);
        bid_.setSuit(deal_.derniereMain().premiereCarte().getId().getCouleur());
        game_.ajouterContrat(bid_,game_.playerHavingToBid());
        game_.simuPlayCards(s_);
        assertTrue(!game_.isEnded());
    }
    @Test
    public void simuPlayCards5Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbrupt s_ = new SimulatingBeloteAbrupt(game_);
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards6Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbrupt s_ = new SimulatingBeloteAbrupt(game_);
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.finEncherePremierTour();
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.ajouterContrat(new BidBeloteSuit(),game_.playerHavingToBid());
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards7Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.getAllowedBids().put(BidBelote.NO_TRUMP, BoolVal.TRUE);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.NO_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_,game_.playerHavingToBid());
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards8Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.getAllowedBids().put(BidBelote.ALL_TRUMP,BoolVal.TRUE);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        BidBeloteSuit bid_ = new BidBeloteSuit();
        bid_.setBid(BidBelote.ALL_TRUMP);
        bid_.setSuit(Suit.UNDEFINED);
        game_.ajouterContrat(bid_,game_.playerHavingToBid());
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuler1Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbruptBid s_ = new SimulatingBeloteAbruptBid(game_);
        game_.simuler(s_);
        assertTrue(!game_.isEnded());
    }
    @Test
    public void simuler2Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        rules_.setDealing(DealingBelote.COINCHE_2_VS_2);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteAbruptBid s_ = new SimulatingBeloteAbruptBid(game_);
        game_.simuler(s_);
        assertTrue(!game_.isEnded());
    }
    @Test
    public void simuler3Test() {
        RulesBelote rules_ = new RulesBelote();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
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
        DealBelote deal_ = new DealBelote(0, HandBelote.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameBelote game_ = new GameBelote(GameType.RANDOM, deal_, rules_);
        SimulatingBeloteNormal s_ = new SimulatingBeloteNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }
}
