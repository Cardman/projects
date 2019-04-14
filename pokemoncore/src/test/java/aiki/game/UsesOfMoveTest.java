package aiki.game;

import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNotNull;

public class UsesOfMoveTest {
    @Test
    public void new_UsesOfMove_1_test() {
        UsesOfMove u_ = new UsesOfMove("");
        assertEq((int)u_.getCurrent(),(long)u_.getMax());
    }
    @Test
    public void new_UsesOfMove_2_test() {
        UsesOfMove u_ = UsesOfMove.newUsesOfMove("");
        assertEq((int)u_.getCurrent(),(long)u_.getMax());
        assertNotNull(u_.display());
    }
}
