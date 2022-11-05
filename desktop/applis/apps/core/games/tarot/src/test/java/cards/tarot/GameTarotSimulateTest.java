package cards.tarot;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.ModeTarot;
import code.maths.montecarlo.DefaultGenerator;
import code.util.CustList;
import org.junit.Test;

public final class GameTarotSimulateTest extends EquallableTarotUtil {
    @Test
    public void bidSimulate1Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        game_.bidSimulate(game_.playerAfter(game_.getDistribution().getDealer()),s_);
        assertEq(1,game_.getBids().size());
    }

    private static void initDonneLoc(RulesTarot _rules, DealTarot _deal) {
        _deal.initDonne(_rules,DefaultGenerator.oneElt());
    }

    @Test
    public void bidSimulate2Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.getCommon().setMixedCards(MixCardsChoice.NEVER);
        DealTarot deal_ = new DealTarot(0, HandTarot.pileBase());
        deal_.setDealer((byte) 0);
        initDonneLoc(rules_, deal_);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        game_.setContrat(game_.allowedBids().last());
        game_.bidSimulate(game_.playerAfter(game_.getDistribution().getDealer()),s_);
        assertEq(0,game_.getBids().size());
    }

    @Test
    public void simuCallDiscard1Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(1,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard2Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(1,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard3Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal2((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(0,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard4Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal3((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(0,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard5Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(1,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard6Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(1,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard7Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal2((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(0,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard8Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal3((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(0,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard9Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(1,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard10Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(1,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard11Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal2((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(0,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard12Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal3((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(0,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard13Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(1,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard14Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_CHAR);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(1,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard15Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setDealing(DealingTarot.DEAL_2_VS_4_WITHOUT_CALL);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal2((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(0,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard16Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal3((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD_WITHOUT, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(0,game_.getCalledCards().total());
    }

    @Test
    public void simuCallDiscard17Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDiscardAfterCall(false);
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal3((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuCallDiscard(game_.getContrat(),s_);
        assertEq(0,game_.getCalledCards().total());
    }
    @Test
    public void simuStarter1Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal3((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartes(game_.getPreneur(),game_.derniereMain());
        //On ajoute les cartes du chien au preneur pour en ecarter d'autres
        HandTarot mt_=game_.strategieEcart();
        //Le preneur ecarte les cartes qu'il veut
        game_.supprimerCartes(game_.getPreneur(),mt_);
        game_.ajouterChelem(game_.getPreneur(), true);

        game_.setEntameur(game_.getPreneur());
        game_.setPliEnCours(false);
        game_.ajouterCartesDansPliEnCours(mt_);
        game_.getTricks().add(game_.getProgressingTrick());
        game_.setEntameur(game_.getPreneur());

        game_.simuStarter();
        assertEq(2,game_.getEntameur());
    }
    @Test
    public void simuStarter2Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_1_VS_2);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal3((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.ajouterCartes(game_.getPreneur(),game_.derniereMain());
        //On ajoute les cartes du chien au preneur pour en ecarter d'autres
        HandTarot mt_=game_.strategieEcart();
        //Le preneur ecarte les cartes qu'il veut
        game_.supprimerCartes(game_.getPreneur(),mt_);
        game_.ajouterChelem(game_.getPreneur(), false);

        game_.setEntameur(game_.getPreneur());
        game_.setPliEnCours(false);
        game_.ajouterCartesDansPliEnCours(mt_);
        game_.getTricks().add(game_.getProgressingTrick());

        game_.simuStarter();
        assertEq(1,game_.getEntameur());
    }
    @Test
    public void simuPlayCards1Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards2Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotAbrupt s_ = new SimulatingTarotAbrupt(game_);
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.GUARD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuPlayCards(s_);
        assertTrue(!game_.isEnded());
    }
    @Test
    public void simuPlayCards3Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuPlayCards4Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        int first_ = game_.playerAfter(deal_.getDealer());
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        first_ = game_.playerAfter((byte) first_);
        game_.ajouterContrat(BidTarot.FOLD, (byte) first_);
        game_.simuPlayCards(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuler1Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.ONE_FOR_ONE);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuler2Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotNormal s_ = new SimulatingTarotNormal();
        game_.simuler(s_);
        assertTrue(game_.isEnded());
    }
    @Test
    public void simuler3Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotAbruptBid s_ = new SimulatingTarotAbruptBid(game_);
        game_.simuler(s_);
        assertTrue(!game_.isEnded());
        assertTrue(game_.getTricks().isEmpty());
    }
    @Test
    public void simuler4Test() {
        RulesTarot rules_ = new RulesTarot();
        rules_.setDealing(DealingTarot.DEAL_2_VS_3_CALL_KING);
        rules_.setMode(ModeTarot.NORMAL_WITH_ONE_FOR_ONE);
        DealTarot deal_ = deal1((byte) 0);
        GameTarot game_ = new GameTarot(GameType.RANDOM, deal_, rules_);
        SimulatingTarotAbrupt s_ = new SimulatingTarotAbrupt(game_);
        game_.simuler(s_);
        assertTrue(!game_.isEnded());
        assertTrue(!game_.getTricks().isEmpty());
    }
    private static DealTarot deal1(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_5);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }

    private static DealTarot deal2(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_5);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }

    private static DealTarot deal3(byte _dealer) {
        CustList<HandTarot> hands_ = new CustList<HandTarot>();
        HandTarot hand_;
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_1);
        hand_.ajouter(CardTarot.TRUMP_4);
        hand_.ajouter(CardTarot.TRUMP_10);
        hand_.ajouter(CardTarot.TRUMP_14);
        hand_.ajouter(CardTarot.TRUMP_17);
        hand_.ajouter(CardTarot.TRUMP_21);
        hand_.ajouter(CardTarot.HEART_KING);
        hand_.ajouter(CardTarot.HEART_7);
        hand_.ajouter(CardTarot.HEART_1);
        hand_.ajouter(CardTarot.SPADE_QUEEN);
        hand_.ajouter(CardTarot.SPADE_10);
        hand_.ajouter(CardTarot.DIAMOND_2);
        hand_.ajouter(CardTarot.TRUMP_3);
        hand_.ajouter(CardTarot.TRUMP_6);
        hand_.ajouter(CardTarot.TRUMP_13);
        hand_.ajouter(CardTarot.HEART_8);
        hand_.ajouter(CardTarot.HEART_4);
        hand_.ajouter(CardTarot.SPADE_KING);
        hand_.ajouter(CardTarot.SPADE_KNIGHT);
        hand_.ajouter(CardTarot.SPADE_JACK);
        hand_.ajouter(CardTarot.CLUB_10);
        hand_.ajouter(CardTarot.CLUB_4);
        hand_.ajouter(CardTarot.DIAMOND_7);
        hand_.ajouter(CardTarot.DIAMOND_6);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_2);
        hand_.ajouter(CardTarot.TRUMP_8);
        hand_.ajouter(CardTarot.TRUMP_12);
        hand_.ajouter(CardTarot.HEART_3);
        hand_.ajouter(CardTarot.HEART_2);
        hand_.ajouter(CardTarot.SPADE_5);
        hand_.ajouter(CardTarot.SPADE_2);
        hand_.ajouter(CardTarot.SPADE_1);
        hand_.ajouter(CardTarot.CLUB_9);
        hand_.ajouter(CardTarot.CLUB_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_KNIGHT);
        hand_.ajouter(CardTarot.DIAMOND_1);
        hand_.ajouter(CardTarot.TRUMP_5);
        hand_.ajouter(CardTarot.TRUMP_9);
        hand_.ajouter(CardTarot.TRUMP_15);
        hand_.ajouter(CardTarot.HEART_KNIGHT);
        hand_.ajouter(CardTarot.HEART_10);
        hand_.ajouter(CardTarot.SPADE_8);
        hand_.ajouter(CardTarot.SPADE_3);
        hand_.ajouter(CardTarot.CLUB_JACK);
        hand_.ajouter(CardTarot.CLUB_8);
        hand_.ajouter(CardTarot.DIAMOND_5);
        hand_.ajouter(CardTarot.DIAMOND_9);
        hand_.ajouter(CardTarot.DIAMOND_3);
        hands_.add(hand_);
        //full_.supprimerCartes(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_11);
        hand_.ajouter(CardTarot.TRUMP_18);
        hand_.ajouter(CardTarot.HEART_6);
        hand_.ajouter(CardTarot.HEART_5);
        hand_.ajouter(CardTarot.SPADE_9);
        hand_.ajouter(CardTarot.SPADE_7);
        hand_.ajouter(CardTarot.SPADE_6);
        hand_.ajouter(CardTarot.DIAMOND_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_10);
        hand_.ajouter(CardTarot.CLUB_KING);
        hand_.ajouter(CardTarot.CLUB_2);
        hand_.ajouter(CardTarot.CLUB_1);
        hand_.ajouter(CardTarot.TRUMP_20);
        hand_.ajouter(CardTarot.TRUMP_16);
        hand_.ajouter(CardTarot.HEART_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_KING);
        hand_.ajouter(CardTarot.CLUB_3);
        hand_.ajouter(CardTarot.CLUB_QUEEN);
        hand_.ajouter(CardTarot.DIAMOND_8);
        hand_.ajouter(CardTarot.DIAMOND_4);
        hand_.ajouter(CardTarot.EXCUSE);
        hand_.ajouter(CardTarot.HEART_9);
        hand_.ajouter(CardTarot.CLUB_7);
        hand_.ajouter(CardTarot.CLUB_5);
        hands_.add(hand_);
        hand_ = new HandTarot();
        hand_.ajouter(CardTarot.TRUMP_19);
        hand_.ajouter(CardTarot.TRUMP_7);
        hand_.ajouter(CardTarot.HEART_JACK);
        hand_.ajouter(CardTarot.SPADE_4);
        hand_.ajouter(CardTarot.CLUB_6);
        hand_.ajouter(CardTarot.DIAMOND_JACK);
        hands_.add(hand_);
        return new DealTarot(hands_,_dealer);
    }
}
