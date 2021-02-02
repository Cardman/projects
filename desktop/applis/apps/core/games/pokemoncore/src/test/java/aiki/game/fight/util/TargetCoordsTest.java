package aiki.game.fight.util;

import aiki.db.EquallablePkUtil;
import aiki.game.fight.TargetCoords;
import org.junit.Test;

public class TargetCoordsTest extends EquallablePkUtil {
    @Test
    public void new_MoveTarget_1_test() {
        TargetCoords mt_ = new TargetCoords();
        assertEq(0,mt_.getPosition());
        assertEq(0,mt_.getTeam());
    }
    @Test
    public void new_MoveTarget_2_test() {
        TargetCoords mt_ = new TargetCoords(";");
        assertEq(0,mt_.getPosition());
        assertEq(0,mt_.getTeam());
    }
    @Test
    public void new_MoveTarget_3_test() {
        TargetCoords mt_ = TargetCoords.newTargetCoords(";");
        assertEq(0,mt_.getPosition());
        assertEq(0,mt_.getTeam());
    }
    @Test
    public void displayTest() {
        TargetCoords mt_ = TargetCoords.newTargetCoords(";");
        assertNotNull(mt_.display());
    }
}
