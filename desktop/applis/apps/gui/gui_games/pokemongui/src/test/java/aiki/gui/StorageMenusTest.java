package aiki.gui;

import aiki.game.Game;
import aiki.gui.components.PaginatorEgg;
import aiki.gui.components.PaginatorPokemon;
import aiki.gui.dialogs.SelectEgg;
import aiki.gui.dialogs.SelectPokemon;
import aiki.map.characters.enums.GeranceType;
import aiki.map.enums.Direction;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import code.gui.AbsCustComponent;
import code.maths.LgInt;
import code.mock.MockCustComponent;
import code.util.IdList;
import org.junit.Test;

public final class StorageMenusTest extends InitDbGuiAiki {
    @Test
    public void switchPkEggs() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(4, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(3,window_.getScenePanel().getTeamPan().getListe().size());
    }
    @Test
    public void store1() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getStore()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    @Test
    public void store2() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getStore());
        assertEq(1,window_.getFacade().getGame().getPlayer().getBox().size());
        assertEq(2,window_.getFacade().getGame().getPlayer().getTeam().size());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(4, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    @Test
    public void store3() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getStore()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    @Test
    public void store4() {
        WindowAiki window_ = newProg();
        loadRomGameStore(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getStore());
        assertEq(1,window_.getFacade().getGame().getPlayer().getBox().size());
        assertEq(2,window_.getFacade().getGame().getPlayer().getTeam().size());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(4, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    @Test
    public void withdrawPk1() {
        WindowAiki window_ = newSelPk();
        loadRomGameWithdraw(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectPkBox());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(32, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }
    @Test
    public void withdrawPk2() {
        WindowAiki window_ = newSelPk();
        loadRomGameWithdraw(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectPkBox());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(38, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }
    @Test
    public void withdrawPk3() {
        WindowAiki window_ = newSelPk();
        loadRomGameWithdraw(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectPkBox());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getWithdraw()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRelease()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    @Test
    public void withdrawPk4() {
        WindowAiki window_ = newSelPk();
        loadRomGameWithdraw(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectPkBox());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getWithdraw());
        assertEq(1,window_.getFacade().getGame().getPlayer().getBox().size());
        assertEq(2,window_.getFacade().getGame().getPlayer().getTeam().size());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(4, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    @Test
    public void withdrawEgg1() {
        WindowAiki window_ = newSelEgg();
        loadRomGameWithdraw(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectEggBox());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(14, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }
    @Test
    public void withdrawEgg2() {
        WindowAiki window_ = newSelEgg();
        loadRomGameWithdraw(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectEggBox());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(20, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }
    @Test
    public void withdrawEgg3() {
        WindowAiki window_ = newSelEgg();
        loadRomGameWithdraw(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectEggBox());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getWithdrawEgg()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRelease()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    @Test
    public void withdrawEgg4() {
        WindowAiki window_ = newSelEgg();
        loadRomGameWithdraw(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectEggBox());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getWithdrawEgg());
        assertEq(1,window_.getFacade().getGame().getPlayer().getBox().size());
        assertEq(2,window_.getFacade().getGame().getPlayer().getTeam().size());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(4, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    @Test
    public void withdrawIt1() {
        WindowAiki window_ = newSelPk();
        loadRomGameWithdrawIt(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectPkBox());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(7, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getWithdraw()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTakeItem()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRelease()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    @Test
    public void withdrawIt2() {
        WindowAiki window_ = newSelPk();
        loadRomGameWithdrawIt(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectPkBox());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getTakeItem());
        assertEq(2,window_.getFacade().getGame().getPlayer().getBox().size());
        assertEq(1,window_.getFacade().getGame().getPlayer().getTeam().size());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getWithdraw()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRelease()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(NULL_REF,((PokemonPlayer)window_.getFacade().getGame().getPlayer().getBox().get(0)).getItem());
        assertEq(LgInt.one(),window_.getFacade().getGame().getPlayer().getInventory().getNumber(POKE_BALL));
    }
    @Test
    public void switchBoxTeam() {
        WindowAiki window_ = newSelPk();
        loadRomGameWithdrawTwo(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectPkBox());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(8, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectEggBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectPkBox()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getStore()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getWithdraw()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRelease()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSwitchPk()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    private static void loadRomGameStore(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(newGerantPokemon(GeranceType.HOST)));
        Game game_ = build(_window.getFacade());
        game_.setPlayerOrientation(Direction.DOWN);
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getTeam().add(new Egg(PIKACHU));
    }

    private static void loadRomGameWithdraw(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(newGerantPokemon(GeranceType.HOST)));
        Game game_ = build(_window.getFacade());
        game_.setPlayerOrientation(Direction.DOWN);
        loadGame(_window, game_);
        game_.getPlayer().getBox().add(pk(_window));
        game_.getPlayer().getBox().add(new Egg(PIKACHU));
    }

    private static void loadRomGameWithdrawTwo(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(newGerantPokemon(GeranceType.HOST)));
        Game game_ = build(_window.getFacade());
        game_.setPlayerOrientation(Direction.DOWN);
        loadGame(_window, game_);
        game_.getPlayer().getTeam().add(pk(_window));
        game_.getPlayer().getBox().add(pk(_window));
        game_.getPlayer().getBox().add(new Egg(PIKACHU));
    }
    private static void loadRomGameWithdrawIt(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(newGerantPokemon(GeranceType.HOST)));
        Game game_ = build(_window.getFacade());
        game_.setPlayerOrientation(Direction.DOWN);
        loadGame(_window, game_);
        game_.getPlayer().getBox().add(pkIt(_window));
        game_.getPlayer().getBox().add(new Egg(PIKACHU));
    }
}
