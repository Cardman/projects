package aiki.game.fight.util;

import aiki.game.fight.Fighter;
import aiki.game.fight.TeamPosition;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNotNull;

public class TeamPositionTest {
    @Test
    public void new_TeamPosition_1_test() {
        TeamPosition t_ = new TeamPosition(";");
        assertEq(0,t_.getPosition());
    }
    @Test
    public void new_TeamPosition_2_test() {
        TeamPosition t_ = TeamPosition.newTeamPosition(";");
        assertEq(0,t_.getPosition());
    }
    @Test
    public void new_TeamPosition_3_test() {
        TeamPosition t_ = new TeamPosition(Fighter.BACK,Fighter.BACK);
        assertEq(Fighter.BACK,t_.getPosition());
    }
    @Test
    public void new_TeamPosition_4_test() {
        TeamPosition t_ = new TeamPosition(Fighter.BACK, (byte) 0);
        assertEq(0,t_.getPosition());
    }
    @Test
    public void new_TeamPosition_5_test() {
        TeamPosition t_ = new TeamPosition((byte) 0,Fighter.BACK);
        assertEq(Fighter.BACK,t_.getPosition());
    }
    @Test
    public void display1Test() {
        TeamPosition t_ = new TeamPosition((byte) 0,(byte) 0);
        assertNotNull(t_.display());
    }
    @Test
    public void display2Test() {
        TeamPosition t_ = new TeamPosition((byte) 0,Fighter.BACK);
        assertNotNull(t_.display());
    }
}
