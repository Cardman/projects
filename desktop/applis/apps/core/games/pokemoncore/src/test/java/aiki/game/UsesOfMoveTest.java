package aiki.game;

import aiki.db.EquallablePkUtil;
import org.junit.Test;

public class UsesOfMoveTest extends EquallablePkUtil {
    @Test
    public void new_UsesOfMove_1_test() {
        UsesOfMove u_ = new UsesOfMove("");
        assertEq(u_.getCurrent(),(long)u_.getMax());
    }
    @Test
    public void new_UsesOfMove_2_test() {
        UsesOfMove u_ = UsesOfMove.newUsesOfMove("");
        assertEq(u_.getCurrent(),(long)u_.getMax());
        assertNotNull(u_.display());
    }
}
