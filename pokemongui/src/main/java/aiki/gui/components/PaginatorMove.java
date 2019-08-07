package aiki.gui.components;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.facade.PaginationMove;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.gui.MainWindow;
import aiki.gui.components.labels.TmLabel;
import aiki.gui.components.listeners.ChangedDeltaPageEvent;
import aiki.gui.components.listeners.ChangedModeEvent;
import aiki.gui.components.listeners.ChangedNbResultsEvent;
import aiki.gui.components.listeners.ChangedPageEvent;
import aiki.gui.components.listeners.NewSearchEvent;
import aiki.gui.components.listeners.SearchEvent;
import aiki.gui.listeners.PaginatorEvent;
import aiki.util.SortingMove;
import code.gui.*;
import code.util.*;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;

public final class PaginatorMove extends Paginator {

    private static final String NAME = "name";

    private static final String TYPES = "types";

    private static final String DAMAGING = "damaging";

    private static final String TARGETS = "targets";

    private static final String PRIORITY = "priority";

    private static final String PP = "pp";

    private static final String PRICE = "price";

    //private static final String MOVE = "move";

    private TextField name;

    private TextField types = new TextField(16);

    private TextField minPriority = new TextField(16);

    private TextField maxPriority = new TextField(16);

    private TextField minPp = new TextField(16);

    private TextField maxPp = new TextField(16);

    private ComboBoxSelectedBool damaging;

    private ComboBoxTargetChoice targets;

    private TextField minPrice = new TextField(16);

    private TextField maxPrice = new TextField(16);


    private EnumList<SearchingMode> order = new EnumList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
    private ComboBoxSearchingMode modeName;

    private ComboBoxSearchingMode modeTypes;

    private Panel results = Panel.newGrid(0,1);

    private ComboBoxSelectedBool cmpNameSorting;

    private NumComboBox cmpNamePrio = new NumComboBox();

//    private ComboBoxSelectedBool cmpTypesSorting;
//
//    private NumComboBox cmpTypesPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpDamagingSorting;

    private NumComboBox cmpDamagingPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpPpSorting;

    private NumComboBox cmpPpPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpPriceSorting;

    private NumComboBox cmpPricePrio = new NumComboBox();

    private ComboBoxSelectedBool cmpPrioSorting;

    private NumComboBox cmpPrioPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpTargetsSorting;

    private NumComboBox cmpTargetsPrio = new NumComboBox();

    private boolean buy;

