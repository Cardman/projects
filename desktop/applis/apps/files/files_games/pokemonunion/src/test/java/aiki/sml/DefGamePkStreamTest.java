package aiki.sml;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import code.mock.MockLSexList;
import code.mock.MockProgramInfos;
import org.junit.Test;

public final class DefGamePkStreamTest extends EquallablePkFileUtil {
    @Test
    public void checkGame1() {
        MockProgramInfos pr_ = pr(0, 1);
        DataBase d_ = InitDbValid.initDb();
        Game g_ = new Game(d_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), d_);
        DefGamePkStream def_ = new DefGamePkStream(pr_);
        def_.save("_",g_);
        assertNotNull(new DefGameChecker().checkGame(d_,new MockLSexList(),def_.load("_",new MockLSexList())));
    }
    @Test
    public void checkGame2() {
        MockProgramInfos pr_ = pr(0, 1);
        DataBase d_ = InitDbValid.initDb();
        Game g_ = new Game(d_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), d_);
        g_.getPlayer().getTeam().clear();
        DefGamePkStream def_ = new DefGamePkStream(pr_);
        def_.save("_",g_);
        assertNull(new DefGameChecker().checkGame(d_,new MockLSexList(),def_.load("_",new MockLSexList())));
    }
    @Test
    public void checkGame3() {
        MockProgramInfos pr_ = pr(0, 1);
        DataBase d_ = InitDbValid.initDb();
        Game g_ = new Game(d_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), d_);
        DefGamePkStream def_ = new DefGamePkStream(pr_);
        def_.save("_",g_);
        assertNull(new DefGameChecker().checkGame(d_,new MockLSexList(),def_.loadThen("__",new MockLSexList())));
    }
}
