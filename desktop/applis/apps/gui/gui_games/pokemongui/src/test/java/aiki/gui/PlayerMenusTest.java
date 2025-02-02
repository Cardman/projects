package aiki.gui;

import aiki.db.DataBase;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.items.*;
import aiki.fight.pokemon.evolution.EvolutionStoneSimple;
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.LevelMove;
import aiki.gui.components.*;
import aiki.gui.components.walk.TeamPanel;
import aiki.gui.dialogs.*;
import aiki.instances.Instances;
import aiki.main.AikiFactory;
import aiki.map.characters.TrainerMultiFights;
import aiki.map.characters.enums.GeranceType;
import aiki.map.places.Road;
import aiki.map.pokemon.Egg;
import aiki.map.pokemon.PokemonPlayer;
import code.gui.AbsButton;
import code.gui.AbsCommonFrame;
import code.gui.AbsCustComponent;
import code.gui.AbsScrollPane;
import code.gui.document.BeanBuilderHelper;
import code.gui.document.MessagesPkBean;
import code.gui.document.MessagesPkPokemon;
import code.gui.document.MessagesProgGameprog;
import code.gui.events.AlwaysActionListenerAct;
import code.maths.LgInt;
import code.maths.Rate;
import code.mock.MockCustComponent;
import code.mock.MockProgramInfos;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsFile;
import code.sml.util.TranslationsLg;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import org.junit.Test;

public final class PlayerMenusTest extends InitDbGuiAiki {
    @Test
    public void progress() {
        WindowAiki window_ = newProg();
        TranslationsAppli en_ = new TranslationsAppli();
        en_.getMapping().addEntry(MessagesPkBean.GAMEPROG, MessagesProgGameprog.en());
        ((MockProgramInfos)window_.getFrames()).getTranslations().getMapping().getVal(EN).getMapping().addEntry(MessagesPkBean.APP_BEAN, en_);
        TranslationsAppli fr_ = new TranslationsAppli();
        fr_.getMapping().addEntry(MessagesPkBean.GAMEPROG, MessagesProgGameprog.fr());
        ((MockProgramInfos)window_.getFrames()).lg(FR).getMapping().addEntry(MessagesPkBean.APP_BEAN, fr_);
//        window_.getCore().getAikiFactory().submitNavProgTask(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),nav())));
//        window_.setPreparedProgTask(window_.getCore().getAikiFactory().getTaskNavProg());
        loadRomGame(window_);
        tryClick(window_.getScenePanel().getGame());
        assertTrue(window_.getDialogGameProgess().getAbsDialog().isVisible());
    }
    @Test
    public void progressEvo() {
        WindowAiki window_ = newProg();
        TranslationsAppli en_ = new TranslationsAppli();
        en_.getMapping().addEntry(MessagesPkBean.GAMEPROG, MessagesProgGameprog.en());
        ((MockProgramInfos)window_.getFrames()).getTranslations().getMapping().getVal(EN).getMapping().addEntry(MessagesPkBean.APP_BEAN, en_);
        TranslationsAppli fr_ = new TranslationsAppli();
        fr_.getMapping().addEntry(MessagesPkBean.GAMEPROG, MessagesProgGameprog.fr());
        ((MockProgramInfos)window_.getFrames()).lg(FR).getMapping().addEntry(MessagesPkBean.APP_BEAN, fr_);
//        window_.getCore().getAikiFactory().submitNavProgTask(new MockCallable<AikiNatLgNamesNavigation>(new AikiNatLgNamesNavigation(new PokemonStandardsSample(),nav())));
//        window_.setPreparedProgTask(window_.getCore().getAikiFactory().getTaskNavProg());
        loadRomGameIt(window_);
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
    public void selEggCancel1() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        tryClick(sel_.getCancelButton());
        assertFalse(sel_.getSelectDial().isVisible());
    }

