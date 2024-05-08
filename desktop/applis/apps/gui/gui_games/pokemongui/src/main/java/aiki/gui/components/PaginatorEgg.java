package aiki.gui.components;


import aiki.facade.FacadeGame;
import aiki.facade.PaginationEgg;
import aiki.facade.enums.SelectedBoolean;
import aiki.gui.WindowAiki;
import aiki.gui.components.labels.EggLabel;
import aiki.gui.components.listeners.ChangedModeEvent;
import aiki.gui.listeners.PaginatorEvent;
import aiki.sml.GamesPk;
import aiki.sml.MessagesRenderPaginatorEgg;
import aiki.util.SortingEgg;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.util.CustList;
import code.util.IdList;
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class PaginatorEgg extends Paginator {

//    private static final String CST_NAME = "name";

//    private static final String STEPS = "steps";

//    private static final String REMAIN_STEPS = "remainSteps";

    //private static final String EGG = "egg";

    private final AbsTextField name;

    private final IdList<SearchingMode> order = new IdList<SearchingMode>();

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
        super(_window, _p);
        cmpNamePrio = new NumComboBox(_window.getFrames());
        cmpStepsPrio = new NumComboBox(_window.getFrames());
        setWindow(_w);
        setFacade(_d);
        order.addAllElts(SearchingMode.all());
//        order.add(SearchingMode.WHOLE_STRING);
//        order.add(SearchingMode.SUBSTRING);
//        order.add(SearchingMode.META_CHARACTER);
//        order.add(SearchingMode.BEGIN);
//        order.add(SearchingMode.END);
//        order.add(SearchingMode.MATCH_SPACE);
        modeName = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeName.refresh(order, getMessagesSearchMode());
        cmpNameSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpStepsSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpStepsSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nbCmp_ = PaginationEgg.NB_COMPARATORS;
        cmpNamePrio.setItems(nbCmp_+1);
        cmpStepsPrio.setItems(nbCmp_+1);
        getFacade().setSearchModeNameEgg(SearchingMode.WHOLE_STRING);
        StringList pk_ = new StringList();
        for (String p: getFacade().getData().getPokedex().getKeys()) {
            String pkTr_ = getFacade().translatePokemon(p);
            pk_.add(pkTr_);
        }
        name = getMain().getCompoFactory().newTextField(16);
        AutoCompleteDocument nameAuto_ = new AutoCompleteDocument(name, pk_, _window.getFrames());
//        name.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfNameEgg(convertStringField(name.getText()));
//            }
//        });
        modeName.setListener(new ChangedModeEvent(modeName, nameAuto_));
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
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.CST_NAME)));
        search_.add(name);
        search_.add(modeName.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.REMAIN_STEPS)));
        search_.add(minSteps);
        search_.add(maxSteps);
        _p.add(search_);
        AbsPanel sorting_;
        sorting_ = getMain().getCompoFactory().newGrid(0,3);
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.CST_NAME)));
        sorting_.add(cmpNameSorting.self());
        sorting_.add(cmpNamePrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.REMAIN_STEPS)));
        sorting_.add(cmpStepsSorting.self());
        sorting_.add(cmpStepsPrio.self());
        _p.add(sorting_);
        beginBuild(_p);
//        AbsPanel top_;
//        top_ = getMain().getCompoFactory().newLineBox();
//        AbsButton button_;
//        button_ = _window.getCompoFactory().newPlainButton(getMessages().getVal(SEARCH));
//        button_.addActionListener(new SearchEvent(this));
//        top_.add(button_);
//        button_ = _window.getCompoFactory().newPlainButton(getMessages().getVal(NEW_SEARCH));
//        button_.addActionListener(new NewSearchEvent(this));
//        top_.add(button_);
//        _p.add(top_);
        //        results.setLayout(new BoxLayout(results, BoxLayout.PAGE_AXIS));
        //map.getSideLength()
        //miniImagePk egg.getName() steps remainSteps
        //getHeader().setText(getMessages().getVal(EGG));
        StringBuilder h_ = new StringBuilder(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.CST_NAME)).append(SPACES);
        h_.append(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.STEPS)).append(SPACES);
        h_.append(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.REMAIN_STEPS));
        getHeader().addString(h_.toString(), FIRST_PIXEL);
        getHeader().setPreferredSize(new MetaDimension(getHeader().width(h_.toString()), HEIGTH_CHARS));
        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
        results.add(getHeader().getPaintableLabel());
        _p.add(getMain().getCompoFactory().newAbsScrollPane(results));
        getNbResults().setValue(getFacade().getNbResultsPerPageEgg());
        finishBuild(_p);
//        AbsPanel bottom_ = getMain().getCompoFactory().newLineBox();
//        getNbResults().addChangeListener(new ChangedNbResultsEvent(this));
//        bottom_.add(getNbResults());
//        getPages().setListener(new ChangedPageEvent(this));
//        bottom_.add(getBegin());
//        bottom_.add(getPreviousDelta());
//        bottom_.add(getPrevious());
//        bottom_.add(getPages().self());
//        bottom_.add(getNext());
//        bottom_.add(getNextDelta());
//        bottom_.add(getEnd());
//        bottom_.add(getDelta());
//        _p.add(bottom_);
//        changeNav();
    }

    @Override
    protected StringMap<String> messagesInitSpec() {
        return GamesPk.getPaginatorEggContentTr(GamesPk.getAppliTr(getMain().getFrames().currentLg())).getMapping();
    }

    @Override
    public void changeNbResults() {
        int int_ = getNbResults().getValue();
//        if (int_ <= 0) {
//            return;
//        }
        getFacade().setNbResultsPerPageEgg(int_);
        appendResults();
//        refreshResults();
//        changePages();
//        changeNav();
//        getWindow().pack();
    }

    @Override
    public void changePageNumber() {
//        if (isAdding()) {
//            return;
//        }
        getFacade().changePageEgg(getPages().getSelectedIndex());
        appendResults();
//        refreshResults();
//        changeNav();
//        getWindow().pack();
    }

    @Override
    public void changeDeltaPage() {
        String text_ = getDelta().getText();
//        if (text_.isEmpty()) {
//            getFacade().setDeltaEgg(1);
//            return;
//        }
//        int nb_ = NumberUtil.parseInt(text_);
//        if (nb_ <= 0) {
//            return;
//        }
        getFacade().setDeltaEgg(getFacade().getPaginationEgg().adj(text_));
    }

