package code.mock;

import cards.president.*;
import org.junit.Test;

public final class MockGamePresidentTest extends EquallableCardsMockUtil {
    @Test
    public void bids1() {
        MockGamePresident m_ = new MockGamePresident();
        m_.getSw().add(new HandPresident());
        assertEq(0,m_.currentSwitch().total());
        assertEq(0,m_.strategieEchange(null,(byte)0).total());
    }
    @Test
    public void bids2() {
        MockGamePresident m_ = new MockGamePresident();
        m_.getSw().add(new HandPresident());
        assertEq(0,m_.currentSwitch().total());
        assertEq(0,m_.strategieEchangeUser(null).total());
    }
    @Test
    public void cards1() {
        MockGamePresident m_ = new MockGamePresident();
        m_.getCards().add(new HandPresident());
        assertEq(0,m_.currentCards().total());
        assertEq(0,m_.playedCards(null).total());
    }
    @Test
    public void cards2() {
        MockGamePresident m_ = new MockGamePresident();
        m_.getCards().add(new HandPresident());
        assertEq(0,m_.currentCards().total());
        assertEq(0,m_.playedCardsUser(null).total());
    }
    @Test
    public void stacks() {
        MockGamePresident m_ = new MockGamePresident();
        m_.getStacks().add(new DealPresident());
        assertEq(0,m_.empiler(0,null,null).nombreDeMains());
    }
}
