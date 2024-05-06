package aiki.gui;

import aiki.game.Game;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import code.gui.AbsCustComponent;
import code.mock.MockCustComponent;
import code.util.IdList;
import org.junit.Test;

public final class LearnMtMenusTest extends InitDbGuiAiki {
    @Test
    public void menus() {
        WindowAiki window_ = newProg();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
    }
    @Test
    public void learn1() {
        WindowAiki window_ = newProg();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertEq(2,window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
        assertTrue(tree_.containsObj(window_.getScenePanel().getMovesLearntList().get(1)));
        assertTrue(tree_.containsObj(window_.getScenePanel().getOkMoveTutor()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getCancelMoveTutor()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
    }
    @Test
    public void learn2() {
        WindowAiki window_ = newProg();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getTeamPan().getListe().select(-1);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
    }
    @Test
    public void learn3() {
        WindowAiki window_ = newProg();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getMovesLearntListLabel().get(0).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertEq(2,window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
        assertTrue(tree_.containsObj(window_.getScenePanel().getMovesLearntList().get(1)));
        assertTrue(tree_.containsObj(window_.getScenePanel().getOkMoveTutor()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getCancelMoveTutor()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
    }
    @Test
    public void learn4() {
        WindowAiki window_ = newProg();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getMovesLearntListLabel().get(0).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        tryClick(window_.getScenePanel().getOkMoveTutor());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertEq(2,window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
        assertTrue(tree_.containsObj(window_.getScenePanel().getMovesLearntList().get(1)));
        assertTrue(tree_.containsObj(window_.getScenePanel().getOkMoveTutor()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getCancelMoveTutor()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
    }
    @Test
    public void learn5() {
        WindowAiki window_ = newProg();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getMovesLearntListLabel().get(1).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertEq(2,window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
        assertTrue(tree_.containsObj(window_.getScenePanel().getMovesLearntList().get(1)));
        assertTrue(tree_.containsObj(window_.getScenePanel().getOkMoveTutor()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getCancelMoveTutor()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
    }
    @Test
    public void learn6() {
        WindowAiki window_ = newProg();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getMovesLearntListLabel().get(1).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        tryClick(window_.getScenePanel().getOkMoveTutor());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(2,((PokemonPlayer)window_.getFacade().getGame().getPlayer().getTeam().get(0)).getMoves().size());
    }
    @Test
    public void learn7() {
        WindowAiki window_ = newProg();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getMovesLearntListLabel().get(1).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        tryClick(window_.getScenePanel().getCancelMoveTutor());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertEq(2,window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
        assertTrue(tree_.containsObj(window_.getScenePanel().getMovesLearntList().get(1)));
        assertTrue(tree_.containsObj(window_.getScenePanel().getOkMoveTutor()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getCancelMoveTutor()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(1,((PokemonPlayer)window_.getFacade().getGame().getPlayer().getTeam().get(0)).getMoves().size());
    }
    private static void loadRomGameBuy(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(newSellerMt()));
        Game game_ = build(_window.getFacade());
        game_.setPlayerOrientation(Direction.LEFT);
        loadGame(_window, game_);
    }
}
