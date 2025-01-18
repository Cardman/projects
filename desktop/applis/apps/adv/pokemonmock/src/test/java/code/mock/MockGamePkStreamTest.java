package code.mock;

import aiki.game.Game;
import aiki.instances.Instances;
import org.junit.Test;

public final class MockGamePkStreamTest extends EquallableMockPkUtil {
    @Test
    public void g1() {
        MockGamePkStream m_ = new MockGamePkStream();
        Game g_ = Instances.newGame();
        g_.getDifficulty().setIvPlayer(1);
        g_.getDifficulty().setIvFoe(1);
        m_.save("", g_);
        assertEq(1,m_.load("",null).getDifficulty().getIvPlayer());
        assertEq(1,m_.loadThen("",null).getDifficulty().getIvFoe());
    }
}