    public PaginatorMove(MainWindow _window, Panel _p,ChangeableTitle _w, FacadeGame _d, boolean _buy) {
        super(_window, ACCESS_MOVE,_p);
        setWindow(_w);
        setFacade(_d);
        buy = _buy;
        order.add(SearchingMode.WHOLE_STRING);
        order.add(SearchingMode.SUBSTRING);
        order.add(SearchingMode.META_CHARACTER);
        order.add(SearchingMode.BEGIN);
        order.add(SearchingMode.END);
        order.add(SearchingMode.MATCH_SPACE);
        modeName = new ComboBoxSearchingMode();
        modeName.setWithDefaultValue(false);
        modeName.refresh(order, getMessagesSearchMode());
        modeTypes = new ComboBoxSearchingMode();
        modeTypes.setWithDefaultValue(false);
        modeTypes.refresh(order, getMessagesSearchMode());
        damaging = new ComboBoxSelectedBool();
        damaging.setWithDefaultValue(false);
        damaging.refresh(getFacade().getTranslatedBooleansCurLanguage());
        targets = new ComboBoxTargetChoice();
        targets.setWithDefaultValue(true);
        String lg_ = getMain().getLanguageKey();
        targets.refresh(getFacade().getData().getTranslatedTargets().getVal(lg_));
        cmpNameSorting = new ComboBoxSelectedBool();
        cmpNameSorting.setWithDefaultValue(false);
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpTargetsSorting = new ComboBoxSelectedBool();
        cmpTargetsSorting.setWithDefaultValue(false);
        cmpTargetsSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpTypesSorting = new ComboBoxSelectedBool();
//        cmpTypesSorting.setWithDefaultValue(false);
//        cmpTypesSorting.refresh(getFacade().getData().getTranslatedBooleans().getVal(Constants.getLanguage()));
        cmpDamagingSorting = new ComboBoxSelectedBool();
        cmpDamagingSorting.setWithDefaultValue(false);
        cmpDamagingSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPrioSorting = new ComboBoxSelectedBool();
        cmpPrioSorting.setWithDefaultValue(false);
        cmpPrioSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPpSorting = new ComboBoxSelectedBool();
        cmpPpSorting.setWithDefaultValue(false);
        cmpPpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPriceSorting = new ComboBoxSelectedBool();
        cmpPriceSorting.setWithDefaultValue(false);
        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nb_ = PaginationMove.NB_CMPARATORS;
        for (int i = CustList.FIRST_INDEX; i <= nb_; i++) {
            cmpNamePrio.addItem(i);
            cmpTargetsPrio.addItem(i);
            cmpDamagingPrio.addItem(i);
            cmpPrioPrio.addItem(i);
            cmpPpPrio.addItem(i);
            cmpPricePrio.addItem(i);
        }
        getFacade().setSearchModeNameMove(SearchingMode.WHOLE_STRING);
        StringList mvs_ = new StringList();
        for (String p: getFacade().getData().getMoves().getKeys()) {
            String mv_ = getFacade().translateMove(p);
            mvs_.add(mv_);
        }
        name = AutoCompleteDocument.createAutoCompleteTextField(mvs_, 16);
        modeName.setListener(new ChangedModeEvent(modeName, name));

        StringList ts_ = new StringList();
        for (String p: getFacade().getData().getTypes()) {
            String mv_ = getFacade().translateType(p);
            ts_.add(mv_);
        }
        types = AutoCompleteDocument.createAutoCompleteTextField(ts_, 16);
        modeTypes.setListener(new ChangedModeEvent(modeTypes, types));
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
        Panel search_;
        search_ = Panel.newGrid(0,3);
        search_.add(new TextLabel(getMessages().getVal(NAME)));
        search_.add(name);
        search_.add(modeName);
        search_.add(new TextLabel(getMessages().getVal(TYPES)));
        search_.add(types);
        search_.add(modeTypes);
        search_.add(new TextLabel(getMessages().getVal(PRIORITY)));
        search_.add(minPriority);
        search_.add(maxPriority);
        search_.add(new TextLabel(getMessages().getVal(PP)));
        search_.add(minPp);
        search_.add(maxPp);
        search_.add(new TextLabel(getMessages().getVal(PRICE)));
        search_.add(minPrice);
        search_.add(maxPrice);
        search_.add(new TextLabel(getMessages().getVal(DAMAGING)));
        search_.add(damaging);
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        search_.add(new TextLabel(getMessages().getVal(TARGETS)));
        search_.add(targets);
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        _p.add(search_);
        Panel sorting_;
        sorting_ = Panel.newGrid(0,3);
        sorting_.add(new TextLabel(getMessages().getVal(NAME)));
        sorting_.add(cmpNameSorting);
        sorting_.add(cmpNamePrio);
        sorting_.add(new TextLabel(getMessages().getVal(PRICE)));
        sorting_.add(cmpPriceSorting);
        sorting_.add(cmpPricePrio);
        sorting_.add(new TextLabel(getMessages().getVal(PRIORITY)));
        sorting_.add(cmpPrioSorting);
        sorting_.add(cmpPrioPrio);
        sorting_.add(new TextLabel(getMessages().getVal(PP)));
        sorting_.add(cmpPpSorting);
        sorting_.add(cmpPpPrio);
        sorting_.add(new TextLabel(getMessages().getVal(DAMAGING)));
        sorting_.add(cmpDamagingSorting);
        sorting_.add(cmpDamagingPrio);
        sorting_.add(new TextLabel(getMessages().getVal(TARGETS)));
        sorting_.add(cmpTargetsSorting);
        sorting_.add(cmpTargetsPrio);
        _p.add(sorting_);
        Panel top_;
        top_ = new Panel();
        LabelButton button_;
        button_ = new LabelButton(getMessages().getVal(SEARCH));
        button_.addMouseListener(new SearchEvent(this));
        top_.add(button_);
        button_ = new LabelButton(getMessages().getVal(NEW_SEARCH));
        button_.addMouseListener(new NewSearchEvent(this));
        top_.add(button_);
        _p.add(top_);
//        results.setLayout(new BoxLayout(results, BoxLayout.PAGE_AXIS));
//        int side_ = getFacade().getData().getMap().getSideLength();
        //Ints widths_ = new Ints();
        int nameWidth_ = getHeader().width(getMessages().getVal(NAME));
        int typesWidth_ = getHeader().width(StringList.concat(SPACE,getMessages().getVal(TYPES)));
        int prioWidth_ = getHeader().width(StringList.concat(SPACE,getMessages().getVal(PRIORITY)));
        int ppWidth_ = getHeader().width(StringList.concat(SPACE,getMessages().getVal(PP)));
        int targetWidth_ = getHeader().width(StringList.concat(SPACE,getMessages().getVal(TARGETS)));
//        widths_.add(nameWidth_);
//        int numberWidth_ = getHeader().width(getMessages().getVal(NUMBER));
//        widths_.add(numberWidth_);
        int width_ = nameWidth_+typesWidth_;
        width_ += prioWidth_;
        width_ += ppWidth_;
        width_ += targetWidth_;
        width_ += getHeader().width(StringList.concat(SPACE,getMessages().getVal(PRICE)));
//        width_ += getHeader().width(getMessages().getVal(PRICE));
//        widths_.add(getHeader().width(getMessages().getVal(NAME)));
//        widths_.add(getHeader().width(getMessages().getVal(NUMBER)));
//        widths_.add(getHeader().width(getMessages().getVal(PRICE)));
//        widths_.add(getHeader().width(getMessages().getVal(DESCRIPTION)));
//        if (width_ < getHeader().width(getMessages().getVal(DESCRIPTION))) {
//            width_ = getHeader().width(getMessages().getVal(DESCRIPTION));
//        }
        getHeader().addString(getMessages().getVal(NAME), FIRST_PIXEL);
        getHeader().addString(StringList.concat(SPACE,getMessages().getVal(TYPES)), nameWidth_);
        getHeader().addString(StringList.concat(SPACE,getMessages().getVal(PRIORITY)), nameWidth_ + typesWidth_);
        getHeader().addString(StringList.concat(SPACE,getMessages().getVal(PP)), nameWidth_ + typesWidth_ + prioWidth_);
        getHeader().addString(StringList.concat(SPACE,getMessages().getVal(TARGETS)), nameWidth_ + typesWidth_ + prioWidth_ + ppWidth_);
        getHeader().addString(StringList.concat(SPACE,getMessages().getVal(PRICE)), nameWidth_ + typesWidth_ + prioWidth_ + ppWidth_ + targetWidth_);
        getHeader().setPreferredSize(new Dimension(width_, Paginator.HEIGTH_CHARS));
        results.add(getHeader());
        //results.add(new JLabel(getMessages().getVal(MOVE)));
        _p.add(new ScrollPane(results));
        Panel bottom_ = new Panel();
        getNbResults().setValue(getFacade().getNbResultsPerPageFirstBox());
        getNbResults().addChangeListener(new ChangedNbResultsEvent(this));
        bottom_.add(getNbResults());
        getPages().setListener(new ChangedPageEvent(this));
        getDelta().addDocumentListener(new ChangedDeltaPageEvent(this));
        bottom_.add(getBegin());
        bottom_.add(getPreviousDelta());
        bottom_.add(getPrevious());
        bottom_.add(getPages());
        bottom_.add(getNext());
        bottom_.add(getNextDelta());
        bottom_.add(getEnd());
        bottom_.add(getDelta());
        _p.add(bottom_);
        changeNav();
    }

