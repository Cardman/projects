package aiki.gui.components;


import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.facade.PaginationMove;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.enums.TargetChoice;
import aiki.gui.WindowAiki;
import aiki.gui.components.labels.TmLabel;
import aiki.gui.components.listeners.ChangedModeEvent;
import aiki.gui.listeners.PaginatorEvent;
import aiki.sml.GamesPk;
import aiki.sml.MessagesRenderPaginatorMove;
import aiki.util.SortingMove;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.util.*;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class PaginatorMove extends Paginator {

//    private static final String CST_NAME = "name";
//
//    private static final String CST_TYPES = "types";
//
//    private static final String CST_DAMAGING = "damaging";
//
//    private static final String CST_TARGETS = "targets";
//
//    private static final String CST_PRIORITY = "priority";
//
//    private static final String CST_PP = "pp";
//
//    private static final String CST_PRICE = "price";

    //private static final String MOVE = "move";

    private final AbsTextField name;

    private final AbsTextField types;

    private final AbsTextField minPriority = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxPriority = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField minPp = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxPp = getMain().getCompoFactory().newTextField(16);

    private final ComboBox<SelectedBoolean> damaging;

    private final ComboBox<TargetChoice> targets;

    private final AbsTextField minPrice = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxPrice = getMain().getCompoFactory().newTextField(16);


    private final IdList<SearchingMode> order = new IdList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
    private final ComboBox<SearchingMode> modeName;

    private final ComboBox<SearchingMode> modeTypes;

    private final AbsPanel results = getMain().getCompoFactory().newGrid(0,1);

    private final ComboBox<SelectedBoolean> cmpNameSorting;

    private final NumComboBox cmpNamePrio;

//    private ComboBox<SelectedBoolean> cmpTypesSorting;
//
//    private NumComboBox cmpTypesPrio = new NumComboBox();

    private final ComboBox<SelectedBoolean> cmpDamagingSorting;

    private final NumComboBox cmpDamagingPrio;

    private final ComboBox<SelectedBoolean> cmpPpSorting;

    private final NumComboBox cmpPpPrio;

    private final ComboBox<SelectedBoolean> cmpPriceSorting;

    private final NumComboBox cmpPricePrio;

    private final ComboBox<SelectedBoolean> cmpPrioSorting;

    private final NumComboBox cmpPrioPrio;

    private final ComboBox<SelectedBoolean> cmpTargetsSorting;

    private final NumComboBox cmpTargetsPrio;

    private final boolean buy;

    public PaginatorMove(WindowAiki _window, AbsPanel _p, ChangeableTitle _w, FacadeGame _d, boolean _buy) {
        super(_window, _p);
        cmpNamePrio = new NumComboBox(_window.getFrames());
        cmpDamagingPrio = new NumComboBox(_window.getFrames());
        cmpTargetsPrio = new NumComboBox(_window.getFrames());
        cmpPpPrio = new NumComboBox(_window.getFrames());
        cmpPricePrio = new NumComboBox(_window.getFrames());
        cmpPrioPrio = new NumComboBox(_window.getFrames());
        setWindow(_w);
        setFacade(_d);
        buy = _buy;
        order.addAllElts(SearchingMode.all());
//        order.add(SearchingMode.WHOLE_STRING);
//        order.add(SearchingMode.SUBSTRING);
//        order.add(SearchingMode.META_CHARACTER);
//        order.add(SearchingMode.BEGIN);
//        order.add(SearchingMode.END);
//        order.add(SearchingMode.MATCH_SPACE);
        modeName = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeName.refresh(order, getMessagesSearchMode());
        modeTypes = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeTypes.refresh(order, getMessagesSearchMode());
        damaging = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        damaging.refresh(getFacade().getTranslatedBooleansCurLanguage());
        damaging.setSelectedItem(SelectedBoolean.YES_AND_NO);
        damaging.getCombo().repaint();
        targets = new ComboBox<TargetChoice>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        String lg_ = getMain().getLanguageKey();
        targets.refresh(targets(lg_));
        cmpNameSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpTargetsSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpTargetsSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpTypesSorting = new ComboBox<SelectedBoolean>();
//        cmpTypesSorting.setWithDefaultValue(false);
//        cmpTypesSorting.refresh(getFacade().getData().getTranslatedBooleans().getVal(Constants.getLanguage()));
        cmpDamagingSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpDamagingSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPrioSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpPrioSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPpSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpPpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPriceSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nb_ = PaginationMove.NB_CMPARATORS;
        cmpNamePrio.setItems(nb_ + 1);
        cmpTargetsPrio.setItems(nb_ + 1);
        cmpDamagingPrio.setItems(nb_ + 1);
        cmpPrioPrio.setItems(nb_ + 1);
        cmpPpPrio.setItems(nb_ + 1);
        cmpPricePrio.setItems(nb_ + 1);
        getFacade().setSearchModeNameMove(SearchingMode.WHOLE_STRING);
        StringList mvs_ = new StringList();
        for (String p: getFacade().getData().getMoves().getKeys()) {
            String mv_ = getFacade().translateMove(p);
            mvs_.add(mv_);
        }
        name = getMain().getCompoFactory().newTextField(16);
        AutoCompleteDocument nameAuto_ = new AutoCompleteDocument(name, mvs_, _window.getFrames());
        modeName.setListener(new ChangedModeEvent(modeName, nameAuto_));

        StringList ts_ = new StringList();
        for (String p: getFacade().getData().getTypes()) {
            String mv_ = getFacade().translateType(p);
            ts_.add(mv_);
        }
        types = getMain().getCompoFactory().newTextField(16);
        AutoCompleteDocument typesAuto_ = new AutoCompleteDocument(types, ts_, _window.getFrames());
        modeTypes.setListener(new ChangedModeEvent(modeTypes, typesAuto_));
//        name.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfNameMove(convertStringField(name.getText()));
//            }
//        });
//        types.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfTypeMove(convertStringField(types.getText()));
//            }
//        });
//        modeName.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeName.getCurrent();
//                getFacade().setSearchModeNameMove(s_);
//            }
//        });
//        modeTypes.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeTypes.getCurrent();
//                getFacade().setSearchModeTypeMove(s_);
//            }
//        });
//        damaging.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                if (damaging.getCurrent() == SelectedBoolean.YES_AND_NO) {
//                    getFacade().setSelectedClassMove(MoveData.class);
//                } else if (damaging.getCurrent() == SelectedBoolean.YES) {
//                    getFacade().setSelectedClassMove(DamagingMoveData.class);
//                } else {
//                    getFacade().setSelectedClassMove(StatusMoveData.class);
//                }
//            }
//        });
//        targets.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setTargetChoiceMove(targets.getCurrent());
//            }
//        });
//        minPriority.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinPrioMove(convertNumberField(minPriority.getText()));
//            }
//        });
//        maxPriority.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxPrioMove(convertNumberField(maxPriority.getText()));
//            }
//        });
//        minPp.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinPpMove(convertNumberField(minPp.getText()));
//            }
//        });
//        maxPp.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxPpMove(convertNumberField(maxPp.getText()));
//            }
//        });
//        minPrice.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinPriceMove(convertNumberField(minPrice.getText()));
//            }
//        });
//        maxPrice.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxPriceMove(convertNumberField(maxPrice.getText()));
//            }
//        });
//        cmpNameSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNameIncreasingMove(cmpNameSorting.getCurrent());
//            }
//        });
//        cmpNamePrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNamePriorityMove((Integer)cmpNamePrio.getSelectedItem());
//            }
//        });
//        cmpPriceSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPriceIncreasingMove(cmpPriceSorting.getCurrent());
//            }
//        });
//        cmpPricePrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPricePriorityMove((Integer)cmpPricePrio.getSelectedItem());
//            }
//        });
//        cmpPpSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPppIncreasingMove(cmpPpSorting.getCurrent());
//            }
//        });
//        cmpPpPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPppPriorityMove((Integer)cmpPpPrio.getSelectedItem());
//            }
//        });
//        cmpPrioSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPrioIncreasingMove(cmpPrioSorting.getCurrent());
//            }
//        });
//        cmpPrioPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPrioPriorityMove((Integer)cmpPrioPrio.getSelectedItem());
//            }
//        });
//        cmpDamagingSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpDescriptionIncreasingMove(cmpDamagingSorting.getCurrent());
//            }
//        });
//        cmpDamagingPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpDescriptionPriorityMove((Integer)cmpDamagingPrio.getSelectedItem());
//            }
//        });
//        cmpTargetsSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpTargetChoiceIncreasingMove(cmpTargetsSorting.getCurrent());
//            }
//        });
//        cmpTargetsPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpTargetChoicePriorityMove((Integer)cmpTargetsPrio.getSelectedItem());
//            }
//        });
        AbsPanel search_;
        search_ = getMain().getCompoFactory().newGrid(0,3);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_NAME)));
        search_.add(name);
        search_.add(modeName.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TYPES)));
        search_.add(types);
        search_.add(modeTypes.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRIORITY)));
        search_.add(minPriority);
        search_.add(maxPriority);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PP)));
        search_.add(minPp);
        search_.add(maxPp);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRICE)));
        search_.add(minPrice);
        search_.add(maxPrice);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_DAMAGING)));
        search_.add(damaging.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TARGETS)));
        search_.add(targets.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        _p.add(search_);
        AbsPanel sorting_;
        sorting_ = getMain().getCompoFactory().newGrid(0,3);
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_NAME)));
        sorting_.add(cmpNameSorting.self());
        sorting_.add(cmpNamePrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRICE)));
        sorting_.add(cmpPriceSorting.self());
        sorting_.add(cmpPricePrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRIORITY)));
        sorting_.add(cmpPrioSorting.self());
        sorting_.add(cmpPrioPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PP)));
        sorting_.add(cmpPpSorting.self());
        sorting_.add(cmpPpPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_DAMAGING)));
        sorting_.add(cmpDamagingSorting.self());
        sorting_.add(cmpDamagingPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TARGETS)));
        sorting_.add(cmpTargetsSorting.self());
        sorting_.add(cmpTargetsPrio.self());
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
//        int side_ = getFacade().getData().getMap().getSideLength();
        //Ints widths_ = new Ints();
        int nameWidth_ = getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_NAME));
        int typesWidth_ = getHeader().width(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TYPES)));
        int prioWidth_ = getHeader().width(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRIORITY)));
        int ppWidth_ = getHeader().width(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PP)));
        int targetWidth_ = getHeader().width(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TARGETS)));
