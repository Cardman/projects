package aiki.gui;

import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.gui.components.PaginatorEgg;
import aiki.gui.components.PaginatorPokemon;
import aiki.gui.dialogs.SelectEgg;
import aiki.gui.dialogs.SelectPokemon;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import code.gui.AbsCustComponent;
import code.mock.MockCustComponent;
import code.util.IdList;
import org.junit.Test;

public final class PlayerMenusTest extends InitDbGuiAiki {
    @Test
    public void progress() {
        WindowAiki window_ = newProg();
        window_.getCore().getAikiFactory().setPreparedProgTask(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),nav()));
        window_.setPreparedProgTask(window_.getCore().getAikiFactory().getPreparedProgTask());
        loadRomGame(window_);
        tryClick(window_.getScenePanel().getGame());
        assertTrue(window_.getDialogGameProgess().getAbsDialog().isVisible());
    }

    @Test
    public void selEgg1() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggs(window_);
        tryClick(window_.getScenePanel().getSeeEggs());
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
    public void selEgg2() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggs(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
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
    public void selEgg3() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggs(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        pag_.getModeName().setSelectedItem(SearchingMode.SUBSTRING);
        pag_.getModeName().getCombo().events(null);
        pag_.getName().setText(PIKACHU_TR.substring(1,2));
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
    public void selEgg4() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggs(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        pag_.getModeName().setSelectedItem(SearchingMode.SUBSTRING);
        pag_.getModeName().getCombo().events(null);
        pag_.getName().setText(PIKACHU_TR.substring(1,2));
        pag_.getModeName().setSelectedItem(SearchingMode.WHOLE_STRING);
        pag_.getModeName().getCombo().events(null);
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(14, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }

    @Test
    public void selEgg5() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggs(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        pag_.getMinSteps().setText("2");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(14, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }

    @Test
    public void selEgg6() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggs(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        pag_.getMaxSteps().setText("2");
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
    public void selEgg7() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsTwice(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(21, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selEgg8() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsTwice(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(21, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selEgg9() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(21, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selEgg10() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selEgg11() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        tryClick(pag_.getNext());
        tryClick(pag_.getPrevious());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selEgg12() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        pag_.getDelta().setText("1");
        tryClick(pag_.getNextDelta());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selEgg13() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        pag_.getDelta().setText("1");
        tryClick(pag_.getNextDelta());
        tryClick(pag_.getNextDelta());
        tryClick(pag_.getPreviousDelta());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selEgg14() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        pag_.getPages().selectItem(1);
        pag_.getPages().getCombo().events(null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selEgg15() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsTwice(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getEnd());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(21, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selEgg16() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsTwice(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getEnd());
        tryClick(pag_.getBegin());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(21, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selEgg17() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(3,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(2).getPaintableLabel()));
    }

    @Test
    public void selEgg18() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggs(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNewSearchButton());
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
    public void selEgg19() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(3,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(2).getPaintableLabel()));
        assertFalse(pag_.getResultsLabels().get(0).isSelected());
        assertTrue(pag_.getResultsLabels().get(1).isSelected());
        assertFalse(pag_.getResultsLabels().get(2).isSelected());
    }

    @Test
    public void selEgg20() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        tryClick(pag_.getResultsLabels().get(1));
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(22, tr_.size());
        checkCommon12(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(3,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(2).getPaintableLabel()));
        assertFalse(pag_.getResultsLabels().get(0).isSelected());
        assertFalse(pag_.getResultsLabels().get(1).isSelected());
        assertFalse(pag_.getResultsLabels().get(2).isSelected());
    }

    @Test
    public void selEgg() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        tryClick(sel_.getOkButton());
        assertFalse(sel_.getSelectDial().isVisible());
    }

    @Test
    public void selPk1() {
        WindowAiki window_ = newSelPk();
        loadRomGamePks(window_);
        tryClick(window_.getScenePanel().getSeeBoxes());
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
    public void selPk2() {
        WindowAiki window_ = newSelPk();
        loadRomGamePks(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
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
    public void selPk3() {
        WindowAiki window_ = newSelPk();
        loadRomGamePks(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        pag_.getModeName().setSelectedItem(SearchingMode.SUBSTRING);
        pag_.getModeName().getCombo().events(null);
        pag_.getName().setText(PIKACHU_TR.substring(1,2));
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
    public void selPk4() {
        WindowAiki window_ = newSelPk();
        loadRomGamePks(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        pag_.getModeName().setSelectedItem(SearchingMode.SUBSTRING);
        pag_.getModeName().getCombo().events(null);
        pag_.getName().setText(PIKACHU_TR.substring(1,2));
        pag_.getModeName().setSelectedItem(SearchingMode.WHOLE_STRING);
        pag_.getModeName().getCombo().events(null);
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(32, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }

    @Test
    public void selPk5() {
        WindowAiki window_ = newSelPk();
        loadRomGamePks(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        pag_.getMinLevel().setText("2");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(32, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }

    @Test
    public void selPk6() {
        WindowAiki window_ = newSelPk();
        loadRomGamePks(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        pag_.getMaxLevel().setText("2");
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
    public void selPk7() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksTwice(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(39, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selPk8() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksTwice(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(39, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selPk9() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(39, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selPk10() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(40, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selPk11() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        tryClick(pag_.getNext());
        tryClick(pag_.getPrevious());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(40, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selPk12() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        pag_.getDelta().setText("1");
        tryClick(pag_.getNextDelta());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(40, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selPk13() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        pag_.getDelta().setText("1");
        tryClick(pag_.getNextDelta());
        tryClick(pag_.getNextDelta());
        tryClick(pag_.getPreviousDelta());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(40, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selPk14() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        pag_.getPages().selectItem(1);
        pag_.getPages().getCombo().events(null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(40, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selPk15() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksTwice(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getEnd());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(39, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selPk16() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksTwice(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getEnd());
        tryClick(pag_.getBegin());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(39, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void selPk17() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(40, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(3,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(2).getPaintableLabel()));
    }

    @Test
    public void selPk18() {
        WindowAiki window_ = newSelPk();
        loadRomGamePks(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNewSearchButton());
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
    public void selPk19() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(41, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(pag_.getDetailButton()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(3,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(2).getPaintableLabel()));
        assertFalse(pag_.getResultsLabels().get(0).isSelected());
        assertTrue(pag_.getResultsLabels().get(1).isSelected());
        assertFalse(pag_.getResultsLabels().get(2).isSelected());
    }

    @Test
    public void selPk20() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        tryClick(pag_.getResultsLabels().get(1));
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(40, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(3,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(2).getPaintableLabel()));
        assertFalse(pag_.getResultsLabels().get(0).isSelected());
        assertFalse(pag_.getResultsLabels().get(1).isSelected());
        assertFalse(pag_.getResultsLabels().get(2).isSelected());
    }

    @Test
    public void selPk21() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimesIt(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        pag_.getWithItem().setSelectedItem(SelectedBoolean.YES);
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(41, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(pag_.getDetailButton()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(3,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(2).getPaintableLabel()));
        assertFalse(pag_.getResultsLabels().get(0).isSelected());
        assertTrue(pag_.getResultsLabels().get(1).isSelected());
        assertFalse(pag_.getResultsLabels().get(2).isSelected());
    }
    @Test
    public void selPk() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        tryClick(sel_.getOkButton());
        assertFalse(sel_.getSelectDial().isVisible());
    }

    @Test
    public void consBoxPk() {
        WindowAiki window_ = newSelPkCons();
        window_.getCore().getAikiFactory().setPreparedPkTask(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),nav()));
        window_.setPreparedPkTask(window_.getCore().getAikiFactory().getPreparedPkTask());
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        tryClick(pag_.getDetailButton());
        assertTrue(sel_.getPkDetailContent().getContent().isVisible());
    }

    private static void loadRomGameEggs(WindowAiki _window) {
        loadRomGame(_window);
        _window.getFacade().getGame().getPlayer().getBox().add(new Egg(PIKACHU));
    }
    private static void loadRomGameEggsTwice(WindowAiki _window) {
        loadRomGame(_window);
        _window.getFacade().getGame().getPlayer().getBox().add(new Egg(PIKACHU));
        _window.getFacade().getGame().getPlayer().getBox().add(new Egg(PIKACHU));
    }
    private static void loadRomGameEggsThreeTimes(WindowAiki _window) {
        loadRomGame(_window);
        _window.getFacade().getGame().getPlayer().getBox().add(new Egg(PIKACHU));
        _window.getFacade().getGame().getPlayer().getBox().add(new Egg(PIKACHU));
        _window.getFacade().getGame().getPlayer().getBox().add(new Egg(PIKACHU));
    }
    private static void loadRomGamePks(WindowAiki _window) {
        loadRomGame(_window);
        _window.getFacade().getGame().getPlayer().getBox().add(pk(_window));
    }
    private static void loadRomGamePksTwice(WindowAiki _window) {
        loadRomGame(_window);
        _window.getFacade().getGame().getPlayer().getBox().add(pk(_window));
        _window.getFacade().getGame().getPlayer().getBox().add(pk(_window));
    }
    private static void loadRomGamePksThreeTimes(WindowAiki _window) {
        loadRomGame(_window);
        _window.getFacade().getGame().getPlayer().getBox().add(pk(_window));
        _window.getFacade().getGame().getPlayer().getBox().add(pk(_window));
        _window.getFacade().getGame().getPlayer().getBox().add(pk(_window));
    }
    private static void loadRomGamePksThreeTimesIt(WindowAiki _window) {
        loadRomGame(_window);
        _window.getFacade().getGame().getPlayer().getBox().add(pkIt(_window));
        _window.getFacade().getGame().getPlayer().getBox().add(pkIt(_window));
        _window.getFacade().getGame().getPlayer().getBox().add(pkIt(_window));
    }

    private static PokemonPlayer pkIt(WindowAiki _window) {
        PokemonPlayer pk_ = pk(_window);
        pk_.setItem(POKE_BALL);
        return pk_;
    }

    private static PokemonPlayer pk(WindowAiki _window) {
        return new PokemonPlayer(new Egg(PIKACHU), _window.getFacade().getData());
    }

    private static void loadRomGame(WindowAiki _window) {
        loadRom(_window, coreDataBase());
        loadGame(_window, build(_window.getFacade()));
    }

    private void checkCommon12(PaginatorEgg _pag, IdList<AbsCustComponent> _tr) {
        assertTrue(_tr.containsObj(_pag.getDelta()));
        assertTrue(_tr.containsObj(_pag.getNbResults()));
        assertTrue(_tr.containsObj(_pag.getMinSteps()));
        assertTrue(_tr.containsObj(_pag.getMaxSteps()));
        assertTrue(_tr.containsObj(_pag.getCmpStepsPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpStepsSorting().self()));
        assertTrue(_tr.containsObj(_pag.getName()));
        assertTrue(_tr.containsObj(_pag.getModeName().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNamePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNameSorting().self()));
        assertTrue(_tr.containsObj(_pag.getSearchButton()));
        assertTrue(_tr.containsObj(_pag.getNewSearchButton()));
    }

    private void checkCommon30(PaginatorPokemon _pag, IdList<AbsCustComponent> _tr) {
        assertTrue(_tr.containsObj(_pag.getDelta()));
        assertTrue(_tr.containsObj(_pag.getNbResults()));
        assertTrue(_tr.containsObj(_pag.getCmpPossEvosPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPossEvosSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpItemPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpItemSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpGenderPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpGenderSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpLevelPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpLevelSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpAbilityPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpAbilitySorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNamePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNameSorting().self()));
        assertTrue(_tr.containsObj(_pag.getName()));
        assertTrue(_tr.containsObj(_pag.getAbility()));
        assertTrue(_tr.containsObj(_pag.getMoves()));
        assertTrue(_tr.containsObj(_pag.getItem()));
        assertTrue(_tr.containsObj(_pag.getGender().self()));
        assertTrue(_tr.containsObj(_pag.getWithItem().self()));
        assertTrue(_tr.containsObj(_pag.getModeName().self()));
        assertTrue(_tr.containsObj(_pag.getModeAbility().self()));
        assertTrue(_tr.containsObj(_pag.getModeMoves().self()));
        assertTrue(_tr.containsObj(_pag.getModeItem().self()));
        assertTrue(_tr.containsObj(_pag.getMaxLevel()));
        assertTrue(_tr.containsObj(_pag.getMinLevel()));
        assertTrue(_tr.containsObj(_pag.getMaxPossEvos()));
        assertTrue(_tr.containsObj(_pag.getMinPossEvos()));
        assertTrue(_tr.containsObj(_pag.getSearchButton()));
        assertTrue(_tr.containsObj(_pag.getNewSearchButton()));
    }

}
