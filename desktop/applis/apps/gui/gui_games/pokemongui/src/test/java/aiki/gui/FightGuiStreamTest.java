package aiki.gui;

import aiki.db.*;
import aiki.facade.enums.*;
import aiki.fight.enums.*;
import aiki.fight.moves.*;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.fight.util.*;
import aiki.game.Game;
import aiki.game.params.enums.*;
import aiki.game.player.enums.*;
import aiki.gui.components.walk.DefTileRender;
import aiki.instances.*;
import aiki.main.*;
import aiki.map.*;
import aiki.map.buildings.*;
import aiki.map.characters.*;
import aiki.map.characters.enums.*;
import aiki.map.enums.*;
import aiki.map.levels.*;
import aiki.map.levels.enums.*;
import aiki.map.places.*;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.*;
import aiki.map.util.*;
import aiki.sml.*;
import aiki.util.*;
import code.bean.nat.NatNavigation;
import code.gui.*;
import code.gui.files.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.maths.litteral.*;
import code.mock.*;
import code.sml.NavigationCore;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.stream.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import org.junit.Test;

public final class FightGuiStreamTest extends InitDbGuiAiki {

    @Test
    public void loadNoParam1() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.submit(new MockCallable<DataBase>(initDb()));
        CreateMainWindowAiki runner_ = launcher(pr_, fact_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam2() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        DataBase d_ = initDb();
        fact_.submit(new MockCallable<DataBase>(d_));
        Game g_ = game(d_);
        fact_.getGamePkStream().save("last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("last");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam3() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        DataBase d_ = initDb();
        fact_.submit(new MockCallable<DataBase>(d_));
        Game g_ = game(d_);
        fact_.getGamePkStream().save("/last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam4() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam5() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        Game g_ = game(initDb());
        fact_.getGamePkStream().save("/last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam6() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("/rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam7() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        Game g_ = game(initDb());
        fact_.getGamePkStream().save("/last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("/rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam8() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setSaveGameAtExit(false);
        last_.setLoadLastGame(false);
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam9() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.submit(new MockCallable<DataBase>(init()));
        fact_.setDataBaseStream(new MockDataBaseStream());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setSaveGameAtExit(false);
        last_.setLoadLastGame(false);
        last_.setLastSavedGame("/last");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam10() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLoadLastRom(false);
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam11() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStreamNoLast());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam12() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStreamNoLast());
        Game g_ = game(initDb());
        fact_.getGamePkStream().save("/last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam13() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStreamNoLast());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("/rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam14() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStreamNoLast());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setSaveGameAtExit(false);
        last_.setLoadLastGame(false);
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam15() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.submit(new MockCallable<DataBase>(init()));
        fact_.setDataBaseStream(new MockDataBaseStreamNoLast());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setSaveGameAtExit(false);
        last_.setLoadLastGame(false);
        last_.setLastSavedGame("/last");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadNoParam16() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStreamNoLast());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLoadLastRom(false);
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowNoParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam1() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.submit(new MockCallable<DataBase>(initDb()));
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "last");
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam2() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        DataBase d_ = initDb();
        fact_.submit(new MockCallable<DataBase>(d_));
        Game g_ = game(d_);
        fact_.getGamePkStream().save("last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("last");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam3() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        DataBase d_ = initDb();
        fact_.submit(new MockCallable<DataBase>(d_));
        Game g_ = game(d_);
        fact_.getGamePkStream().save("/last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam4() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam5() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        Game g_ = game(initDb());
        fact_.getGamePkStream().save("/last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam6() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("/rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam7() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        Game g_ = game(initDb());
        fact_.getGamePkStream().save("/last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("/rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam8() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setSaveGameAtExit(false);
        last_.setLoadLastGame(false);
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam9() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.submit(new MockCallable<DataBase>(init()));
        fact_.setDataBaseStream(new MockDataBaseStream());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setSaveGameAtExit(false);
        last_.setLoadLastGame(false);
        last_.setLastSavedGame("/last");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam10() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        Game g_ = game(initDb());
        g_.getPlayer().getTeam().clear();
        fact_.getGamePkStream().save("/last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("/rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam11() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStreamNoLast());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam12() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStreamNoLast());
        Game g_ = game(initDb());
        fact_.getGamePkStream().save("/last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam13() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStreamNoLast());
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("/rom");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }

    @Test
    public void loadParam14() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
        fact_.setDataBaseStream(new MockDataBaseStream());
        Game g_ = game(initDb());
        fact_.getGamePkStream().save("/last",g_);
        LoadingGame last_ = DocumentReaderAikiCoreUtil.newLoadingGame();
        last_.setLastSavedGame("/last");
        last_.setLastRom("/rom");
        last_.setExport("exp");
        CreateMainWindowAiki runner_ = launcher(pr_, fact_, "/last", last_);
        run(runner_);
        runner_.getWindow().getLoadingConf().setExport("exp");
        AbstractThread th_ = tryAn(((MockThreadFactory) pr_.getThreadFactory()));
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
        ((MockBaseExecutorService)((CreateMainWindowParam)th_.getRunnable()).getWindow().getExpThread()).getTasks().lastValue().attendre();
        assertTrue(((CreateMainWindowParam)th_.getRunnable()).getWindow().getCommonFrame().isVisible());
    }
    @Test
    public void menuRom1() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getZipLoad());
        window_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom2() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStreamNoLast());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getZipLoad());
        window_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom3() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getFolderLoad());
        window_.getFileOpenFolderFrame().getFolderOpenDialogContent().getFileName().setText("_");
        window_.getFileOpenFolderFrame().getFolderOpenDialogContent().setSelectedPath("_");
        tryClick((AbsButton) window_.getFileOpenFolderFrame().getFolderOpenDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom4() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStreamNoLast());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getFolderLoad());
        window_.getFileOpenFolderFrame().getFolderOpenDialogContent().getFileName().setText("_");
        window_.getFileOpenFolderFrame().getFolderOpenDialogContent().setSelectedPath("_");
        tryClick((AbsButton) window_.getFileOpenFolderFrame().getFolderOpenDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom5() {
        WindowAiki window_ = newGame();
        window_.getLoadingConf().setLoadHomeFolder(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getZipLoad());
        window_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom6() {
        WindowAiki window_ = newGame();
        window_.getLoadingConf().setLoadHomeFolder(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStreamNoLast());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getZipLoad());
        window_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom7() {
        WindowAiki window_ = newGame();
        window_.getLoadingConf().setLoadHomeFolder(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getFolderLoad());
        window_.getFileOpenFolderFrame().getFolderOpenDialogContent().getFileName().setText("_");
        window_.getFileOpenFolderFrame().getFolderOpenDialogContent().setSelectedPath("_");
        tryClick((AbsButton) window_.getFileOpenFolderFrame().getFolderOpenDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom8() {
        WindowAiki window_ = newGame();
        window_.getLoadingConf().setLoadHomeFolder(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStreamNoLast());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getFolderLoad());
        window_.getFileOpenFolderFrame().getFolderOpenDialogContent().getFileName().setText("_");
        window_.getFileOpenFolderFrame().getFolderOpenDialogContent().setSelectedPath("_");
        tryClick((AbsButton) window_.getFileOpenFolderFrame().getFolderOpenDialogContent().getButtons().getComponent(0));
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom9() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        save(window_);
        tryClick(window_.getCore().getZipLoad());
        window_.getFileOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("txt1");
        window_.getFileOpenSaveFrame().getFileOpenDialogContent().getFileName().setText("txt2");
        tryClick(window_.getFileOpenSaveFrame().getMainAction());
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom10() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStreamNoLast());
        updateBase(window_.getFrames().currentLg());
        save(window_);
        tryClick(window_.getCore().getZipLoad());
        window_.getFileOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("txt1");
        window_.getFileOpenSaveFrame().getFileOpenDialogContent().getFileName().setText("txt2");
        tryClick(window_.getFileOpenSaveFrame().getMainAction());
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom11() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        save(window_);
        tryClick(window_.getCore().getFolderLoad());
        window_.getFolderOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("txt1");
        window_.getFolderOpenSaveFrame().getFolderOpenDialogContent().getFileName().setText("txt2");
        tryClick(window_.getFolderOpenSaveFrame().getMainAction());
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        window_.initMessages();
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom12() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStreamNoLast());
        updateBase(window_.getFrames().currentLg());
        save(window_);
        tryClick(window_.getCore().getFolderLoad());
        window_.getFolderOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("txt1");
        window_.getFolderOpenSaveFrame().getFolderOpenDialogContent().getFileName().setText("txt2");
        tryClick(window_.getFolderOpenSaveFrame().getMainAction());
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom13() {
        WindowAiki window_ = newGame();
        window_.getLoadingConf().setLoadHomeFolder(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        save(window_);
        tryClick(window_.getCore().getZipLoad());
        window_.getFileOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("txt1");
        window_.getFileOpenSaveFrame().getFileOpenDialogContent().getFileName().setText("txt2");
        tryClick(window_.getFileOpenSaveFrame().getMainAction());
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom14() {
        WindowAiki window_ = newGame();
        window_.getLoadingConf().setLoadHomeFolder(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStreamNoLast());
        updateBase(window_.getFrames().currentLg());
        save(window_);
        tryClick(window_.getCore().getZipLoad());
        window_.getFileOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("txt1");
        window_.getFileOpenSaveFrame().getFileOpenDialogContent().getFileName().setText("txt2");
        tryClick(window_.getFileOpenSaveFrame().getMainAction());
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRom15() {
        WindowAiki window_ = newGame();
        window_.getLoadingConf().setLoadHomeFolder(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        save(window_);
        tryClick(window_.getCore().getFolderLoad());
        window_.getFolderOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("txt1");
        window_.getFolderOpenSaveFrame().getFolderOpenDialogContent().getFileName().setText("txt2");
        tryClick(window_.getFolderOpenSaveFrame().getMainAction());
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }

    @Test
    public void menuRom16() {
        WindowAiki window_ = newGame();
        window_.getLoadingConf().setLoadHomeFolder(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStreamNoLast());
        updateBase(window_.getFrames().currentLg());
        save(window_);
        tryClick(window_.getCore().getFolderLoad());
        window_.getFolderOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("txt1");
        window_.getFolderOpenSaveFrame().getFolderOpenDialogContent().getFileName().setText("txt2");
        tryClick(window_.getFolderOpenSaveFrame().getMainAction());
        tryAn(((MockThreadFactory) window_.getFrames().getThreadFactory()));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuGame1() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setData(initDb());
        window_.getCore().getGameLoad().setEnabled(true);
        tryClick(window_.getCore().getGameLoad());
        window_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuGame2() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setData(initDb());
        save(window_);
        window_.setSavedGame(true);
        window_.getGameLoad().setEnabled(true);
        tryClick(window_.getCore().getGameLoad());
        window_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuGame3() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setSexList(new MockLSexList());
        window_.getFacade().setData(initDb());
        save(window_);
        Game g_ = game(initDb());
        window_.getCore().getAikiFactory().getGamePkStream().save("last",g_);
        window_.getCore().getGameLoad().setEnabled(true);
        tryClick(window_.getCore().getGameLoad());
        window_.getFileOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("_");
        window_.getFileOpenSaveFrame().getFileOpenDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenSaveFrame().getMainAction());
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuGame4() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setSexList(new MockLSexList());
        window_.getFacade().setData(initDb());
        save(window_);
        Game g_ = game(initDb());
        g_.getPlayer().getTeam().clear();
        window_.getCore().getAikiFactory().getGamePkStream().save("last",g_);
        window_.getCore().getGameLoad().setEnabled(true);
        tryClick(window_.getCore().getGameLoad());
        window_.getFileOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("_");
        window_.getFileOpenSaveFrame().getFileOpenDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenSaveFrame().getMainAction());
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuGame5() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setData(initDb());
        window_.getCore().getGameLoad().setEnabled(true);
        Game g_ = game(initDb());
        window_.getCore().getAikiFactory().getGamePkStream().save("last",g_);
        tryClick(window_.getCore().getGameLoad());
        window_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuGame6() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setData(initDb());
        window_.getCore().getGameLoad().setEnabled(true);
        Game g_ = game(initDb());
        g_.getPlayer().getTeam().clear();
        window_.getCore().getAikiFactory().getGamePkStream().save("last",g_);
        tryClick(window_.getCore().getGameLoad());
        window_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        window_.getFileOpenRomFrame().getFileDialogContent().setSelectedAbsolutePath("_");
        tryClick((AbsButton) window_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuGame7() {
        WindowAiki window_ = newGame();
        GamesPk.appendPkGameDetailContent(GamesPk.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
        prepareFightTask(window_);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        DataBase db_ = initDb();
        db_.getMaxiPkBack().addEntry(PIKACHU,new int[][]{new int[1]});
        db_.getMaxiPkFront().addEntry(PIKACHU,new int[][]{new int[1]});
        db_.getMiniPk().addEntry(PIKACHU,new int[][]{new int[1]});
        db_.initializeWildPokemon();
        db_.boundsPk();
        window_.getFacade().setData(db_);
        window_.getCore().getGameLoad().setEnabled(true);
        Game g_ = game(db_);
        g_.setPlayerCoords(newCoords(2,0,5,0));
        g_.attract(db_);
        window_.getCore().getAikiFactory().getGamePkStream().save("last",g_);
        tryClick(window_.getCore().getGameLoad());
        window_.getFileOpenRomFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenRomFrame().getFileDialogContent().getButtons().getComponent(0));
        assertTrue(window_.getCommonFrame().isVisible());
        window_.getDataBattle().getActionListeners().get(0).action();
    }
    @Test
    public void menuGame8() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setSexList(new MockLSexList());
        window_.getFacade().setData(initDb());
        window_.getLoadingConf().setSaveHomeFolder(false);
        save(window_);
        Game g_ = game(initDb());
        window_.getCore().getAikiFactory().getGamePkStream().save("last",g_);
        window_.getCore().getGameLoad().setEnabled(true);
        tryClick(window_.getCore().getGameLoad());
        window_.getFileOpenSaveFrame().getFileSaveDialogContent().getFileName().setText("_");
        window_.getFileOpenSaveFrame().getFileOpenDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileOpenSaveFrame().getMainAction());
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void save1() {
        WindowAiki window_ = newGame();
        window_.getCommonFrame().getFrames().getCounts().put(window_.getApplicationName(),new ConcreteInteger(1));
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setData(initDb());
        window_.setVisible(false);
        window_.quit();
        assertFalse(window_.getCommonFrame().isVisible());
    }
    @Test
    public void save2() {
        WindowAiki window_ = newGame();
        window_.getCommonFrame().getFrames().getCounts().put(window_.getApplicationName(),new ConcreteInteger(1));
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getLoadingConf().setLastSavedGame("_");
        window_.getFacade().setData(initDb());
        window_.setVisible(false);
        window_.quit();
        assertFalse(window_.getCommonFrame().isVisible());
    }
    @Test
    public void save3() {
        WindowAiki window_ = newGame();
        window_.getCommonFrame().getFrames().getCounts().put(window_.getApplicationName(),new ConcreteInteger(1));
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getLoadingConf().setSaveGameAtExit(false);
        window_.getFacade().setData(initDb());
        window_.setVisible(false);
        window_.quit();
        assertFalse(window_.getCommonFrame().isVisible());
    }
    @Test
    public void save4() {
        WindowAiki window_ = newGame();
        window_.getCommonFrame().getFrames().getCounts().put(window_.getApplicationName(),new ConcreteInteger(1));
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setData(initDb());
        Game g_ = game(window_.getFacade().getData());
        window_.getFacade().setGame(g_);
        window_.setVisible(false);
        window_.quit();
        assertFalse(window_.getCommonFrame().isVisible());
    }
    @Test
    public void save5() {
        WindowAiki window_ = newGame();
        window_.getCommonFrame().getFrames().getCounts().put(window_.getApplicationName(),new ConcreteInteger(1));
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getLoadingConf().setLastSavedGame("_");
        window_.getFacade().setData(initDb());
        Game g_ = game(window_.getFacade().getData());
        window_.getFacade().setGame(g_);
        window_.setVisible(false);
        window_.quit();
        assertFalse(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuSave1() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setData(initDb());
        Game g_ = game(window_.getFacade().getData());
        window_.getFacade().setGame(g_);
        window_.getCore().getGameSave().setEnabled(true);
        tryClick(window_.getCore().getGameSave());
        window_.getFileSaveFrame().getFileDialogContent().getFileName().setText("_");
        tryClick((AbsButton) window_.getFileSaveFrame().getFileDialogContent().getButtons().getComponent(0));
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRomOpened1() {
        WindowAiki window_ = newGame();
        GamesPk.appendPkGameDetailContent(GamesPk.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
        prepareFightTask(window_);
        prepareWebTask(window_);
        window_.getBattle().getBattle().getRenderDataFight().getCommonFrame().setVisible(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getZipLoad());
        assertTrue(window_.getCommonFrame().isVisible());
        window_.getCore().getAikiFactory().getTaskNav().attendreResultat();
        window_.setPreparedDataWebTask(window_.getCore().getAikiFactory().getTaskNav());
        window_.getRenderDataWeb().getSession().setNavCore(new NavigationCore());
        window_.getDataWeb().getActionListeners().get(0).action();
        new LoadGame(window_,new ConcreteInteger()).run();
        new OpeningGame(window_,new ConcreteInteger()).run();
        window_.getCore().getAikiFactory().setPreparedPkNetTask(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),new NatNavigation()));
        window_.setPreparedPkNetTask(window_.getPreparedPkNetTask());
        window_.setPreparedPkNetTask(window_.getCore().getAikiFactory().getPreparedPkNetTask());
        window_.setTileRender(new DefTileRender());
    }
    @Test
    public void menuRomOpened2() {
        WindowAiki window_ = newGame();
        prepareFightTask(window_);
        prepareWebTask(window_);
        window_.getRenderDataWeb().getCommonFrame().setVisible(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getZipLoad());
        assertTrue(window_.getCommonFrame().isVisible());
    }
    private void save(WindowAiki _win) {
        Game g_ = game(initDb());
        _win.getFacade().setGame(g_);
        _win.setSavedGame(false);
    }

    public static void updateBase(TranslationsLg _en) {
        StringMap<TranslationsFile> en_ = FileDialog.initAppliTr(_en).getMapping();
        en_.addEntry(FileDialog.FILE_DIAL, MessagesFileDialog.en());
        en_.addEntry(ConfirmDialog.CONFIRM, MessagesConfirmDialog.en());
        en_.addEntry(FolderOpenDialog.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.en());
        en_.addEntry(FileOpenDialog.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        en_.addEntry(FileSaveDialog.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        en_.addEntry(FileTable.FILE_TAB,MessagesFileTable.en());
    }

    private void run(CreateMainWindowAiki _runner) {
        _runner.run();
        _runner.getWindow().getFacade().setSexList(new MockLSexList());
    }

    private Game game(DataBase _d) {
        Game g_ = new Game(_d);
        g_.initUserInteract("_", Sex.NO, g_.getDifficulty(), _d);
        return g_;
    }

    public static DataBase initDb() {
        DataBase data_ = coreDataBaseValid();
        data_.sortEndRound();
        data_.getMap().setMiniMap(new MiniMapCoordsList());
        data_.getMap().setUnlockedCity(NULL_REF);
        data_.getMap().setSideLength(1);
        data_.addLink(LINK, new int[][]{new int[]{-255}});
        data_.setImageTmHm(new int[][]{new int[]{-800}});
        data_.setAnimAbsorb(new int[][]{new int[]{-700}});
        data_.setStorage(new int[][]{new int[]{-3}});
        data_.addImage(BUILDING, new int[][]{new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985}});
        data_.addImage(NOTHING, new int[][]{new int[]{-16777216,-16777216},new int[]{-16777216,-16777216}});
        data_.addImage(ROAD, new int[][]{new int[]{-7369361,-7369361},new int[]{-7369361,-7369361}});
        data_.addImage(WATER, new int[][]{new int[]{-16776961,-16776961},new int[]{-16776961,-16776961}});
        data_.addPerson(TRAINER, new int[][]{new int[]{-18000}});
        data_.addPerson(PERSON, new int[][]{new int[]{-1800}});
        data_.addPerson(TRAINER_ONE, new int[][]{new int[]{-19000}});
        data_.addPerson(TRAINER_TWO, new int[][]{new int[]{-19008}});
        data_.addPerson(ALLY, new int[][]{new int[]{-19508}});
        data_.addPerson(GERANT, new int[][]{new int[]{-20508}});
        data_.addTrainerImage(TRAINER, new int[][]{new int[]{-18000}});
        data_.addTrainerImage(TRAINER_ONE, new int[][]{new int[]{-19000}});
        data_.addTrainerImage(TRAINER_TWO, new int[][]{new int[]{-19008}});
        data_.addTrainerImage(ALLY, new int[][]{new int[]{-19508}});
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.DOWN, Sex.NO), new int[][]{new int[]{1}});
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.UP,Sex.NO), new int[][]{new int[]{1}});
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.LEFT,Sex.NO), new int[][]{new int[]{1}});
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.RIGHT,Sex.NO), new int[][]{new int[]{1}});
        data_.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Sex.NO), new int[][]{new int[]{1}});
        data_.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Sex.NO), new int[][]{new int[]{1}});
        data_.getMiniPk().addEntry(PIKACHU,new int[][]{new int[]{2}});
        data_.getMaxiPkBack().addEntry(PIKACHU,new int[][]{new int[]{2}});
        data_.getMaxiPkFront().addEntry(PIKACHU,new int[][]{new int[]{2}});
        data_.getMiniItems().addEntry(POKE_BALL,new int[][]{new int[]{2}});
        data_.getAnimStatis().addEntry(Statistic.ATTACK.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.SPECIAL_ATTACK.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.DEFENSE.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.SPECIAL_DEFENSE.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.SPEED.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.ACCURACY.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.EVASINESS.getStatName(), new int[][]{new int[]{3}});
        data_.getAnimStatis().addEntry(Statistic.CRITICAL_HIT.getStatName(), new int[][]{new int[]{3}});
        data_.getTypesImages().addEntry(ELECTRICK,new int[][]{new int[]{4}});
        data_.getTypesColors().addEntry(ELECTRICK,"1;3;5");
        data_.setEndGameImage(new int[][]{new int[1]});
        initBegin(data_);

        initBlockFirstCity(data_);
        initBuildingsFirstCity(data_);
        initTrainersFirstCity(data_);
        initPokemonCenterFirstCity(data_);

        initLeague(data_);
        initBlockLeague(data_);
        initLeagueTrainers(data_);

        initBlockFirstRoad(data_);
        initFirstRoadAreas(data_);

        data_.getMap().join((short) 0,(short) 2,newPoint(0,0),newPoint(5,0), Direction.LEFT);

        initMiniMap(data_);
        data_.completeVariables();
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getTm().addEntry((short)2,ECLAIR);
        data_.getTmPrice().addEntry((short)2,new LgInt("1"));
        data_.initTypesByTable();
        initTranslations(data_);
        data_.initFamilies();
        return data_;
    }
    public static void initBegin(DataBase _data) {
        DataMap map_ = _data.getMap();
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel((short) 7);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 2, 1));
    }
    public static DataBase coreDataBaseValid() {
        DataBase data_ = new DataBase(DefaultGenerator.oneElt());
        data_.setLanguage(LANGUAGE);
        data_.setLanguages(new StringList(LANGUAGE));
        data_.initializeMembers();
        initConstants(data_);
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKACHU);
        pkData_.setEggGroups(new StringList(data_.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove((short)1,ECLAIR));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate((short) 1);
        pkData_.setHappiness((short) 1);
        pkData_.setHappinessHatch((short) 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
        data_.completeMembers(PIKACHU,pkData_);
        data_.completeMembers(PARATONNERRE,Instances.newAbilityData());
        data_.completeMembers(ECLAIR,learn(Statistic.ATTACK));
        data_.completeMembers(ECLAIR_2, learn(Statistic.SPECIAL_ATTACK));
        data_.completeMembers(ECLAIR_3, def());
        data_.completeMembers(POKE_BALL, Instances.newBall());
        data_.getTableTypes().addEntry(new TypesDuo(ELECTRICK,ELECTRICK),Rate.one());
        data_.setMap(Instances.newDataMap());
        data_.setCombos(Instances.newCombos());
        data_.getConstNum().addEntry(DataBase.STRONG_MOVE,Rate.newRate("90"));
        return data_;
    }

    private static DamagingMoveData learn(Statistic _stat) {
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        EffectDamage eff_ = Instances.newEffectDamage();
        eff_.setPower("100");
        eff_.setFail("");
        eff_.setTargetChoice(TargetChoice.TOUS_ADV);
        eff_.setStatisAtt(_stat);
        move_.getEffects().add(eff_);
        move_.setTargetChoice(TargetChoice.TOUS_ADV);
        move_.setTypes(new StringList(ELECTRICK));
        move_.setCategory("SPEC");
        move_.setAccuracy("1");
        move_.setPp((short) 1);
        return move_;
    }

    public static void statBase(PokemonData _pk) {
        _pk.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv((short)1,(short)0));
        _pk.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv((short)1,(short)0));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv((short)1,(short)0));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv((short)1,(short)0));
        _pk.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv((short)1,(short)0));
        _pk.getStatistics().addEntry(Statistic.HP,new StatBaseEv((short)1,(short)0));
    }

    public static void initConstants(DataBase _data) {
        _data.addConstNumTest(DataBase.MAX_EV, new Rate(20));
        _data.addConstNumTest(DataBase.MAX_IV, new Rate(31));
        _data.addConstNumTest(DataBase.DEF_MAX_ATT, new Rate(4));
        _data.addConstNumTest(DataBase.DEF_PKEQ, new Rate(6));
        _data.addConstNumTest(DataBase.ARGENT, new Rate(3000));
        _data.addConstNumTest(DataBase.NIVEAU_PK_ECLOSION, new Rate(1));
        _data.addConstNumTest(DataBase.NIVEAU_PK_MAX, new Rate(100));
        _data.addConstNumTest(DataBase.EVO_BONHEUR, new Rate(110));
        _data.addConstNumTest(DataBase.MAX_BONHEUR, new Rate(170));
        _data.addConstNumTest(DataBase.GAIN_BONHEUR_NIV, new Rate(2));
        _data.addConstNumTest(DataBase.PAS_NECES_INCREMENT_BONHEUR, new Rate(10));
        _data.addConstNumTest(DataBase.PP_MAX, new Rate(80));
        _data.addConstNumTest(DataBase.VALEUR_DEF_STATIS, new Rate(0));
        _data.addConstNumTest(DataBase.MAX_BOOST, new Rate(6));
        _data.addConstNumTest(DataBase.MIN_BOOST, new Rate(-6));
        _data.addConstNumTest(DataBase.MIN_HP, new Rate(1));
        _data.addConstNumTest(DataBase.BONUS_BOOST, new Rate("3/2"));
        _data.addConstNumTest(DataBase.MAX_STEPS, new Rate("1024"));
        _data.addConstNumTest(DataBase.MAX_STEPS_SAME_EVO_BASE, new Rate("256"));
        _data.addConstNumTest(DataBase.DEF_BASE_MOVE, new Rate("0"));
        initDefaultConsts(POKE_BALL,
                "1",
                "1",
                "div(2*caracgaucheouvert(VAR__BOOST,0),max(2-VAR__BOOST,1))+div((2+VAR__BOOST)*caracdroiteferme(VAR__BOOST,0),2)",
                "puis(2,VAR__BOOST-4)",
                "1",
                ECLAIR_3,
                "METAMORPH", _data);
    }

    public static void initDefaultConsts(String _ballDef, String _rateCatching,
                                         String _rateFleeing, String _rateBoost,
                                         String _rateBoostCriticalHit, String _damageFormula,
                                         String _defMove, String _defaultEggGoup, DataBase _db) {
        _db.setBallDef(_ballDef);
        _db.setRateCatching(_rateCatching);
        _db.setRateFleeing(_rateFleeing);
        _db.setRateBoost(_rateBoost);
        _db.setRateBoostCriticalHit(_rateBoostCriticalHit);
        _db.setDamageFormula(_damageFormula);
        _db.setDefMove(_defMove);
        _db.setDefaultEggGroup(_defaultEggGoup);
        _db.setDefCategory("_");
    }
    public static void initExpPoints(DataBase _data) {
        _data.getExpGrowth().addEntry(ExpType.E,"2*VAR__NIVEAU");
        _data.getExpGrowth().addEntry(ExpType.L,"5/4*puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().addEntry(ExpType.M,"puis(VAR__NIVEAU,3)");
        _data.getExpGrowth().addEntry(ExpType.P,"puis(VAR__NIVEAU,2)");
        _data.getExpGrowth().addEntry(ExpType.F,"VAR__NIVEAU");
        _data.getExpGrowth().addEntry(ExpType.R,"4/5*puis(VAR__NIVEAU,3)");
        _data.getRates().addEntry(DifficultyWinPointsFight.TRES_FACILE, "4");
        _data.getRates().addEntry(DifficultyWinPointsFight.FACILE, "2");
        _data.getRates().addEntry(DifficultyWinPointsFight.DIFFICILE, "1");
        _data.getRates().addEntry(DifficultyWinPointsFight.TRES_DIFFICILE, "1/2");
    }

    public static void initTranslations(DataBase _data) {
        IdMap<SelectedBoolean,String> bools_;
        bools_ = new IdMap<SelectedBoolean,String>();
        bools_.addEntry(SelectedBoolean.YES, SelectedBoolean.YES.getBoolName());
        bools_.addEntry(SelectedBoolean.NO, SelectedBoolean.NO.getBoolName());
        bools_.addEntry(SelectedBoolean.YES_AND_NO, SelectedBoolean.YES_AND_NO.getBoolName());
        _data.getTranslatedBooleans().addEntry(LANGUAGE, bools_);
        IdMap<DifficultyWinPointsFight,String> diffsWin_;
        diffsWin_ = new IdMap<DifficultyWinPointsFight,String>();
        diffsWin_.addEntry(DifficultyWinPointsFight.TRES_FACILE, DifficultyWinPointsFight.TRES_FACILE.getWinName());
        diffsWin_.addEntry(DifficultyWinPointsFight.FACILE, DifficultyWinPointsFight.FACILE.getWinName());
        diffsWin_.addEntry(DifficultyWinPointsFight.DIFFICILE, DifficultyWinPointsFight.DIFFICILE.getWinName());
        diffsWin_.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE, DifficultyWinPointsFight.TRES_DIFFICILE.getWinName());
        _data.getTranslatedDiffWinPts().addEntry(LANGUAGE, diffsWin_);
        IdMap<DifficultyModelLaw,String> diffsLaw_;
        diffsLaw_ = new IdMap<DifficultyModelLaw,String>();
        diffsLaw_.addEntry(DifficultyModelLaw.CONSTANT_MIN, DifficultyModelLaw.CONSTANT_MIN.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.CROISSANT, DifficultyModelLaw.CROISSANT.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.UNIFORME, DifficultyModelLaw.UNIFORME.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.DECROISSANT, DifficultyModelLaw.DECROISSANT.getModelName());
        diffsLaw_.addEntry(DifficultyModelLaw.CONSTANT_MAX, DifficultyModelLaw.CONSTANT_MAX.getModelName());
        _data.getTranslatedDiffModelLaw().addEntry(LANGUAGE, diffsLaw_);
        IdMap<EnvironmentType,String> envs_;
        envs_ = new IdMap<EnvironmentType,String>();
        envs_.addEntry(EnvironmentType.NOTHING, EnvironmentType.NOTHING.getEnvName());
        envs_.addEntry(EnvironmentType.ROAD, EnvironmentType.ROAD.getEnvName());
        envs_.addEntry(EnvironmentType.DESERT, EnvironmentType.DESERT.getEnvName());
        envs_.addEntry(EnvironmentType.ROCK, EnvironmentType.ROCK.getEnvName());
        envs_.addEntry(EnvironmentType.BUILDING, EnvironmentType.BUILDING.getEnvName());
        envs_.addEntry(EnvironmentType.WATER, EnvironmentType.WATER.getEnvName());
        envs_.addEntry(EnvironmentType.GRASS, EnvironmentType.GRASS.getEnvName());
        envs_.addEntry(EnvironmentType.SNOW, EnvironmentType.SNOW.getEnvName());
        envs_.addEntry(EnvironmentType.ICE, EnvironmentType.ICE.getEnvName());
        _data.getTranslatedEnvironment().addEntry(LANGUAGE, envs_);
        IdMap<Gender,String> genders_;
        genders_ = new IdMap<Gender,String>();
        genders_.addEntry(Gender.FEMALE, Gender.FEMALE.getGenderName());
        genders_.addEntry(Gender.NO_GENDER, Gender.NO_GENDER.getGenderName());
        genders_.addEntry(Gender.MALE, Gender.MALE.getGenderName());
        _data.getTranslatedGenders().addEntry(LANGUAGE, genders_);
        IdMap<Statistic,String> statistics_;
        statistics_ = new IdMap<Statistic,String>();
        statistics_.addEntry(Statistic.ATTACK, Statistic.ATTACK.getStatName());
        statistics_.addEntry(Statistic.DEFENSE, Statistic.DEFENSE.getStatName());
        statistics_.addEntry(Statistic.SPECIAL_ATTACK, Statistic.SPECIAL_ATTACK.getStatName());
        statistics_.addEntry(Statistic.SPECIAL_DEFENSE, Statistic.SPECIAL_DEFENSE.getStatName());
        statistics_.addEntry(Statistic.SPEED, Statistic.SPEED.getStatName());
        statistics_.addEntry(Statistic.CRITICAL_HIT, Statistic.CRITICAL_HIT.getStatName());
        statistics_.addEntry(Statistic.ACCURACY, Statistic.ACCURACY.getStatName());
        statistics_.addEntry(Statistic.EVASINESS, Statistic.EVASINESS.getStatName());
        statistics_.addEntry(Statistic.PV_RESTANTS, Statistic.PV_RESTANTS.getStatName());
        statistics_.addEntry(Statistic.HP, Statistic.HP.getStatName());
        _data.getTranslatedStatistics().addEntry(LANGUAGE, statistics_);
        IdMap<TargetChoice,String> targets_;
        targets_ = new IdMap<TargetChoice,String>();
        targets_.addEntry(TargetChoice.ADJ_ADV, TargetChoice.ADJ_ADV.getTargetName());
        targets_.addEntry(TargetChoice.ADJ_MULT, TargetChoice.ADJ_MULT.getTargetName());
        targets_.addEntry(TargetChoice.ADJ_UNIQ, TargetChoice.ADJ_UNIQ.getTargetName());
        targets_.addEntry(TargetChoice.ALLIE, TargetChoice.ALLIE.getTargetName());
        targets_.addEntry(TargetChoice.ALLIES, TargetChoice.ALLIES.getTargetName());
        targets_.addEntry(TargetChoice.ANY_FOE, TargetChoice.ANY_FOE.getTargetName());
        targets_.addEntry(TargetChoice.AUTRE_UNIQ, TargetChoice.AUTRE_UNIQ.getTargetName());
        targets_.addEntry(TargetChoice.GLOBALE, TargetChoice.GLOBALE.getTargetName());
        targets_.addEntry(TargetChoice.LANCEUR, TargetChoice.LANCEUR.getTargetName());
        targets_.addEntry(TargetChoice.PSEUDO_GLOBALE, TargetChoice.PSEUDO_GLOBALE.getTargetName());
        targets_.addEntry(TargetChoice.TOUS_ADV, TargetChoice.TOUS_ADV.getTargetName());
        targets_.addEntry(TargetChoice.UNIQUE_IMPORTE, TargetChoice.UNIQUE_IMPORTE.getTargetName());
        targets_.addEntry(TargetChoice.NOTHING, TargetChoice.NOTHING.getTargetName());
        _data.getTranslatedTargets().addEntry(LANGUAGE, targets_);
        StringMap<String> words_ = DataBase.basicTranslation(_data.getPokedex().getKeys());
        _data.getTranslatedPokemon().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getMoves().getKeys());
        _data.getTranslatedMoves().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getItems().getKeys());
        _data.getTranslatedItems().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getAbilities().getKeys());
        _data.getTranslatedAbilities().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getStatus().getKeys());
        _data.getTranslatedStatus().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslationItemsType(_data);
        _data.getTranslatedClassesDescriptions().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getTypes());
        _data.getTranslatedTypes().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(_data.getAllCategories());
        _data.getTranslatedCategories().addEntry(LANGUAGE, words_);
        words_ = DataBase.basicTranslation(EvolvedMathFactory.getFunctions());
        _data.getTranslatedFctMath().addEntry(LANGUAGE, words_);
        StringMap<String> litteral_ = new StringMap<String>();
        litteral_.addEntry("NIVEAU", StringUtil.concat("level",TAB,"l",TAB,"The level of the Pokemon"));
        litteral_.addEntry("BOOST", StringUtil.concat("boost",TAB,"b",TAB,"The boost of the Pokemon"));
        _data.getLitterals().addEntry(LANGUAGE,litteral_);
    }

    public static void initRandomLaws(DataBase _data) {
        MonteCarloNumber monteCarloNumber_;
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("14"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("8"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("5"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("11"));
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("15"));
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("12"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("2"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("7"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("10"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("9"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("13"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("6"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("4"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("3"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("16"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CROISSANT,new LawNumber(monteCarloNumber_,(short)4));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.UNIFORME,new LawNumber(monteCarloNumber_,(short)3));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("24/25"),new LgInt("5"));
        monteCarloNumber_.addQuickEvent(new Rate("19/20"),new LgInt("6"));
        monteCarloNumber_.addQuickEvent(new Rate("9/10"),new LgInt("11"));
        monteCarloNumber_.addQuickEvent(new Rate("99/100"),new LgInt("2"));
        monteCarloNumber_.addQuickEvent(new Rate("43/50"),new LgInt("15"));
        monteCarloNumber_.addQuickEvent(new Rate("87/100"),new LgInt("14"));
        monteCarloNumber_.addQuickEvent(new Rate("23/25"),new LgInt("9"));
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        monteCarloNumber_.addQuickEvent(new Rate("93/100"),new LgInt("8"));
        monteCarloNumber_.addQuickEvent(new Rate("91/100"),new LgInt("10"));
        monteCarloNumber_.addQuickEvent(new Rate("47/50"),new LgInt("7"));
        monteCarloNumber_.addQuickEvent(new Rate("22/25"),new LgInt("13"));
        monteCarloNumber_.addQuickEvent(new Rate("97/100"),new LgInt("4"));
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("16"));
        monteCarloNumber_.addQuickEvent(new Rate("49/50"),new LgInt("3"));
        monteCarloNumber_.addQuickEvent(new Rate("89/100"),new LgInt("12"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.DECROISSANT,new LawNumber(monteCarloNumber_,(short)2));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(monteCarloNumber_,(short)1));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(monteCarloNumber_,(short)5));
    }

    public static void initMiniMap(DataBase _data) {
        _data.getMiniMap().addEntry(MINI, new int[][]{new int[]{118}});
        _data.getMiniMap().addEntry(MINI1, new int[][]{new int[]{218}});
        _data.getMiniMap().addEntry(MINI2, new int[][]{new int[]{112}});
        _data.getMiniMap().addEntry(MINI3, new int[][]{new int[]{200}});
        _data.getMiniMap().addEntry(MINI4, new int[][]{new int[]{128}});
        _data.getMiniMap().addEntry(MINI5, new int[][]{new int[]{211}});
        _data.getMiniMap().addEntry(MINI6, new int[][]{new int[]{221}});
        DataMap map_ = _data.getMap();
        TileMiniMap tile_;
        map_.setMiniMap(new MiniMapCoordsList());
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace((short) 0);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 0), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace((short) 1);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI2);
        tile_.setPlace((short) 2);
        map_.getMiniMap().addEntry(new MiniMapCoords((short) 0,(short) 2), tile_);
        map_.setUnlockedCity(MINI5);
    }

    public static void initBlockFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addCity("__");
        City city_ = (City) map_.getPlaces().last();
        Block block_;
        block_ = newBlock(2, 2,EnvironmentType.NOTHING,NOTHING,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.NOTHING,NOTHING,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.WATER,WATER,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.WATER,WATER,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newBlock(2, 2,EnvironmentType.WATER,WATER,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newBlock(2, 2,EnvironmentType.WATER,WATER,-1);
        city_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    public static void initBuildingsFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().last();
        Gym gym_;
        gym_ = new Gym();
        gym_.setImageFileName(LINK);
        gym_.setLevel(new LevelIndoorGym());
        gym_.setExitCity(newPoint(4,8));
        gym_.getLevel().setBlocks(new PointsBlock());
        gym_.getLevel().getBlocks().addEntry(newPoint(0,0), newBlock(9, 9,EnvironmentType.BUILDING,BUILDING,-1));
        gym_.getIndoor().setGymTrainers(new PointsGymTrainer());
        city_.setBuildings(new PointsBuilding());
        city_.getBuildings().addEntry(newPoint(5, 1), gym_);
        PokemonCenter pkCenter_;
        pkCenter_ = new PokemonCenter();
        pkCenter_.setImageFileName(LINK);
        pkCenter_.setLevel(new LevelIndoorPokemonCenter());
        pkCenter_.setExitCity(newPoint(4,8));
        pkCenter_.getLevel().setBlocks(new PointsBlock());
        pkCenter_.getLevel().getBlocks().addEntry(newPoint(0,0), newBlock(9, 9,EnvironmentType.BUILDING,BUILDING,-1));
        pkCenter_.getIndoor().setStorageCoords(newPoint(4, 0));
        pkCenter_.getIndoor().setGerants(new PointsPerson());
        city_.getBuildings().addEntry(newPoint(1, 1), pkCenter_);
    }

    public static void initTrainersFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().last();
        Gym gym_;
        GymTrainer gymTrainer_;
        GymLeader gymLeader_;
        gym_ = (Gym) city_.getBuildings().getVal(newPoint(5, 1));
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 1), new StringList(ECLAIR)));
        gymTrainer_ = nvGymTrainer((short) 200, (byte) 1, team_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(1, 7), gymTrainer_);
        CustList<PkTrainer> teamTwo_ = new CustList<PkTrainer>();
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 1), new StringList(ECLAIR)));
        gymTrainer_ = nvGymTrainer((short) 200, (byte) 1, teamTwo_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(7, 7), gymTrainer_);
        gym_.getIndoor().setGymLeaderCoords(newPoint(4, 1));
        CustList<PkTrainer> teamThree_ = new CustList<PkTrainer>();
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 1), new StringList(ECLAIR)));
        gymLeader_ = nvGymLeader((short) 500, (byte) 1, teamThree_);
        gymLeader_.setName("__");
        gym_.getIndoor().setGymLeader(gymLeader_);
    }

    public static void initPokemonCenterFirstCity(DataBase _data) {
        DataMap map_ = _data.getMap();
        City city_ = (City) map_.getPlaces().last();
        PokemonCenter pk_;
        pk_ = (PokemonCenter) city_.getBuildings().getVal(newPoint(1, 1));
        pk_.getIndoor().getGerants().addEntry(newPoint(0, 4), newGerantPokemon(GeranceType.HEAL));
        Seller seller_;
        seller_ = new Seller();
        seller_.setItems(new StringList(POKE_BALL));
        seller_.setTm(Shorts.newList());
        seller_.setSell(SellType.ITEM);
        seller_.setImageMiniFileName(GERANT);
        pk_.getIndoor().getGerants().addEntry(newPoint(8, 4), seller_);
        seller_ = new Seller();
        seller_.setItems(new StringList());
        seller_.setTm(Shorts.newList());
        seller_.setSell(SellType.MOVE);
        seller_.setImageMiniFileName(GERANT);
        pk_.getIndoor().getGerants().addEntry(newPoint(8, 6), seller_);
    }
    public static void initLeague(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addLeague(LINK, newCoords(0, 0, 2, 0));
        League league_ =(League) map_.getPlaces().last();
        league_.getRooms().last().setFileName(LINK);
        league_.setName("__");
    }

    public static void initBlockLeague(DataBase _data) {
        DataMap map_ = _data.getMap();
        League road_ = (League) map_.getPlaces().last();
        LevelLeague level_;
        Block block_;
        level_ = (LevelLeague) road_.getLevelsMap().getVal((byte) 0);
        block_ = newBlock(9, 9,EnvironmentType.BUILDING,BUILDING,-1);
        level_.getBlocks().addEntry(newPoint(0,0), block_);
        road_.setBegin(newPoint(4,8));
        level_ = (LevelLeague) road_.getLevelsMap().getVal((byte) 0);
        level_.setAccessPoint(newPoint(4, 0));
    }

    public static void initLeagueTrainers(DataBase _data) {
        DataMap map_ = _data.getMap();
        League league_ = (League) map_.getPlaces().last();
        league_.getRooms().get(0).setTrainerCoords(newPoint(4, 4));
        league_.getRooms().get(0).setTrainer(trainerLeagueOne());
        league_.getRooms().get(0).getTrainer().setName("__");
        map_.getAccessCondition().addEntry(newCoords(0, 0, 2, 0), Condition.newList(newCoords(0, 0, 5, 1, 4, 1)));
    }

    public static void initBlockFirstRoad(DataBase _data) {
        DataMap map_ = _data.getMap();
        map_.addRoad();
        Road road_ = (Road) map_.getPlaces().last();
        road_.setName("___");
        Block block_;
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(2,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(4,0), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(2,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(4,2), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(0,4), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(2,4), block_);
        block_ = newBlock(2, 2,EnvironmentType.ROAD,ROAD, 0);
        road_.getLevel().getBlocks().addEntry(newPoint(4,4), block_);
    }

    public static void initFirstRoadAreas(DataBase _data) {
        DataMap map_ = _data.getMap();
        Road road_ = (Road) map_.getPlaces().last();
        AreaApparition area_;
        WildPk wild_;
        area_ = new AreaApparition();
        area_.setAvgNbSteps((short) 1);
        area_.setMultFight((byte) 1);
        wild_ = new WildPk();
        wild_.setName(PIKACHU);
        wild_.setLevel((short) 1);
        wild_.setAbility(PARATONNERRE);
        wild_.setGender(Gender.NO_GENDER);
        area_.setWildPokemon(new CustList<WildPk>());
        area_.setWildPokemonFishing(new CustList<WildPk>());
        area_.getWildPokemon().add(wild_);
        road_.getLevelRoad().getWildPokemonAreas().add(area_);
    }

    private static TrainerLeague trainerLeagueOne() {
        CustList<PkTrainer> team_ = new CustList<PkTrainer>();
        team_.add(toPkTrainer(new NameLevel(PIKACHU, 35), new StringList(ECLAIR)));
        return nvTrainerLeague((short) 2000, (byte) 1, team_);
    }
    private static GymLeader nvGymLeader(short _reward, byte _mult, CustList<PkTrainer> _team) {
        GymLeader gymLeader_ = new GymLeader();
        gymLeader_.setTeam(_team);
        gymLeader_.setReward(_reward);
        gymLeader_.setMultiplicityFight(_mult);
        gymLeader_.setName(NULL_REF);
        gymLeader_.setTm((short) 2);
        gymLeader_.setImageMiniFileName(TRAINER);
        gymLeader_.setImageMaxiFileName(TRAINER);
        return gymLeader_;
    }

    private static GymTrainer nvGymTrainer(short _reward, byte _mult, CustList<PkTrainer> _team) {
        GymTrainer gymTrainer_ = new GymTrainer();
        gymTrainer_.setTeam(_team);
        gymTrainer_.setReward(_reward);
        gymTrainer_.setMultiplicityFight(_mult);
        gymTrainer_.setImageMiniFileName(TRAINER);
        gymTrainer_.setImageMaxiFileName(TRAINER);
        return gymTrainer_;
    }

    private static TrainerLeague nvTrainerLeague(short _reward, byte _mult, CustList<PkTrainer> _team) {
        TrainerLeague trainerLeague_ = new TrainerLeague();
        trainerLeague_.setTeam(_team);
        trainerLeague_.setReward(_reward);
        trainerLeague_.setMultiplicityFight(_mult);
        trainerLeague_.setImageMiniFileName(TRAINER);
        trainerLeague_.setImageMaxiFileName(TRAINER);
        trainerLeague_.setName(NULL_REF);
        return trainerLeague_;
    }
//
//    private static DualFight nvDualFight(short _reward, CustList<PkTrainer> _teamAl, CustList<PkTrainer> _team) {
//        DualFight dual_ = new DualFight();
//        Ally ally_ = new Ally();
//        ally_.setTeam(_teamAl);
//        TempTrainer trainer_ = new TempTrainer();
//        trainer_.setTeam(_team);
//        trainer_.setReward(_reward);
//        trainer_.setImageMaxiFileName(TRAINER_ONE);
//        trainer_.setImageMiniFileName(TRAINER_ONE);
//        trainer_.setImageMiniSecondTrainerFileName(TRAINER_TWO);
//        dual_.setAlly(ally_);
//        dual_.setFoeTrainer(trainer_);
//        dual_.setNames(new StringList());
//        return dual_;
//    }
//
//    private static PokemonTeam nvTeam(short _reward, CustList<PkTrainer> _team) {
//        PokemonTeam teamReward_ = new PokemonTeam();
//        teamReward_.setTeam(_team);
//        teamReward_.setReward(_reward);
//        return teamReward_;
//    }
//
//    private static TrainerMultiFights newTrainer(
//            CustList<PokemonTeam> _teams, int _mult) {
//        TrainerMultiFights trainer_ = new TrainerMultiFights();
//        trainer_.setTeamsRewards(_teams);
//        trainer_.setMultiplicityFight((byte) _mult);
//        trainer_.setImageMiniFileName(TRAINER);
//        trainer_.setImageMaxiFileName(TRAINER);
//        return trainer_;
//    }

//    private static GerantPokemon newGerantPokemon(GeranceType _gerance) {
//        GerantPokemon gerant_ = new GerantPokemon();
//        gerant_.setGerance(_gerance);
//        gerant_.setImageMiniFileName(GERANT);
//        return gerant_;
//    }
//
//    private static DealerItem newDealerObject(StringList _obj, Shorts _tm) {
//        DealerItem dealer_ = new DealerItem();
//        dealer_.setItems(new StringList(_obj));
//        dealer_.setTechnicalMoves(_tm);
//        dealer_.setImageMiniFileName(PERSON);
//        return dealer_;
//    }
//
//    private static Block newBlock(int _h, int _w, EnvironmentType _type, String _tileFileName, int _index) {
//        //black
//        Block block_;
//        block_ = new Block();
//        block_.setHeight((short) _h);
//        block_.setWidth((short) _w);
//        block_.setType(_type);
//        block_.setTileFileName(_tileFileName);
//        block_.setIndexApparition((short) _index);
//        return block_;
//    }

//    private static LevelPoint newLevelPoint(int _level, int _x, int _y) {
//        LevelPoint begin_ = new LevelPoint();
//        begin_.setLevelIndex((byte) _level);
//        begin_.setPoint(newPoint(_x, _y));
//        return begin_;
//    }
//
//    private static Coords newCoords(int _place, int _level, int _x, int _y) {
//        Coords begin_ = new Coords();
//        begin_.setNumberPlace((short) _place);
//        begin_.setLevel(new LevelPoint());
//        begin_.getLevel().setLevelIndex((byte) _level);
//        begin_.getLevel().setPoint(newPoint(_x, _y));
//        return begin_;
//    }
//
//    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
//        Coords begin_ = new Coords();
//        begin_.setNumberPlace((short) _place);
//        begin_.affectInside(newPoint(_xi, _yi));
//        begin_.setLevel(new LevelPoint());
//        begin_.getLevel().setLevelIndex((byte) _level);
//        begin_.getLevel().setPoint(newPoint(_x, _y));
//        return begin_;
//    }

    private static PkTrainer toPkTrainer(NameLevel _nameLevel, StringList _moves) {
        PkTrainer pk_ = new PkTrainer();
        pk_.setName(_nameLevel.getName());
        pk_.setLevel(_nameLevel.getLevel());
        pk_.setAbility(PARATONNERRE);
        pk_.setItem(NULL_REF);
        pk_.setMoves(_moves);
        return pk_;
    }

//    private static Point newPoint(int _x, int _y) {
//        return new Point((short)_x, (short)_y);
//    }
    private static CreateMainWindowAiki launcher(MockProgramInfos _pr, AikiFactory _fact) {
        return launcher(_pr, _fact, DocumentReaderAikiCoreUtil.newLoadingGame());
    }

    private static CreateMainWindowAiki launcher(MockProgramInfos _pr, AikiFactory _fact, LoadingGame _conf) {
        TranslationsLg en_ = _pr.lg(EN);
        TranslationsAppli app_ = GamesPk.initAppliTr(en_);
        windowPk(app_);
        return new CreateMainWindowAiki(_conf, new StringList(), StreamFolderFile.getCurrentPath(_pr.getFileCoreStream()), new TopLeftFrame(), _pr.getCompoFactory().newMenuItem(), _pr, _fact);
    }

    private static CreateMainWindowAiki launcher(MockProgramInfos _pr, AikiFactory _fact, String _file) {
        return launcher(_pr, _fact, _file, DocumentReaderAikiCoreUtil.newLoadingGame());
    }

    private static CreateMainWindowAiki launcher(MockProgramInfos _pr, AikiFactory _fact, String _file, LoadingGame _conf) {
        StringList path_ = new StringList();
        path_.add(_file);
        TranslationsLg en_ = _pr.lg(EN);
        TranslationsAppli app_ = GamesPk.initAppliTr(en_);
        windowPk(app_);
        return new CreateMainWindowAiki(_conf, path_, StreamFolderFile.getCurrentPath(_pr.getFileCoreStream()), new TopLeftFrame(), _pr.getCompoFactory().newMenuItem(), _pr, _fact);
    }
}
