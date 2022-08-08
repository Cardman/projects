package cards.consts;

import code.maths.Rate;
import code.util.*;
import org.junit.Test;

public final class CardsTest extends EquallableCardsUtil {
    @Test
    public void test() {
        ResultsGame r_ = new ResultsGame();
        r_.setScores(new CustList<Longs>());
        r_.setSigmas(new CustList<Rate>());
        r_.setSums(new Longs());
        r_.setDetailResultsTitle(CoreResourcesAccess.CHARS);
        assertNotNull(r_.getDetailResultsTitle());
        r_.setGlobalResultsPageTitle(CoreResourcesAccess.MIX);
        assertNotNull(r_.getGlobalResultsPageTitle());
        r_.setRenderedPages(new StringMap<String>());
        r_.setGeneral("");
        assertNotNull(r_.getGeneral());
        r_.setSpecific("");
        assertNotNull(r_.getSpecific());
        r_.setLoc("");
        assertNotNull(r_.getLoc());
        r_.setUser((byte) 0);
        assertEq(0, r_.getUser());
        r_.setNicknames(new StringList());
        assertEq(0, r_.getNicknames().size());
        assertNotNull(r_.getRenderedPages());
        assertNotNull(Role.values());
        assertNotNull(PossibleTrickWinner.values());
        assertNotNull(Order.values());
        assertNotNull(MixCardsChoice.values());
        assertNotNull(Hypothesis.values());
        assertNotNull(EndGameState.values());
        assertNotNull(CardChar.values());
    }
    @Test
    public void eqSuitTest() {
        assertEq(4, Suit.couleursOrdinaires().size());
        assertEq(5, Suit.couleursDefinies().size());
        assertEq(6, Suit.toutesCouleurs().size());
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
    @Test
    public void hasToCalculateScores1Test() {
        assertTrue(ResultsGame.hasToCalculateScores(GameType.EDIT,0,0));
    }
    @Test
    public void hasToCalculateScores2Test() {
        assertTrue(!ResultsGame.hasToCalculateScores(GameType.EDIT,1,0));
    }
    @Test
    public void hasToCalculateScores3Test() {
        assertTrue(ResultsGame.hasToCalculateScores(GameType.RANDOM,0,0));
    }
    @Test
    public void hasToCalculateScores4Test() {
        assertTrue(!ResultsGame.hasToCalculateScores(GameType.RANDOM,1,0));
    }

    @Test
    public void calculateScores1() {
        ResultsGame r_ = new ResultsGame();
        r_.calculateScores(Shorts.newList((short)1,(short) 2));
        assertEq(1, r_.getScores().size());
        assertEq(2, r_.getScores().get(0).size());
        assertEq(1, r_.getScores().get(0).get(0));
        assertEq(2, r_.getScores().get(0).get(1));
        assertEq(1, r_.getSums().size());
        assertEq(3, r_.getSums().get(0));
        assertEq(1, r_.getSigmas().size());
        assertEq(new Rate(3,2), r_.getSigmas().get(0));
    }
    @Test
    public void calculateScores2() {
        ResultsGame r_ = new ResultsGame();
        r_.calculateScores(Shorts.newList((short)1,(short) 2));
        r_.calculateScores(Shorts.newList((short)2,(short) 1));
        assertEq(2, r_.getScores().size());
        assertEq(2, r_.getScores().get(0).size());
        assertEq(1, r_.getScores().get(0).get(0));
        assertEq(2, r_.getScores().get(0).get(1));
        assertEq(2, r_.getScores().get(1).size());
        assertEq(3, r_.getScores().get(1).get(0));
        assertEq(3, r_.getScores().get(1).get(1));
        assertEq(2, r_.getSums().size());
        assertEq(3, r_.getSums().get(0));
        assertEq(6, r_.getSums().get(1));
        assertEq(2, r_.getSigmas().size());
        assertEq(new Rate(3,2), r_.getSigmas().get(0));
        assertEq(Rate.zero(), r_.getSigmas().get(1));
    }
    @Test
    public void calculateScores3() {
        ResultsGame r_ = new ResultsGame();
        r_.calculateScores(Shorts.newList((short)1,(short) 2),GameType.RANDOM,0,1);
        assertEq(1, r_.getScores().size());
    }
    @Test
    public void calculateScores4() {
        ResultsGame r_ = new ResultsGame();
        r_.calculateScores(Shorts.newList((short)1,(short) 2),GameType.TRAINING,0,1);
        assertEq(0, r_.getScores().size());
    }
}