//    public void refreshLang() {
//        initMessages();
//        modeName.refresh(order, getMessagesSearchMode());
//        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpStepsSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//    }
//
//    public void clearResults() {
//        getFacade().clearFoundResultsStoragePokemon();
//        appendResults();
////        refreshResults();
////        changePages();
////        changeNav();
////        getWindow().pack();
//    }

    @Override
    public void search() {
        setInfos();
        getFacade().searchEgg();
        appendResults();
//        refreshResults();
//        changePages();
//        changeNav();
//        getWindow().pack();
    }
//
//    public void appendResults() {
//        //data.getPagination().appendResults(data.getPeople());
//        refreshResults();
//        changePages();
//        changeNav();
//        getWindow().pack();
//    }

    @Override
    public void newSearch() {
        setInfos();
        getFacade().newSearchEgg();
        appendResults();
//        refreshResults();
//        changePages();
//        changeNav();
//        getWindow().pack();
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
    public void refreshResults() {
        getFacade().setLineEggs(IndexConstants.INDEX_NOT_FOUND_ELT);
        getHeader().clearStrings();
        getResultsLabels().clear();
        results.removeAll();
        CustList<SortingEgg> rendered_ = getFacade().getRenderedEgg();
        CustList<EggLabel> list_ = new CustList<EggLabel>();
        int h_ = NumberUtil.max(getFacade().getMap().getSideLength(), HEIGTH_CHARS);
        int nb_ = rendered_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            EggLabel l_ = new EggLabel(rendered_.get(i), getMain().getCompoFactory());
            l_.setImagesResults(getMain().getImageFactory(),getFacade(),getMain().getTileRender());
            l_.addMouseListener(new PaginatorEvent(this,i));
            list_.add(l_);
        }
        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.CST_NAME),SPACES), FIRST_PIXEL);
        int maxPixName_ = getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.CST_NAME),SPACES));
        for (EggLabel l: list_) {
            maxPixName_ = NumberUtil.max(maxPixName_, l.stringWidth(StringUtil.concat(l.getEgg().getName(),SPACES)));
        }
        int side_ = getFacade().getMap().getSideLength();
        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.STEPS),SPACES), side_+maxPixName_);
        int maxPixSteps_ = getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.STEPS),SPACES));
        for (EggLabel l: list_) {
            maxPixSteps_ = NumberUtil.max(maxPixSteps_, l.stringWidth(StringUtil.concat(Long.toString(l.getEgg().getSteps()),SPACES)));
        }
        getHeader().addString(getMessagesSpec().getVal(MessagesRenderPaginatorEgg.REMAIN_STEPS), side_+maxPixSteps_+maxPixName_);
        for (EggLabel l: list_) {
            l.setNameCoord(maxPixName_, maxPixSteps_, h_);
        }
        results.add(getHeader().getPaintableLabel());
        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
        for (EggLabel l: list_) {
            AbsMetaLabelPk.paintPk(getMain().getImageFactory(), l);
            results.add(l.getPaintableLabel());
            getResultsLabels().add(l);
        }
    }
    public void changePages() {
//        setAdding(true);
        int nbPages_ = getFacade().pagesEgg();
        getPages().setItems(nbPages_);
        getEnd().setText(Long.toString(nbPages_ - 1L));
//        setAdding(false);
    }
    public void changeNav() {
        changeNav(getFacade().enabledPreviousEgg(), getFacade().enabledNextEgg(), getFacade().pagesEgg(), getFacade().getNumberPageEgg());
    }

    @Override
    public void begin() {
        getFacade().beginEgg();
        appendResults();
    }

    @Override
    public void previousDelta() {
        getFacade().previousDeltaEgg();
        appendResults();
    }

    @Override
    public void previous() {
        getFacade().previousEgg();
        appendResults();
    }

    @Override
    public void next() {
        getFacade().nextEgg();
        appendResults();
    }

    @Override
    public void nextDelta() {
        getFacade().nextDeltaEgg();
        appendResults();
    }

    @Override
    public void end() {
        getFacade().endEgg();
        appendResults();
    }
    public AbsTextField getName() {
        return name;
    }

    public ComboBox<SearchingMode> getModeName() {
        return modeName;
    }

    public AbsTextField getMinSteps() {
        return minSteps;
    }

    public AbsTextField getMaxSteps() {
        return maxSteps;
    }

    public ComboBox<SelectedBoolean> getCmpNameSorting() {
        return cmpNameSorting;
    }

    public NumComboBox getCmpNamePrio() {
        return cmpNamePrio;
    }

    public ComboBox<SelectedBoolean> getCmpStepsSorting() {
        return cmpStepsSorting;
    }

    public NumComboBox getCmpStepsPrio() {
        return cmpStepsPrio;
    }
}
