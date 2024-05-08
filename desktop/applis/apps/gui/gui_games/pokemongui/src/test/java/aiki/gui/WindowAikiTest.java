package aiki.gui;

import aiki.gui.dialogs.*;
import aiki.main.PkNonModalEvent;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.images.BaseSixtyFourUtil;
import code.mock.*;
import code.sml.*;
import code.stream.StreamBinaryFile;
import code.stream.StreamTextFile;
import code.threads.ConcreteBoolean;
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
