package aiki.game.fight.util;

import aiki.db.EquallablePkUtil;
import org.junit.Test;

public class CopiedMoveTest extends EquallablePkUtil {
    @Test
    public void new_CopiedMove_1_Test() {
        CopiedMove c_ = new CopiedMove();
        assertEq(0,c_.getPp());
    }
    @Test
    public void new_CopiedMove_2_Test() {
        CopiedMove c_ = new CopiedMove(",");
        assertEq(0,c_.getPp());
    }
    @Test
    public void new_CopiedMove_3_Test() {
        CopiedMove c_ = CopiedMove.newCopiedMove(",");
        assertEq(0,c_.getPp());
    }
    @Test
    public void displayTest() {
        CopiedMove c_ = CopiedMove.newCopiedMove(",");
        assertNotNull(c_.display());
    }
}
