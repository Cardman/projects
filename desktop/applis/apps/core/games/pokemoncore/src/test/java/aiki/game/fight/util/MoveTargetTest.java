package aiki.game.fight.util;

import aiki.db.EquallablePkUtil;
import org.junit.Test;

public class MoveTargetTest extends EquallablePkUtil {
    @Test
    public void new_MoveTarget_1_test() {
        MoveTarget mt_ = new MoveTarget();
        assertNull(mt_.getTarget());
    }
    @Test
    public void new_MoveTarget_2_test() {
        MoveTarget mt_ = new MoveTarget(",");
        assertNotNull(mt_.getTarget());
    }
    @Test
    public void new_MoveTarget_3_test() {
        MoveTarget mt_ = MoveTarget.newMoveTarget(",");
        assertNotNull(mt_.getTarget());
    }
    @Test
    public void displayTest() {
        MoveTarget mt_ = MoveTarget.newMoveTarget(",");
        assertNotNull(mt_.display());
    }
}
