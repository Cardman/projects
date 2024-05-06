package aiki.gui;

import aiki.facade.ItemsBuySellMap;
import aiki.game.Game;
import aiki.gui.components.PaginatorItem;
import aiki.gui.components.PaginatorMove;
import aiki.gui.dialogs.SelectItem;
import aiki.gui.dialogs.SelectTm;
import aiki.map.enums.Direction;
import code.gui.AbsCustComponent;
import code.maths.LgInt;
import code.mock.MockCustComponent;
import code.util.IdList;
import org.junit.Test;

public final class BuyItemMenusTest extends InitDbGuiAiki {
    @Test
    public void menus() {
        WindowAiki window_ = newProg();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(7, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuySell()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getItemsPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(0,window_.getScenePanel().getItemsPan().getListe().size());
    }

    @Test
    public void buyIt1() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }

    @Test
    public void buyIt2() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(28, tr_.size());
        checkCommon20(pag_, tr_);
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
    public void buyIt3() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(7, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuySell()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getItemsPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getItemsPan().getListe().size());
    }

    @Test
    public void buyIt4() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getAddItemBuy());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(7, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuySell()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getItemsPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getItemsPan().getListe().size());
        ItemsBuySellMap choice_ = window_.getFacade().getChosenItemsForBuyOrSell();
        assertEq(1, choice_.size());
        assertEq(POKE_BALL, choice_.getKey(0));
        assertEq(LgInt.one(), choice_.getValue(0));
    }

    @Test
    public void buyIt5() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getRemoveItemBuy());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(7, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuySell()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getItemsPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getItemsPan().getListe().size());
        ItemsBuySellMap choice_ = window_.getFacade().getChosenItemsForBuyOrSell();
        assertEq(1, choice_.size());
        assertEq(POKE_BALL, choice_.getKey(0));
        assertEq(LgInt.one(), choice_.getValue(0));
    }

    @Test
    public void buyIt6() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getItemsPan().getListe().select(0);
        window_.getScenePanel().getItemsPan().getListe().events();
        tryClick(window_.getScenePanel().getAddItemBuy());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(7, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuySell()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getItemsPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getItemsPan().getListe().size());
        ItemsBuySellMap choice_ = window_.getFacade().getChosenItemsForBuyOrSell();
        assertEq(1, choice_.size());
        assertEq(POKE_BALL, choice_.getKey(0));
        assertEq(new LgInt(2), choice_.getValue(0));
    }

    @Test
    public void buyIt7() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getItemsPan().getListe().select(0);
        window_.getScenePanel().getItemsPan().getListe().events();
        tryClick(window_.getScenePanel().getRemoveItemBuy());
        IdList<AbsCustComponent> tree_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(7, tree_.size());
        assertTrue(tree_.containsObj(window_.getScenePanel().getAddItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getRemoveItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getSelectItemBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuy()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getBuySell()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getItemsPan().getListe().getGlobal()));
        assertTrue(tree_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getItemsPan().getListe().size());
        ItemsBuySellMap choice_ = window_.getFacade().getChosenItemsForBuyOrSell();
        assertEq(1, choice_.size());
        assertEq(POKE_BALL, choice_.getKey(0));
        assertEq(LgInt.zero(), choice_.getValue(0));
    }

    @Test
    public void buyIt8() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().getGame().getPlayer().setMoney(LgInt.zero());
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getBuySell());
        assertEq(LgInt.zero(), window_.getFacade().getGame().getPlayer().getInventory().getNumber(POKE_BALL));
    }

    @Test
    public void buyIt9() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getBuySell());
        assertEq(LgInt.one(), window_.getFacade().getGame().getPlayer().getInventory().getNumber(POKE_BALL));
    }

    @Test
    public void buyIt10() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().getGame().getPlayer().getInventory().getItem(POKE_BALL);
        window_.getFacade().getGame().getPlayer().setMoney(LgInt.zero());
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getBuy());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getBuySell());
        assertEq(LgInt.zero(), window_.getFacade().getGame().getPlayer().getInventory().getNumber(POKE_BALL));
        assertEq(LgInt.one(), window_.getFacade().getGame().getPlayer().getMoney());
    }

    @Test
    public void buyIt11() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getBuy());
        tryClick(window_.getScenePanel().getBuy());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getBuySell());
        assertEq(LgInt.one(), window_.getFacade().getGame().getPlayer().getInventory().getNumber(POKE_BALL));
    }

    @Test
    public void okNoItem() {
        WindowAiki window_ = newSelIt();
        loadRomGameBuy(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getButtonInteract());
        tryClick(window_.getScenePanel().getSelectItemBuy());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertFalse(SelectItem.isSelectedIndex(window_.getSelectItem()));
    }
    private static void loadRomGameBuy(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(sellerWithItem(newSellerItems(),POKE_BALL)));
        Game game_ = build(_window.getFacade());
        game_.setPlayerOrientation(Direction.LEFT);
        loadGame(_window, game_);
    }
}
