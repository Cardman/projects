package aiki.sml;

import aiki.db.DataBase;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import code.mock.MockLSexList;
import code.mock.MockProgramInfos;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.util.StringList;
import code.util.core.StringUtil;
import org.junit.Test;

public final class DefConfPkStreamTest extends EquallablePkFileUtil {
    @Test
    public void load1() {
        MockProgramInfos pr_ = pr(0, 1);
        DefConfPkStream def_ = new DefConfPkStream(pr_);
        LoadedGameConf load_ = def_.load(StreamFolderFile.getTempFolder(pr_, "__"), new StringList(), new MockLSexList());
        assertEq("",load_.getLoadingGame().getLastRom());
        assertEq("",load_.getLoadingGame().getLastSavedGame());
        assertTrue(load_.getLoadingGame().isSaveHomeFolder());
        assertFalse(load_.getLoadingGame().isLoadHomeFolder());
        assertTrue(load_.getLoadingGame().isLoadLastRom());
        assertFalse(load_.getLoadingGame().isLoadLastGame());
        assertTrue(load_.getLoadingGame().isSaveGameAtExit());
        assertTrue(load_.getLoadingGame().isEnableAnimation());
        assertTrue(load_.getLoadingGame().isEnableMovingHerosAnimation());
        assertEq("",load_.getLoadingGame().getExport());
        assertNull(load_.getGame());
    }
    @Test
    public void load2() {
        MockProgramInfos pr_ = pr(0, 1);
        DefConfPkStream def_ = new DefConfPkStream(pr_);
        LoadingGame conf_ = new LoadingGame();
        conf_.setExport("2");
        conf_.setLastRom("3");
        conf_.setLastSavedGame("4");
        conf_.setSaveHomeFolder(false);
        conf_.setLoadLastRom(false);
        conf_.setSaveGameAtExit(false);
        conf_.setEnableAnimation(false);
        conf_.setEnableMovingHerosAnimation(false);
        conf_.setLoadHomeFolder(true);
        conf_.setLoadLastGame(true);
        String tmp_ = StreamFolderFile.getTempFolder(pr_, "__");
        def_.save("1", conf_);
        LoadedGameConf load_ = def_.load(tmp_, new StringList("1"), new MockLSexList());
        assertEq("3",load_.getLoadingGame().getLastRom());
        assertEq("4",load_.getLoadingGame().getLastSavedGame());
        assertFalse(load_.getLoadingGame().isSaveHomeFolder());
        assertTrue(load_.getLoadingGame().isLoadHomeFolder());
        assertFalse(load_.getLoadingGame().isLoadLastRom());
        assertTrue(load_.getLoadingGame().isLoadLastGame());
        assertFalse(load_.getLoadingGame().isSaveGameAtExit());
        assertFalse(load_.getLoadingGame().isEnableAnimation());
        assertFalse(load_.getLoadingGame().isEnableMovingHerosAnimation());
        assertEq("2",load_.getLoadingGame().getExport());
        assertNull(load_.getGame());
    }
    @Test
    public void load3() {
        MockProgramInfos pr_ = pr(0, 1);
        DataBase d_ = InitDbValid.initDb();
        Game g_ = new Game(d_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), d_);
        DefGamePkStream defSave_ = new DefGamePkStream(pr_);
        DefConfPkStream defLoad_ = new DefConfPkStream(pr_);
        String tmp_ = StreamFolderFile.getTempFolder(pr_, "__");
        defSave_.save("1",g_);
        LoadedGameConf load_ = defLoad_.load(tmp_, new StringList("1"), new MockLSexList());
        assertNotNull(load_.getGame());
        assertEq("",load_.getLoadingGame().getLastRom());
        assertEq("1",load_.getLoadingGame().getLastSavedGame());
        assertTrue(load_.getLoadingGame().isSaveHomeFolder());
        assertFalse(load_.getLoadingGame().isLoadHomeFolder());
        assertTrue(load_.getLoadingGame().isLoadLastRom());
        assertFalse(load_.getLoadingGame().isLoadLastGame());
        assertTrue(load_.getLoadingGame().isSaveGameAtExit());
        assertTrue(load_.getLoadingGame().isEnableAnimation());
        assertTrue(load_.getLoadingGame().isEnableMovingHerosAnimation());
        assertEq("",load_.getLoadingGame().getExport());
    }
    @Test
    public void load4() {
        MockProgramInfos pr_ = pr(0, 1);
        DefConfPkStream def_ = new DefConfPkStream(pr_);
        LoadingGame conf_ = new LoadingGame();
        conf_.setExport("2");
        conf_.setLastRom("//");
        conf_.setLastSavedGame("//");
        conf_.setSaveHomeFolder(false);
        conf_.setLoadLastRom(false);
        conf_.setSaveGameAtExit(false);
        conf_.setEnableAnimation(false);
        conf_.setEnableMovingHerosAnimation(false);
        conf_.setLoadHomeFolder(true);
        conf_.setLoadLastGame(true);
        String tmp_ = StreamFolderFile.getTempFolder(pr_, "__");
        def_.save(StringUtil.concat(tmp_, MessagesPkGame.getAppliFilesTr(pr_.getTranslations()).val().getMapping().getVal(MessagesPkGame.LOAD_CONFIG_FILE)), conf_);
        LoadedGameConf load_ = def_.load(tmp_, new StringList(), new MockLSexList());
        assertEq("",load_.getLoadingGame().getLastRom());
        assertEq("",load_.getLoadingGame().getLastSavedGame());
        assertFalse(load_.getLoadingGame().isSaveHomeFolder());
        assertTrue(load_.getLoadingGame().isLoadHomeFolder());
        assertFalse(load_.getLoadingGame().isLoadLastRom());
        assertTrue(load_.getLoadingGame().isLoadLastGame());
        assertFalse(load_.getLoadingGame().isSaveGameAtExit());
        assertFalse(load_.getLoadingGame().isEnableAnimation());
        assertFalse(load_.getLoadingGame().isEnableMovingHerosAnimation());
        assertEq("2",load_.getLoadingGame().getExport());
        assertNull(load_.getGame());
    }
    @Test
    public void load5() {
        MockProgramInfos pr_ = pr(0, 1);
        DefConfPkStream def_ = new DefConfPkStream(pr_);
        LoadingGame conf_ = new LoadingGame();
        conf_.setExport("2");
        conf_.setLastRom("3");
        conf_.setLastSavedGame("4");
        conf_.setSaveHomeFolder(false);
        conf_.setLoadLastRom(false);
        conf_.setSaveGameAtExit(false);
        conf_.setEnableAnimation(false);
        conf_.setEnableMovingHerosAnimation(false);
        conf_.setLoadHomeFolder(true);
        conf_.setLoadLastGame(true);
        String tmp_ = StreamFolderFile.getTempFolder(pr_, "__");
        def_.save(StringUtil.concat(tmp_, MessagesPkGame.getAppliFilesTr(pr_.getTranslations()).val().getMapping().getVal(MessagesPkGame.LOAD_CONFIG_FILE)), conf_);
        LoadedGameConf load_ = def_.load(tmp_, new StringList(), new MockLSexList());
        assertEq("3",load_.getLoadingGame().getLastRom());
        assertEq("4",load_.getLoadingGame().getLastSavedGame());
        assertFalse(load_.getLoadingGame().isSaveHomeFolder());
        assertTrue(load_.getLoadingGame().isLoadHomeFolder());
        assertFalse(load_.getLoadingGame().isLoadLastRom());
        assertTrue(load_.getLoadingGame().isLoadLastGame());
        assertFalse(load_.getLoadingGame().isSaveGameAtExit());
        assertFalse(load_.getLoadingGame().isEnableAnimation());
        assertFalse(load_.getLoadingGame().isEnableMovingHerosAnimation());
        assertEq("2",load_.getLoadingGame().getExport());
        assertNull(load_.getGame());
    }
    @Test
    public void load6() {
        MockProgramInfos pr_ = pr(0, 1);
        DataBase d_ = InitDbValid.initDb();
        Game g_ = new Game(d_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), d_);
        g_.setZippedRom("2");
        DefGamePkStream defSave_ = new DefGamePkStream(pr_);
        DefConfPkStream defLoad_ = new DefConfPkStream(pr_);
        String tmp_ = StreamFolderFile.getTempFolder(pr_, "__");
        defSave_.save("1",g_);
        LoadedGameConf load_ = defLoad_.load(tmp_, new StringList("1"), new MockLSexList());
        assertNotNull(load_.getGame());
        assertEq("",load_.getLoadingGame().getLastRom());
        assertEq("",load_.getLoadingGame().getLastSavedGame());
        assertTrue(load_.getLoadingGame().isSaveHomeFolder());
        assertFalse(load_.getLoadingGame().isLoadHomeFolder());
        assertTrue(load_.getLoadingGame().isLoadLastRom());
        assertFalse(load_.getLoadingGame().isLoadLastGame());
        assertTrue(load_.getLoadingGame().isSaveGameAtExit());
        assertTrue(load_.getLoadingGame().isEnableAnimation());
        assertTrue(load_.getLoadingGame().isEnableMovingHerosAnimation());
        assertEq("",load_.getLoadingGame().getExport());
    }
    @Test
    public void load7() {
        MockProgramInfos pr_ = pr(0, 1);
        DataBase d_ = InitDbValid.initDb();
        Game g_ = new Game(d_);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), d_);
        g_.setZippedRom("2");
        DefGamePkStream defSave_ = new DefGamePkStream(pr_);
        DefConfPkStream defLoad_ = new DefConfPkStream(pr_);
        String tmp_ = StreamFolderFile.getTempFolder(pr_, "__");
        defSave_.save("1",g_);
        StreamBinaryFile.writeFile("2",new byte[4],pr_.getStreams());
        LoadedGameConf load_ = defLoad_.load(tmp_, new StringList("1"), new MockLSexList());
        assertNotNull(load_.getGame());
        assertEq("2",load_.getLoadingGame().getLastRom());
        assertEq("1",load_.getLoadingGame().getLastSavedGame());
        assertTrue(load_.getLoadingGame().isSaveHomeFolder());
        assertFalse(load_.getLoadingGame().isLoadHomeFolder());
        assertTrue(load_.getLoadingGame().isLoadLastRom());
        assertFalse(load_.getLoadingGame().isLoadLastGame());
        assertTrue(load_.getLoadingGame().isSaveGameAtExit());
        assertTrue(load_.getLoadingGame().isEnableAnimation());
        assertTrue(load_.getLoadingGame().isEnableMovingHerosAnimation());
        assertEq("",load_.getLoadingGame().getExport());
    }
}
