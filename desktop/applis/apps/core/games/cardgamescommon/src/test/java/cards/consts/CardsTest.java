package cards.consts;

import code.maths.Rate;
import code.util.*;
import org.junit.Test;

public class CardsTest extends EquallableCardsUtil {
    @Test
    public void test() {
        ResultsGame r_ = new ResultsGame();
        r_.setScores(new CustList<Longs>());
        assertNotNull(r_.getScores());
        r_.setSigmas(new CustList<Rate>());
        assertNotNull(r_.getSigmas());
        r_.setSums(new Longs());
        assertNotNull(r_.getSums());
        r_.setDetailResultsTitle(CoreResourcesAccess.CHARS);
        assertNotNull(r_.getDetailResultsTitle());
        r_.setGlobalResultsPageTitle(CoreResourcesAccess.MIX);
        assertNotNull(r_.getGlobalResultsPageTitle());
        r_.setRenderedPages(new StringMap<String>());
        r_.setGeneral("");
        assertNotNull(r_.getGeneral());
        r_.setSpecific("");
        assertNotNull(r_.getSpecific());
        assertNotNull(r_.getRenderedPages());
        assertNotNull(Role.values());
        assertNotNull(PossibleTrickWinner.values());
        assertNotNull(Order.values());
        assertNotNull(MixCardsChoice.values());
        assertNotNull(Hypothesis.values());
        assertNotNull(EndGameState.values());
        assertNotNull(CardChar.values());
        assertNotNull(GameType.values());
    }
    @Test
    public void eqSuitTest() {
        assertEq(4, Suit.couleursOrdinaires().size());
        EnumList<Suit> one_ = new EnumList<Suit>();
        EnumList<Suit> two_ = new EnumList<Suit>();
        assertTrue(Suit.equalsSuits(one_,two_));
        one_ = new EnumList<Suit>();
        two_ = new EnumList<Suit>();
        one_.add(Suit.HEART);
        two_.add(Suit.SPADE);
        assertTrue(!Suit.equalsSuits(one_,two_));
        one_ = new EnumList<Suit>();
        two_ = new EnumList<Suit>();
        one_.add(Suit.HEART);
        assertTrue(!Suit.equalsSuits(one_,two_));
        one_ = new EnumList<Suit>();
        two_ = new EnumList<Suit>();
        one_.add(Suit.HEART);
        two_.add(Suit.HEART);
        assertTrue(Suit.equalsSuits(one_,two_));
    }
}
