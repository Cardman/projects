package aiki.gui;

import aiki.beans.*;
import aiki.game.Game;
import aiki.game.fight.util.*;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.gui.components.walk.events.WalkNicknameAutoCompleteListener;
import aiki.gui.dialogs.*;
import aiki.gui.listeners.DefTaskEnabled;
import aiki.instances.Instances;
import aiki.main.PkNonModalEvent;
import aiki.sml.MessagesPkGame;
import aiki.sml.MessagesRenderPkGameDetail;
import aiki.sml.MessagesRenderPkSoftParams;
import aiki.util.LawNumber;
import code.gui.*;
import code.gui.document.*;
import code.gui.images.AbstractImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.mock.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.Translations;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.stream.StreamBinaryFile;
import code.threads.ConcreteBoolean;
import code.threads.ConcreteInteger;
import code.util.*;
import code.util.core.StringUtil;
import org.junit.*;

public final class WindowAikiTest extends InitDbGuiAiki {
    @Test
    public void initGame() {
        WindowAiki window_ = newGame();
        window_.getFacade().setData(coreDataBase());
        window_.getNewGame().setEnabled(true);
        tryClick(window_.getNewGame());
        DialogHeros dial_ = window_.getDialogHeros();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) dial_.getFrame().getPane()).getTreeAccessible();
        assertEq(2, tree_.size());
        assertTrue(tree_.containsObj(dial_.getNickname()));
        assertTrue(tree_.containsObj(dial_.getLabsBegin().get(0).getPaintableLabel()));
        assertTrue(window_.getModal().get());
    }
    @Test
    public void chooseGame() {
        WindowAiki window_ = newGame();
        window_.getFacade().setData(coreDataBase());
        window_.getNewGame().setEnabled(true);
        tryClick(window_.getNewGame());
        DialogHeros dial_ = window_.getDialogHeros();
        dial_.getNickname().setText("__");
        tryClick(dial_.getLabsBegin().get(0));
        tryClick(dial_.getLabsBegin().get(0));
        tryClick(dial_.getLabsBegin().get(0));
        tryClick(dial_.getConfirmNewGame());
        assertFalse(window_.getModal().get());
        assertEq("__",window_.getFacade().getPlayer().getNickname());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getCommonFrame().getPane()).getTreeAccessible();
        assertEq(15, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getUp().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getDown().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getLeft().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getRight().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getMiddle().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getScene().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSeeBoxes()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSeeEggs()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeam()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getItems()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getAttract()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTm()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getGoBack()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getHost()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getGame()));
    }
    @Test
    public void reChooseHeros() {
        WindowAiki window_ = newGame();
        window_.getFacade().setData(coreDataBase());
        window_.getNewGame().setEnabled(true);
        tryClick(window_.getNewGame());
        DialogHeros dial_ = window_.getDialogHeros();
        dial_.getNickname().setText("__");
        tryClick(dial_.getLabsBegin().get(0));
        tryClick(dial_.getConfirmNewGame());
        tryClick(window_.getNewGame());
        assertTrue(window_.getModal().get());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) dial_.getFrame().getPane()).getTreeAccessible();
        assertEq(2, tree_.size());
        assertTrue(tree_.containsObj(dial_.getNickname()));
        assertTrue(tree_.containsObj(dial_.getLabsBegin().get(0).getPaintableLabel()));
    }
    @Test
    public void cancelHeros() {
        WindowAiki window_ = newGame();
        window_.getFacade().setData(coreDataBase());
        window_.getNewGame().setEnabled(true);
        tryClick(window_.getNewGame());
        DialogHeros dial_ = window_.getDialogHeros();
        dial_.getFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(window_.getModal().get());
    }
    @Test
    public void params1() {
        WindowAiki window_ = newFight();
        updateBase(window_.getFrames().getTranslations());
        MessagesPkGame.appendSoftParamsContent(MessagesPkGame.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkSoftParams.en());
        window_.getLoadingConf().setEnableAnimation(false);
        window_.getLoadingConf().setClickButtonsPad(false);
        window_.getLoadingConf().setEnabledKeyPad(false);
        window_.getLoadingConf().setEnableMovingHerosAnimation(false);
        window_.getLoadingConf().setSaveGameAtExit(false);
        window_.getLoadingConf().setLoadHomeFolder(false);
        window_.getLoadingConf().setSaveHomeFolder(false);
        window_.getLoadingConf().setLoadLastGame(false);
        window_.getLoadingConf().setLoadLastRom(false);
        tryClick(window_.getParams());
        window_.getSoftParams().getEnableAnimation().setSelected(true);
        window_.getSoftParams().getClickButtonsPad().setSelected(true);
        window_.getSoftParams().getEnabledKeyPad().setSelected(true);
        window_.getSoftParams().getEnableMovingHerosAnimation().setSelected(true);
        window_.getSoftParams().getSaveGameAtExit().setSelected(true);
        window_.getSoftParams().getSelectHomePathZip().setSelected(true);
        window_.getSoftParams().getSelectHomePath().setSelected(true);
        window_.getSoftParams().getLoadLastGame().setSelected(true);
        window_.getSoftParams().getLoadLastRom().setSelected(true);
        tryClick(window_.getSoftParams().getValidChoice());
        assertFalse(window_.getModal().get());
    }
    @Test
    public void params2() {
        WindowAiki window_ = newFight();
        updateBase(window_.getFrames().getTranslations());
        MessagesPkGame.appendSoftParamsContent(MessagesPkGame.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkSoftParams.en());
        window_.getLoadingConf().setEnableAnimation(true);
        window_.getLoadingConf().setClickButtonsPad(true);
        window_.getLoadingConf().setEnabledKeyPad(true);
        window_.getLoadingConf().setEnableMovingHerosAnimation(true);
        window_.getLoadingConf().setSaveGameAtExit(true);
        window_.getLoadingConf().setLoadHomeFolder(true);
        window_.getLoadingConf().setSaveHomeFolder(true);
        window_.getLoadingConf().setLoadLastGame(true);
        window_.getLoadingConf().setLoadLastRom(true);
        tryClick(window_.getParams());
        window_.getSoftParams().getEnableAnimation().setSelected(false);
        window_.getSoftParams().getClickButtonsPad().setSelected(false);
        window_.getSoftParams().getEnabledKeyPad().setSelected(false);
        window_.getSoftParams().getEnableMovingHerosAnimation().setSelected(false);
        window_.getSoftParams().getSaveGameAtExit().setSelected(false);
        window_.getSoftParams().getSelectHomePathZip().setSelected(false);
        window_.getSoftParams().getSelectHomePath().setSelected(false);
        window_.getSoftParams().getLoadLastGame().setSelected(false);
        window_.getSoftParams().getLoadLastRom().setSelected(false);
        tryClick(window_.getSoftParams().getValidChoice());
        assertFalse(window_.getModal().get());
    }
    @Test
    public void lgs() {
        WindowAiki window_ = newFight();
        updateBase(window_.getFrames().getTranslations());
        MessagesPkGame.appendPkGameDetailContent(MessagesPkGame.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
//        prepareFightTask(window_);
//        prepareWebTask(window_);
//        window_.getCore().getAikiFactory().getTaskNavData().attendreResultat();
//        window_.getRenderDataWeb().getSession().setNavCore(new NavigationCore());
//        window_.getRenderDataWebSimu().getSession().setNavCore(new NavigationCore());
//        window_.getBattle().getRenderDataFight().getSession().setNavCore(new NavigationCore());
        coreDataBaseFish(window_);
        tryClick(window_.getScenePanel().getTeam());
        tryClick(window_.getScenePanel().getExitOptions());
        window_.changeLanguage(LANGUAGE);
//        window_.getBattle().resetWindows();
        assertFalse(window_.getModal().get());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAnNoCheck((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getFacade().setChangeToFightScene(true);
//        window_.getBattle().resetWindows();
        assertFalse(window_.getModal().get());
//        AikiNatLgNamesNavigation res_ = new DataWebInit(new PreparedRenderedPages(new DataGameInit(), new StringMap<Document>(), new StringMap<TranslationsAppli>(), new StringMap<String>(), new PokemonStandardsSample(), new StringList())).call();
//        res_.getNavigation().setLanguage("");
//        assertEq("",res_.getNavigation().getLanguage());
        new WalkNicknameAutoCompleteListener(new MockTextField(""),window_.getFacade()).changedUpdate(0,0);
        tryClick(window_.getLanguage());
        assertEq(0,new DefTaskEnabled().status(new ConcreteInteger(0)));
        window_.getScenePanel().getScene().getPaintableLabel().getMouseListenersRel().get(0).mouseReleased(null,null,null);
//        RenderedPage rend_ = new RenderedPage(new MockScrollPane(), window_.getFrames(), new FixCharacterCaseConverter(), new AlwaysActionListenerAct());
//        NavigationCore nav_ = new NavigationCore();
//        Document d_ = DocumentBuilder.newXmlDocument();
//        d_.appendChild(d_.createElement("_"));
//        nav_.setupText("<_/>", d_,"","");
//        rend_.setNavCore(nav_);
//        new ThreadRefresh(rend_).run();
//        new NatRenderAction(new PokemonStandardsSample(),new NatNavigation()).execute(false,d_.getDocumentElement());
        BeanBuilderHelper h_ = new BeanBuilderHelper(window_.getFrames(), new FindBeanEvent(window_.getFrames().getCompoFactory().newTextField(),window_.getFrames()));
        h_.initGrid();
        h_.colCount(1);
        h_.setTranslations(window_.getFrames().getTranslations());
        h_.setScrollPane(new MockScrollPane());
        h_.setFrame(window_.getCommonFrame());
        TranslationsAppli ta_ = new TranslationsAppli();
        ta_.getMapping().addEntry(MessagesPkBean.INDEX,new TranslationsFile());
        window_.getFrames().getTranslations().getMapping().getVal(window_.getFrames().getLanguage()).getMapping().addEntry(MessagesPkBean.APP_BEAN_DATA, ta_);
        IntBeanChgString txt_ = h_.getGenInput().newText();
        txt_.setupValue(txt_.tryRet());
        WelcomeBean pk_ = new WelcomeBean();
        pk_.setLanguage(window_.getFrames().getLanguage());
//        pk_.setForms(new StringMapObject());
        pk_.setDataBase(window_.getFacade());
        h_.setFacade(window_.getFacade());
        BeanAnchorCstEvent evt_ = new BeanAnchorCstEvent("#_");
        h_.getRenders().addEntry("",pk_);
        h_.formatMessageDirCts("", evt_);
        h_.addImgCts(new int[1][1],"");
        h_.addImgCtsAnc(new int[1][1],"", evt_);
        h_.setIndent(1);
        h_.paintIndent();
        h_.paintNb(1);
        h_.setRefLk("_");
        h_.hierarchy("",new MockTextPane());
        h_.build(evt_);
        IntMap<String> i_ = new IntMap<String>();
        i_.addEntry(0,"");
        IntBeanChgInt chgInt_ = h_.getGenInput().newInt(i_);
        chgInt_.valueInt(chgInt_.valueInt());
        h_.getGenInput().newLgInt().valueLgInt(h_.getGenInput().newLgInt().valueLgInt());
        h_.getGenInput().newStringList(new StringMap<String>()).setupValue(h_.getGenInput().newStringList(new StringMap<String>()).tryRet());
        h_.getGenInput().newInts(new IntMap<String>()).setupValue(h_.getGenInput().newInts(new IntMap<String>()).tryRet());
        h_.getGenInput().newAc().valueActivity(h_.getGenInput().newAc().valueActivity());
        h_.getGenInput().newChoice(new StringMap<String>(),new StringMap<String>(),new StringMap<String>()).valueChoice(h_.getGenInput().newChoice(new StringMap<String>(),new StringMap<String>(),new StringMap<String>()).valueChoice());
        h_.getGenInput().newMt(new IdMap<MoveTarget, String>()).valueMt(h_.getGenInput().newMt(new IdMap<MoveTarget, String>()).valueMt());
//        WindowAiki.getMessagesFromLocaleClass(LANGUAGE);
    }

    @Test
    public void diff1() {
        WindowAiki window_ = newFight();
        MessagesPkGame.appendPkGameDetailContent(MessagesPkGame.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
        Game game_ = Instances.newGame();
        game_.getDifficulty().setEndFightIfOneTeamKo(false);
        game_.getDifficulty().setEnabledClosing(false);
        game_.getDifficulty().setStillPossibleFlee(false);
        game_.getDifficulty().setRandomWildFight(false);
        game_.getDifficulty().setRestoredMovesEndFight(false);
        game_.getDifficulty().setAllowedSwitchPlacesEndRound(false);
        game_.getDifficulty().setAllowCatchingKo(false);
        game_.getDifficulty().setSkipLearningMovesWhileNotGrowingLevel(false);
        window_.getFacade().setGame(game_);
        window_.getFacade().getData().initializeMembers();
        window_.getFacade().getData().getTranslatedDiffWinPts().addEntry(window_.getFrames().getLanguage(),new IdMap<DifficultyWinPointsFight, String>());
        window_.getFacade().getData().getTranslatedDiffWinPts().getVal(window_.getFrames().getLanguage()).addEntry(game_.getDifficulty().getDiffWinningExpPtsFight(),"");
        window_.getFacade().getData().getTranslatedDiffModelLaw().addEntry(window_.getFrames().getLanguage(),new IdMap<DifficultyModelLaw, String>());
        window_.getFacade().getData().getTranslatedDiffModelLaw().getVal(window_.getFrames().getLanguage()).put(game_.getDifficulty().getDamageRateLawFoe(),"");
        window_.getFacade().getData().getTranslatedDiffModelLaw().getVal(window_.getFrames().getLanguage()).put(game_.getDifficulty().getDamageRatePlayer(),"");
        MonteCarloNumber lawOne_ = new MonteCarloNumber();
        lawOne_.addQuickEvent(Rate.one(), LgInt.one());
        window_.getFacade().getData().getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(lawOne_,0));
        MonteCarloNumber lawTwo_ = new MonteCarloNumber();
        lawTwo_.addQuickEvent(Rate.one(),LgInt.one());
        window_.getFacade().getData().getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(lawTwo_,0));
//        prepareDiffTask(window_);
        window_.getFrames().currentLg().getMapping().addEntry(MessagesPkBean.APP_BEAN, MessagesPkBean.en());
//        ((MockProgramInfos)window_.getFrames()).lg(FR).getMapping().addEntry(MessagesPkBean.APP_BEAN, MessagesPkBean.fr());
        window_.getDifficulty().setEnabled(true);
        tryClick(window_.getDifficulty());
        assertTrue(window_.getDialogDifficulty().getAbsDialog().isVisible());
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getEnabledClosing().setSelected(true);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getAllowCatchingKo().setSelected(true);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getAllowedSwitchPlacesEndRound().setSelected(true);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getEndFightIfOneTeamKo().setSelected(true);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getRandomWildFight().setSelected(true);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getRestoredMovesEndFight().setSelected(true);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getSkipLearningMovesWhileNotGrowingLevel().setSelected(true);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getStillPossibleFlee().setSelected(true);
        tryClick(window_.getDialogDifficulty().getDifficultyBeanRender().getUpdateValues());
        window_.getDialogDifficulty().getAbsDialog().getWindowListenersDef().get(0).windowClosing();
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void diff2() {
        WindowAiki window_ = newFight();
        MessagesPkGame.appendPkGameDetailContent(MessagesPkGame.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
        Game game_ = Instances.newGame();
        game_.getDifficulty().setEndFightIfOneTeamKo(true);
        game_.getDifficulty().setEnabledClosing(true);
        game_.getDifficulty().setStillPossibleFlee(true);
        game_.getDifficulty().setRandomWildFight(true);
        game_.getDifficulty().setRestoredMovesEndFight(true);
        game_.getDifficulty().setAllowedSwitchPlacesEndRound(true);
        game_.getDifficulty().setAllowCatchingKo(true);
        game_.getDifficulty().setSkipLearningMovesWhileNotGrowingLevel(true);
        window_.getFacade().setGame(game_);
        window_.getFacade().getData().initializeMembers();
        window_.getFacade().getData().getTranslatedDiffWinPts().addEntry(window_.getFrames().getLanguage(),new IdMap<DifficultyWinPointsFight, String>());
        window_.getFacade().getData().getTranslatedDiffWinPts().getVal(window_.getFrames().getLanguage()).addEntry(game_.getDifficulty().getDiffWinningExpPtsFight(),"");
        window_.getFacade().getData().getTranslatedDiffModelLaw().addEntry(window_.getFrames().getLanguage(),new IdMap<DifficultyModelLaw, String>());
        window_.getFacade().getData().getTranslatedDiffModelLaw().getVal(window_.getFrames().getLanguage()).put(game_.getDifficulty().getDamageRateLawFoe(),"");
        window_.getFacade().getData().getTranslatedDiffModelLaw().getVal(window_.getFrames().getLanguage()).put(game_.getDifficulty().getDamageRatePlayer(),"");
        MonteCarloNumber lawOne_ = new MonteCarloNumber();
        lawOne_.addQuickEvent(Rate.one(), LgInt.one());
        window_.getFacade().getData().getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MIN, new LawNumber(lawOne_,0));
        MonteCarloNumber lawTwo_ = new MonteCarloNumber();
        lawTwo_.addQuickEvent(Rate.one(),LgInt.one());
        window_.getFacade().getData().getLawsDamageRate().addEntry(DifficultyModelLaw.CONSTANT_MAX, new LawNumber(lawTwo_,0));
//        prepareDiffTask(window_);
        window_.getFrames().currentLg().getMapping().addEntry(MessagesPkBean.APP_BEAN, MessagesPkBean.en());
//        ((MockProgramInfos)window_.getFrames()).lg(FR).getMapping().addEntry(MessagesPkBean.APP_BEAN, MessagesPkBean.fr());
        window_.getDifficulty().setEnabled(true);
        tryClick(window_.getDifficulty());
        assertTrue(window_.getDialogDifficulty().getAbsDialog().isVisible());
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getEnabledClosing().setSelected(false);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getAllowCatchingKo().setSelected(false);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getAllowedSwitchPlacesEndRound().setSelected(false);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getEndFightIfOneTeamKo().setSelected(false);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getRandomWildFight().setSelected(false);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getRestoredMovesEndFight().setSelected(false);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getSkipLearningMovesWhileNotGrowingLevel().setSelected(false);
        window_.getDialogDifficulty().getDifficultyBeanRender().getForm().getStillPossibleFlee().setSelected(false);
        tryClick(window_.getDialogDifficulty().getDifficultyBeanRender().getUpdateValues());
        window_.getDialogDifficulty().getAbsDialog().getWindowListenersDef().get(0).windowClosing();
        assertTrue(window_.getCommonFrame().isVisible());
    }
    @Test
    public void prog() {
        WindowAiki window_ = newFight();
        assertTrue(new PkNonModalEvent(window_.getModal()).act());
        assertFalse(window_.getModal().get());
        ProgressingDialog pr_ = new ProgressingDialog(window_.getFrames(), window_);
        pr_.setPerCent(0);
        pr_.stopTimer();
        pr_.init(new ConcreteBoolean(),window_,new CustList<AbstractImage>(),false);
        pr_.setPerCent(0);
        pr_.stopTimer();
        pr_.startAnimation();
        pr_.stopAnimation();
        pr_.st();
        pr_.setTitle(pr_.getPercent());
        pr_.getTitle();
        assertFalse(new PkNonModalEvent(window_.getModal()).act());
        assertTrue(window_.getModal().get());
        pr_.getAbsDialog().getWindowListenersDef().get(0).windowClosing();
        assertTrue(new PkNonModalEvent(window_.getModal()).act());
        assertFalse(window_.getModal().get());
    }
    @Test
    public void video() {
        WindowAiki window_ = newFight();
        updateBase(window_.getFrames().getTranslations());
        window_.getVideoLoading().getVideo(window_.getGenerator(),window_.getFrames().getFileCoreStream(),window_.getFrames(), window_.getVideoBase());
        window_.getVideoLoading().getVideo(window_.getGenerator(),window_.getFrames().getFileCoreStream(),window_.getFrames(), window_.getVideoBase());
        assertFalse(window_.getModal().get());
        WindowAiki window2_ = newFight();
        updateBase(window2_.getFrames().getTranslations());
        window2_.getFrames().getFileCoreStream().newFile("/video/1").mkdirs();
        StreamBinaryFile.writeFile("/video/1/link_0.png", StringUtil.encode("1;1"),window2_.getStreams());
//        StreamBinaryFile.writeFile("/video/1/link_0.png", StringUtil.encode(BaseSixtyFourUtil.getStringByImage(new int[][]{new int[1]})),window2_.getStreams());
        StreamBinaryFile.writeFile("/video/1/2", new byte[0],window2_.getStreams());
        StreamBinaryFile.writeFile("/video/2", new byte[0],window2_.getStreams());
        window2_.getVideoLoading().getVideo(window2_.getGenerator(),window2_.getFrames().getFileCoreStream(),window2_.getFrames(),window2_.getVideoBase());
        assertFalse(window2_.getModal().get());
        WindowAiki window3_ = newFight();
        updateBase(window3_.getFrames().getTranslations());
        window3_.getFrames().getFileCoreStream().newFile("/video").mkdirs();
        window3_.getVideoLoading().getVideo(window3_.getGenerator(),window3_.getFrames().getFileCoreStream(),window3_.getFrames(), window3_.getVideoBase());
        assertFalse(window3_.getModal().get());
    }

    public static void updateBase(Translations _en) {
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(_en));
    }
    private static void coreDataBaseFish(WindowAiki _window) {
        loadRom(_window, coreDataBaseFish());
        loadGame(_window, build(_window.getFacade()));
    }
}