//        widths_.add(nameWidth_);
//        int numberWidth_ = getHeader().width(getMessages().getVal(NUMBER));
//        widths_.add(numberWidth_);
        int width_ = nameWidth_+typesWidth_;
        width_ += prioWidth_;
        width_ += ppWidth_;
        width_ += targetWidth_;
        width_ += getHeader().width(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRICE)));
//        width_ += getHeader().width(getMessages().getVal(PRICE));
//        widths_.add(getHeader().width(getMessages().getVal(NAME)));
//        widths_.add(getHeader().width(getMessages().getVal(NUMBER)));
//        widths_.add(getHeader().width(getMessages().getVal(PRICE)));
//        widths_.add(getHeader().width(getMessages().getVal(DESCRIPTION)));
//        if (width_ < getHeader().width(getMessages().getVal(DESCRIPTION))) {
//            width_ = getHeader().width(getMessages().getVal(DESCRIPTION));
//        }
        getHeader().addString(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_NAME), FIRST_PIXEL);
        getHeader().addString(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TYPES)), nameWidth_);
        getHeader().addString(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRIORITY)), nameWidth_ + typesWidth_);
        getHeader().addString(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PP)), nameWidth_ + typesWidth_ + prioWidth_);
        getHeader().addString(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TARGETS)), nameWidth_ + typesWidth_ + prioWidth_ + ppWidth_);
        getHeader().addString(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRICE)), nameWidth_ + typesWidth_ + prioWidth_ + ppWidth_ + targetWidth_);
        getHeader().setPreferredSize(new MetaDimension(width_, Paginator.HEIGTH_CHARS));
        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
        results.add(getHeader().getPaintableLabel());
        //results.add(new JLabel(getMessages().getVal(MOVE)));
        _p.add(getMain().getCompoFactory().newAbsScrollPane(results));
        getNbResults().setValue(getFacade().getNbResultsPerPageMove());
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

    private IdMap<TargetChoice, String> targets(String _lg) {
        IdMap<TargetChoice, String> id_ = new IdMap<TargetChoice, String>();
        id_.addEntry(TargetChoice.NONE,"");
        id_.addAllEntries(getFacade().getData().getTranslatedTargets().getVal(_lg));
        return id_;
    }

    @Override
    protected StringMap<String> messagesInitSpec() {
        return GamesPk.getPaginatorMvContentTr(GamesPk.getAppliTr(getMain().getFrames().currentLg())).getMapping();
    }

    @Override
    public void changeNbResults() {
        int int_ = getNbResults().getValue();
//        if (int_ <= 0) {
//            return;
//        }
        getFacade().setNbResultsPerPageMove(int_);
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
        getFacade().changePageMove(getPages().getSelectedIndex());
        appendResults();
        getWindow().pack();
    }

    @Override
    public void changeDeltaPage() {
        String text_ = getDelta().getText();
//        if (text_.isEmpty()) {
//            getFacade().setDeltaMove(1);
//            return;
//        }
//        int nbDelta_ = NumberUtil.parseInt(text_);
//        if (nbDelta_ <= 0) {
//            return;
//        }
//        getFacade().setDeltaMove(nbDelta_);
        getFacade().setDeltaMove(getFacade().getPaginationMove().adj(text_));
    }

