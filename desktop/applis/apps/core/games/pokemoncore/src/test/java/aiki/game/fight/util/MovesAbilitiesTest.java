package aiki.game.fight.util;

import aiki.db.EquallablePkUtil;
import code.util.StringList;
import org.junit.Test;

public class MovesAbilitiesTest extends EquallablePkUtil {
    @Test
    public void new_MovesAbilities_test() {
        MovesAbilities m_ = new MovesAbilities();
        m_.setAbilities(new StringList());
        m_.setMoves(new StringList());
        assertNotNull(m_.getAbilities());
        assertNotNull(m_.getMoves());
    }
}
