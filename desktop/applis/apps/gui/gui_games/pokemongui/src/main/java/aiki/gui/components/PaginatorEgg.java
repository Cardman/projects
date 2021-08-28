package aiki.gui.components;
import java.awt.Dimension;

import aiki.facade.FacadeGame;
import aiki.facade.PaginationEgg;
import aiki.facade.enums.SelectedBoolean;
import aiki.gui.WindowAiki;
import aiki.gui.components.labels.EggLabel;
import aiki.gui.components.listeners.ChangedModeEvent;
import aiki.gui.components.listeners.ChangedNbResultsEvent;
import aiki.gui.components.listeners.ChangedPageEvent;
import aiki.gui.components.listeners.NewSearchEvent;
import aiki.gui.components.listeners.SearchEvent;
import aiki.gui.listeners.PaginatorEvent;
import aiki.util.SortingEgg;
import code.gui.*;
import code.util.CustList;
import code.util.EnumList;
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class PaginatorEgg extends Paginator {

    private static final String CST_NAME = "name";

    private static final String STEPS = "steps";

    private static final String REMAIN_STEPS = "remainSteps";

    //private static final String EGG = "egg";

    private final AbsTextField name;
    private final AutoCompleteDocument nameAuto;

    private final EnumList<SearchingMode> order = new EnumList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
    private final ComboBox<SearchingMode> modeName;

    private final AbsTextField minSteps = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxSteps = getMain().getCompoFactory().newTextField(16);

    private final AbsPanel results = getMain().getCompoFactory().newGrid(0,1);

    private final ComboBox<SelectedBoolean> cmpNameSorting;

    private final NumComboBox cmpNamePrio;

    private final ComboBox<SelectedBoolean> cmpStepsSorting;

    private final NumComboBox cmpStepsPrio;

    public PaginatorEgg(WindowAiki _window, AbsPanel _p, ChangeableTitle _w, FacadeGame _d) {
        super(_window, ACCESS_EGG,_p);
        cmpNamePrio = new NumComboBox(_window.getFrames(),_window.getFrames().getGeneComboBox());
        cmpStepsPrio = new NumComboBox(_window.getFrames(),_window.getFrames().getGeneComboBox());
        setWindow(_w);
        setFacade(_d);
        order.add(SearchingMode.WHOLE_STRING);
        order.add(SearchingMode.SUBSTRING);
        order.add(SearchingMode.META_CHARACTER);
        order.add(SearchingMode.BEGIN);
        order.add(SearchingMode.END);
        order.add(SearchingMode.MATCH_SPACE);
        modeName = new ComboBox<SearchingMode>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeName.setWithDefaultValue(false);
        modeName.refresh(order, getMessagesSearchMode());
        cmpNameSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpNameSorting.setWithDefaultValue(false);
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpStepsSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpStepsSorting.setWithDefaultValue(false);
        cmpStepsSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nbCmp_ = PaginationEgg.NB_COMPARATORS;
        for (int i = IndexConstants.FIRST_INDEX; i <= nbCmp_; i++) {
            cmpNamePrio.addItem(i);
            cmpStepsPrio.addItem(i);
        }
        getFacade().setSearchModeNameEgg(SearchingMode.WHOLE_STRING);
        StringList pk_ = new StringList();
        for (String p: getFacade().getData().getPokedex().getKeys()) {
            String pkTr_ = getFacade().translatePokemon(p);
            pk_.add(pkTr_);
        }
        name = getMain().getCompoFactory().newTextField(16);
        nameAuto = new AutoCompleteDocument(name,pk_, getWindow(),_window.getFrames());
//        name.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfNameEgg(convertStringField(name.getText()));
//            }
//        });
        modeName.setListener(new ChangedModeEvent(modeName, nameAuto));
//        modeName.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeName.getCurrent();
//                getFacade().setSearchModeNameEgg(s_);
//                AutoCompleteDocument.setMode(name, s_);
//            }
//        });
//        minSteps.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinStepsEgg(convertNumberField(minSteps.getText()));
//            }
//        });
//        maxSteps.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxStepsEgg(convertNumberField(maxSteps.getText()));
//            }
//        });
//        cmpNameSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNameIncreasingEgg(cmpNameSorting.getCurrent());
//            }
//        });
//        cmpStepsSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpStepsIncreasingEgg(cmpStepsSorting.getCurrent());
//            }
//        });
//        cmpNamePrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNamePriorityEgg((Integer)cmpNamePrio.getSelectedItem());
//            }
//        });
//        cmpStepsPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpStepsPriorityEgg((Integer)cmpStepsPrio.getSelectedItem());
//            }
//        });
        AbsPanel search_;
        search_ = getMain().getCompoFactory().newGrid(0,3);
        search_.add(new TextLabel(getMessages().getVal(CST_NAME)));
        search_.add(name);
        search_.add(modeName.self());
        search_.add(new TextLabel(getMessages().getVal(REMAIN_STEPS)));
        search_.add(minSteps);
        search_.add(maxSteps);
        _p.add(search_);
        AbsPanel sorting_;
        sorting_ = getMain().getCompoFactory().newGrid(0,3);
        sorting_.add(new TextLabel(getMessages().getVal(CST_NAME)));
        sorting_.add(cmpNameSorting.self());
        sorting_.add(cmpNamePrio.self());
        sorting_.add(new TextLabel(getMessages().getVal(REMAIN_STEPS)));
        sorting_.add(cmpStepsSorting.self());
        sorting_.add(cmpStepsPrio.self());
        _p.add(sorting_);
        AbsPanel top_;
        top_ = getMain().getCompoFactory().newLineBox();
        AbsPlainButton button_;
        button_ = _window.getCompoFactory().newPlainButton(getMessages().getVal(SEARCH));
        button_.addActionListener(new SearchEvent(this));
        top_.add(button_);
        button_ = _window.getCompoFactory().newPlainButton(getMessages().getVal(NEW_SEARCH));
        button_.addActionListener(new NewSearchEvent(this));
        top_.add(button_);
        _p.add(top_);
        //        results.setLayout(new BoxLayout(results, BoxLayout.PAGE_AXIS));
        //map.getSideLength()
        //miniImagePk egg.getName() steps remainSteps
        //getHeader().setText(getMessages().getVal(EGG));
        StringBuilder h_ = new StringBuilder(getMessages().getVal(CST_NAME)).append(SPACES);
        h_.append(getMessages().getVal(STEPS)).append(SPACES);
        h_.append(getMessages().getVal(REMAIN_STEPS));
        getHeader().addString(h_.toString(), FIRST_PIXEL);
        getHeader().setPreferredSize(new Dimension(getHeader().width(h_.toString()), HEIGTH_CHARS));
        results.add(getHeader());
        _p.add(getMain().getCompoFactory().newAbsScrollPane(results));
        AbsPanel bottom_ = getMain().getCompoFactory().newLineBox();
        getNbResults().setValue(getFacade().getNbResultsPerPageEgg());
        getNbResults().addChangeListener(new ChangedNbResultsEvent(this));
        bottom_.add(getNbResults());
        getPages().setListener(new ChangedPageEvent(this));
        bottom_.add(getBegin());
        bottom_.add(getPreviousDelta());
        bottom_.add(getPrevious());
        bottom_.add(getPages().self());
        bottom_.add(getNext());
        bottom_.add(getNextDelta());
        bottom_.add(getEnd());
        bottom_.add(getDelta());
        _p.add(bottom_);
        changeNav();
    }

    @Override
    public void changeNbResults() {
        int int_ = getNbResults().getValue();
        if (int_ <= 0) {
            return;
        }
        getFacade().setNbResultsPerPageEgg(int_);
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }

    @Override
    public void changePageNumber() {
        if (isAdding()) {
            return;
        }
        getFacade().changePageEgg(getPages().getSelectedIndex());
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void changeDeltaPage() {
        String text_ = getDelta().getText();
        if (text_.isEmpty()) {
            getFacade().setDeltaEgg(1);
            return;
        }
        int nb_ = NumberUtil.parseInt(text_);
        if (nb_ <= 0) {
            return;
        }
        getFacade().setDeltaEgg(nb_);
    }

    public void refreshLang() {
        initMessages(ACCESS_EGG);
        modeName.refresh(order, getMessagesSearchMode());
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpStepsSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
    }

    public void clearResults() {
        getFacade().clearFoundResultsStoragePokemon();
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }

    @Override
    public void search() {
        setInfos();
        getFacade().searchEgg();
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }

    public void appendResults() {
        //data.getPagination().appendResults(data.getPeople());
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }

    @Override
    public void newSearch() {
        setInfos();
        getFacade().newSearchEgg();
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }

    private void setInfos() {
        getFacade().setContentOfNameEgg(convertStringField(name.getText()));
        SearchingMode s_ = modeName.getCurrent();
        getFacade().setSearchModeNameEgg(s_);
        getFacade().setMinStepsEgg(convertLongNumberField(minSteps.getText()));
        getFacade().setMaxStepsEgg(convertLongNumberField(maxSteps.getText()));
        getFacade().setCmpNameIncreasingEgg(cmpNameSorting.getCurrent());
        getFacade().setCmpStepsIncreasingEgg(cmpStepsSorting.getCurrent());
        getFacade().setCmpNamePriorityEgg(cmpNamePrio.getCurrent());
        getFacade().setCmpStepsPriorityEgg(cmpStepsPrio.getCurrent());
    }

    @Override
    public void check(int _line) {
        getFacade().checkLineEggs(_line);
        check(_line, getFacade().getLineEgg());
//        int count_ = results.getComponentCount();
//        for (SelectableLabel s: getResultsLabels()) {
//            s.setSelected(false);
//        }
////        for (int i = CustList.SECOND_INDEX; i < count_; i++) {
////            SelectableLabel l_ = (SelectableLabel) results.getComponent(i);
////            l_.setSelected(false);
////        }
//        if (getFacade().getLineEgg() != CustList.INDEX_NOT_FOUND_ELT) {
//            SelectableLabel l_ = getResultsLabels().get(_line);
//            l_.setSelected(true);
//        }
//        for (SelectableLabel s: getResultsLabels()) {
//            s.repaint();
//        }
//        for (int i = CustList.SECOND_INDEX; i < count_; i++) {
//            SelectableLabel l_ = (SelectableLabel) results.getComponent(i);
//            l_.repaint();
//        }
    }
    private void refreshResults() {
        getHeader().clearStrings();
        getResultsLabels().clear();
        results.removeAll();
        CustList<SortingEgg> rendered_ = getFacade().getRenderedEgg();
        CustList<EggLabel> list_ = new CustList<EggLabel>();
        int h_ = Math.max(getFacade().getMap().getSideLength(), HEIGTH_CHARS);
        int nb_ = rendered_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            EggLabel l_ = new EggLabel(rendered_.get(i), getMain().getCompoFactory());
            l_.setImagesResults(getMain().getImageFactory(),getFacade());
            l_.addMouseListener(new PaginatorEvent(this,i));
            list_.add(l_);
        }
        getHeader().addString(StringUtil.concat(getMessages().getVal(CST_NAME),SPACES), FIRST_PIXEL);
        int maxPixName_ = getHeader().width(StringUtil.concat(getMessages().getVal(CST_NAME),SPACES));
        for (EggLabel l: list_) {
            int value_ = l.stringWidth(StringUtil.concat(l.getEgg().getName(),SPACES));
            if (value_ > maxPixName_) {
                maxPixName_ = value_;
            }
        }
        int side_ = getFacade().getMap().getSideLength();
        getHeader().addString(StringUtil.concat(getMessages().getVal(STEPS),SPACES), side_+maxPixName_);
        int maxPixSteps_ = getHeader().width(StringUtil.concat(getMessages().getVal(STEPS),SPACES));
        for (EggLabel l: list_) {
            int value_ = l.stringWidth(StringUtil.concat(Long.toString(l.getEgg().getSteps()),SPACES));
            if (value_ > maxPixSteps_) {
                maxPixSteps_ = value_;
            }
        }
        getHeader().addString(getMessages().getVal(REMAIN_STEPS), side_+maxPixSteps_+maxPixName_);
        for (EggLabel l: list_) {
            l.setNameCoord(maxPixName_, maxPixSteps_, h_);
        }
        results.add(getHeader());
        for (EggLabel l: list_) {
            l.repaintLabel(getMain().getImageFactory());
            results.add(l);
            getResultsLabels().add(l);
        }
    }
    private void changePages() {
        setAdding(true);
        getPages().removeAllItems();
        int nbPages_ = getFacade().pagesEgg();
        for (int i = IndexConstants.FIRST_INDEX; i < nbPages_; i++) {
            getPages().addItem(i);
        }
        getEnd().setText(Long.toString(nbPages_ - 1L));
        setAdding(false);
    }
    private void changeNav() {
        changeNav(getFacade().enabledPreviousEgg(), getFacade().enabledNextEgg(), getFacade().pagesEgg(), getFacade().getNumberPageEgg());
    }

    @Override
    public void begin() {
        getFacade().beginEgg();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void previousDelta() {
        getFacade().previousDeltaEgg();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void previous() {
        getFacade().previousEgg();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void next() {
        getFacade().nextEgg();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void nextDelta() {
        getFacade().nextDeltaEgg();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void end() {
        getFacade().endEgg();
        changeNav();
        refreshResults();
        getWindow().pack();
    }
}