//    public void refreshLang() {
//        initMessages();
//        modeName.refresh(order, getMessagesSearchMode());
//        modeTypes.refresh(order, getMessagesSearchMode());
//        damaging.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//    }
//
//    public void clearResults() {
//        getFacade().clearFoundResultsTm();
//        appendResults();
////        refreshResults();
////        changePages();
////        changeNav();
////        getWindow().pack();
//    }

    @Override
    public void search() {
        setInfos();
        if (buy) {
            getFacade().searchTmToBuy();
        } else {
            getFacade().searchTmToUse();
        }
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
        getFacade().newSearchMove();
        appendResults();
//        refreshResults();
//        changePages();
//        changeNav();
//        getWindow().pack();
    }

    private void setInfos() {
        SearchingMode s_;
        getFacade().setContentOfNameMove(convertStringField(name.getText()));
        getFacade().setContentOfTypeMove(convertStringField(types.getText()));
        s_ = modeName.getCurrent();
        getFacade().setSearchModeNameMove(s_);
        s_ = modeTypes.getCurrent();
        getFacade().setSearchModeTypeMove(s_);
        if (damaging.getCurrent() == SelectedBoolean.YES_AND_NO) {
            getFacade().setSelectedClassMove(DataBase.EMPTY_STRING);
        } else if (damaging.getCurrent() == SelectedBoolean.YES) {
            getFacade().setSelectedClassMove(DamagingMoveData.MOVE);
        } else {
            getFacade().setSelectedClassMove(StatusMoveData.MOVE);
        }
        getFacade().setTargetChoiceMove(targets.getCurrent());
        getFacade().setMinPrioMove(convertLongNumberField(minPriority.getText()));
        getFacade().setMaxPrioMove(convertLongNumberField(maxPriority.getText()));
        getFacade().setMinPpMove(convertLongNumberField(minPp.getText()));
        getFacade().setMaxPpMove(convertLongNumberField(maxPp.getText()));
        getFacade().setMinPriceMove(convertLongNumberField(minPrice.getText()));
        getFacade().setMaxPriceMove(convertLongNumberField(maxPrice.getText()));
        getFacade().setCmpNameIncreasingMove(cmpNameSorting.getCurrent());
        getFacade().setCmpNamePriorityMove(cmpNamePrio.getCurrent());
        getFacade().setCmpPriceIncreasingMove(cmpPriceSorting.getCurrent());
        getFacade().setCmpPricePriorityMove(cmpPricePrio.getCurrent());
        getFacade().setCmpPppIncreasingMove(cmpPpSorting.getCurrent());
        getFacade().setCmpPppPriorityMove(cmpPpPrio.getCurrent());
        getFacade().setCmpPrioIncreasingMove(cmpPrioSorting.getCurrent());
        getFacade().setCmpPrioPriorityMove(cmpPrioPrio.getCurrent());
        getFacade().setCmpDescriptionIncreasingMove(cmpDamagingSorting.getCurrent());
        getFacade().setCmpDescriptionPriorityMove(cmpDamagingPrio.getCurrent());
        getFacade().setCmpTargetChoiceIncreasingMove(cmpTargetsSorting.getCurrent());
        getFacade().setCmpTargetChoicePriorityMove(cmpTargetsPrio.getCurrent());
    }

    @Override
    public void check(int _line) {
        getFacade().checkLineMove(_line);
        check(_line, getFacade().getLineMove());
//        int count_ = results.getComponentCount();
//        for (int i = CustList.SECOND_INDEX; i < count_; i++) {
//            SelectableLabel l_ = (SelectableLabel) results.getComponent(i);
//            l_.setSelected(false);
//        }
//        if (getFacade().getLineMove() != CustList.INDEX_NOT_FOUND_ELT) {
//            SelectableLabel l_ = (SelectableLabel) results.getComponent(_line + 1);
//            l_.setSelected(true);
//        }
//        for (int i = CustList.SECOND_INDEX; i < count_; i++) {
//            SelectableLabel l_ = (SelectableLabel) results.getComponent(i);
//            l_.repaint();
//        }
    }
    public void refreshResults() {
        results.removeAll();
        getResultsLabels().clear();
        getHeader().clearStrings();
        int nameWidth_ = getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_NAME));
        int typesWidth_ = getHeader().width(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TYPES)));
        int prioWidth_ = getHeader().width(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRIORITY)));
        int ppWidth_ = getHeader().width(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PP)));
        int targetWidth_ = getHeader().width(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TARGETS)));
        int priceWidth_ = getHeader().width(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRICE)));