    @Override
    public void changeNbResults() {
        int int_ = (Integer)getNbResults().getValue();
        if (int_ <= 0) {
            return;
        }
        getFacade().setNbResultsPerPageMove(int_);
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
        getFacade().changePageMove(getPages().getSelectedIndex());
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void changeDeltaPage() {
        String text_ = getDelta().getText();
        if (text_.isEmpty()) {
            getFacade().setDeltaMove(1);
            return;
        }
        int nbDelta_ = Numbers.parseInt(text_);
        if (nbDelta_ <= 0) {
            return;
        }
        getFacade().setDeltaMove(nbDelta_);
    }

    public void refreshLang() {
        initMessages(ACCESS_MOVE);
        modeName.refresh(order, getMessagesSearchMode());
        modeTypes.refresh(order, getMessagesSearchMode());
        damaging.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
    }

    public void clearResults() {
        getFacade().clearFoundResultsTm();
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }

    @Override
    public void search() {
        setInfos();
        if (buy) {
            getFacade().searchTmToBuy();
        } else {
            getFacade().searchTmToUse();
        }
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
        getFacade().newSearchMove();
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
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
    private void refreshResults() {
        results.removeAll();
        getResultsLabels().clear();
        getHeader().clearStrings();
        int nameWidth_ = getHeader().width(getMessages().getVal(NAME));
        int typesWidth_ = getHeader().width(StringList.concat(SPACE,getMessages().getVal(TYPES)));
        int prioWidth_ = getHeader().width(StringList.concat(SPACE,getMessages().getVal(PRIORITY)));
        int ppWidth_ = getHeader().width(StringList.concat(SPACE,getMessages().getVal(PP)));
        int targetWidth_ = getHeader().width(StringList.concat(SPACE,getMessages().getVal(TARGETS)));
        int priceWidth_ = getHeader().width(StringList.concat(SPACE,getMessages().getVal(PRICE)));
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
        getHeader().addString(getMessages().getVal(NAME), FIRST_PIXEL);
        CustList<SortingMove> rendered_ = getFacade().getRenderedMove();
        CustList<TmLabel> list_ = new CustList<TmLabel>();
        int nb_ = rendered_.size();
        for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
            TmLabel l_ = new TmLabel(rendered_.get(i), getFacade());
            l_.addMouseListener(new PaginatorEvent(this,i));
            list_.add(l_);
        }
        for (TmLabel l: list_) {
            int th_;
            th_ = l.getNameWidth();
            if (th_ > nameWidth_) {
                nameWidth_ = th_;
            }
            th_ = l.getTypesWidth();
            if (th_ > typesWidth_) {
                typesWidth_ = th_;
            }
            th_ = l.getPriorityWidth();
            if (th_ > prioWidth_) {
                prioWidth_ = th_;
            }
            th_ = l.getPpWidth();
            if (th_ > ppWidth_) {
                ppWidth_ = th_;
            }
            th_ = l.getTargetWidth();
            if (th_ > targetWidth_) {
                targetWidth_ = th_;
            }
            th_ = l.getPriceWidth();
            if (th_ > priceWidth_) {
                priceWidth_ = th_;
            }
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
        getHeader().addString(StringList.concat(SPACE,getMessages().getVal(TYPES)), nameWidth_);
        getHeader().addString(StringList.concat(SPACE,getMessages().getVal(PRIORITY)), nameWidth_ + typesWidth_);
        getHeader().addString(StringList.concat(SPACE,getMessages().getVal(PP)), nameWidth_ + typesWidth_ + prioWidth_);
        getHeader().addString(StringList.concat(SPACE,getMessages().getVal(TARGETS)), nameWidth_ + typesWidth_ + prioWidth_ + ppWidth_);
        getHeader().addString(StringList.concat(SPACE,getMessages().getVal(PRICE)), nameWidth_ + typesWidth_ + prioWidth_ + ppWidth_ + targetWidth_);
//        results.add(new JLabel(getMessages().getVal(MOVE)));
        results.add(getHeader());
        for (TmLabel l: list_) {
            l.repaint();
            results.add(l);
            getResultsLabels().add(l);
        }
    }
    private void changePages() {
        setAdding(true);
        getPages().removeAllItems();
        int nbPages_ = getFacade().pagesMove();
        for (int i = CustList.FIRST_INDEX; i < nbPages_; i++) {
            getPages().addItem(i);
        }
        getEnd().setTextAndSize(Integer.toString(nbPages_ - 1));
        setAdding(false);
    }
    private void changeNav() {
        changeNav(getFacade().enabledPreviousMove(), getFacade().enabledNextMove(), getFacade().pagesMove(), getFacade().getNumberPageMove());
    }

    @Override
    public void begin() {
        getFacade().beginMove();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void previousDelta() {
        getFacade().previousDeltaMove();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void previous() {
        getFacade().previousMove();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void next() {
        getFacade().nextMove();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void nextDelta() {
        getFacade().nextDeltaMove();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void end() {
        getFacade().endMove();
        changeNav();
        refreshResults();
        getWindow().pack();
    }
}
