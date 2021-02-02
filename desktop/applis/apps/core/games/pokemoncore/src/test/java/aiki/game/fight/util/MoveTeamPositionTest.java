package aiki.game.fight.util;

import aiki.db.EquallablePkUtil;
import aiki.game.fight.MoveTeamPosition;
import org.junit.Test;

public class MoveTeamPositionTest extends EquallablePkUtil {
    @Test
    public void new_MoveTeamPosition_1_Test() {
        MoveTeamPosition m_ = new MoveTeamPosition("");
        assertNotNull(m_.getTeamPosition());
    }
    @Test
    public void new_MoveTeamPosition_2_Test() {
        MoveTeamPosition m_ = MoveTeamPosition.newMoveTeamPosition("");
        assertNotNull(m_.getTeamPosition());
    }
    @Test
    public void displayTest() {
        MoveTeamPosition m_ = new MoveTeamPosition("");
        assertNotNull(m_.display());
    }
}
