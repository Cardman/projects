package aiki.gui.components;


import aiki.facade.FacadeGame;
import aiki.facade.PaginationItem;
import aiki.facade.enums.SelectedBoolean;
import aiki.gui.WindowAiki;
import aiki.gui.components.labels.ItemLabel;
import aiki.gui.components.listeners.ChangedModeEvent;
import aiki.gui.listeners.PaginatorEvent;
import aiki.sml.GamesPk;
import aiki.sml.MessagesRenderPaginatorItem;
import aiki.util.SortingItem;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.util.*;
import aiki.facade.enums.SearchingMode;
import code.util.core.IndexConstants;

public final class PaginatorItem extends Paginator {

//    private static final String CST_NAME = "name";

//    private static final String CST_DESCRIPTION = "description";

//    private static final String PRICE = "price";

    //private static final String ITEM = "item";

//    private static final String NUMBER = "number";

    private final AbsTextField name;

    private final AbsTextField description = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField minPrice = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxPrice = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField minNumber = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxNumber = getMain().getCompoFactory().newTextField(16);


    private final IdList<SearchingMode> order = new IdList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
    private final ComboBox<SearchingMode> modeName;

    private final ComboBox<SearchingMode> modeDescription;

    private final AbsPanel results = getMain().getCompoFactory().newGrid(0,1);

    private final ComboBox<SelectedBoolean> cmpNameSorting;

    private final NumComboBox cmpNamePrio;

    private final ComboBox<SelectedBoolean> cmpDescriptionSorting;

    private final NumComboBox cmpDescriptionPrio;

    private final ComboBox<SelectedBoolean> cmpPriceSorting;

    private final NumComboBox cmpPricePrio;

    private final ComboBox<SelectedBoolean> cmpNumberSorting;

    private final NumComboBox cmpNumberPrio;

    private final boolean buy;

    public PaginatorItem(WindowAiki _window, AbsPanel _p, ChangeableTitle _w, FacadeGame _d, boolean _buy) {
        super(_window, _p);
        cmpNamePrio = new NumComboBox(_window.getFrames());
        cmpNumberPrio = new NumComboBox(_window.getFrames());
        cmpPricePrio = new NumComboBox(_window.getFrames());
        cmpDescriptionPrio = new NumComboBox(_window.getFrames());
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
        modeDescription = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeDescription.refresh(order, getMessagesSearchMode());
        cmpNameSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpDescriptionSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpDescriptionSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPriceSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNumberSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpNumberSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nb_ = PaginationItem.NB_CMPARATORS;
        cmpNamePrio.setItems(nb_+1);
        cmpDescriptionPrio.setItems(nb_+1);
        cmpPricePrio.setItems(nb_+1);
        cmpNumberPrio.setItems(nb_+1);
        getFacade().setSearchModeNameItem(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeDescriptionItem(SearchingMode.WHOLE_STRING);
        StringList it_ = new StringList();
        for (String i: getFacade().getData().getItems().getKeys()) {
            String abTr_ = getFacade().translateItem(i);
            it_.add(abTr_);
        }
        name = getMain().getCompoFactory().newTextField(16);
        AutoCompleteDocument nameAuto_ = new AutoCompleteDocument(name, it_, _window.getFrames());
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
        modeName.setListener(new ChangedModeEvent(modeName, nameAuto_));
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
        AbsPanel search_;
        search_ = getMain().getCompoFactory().newGrid(0,3);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorItem.CST_NAME)));
        search_.add(name);
        search_.add(modeName.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorItem.CST_DESCRIPTION)));
        search_.add(description);
        search_.add(modeDescription.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorItem.PRICE)));
        search_.add(minPrice);
        search_.add(maxPrice);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorItem.NUMBER)));
        search_.add(minNumber);
        search_.add(maxNumber);
        _p.add(search_);
        AbsPanel sorting_;
        sorting_ = getMain().getCompoFactory().newGrid(0,3);
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorItem.CST_NAME)));
        sorting_.add(cmpNameSorting.self());
        sorting_.add(cmpNamePrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorItem.CST_DESCRIPTION)));
        sorting_.add(cmpDescriptionSorting.self());
        sorting_.add(cmpDescriptionPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorItem.PRICE)));
        sorting_.add(cmpPriceSorting.self());
        sorting_.add(cmpPricePrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorItem.NUMBER)));
        sorting_.add(cmpNumberSorting.self());
        sorting_.add(cmpNumberPrio.self());
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
        int side_ = getFacade().getMap().getSideLength();
        Ints widths_ = new Ints();
//        int nameWidth_ = getHeader().width(getMessages().getVal(NAME));
//        widths_.add(nameWidth_);
//        int numberWidth_ = getHeader().width(getMessages().getVal(NUMBER));
//        widths_.add(numberWidth_);
//        int width_ = side_+nameWidth_+numberWidth_;
//        width_ += getHeader().width(getMessages().getVal(PRICE));
        widths_.add(getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorItem.CST_NAME)));
        widths_.add(getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorItem.NUMBER)));
        widths_.add(getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorItem.PRICE)));
        widths_.add(getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorItem.CST_DESCRIPTION)));
