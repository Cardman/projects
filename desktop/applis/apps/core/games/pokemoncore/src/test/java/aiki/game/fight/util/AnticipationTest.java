package aiki.game.fight.util;

import aiki.game.fight.Anticipation;
import aiki.game.fight.Fighter;
import code.util.core.StringUtil;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AnticipationTest {
    @Test
    public void new_Anticipation_1_test() {
        Anticipation a_ = new Anticipation(",");
        assertNotNull(a_.getTargetPosition());
    }
    @Test
    public void new_Anticipation_2_test() {
        Anticipation a_ = Anticipation.newAnticipation(",");
        assertNotNull(a_.getTargetPosition());
    }
    @Test
    public void new_Anticipation_3_test() {
        Anticipation a_ = Anticipation.newAnticipation(StringUtil.concatNbs(",", Fighter.BACK));
        assertNotNull(a_.getTargetPosition());
    }
    @Test
    public void displayTest() {
        Anticipation a_ = Anticipation.newAnticipation(",");
        assertNotNull(a_.display());
    }
}
