package aiki.gui;

import aiki.facade.enums.SearchingMode;
import aiki.gui.components.PaginatorEgg;
import aiki.gui.dialogs.SelectEgg;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.map.pokemon.Egg;
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
        assertTrue(tr_.containsObj(pag_.getDelta()));
        assertTrue(tr_.containsObj(pag_.getNbResults()));
        assertTrue(tr_.containsObj(pag_.getMinSteps()));
        assertTrue(tr_.containsObj(pag_.getMaxSteps()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsPrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpStepsSorting().self()));
        assertTrue(tr_.containsObj(pag_.getName()));
        assertTrue(tr_.containsObj(pag_.getModeName().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNamePrio().self()));
        assertTrue(tr_.containsObj(pag_.getCmpNameSorting().self()));
        assertTrue(tr_.containsObj(pag_.getSearchButton()));
        assertTrue(tr_.containsObj(pag_.getNewSearchButton()));
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
    private static void loadRomGame(WindowAiki _window) {
        loadRom(_window, coreDataBase());
        loadGame(_window, build(_window.getFacade()));
    }

}
