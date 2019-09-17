package aiki.gui.components;
import java.awt.Dimension;

import aiki.facade.FacadeGame;
import aiki.facade.PaginationItem;
import aiki.gui.MainWindow;
import aiki.gui.components.labels.ItemLabel;
import aiki.gui.components.listeners.ChangedModeEvent;
import aiki.gui.components.listeners.ChangedNbResultsEvent;
import aiki.gui.components.listeners.ChangedPageEvent;
import aiki.gui.components.listeners.NewSearchEvent;
import aiki.gui.components.listeners.SearchEvent;
import aiki.gui.listeners.PaginatorEvent;
import aiki.util.SortingItem;
import code.gui.*;
import code.util.*;
import aiki.facade.enums.SearchingMode;

public final class PaginatorItem extends Paginator {

    private static final String NAME = "name";

    private static final String DESCRIPTION = "description";

    private static final String PRICE = "price";

    //private static final String ITEM = "item";

    private static final String NUMBER = "number";

    private TextField name;

    private TextField description = new TextField(16);

    private TextField minPrice = new TextField(16);

    private TextField maxPrice = new TextField(16);

    private TextField minNumber = new TextField(16);

    private TextField maxNumber = new TextField(16);


    private EnumList<SearchingMode> order = new EnumList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
    private ComboBoxSearchingMode modeName;

    private ComboBoxSearchingMode modeDescription;

    private Panel results = Panel.newGrid(0,1);

    private ComboBoxSelectedBool cmpNameSorting;

    private NumComboBox cmpNamePrio = new NumComboBox();

    private ComboBoxSelectedBool cmpDescriptionSorting;

    private NumComboBox cmpDescriptionPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpPriceSorting;

    private NumComboBox cmpPricePrio = new NumComboBox();

    private ComboBoxSelectedBool cmpNumberSorting;

    private NumComboBox cmpNumberPrio = new NumComboBox();

    private boolean buy;

