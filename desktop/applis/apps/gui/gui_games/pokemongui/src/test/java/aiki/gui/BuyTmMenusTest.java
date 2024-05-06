package aiki.gui;

import aiki.game.Game;
import aiki.gui.components.PaginatorMove;
import aiki.gui.dialogs.SelectTm;
import aiki.map.enums.Direction;
import code.gui.AbsCustComponent;
import code.maths.LgInt;
import code.mock.MockCustComponent;
import code.util.IdList;
import org.junit.Test;

public final class BuyTmMenusTest extends InitDbGuiAiki {
    @Test
    public void hostMenus() {
        WindowAiki window_ = newProg();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTmPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(0,window_.getScenePanel().getTmPanel().getListe().size());
    }
    @Test
    public void byTm1() {
        WindowAiki window_ = newSelMv();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getAddTmBuy());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon28(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }
    @Test
    public void byTm2() {
        WindowAiki window_ = newSelMv();
        loadRomGameBuy(window_);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getAddTmBuy());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(36, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void byTm3() {
        WindowAiki window_ = newSelMv();
        loadRomGameBuy(window_);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getAddTmBuy());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTmPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTmPanel().getListe().size());
    }
    @Test
    public void byTm4() {
        WindowAiki window_ = newSelMv();
        loadRomGameBuy(window_);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getAddTmBuy());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getRemoveTmBuy());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTmPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTmPanel().getListe().size());
    }
    @Test
    public void byTm5() {
        WindowAiki window_ = newSelMv();
        loadRomGameBuy(window_);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getAddTmBuy());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTmPanel().getListe().select(0);
        window_.getScenePanel().getTmPanel().getListe().events();
        tryClick(window_.getScenePanel().getRemoveTmBuy());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTmPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(0,window_.getScenePanel().getTmPanel().getListe().size());
    }
    @Test
    public void byTm6() {
        WindowAiki window_ = newSelMv();
        loadRomGameBuy(window_);
        window_.getFacade().getGame().getPlayer().setMoney(LgInt.zero());
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getAddTmBuy());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getTmBuy());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(5, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTmBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getTmPanel().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTmPanel().getListe().size());
    }
    @Test
    public void byTm7() {
        WindowAiki window_ = newSelMv();
        loadRomGameBuy(window_);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getAddTmBuy());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getTmBuy());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(0, tree_.size());
        assertTrue(window_.getFacade().getGame().getPlayer().getInventory().gotTm().containsObj(2));
    }
    @Test
    public void okNoTm() {
        WindowAiki window_ = newSelMv();
        loadRomGameBuy(window_);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getAddTmBuy());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        assertFalse(SelectTm.isSelectedIndex(window_.getSelectTm()));
    }
    private static void loadRomGameBuy(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(sellerWithTm(sellerWithTm(newSellerTm(),2),3)));
        Game game_ = build(_window.getFacade());
        game_.setPlayerOrientation(Direction.LEFT);
        loadGame(_window, game_);
    }
}
