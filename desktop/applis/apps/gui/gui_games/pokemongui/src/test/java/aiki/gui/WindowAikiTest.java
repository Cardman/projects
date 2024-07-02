package aiki.gui;

import aiki.beans.DataGameInit;
import aiki.gui.components.walk.events.WalkNicknameAutoCompleteListener;
import aiki.gui.dialogs.*;
import aiki.gui.listeners.DefTaskEnabled;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.instances.Instances;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.main.DataWebInit;
import aiki.main.PkNonModalEvent;
import aiki.sml.GamesPk;
import aiki.sml.MessagesRenderPkGameDetail;
import aiki.sml.MessagesRenderPkSoftParams;
import code.bean.nat.FixCharacterCaseConverter;
import code.bean.nat.NatNavigation;
import code.gui.*;
import code.gui.document.NatRenderAction;
import code.gui.document.RenderedPage;
import code.gui.document.ThreadRefresh;
import code.gui.events.AlwaysActionListenerAct;
import code.gui.images.AbstractImage;
import code.images.BaseSixtyFourUtil;
import code.mock.*;
import code.sml.*;
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
        assertEq(14, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getUp().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getDown().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getLeft().getPaintableLabel()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getPad().getRight().getPaintableLabel()));
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
        tryClick(window_.getNewGame());
        DialogHeros dial_ = window_.getDialogHeros();
        dial_.getFrame().getWindowListenersDef().get(0).windowClosing();
        assertFalse(window_.getModal().get());
    }
    @Test
    public void params1() {
        WindowAiki window_ = newFight();
        GamesPk.appendSoftParamsContent(GamesPk.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkSoftParams.en());
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
        GamesPk.appendSoftParamsContent(GamesPk.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkSoftParams.en());
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
        GamesPk.appendPkGameDetailContent(GamesPk.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
        prepareFightTask(window_);
        prepareWebTask(window_);
        window_.getCore().getAikiFactory().getTaskNav().attendreResultat();
        window_.getRenderDataWeb().getSession().setNavCore(new NavigationCore());
        window_.getBattle().getRenderDataFight().getSession().setNavCore(new NavigationCore());
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
        AikiNatLgNamesNavigation res_ = new DataWebInit(new PreparedRenderedPages("", new DataGameInit(), new StringMap<Document>(), new StringMap<String>(), new StringMap<String>(), new PokemonStandardsSample(), new StringList()), window_.getFrames().getCompoFactory().newMenuItem("")).call();
        res_.getNavigation().setLanguage("");
        assertEq("",res_.getNavigation().getLanguage());
        new WalkNicknameAutoCompleteListener(new MockTextField(""),window_.getFacade()).changedUpdate();
        tryClick(window_.getLanguage());
        assertEq(0,new DefTaskEnabled().status(new ConcreteInteger(0)));
        window_.getScenePanel().getScene().getPaintableLabel().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        RenderedPage rend_ = new RenderedPage(new MockScrollPane(), window_.getFrames(), new FixCharacterCaseConverter(), new AlwaysActionListenerAct());
        NavigationCore nav_ = new NavigationCore();
        Document d_ = DocumentBuilder.newXmlDocument();
        d_.appendChild(d_.createElement("_"));
        nav_.setupText("<_/>", d_,"","");
        rend_.setNavCore(nav_);
        new ThreadRefresh(rend_).run();
        new NatRenderAction(new PokemonStandardsSample(),new NatNavigation()).execute(false,d_.getDocumentElement());
//        WindowAiki.getMessagesFromLocaleClass(LANGUAGE);
    }

    @Test
    public void diff() {
        WindowAiki window_ = newFight();
        GamesPk.appendPkGameDetailContent(GamesPk.getAppliTr(window_.getFrames().currentLg()), MessagesRenderPkGameDetail.en());
        window_.getFacade().setGame(Instances.newGame());
        prepareDiffTask(window_);
        window_.getDifficulty().setEnabled(true);
        tryClick(window_.getDifficulty());
        assertTrue(window_.getDialogDifficulty().getAbsDialog().isVisible());
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
        window_.getVideoLoading().getVideo(window_.getGenerator(),window_.getFrames().getFileCoreStream(),window_.getFrames());
        window_.getVideoLoading().getVideo(window_.getGenerator(),window_.getFrames().getFileCoreStream(),window_.getFrames());
        assertFalse(window_.getModal().get());
        WindowAiki window2_ = newFight();
        window2_.getFrames().getFileCoreStream().newFile("/video/1").mkdirs();
        StreamBinaryFile.writeFile("/video/1/link_0.png", StringUtil.encode(BaseSixtyFourUtil.getStringByImage(new int[][]{new int[1]})),window2_.getStreams());
        StreamBinaryFile.writeFile("/video/1/2", new byte[0],window2_.getStreams());
        StreamBinaryFile.writeFile("/video/2", new byte[0],window2_.getStreams());
        window2_.getVideoLoading().getVideo(window2_.getGenerator(),window2_.getFrames().getFileCoreStream(),window2_.getFrames());
        assertFalse(window2_.getModal().get());
        WindowAiki window3_ = newFight();
        window3_.getFrames().getFileCoreStream().newFile("/video").mkdirs();
        window3_.getVideoLoading().getVideo(window3_.getGenerator(),window3_.getFrames().getFileCoreStream(),window3_.getFrames());
        assertFalse(window3_.getModal().get());
    }
    private static void coreDataBaseFish(WindowAiki _window) {
        loadRom(_window, coreDataBaseFish());
        loadGame(_window, build(_window.getFacade()));
    }
}
