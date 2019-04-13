package aiki.game.fight.util;

import aiki.game.fight.Anticipation;
import aiki.game.fight.StacksOfUses;
import org.junit.Test;

import static aiki.db.EquallablePkUtil.assertEq;
import static org.junit.Assert.assertNotNull;

public class StacksOfUsesTest {
    @Test
    public void new_StacksOfUses_1_test() {
        StacksOfUses a_ = new StacksOfUses(",");
        assertEq(0, a_.getNbRounds());
    }
    @Test
    public void new_Anticipation_2_test() {
        StacksOfUses a_ = StacksOfUses.newStacksOfUses(",");
        assertEq(0, a_.getNbRounds());
    }
    @Test
    public void display1Test() {
        StacksOfUses a_ =  new StacksOfUses();
        a_.setFirstStacked(true);
        a_.setLastStacked(true);
        assertNotNull(a_.display());
    }
    @Test
    public void display2Test() {
        StacksOfUses a_ =  new StacksOfUses(",");
        a_.setFirstStacked(true);
        a_.setLastStacked(false);
        assertNotNull(a_.display());
    }
    @Test
    public void display3Test() {
        StacksOfUses a_ =  new StacksOfUses(",");
        a_.setFirstStacked(false);
        a_.setLastStacked(true);
        assertNotNull(a_.display());
    }
    @Test
    public void display4Test() {
        StacksOfUses a_ =  new StacksOfUses(",");
        a_.setFirstStacked(false);
        a_.setLastStacked(false);
        assertNotNull(a_.display());
    }
}