//        widths_.add(nameWidth_);
//        int numberWidth_ = getHeader().width(getMessages().getVal(NUMBER));
//        widths_.add(numberWidth_);
//        width_ += getHeader().width(getMessages().getVal(PRICE));
//        widths_.add(getHeader().width(getMessages().getVal(NAME)));
//        widths_.add(getHeader().width(getMessages().getVal(NUMBER)));
//        widths_.add(getHeader().width(getMessages().getVal(PRICE)));
//        widths_.add(getHeader().width(getMessages().getVal(DESCRIPTION)));
//        if (width_ < getHeader().width(getMessages().getVal(DESCRIPTION))) {
//            width_ = getHeader().width(getMessages().getVal(DESCRIPTION));
//        }
        getHeader().addString(getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_NAME), FIRST_PIXEL);
        CustList<SortingMove> rendered_ = getFacade().getRenderedMove();
        CustList<TmLabel> list_ = new CustList<TmLabel>();
        int nb_ = rendered_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            TmLabel l_ = new TmLabel(rendered_.get(i), getFacade(), getMain().getCompoFactory());
            l_.addMouseListener(new PaginatorEvent(this,i));
            list_.add(l_);
        }
        for (TmLabel l: list_) {
//            int th_;
//            th_ = l.getNameWidth();
            nameWidth_ = NumberUtil.max(nameWidth_,l.getNameWidth());
//            if (th_ > nameWidth_) {
//                nameWidth_ = th_;
//            }
//            th_ = l.getTypesWidth();
            typesWidth_ = NumberUtil.max(typesWidth_,l.getTypesWidth());
//            if (th_ > typesWidth_) {
//                typesWidth_ = th_;
//            }
//            th_ = l.getPriorityWidth();
            prioWidth_ = NumberUtil.max(prioWidth_,l.getPriorityWidth());
//            if (th_ > prioWidth_) {
//                prioWidth_ = th_;
//            }
//            th_ = l.getPpWidth();
            ppWidth_ = NumberUtil.max(ppWidth_,l.getPpWidth());
//            if (th_ > ppWidth_) {
//                ppWidth_ = th_;
//            }
            targetWidth_ = NumberUtil.max(targetWidth_,l.getTargetWidth());
//            if (th_ > targetWidth_) {
//                targetWidth_ = th_;
//            }
            priceWidth_ = NumberUtil.max(priceWidth_,l.getPriceWidth());
//            if (th_ > priceWidth_) {
//                priceWidth_ = th_;
//            }
        }
        int width_ = nameWidth_+typesWidth_;
        width_ += prioWidth_;
        width_ += ppWidth_;
        width_ += targetWidth_;
        width_ += priceWidth_;
        for (TmLabel l: list_) {
            l.setxMoveName(nameWidth_);
            l.setxTypes(typesWidth_);
            l.setxPriority(prioWidth_);
            l.setxPp(ppWidth_);
            l.setxTarget(targetWidth_);
//            l.setxPrice(priceWidth_);
            l.setPreferredSize(width_);
        }
        getHeader().addString(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TYPES)), nameWidth_);
        getHeader().addString(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRIORITY)), nameWidth_ + typesWidth_);
        getHeader().addString(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PP)), nameWidth_ + typesWidth_ + prioWidth_);
        getHeader().addString(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_TARGETS)), nameWidth_ + typesWidth_ + prioWidth_ + ppWidth_);
        getHeader().addString(StringUtil.concat(SPACE,getMessagesSpec().getVal(MessagesRenderPaginatorMove.CST_PRICE)), nameWidth_ + typesWidth_ + prioWidth_ + ppWidth_ + targetWidth_);
