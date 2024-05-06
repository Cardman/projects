package aiki.gui;

import aiki.game.*;
import aiki.map.characters.enums.*;
import aiki.map.enums.*;
import aiki.map.pokemon.*;
import code.gui.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class HostMenusTest extends InitDbGuiAiki {
    @Test
    public void hostMenus() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
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
    public void hostPk1() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHostPk());
        assertEq(1,window_.getFacade().getGame().getPlayer().getTeam().size());
    }
    @Test
    public void hostPk2() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHostPk());
        assertEq(3,window_.getFacade().getGame().getPlayer().getTeam().size());
    }
    @Test
    public void hostPk3() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHostPk());
        tryClick(window_.getScenePanel().getReceiveEgg());
        assertEq(1,window_.getFacade().getGame().getPlayer().getTeam().size());
        assertFalse(window_.getFacade().getGame().getHostedPk().getList().get(0).getValue().isFree());
    }
    @Test
    public void hostPk4() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHostPk());
        window_.getFacade().getGame().getHostedPk().getList().get(0).getValue().setNbSteps(256);
        tryClick(window_.getScenePanel().getReceiveEgg());
        assertEq(2,window_.getFacade().getGame().getPlayer().getTeam().size());
        assertFalse(window_.getFacade().getGame().getHostedPk().getList().get(0).getValue().isFree());
    }
    @Test
    public void hostPk5() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHostPk());
        tryClick(window_.getScenePanel().getReceiveParents());
        assertEq(3,window_.getFacade().getGame().getPlayer().getTeam().size());
        assertTrue(window_.getFacade().getGame().getHostedPk().getList().get(0).getValue().isFree());
    }
    @Test
    public void hostPk6() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHostPk());
        window_.getFacade().getGame().getHostedPk().getList().get(0).getValue().setNbSteps(256);
        tryClick(window_.getScenePanel().getReceiveParents());
        assertEq(4,window_.getFacade().getGame().getPlayer().getTeam().size());
        assertTrue(window_.getFacade().getGame().getHostedPk().getList().get(0).getValue().isFree());
    }
    @Test
    public void hostPk7() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHostPk());
        window_.getFacade().getGame().getPlayer().getTeam().add(pk(window_));
        window_.getFacade().getGame().getPlayer().getTeam().add(pk(window_));
        window_.getFacade().getGame().getPlayer().getTeam().add(pk(window_));
        window_.getFacade().getGame().getPlayer().getTeam().add(pk(window_));
        window_.getFacade().getGame().getPlayer().getTeam().add(pk(window_));
        window_.getFacade().getGame().getPlayer().getTeam().add(pk(window_));
        window_.getFacade().getGame().getPlayer().getTeam().add(pk(window_));
        tryClick(window_.getScenePanel().getReceiveParents());
        assertEq(8,window_.getFacade().getGame().getPlayer().getTeam().size());
        assertFalse(window_.getFacade().getGame().getHostedPk().getList().get(0).getValue().isFree());
    }
    @Test
    public void revive() {
        WindowAiki window_ = newProg();
        loadRomGameRevive(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        assertEq(2,window_.getFacade().getGame().getPlayer().getTeam().size());
    }
    private static void loadRomGameStore(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(newGerantPokemon(GeranceType.HOST)));
        Game game_ = build(_window.getFacade());
        game_.setPlayerOrientation(Direction.LEFT);
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
    private static void loadRomGameRevive(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity());
        Game game_ = build(_window.getFacade());
        game_.setPlayerOrientation(Direction.LEFT);
        game_.getPlayer().getInventory().getItem(SNOW);
        loadGame(_window, game_);
    }
}
