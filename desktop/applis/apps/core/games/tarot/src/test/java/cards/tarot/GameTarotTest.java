package cards.tarot;

import cards.consts.GameType;
import cards.tarot.enumerations.CardTarot;
import code.util.BooleanList;
import code.util.CustList;
import code.util.EnumList;
import org.junit.Test;

public final class GameTarotTest extends EquallableTarotUtil {
    @Test
    public void test() {
        GameTarot g_ = new GameTarot();
        g_.setNumber(5);
        g_.setNombre();
        DealTarot d_ = new DealTarot();
        HandTarot h_ = new HandTarot();
        h_.setCards(new EnumList<CardTarot>());
        d_.getDeal().add(h_);
        RulesTarot rules_ = new RulesTarot();
        d_.donneurSuivant((byte) 3, rules_);
        d_.setNbDeals(1);
        g_.setDeal(d_);
        g_.setDeclaresSlam(new CustList<Boolean>());
        g_.setSmallBound(new CustList<Boolean>());
        g_.setConfidence(new CustList<CustList<Boolean>>());
        g_.setRules(rules_);
        g_.setType(GameType.RANDOM);
        assertTrue(!h_.validStack());
        assertEq(1,d_.getNbDeals());
        assertEq(4,d_.getDealer());
        assertEq(6,g_.getNumber());
        assertEq(0,g_.getDeclaresSlam().size());
        assertEq(0,g_.getLastBid().getForce());
        h_ = HandTarot.pileBase();
        assertTrue(h_.validStack());
    }
}
