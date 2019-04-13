package aiki.game.fight.util;

import aiki.game.fight.ActivityOfMove;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ActivityOfMoveTest {
    @Test
    public void new_ActivityOfMove_1_test() {
        ActivityOfMove a_ = new ActivityOfMove();
        assertTrue(a_.isIncrementCount());
    }
    @Test
    public void new_ActivityOfMove_2_test() {
        ActivityOfMove a_ = new ActivityOfMove(";");
        assertEq(0, a_.getNbTurn());
    }
    @Test
    public void new_ActivityOfMove_3_test() {
        ActivityOfMove a_ = ActivityOfMove.newActivityOfMove(";");
        assertEq(0, a_.getNbTurn());
    }
    @Test
    public void new_ActivityOfMove_4_test() {
        ActivityOfMove a_ = new ActivityOfMove(";;");
        assertEq(0, a_.getNbTurn());
    }
    @Test
    public void new_ActivityOfMove_5_test() {
        ActivityOfMove a_ = new ActivityOfMove(";T;");
        assertEq(0, a_.getNbTurn());
    }
    @Test
    public void increment1Test() {
        ActivityOfMove a_ = new ActivityOfMove(true);
        a_.increment();
        assertEq(1, a_.getNbTurn());
    }
    @Test
    public void increment2Test() {
        ActivityOfMove a_ = new ActivityOfMove(false);
        a_.increment();
        assertEq(0, a_.getNbTurn());
    }
    @Test
    public void display1Test() {
        ActivityOfMove a_ = new ActivityOfMove(true);
        a_.setEnabled(false);
        assertNotNull(a_.display());
    }
    @Test
    public void display2Test() {
        ActivityOfMove a_ = new ActivityOfMove(false);
        a_.setEnabled(false);
        assertNotNull(a_.display());
    }
    @Test
    public void display3Test() {
        ActivityOfMove a_ = new ActivityOfMove(true);
        a_.setEnabled(true);
        assertNotNull(a_.display());
    }
    @Test
    public void display4Test() {
        ActivityOfMove a_ = new ActivityOfMove(false);
        a_.setEnabled(true);
        assertNotNull(a_.display());
    }
}
