package aiki.gui;

import aiki.game.Game;
import aiki.game.fight.Fight;
import aiki.gui.threads.Painting;
import aiki.map.characters.enums.GeranceType;
import aiki.map.enums.Direction;
import code.gui.AbsButton;
import code.gui.AbsCustComponent;
import code.gui.GuiConstants;
import code.mock.MockCustComponent;
import code.mock.MockPanel;
import code.mock.MockThreadFactory;
import code.util.IdList;
import code.util.core.BoolVal;
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
    @Test
    public void move3() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_UP);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void move4() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getPad().getUp());
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
    public void move5() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_UP);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_UP);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void move6() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getPad().getUp());
        tryPress(window_.getScenePanel().getPad().getUp());
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
    public void move7() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_LEFT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void move8() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getPad().getLeft());
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
    @Test
    public void move9() {
        WindowAiki window_ = newProg();
        loadRomGameTwoJoinPlaces(window_);
        assertEq(0,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        assertEq(1,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
    }
    @Test
    public void move10() {
        WindowAiki window_ = newProg();
        loadRomGameTwoJoinPlaces(window_);
        assertEq(0,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
        tryPress(window_.getScenePanel().getPad().getRight());
        tryPress(window_.getScenePanel().getPad().getRight());
        assertEq(1,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
    }
    @Test
    public void move11() {
        WindowAiki window_ = newProg();
        loadRomGameTwoLinkPlaces(window_);
        assertEq(0,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_UP);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        assertEq(1,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
    }
    @Test
    public void move12() {
        WindowAiki window_ = newProg();
        loadRomGameTwoLinkPlaces(window_);
        assertEq(0,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
        tryPress(window_.getScenePanel().getPad().getUp());
        assertEq(1,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
    }
    @Test
    public void move13() {
        WindowAiki window_ = newProg();
        coreDataBaseFish(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        assertTrue(((MockCustComponent)window_.getScenePanel().getFish()).isDeepAccessible());
    }
    @Test
    public void move14() {
        WindowAiki window_ = newProg();
        coreDataBaseFish(window_);
        tryPress(window_.getScenePanel().getPad().getRight());
        assertTrue(((MockCustComponent)window_.getScenePanel().getFish()).isDeepAccessible());
    }
    @Test
    public void move15() {
        WindowAiki window_ = newProg();
        coreDataBaseFish(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAnNoCheck((MockThreadFactory) window_.getFrames().getThreadFactory());
        assertTrue(window_.getFacade().getFight().getFightType().isExisting());
        assertTrue(window_.getFacade().getFight().getFightType().isWild());
    }
    @Test
    public void move16() {
        WindowAiki window_ = newProg();
        coreDataBaseFish(window_);
        tryPress(window_.getScenePanel().getPad().getRight());
        tryPress(window_.getScenePanel().getPad().getRight());
        assertTrue(window_.getFacade().getFight().getFightType().isExisting());
        assertTrue(window_.getFacade().getFight().getFightType().isWild());
    }
    @Test
    public void move17() {
        WindowAiki window_ = newProg();
        loadRomGameTwoJoinPlacesGymTrainer(window_);
        assertEq(0,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
        tryPress(window_.getScenePanel().getPad().getMiddle());
        tryPress(window_.getScenePanel().getPad().getMiddle());
        assertEq(0,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
    }
    @Test
    public void move18() {
        WindowAiki window_ = newProg();
        loadRomGameTwoJoinPlacesGymTrainer(window_);
        tryClick((AbsButton) ((MockPanel)window_.getScenePanel().getGymTrainer()).getComponent(0));
        assertTrue(window_.getFacade().getGame().getGymTrainer().isValid());
        window_.getFacade().getGame().getFight().getTemp().getKos().put(Fight.CST_FOE, BoolVal.TRUE);
        window_.getFacade().getGame().endFight(window_.getFacade().getData());
        window_.getBattle().getBattle().endFight();
        assertEq(0,window_.getScenePanel().getGymTrainer().getComponentCount());
    }
    @Test
    public void noMove1() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_LEFT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_LEFT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_LEFT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void noMove2() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getPad().getLeft());
        tryPress(window_.getScenePanel().getPad().getLeft());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryPress(window_.getScenePanel().getPad().getLeft());
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
    public void noAnim1() {
        WindowAiki window_ = newProg();
        window_.getLoadingConf().setEnableMovingHerosAnimation(false);
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_LEFT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_LEFT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_LEFT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
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
    public void noAnim2() {
        WindowAiki window_ = newProg();
        window_.getLoadingConf().setEnableMovingHerosAnimation(false);
        loadRomGameStore(window_);
        tryPress(window_.getScenePanel().getPad().getLeft());
        tryPress(window_.getScenePanel().getPad().getLeft());
        tryClick(window_.getScenePanel().getButtonInteract());
        tryPress(window_.getScenePanel().getPad().getLeft());
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
    public void noAnim3() {
        WindowAiki window_ = newProg();
        window_.getLoadingConf().setEnableMovingHerosAnimation(false);
        coreDataBaseFish(window_);
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAn((MockThreadFactory) window_.getFrames().getThreadFactory());
        tryPress(window_.getScenePanel().getScene(), GuiConstants.VK_RIGHT);
        tryAnNoCheck((MockThreadFactory) window_.getFrames().getThreadFactory());
        assertTrue(window_.getFacade().getFight().getFightType().isExisting());
        assertTrue(window_.getFacade().getFight().getFightType().isWild());
    }
    @Test
    public void noAnim4() {
        WindowAiki window_ = newProg();
        window_.getLoadingConf().setEnableMovingHerosAnimation(false);
        coreDataBaseFish(window_);
        tryPress(window_.getScenePanel().getPad().getRight());
        tryPress(window_.getScenePanel().getPad().getRight());
        assertTrue(window_.getFacade().getFight().getFightType().isExisting());
        assertTrue(window_.getFacade().getFight().getFightType().isWild());
    }
    @Test
    public void paint() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        window_.getScenePanel().getPaintingScene().set(true);
        new Painting(window_.getScenePanel().getScene(), window_.getFacade(), Direction.UP, window_, null).run();
        assertTrue(window_.getScenePanel().getPaintingScene().get());
    }
    private static void loadRomGameStore(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(newGerantPokemon(GeranceType.HOST)));
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
    private static void loadRomGameTwoJoinPlaces(WindowAiki _window) {
        loadRom(_window, coreDataBaseTwoJoinPlaces());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }

    private static void loadRomGameTwoJoinPlacesGymTrainer(WindowAiki _window) {
        loadRom(_window, coreDataBaseTwoJoinPlacesGymTrainer());
        Game game_ = build(_window.getFacade());
        game_.setPlayerCoords(newCoords(0,0,0,0,0,0));
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
    private static void loadRomGameTwoLinkPlaces(WindowAiki _window) {
        loadRom(_window, coreDataBaseTwoLinkPlaces());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }

    private static void coreDataBaseFish(WindowAiki _window) {
        loadRom(_window, coreDataBaseFish());
        Game game_ = build(_window.getFacade());
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(pk(_window));
    }
}
