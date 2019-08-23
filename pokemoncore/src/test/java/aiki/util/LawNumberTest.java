package aiki.util;

import aiki.comments.Comment;
import aiki.game.fight.enums.IssueSimulation;
import code.maths.montecarlo.MonteCarloNumber;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertTrue;

public class LawNumberTest {
    @Test
    public void test() {
        assertEq("",new Comment().join());
        assertEq(0,new LawNumber(new MonteCarloNumber(), (short) 0).getNumber());
        assertTrue(!IssueSimulation.KO_PLAYER.isRules());
        assertTrue(IssueSimulation.RULES_LEARN.isRules());
    }
}
