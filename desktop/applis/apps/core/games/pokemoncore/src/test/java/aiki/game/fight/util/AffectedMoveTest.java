package aiki.game.fight.util;

import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNotNull;

public class AffectedMoveTest {
    @Test
    public void new_AffectedMove_1_test() {
        AffectedMove a_ = new AffectedMove("/");
        assertEq("",a_.getMove());
    }
    @Test
    public void new_AffectedMove_2_test() {
        AffectedMove a_ = AffectedMove.newAffectedMove("/");
        assertEq("",a_.getMove());
    }
    @Test
    public void displayTest() {
        AffectedMove a_ = AffectedMove.newAffectedMove("/");
        assertNotNull(a_.display());
    }
}
