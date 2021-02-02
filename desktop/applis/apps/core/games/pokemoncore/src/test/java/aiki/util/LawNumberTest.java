package aiki.util;

import aiki.comments.Comment;
import aiki.db.EquallablePkUtil;
import aiki.game.fight.enums.IssueSimulation;
import code.maths.montecarlo.MonteCarloNumber;
import org.junit.Test;

public class LawNumberTest extends EquallablePkUtil {
    @Test
    public void test() {
        assertEq("",new Comment().join());
        assertEq(0,new LawNumber(new MonteCarloNumber(), (short) 0).getNumber());
        assertTrue(!IssueSimulation.KO_PLAYER.isRules());
        assertTrue(IssueSimulation.RULES_LEARN.isRules());
    }
}
