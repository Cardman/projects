package cards.consts.beans;

import cards.consts.LineDeal;
import code.util.*;
import org.junit.Test;

public final class BeanDisplayCardsUtilTest extends EquallableCardConstBeanUtil {
    @Test
    public void test() {
        LineDeal scores_ = new LineDeal();
        scores_.setScores(new Longs());
        scores_.getScores().add(0L);
        assertEq(0,TakerResult.buildScores(new BeanDisplayCardsSample(),new StringList(""),new CustList<LineDeal>(scores_)));
    }
}
