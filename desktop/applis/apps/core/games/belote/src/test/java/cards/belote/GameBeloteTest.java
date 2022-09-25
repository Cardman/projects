package cards.belote;

import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import code.maths.montecarlo.DefaultGenerator;
import code.util.BooleanList;
import code.util.Bytes;
import code.util.CustList;
import code.util.IdList;
import code.util.core.BoolVal;
import org.junit.Test;

public final class GameBeloteTest extends CommonGameBelote {
    @Test
    public void test() {
        GameBelote g_ = new GameBelote();
        g_.setNumber(5);
        g_.setNombre();
        g_.setRules(new RulesBelote());
        g_.setType(GameType.RANDOM);
        DealBelote d_ = new DealBelote();
        d_.donneurSuivant((byte) 2,4);
        d_.setNbDeals(1);
        HandBelote h_ = new HandBelote();
        assertTrue(!h_.validStack());
        IdList<CardBelote> c_ = new IdList<CardBelote>();
        c_.add(CardBelote.WHITE);
        h_.setCards(c_);
        assertEq(1,d_.getNbDeals());
        assertEq(3,d_.getDealer());
        assertEq(6,g_.getNumber());
        d_.getDeal().add(h_);
        g_.setDeal(d_);
        g_.setDeclares(new CustList<DeclareHandBelote>());
        g_.setDeclaresBeloteRebelote(new CustList<HandBelote>());
        g_.setWonLastTrick(new CustList<BoolVal>(BoolVal.TRUE,BoolVal.FALSE));
        g_.jouer(CardBelote.WHITE);
        h_ = HandBelote.pileBase();
        assertTrue(h_.validStack());
        assertTrue(g_.getDixDeDer((byte) 0));
        assertFalse(g_.getDixDeDer((byte) 1));
        assertEq(0, g_.tousContrats().size());
        assertEq(0, g_.getLastBid().getPoints());
        assertEq(0,g_.getDeal().hand().total());
        g_.isSameTeam(new Bytes());
        g_.playersBelongingToSameTeam();
        d_.setRandomDealer(4, new DefaultGenerator());
    }
}
