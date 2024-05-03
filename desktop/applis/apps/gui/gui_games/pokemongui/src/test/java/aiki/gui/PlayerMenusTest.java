package aiki.gui;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.gui.components.PaginatorEgg;
import aiki.gui.components.PaginatorMove;
import aiki.gui.components.PaginatorPokemon;
import aiki.gui.dialogs.ConsultHosts;
import aiki.gui.dialogs.SelectEgg;
import aiki.gui.dialogs.SelectPokemon;
import aiki.gui.dialogs.SelectTm;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.map.characters.enums.GeranceType;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import code.gui.AbsButton;
import code.gui.AbsCommonFrame;
import code.gui.AbsCustComponent;
import code.maths.Rate;
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
        preparePkTask(window_);
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
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(43, tr_.size());
        checkCommon30(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(pag_.getDetailButton()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertTrue(tr_.containsObj(sel_.getPkDetailContent().getField()));
        assertTrue(tr_.containsObj(sel_.getPkDetailContent().getSearch()));
        assertEq(3,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(2).getPaintableLabel()));
    }

    @Test
    public void consHost1() {
        WindowAiki window_ = newHostCons();
        preparePkTask(window_);
        loadRomGameOneHost(window_);
        tryClick(window_.getScenePanel().getHost());
        ConsultHosts sel_ = window_.getConsultHosts();
        AbsCommonFrame pag_ = sel_.getAbsDialog();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) pag_.getPane()).getTreeAccessible();
        assertEq(2,tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) pag_.getPane()).getTreeAccessible();
        assertEq(4,tr2_.size());
        assertTrue(tr2_.containsAllObj(tr_));
        assertTrue(tr2_.containsObj(sel_.getPkDetailContent().getField()));
        assertTrue(tr2_.containsObj(sel_.getPkDetailContent().getSearch()));
    }

    @Test
    public void consHost2() {
        WindowAiki window_ = newHostCons();
        preparePkTask(window_);
        loadRomGameOneHost(window_);
        tryClick(window_.getScenePanel().getHost());
        ConsultHosts sel_ = window_.getConsultHosts();
        AbsCommonFrame pag_ = sel_.getAbsDialog();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) pag_.getPane()).getTreeAccessible();
        assertEq(2,tr_.size());
        tryClick((AbsButton) tr_.get(1));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) pag_.getPane()).getTreeAccessible();
        assertEq(4,tr2_.size());
        assertTrue(tr2_.containsAllObj(tr_));
        assertTrue(tr2_.containsObj(sel_.getPkDetailContent().getField()));
        assertTrue(tr2_.containsObj(sel_.getPkDetailContent().getSearch()));
    }

    @Test
    public void consHost3() {
        WindowAiki window_ = newHostCons();
        preparePkTask(window_);
        loadRomGameHost(window_);
        tryClick(window_.getScenePanel().getHost());
        ConsultHosts sel_ = window_.getConsultHosts();
        AbsCommonFrame pag_ = sel_.getAbsDialog();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) pag_.getPane()).getTreeAccessible();
        assertEq(0,tr_.size());
    }

    @Test
    public void selTm1() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        tryClick(window_.getScenePanel().getTm());
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
    public void selTm2() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm3() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm4() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        tryClick(pag_.getPrevious());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm5() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getEnd());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm6() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getEnd());
        tryClick(pag_.getBegin());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm7() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        pag_.getPages().selectItem(1);
        pag_.getPages().getCombo().events(null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm8() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        pag_.getPages().selectItem(1);
        pag_.getPages().getCombo().events(null);
        pag_.getPages().selectItem(0);
        pag_.getPages().getCombo().events(null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm9() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNextDelta());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm10() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNextDelta());
        tryClick(pag_.getPreviousDelta());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm11() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getDelta().setText("1");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm12() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(2);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(2,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
    }

    @Test
    public void selTm13() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getDamaging().setSelectedItem(SelectedBoolean.YES);
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void selTm14() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getDamaging().setSelectedItem(SelectedBoolean.NO);
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon28(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }

    @Test
    public void selTm15() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNewSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(37, tr_.size());
        checkCommon28(pag_, tr_);
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
    public void cancelTm() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(sel_.getCancelButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        assertFalse(SelectTm.isOk(window_.getSelectTm()));
    }

    @Test
    public void okNoTm() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        assertTrue(SelectTm.isOk(window_.getSelectTm()));
        assertFalse(SelectTm.isSelectedIndex(window_.getSelectTm()));
    }

    @Test
    public void tmNotLearnt() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        assertTrue(SelectTm.isOk(window_.getSelectTm()));
        assertTrue(window_.getScenePanel().getResultScene().getCommonFrame().isVisible());
        assertEq(NULL_REF,window_.getFacade().getPlayer().getSelectedMove());
        assertEq(0,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
    }

    @Test
    public void tmLearnt1() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getModeName().setSelectedItem(SearchingMode.SUBSTRING);
        pag_.getModeName().getCombo().events(null);
        pag_.getName().setText("4");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        assertTrue(SelectTm.isOk(window_.getSelectTm()));
        assertEq(ECLAIR_4,window_.getFacade().getPlayer().getSelectedMove());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
    }

    @Test
    public void tmLearnt2() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getModeName().setSelectedItem(SearchingMode.SUBSTRING);
        pag_.getModeName().getCombo().events(null);
        pag_.getName().setText("4");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        assertTrue(SelectTm.isOk(window_.getSelectTm()));
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(1,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().size());
        assertEq(ECLAIR, ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getKey(0));
    }

    @Test
    public void tmLearnt3() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getModeName().setSelectedItem(SearchingMode.SUBSTRING);
        pag_.getModeName().getCombo().events(null);
        pag_.getName().setText("4");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        assertTrue(SelectTm.isOk(window_.getSelectTm()));
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().fireEvents();
        assertEq(NULL_REF,window_.getFacade().getPlayer().getSelectedMove());
        assertEq(2,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().size());
        assertEq(ECLAIR, ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getKey(0));
        assertEq(ECLAIR_4, ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getKey(1));
    }

    @Test
    public void tmLearnt4() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,1);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getModeName().setSelectedItem(SearchingMode.SUBSTRING);
        pag_.getModeName().getCombo().events(null);
        pag_.getName().setText("4");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        assertTrue(SelectTm.isOk(window_.getSelectTm()));
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().fireEvents();
        assertEq(ECLAIR_4,window_.getFacade().getPlayer().getSelectedMove());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1, window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
    }

    @Test
    public void tmLearnt5() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,1);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getModeName().setSelectedItem(SearchingMode.SUBSTRING);
        pag_.getModeName().getCombo().events(null);
        pag_.getName().setText("4");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        assertTrue(SelectTm.isOk(window_.getSelectTm()));
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().fireEvents();
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(1,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().size());
        assertEq(ECLAIR, ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getKey(0));
    }

    @Test
    public void tmLearnt6() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,1);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        PaginatorMove pag_ = sel_.getPaginatorMove();
        pag_.getModeName().setSelectedItem(SearchingMode.SUBSTRING);
        pag_.getModeName().getCombo().events(null);
        pag_.getName().setText("4");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectTm().getSelectDial().isVisible());
        assertTrue(SelectTm.isOk(window_.getSelectTm()));
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().fireEvents();
        tryClick((AbsButton) window_.getScenePanel().getMovesLearntList().get(0));
        assertEq(1,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().size());
        assertEq(ECLAIR_4, ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getKey(0));
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
    private static void loadRomGameTm(WindowAiki _window, int _max) {
        loadRom(_window, coreDataBase());
        _window.getFacade().getData().getConstNum().put(DataBase.DEF_MAX_ATT,new Rate(_max));
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getTm((short) 2);
        _window.getFacade().getGame().getPlayer().getInventory().getTm((short) 3);
    }
    private static void loadRomGameOneHost(WindowAiki _window) {
        loadRomGameHost(_window);
        _window.getFacade().getGame().getHostedPk().getList().get(0).getValue().setFirstPokemon(pk(_window));
        _window.getFacade().getGame().getHostedPk().getList().get(0).getValue().setSecondPokemon(pk(_window));
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

    private static void loadRomGameHost(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(newGerantPokemon(GeranceType.HOST)));
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

    private void checkCommon28(PaginatorMove _pag, IdList<AbsCustComponent> _tr) {
        assertTrue(_tr.containsObj(_pag.getDelta()));
        assertTrue(_tr.containsObj(_pag.getNbResults()));
        assertTrue(_tr.containsObj(_pag.getCmpDamagingPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpDamagingSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPpPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPpSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPricePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPriceSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPrioPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpPrioSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpTargetsPrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpTargetsSorting().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNamePrio().self()));
        assertTrue(_tr.containsObj(_pag.getCmpNameSorting().self()));
        assertTrue(_tr.containsObj(_pag.getName()));
        assertTrue(_tr.containsObj(_pag.getTypes()));
        assertTrue(_tr.containsObj(_pag.getTargets().self()));
        assertTrue(_tr.containsObj(_pag.getDamaging().self()));
        assertTrue(_tr.containsObj(_pag.getModeName().self()));
        assertTrue(_tr.containsObj(_pag.getModeTypes().self()));
        assertTrue(_tr.containsObj(_pag.getMaxPp()));
        assertTrue(_tr.containsObj(_pag.getMinPp()));
        assertTrue(_tr.containsObj(_pag.getMaxPriority()));
        assertTrue(_tr.containsObj(_pag.getMinPriority()));
        assertTrue(_tr.containsObj(_pag.getMaxPrice()));
        assertTrue(_tr.containsObj(_pag.getMinPrice()));
        assertTrue(_tr.containsObj(_pag.getSearchButton()));
        assertTrue(_tr.containsObj(_pag.getNewSearchButton()));
    }

}
