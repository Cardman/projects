package aiki.game.fight.util;

import aiki.game.fight.MoveTeamPosition;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MoveTeamPositionTest {
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