//        if (width_ < getHeader().width(getMessages().getVal(DESCRIPTION))) {
//            width_ = getHeader().width(getMessages().getVal(DESCRIPTION));
//        }
        getHeader().addString(getMessagesSpec().getVal(MessagesRenderPaginatorItem.CST_NAME), side_);
        getHeader().addString(getMessagesSpec().getVal(MessagesRenderPaginatorItem.CST_DESCRIPTION), side_, Paginator.HEIGTH_CHARS);
        getHeader().addString(getMessagesSpec().getVal(MessagesRenderPaginatorItem.PRICE), side_, Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS);
        getHeader().addString(getMessagesSpec().getVal(MessagesRenderPaginatorItem.NUMBER), side_, Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS);
        getHeader().setPreferredSize(new MetaDimension((int)widths_.getMaximum(1), Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS));
        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
        results.add(getHeader().getPaintableLabel());
        //results.add(new JLabel(getMessages().getVal(ITEM)));
        _p.add(getMain().getCompoFactory().newAbsScrollPane(results));
        getNbResults().setValue(getFacade().getNbResultsPerPageItem());
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
        return GamesPk.getPaginatorItContentTr(GamesPk.getAppliTr(getMain().getFrames().currentLg())).getMapping();
    }

    @Override
    public void changeNbResults() {
        int int_ = getNbResults().getValue();
//        if (int_ <= 0) {
//            return;
//        }
        getFacade().setNbResultsPerPageItem(int_);
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
        getFacade().changePageItem(getPages().getSelectedIndex());
        appendResults();
        getWindow().pack();
    }

    @Override
    public void changeDeltaPage() {
        String text_ = getDelta().getText();
//        if (text_.isEmpty()) {
//            getFacade().setDeltaItem(1);
//            return;
//        }
//        int nbDelta_ = NumberUtil.parseInt(text_);
//        if (nbDelta_ <= 0) {
//            return;
//        }
//        getFacade().setDeltaItem(nbDelta_);
        getFacade().setDeltaItem(getFacade().getPaginationItem().adj(text_));
    }

    public void refreshLang() {
        initMessages();
        modeName.refresh(order, getMessagesSearchMode());
        modeDescription.refresh(order, getMessagesSearchMode());
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpDescriptionSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNumberSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
    }
//
//    public void clearResults() {
//        getFacade().clearFoundResultsItems();
//        appendResults();
////        refreshResults();
////        changePages();
////        changeNav();
////        getWindow().pack();
//    }

    @Override
    public void search() {
        setInfos();
        getFacade().searchObjectToBuyOrSell(buy);
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
        getFacade().newSearchItem();
        appendResults();
//        refreshResults();
//        changePages();
//        changeNav();
//        getWindow().pack();
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
    public void refreshResults() {
        results.removeAll();
        getResultsLabels().clear();
        CustList<SortingItem> rendered_ = getFacade().getRenderedItem();
        CustList<ItemLabel> list_ = new CustList<ItemLabel>();
        int nb_ = rendered_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            ItemLabel l_ = new ItemLabel(rendered_.get(i), getMain().getCompoFactory());
            l_.setImagesResults(getMain().getImageFactory(), getFacade(),getMain().getTileRender());
            l_.addMouseListener(new PaginatorEvent(this,i));
            list_.add(l_);
        }
        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
        results.add(getHeader().getPaintableLabel());
        //results.add(new JLabel(getMessages().getVal(ITEM)));
        for (ItemLabel l: list_) {
            AbsMetaLabelPk.paintPk(getMain().getImageFactory(), l);
            results.add(l.getPaintableLabel());
            getResultsLabels().add(l);
        }
    }
    public void changePages() {
//        setAdding(true);
        int nbPages_ = getFacade().pagesItem();
        getPages().setItems(nbPages_);
        getEnd().setText(Long.toString(nbPages_ - 1L));
//        setAdding(false);
    }
    public void changeNav() {
        changeNav(getFacade().enabledPreviousItem(), getFacade().enabledNextItem(), getFacade().pagesItem(), getFacade().getNumberPageItem());
    }

    @Override
    public void begin() {
        getFacade().beginItem();
        appendResults();
    }

    @Override
    public void previousDelta() {
        getFacade().previousDeltaItem();
        appendResults();
    }

    @Override
    public void previous() {
        getFacade().previousItem();
        appendResults();
    }

    @Override
    public void next() {
        getFacade().nextItem();
        appendResults();
    }

    @Override
    public void nextDelta() {
        getFacade().nextDeltaItem();
        appendResults();
    }

    @Override
    public void end() {
        getFacade().endItem();
        appendResults();
    }

    public AbsTextField getName() {
        return name;
    }

    public AbsTextField getDescription() {
        return description;
    }

    public AbsTextField getMinPrice() {
        return minPrice;
    }

    public AbsTextField getMaxPrice() {
        return maxPrice;
    }

    public AbsTextField getMinNumber() {
        return minNumber;
    }

    public AbsTextField getMaxNumber() {
        return maxNumber;
    }

    public IdList<SearchingMode> getOrder() {
        return order;
    }

    public ComboBox<SearchingMode> getModeName() {
        return modeName;
    }

    public ComboBox<SearchingMode> getModeDescription() {
        return modeDescription;
    }

    public ComboBox<SelectedBoolean> getCmpNameSorting() {
        return cmpNameSorting;
    }

    public NumComboBox getCmpNamePrio() {
        return cmpNamePrio;
    }

    public ComboBox<SelectedBoolean> getCmpDescriptionSorting() {
        return cmpDescriptionSorting;
    }

    public NumComboBox getCmpDescriptionPrio() {
        return cmpDescriptionPrio;
    }

    public ComboBox<SelectedBoolean> getCmpPriceSorting() {
        return cmpPriceSorting;
    }

    public NumComboBox getCmpPricePrio() {
        return cmpPricePrio;
    }

    public ComboBox<SelectedBoolean> getCmpNumberSorting() {
        return cmpNumberSorting;
    }

    public NumComboBox getCmpNumberPrio() {
        return cmpNumberPrio;
    }
}
