package aiki.gui;

import aiki.beans.BeanRenderWithAppName;
import aiki.beans.CommonBean;
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
import aiki.game.UsesOfMove;
import aiki.game.fight.*;
import aiki.game.fight.actions.ActionMove;
import aiki.game.fight.enums.FightState;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.ListActivityOfMove;
import aiki.game.fight.util.MoveTarget;
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
import code.gui.*;
import code.gui.document.*;
import code.gui.files.*;
import code.maths.*;
import code.maths.montecarlo.*;
import code.maths.litteral.*;
import code.mock.*;
import code.scripts.pages.aiki.MessagesDataIndex;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.NavigationCore;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        MessagesPkGame.appendPkGameDetailContent(MessagesPkGame.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
//        prepareFightTask(window_);
        window_.getFrames().currentLg().getMapping().addEntry(MessagesPkBean.APP_BEAN_FIGHT,MessagesPkBean.enFight());
//        ((MockProgramInfos)window_.getFrames()).lg(FR).getMapping().addEntry(MessagesPkBean.APP_BEAN_FIGHT,MessagesPkBean.frFight());
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().getTranslations());
        updateBase(window_.getFrames().currentLg());
        DataBase db_ = initDb();
        db_.getMaxiPkBack().addEntry(PIKACHU,instance(new int[][]{new int[1]}));
        db_.getMaxiPkFront().addEntry(PIKACHU,instance(new int[][]{new int[1]}));
        db_.getMiniPk().addEntry(PIKACHU,instance(new int[][]{new int[1]}));
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
        db_.completeQuickMembers("_1", damage());
        db_.completeQuickMembers("_2", damage());
        db_.completeQuickMembers("_3", damage());
        db_.getTranslatedMoves().getVal(window_.getFrames().getLanguage()).addEntry("_1","_1");
        db_.getTranslatedMoves().getVal(window_.getFrames().getLanguage()).addEntry("_2","_2");
        db_.getTranslatedMoves().getVal(window_.getFrames().getLanguage()).addEntry("_3","_3");
        db_.getTranslatedCategories().getVal(window_.getFrames().getLanguage()).addEntry("","SPEC_");
        db_.getTranslatedStatus().getVal(window_.getFrames().getLanguage()).addEntry("_4","_4");
        ActivityOfMove a1_ = new ActivityOfMove();
        a1_.setEnabled(true);
        window_.getFacade().getFight().getUserTeam().getEnabledMoves().addEntry("_1", a1_);
        ActivityOfMove a2_ = new ActivityOfMove();
        a2_.setEnabled(false);
        window_.getFacade().getFight().getUserTeam().getEnabledMoves().addEntry("_2", a2_);
        window_.getFacade().getFight().getUserTeam().getEnabledMoves().addEntry("_3",new ActivityOfMove(false));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesByGroup().add(new ListActivityOfMove(new StringList("_1"),new ActivityOfMove()));
        IntMap<StacksOfUses> st_ = new IntMap<StacksOfUses>();
        StacksOfUses sOne_ = new StacksOfUses();
        sOne_.setFirstStacked(true);
        sOne_.setLastStacked(true);
        st_.addEntry(0, sOne_);
        StacksOfUses sTwo_ = new StacksOfUses();
        sTwo_.setFirstStacked(false);
        sTwo_.setLastStacked(false);
        st_.addEntry(1, sTwo_);
        window_.getFacade().getFight().getUserTeam().getHealAfter().addEntry("_1", st_);
        IntMap<Anticipation> ant_ = new IntMap<Anticipation>();
        Anticipation aOne_ = new Anticipation();
        aOne_.setIncrementing(true);
        aOne_.setTargetPosition(TargetCoords.def());
        ant_.addEntry(0, aOne_);
        Anticipation aTwo_ = new Anticipation();
        aTwo_.setIncrementing(false);
        aTwo_.setTargetPosition(TargetCoords.toFoeTarget(0));
        ant_.addEntry(1, aTwo_);
        Anticipation aThree_ = new Anticipation();
        aThree_.setIncrementing(true);
        aThree_.setTargetPosition(TargetCoords.toUserTarget(0));
        ant_.addEntry(2, aThree_);
        window_.getFacade().getFight().getEnabledMoves().addEntry("_1",new ActivityOfMove());
        window_.getFacade().getFight().getEnabledMoves().addEntry("_2",new ActivityOfMove());
        window_.getFacade().getFight().getStillEnabledMoves().addEntry("_1",BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getMovesAnticipation().addEntry("_1", ant_);
        window_.getFacade().getFight().getUserTeam().getNbUsesMoves().addEntry("_1", 0L);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().addEntry("_1", LgInt.zero());
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getStatusRelat().addEntry(new MoveTeamPosition("_4",Fight.toUserFighter(0)),0L);
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getStatusRelat().addEntry(new MoveTeamPosition("_4",Fight.toFoeFighter(0)),1L);
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().setNeedingToRecharge(true);
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getEnabledMovesForAlly().addEntry("_1",BoolVal.FALSE);
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getEnabledMovesForAlly().addEntry("_2",BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getDamageSufferedCategRound().put("SPEC",Rate.one());
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getDamageSufferedCategRound().put("",Rate.one());
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getDamageSufferedCateg().put("SPEC",Rate.one());
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getDamageSufferedCateg().put("",Rate.one());
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getCopiedMoves().addEntry("_1",new CopiedMove("_2",5));
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getTrackingMoves().addEntry(new MoveTeamPosition("_1",Fight.toFoeFighter(0)),new AffectedMove("_2",new ActivityOfMove()));
        window_.getDataBattle().getActionListeners().get(0).action();
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().firstValue()),2);
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().getVal(CommonBean.WEB_FIGHT_HTML_TEAM_HTML)),2);
        int bkGround_ = window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getGroundPlace();
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().setGroundPlace(Fighter.BACK);
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().getVal(CommonBean.WEB_FIGHT_HTML_FIGHTER_HTML)),2);
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().setGroundPlace(bkGround_);
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().getVal(CommonBean.WEB_FIGHT_HTML_FIGHTER_HTML)),2);
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().getVal(CommonBean.WEB_FIGHT_HTML_FIGHTER_HTML)),0);
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().firstValue()),(3));
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().getVal(CommonBean.WEB_FIGHT_HTML_TEAM_HTML)),0);
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().firstValue()),(1));
    }
    @Test
    public void menuGame8() {
        WindowAiki window_ = newGame();
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().getTranslations());
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
    public void menuGame9() {
        WindowAiki window_ = newGame();
        MessagesPkGame.appendPkGameDetailContent(MessagesPkGame.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
//        prepareFightTask(window_);
        window_.getFrames().currentLg().getMapping().addEntry(MessagesPkBean.APP_BEAN_FIGHT,MessagesPkBean.enFight());
//        ((MockProgramInfos)window_.getFrames()).lg(FR).getMapping().addEntry(MessagesPkBean.APP_BEAN_FIGHT,MessagesPkBean.frFight());
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().getTranslations());
        updateBase(window_.getFrames().currentLg());

        coreDataBaseTrainerDualKoPlayer(window_);

        DataBase db_ = window_.getFacade().getData();
        assertTrue(window_.getCommonFrame().isVisible());
        db_.completeQuickMembers("_1", damage());
        db_.completeQuickMembers("_2", damage());
        db_.completeQuickMembers("_3", damage());
        db_.getTranslatedMoves().getVal(window_.getFrames().getLanguage()).addEntry("_1","_1");
        db_.getTranslatedMoves().getVal(window_.getFrames().getLanguage()).addEntry("_2","_2");
        db_.getTranslatedMoves().getVal(window_.getFrames().getLanguage()).addEntry("_3","_3");
        db_.getTranslatedCategories().addEntry(window_.getFrames().getLanguage(),new StringMap<String>());
        db_.getTranslatedCategories().getVal(window_.getFrames().getLanguage()).addEntry("","SPEC_");
        ActivityOfMove a1_ = new ActivityOfMove();
        a1_.setEnabled(true);
        window_.getFacade().getFight().getUserTeam().getEnabledMoves().addEntry("_1", a1_);
        ActivityOfMove a2_ = new ActivityOfMove();
        a2_.setEnabled(false);
        window_.getFacade().getFight().getUserTeam().getEnabledMoves().addEntry("_2", a2_);
        window_.getFacade().getFight().getUserTeam().getEnabledMoves().addEntry("_3",new ActivityOfMove(false));
        window_.getFacade().getFight().getUserTeam().getEnabledMovesByGroup().add(new ListActivityOfMove(new StringList("_1"),new ActivityOfMove()));
        IntMap<StacksOfUses> st_ = new IntMap<StacksOfUses>();
        StacksOfUses sOne_ = new StacksOfUses();
        sOne_.setFirstStacked(true);
        sOne_.setLastStacked(true);
        st_.addEntry(0, sOne_);
        StacksOfUses sTwo_ = new StacksOfUses();
        sTwo_.setFirstStacked(false);
        sTwo_.setLastStacked(false);
        st_.addEntry(1, sTwo_);
        window_.getFacade().getFight().getUserTeam().getHealAfter().addEntry("_1", st_);
        IntMap<Anticipation> ant_ = new IntMap<Anticipation>();
        Anticipation aOne_ = new Anticipation();
        aOne_.setIncrementing(true);
        aOne_.setTargetPosition(TargetCoords.def());
        ant_.addEntry(0, aOne_);
        Anticipation aTwo_ = new Anticipation();
        aTwo_.setIncrementing(false);
        aTwo_.setTargetPosition(TargetCoords.toFoeTarget(0));
        ant_.addEntry(1, aTwo_);
        Anticipation aThree_ = new Anticipation();
        aThree_.setIncrementing(true);
        aThree_.setTargetPosition(TargetCoords.toUserTarget(0));
        ant_.addEntry(2, aThree_);
        window_.getFacade().getFight().getEnabledMoves().addEntry("_1",new ActivityOfMove());
        window_.getFacade().getFight().getEnabledMoves().addEntry("_2",new ActivityOfMove());
        window_.getFacade().getFight().getStillEnabledMoves().addEntry("_1",BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getMovesAnticipation().addEntry("_1", ant_);
        window_.getFacade().getFight().getUserTeam().getNbUsesMoves().addEntry("_1", 0L);
        window_.getFacade().getFight().getUserTeam().getEnabledMovesWhileSendingFoeUses().addEntry("_1", LgInt.zero());
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().setNeedingToRecharge(true);
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getEnabledMovesForAlly().addEntry("_1",BoolVal.FALSE);
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getEnabledMovesForAlly().addEntry("_2",BoolVal.TRUE);
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getDamageSufferedCategRound().put("SPEC",Rate.one());
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getDamageSufferedCategRound().put("",Rate.one());
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getDamageSufferedCateg().put("SPEC",Rate.one());
        window_.getFacade().getFight().getUserTeam().getMembers().firstValue().getDamageSufferedCateg().put("",Rate.one());
        window_.getFacade().getFight().getAllyChoice().addEntry(new MoveTarget("_1",TargetCoords.def()),new MoveTarget("_1",TargetCoords.toFoeTarget(0)));
        window_.getFacade().getFight().getAllyChoice().addEntry(new MoveTarget("_2",TargetCoords.def()),new MoveTarget("_2",TargetCoords.toFoeTarget(1)));
        window_.getFacade().getFight().getAllyChoice().addEntry(new MoveTarget("_3",TargetCoords.def()),new MoveTarget("_3",TargetCoords.def()));
        window_.getFacade().getFight().getFoeTeam().getMembers().firstValue().getMoves().addEntry("_1",new UsesOfMove(10));
        window_.getFacade().getFight().getFoeTeam().getMembers().firstValue().getMoves().addEntry("_2",new UsesOfMove(10));
        window_.getFacade().getFight().getFoeTeam().getMembers().firstValue().getMoves().addEntry("_3",new UsesOfMove(10));

        window_.getDataBattle().getActionListeners().get(0).action();
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().firstValue()),1);
        ActionMove am_ = new ActionMove();
        TargetCoordsList tc_ = new TargetCoordsList();
        tc_.add(TargetCoords.toUserTarget(1));
        am_.setChosenTargets(tc_);
        am_.setFirstChosenMove("_1");
        window_.getFacade().getFight().getFoeTeam().getMembers().firstValue().setAction(am_);
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().getVal(CommonBean.WEB_FIGHT_HTML_FIGHTDETAIL_HTML)),1);
        ActionMove am2_ = new ActionMove();
        TargetCoordsList tc2_ = new TargetCoordsList();
        tc2_.add(TargetCoords.toFoeTarget(1));
        am2_.setChosenTargets(tc2_);
        am2_.setFirstChosenMove("_1");
        window_.getFacade().getFight().getFoeTeam().getMembers().firstValue().setAction(am2_);
        tryClick(builder(window_.getBattle().getBattle().getRenderDataFight().getWrapBeanRender().getRenders().getVal(CommonBean.WEB_FIGHT_HTML_FIGHTDETAIL_HTML)),1);
    }
    @Test
    public void save1() {
        WindowAiki window_ = newGame();
//        window_.getCommonFrame().getFrames().getCounts().put(window_.getApplicationName(),new ConcreteInteger(1));
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().getTranslations());
        updateBase(window_.getFrames().currentLg());
        window_.getFacade().setData(initDb());
        window_.setVisible(false);
        window_.quit();
        assertFalse(window_.getCommonFrame().isVisible());
        GuiBaseUtil.tryToReopen(window_.getApplicationName(),window_.getFrames());
    }
    @Test
    public void save2() {
        WindowAiki window_ = newGame();
//        window_.getCommonFrame().getFrames().getCounts().put(window_.getApplicationName(),new ConcreteInteger(1));
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().getTranslations());
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
//        window_.getCommonFrame().getFrames().getCounts().put(window_.getApplicationName(),new ConcreteInteger(1));
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().getTranslations());
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
//        window_.getCommonFrame().getFrames().getCounts().put(window_.getApplicationName(),new ConcreteInteger(1));
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().getTranslations());
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
//        window_.getCommonFrame().getFrames().getCounts().put(window_.getApplicationName(),new ConcreteInteger(1));
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().getTranslations());
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
        updateBase(window_.getFrames().getTranslations());
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
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
//        prepareWebTask(fact_);
//        fact_.submitNavDataSimu(new PokemonStandardsSample());
        gameTr(pr_);
        WindowAiki window_ = window(pr_, fact_);
        updateBase(window_.getFrames().getTranslations());
        MessagesPkGame.appendPkGameDetailContent(MessagesPkGame.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
//        prepareFightTask(window_);
        TranslationsAppli app_ = new TranslationsAppli();
        TranslationsFile tf_ = new TranslationsFile();
        tf_.getMapping().addEntry(MessagesDataIndex.M_P_15_SOLUTION,"Sol");
        app_.getMapping().addEntry(MessagesPkBean.INDEX, tf_);
        window_.getFrames().currentLg().getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, app_);