    @Test
    public void selEggCancel2() {
        WindowAiki window_ = newSelEgg();
        loadRomGameEggsThreeTimes(window_);
        window_.getFacade().eggsTr();
        tryClick(window_.getScenePanel().getSeeEggs());
        SelectEgg sel_ = window_.getSelectEgg();
        PaginatorEgg pag_ = sel_.getPaginatorEgg();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        sel_.getBuilt().windowClosing();
        assertFalse(window_.getModal().get());
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
    public void selPkCancel1() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        tryClick(sel_.getCancelButton());
        assertFalse(sel_.getSelectDial().isVisible());
    }
    @Test
    public void selPkCancel2() {
        WindowAiki window_ = newSelPk();
        loadRomGamePksThreeTimes(window_);
        window_.getFacade().pkTr();
        tryClick(window_.getScenePanel().getSeeBoxes());
        SelectPokemon sel_ = window_.getSelectPokemon();
        PaginatorPokemon pag_ = sel_.getPaginatorPokemon();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(3);
        tryClick(pag_.getResultsLabels().get(1));
        sel_.getBuilt().windowClosing();
        assertFalse(window_.getModal().get());
    }
    @Test
    public void consBoxPk() {
        WindowAiki window_ = newSelPkCons();
        update(window_.getFrames().currentLg(), MessagesPkPokemon.en());
        update(((MockProgramInfos)window_.getFrames()).lg(FR), MessagesPkPokemon.fr());
        ((MockProgramInfos)window_.getFrames()).setLanguage(EN);
//        preparePkTask(window_);
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
        IdList<AbsCustComponent> scAcc_ = ((MockCustComponent) scroll(sel_.getPkDetailContent())).getTreeAccessible();
        assertEq(43, tr_.size() - scAcc_.size());
        checkCommon30(pag_, tr_);
        checkCommonNot30(pag_, scAcc_);
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
        assertFalse(scAcc_.containsObj(pag_.getPages().self()));
        assertFalse(scAcc_.containsObj(pag_.getBegin()));
        assertFalse(scAcc_.containsObj(pag_.getPreviousDelta()));
        assertFalse(scAcc_.containsObj(pag_.getNextDelta()));
        assertFalse(scAcc_.containsObj(pag_.getEnd()));
        assertFalse(scAcc_.containsObj(pag_.getDetailButton()));
        assertFalse(scAcc_.containsObj(sel_.getOkButton()));
        assertFalse(scAcc_.containsObj(sel_.getCancelButton()));
        assertFalse(scAcc_.containsObj(sel_.getPkDetailContent().getField()));
        assertFalse(scAcc_.containsObj(sel_.getPkDetailContent().getSearch()));
        assertEq(3,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(2).getPaintableLabel()));
        assertFalse(scAcc_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertFalse(scAcc_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
        assertFalse(scAcc_.containsObj(pag_.getResultsLabels().get(2).getPaintableLabel()));
    }

    @Test
    public void consHost1() {
        WindowAiki window_ = newHostCons();
        update(window_.getFrames().currentLg(), MessagesPkPokemon.en());
        update(((MockProgramInfos)window_.getFrames()).lg(FR), MessagesPkPokemon.fr());
        ((MockProgramInfos)window_.getFrames()).setLanguage(EN);
//        preparePkTask(window_);
        loadRomGameOneHost(window_);
        tryClick(window_.getScenePanel().getHost());
        ConsultHosts sel_ = window_.getConsultHosts();
        AbsCommonFrame pag_ = sel_.getAbsDialog();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) pag_.getPane()).getTreeAccessible();
        assertEq(2,tr_.size());
        tryClick((AbsButton) tr_.get(0));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) pag_.getPane()).getTreeAccessible();
        IdList<AbsCustComponent> scAcc_ = ((MockCustComponent) scroll(sel_.getPkDetailContent())).getTreeAccessible();
        assertEq(4,tr2_.size()- scAcc_.size());
        assertTrue(tr2_.containsAllObj(tr_));
        assertTrue(tr2_.containsObj(sel_.getPkDetailContent().getField()));
        assertTrue(tr2_.containsObj(sel_.getPkDetailContent().getSearch()));
        assertFalse(scAcc_.containsObj(tr_.get(0)));
        assertFalse(scAcc_.containsObj(tr_.get(1)));
        assertFalse(scAcc_.containsObj(sel_.getPkDetailContent().getField()));
        assertFalse(scAcc_.containsObj(sel_.getPkDetailContent().getSearch()));
    }

    @Test
    public void consHost2() {
        WindowAiki window_ = newHostCons();
        update(window_.getFrames().currentLg(), MessagesPkPokemon.en());
        update(((MockProgramInfos)window_.getFrames()).lg(FR), MessagesPkPokemon.fr());
        ((MockProgramInfos)window_.getFrames()).setLanguage(EN);
//        preparePkTask(window_);
        loadRomGameOneHost(window_);
        tryClick(window_.getScenePanel().getHost());
        ConsultHosts sel_ = window_.getConsultHosts();
        AbsCommonFrame pag_ = sel_.getAbsDialog();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) pag_.getPane()).getTreeAccessible();
        assertEq(2,tr_.size());
        tryClick((AbsButton) tr_.get(1));
        IdList<AbsCustComponent> tr2_ = ((MockCustComponent) pag_.getPane()).getTreeAccessible();
        IdList<AbsCustComponent> scAcc_ = ((MockCustComponent) scroll(sel_.getPkDetailContent())).getTreeAccessible();
        assertEq(4,tr2_.size()- scAcc_.size());
        assertTrue(tr2_.containsAllObj(tr_));
        assertTrue(tr2_.containsObj(sel_.getPkDetailContent().getField()));
        assertTrue(tr2_.containsObj(sel_.getPkDetailContent().getSearch()));
        assertFalse(scAcc_.containsObj(tr_.get(0)));
        assertFalse(scAcc_.containsObj(tr_.get(1)));
        assertFalse(scAcc_.containsObj(sel_.getPkDetailContent().getField()));
        assertFalse(scAcc_.containsObj(sel_.getPkDetailContent().getSearch()));
    }

    @Test
    public void consHost3() {
        WindowAiki window_ = newHostCons();
//        preparePkTask(window_);
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
//        assertFalse(SelectTm.isOk(window_.getSelectTm()));
    }
    @Test
    public void cancelTm2() {
        WindowAiki window_ = newSelMv();
        loadRomGameTm(window_,4);
        window_.getFacade().mvTr();
        tryClick(window_.getScenePanel().getTm());
        SelectTm sel_ = window_.getSelectTm();
        sel_.getBuilt().windowClosing();
        assertFalse(window_.getModal().get());
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
        //assertTrue(SelectTm.isOk(window_.getSelectTm()));
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
        //assertTrue(SelectTm.isOk(window_.getSelectTm()));
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
        //assertTrue(SelectTm.isOk(window_.getSelectTm()));
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
        //assertTrue(SelectTm.isOk(window_.getSelectTm()));
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
        //assertTrue(SelectTm.isOk(window_.getSelectTm()));
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
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
        //assertTrue(SelectTm.isOk(window_.getSelectTm()));
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
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
        //assertTrue(SelectTm.isOk(window_.getSelectTm()));
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
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
        //assertTrue(SelectTm.isOk(window_.getSelectTm()));
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick((AbsButton) window_.getScenePanel().getMovesLearntList().get(0));
        assertEq(1,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().size());
        assertEq(ECLAIR_4, ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getKey(0));
    }

    @Test
    public void selIt1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(23, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }

    @Test
    public void selIt2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getMinNumber().setText("0");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void setIt3() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void setIt4() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        tryClick(pag_.getPrevious());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void setIt5() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getEnd());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void setIt6() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getEnd());
        tryClick(pag_.getBegin());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void setIt7() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        pag_.getPages().selectItem(1);
        pag_.getPages().getCombo().events(null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void setIt8() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        pag_.getPages().selectItem(1);
        pag_.getPages().getCombo().events(null);
        pag_.getPages().selectItem(0);
        pag_.getPages().getCombo().events(null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void setIt9() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNextDelta());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPrevious()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void setIt10() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNextDelta());
        tryClick(pag_.getPreviousDelta());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void setIt11() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getDelta().setText("1");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void setIt12() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(2);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(2,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(1).getPaintableLabel()));
    }

    @Test
    public void setIt13() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNewSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(30, tr_.size());
        checkCommon20(pag_, tr_);
        assertTrue(tr_.containsObj(pag_.getPages().self()));
        assertTrue(tr_.containsObj(pag_.getBegin()));
        assertTrue(tr_.containsObj(pag_.getPreviousDelta()));
        assertTrue(tr_.containsObj(pag_.getNext()));
        assertTrue(tr_.containsObj(pag_.getNextDelta()));
        assertTrue(tr_.containsObj(pag_.getEnd()));
        assertTrue(tr_.containsObj(sel_.getGiveCheckBox()));
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(1,pag_.getResultsLabels().size());
        assertTrue(tr_.containsObj(pag_.getResultsLabels().get(0).getPaintableLabel()));
    }

    @Test
    public void cancelIt1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(sel_.getCancelButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
    }
    @Test
    public void cancelIt2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        sel_.getBuilt().windowClosing();
        assertFalse(window_.getModal().get());
    }
    @Test
    public void okNoIt() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertFalse(SelectItem.isSelectedIndex(window_.getSelectItem()));
    }

    @Test
    public void badUseIt() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBadUse(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertTrue(window_.getScenePanel().getResultScene().getCommonFrame().isVisible());
        assertEq(0,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
    }

    @Test
    public void useEvo1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(SNOW,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
    }

    @Test
    public void useEvo2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
    }

    @Test
    public void useEvo3() {
        WindowAiki window_ = newSelIt();
        assertEq(IndexConstants.INDEX_NOT_FOUND_ELT, TeamPanel.adjustIndex(AikiFactory.usable(window_.getCompoFactory(),window_.getImageFactory(),null,new AlwaysActionListenerAct()),new Ints()));
        loadRomGameItBase(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(NULL_REF,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
        assertEq(RAICHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
    }

    @Test
    public void useEvo4() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseTwoAbilities(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(SNOW,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
        assertEq(PIKACHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(3, tr_.size());
        assertEq(2, window_.getScenePanel().getAbilityLabels().size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getAbilityLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getAbilityLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
    }

    @Test
    public void useEvo5() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseTwoAbilities(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getExitOptions());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(NULL_REF,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
        assertEq(PIKACHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
    }

    @Test
    public void useEvo6() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseTwoAbilities(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(SNOW,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
        assertEq(PIKACHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(3, tr_.size());
        assertEq(0, window_.getScenePanel().getMovesLearntList().size());
        assertEq(2, window_.getScenePanel().getAbilityLabels().size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getAbilityLabels().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getAbilityLabels().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
    }

    @Test
    public void useEvo7() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseTwoAbilities(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getAbilityLabels().get(0));
        tryClick(window_.getScenePanel().getEvolveStone());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(NULL_REF,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
        assertEq(RAICHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
    }

    @Test
    public void useEvo8() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseTwoMoves(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getExitOptions());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(NULL_REF,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
        assertEq(PIKACHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
    }

    @Test
    public void useEvo9() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseTwoMoves(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertEq(0, window_.getScenePanel().getAbilityLabels().size());
        assertEq(2, window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
        assertTrue(tr_.containsObj(window_.getScenePanel().getMovesLearntList().get(1)));
        assertTrue(tr_.containsObj(window_.getScenePanel().getEvolveStone()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
    }

    @Test
    public void useEvo10() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseTwoMoves(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getMovesLearntListLabel().get(1).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(3, tr_.size());
        assertEq(0, window_.getScenePanel().getAbilityLabels().size());
        assertEq(2, window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
        assertTrue(tr_.containsObj(window_.getScenePanel().getMovesLearntList().get(1)));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
    }

    @Test
    public void useEvo11() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseTwoMoves(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getMovesLearntListLabel().get(1).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        window_.getScenePanel().getMovesLearntListLabel().get(0).getComponent().getMouseListenersRel().get(0).mouseReleased(null,null,null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(4, tr_.size());
        assertEq(0, window_.getScenePanel().getAbilityLabels().size());
        assertEq(2, window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
        assertTrue(tr_.containsObj(window_.getScenePanel().getMovesLearntList().get(1)));
        assertTrue(tr_.containsObj(window_.getScenePanel().getEvolveStone()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
    }

    @Test
    public void useEvo12() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseTwoMoves(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getExitOptions());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(NULL_REF,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
        assertEq(PIKACHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
    }

    @Test
    public void useEvo13() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseTwoMoves(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getEvolveStone());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(NULL_REF,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
        assertEq(RAICHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
    }

    @Test
    public void useEvo14() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBaseNoEvo(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(NULL_REF,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
        assertEq(PIKACHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
    }

    @Test
    public void useBoost1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoost(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(SNOW,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
    }

    @Test
    public void useBoost2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoost(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
    }

    @Test
    public void useBoost3() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoost(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(1, window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
    }

    @Test
    public void useBoost4() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoost(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
    }

    @Test
    public void useBoost5() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoost(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(1,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).getMax());
        tryClick((AbsButton) window_.getScenePanel().getMovesLearntList().get(0));
        assertEq(2,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).getMax());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
    }

    @Test
    public void useBoost6() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoostHp(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(SNOW,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
    }

    @Test
    public void useBoost7() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoostHp(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
    }

    @Test
    public void useBoost8() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoostHp(window_);
        window_.getFacade().itTr();
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("gel");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertEq(0,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getEv().getVal(Statistic.HP));
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(SNOW));
        assertEq(1,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getEv().getVal(Statistic.HP));
    }

    @Test
    public void useHealMove1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoost(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(HUILE,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealMove2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoost(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealMove3() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoost(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(1, window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
    }

    @Test
    public void useHealMove4() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoost(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealMove5() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBoost(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(0,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).getCurrent());
        tryClick((AbsButton) window_.getScenePanel().getMovesLearntList().get(0));
        assertEq(1,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).getCurrent());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealMove6() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealMoves(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(HUILE,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealMove7() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealMoves(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealMove8() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealMoves(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(1,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).getCurrent());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealHp1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealHp(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).setRemainingHp(Rate.one());
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(HUILE,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealHp2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealHp(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).setRemainingHp(Rate.one());
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealHp3() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealHp(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).setRemainingHp(Rate.one());
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(new Rate(2),((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getRemainingHp());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealStatus1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealStatus(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().add(DESERT);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(HUILE,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealStatus2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealStatus(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().add(DESERT);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealStatus3() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealStatus(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().add(DESERT);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertTrue(((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().isEmpty());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealMoveBerry1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBerryMove(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(HUILE,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealMoveBerry2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBerryMove(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealMoveBerry3() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBerryMove(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertEq(1, window_.getScenePanel().getMovesLearntList().size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getMovesLearntList().get(0)));
    }

    @Test
    public void useHealMoveBerry4() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBerryMove(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealMoveBerry5() {
        WindowAiki window_ = newSelIt();
        loadRomGameItBerryMove(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(0,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).getCurrent());
        tryClick((AbsButton) window_.getScenePanel().getMovesLearntList().get(0));
        assertEq(1,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).getCurrent());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealHpBerry1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealHpBerry(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).setRemainingHp(Rate.one());
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(HUILE,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealHpBerry2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealHpBerry(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).setRemainingHp(Rate.one());
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealHpBerry3() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealHpBerry(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).setRemainingHp(Rate.one());
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(new Rate(2),((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getRemainingHp());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealStatusBerry1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealStatusBerry(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().add(DESERT);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(HUILE,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealStatusBerry2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealStatusBerry(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().add(DESERT);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void useHealStatusBerry3() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealStatusBerry(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().add(DESERT);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertTrue(((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().isEmpty());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void loadRomGameItHealTeam() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealTeam(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().add(DESERT);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertTrue(((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().isEmpty());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void loadRomGameItGive1() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealTeam(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().add(DESERT);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryToggle(sel_.getGiveCheckBox());
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectItem().getSelectDial().isVisible());
        assertEq(HUILE,window_.getFacade().getPlayer().getSelectedObject());
        assertEq(1,window_.getFacade().getPlayer().getIndexesOfPokemonTeam().size());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(1,window_.getScenePanel().getTeamPan().getListe().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void loadRomGameItGive2() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealTeam(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().add(DESERT);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryToggle(sel_.getGiveCheckBox());
        tryClick(sel_.getOkButton());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void loadRomGameItGive3() {
        WindowAiki window_ = newSelIt();
        loadRomGameItHealTeam(window_);
        window_.getFacade().itTr();
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().add(DESERT);
        tryClick(window_.getScenePanel().getItems());
        SelectItem sel_ = window_.getSelectItem();
        PaginatorItem pag_ = sel_.getPaginatorItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryToggle(sel_.getGiveCheckBox());
        tryClick(sel_.getOkButton());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(1,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().size());
        assertEq(DESERT,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getStatus().get(0));
        assertEq(HUILE,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getItem());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void teamManage1() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(2, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(4,window_.getScenePanel().getTeamPan().getListe().size());
    }

    @Test
    public void teamManage2() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getSwitchUsable()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getHealPk()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getDetailPk()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getNicknameField()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(4,window_.getScenePanel().getTeamPan().getListe().size());
    }

    @Test
    public void teamManage3() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(7, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getSwitchUsable()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getHealPk()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getDetailPk()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getNicknameField()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getTakeItemTeam()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(4,window_.getScenePanel().getTeamPan().getListe().size());
    }

    @Test
    public void teamManage4() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(2);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(3, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getSwitchUsable()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(4,window_.getScenePanel().getTeamPan().getListe().size());
    }
    @Test
    public void teamManage5() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(3);
        window_.getScenePanel().getTeamPan().getListe().events();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tr_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getTeamPan().getListe().getGlobal()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getSwitchUsable()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getHealPk()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getDetailPk()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getNicknameField()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
        assertEq(4,window_.getScenePanel().getTeamPan().getListe().size());
    }

    @Test
    public void teamManage6() {
        WindowAiki window_ = newProg();
        update(window_.getFrames().currentLg(), MessagesPkPokemon.en());
        update(((MockProgramInfos)window_.getFrames()).lg(FR), MessagesPkPokemon.fr());
        ((MockProgramInfos)window_.getFrames()).setLanguage(EN);
//        preparePkTask(window_);
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getDetailPk());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPkDetailContent().getContent()).getTreeAccessible();
        IdList<AbsCustComponent> scAcc_ = ((MockCustComponent) scroll(window_.getScenePanel().getPkDetailContent())).getTreeAccessible();
        assertEq(3, tr_.size()-scAcc_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getPkDetailContent().getSearch()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getPkDetailContent().getField()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getPkDetailContent().getHide()));
        assertFalse(scAcc_.containsObj(window_.getScenePanel().getPkDetailContent().getField()));
        assertFalse(scAcc_.containsObj(window_.getScenePanel().getPkDetailContent().getSearch()));
        assertFalse(scAcc_.containsObj(window_.getScenePanel().getPkDetailContent().getHide()));
    }

    @Test
    public void teamManage7() {
        WindowAiki window_ = newProg();
        update(window_.getFrames().currentLg(), MessagesPkPokemon.en());
        update(((MockProgramInfos)window_.getFrames()).lg(FR), MessagesPkPokemon.fr());
        ((MockProgramInfos)window_.getFrames()).setLanguage(EN);
//        preparePkTask(window_);
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getDetailPk());
        tryClick(window_.getScenePanel().getPkDetailContent().getHide());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPkDetailContent().getContent()).getTreeAccessible();
        assertEq(0, tr_.size());
    }

    @Test
    public void teamManage8() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryToggle(window_.getScenePanel().getSwitchUsable());
        assertEq(PIKACHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
        assertEq(RAICHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(1)).getName());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(RAICHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
        assertEq(PIKACHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(1)).getName());
    }

    @Test
    public void teamManage9() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(POKE_BALL,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(1)).getItem());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(POKE_BALL));
        tryClick(window_.getScenePanel().getTakeItemTeam());
        assertEq(NULL_REF,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(1)).getItem());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(POKE_BALL));
    }

    @Test
    public void teamManage10() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        window_.getScenePanel().getNicknameField().setText("sur");
        assertEq("sur",((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(1)).getNickname());
    }

    @Test
    public void teamManage11() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(1);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryToggle(window_.getScenePanel().getSwitchUsable());
        assertEq(PIKACHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
        assertEq(RAICHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(1)).getName());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        assertEq(RAICHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getName());
        assertEq(PIKACHU,((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(1)).getName());
        tryClick(window_.getScenePanel().getExitOptions());
        assertEq(0,((MockCustComponent)window_.getScenePanel().getPanelOptions()).getTreeAccessible().size());
    }

    @Test
    public void teamManage12() {
        WindowAiki window_ = newProg();
        update(window_.getFrames().currentLg(), MessagesPkPokemon.en());
        update(((MockProgramInfos)window_.getFrames()).lg(FR), MessagesPkPokemon.fr());
        ((MockProgramInfos)window_.getFrames()).setLanguage(EN);
        loadRomGameItBaseTwoAbilities(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        ((PokemonPlayer)window_.getFacade().getGame().getTeam().get(0)).setItem(SNOW);
        ((PokemonPlayer)window_.getFacade().getGame().getTeam().get(0)).setUsedBallCatching(NULL_REF);
        tryClick(window_.getScenePanel().getDetailPk());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPkDetailContent().getContent()).getTreeAccessible();
        IdList<AbsCustComponent> scAcc_ = ((MockCustComponent) scroll(window_.getScenePanel().getPkDetailContent())).getTreeAccessible();
        assertEq(3, tr_.size()-scAcc_.size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getPkDetailContent().getSearch()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getPkDetailContent().getField()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getPkDetailContent().getHide()));
        assertFalse(scAcc_.containsObj(window_.getScenePanel().getPkDetailContent().getField()));
        assertFalse(scAcc_.containsObj(window_.getScenePanel().getPkDetailContent().getSearch()));
        assertFalse(scAcc_.containsObj(window_.getScenePanel().getPkDetailContent().getHide()));
        window_.getScenePanel().getPkDetailContent().getField().setText("g");
        tryClick(window_.getScenePanel().getPkDetailContent().getSearch());
        window_.getScenePanel().getPkDetailContent().getField().setText("");
        tryClick(window_.getScenePanel().getPkDetailContent().getSearch());
        window_.getScenePanel().getPkDetailContent().getField().setText("#");
        tryClick(window_.getScenePanel().getPkDetailContent().getSearch());
    }

    @Test
    public void selHealIt1() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(47, tr_.size());
        checkCommon45(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }

    @Test
    public void selHealIt2() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getMinNumber().setText("0");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt3() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt4() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNext());
        tryClick(pag_.getPrevious());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt5() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getEnd());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt6() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getEnd());
        tryClick(pag_.getBegin());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt7() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        pag_.getPages().selectItem(1);
        pag_.getPages().getCombo().events(null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt8() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        pag_.getPages().selectItem(1);
        pag_.getPages().getCombo().events(null);
        pag_.getPages().selectItem(0);
        pag_.getPages().getCombo().events(null);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt9() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNextDelta());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt10() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNextDelta());
        tryClick(pag_.getPreviousDelta());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt11() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getDelta().setText("1");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt12() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        pag_.getNbResults().setValue(2);
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt13() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getNewSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(54, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt14() {
        WindowAiki window_ = newSelHealIt();
        HealingHp hp_ = Instances.newHealingHp();
        hp_.setHp(Rate.one());
        loadRomGameManageTeam(window_, hp_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(53, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt15() {
        WindowAiki window_ = newSelHealIt();
        HealingHpStatus hp_ = Instances.newHealingHpStatus();
        hp_.setHealedHpRate(Rate.one());
        loadRomGameManageTeam(window_, hp_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(53, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt16() {
        WindowAiki window_ = newSelHealIt();
        HealingPp hp_ = Instances.newHealingPp();
        hp_.setHealingAllMovesFullpp(5);
        loadRomGameManageTeam(window_, hp_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(53, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt17() {
        WindowAiki window_ = newSelHealIt();
        HealingHpStatus hp_ = Instances.newHealingHpStatus();
        hp_.getStatus().add(DESERT);
        loadRomGameManageTeam(window_, hp_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(53, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt18() {
        WindowAiki window_ = newSelHealIt();
        HealingHpStatus hp_ = Instances.newHealingHpStatus();
        hp_.setHealingKo(true);
        loadRomGameManageTeam(window_, hp_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(53, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void selHealIt19() {
        WindowAiki window_ = newSelHealIt();
        HealingHpStatus hp_ = Instances.newHealingHpStatus();
        hp_.setHealedHpRate(Rate.one());
        loadRomGameManageTeam(window_, hp_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("inex");
        tryToggle(pag_.getRelativeHpCheck());
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(47, tr_.size());
        checkCommon45(pag_, tr_);
        assertTrue(tr_.containsObj(sel_.getOkButton()));
        assertTrue(tr_.containsObj(sel_.getCancelButton()));
        assertEq(0,pag_.getResultsLabels().size());
    }

    @Test
    public void selHealIt20() {
        WindowAiki window_ = newSelHealIt();
        HealingHpStatus hp_ = Instances.newHealingHpStatus();
        hp_.setHealedHpRate(Rate.one());
        loadRomGameManageTeam(window_, hp_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        pag_.getMinHpRate().setText("1");
        tryClick(pag_.getSearchButton());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) sel_.getSelectDial().getPane()).getTreeAccessible();
        assertEq(53, tr_.size());
        checkCommon45(pag_, tr_);
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
    public void cancelHealIt() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(sel_.getCancelButton());
        assertFalse(window_.getSelectHealingItem().getSelectDial().isVisible());
    }
    @Test
    public void cancelHealIt2() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        sel_.getBuilt().windowClosing();
        assertFalse(window_.getModal().get());
    }
    @Test
    public void okNoHealIt() {
        WindowAiki window_ = newSelHealIt();
        loadRomGameManageTeam(window_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("biz");
        tryClick(pag_.getSearchButton());
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectHealingItem().getSelectDial().isVisible());
        assertFalse(SelectHealingItem.isSelectedIndex(window_.getSelectHealingItem()));
    }

    @Test
    public void selHealItFromKo1() {
        WindowAiki window_ = newSelHealIt();
        HealingHpStatus hp_ = Instances.newHealingHpStatus();
        hp_.setHealingKo(true);
        hp_.setHealedHpRate(Rate.one());
        loadRomGameManageTeam(window_, hp_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(3);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        PokemonPlayer last_ = (PokemonPlayer) window_.getFacade().getPlayer().getTeam().last();
        assertEq(last_.pvMax(window_.getFacade().getData()),last_.getRemainingHp());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
        assertFalse(window_.getSelectHealingItem().getSelectDial().isVisible());
    }

    @Test
    public void selHealItFromKo2() {
        WindowAiki window_ = newSelHealIt();
        HealingHpStatus hp_ = Instances.newHealingHpStatus();
        hp_.setHealingKo(true);
        hp_.setHealedHpRate(Rate.one());
        loadRomGameManageTeam(window_, hp_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertFalse(window_.getSelectHealingItem().getSelectDial().isVisible());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void selHealItPp1() {
        WindowAiki window_ = newSelHealIt();
        HealingPp pp_ = Instances.newHealingPp();
        pp_.setHealedMovePp(5);
        loadRomGameManageTeam(window_, pp_);
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        assertEq(1, sel_.getMoves().size());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
    }

    @Test
    public void selHealItPp2() {
        WindowAiki window_ = newSelHealIt();
        HealingPp pp_ = Instances.newHealingPp();
        pp_.setHealedMovePp(5);
        loadRomGameManageTeam(window_, pp_);
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(sel_.getCancelButton());
        assertEq(LgInt.one(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
        assertFalse(window_.getSelectHealingItem().getSelectDial().isVisible());
    }

    @Test
    public void selHealItPp3() {
        WindowAiki window_ = newSelHealIt();
        HealingPp pp_ = Instances.newHealingPp();
        pp_.setHealedMovePp(5);
        loadRomGameManageTeam(window_, pp_);
        ((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).setCurrent( 0);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(0);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        tryClick(sel_.getMoves().get(0));
        assertEq(((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).getMax(),((PokemonPlayer)window_.getFacade().getPlayer().getTeam().get(0)).getMoves().getValue(0).getCurrent());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
        assertFalse(window_.getSelectHealingItem().getSelectDial().isVisible());
    }

    @Test
    public void selHealItHealTeam() {
        WindowAiki window_ = newSelHealIt();
        HealingSimpleItem hp_ = Instances.newHealingSimpleItem();
        hp_.setHealingTeam(true);
        loadRomGameManageTeam(window_, hp_);
        window_.getFacade().healTr();
        tryClick(window_.getScenePanel().getTeam());
        window_.getScenePanel().getTeamPan().getListe().select(3);
        window_.getScenePanel().getTeamPan().getListe().events();
        tryClick(window_.getScenePanel().getHealPk());
        SelectHealingItem sel_ = window_.getSelectHealingItem();
        PaginatorHealingItem pag_ = sel_.getPaginatorHealingItem();
        pag_.getName().setText("jama");
        tryClick(pag_.getSearchButton());
        tryClick(pag_.getResultsLabels().get(0));
        tryClick(sel_.getOkButton());
        PokemonPlayer last_ = (PokemonPlayer) window_.getFacade().getPlayer().getTeam().last();
        assertEq(last_.pvMax(window_.getFacade().getData()),last_.getRemainingHp());
        assertEq(LgInt.zero(),window_.getFacade().getPlayer().getInventory().getNumber(HUILE));
        assertFalse(window_.getSelectHealingItem().getSelectDial().isVisible());
    }
    @Test
    public void mapReturn1() {
        WindowAiki window_ = newProg();
        loadRomGameMapReturn(window_);
        tryClick(window_.getScenePanel().getGoBack());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(6, tr_.size());
        assertEq(4, window_.getScenePanel().getMapPanel().getTiles().size());
        assertTrue(tr_.containsObj(window_.getScenePanel().getMapPanel().getTiles().get(0).getPaintableLabel()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getMapPanel().getTiles().get(1).getPaintableLabel()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getMapPanel().getTiles().get(2).getPaintableLabel()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getMapPanel().getTiles().get(3).getPaintableLabel()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getValidateMoveToPlace()));
        assertTrue(tr_.containsObj(window_.getScenePanel().getExitOptions()));
    }
    @Test
    public void mapReturn2() {
        WindowAiki window_ = newProg();
        loadRomGameMapReturn(window_);
        tryClick(window_.getScenePanel().getGoBack());
        tryClick(window_.getScenePanel().getValidateMoveToPlace());
        assertEq(0,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
    }
    @Test
    public void mapReturn3() {
        WindowAiki window_ = newProg();
        loadRomGameMapReturn(window_);
        tryClick(window_.getScenePanel().getGoBack());
        tryClick(window_.getScenePanel().getMapPanel().getTiles().get(3));
        tryClick(window_.getScenePanel().getValidateMoveToPlace());
        assertEq(2,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
        IdList<AbsCustComponent> tr_ = ((MockCustComponent) window_.getScenePanel().getPanelOptions()).getTreeAccessible();
        assertEq(0, tr_.size());
    }
    @Test
    public void mapReturn4() {
        WindowAiki window_ = newProg();
        loadRomGameMapReturn(window_);
        window_.getFacade().getGame().getVisitedPlaces().setValue(1,BoolVal.FALSE);
        tryClick(window_.getScenePanel().getGoBack());
        tryClick(window_.getScenePanel().getMapPanel().getTiles().get(3));
        tryClick(window_.getScenePanel().getValidateMoveToPlace());
        assertEq(0,window_.getFacade().getGame().getPlayerCoords().getNumberPlace());
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
        _window.getFacade().getGame().getPlayer().getInventory().getTm( 2);
        _window.getFacade().getGame().getPlayer().getInventory().getTm( 3);
    }
    private static void loadRomGameOneHost(WindowAiki _window) {
        loadRomGameHost(_window);
        _window.getFacade().getGame().getHostedPk().getValue(0).setFirstPokemon(pk(_window));
        _window.getFacade().getGame().getHostedPk().getValue(0).setSecondPokemon(pk(_window));
    }
    private static void loadRomGameItBase(WindowAiki _window) {
        loadRom(_window, coreDataBaseIt());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }
    private static void loadRomGameItBoost(WindowAiki _window) {
        loadRom(_window, coreDataBaseItBoost());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }
    private static void loadRomGameItBerryMove(WindowAiki _window) {
        loadRom(_window, coreDataBaseItBerryMove());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }
    private static void loadRomGameItBoostHp(WindowAiki _window) {
        loadRom(_window, coreDataBaseItBoostHp());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }

    private static void loadRomGameItHealMoves(WindowAiki _window) {
        loadRom(_window, coreDataBaseItHealMoves());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }

    private static void loadRomGameItHealHp(WindowAiki _window) {
        loadRom(_window, coreDataBaseItHealHp());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }
    private static void loadRomGameItHealHpBerry(WindowAiki _window) {
        loadRom(_window, coreDataBaseItHealHpBerry());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }

    private static void loadRomGameItHealStatus(WindowAiki _window) {
        loadRom(_window, coreDataBaseItHealStatus());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }

    private static void loadRomGameItHealStatusBerry(WindowAiki _window) {
        loadRom(_window, coreDataBaseItHealStatusBerry());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }

    private static void loadRomGameItHealTeam(WindowAiki _window) {
        loadRom(_window, coreDataBaseItHealTeam());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }
    private static void loadRomGameItBaseTwoAbilities(WindowAiki _window) {
        loadRom(_window, coreDataBaseItTwoAbilities());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }

    private static void loadRomGameItBaseTwoMoves(WindowAiki _window) {
        loadRom(_window, coreDataBaseItTwoMoves());
        _window.getFacade().getData().getConstNum().put(DataBase.DEF_MAX_ATT,new Rate(1));
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }

    private static void loadRomGameItBaseNoEvo(WindowAiki _window) {
        loadRom(_window, coreDataBaseNoEvo());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }
    private static void loadRomGameItBadUse(WindowAiki _window) {
        loadRom(_window, coreDataBaseItBadUse());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
    }
    private static void loadRomGame(WindowAiki _window) {
        loadRom(_window, coreDataBase());
        loadGame(_window, build(_window.getFacade()));
    }
    private static void loadRomGameIt(WindowAiki _window) {
        loadRom(_window, coreDataBaseItTr());
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
    }
    private static void loadRomGameHost(WindowAiki _window) {
        loadRom(_window, coreDataBaseCity(newGerantPokemon(GeranceType.HOST)));
        loadGame(_window, build(_window.getFacade()));
    }

    private static void loadRomGameManageTeam(WindowAiki _window) {
        HealingPp it_ = Instances.newHealingPp();
        it_.setHealedMovePp(5);
        loadRomGameManageTeam(_window, it_);
    }

    private static void loadRomGameManageTeam(WindowAiki _window, Item _it) {
        loadRom(_window, coreDataBaseManageTeam(_it));
        loadGame(_window, build(_window.getFacade()));
        _window.getFacade().getGame().getPlayer().getInventory().getItem(SNOW);
        _window.getFacade().getGame().getPlayer().getInventory().getItem(HUILE);
        _window.getFacade().getGame().getPlayer().getTeam().add(pkItSec(_window));
        _window.getFacade().getGame().getPlayer().getTeam().add(new Egg(PIKACHU));
        _window.getFacade().getGame().getPlayer().getTeam().add(pkSec(_window));
        ((PokemonPlayer)_window.getFacade().getGame().getPlayer().getTeam().last()).setRemainingHp(Rate.zero());
    }

    private static void loadRomGameMapReturn(WindowAiki _window) {
        loadRom(_window, coreDataBaseMapReturn());
        loadGame(_window, build(_window.getFacade()));
    }

    private static DataBase coreDataBaseIt() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        withPk(init_, RAICHU, trsPk_, RAICHU_TR);
        EvolutionStoneSimple ev_ = Instances.newEvolutionStoneSimple();
        ev_.setStone(SNOW);
        init_.getPokedex().getVal(PIKACHU).getEvolutions().addEntry(RAICHU, ev_);
        init_.getPokedex().getVal(PIKACHU).setBaseEvo(PIKACHU);
        init_.getPokedex().getVal(RAICHU).setBaseEvo(PIKACHU);
        EvolvingStone evo_ = Instances.newEvolvingStone();
        HealingPp it_ = Instances.newHealingPp();
        it_.setHealedMovePp(5);
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", evo_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }

    private static DataBase coreDataBaseItTr() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        withPk(init_, RAICHU, trsPk_, RAICHU_TR);
        EvolutionStoneSimple ev_ = Instances.newEvolutionStoneSimple();
        ev_.setStone(SNOW);
        init_.getPokedex().getVal(PIKACHU).getEvolutions().addEntry(RAICHU, ev_);
        init_.getPokedex().getVal(PIKACHU).setBaseEvo(PIKACHU);
        init_.getPokedex().getVal(RAICHU).setBaseEvo(PIKACHU);
        EvolvingStone evo_ = Instances.newEvolvingStone();
        HealingPp it_ = Instances.newHealingPp();
        it_.setHealedMovePp(5);
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", evo_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        Road road_ = Instances.newRoad();
        data_.getMap().addPlace(withBlocks(road_));
        road_.addDualFight(newCoords(0,0,0,0),Instances.newDualFight());
        TrainerMultiFights tmf_ = Instances.newTrainerMultiFights();
        tmf_.getTeamsRewards().add(Instances.newPokemonTeam());
        road_.getLevelRoad().getCharacters().addEntry(newPoint(1,1), tmf_);
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }
    private static DataBase coreDataBaseItBoost() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        Boost boost_ = Instances.newBoost();
        boost_.setWinPp(new Rate(1));
        HealingPp it_ = Instances.newHealingPp();
        it_.setHealedMovePp(5);
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", boost_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }

    private static DataBase coreDataBaseItBerryMove() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        Boost boost_ = Instances.newBoost();
        boost_.setWinPp(new Rate(1));
        Berry it_ = Instances.newBerry();
        it_.setHealPp(5);
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", boost_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }
    private static DataBase coreDataBaseItBoostHp() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        Boost boost_ = Instances.newBoost();
        IdMap<Statistic,Long> map_ = new IdMap<Statistic,Long>();
        map_.addEntry(Statistic.HP, 1L);
        boost_.setEvs(map_);
        HealingPp it_ = Instances.newHealingPp();
        it_.setHealedMovePp(5);
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", boost_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }

    private static DataBase coreDataBaseItHealMoves() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        Boost boost_ = Instances.newBoost();
        IdMap<Statistic,Long> map_ = new IdMap<Statistic,Long>();
        map_.addEntry(Statistic.HP,1L);
        boost_.setEvs(map_);
        HealingPp it_ = Instances.newHealingPp();
        it_.setHealingAllMovesFullpp(5);
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", boost_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }

    private static DataBase coreDataBaseItHealHp() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        Boost boost_ = Instances.newBoost();
        IdMap<Statistic,Long> map_ = new IdMap<Statistic,Long>();
        map_.addEntry(Statistic.HP,1L);
        boost_.setEvs(map_);
        HealingHp it_ = Instances.newHealingHp();
        it_.setHp(Rate.one());
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", boost_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }

    private static DataBase coreDataBaseItHealHpBerry() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        Boost boost_ = Instances.newBoost();
        IdMap<Statistic,Long> map_ = new IdMap<Statistic,Long>();
        map_.addEntry(Statistic.HP,1L);
        boost_.setEvs(map_);
        Berry it_ = Instances.newBerry();
        it_.setHealHp(Rate.one());
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", boost_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }
    private static DataBase coreDataBaseItHealStatus() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsSt_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        init_.getTranslatedStatus().addEntry(LANGUAGE, trsSt_);
        Boost boost_ = Instances.newBoost();
        IdMap<Statistic,Long> map_ = new IdMap<Statistic,Long>();
        map_.addEntry(Statistic.HP,1L);
        boost_.setEvs(map_);
        HealingSimpleStatus it_ = Instances.newHealingSimpleStatus();
        it_.getStatus().add(DESERT);
        init_.completeMembers(DESERT,Instances.newStatusSimple());
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", boost_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }

    private static DataBase coreDataBaseItHealStatusBerry() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsSt_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        init_.getTranslatedStatus().addEntry(LANGUAGE, trsSt_);
        Boost boost_ = Instances.newBoost();
        IdMap<Statistic,Long> map_ = new IdMap<Statistic,Long>();
        map_.addEntry(Statistic.HP,1L);
        boost_.setEvs(map_);
        Berry it_ = Instances.newBerry();
        it_.getHealStatus().add(DESERT);
        init_.completeMembers(DESERT,Instances.newStatusSimple());
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", boost_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }

    private static DataBase coreDataBaseItHealTeam() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsSt_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        init_.getTranslatedStatus().addEntry(LANGUAGE, trsSt_);
        Boost boost_ = Instances.newBoost();
        IdMap<Statistic,Long> map_ = new IdMap<Statistic,Long>();
        map_.addEntry(Statistic.HP,1L);
        boost_.setEvs(map_);
        HealingSimpleItem it_ = Instances.newHealingSimpleItem();
        it_.setHealingTeam(true);
        init_.completeMembers(DESERT,Instances.newStatusSimple());
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", boost_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }
    private static DataBase coreDataBaseItTwoMoves() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        withPk(init_, RAICHU, trsPk_, RAICHU_TR);
        EvolutionStoneSimple ev_ = Instances.newEvolutionStoneSimple();
        ev_.setStone(SNOW);
        init_.getPokedex().getVal(PIKACHU).getEvolutions().addEntry(RAICHU, ev_);
        init_.getPokedex().getVal(PIKACHU).setBaseEvo(PIKACHU);
        init_.getPokedex().getVal(RAICHU).setBaseEvo(PIKACHU);
        init_.getPokedex().getVal(RAICHU).getLevMoves().add(new LevelMove( 1,ECLAIR_2));
        EvolvingStone evo_ = Instances.newEvolvingStone();
        HealingPp it_ = Instances.newHealingPp();
        it_.setHealedMovePp(5);
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", evo_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }
    private static DataBase coreDataBaseItTwoAbilities() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsAb_ = new StringMap<String>();
        StringMap<String> trsMv_ = new StringMap<String>();
        DataBase init_ = coreDataBaseItEvo();
        init_.getTranslatedPokemon().addEntry(LANGUAGE, trsPk_);
        init_.getTranslatedMoves().addEntry(LANGUAGE, trsMv_);
        init_.getTranslatedAbilities().addEntry(LANGUAGE, trsAb_);
        init_.getTranslatedItems().addEntry(LANGUAGE, trsIt_);
        DataBase ab_ = withAb(withAb(init_, PARATONNERRE, trsAb_, "parra"), PARAFEU, trsAb_, "flamme");
        DataBase mv_ = withMv(withMv(withMv(ab_, ECLAIR_4, trsMv_, "biz 4"), ECLAIR_2, trsMv_, "biz 2"), ECLAIR, trsMv_, "biz");
        withPk(mv_, PIKACHU, trsPk_, PIKACHU_TR);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        withPk(init_, RAICHU, trsPk_, RAICHU_TR);
        EvolutionStoneSimple ev_ = Instances.newEvolutionStoneSimple();
        ev_.setStone(SNOW);
        init_.getPokedex().getVal(PIKACHU).getEvolutions().addEntry(RAICHU, ev_);
        init_.getPokedex().getVal(PIKACHU).setBaseEvo(PIKACHU);
        init_.getPokedex().getVal(RAICHU).setBaseEvo(PIKACHU);
        init_.getPokedex().getVal(RAICHU).getAbilities().add(PARAFEU);
        EvolvingStone evo_ = Instances.newEvolvingStone();
        HealingPp it_ = Instances.newHealingPp();
        it_.setHealedMovePp(5);
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", evo_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }
    private static DataBase coreDataBaseItBadUse() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        DataBase data_ = withIt(init_, SNOW, trsIt_, "gel", Instances.newSellingItem(),trsDesc_,"eve");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }
    private static DataBase coreDataBaseNoEvo() {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        withPk(init_, RAICHU, trsPk_, RAICHU_TR);
        init_.getPokedex().getVal(PIKACHU).setBaseEvo(PIKACHU);
        init_.getPokedex().getVal(RAICHU).setBaseEvo(RAICHU);
        init_.getPokedex().getVal(RAICHU).getLevMoves().add(new LevelMove( 1,ECLAIR_2));
        EvolvingStone evo_ = Instances.newEvolvingStone();
        HealingPp it_ = Instances.newHealingPp();
        it_.setHealedMovePp(5);
        DataBase data_ = withIt(withIt(init_, SNOW, trsIt_, "gel", evo_,trsDesc_,"eve"), HUILE, trsIt_, "jama", it_, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }

    private static DataBase coreDataBaseManageTeam(Item _item) {
        StringMap<String> trsIt_ = new StringMap<String>();
        StringMap<String> trsDesc_ = new StringMap<String>();
        StringMap<String> trsPk_ = new StringMap<String>();
        StringMap<String> trsSt_ = new StringMap<String>();
        DataBase init_ = coreDataBaseIt(trsIt_, trsPk_);
        init_.getTranslatedClassesDescriptions().addEntry(LANGUAGE, trsDesc_);
        init_.getTranslatedStatus().addEntry(LANGUAGE, trsSt_);
        init_.completeMembers(DESERT,Instances.newStatusSimple());
        trsSt_.addEntry(DESERT,DEFAULT);
        withPk(init_, RAICHU, trsPk_, RAICHU_TR);
        init_.getPokedex().getVal(RAICHU).setBaseEvo(RAICHU);
        Berry stat_ = Instances.newBerry();
        stat_.getMultStat().addEntry(Statistic.SPEED,new BoostHpRate(1,Rate.one()));
        HealingPp it_ = Instances.newHealingPp();
        it_.setHealedMovePp(5);
        DataBase data_ = withIt(withIt(withIt(init_, POKE_BALL, trsIt_, "poc"), SNOW, trsIt_, "gel", stat_,trsDesc_,"eve"), HUILE, trsIt_, "jama", _item, trsDesc_, "velo");
        initBegin(data_);
        data_.getMap().addPlace(withBlocks(Instances.newRoad()));
        data_.getTm().addEntry(2,ECLAIR);
        data_.getTm().addEntry(3,ECLAIR_4);
        data_.getTmPrice().addEntry(2,new LgInt("1"));
        data_.getTmPrice().addEntry(3,new LgInt("2"));
        compute(data_);
        return data_;
    }

    private void update(TranslationsLg _lg, TranslationsFile _file) {
        TranslationsAppli ta_ = new TranslationsAppli();
        ta_.getMapping().addEntry(MessagesPkBean.POKEMON, _file);
        _lg.getMapping().addEntry(MessagesPkBean.APP_BEAN, ta_);
    }

    private AbsScrollPane scroll(PkDetailContent _det) {
        return ((BeanBuilderHelper)_det.getScrollPane()).getScrollPane();
    }

}
