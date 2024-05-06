package aiki.gui;

import aiki.game.Game;
import aiki.map.characters.enums.GeranceType;
import aiki.map.enums.Direction;
import code.gui.AbsCustComponent;
import code.gui.GuiConstants;
import code.mock.MockCustComponent;
import code.mock.MockThreadFactory;
import code.util.IdList;
import org.junit.Test;

public final class MoveHerosTest extends InitDbGuiAiki {
    @Test
    public void move1() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_LEFT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getReceiveEgg()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getReceiveParents()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getHostPk()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(3,window_.getScenePanel().getTeamPan().getListe().size());
    }
    @Test
    public void move2() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getPad().getLeft());
        tryClick(window_.getScenePanel().getButtonInteract());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getReceiveEgg()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getReceiveParents()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getHostPk()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(3,window_.getScenePanel().getTeamPan().getListe().size());
    }
    private static void loadRomGameStore(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(newGerantPokemon(GeranceType.HOST)));
        Game game_ = build(_window.getFacade());
//        game_.setPlayerOrientation(Direction.LEFT);
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
}
