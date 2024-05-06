package aiki.gui;

import aiki.game.Game;
import code.gui.GuiConstants;
import code.mock.MockThreadFactory;
import org.junit.Test;

public final class FightGuiInitTest extends InitDbGuiAiki {

    @Test
    public void intro1() {
        WindowAiki window_ = newProg();
        coreDataBaseLeg(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        assertTrue(window_.getFacade().getFight().getFightType().isExisting());
        assertTrue(window_.getFacade().getFight().getFightType().isWild());
    }

    @Test
    public void intro2() {
        WindowAiki window_ = newProg();
        coreDataBaseLeg(window_);
        tryClick(window_.getScenePanel().getAttract());
        assertFalse(window_.getFacade().getFight().getFightType().isExisting());
    }

    @Test
    public void intro3() {
        WindowAiki window_ = newProg();
        coreDataBaseAtt(window_);
        tryClick(window_.getScenePanel().getAttract());
        assertTrue(window_.getFacade().getFight().getFightType().isExisting());
        assertTrue(window_.getFacade().getFight().getFightType().isWild());
    }
    private static void coreDataBaseLeg(WindowAiki _window) {
        loadRom(_window, coreDataBaseLeg());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
    private static void coreDataBaseAtt(WindowAiki _window) {
        loadRom(_window, coreDataBaseAtt());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
}
