package aiki.gui;

import aiki.gui.dialogs.*;
import code.gui.*;
import code.mock.*;
import code.sml.*;
import code.util.*;
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
        window_.getBattle().resetWindows();
        assertFalse(window_.getModal().get());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAnNoCheck((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        window_.getFacade().setChangeToFightScene(true);
        window_.getBattle().resetWindows();
        assertFalse(window_.getModal().get());
    }

    private static void coreDataBaseFish(WindowAiki _window) {
        loadRom(_window, coreDataBaseFish());
        loadGame(_window, build(_window.getFacade()));
    }
}
