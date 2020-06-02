package aiki.game.fight.util;

import code.util.StringList;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MovesAbilitiesTest {
    @Test
    public void new_MovesAbilities_test() {
        MovesAbilities m_ = new MovesAbilities();
        m_.setAbilities(new StringList());
        m_.setMoves(new StringList());
        assertNotNull(m_.getAbilities());
        assertNotNull(m_.getMoves());
    }
}
