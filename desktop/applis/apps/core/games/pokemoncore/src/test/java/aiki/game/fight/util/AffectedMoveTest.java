package aiki.game.fight.util;

import aiki.db.EquallablePkUtil;
import org.junit.Test;

public class AffectedMoveTest extends EquallablePkUtil {
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