//        prepareWebTask(window_);
        window_.getBattle().getBattle().getRenderDataFight().getCommonFrame().setVisible(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getZipLoad());
        assertTrue(window_.getCommonFrame().isVisible());
//        window_.getCore().getAikiFactory().getTaskNav().attendreResultat();
//        window_.setPreparedDataWebTask(window_.getCore().getAikiFactory().getTaskNav());
        window_.getRenderDataWeb().getSession().setNavCore(new NavigationCore());
        window_.getDataWeb().getActionListeners().get(0).action();
        window_.getRenderDataWeb().getWrapBeanRender().getField().setText("");
        tryClick(window_.getRenderDataWeb().getWrapBeanRender().getSearch());
        window_.getRenderDataWeb().getWrapBeanRender().getField().setText("La");
        tryClick(window_.getRenderDataWeb().getWrapBeanRender().getSearch());
        window_.getRenderDataWeb().getWrapBeanRender().getField().setText("Si");
        tryClick(window_.getRenderDataWeb().getWrapBeanRender().getSearch());
        window_.getRenderDataWeb().getWrapBeanRender().getField().setText("Do");
        tryClick(window_.getRenderDataWeb().getWrapBeanRender().getSearch());
        window_.getRenderDataWeb().getWrapBeanRender().getField().setText("Ré");
        tryClick(window_.getRenderDataWeb().getWrapBeanRender().getSearch());
        window_.getRenderDataWeb().getWrapBeanRender().getField().setText("Mi");
        tryClick(window_.getRenderDataWeb().getWrapBeanRender().getSearch());
        window_.getRenderDataWeb().getWrapBeanRender().getField().setText("Fa");
        tryClick(window_.getRenderDataWeb().getWrapBeanRender().getSearch());
        window_.getRenderDataWeb().getWrapBeanRender().getField().setText("Sol");
        tryClick(window_.getRenderDataWeb().getWrapBeanRender().getSearch());
        new LoadGame(window_,new ConcreteInteger()).run();
        new OpeningGame(window_,new ConcreteInteger()).run();