//        results.add(new JLabel(getMessages().getVal(MOVE)));
        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
        results.add(getHeader().getPaintableLabel());
        for (TmLabel l: list_) {
            AbsMetaLabelPk.paintPk(getMain().getImageFactory(), l);
            results.add(l.getPaintableLabel());
            getResultsLabels().add(l);
        }
    }
    public void changePages() {
//        setAdding(true);
        int nbPages_ = getFacade().pagesMove();
        getPages().setItems(nbPages_);
        getEnd().setText(Long.toString(nbPages_ - 1L));
//        setAdding(false);
    }
    public void changeNav() {
        changeNav(getFacade().enabledPreviousMove(), getFacade().enabledNextMove(), getFacade().pagesMove(), getFacade().getNumberPageMove());
    }

    @Override
    public void begin() {
        getFacade().beginMove();
        appendResults();
    }

    @Override
    public void previousDelta() {
        getFacade().previousDeltaMove();
        appendResults();
    }

    @Override
    public void previous() {
        getFacade().previousMove();
        appendResults();
    }

    @Override
    public void next() {
        getFacade().nextMove();
        appendResults();
    }

    @Override
    public void nextDelta() {
        getFacade().nextDeltaMove();
        appendResults();
    }

    @Override
    public void end() {
        getFacade().endMove();
        appendResults();
    }

    public AbsTextField getName() {
        return name;
    }

    public AbsTextField getTypes() {
        return types;
    }

    public AbsTextField getMinPriority() {
        return minPriority;
    }

    public AbsTextField getMaxPriority() {
        return maxPriority;
    }

    public AbsTextField getMinPp() {
        return minPp;
    }

    public AbsTextField getMaxPp() {
        return maxPp;
    }

    public ComboBox<SelectedBoolean> getDamaging() {
        return damaging;
    }

    public ComboBox<TargetChoice> getTargets() {
        return targets;
    }

    public AbsTextField getMinPrice() {
        return minPrice;
    }

    public AbsTextField getMaxPrice() {
        return maxPrice;
    }

    public ComboBox<SearchingMode> getModeName() {
        return modeName;
    }

    public ComboBox<SearchingMode> getModeTypes() {
        return modeTypes;
    }

    public ComboBox<SelectedBoolean> getCmpNameSorting() {
        return cmpNameSorting;
    }

    public NumComboBox getCmpNamePrio() {
        return cmpNamePrio;
    }

    public ComboBox<SelectedBoolean> getCmpDamagingSorting() {
        return cmpDamagingSorting;
    }

    public NumComboBox getCmpDamagingPrio() {
        return cmpDamagingPrio;
    }

    public ComboBox<SelectedBoolean> getCmpPpSorting() {
        return cmpPpSorting;
    }

    public NumComboBox getCmpPpPrio() {
        return cmpPpPrio;
    }

    public ComboBox<SelectedBoolean> getCmpPriceSorting() {
        return cmpPriceSorting;
    }

    public NumComboBox getCmpPricePrio() {
        return cmpPricePrio;
    }

    public ComboBox<SelectedBoolean> getCmpPrioSorting() {
        return cmpPrioSorting;
    }

    public NumComboBox getCmpPrioPrio() {
        return cmpPrioPrio;
    }

    public ComboBox<SelectedBoolean> getCmpTargetsSorting() {
        return cmpTargetsSorting;
    }

    public NumComboBox getCmpTargetsPrio() {
        return cmpTargetsPrio;
    }
}
