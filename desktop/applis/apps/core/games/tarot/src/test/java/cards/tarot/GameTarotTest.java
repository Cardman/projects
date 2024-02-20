package cards.tarot;

import cards.consts.GameType;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.util.CustList;
import code.util.IdList;
import code.util.core.BoolVal;
import org.junit.Test;

public final class GameTarotTest extends EquallableTarotUtil {
    @Test
    public void test() {
        GameTarot g_ = new GameTarot();
        g_.setNumber(5);
        g_.setNombre();
        DealTarot d_ = new DealTarot();
        HandTarot h_ = new HandTarot();
        h_.setCards(new IdList<CardTarot>());
        d_.getDeal().add(h_);
        RulesTarot rules_ = new RulesTarot();
        d_.donneurSuivant((byte) 3, rules_);
        d_.setNbDeals(1);
        g_.setDeal(d_);
//        g_.setDeclaresSlam(new CustList<BoolVal>());
        g_.setSmallBound(new CustList<BoolVal>());
//        g_.setConfidence(new CustList<CustList<BoolVal>>());
        g_.setRules(rules_);
        g_.setType(GameType.RANDOM);
        assertTrue(!h_.validStack());
        assertEq(1,d_.getNbDeals());
        assertEq(4,d_.getDealer());
        assertEq(6,g_.getNumber());
//        assertEq(0,g_.getDeclaresSlam().size());
        assertEq(0,g_.getLastBid().getForce());
        h_ = HandTarot.pileBase();
        assertTrue(h_.validStack());
        g_ = new GameTarot(GameType.RANDOM,d_,rules_);
        g_.firstLeadIfPossible();
        assertEq(BidTarot.GUARD,new DefGameTarot().strategieContratUser(BidTarot.GUARD));
        assertEq(BidTarot.SLAM,new DefGameTarot().currentBid());
        assertEq(0,new DefGameTarot().strategieAppelUser(new HandTarot()).total());
        assertEq(0,new DefGameTarot().handfulCard(new HandTarot()).total());
        assertEq(0,new DefGameTarot().currentCall().total());
        assertEq(0,new DefGameTarot().currentHandfulCard().total());
        assertEq(CardTarot.WHITE,new DefGameTarot().discard(CardTarot.WHITE));
        assertEq(CardTarot.WHITE,new DefGameTarot().restore(CardTarot.WHITE));
        assertEq(CardTarot.WHITE,new DefGameTarot().changerConfianceJeuCarteUniqueUser(CardTarot.WHITE));
        assertEq(CardTarot.WHITE,new DefGameTarot().currentDiscard());
        assertEq(CardTarot.WHITE,new DefGameTarot().currentCard());
        assertEq(0,new DefGameTarot().handful(new IdList<Handfuls>()).size());
        assertEq(0,new DefGameTarot().currentHandful().size());
        assertEq(0,new DefGameTarot().misere(new IdList<Miseres>()).size());
        assertEq(0,new DefGameTarot().currentMiseres().size());
    }
    @Test
    public void firstRound1() {
        DealTarot d_ = new DealTarot();
        HandTarot h_ = new HandTarot();
        h_.setCards(new IdList<CardTarot>());
        d_.getDeal().add(h_);
        RulesTarot rules_ = new RulesTarot();
        d_.donneurSuivant((byte) 3, rules_);
        d_.setNbDeals(1);
        GameTarot g_ = new GameTarot(GameType.RANDOM, d_, rules_);
        g_.getTricks().add(new TrickTarot((byte) 0,false));
        g_.firstLeadIfPossible();
        assertEq(0,g_.getEntameur());
    }
    @Test
    public void firstRound2() {
        DealTarot d_ = new DealTarot();
        HandTarot h_ = new HandTarot();
        h_.setCards(new IdList<CardTarot>());
        d_.getDeal().add(h_);
        RulesTarot rules_ = new RulesTarot();
        d_.donneurSuivant((byte) 3, rules_);
        d_.setNbDeals(1);
        GameTarot g_ = new GameTarot(GameType.RANDOM, d_, rules_);
        g_.getTricks().add(new TrickTarot((byte) 0,false));
        g_.firstLeadIfPossible();
        g_.getProgressingTrick().setStarter((byte) 0);
        g_.getProgressingTrick().ajouter(CardTarot.TRUMP_1);
        g_.getProgressingTrick().ajouter(CardTarot.TRUMP_10);
        g_.getProgressingTrick().ajouter(CardTarot.TRUMP_21);
        g_.getProgressingTrick().ajouter(CardTarot.TRUMP_5);
        g_.getProgressingTrick().ajouter(CardTarot.TRUMP_4);
        g_.ajouterPetitAuBoutPliEnCours();
        g_.setEntameur(g_.getRamasseur());
        g_.firstLeadIfPossible();
        assertEq(2,g_.getEntameur());
    }
}