//        window_.getCore().getAikiFactory().submitNavPkNetTask(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),new NatNavigation())));
//        window_.setPreparedPkNetTask(window_.getPreparedPkNetTask());
//        window_.setPreparedPkNetTask(window_.getCore().getAikiFactory().getTaskNavPkNetTask());
        window_.setTileRender(new DefTileRender());
    }
    @Test
    public void menuRomOpened2() {
        WindowAiki window_ = newGame();
//        prepareFightTask(window_);
//        prepareWebTask(window_);
        window_.getRenderDataWeb().getCommonFrame().setVisible(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getZipLoad());
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void menuRomOpened3() {
        MockProgramInfos pr_ = buildListLgs();
        AikiFactory fact_ = pkFact(pr_);
//        prepareWebTask(fact_);
//        fact_.submitNavDataSimu(new PokemonStandardsSample());
        gameTr(pr_);
        WindowAiki window_ = window(pr_, fact_);
        fact_.getFacade().getData().getTranslatedDiffWinPts().tryAdd(window_.getFrames().getLanguage(),new IdMap<DifficultyWinPointsFight, String>());
        fact_.getFacade().getData().getTranslatedDiffModelLaw().tryAdd(window_.getFrames().getLanguage(),new IdMap<DifficultyModelLaw, String>());
        fact_.getFacade().getData().setLawsDamageRate(new IdMap<DifficultyModelLaw, LawNumber>());
        fact_.getFacade().getData().getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(new MonteCarloNumber(),0));
        fact_.getFacade().getData().getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(new MonteCarloNumber(),0));
        updateBase(window_.getFrames().getTranslations());
        TranslationsAppli ta_ = new TranslationsAppli();
        ta_.getMapping().addEntry(MessagesPkBean.DIFFICULTY,new TranslationsFile());
        ta_.getMapping().addEntry(MessagesPkBean.SIMULATION,new TranslationsFile());
        window_.getFrames().currentLg().getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, ta_);
        MessagesPkGame.appendPkGameDetailContent(MessagesPkGame.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
//        prepareFightTask(window_);
//        prepareWebTask(window_);
//        prepareWebTaskReal(window_);
        window_.getBattle().getBattle().getRenderDataFight().getCommonFrame().setVisible(true);
        window_.getCore().getAikiFactory().setDataBaseStream(new MockDataBaseStream());
        updateBase(window_.getFrames().currentLg());
        tryClick(window_.getCore().getZipLoad());
        assertTrue(window_.getCommonFrame().isVisible());
//        window_.getCore().getAikiFactory().getTaskNavDataSimu().attendreResultat();
//        window_.setPreparedDataWebTask(window_.getCore().getAikiFactory().getTaskNav());
        window_.getRenderDataWebSimu().getSession().setNavCore(new NavigationCore());
        window_.getDataWebSimu().getActionListeners().get(0).action();
//        window_.getDataWeb().getActionListeners().get(0).action();
        new LoadGame(window_,new ConcreteInteger()).run();
        new OpeningGame(window_,new ConcreteInteger()).run();
//        window_.getCore().getAikiFactory().submitNavPkNetTask(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),new NatNavigation())));
//        window_.setPreparedPkNetTask(window_.getPreparedPkNetTask());
//        window_.setPreparedPkNetTask(window_.getCore().getAikiFactory().getTaskNavPkNetTask());
        window_.setTileRender(new DefTileRender());
    }
    @Test
    public void menuRomOpened4() {
        WindowAiki window_ = newGame();
//        prepareFightTask(window_);
//        prepareWebTask(window_);
        window_.getRenderDataWebSimu().getCommonFrame().setVisible(true);
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

    public static void updateBase(Translations _en) {
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(_en));
    }
    public static void updateBase(TranslationsLg _en) {
        StringMap<TranslationsFile> en_ = MessagesGuiFct.initAppliTr(_en).getMapping();
        en_.addEntry(MessagesGuiFct.FILE_DIAL, MessagesFileDialog.en());
        en_.addEntry(MessagesGuiFct.CONFIRM, MessagesConfirmDialog.en());
        en_.addEntry(MessagesGuiFct.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        en_.addEntry(MessagesGuiFct.FILE_TAB,MessagesFileTable.en());
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
        data_.addLink(LINK, instance(new int[][]{new int[]{-255}}));
        data_.setImageTmHm(instance(new int[][]{new int[]{-800}}));
        data_.setAnimAbsorb(instance(new int[][]{new int[]{-700}}));
        data_.setStorage(instance(new int[][]{new int[]{-3}}));
        data_.addImage(BUILDING, instance(new int[][]{new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985},new int[]{-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985,-32985}}));
        data_.addImage(NOTHING, instance(new int[][]{new int[]{-16777216,-16777216},new int[]{-16777216,-16777216}}));
        data_.addImage(ROAD, instance(new int[][]{new int[]{-7369361,-7369361},new int[]{-7369361,-7369361}}));
        data_.addImage(WATER, instance(new int[][]{new int[]{-16776961,-16776961},new int[]{-16776961,-16776961}}));
        data_.addPerson(TRAINER, instance(new int[][]{new int[]{-18000}}));
        data_.addPerson(PERSON, instance(new int[][]{new int[]{-1800}}));
        data_.addPerson(TRAINER_ONE, instance(new int[][]{new int[]{-19000}}));
        data_.addPerson(TRAINER_TWO, instance(new int[][]{new int[]{-19008}}));
        data_.addPerson(ALLY, instance(new int[][]{new int[]{-19508}}));
        data_.addPerson(GERANT, instance(new int[][]{new int[]{-20508}}));
        data_.addTrainerImage(TRAINER, instance(new int[][]{new int[]{-18000}}));
        data_.addTrainerImage(TRAINER_ONE, instance(new int[][]{new int[]{-19000}}));
        data_.addTrainerImage(TRAINER_TWO, instance(new int[][]{new int[]{-19008}}));
        data_.addTrainerImage(ALLY, instance(new int[][]{new int[]{-19508}}));
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.DOWN, Sex.NO), instance(new int[][]{new int[]{1}}));
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.UP,Sex.NO), instance(new int[][]{new int[]{1}}));
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.LEFT,Sex.NO), instance(new int[][]{new int[]{1}}));
        data_.getOverWorldHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Direction.RIGHT,Sex.NO), instance(new int[][]{new int[]{1}}));
        data_.getBackHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Sex.NO), instance(new int[][]{new int[]{1}}));
        data_.getFrontHeros().addEntry(new ImageHeroKey(EnvironmentType.ROAD, Sex.NO), instance(new int[][]{new int[]{1}}));
        data_.getMiniPk().addEntry(PIKACHU,instance(new int[][]{new int[]{2}}));
        data_.getMaxiPkBack().addEntry(PIKACHU,instance(new int[][]{new int[]{2}}));
        data_.getMaxiPkFront().addEntry(PIKACHU,instance(new int[][]{new int[]{2}}));
        data_.getMiniItems().addEntry(POKE_BALL,instance(new int[][]{new int[]{2}}));
        data_.getAnimStatis().addEntry(Statistic.ATTACK.getStatName(), instance(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.SPECIAL_ATTACK.getStatName(), instance(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.DEFENSE.getStatName(), instance(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.SPECIAL_DEFENSE.getStatName(), instance(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.SPEED.getStatName(), instance(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.ACCURACY.getStatName(), instance(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.EVASINESS.getStatName(), instance(new int[][]{new int[]{3}}));
        data_.getAnimStatis().addEntry(Statistic.CRITICAL_HIT.getStatName(), instance(new int[][]{new int[]{3}}));
        data_.getTypesImages().addEntry(ELECTRICK,instance(new int[][]{new int[]{4}}));
        data_.getTypesColors().addEntry(ELECTRICK,"1;3;5");
        data_.setEndGameImage(instance(new int[][]{new int[1]}));
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

        data_.getMap().join( 0, 2,newPoint(0,0),newPoint(5,0), Direction.LEFT);

        initMiniMap(data_);
        data_.completeVariables();
        initRandomLaws(data_);
        initExpPoints(data_);
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
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
        pkm_.setLevel( 7);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 2, 1));
    }
    public static DataBase coreDataBaseValid() {
        DataBase data_ = new DataBase(DefaultGenerator.oneElt());
        updateLg(data_);
        data_.defValues();
        data_.initializeMembers();
        initConstants(data_);
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKACHU);
        pkData_.setEggGroups(new StringList(data_.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove(1,ECLAIR));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate( 1);
        pkData_.setHappiness( 1);
        pkData_.setHappinessHatch( 1);
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
        move_.setPp( 1);
        return move_;
    }

    public static void statBase(PokemonData _pk) {
        _pk.getStatistics().addEntry(Statistic.ATTACK,new StatBaseEv(1,0));
        _pk.getStatistics().addEntry(Statistic.DEFENSE,new StatBaseEv(1,0));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_ATTACK,new StatBaseEv(1,0));
        _pk.getStatistics().addEntry(Statistic.SPECIAL_DEFENSE,new StatBaseEv(1,0));
        _pk.getStatistics().addEntry(Statistic.SPEED,new StatBaseEv(1,0));
        _pk.getStatistics().addEntry(Statistic.HP,new StatBaseEv(1,0));
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
                MbOperationNode.DIV_FCT+"(2*"+MbOperationNode.CARAC_GAUCHE_OUVERT+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+",0),"+MbOperationNode.MAX+"(2-"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+",1))+"+MbOperationNode.DIV_FCT+"((2+"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+")*"+MbOperationNode.CARAC_DROITE_FERME+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+",0),2)",
                MbOperationNode.PUIS+"(2,"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_BOOST+"-4)",
                "1",
                ECLAIR_3,
                MessagesDataBaseConstants.DEFAULT_EGG_GROUP_VALUE, _data);
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
        _data.getExpGrowth().addEntry(ExpType.E,"2*"+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU);
        _data.getExpGrowth().addEntry(ExpType.L,"5/4*"+MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",3)");
        _data.getExpGrowth().addEntry(ExpType.M,MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",3)");
        _data.getExpGrowth().addEntry(ExpType.P,MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",2)");
        _data.getExpGrowth().addEntry(ExpType.F,VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU);
        _data.getExpGrowth().addEntry(ExpType.R,"4/5*"+MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",3)");
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
        litteral_.addEntry(MessagesDataBaseConstants.DEF_NIVEAU, StringUtil.concat("level",TAB,"l",TAB,"The level of the Pokemon"));
        litteral_.addEntry(MessagesDataBaseConstants.DEF_BOOST, StringUtil.concat("boost",TAB,"b",TAB,"The boost of the Pokemon"));
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
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CROISSANT,new LawNumber(monteCarloNumber_,4));
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
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.UNIFORME,new LawNumber(monteCarloNumber_,3));
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
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.DECROISSANT,new LawNumber(monteCarloNumber_,2));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("17/20"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(monteCarloNumber_,1));
        monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        _data.getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(monteCarloNumber_,5));
    }

    public static void initMiniMap(DataBase _data) {
        _data.getMiniMap().addEntry(MINI, instance(new int[][]{new int[]{118}}));
        _data.getMiniMap().addEntry(MINI1, instance(new int[][]{new int[]{218}}));
        _data.getMiniMap().addEntry(MINI2, instance(new int[][]{new int[]{112}}));
        _data.getMiniMap().addEntry(MINI3, instance(new int[][]{new int[]{200}}));
        _data.getMiniMap().addEntry(MINI4, instance(new int[][]{new int[]{128}}));
        _data.getMiniMap().addEntry(MINI5, instance(new int[][]{new int[]{211}}));
        _data.getMiniMap().addEntry(MINI6, instance(new int[][]{new int[]{221}}));
        DataMap map_ = _data.getMap();
        TileMiniMap tile_;
        map_.setMiniMap(new MiniMapCoordsList());
        tile_ = new TileMiniMap();
        tile_.setFile(MINI);
        tile_.setPlace(0);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,0), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI1);
        tile_.setPlace(1);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,1), tile_);
        tile_ = new TileMiniMap();
        tile_.setFile(MINI2);
        tile_.setPlace(2);
        map_.getMiniMap().addEntry(new MiniMapCoords(0,2), tile_);
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
        gymTrainer_ = nvGymTrainer( 200,  1, team_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(1, 7), gymTrainer_);
        CustList<PkTrainer> teamTwo_ = new CustList<PkTrainer>();
        teamTwo_.add(toPkTrainer(new NameLevel(PIKACHU, 1), new StringList(ECLAIR)));
        gymTrainer_ = nvGymTrainer( 200,  1, teamTwo_);
        gym_.getIndoor().getGymTrainers().addEntry(newPoint(7, 7), gymTrainer_);
        gym_.getIndoor().setGymLeaderCoords(newPoint(4, 1));
        CustList<PkTrainer> teamThree_ = new CustList<PkTrainer>();
        teamThree_.add(toPkTrainer(new NameLevel(PIKACHU, 1), new StringList(ECLAIR)));
        gymLeader_ = nvGymLeader( 500,  1, teamThree_);
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
        seller_.setTm(Ints.newList());
        seller_.setSell(SellType.ITEM);
        seller_.setImageMiniFileName(GERANT);
        pk_.getIndoor().getGerants().addEntry(newPoint(8, 4), seller_);
        seller_ = new Seller();
        seller_.setItems(new StringList());
        seller_.setTm(Ints.newList());
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
        level_ = (LevelLeague) road_.getLevelsMap().getVal(0);
        block_ = newBlock(9, 9,EnvironmentType.BUILDING,BUILDING,-1);
        level_.getBlocks().addEntry(newPoint(0,0), block_);
        road_.setBegin(newPoint(4,8));
        level_ = (LevelLeague) road_.getLevelsMap().getVal(0);
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
        area_.setAvgNbSteps( 1);
        area_.setMultFight( 1);
        wild_ = new WildPk();
        wild_.setName(PIKACHU);
        wild_.setLevel( 1);
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
        return nvTrainerLeague( 2000,  1, team_);
    }
    private static GymLeader nvGymLeader(int _reward, int _mult, CustList<PkTrainer> _team) {
        GymLeader gymLeader_ = new GymLeader();
        gymLeader_.setTeam(_team);
        gymLeader_.setReward(_reward);
        gymLeader_.setMultiplicityFight(_mult);
        gymLeader_.setName(NULL_REF);
        gymLeader_.setTm( 2);
        gymLeader_.setImageMiniFileName(TRAINER);
        gymLeader_.setImageMaxiFileName(TRAINER);
        return gymLeader_;
    }

    private static GymTrainer nvGymTrainer(int _reward, int _mult, CustList<PkTrainer> _team) {
        GymTrainer gymTrainer_ = new GymTrainer();
        gymTrainer_.setTeam(_team);
        gymTrainer_.setReward(_reward);
        gymTrainer_.setMultiplicityFight(_mult);
        gymTrainer_.setImageMiniFileName(TRAINER);
        gymTrainer_.setImageMaxiFileName(TRAINER);
        return gymTrainer_;
    }

    private static TrainerLeague nvTrainerLeague(int _reward, int _mult, CustList<PkTrainer> _team) {
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
//        trainer_.setMultiplicityFight( _mult);
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
//        block_.setHeight( _h);
//        block_.setWidth( _w);
//        block_.setType(_type);
//        block_.setTileFileName(_tileFileName);
//        block_.setIndexApparition( _index);
//        return block_;
//    }

//    private static LevelPoint newLevelPoint(int _level, int _x, int _y) {
//        LevelPoint begin_ = new LevelPoint();
//        begin_.setLevelIndex( _level);
//        begin_.setPoint(newPoint(_x, _y));
//        return begin_;
//    }
//
//    private static Coords newCoords(int _place, int _level, int _x, int _y) {
//        Coords begin_ = new Coords();
//        begin_.setNumberPlace( _place);
//        begin_.setLevel(new LevelPoint());
//        begin_.getLevel().setLevelIndex( _level);
//        begin_.getLevel().setPoint(newPoint(_x, _y));
//        return begin_;
//    }
//
//    private static Coords newCoords(int _place, int _level, int _xi, int _yi, int _x, int _y) {
//        Coords begin_ = new Coords();
//        begin_.setNumberPlace( _place);
//        begin_.affectInside(newPoint(_xi, _yi));
//        begin_.setLevel(new LevelPoint());
//        begin_.getLevel().setLevelIndex( _level);
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
//        return new Point(_x, _y);
//    }
    private static CreateMainWindowAiki launcher(MockProgramInfos _pr, AikiFactory _fact) {
        return launcher(_pr, _fact, DocumentReaderAikiCoreUtil.newLoadingGame());
    }

    private static CreateMainWindowAiki launcher(MockProgramInfos _pr, AikiFactory _fact, LoadingGame _conf) {
        TranslationsLg en_ = _pr.lg(EN);
        TranslationsAppli app_ = MessagesPkGame.initAppliTr(en_);
        windowPk(app_);
        updateBase(_pr.getTranslations());
//        app_.getMapping().addEntry(MessagesPkGame.FILES_PATH,MessagesPkGame.mes());
        StringMap<int[][]> mess_ = new StringMap<int[][]>();
        mess_.addEntry("",new int[][]{new int[]{0}});
        return new CreateMainWindowAiki(_conf, new StringList(), _pr, _fact, new LanguagesButtonsPair(_pr.getCompoFactory().newMenuItem(),null,null), mess_, _pr.getImageFactory().newImageRgb(1,1));
    }

    private static CreateMainWindowAiki launcher(MockProgramInfos _pr, AikiFactory _fact, String _file) {
        return launcher(_pr, _fact, _file, DocumentReaderAikiCoreUtil.newLoadingGame());
    }

    private static CreateMainWindowAiki launcher(MockProgramInfos _pr, AikiFactory _fact, String _file, LoadingGame _conf) {
        StringList path_ = new StringList();
        path_.add(_file);
        TranslationsLg en_ = _pr.lg(EN);
        TranslationsAppli app_ = MessagesPkGame.initAppliTr(en_);
        windowPk(app_);
        updateBase(_pr.getTranslations());
//        app_.getMapping().addEntry(MessagesPkGame.FILES_PATH,MessagesPkGame.mes());
        StringMap<int[][]> mess_ = new StringMap<int[][]>();
        mess_.addEntry("",new int[][]{new int[]{0}});
        return new CreateMainWindowAiki(_conf, path_, _pr, _fact, new LanguagesButtonsPair(_pr.getCompoFactory().newMenuItem(),null,null), mess_, _pr.getImageFactory().newImageRgb(1,1));
    }

    private static void coreDataBaseTrainerDualKoPlayer(WindowAiki _window) {
        loadRom(_window, FightGuiRoundTest.coreDataBaseTrainerDual());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
        _window.getFacade().move(Direction.RIGHT);
        _window.getFacade().interactNoFish();
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).setRemainingHp(Rate.zero());
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).setGroundPlace(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 0)).setGroundPlaceSubst(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 1)).setRemainingHp(Rate.zero());
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 1)).setGroundPlace(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 1)).setGroundPlaceSubst(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 2)).setRemainingHp(Rate.zero());
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 2)).setGroundPlace(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 2)).setGroundPlaceSubst(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 3)).setRemainingHp(Rate.zero());
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 3)).setGroundPlace(Fighter.BACK);
        _window.getFacade().getFight().getFighter(Fight.toUserFighter( 3)).setGroundPlaceSubst(Fighter.BACK);
        _window.getFacade().getFight().getFirstPositPlayerFighters().put(0,Fighter.BACK);
        _window.getFacade().getFight().getFirstPositPlayerFighters().put(1,Fighter.BACK);
        _window.getFacade().getFight().getFirstPositPlayerFighters().put(2,Fighter.BACK);
        _window.getFacade().getFight().getFirstPositPlayerFighters().put(3,Fighter.BACK);
        _window.getFacade().getFight().setState(FightState.SWITCH_WHILE_KO_USER);
        _window.getFacade().changeCamera();
        _window.afterLoadGame();
    }
    private DamagingMoveData damage() {
        DamagingMoveData dam_ = Instances.newDamagingMoveData();
        EffectDamage daEff_ = Instances.newEffectDamage();
        daEff_.setPower("1");
        dam_.getEffects().add(daEff_);
        return dam_;
    }

    private BeanBuilderHelper builder(BeanRenderWithAppName _rend) {
        return ((BeanBuilderHelper)_rend.getBuilder());
    }

}