    public PaginatorItem(MainWindow _window, Panel _p,ChangeableTitle _w, FacadeGame _d, boolean _buy) {
        super(_window, ACCESS_ITEM,_p);
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
        modeDescription = new ComboBoxSearchingMode();
        modeDescription.setWithDefaultValue(false);
        modeDescription.refresh(order, getMessagesSearchMode());
        cmpNameSorting = new ComboBoxSelectedBool();
        cmpNameSorting.setWithDefaultValue(false);
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpDescriptionSorting = new ComboBoxSelectedBool();
        cmpDescriptionSorting.setWithDefaultValue(false);
        cmpDescriptionSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPriceSorting = new ComboBoxSelectedBool();
        cmpPriceSorting.setWithDefaultValue(false);
        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNumberSorting = new ComboBoxSelectedBool();
        cmpNumberSorting.setWithDefaultValue(false);
        cmpNumberSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nb_ = PaginationItem.NB_CMPARATORS;
        for (int i = CustList.FIRST_INDEX; i <= nb_; i++) {
            cmpNamePrio.addItem(i);
            cmpDescriptionPrio.addItem(i);
            cmpPricePrio.addItem(i);
            cmpNumberPrio.addItem(i);
        }
        getFacade().setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeDescriptionItem(SearchingMode.WHOLE_STRING);
        StringList it_ = new StringList();
        for (String i: getFacade().getData().getItems().getKeys()) {
            String abTr_ = getFacade().translateItem(i);
            it_.add(abTr_);
        }
        name = AutoCompleteDocument.createAutoCompleteTextField(it_, 16);
//        name.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfNameItem(convertStringField(name.getText()));
//            }
//        });
//        description.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfDescriptionItem(convertStringField(description.getText()));
//            }
//        });
        modeName.setListener(new ChangedModeEvent(modeName, name));
//        modeName.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeName.getCurrent();
//                getFacade().setSearchModeNameItem(s_);
//                AutoCompleteDocument.setMode(name, s_);
//            }
//        });
//        modeDescription.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeDescription.getCurrent();
//                getFacade().setSearchModeDescriptionItem(s_);
//            }
//        });
//        minPrice.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinPriceItem(convertNumberField(minPrice.getText()));
//            }
//        });
//        maxPrice.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxPriceItem(convertNumberField(maxPrice.getText()));
//            }
//        });
//        minNumber.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinNumberItem(convertLgIntField(minNumber.getText()));
//            }
//        });
//        maxNumber.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxNumberItem(convertLgIntField(maxNumber.getText()));
//            }
//        });
//        cmpNameSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNameIncreasingItem(cmpNameSorting.getCurrent());
//            }
//        });
//        cmpDescriptionSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpDescriptionIncreasingItem(cmpDescriptionSorting.getCurrent());
//            }
//        });
//        cmpPriceSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPriceIncreasingItem(cmpPriceSorting.getCurrent());
//            }
//        });
//        cmpNumberSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNumberIncreasingItem(cmpNumberSorting.getCurrent());
//            }
//        });
//        cmpNamePrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNamePriorityItem((Integer)cmpNamePrio.getSelectedItem());
//            }
//        });
//        cmpDescriptionPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpDescriptionPriorityItem((Integer)cmpDescriptionPrio.getSelectedItem());
//            }
//        });
//        cmpPricePrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPricePriorityItem((Integer)cmpPricePrio.getSelectedItem());
//            }
//        });
//        cmpNumberPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNumberPriorityItem((Integer)cmpNumberPrio.getSelectedItem());
//            }
//        });
        Panel search_;
        search_ = Panel.newGrid(0,3);
        search_.add(new TextLabel(getMessages().getVal(NAME)));
        search_.add(name);
        search_.add(modeName);
        search_.add(new TextLabel(getMessages().getVal(DESCRIPTION)));
        search_.add(description);
        search_.add(modeDescription);
        search_.add(new TextLabel(getMessages().getVal(PRICE)));
        search_.add(minPrice);
        search_.add(maxPrice);
        search_.add(new TextLabel(getMessages().getVal(NUMBER)));
        search_.add(minNumber);
        search_.add(maxNumber);
        _p.add(search_);
        Panel sorting_;
        sorting_ = Panel.newGrid(0,3);
        sorting_.add(new TextLabel(getMessages().getVal(NAME)));
        sorting_.add(cmpNameSorting);
        sorting_.add(cmpNamePrio);
        sorting_.add(new TextLabel(getMessages().getVal(DESCRIPTION)));
        sorting_.add(cmpDescriptionSorting);
        sorting_.add(cmpDescriptionPrio);
        sorting_.add(new TextLabel(getMessages().getVal(PRICE)));
        sorting_.add(cmpPriceSorting);
        sorting_.add(cmpPricePrio);
        sorting_.add(new TextLabel(getMessages().getVal(NUMBER)));
        sorting_.add(cmpNumberSorting);
        sorting_.add(cmpNumberPrio);
        _p.add(sorting_);
        Panel top_;
        top_ = Panel.newLineBox();
        LabelButton button_;
        button_ = new LabelButton(getMessages().getVal(SEARCH));
        button_.addMouseListener(new SearchEvent(this));
        top_.add(button_);
        button_ = new LabelButton(getMessages().getVal(NEW_SEARCH));
        button_.addMouseListener(new NewSearchEvent(this));
        top_.add(button_);
        _p.add(top_);
        int side_ = getFacade().getMap().getSideLength();
        Ints widths_ = new Ints();
//        int nameWidth_ = getHeader().width(getMessages().getVal(NAME));
//        widths_.add(nameWidth_);
//        int numberWidth_ = getHeader().width(getMessages().getVal(NUMBER));
//        widths_.add(numberWidth_);
//        int width_ = side_+nameWidth_+numberWidth_;
//        width_ += getHeader().width(getMessages().getVal(PRICE));
        widths_.add(getHeader().width(getMessages().getVal(NAME)));
        widths_.add(getHeader().width(getMessages().getVal(NUMBER)));
        widths_.add(getHeader().width(getMessages().getVal(PRICE)));
        widths_.add(getHeader().width(getMessages().getVal(DESCRIPTION)));
//        if (width_ < getHeader().width(getMessages().getVal(DESCRIPTION))) {
//            width_ = getHeader().width(getMessages().getVal(DESCRIPTION));
//        }
        getHeader().addString(getMessages().getVal(NAME), side_);
        getHeader().addString(getMessages().getVal(DESCRIPTION), side_, Paginator.HEIGTH_CHARS);
        getHeader().addString(getMessages().getVal(PRICE), side_, Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS);
        getHeader().addString(getMessages().getVal(NUMBER), side_, Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS);
        getHeader().setPreferredSize(new Dimension((int)widths_.getMaximum(1), Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS));
        results.add(getHeader());
        //results.add(new JLabel(getMessages().getVal(ITEM)));
        _p.add(new ScrollPane(results));
        Panel bottom_ = Panel.newLineBox();
        getNbResults().setValue(getFacade().getNbResultsPerPageItem());
        getNbResults().addChangeListener(new ChangedNbResultsEvent(this));
        bottom_.add(getNbResults());
        getPages().setListener(new ChangedPageEvent(this));
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
        int int_ = getNbResults().getValue();
        if (int_ <= 0) {
            return;
        }
        getFacade().setNbResultsPerPageItem(int_);
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
        getFacade().changePageItem(getPages().getSelectedIndex());
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void changeDeltaPage() {
        String text_ = getDelta().getText();
        if (text_.isEmpty()) {
            getFacade().setDeltaItem(1);
            return;
        }
        int nbDelta_ = Numbers.parseInt(text_);
        if (nbDelta_ <= 0) {
            return;
        }
        getFacade().setDeltaItem(nbDelta_);
    }

    public void refreshLang() {
        initMessages(ACCESS_ITEM);
        modeName.refresh(order, getMessagesSearchMode());
        modeDescription.refresh(order, getMessagesSearchMode());
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpDescriptionSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNumberSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
    }

    public void clearResults() {
        getFacade().clearFoundResultsItems();
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }

    @Override
    public void search() {
        setInfos();
        getFacade().searchObjectToBuyOrSell(buy);
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
        getFacade().newSearchItem();
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }

    private void setInfos() {
        SearchingMode s_;
        getFacade().setContentOfNameItem(convertStringField(name.getText()));
        getFacade().setContentOfDescriptionItem(convertStringField(description.getText()));
        s_ = modeName.getCurrent();
        getFacade().setSearchModeNameItem(s_);
        s_ = modeDescription.getCurrent();
        getFacade().setSearchModeDescriptionItem(s_);
        getFacade().setMinPriceItem(convertLongNumberField(minPrice.getText()));
        getFacade().setMaxPriceItem(convertLongNumberField(maxPrice.getText()));
        getFacade().setMinNumberItem(convertLgIntField(minNumber.getText()));
        getFacade().setMaxNumberItem(convertLgIntField(maxNumber.getText()));
        getFacade().setCmpNameIncreasingItem(cmpNameSorting.getCurrent());
        getFacade().setCmpDescriptionIncreasingItem(cmpDescriptionSorting.getCurrent());
        getFacade().setCmpPriceIncreasingItem(cmpPriceSorting.getCurrent());
        getFacade().setCmpNumberIncreasingItem(cmpNumberSorting.getCurrent());
        getFacade().setCmpNamePriorityItem(cmpNamePrio.getCurrent());
        getFacade().setCmpDescriptionPriorityItem(cmpDescriptionPrio.getCurrent());
        getFacade().setCmpPricePriorityItem(cmpPricePrio.getCurrent());
        getFacade().setCmpNumberPriorityItem(cmpNumberPrio.getCurrent());
    }

    @Override
    public void check(int _line) {
        getFacade().checkLineItem(_line);
        check(_line, getFacade().getLineItem());
//        int count_ = results.getComponentCount();
//        for (int i = CustList.SECOND_INDEX; i < count_; i++) {
//            SelectableLabel l_ = (SelectableLabel) results.getComponent(i);
//            l_.setSelected(false);
//        }
//        if (getFacade().getLineItem() != CustList.INDEX_NOT_FOUND_ELT) {
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
        CustList<SortingItem> rendered_ = getFacade().getRenderedItem();
        CustList<ItemLabel> list_ = new CustList<ItemLabel>();
        int nb_ = rendered_.size();
        for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
            ItemLabel l_ = new ItemLabel(rendered_.get(i));
            l_.setImagesResults(getFacade());
            l_.addMouseListener(new PaginatorEvent(this,i));
            list_.add(l_);
        }
        results.add(getHeader());
        //results.add(new JLabel(getMessages().getVal(ITEM)));
        for (ItemLabel l: list_) {
            l.repaintLabel();
            results.add(l);
            getResultsLabels().add(l);
        }
    }
    private void changePages() {
        setAdding(true);
        getPages().removeAllItems();
        int nbPages_ = getFacade().pagesItem();
        for (int i = CustList.FIRST_INDEX; i < nbPages_; i++) {
            getPages().addItem(i);
        }
        getEnd().setTextAndSize(Integer.toString(nbPages_ - 1));
        setAdding(false);
    }
    private void changeNav() {
        changeNav(getFacade().enabledPreviousItem(), getFacade().enabledNextItem(), getFacade().pagesItem(), getFacade().getNumberPageItem());
    }

    @Override
    public void begin() {
        getFacade().beginItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void previousDelta() {
        getFacade().previousDeltaItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void previous() {
        getFacade().previousItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void next() {
        getFacade().nextItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void nextDelta() {
        getFacade().nextDeltaItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void end() {
        getFacade().endItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }
}
