package aiki.gui.components;
import java.awt.Dimension;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.facade.PaginationHealingItem;
import aiki.gui.MainWindow;
import aiki.gui.components.labels.HealingItemLabel;
import aiki.gui.components.listeners.ChangedModeEvent;
import aiki.gui.components.listeners.ChangedNbResultsEvent;
import aiki.gui.components.listeners.ChangedPageEvent;
import aiki.gui.components.listeners.NewSearchEvent;
import aiki.gui.components.listeners.SearchEvent;
import aiki.gui.listeners.PaginatorEvent;
import aiki.util.SortingHealingItem;
import code.gui.*;
import code.util.CustList;
import code.util.EnumList;
import code.util.Numbers;
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;

public final class PaginatorHealingItem extends Paginator {

    private static final String NAME = "name";

    private static final String DESCRIPTION = "description";

    private static final String STATUS = "status";

    private static final String HP = "hp";

    private static final String HP_RATE = "hpRate";

    private static final String PP = "pp";

    private static final String PRICE = "price";

    private static final String RELATIVE_HP = "relativeHp";

    private static final String RELATIVE_PP = "relativePp";

    private static final String HEAL_MOVE = "healMove";

    private static final String STATISTIC = "statistic";

    private static final String HEAL_KO = "healKo";

    //private static final String ITEM = "item";

    private static final String NUMBER = "number";

    private TextField name;
    private AutoCompleteDocument nameAuto;

    private TextField description = new TextField(16);

    private TextField status;
    private AutoCompleteDocument statusAuto;

    private CustCheckBox relativeHpCheck;

//    private ComboBoxSelectedBool relativeHp;

    private TextField minHp = new TextField(16);

    private TextField maxHp = new TextField(16);

    private TextField minHpRate = new TextField(16);

    private TextField maxHpRate = new TextField(16);

    private ComboBoxSelectedBool relativePp;

    private TextField minPp = new TextField(16);

    private TextField maxPp = new TextField(16);

    private ComboBoxSelectedBool healMove;

    private ComboBoxStatistic statis;

    private ComboBoxSelectedBool healFromKo;

    private TextField minPrice = new TextField(16);

    private TextField maxPrice = new TextField(16);

    private TextField minNumber = new TextField(16);

    private TextField maxNumber = new TextField(16);

    private EnumList<SearchingMode> order = new EnumList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
    private ComboBoxSearchingMode modeName;

    private ComboBoxSearchingMode modeDescription;

    private ComboBoxSearchingMode modeStatus;

    private Panel results = Panel.newGrid(0,1);

    private ComboBoxSelectedBool cmpNameSorting;

    private NumComboBox cmpNamePrio = new NumComboBox();

    private ComboBoxSelectedBool cmpDescriptionSorting;

    private NumComboBox cmpDescriptionPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpPriceSorting;

    private NumComboBox cmpPricePrio = new NumComboBox();

    private ComboBoxSelectedBool cmpNumberSorting;

    private NumComboBox cmpNumberPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpPpSorting;

    private NumComboBox cmpPpPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpRelativePpSorting;

    private NumComboBox cmpRelativePpPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpHpSorting;

    private NumComboBox cmpHpPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpRelativeHpSorting;

    private NumComboBox cmpRelativeHpPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpNbStatisticsSorting;

    private NumComboBox cmpNbStatisticsPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpNbStatusSorting;

    private NumComboBox cmpNbStatusPrio = new NumComboBox();

    public PaginatorHealingItem(MainWindow _window, Panel _p,ChangeableTitle _w, FacadeGame _d) {
        super(_window, ACCESS_HEALING_ITEM,_p);
        setWindow(_w);
        setFacade(_d);
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
        modeStatus = new ComboBoxSearchingMode();
        modeStatus.setWithDefaultValue(false);
        modeStatus.refresh(order, getMessagesSearchMode());
        relativeHpCheck = new CustCheckBox();
        relativeHpCheck.setText(getMessages().getVal(RELATIVE_HP));
//        relativeHp = new ComboBoxSelectedBool();
//        relativeHp.setWithDefaultValue(false);
//        relativeHp.refresh(getFacade().getData().getTranslatedBooleans().getVal(Constants.getLanguage()));
        relativePp = new ComboBoxSelectedBool();
        relativePp.setWithDefaultValue(false);
        relativePp.refresh(getFacade().getTranslatedBooleansCurLanguage());
        healMove = new ComboBoxSelectedBool();
        healMove.setWithDefaultValue(false);
        healMove.refresh(getFacade().getTranslatedBooleansCurLanguage());
        healFromKo = new ComboBoxSelectedBool();
        healFromKo.setWithDefaultValue(false);
        healFromKo.refresh(getFacade().getTranslatedBooleansCurLanguage());
        statis = new ComboBoxStatistic();
        statis.setWithDefaultValue(true);
        String lg_ = getMain().getLanguageKey();
        statis.refresh(getFacade().getData().getTranslatedStatistics().getVal(lg_));
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
        cmpPpSorting = new ComboBoxSelectedBool();
        cmpPpSorting.setWithDefaultValue(false);
        cmpPpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpRelativePpSorting = new ComboBoxSelectedBool();
        cmpRelativePpSorting.setWithDefaultValue(false);
        cmpRelativePpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpHpSorting = new ComboBoxSelectedBool();
        cmpHpSorting.setWithDefaultValue(false);
        cmpHpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpRelativeHpSorting = new ComboBoxSelectedBool();
        cmpRelativeHpSorting.setWithDefaultValue(false);
        cmpRelativeHpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNbStatisticsSorting = new ComboBoxSelectedBool();
        cmpNbStatisticsSorting.setWithDefaultValue(false);
        cmpNbStatisticsSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNbStatusSorting = new ComboBoxSelectedBool();
        cmpNbStatusSorting.setWithDefaultValue(false);
        cmpNbStatusSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nb_ = PaginationHealingItem.NB_COMPARATORS;
        for (int i = CustList.FIRST_INDEX; i <= nb_; i++) {
            cmpNamePrio.addItem(i);
            cmpDescriptionPrio.addItem(i);
            cmpPricePrio.addItem(i);
            cmpNumberPrio.addItem(i);
            cmpPpPrio.addItem(i);
            cmpRelativePpPrio.addItem(i);
            cmpHpPrio.addItem(i);
            cmpRelativeHpPrio.addItem(i);
            cmpNbStatisticsPrio.addItem(i);
            cmpNbStatusPrio.addItem(i);
        }
        getFacade().setSearchModeNameHealingItem(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeDescriptionHealingItem(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeStatusHealingItem(SearchingMode.WHOLE_STRING);
        StringList it_ = new StringList();
        for (String i: getFacade().getData().getItems().getKeys()) {
            String abTr_ = getFacade().translateItem(i);
            it_.add(abTr_);
        }
        name = new TextField(16);
        nameAuto = new AutoCompleteDocument(name,it_, getWindow());
//        name.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfNameHealingItem(convertStringField(name.getText()));
//            }
//        });
//        description.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfDescriptionHealingItem(convertStringField(description.getText()));
//            }
//        });
        StringList st_ = new StringList();
        for (String s: getFacade().getData().getStatus().getKeys()) {
            String stTr_ = getFacade().translateStatus(s);
            st_.add(stTr_);
        }
        status = new TextField(16);
        statusAuto = new AutoCompleteDocument(status,st_, getWindow());
//        status.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfStatusHealingItem(convertStringField(status.getText()));
//            }
//        });
        modeName.setListener(new ChangedModeEvent(modeName, nameAuto));
//        modeName.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeName.getCurrent();
//                getFacade().setSearchModeNameHealingItem(s_);
//                AutoCompleteDocument.setMode(name, s_);
//            }
//        });
        modeStatus.setListener(new ChangedModeEvent(modeStatus, statusAuto));
//        modeStatus.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeStatus.getCurrent();
//                getFacade().setSearchModeStatusHealingItem(s_);
//                AutoCompleteDocument.setMode(status, s_);
//            }
//        });
//        modeDescription.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeDescription.getCurrent();
//                getFacade().setSearchModeDescriptionHealingItem(s_);
//            }
//        });
//        relativeHpCheck.addItemListener(new ItemListener() {
//
//            @Override
//            public void itemStateChanged(ItemEvent _arg0) {
//                if (relativeHpCheck.isSelected()) {
//                    getFacade().setRelativeRateHpHealingItem(SelectedBoolean.YES);
//                } else {
//                    getFacade().setRelativeRateHpHealingItem(SelectedBoolean.YES_AND_NO);
//                }
//            }
//        });
////        relativeHp.addItemListener(new ItemListener(){
////            public void itemStateChanged(ItemEvent _e) {
////                getFacade().setRelativeRateHpHealingItem(relativeHp.getCurrent());
////            }
////        });
//        relativePp.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setRelativeRatePpHealingItem(relativePp.getCurrent());
//            }
//        });
//        healMove.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setHealOneMoveHealingItem(healMove.getCurrent());
//            }
//        });
//        healFromKo.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setKoHealingItem(healFromKo.getCurrent());
//            }
//        });
//        statis.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setStatisticHealingItem(statis.getCurrent());
//            }
//        });
//        minHp.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinHpHealingItem(convertRateField(minHp.getText()));
//            }
//        });
//        maxHp.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxHpHealingItem(convertRateField(maxHp.getText()));
//            }
//        });
//        minHpRate.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinRateHpHealingItem(convertRateField(minHpRate.getText()));
//            }
//        });
//        maxHpRate.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxRateHpHealingItem(convertRateField(maxHpRate.getText()));
//            }
//        });
//        minPp.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinPpHealingItem(convertLongNumberField(minPp.getText()));
//            }
//        });
//        maxPp.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxPpHealingItem(convertLongNumberField(maxPp.getText()));
//            }
//        });
//        minPrice.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinPriceHealingItem(convertNumberField(minPrice.getText()));
//            }
//        });
//        maxPrice.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxPriceHealingItem(convertNumberField(maxPrice.getText()));
//            }
//        });
//        minNumber.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinNumberHealingItem(convertLgIntField(minNumber.getText()));
//            }
//        });
//        maxNumber.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxNumberHealingItem(convertLgIntField(maxNumber.getText()));
//            }
//        });
//        cmpNameSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNameIncreasingHealingItem(cmpNameSorting.getCurrent());
//            }
//        });
//        cmpDescriptionSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpDescriptionIncreasingHealingItem(cmpDescriptionSorting.getCurrent());
//            }
//        });
//        cmpPriceSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPriceIncreasingHealingItem(cmpPriceSorting.getCurrent());
//            }
//        });
//        cmpNumberSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNumberIncreasingHealingItem(cmpNumberSorting.getCurrent());
//            }
//        });
//        cmpPpSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPpIncreasingHealingItem(cmpPpSorting.getCurrent());
//            }
//        });
//        cmpRelativePpSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpRelativeRatePpIncreasingHealingItem(cmpRelativePpSorting.getCurrent());
//            }
//        });
//        cmpHpSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpRateHpIncreasingHealingItem(cmpHpSorting.getCurrent());
//            }
//        });
//        cmpRelativeHpSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpRelativeRateHpIncreasingHealingItem(cmpRelativeHpSorting.getCurrent());
//            }
//        });
//        cmpNbStatisticsSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpStatisticsIncreasingHealingItem(cmpNbStatisticsSorting.getCurrent());
//            }
//        });
//        cmpNbStatusSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNbHealedStatusIncreasingHealingItem(cmpNbStatusSorting.getCurrent());
//            }
//        });
//
//        //Priority
//        cmpNamePrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNamePriorityHealingItem((Integer)cmpNamePrio.getSelectedItem());
//            }
//        });
//        cmpDescriptionPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpDescriptionPriorityHealingItem((Integer)cmpDescriptionPrio.getSelectedItem());
//            }
//        });
//        cmpPricePrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPricePriorityHealingItem((Integer)cmpPricePrio.getSelectedItem());
//            }
//        });
//        cmpNumberPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNumberPriorityHealingItem((Integer)cmpNumberPrio.getSelectedItem());
//            }
//        });
//        cmpPpPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPpPriorityHealingItem((Integer)cmpPpPrio.getSelectedItem());
//            }
//        });
//        cmpRelativePpPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpRelativeRatePpPriorityHealingItem((Integer)cmpRelativePpPrio.getSelectedItem());
//            }
//        });
//        cmpHpPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpRateHpPriorityHealingItem((Integer)cmpHpPrio.getSelectedItem());
//            }
//        });
//        cmpRelativeHpPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpRelativeRateHpPriorityHealingItem((Integer)cmpRelativeHpPrio.getSelectedItem());
//            }
//        });
//        cmpNbStatisticsPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpStatisticsPriorityHealingItem((Integer)cmpNbStatisticsPrio.getSelectedItem());
//            }
//        });
//        cmpNbStatusPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNbHealedStatusPriorityHealingItem((Integer)cmpNbStatusPrio.getSelectedItem());
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
        search_.add(new TextLabel(getMessages().getVal(STATUS)));
        search_.add(status);
        search_.add(modeStatus);
        search_.add(new TextLabel(getMessages().getVal(HP)));
        search_.add(minHp);
        search_.add(maxHp);
        search_.add(new TextLabel(getMessages().getVal(HP_RATE)));
        search_.add(minHpRate);
        search_.add(maxHpRate);
        search_.add(new TextLabel(getMessages().getVal(PP)));
        search_.add(minPp);
        search_.add(maxPp);
        search_.add(new TextLabel(getMessages().getVal(PRICE)));
        search_.add(minPrice);
        search_.add(maxPrice);
        search_.add(new TextLabel(getMessages().getVal(NUMBER)));
        search_.add(minNumber);
        search_.add(maxNumber);
//        search_.add(new JLabel(getMessages().getVal(RELATIVE_HP)));
//        search_.add(relativeHp);
        search_.add(relativeHpCheck);
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        search_.add(new TextLabel(getMessages().getVal(RELATIVE_PP)));
        search_.add(relativePp);
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        search_.add(new TextLabel(getMessages().getVal(HEAL_MOVE)));
        search_.add(healMove);
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        search_.add(new TextLabel(getMessages().getVal(HEAL_KO)));
        search_.add(healFromKo);
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        search_.add(new TextLabel(getMessages().getVal(STATISTIC)));
        search_.add(statis);
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
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
        sorting_.add(new TextLabel(getMessages().getVal(PP)));
        sorting_.add(cmpPpSorting);
        sorting_.add(cmpPpPrio);
        sorting_.add(new TextLabel(getMessages().getVal(RELATIVE_PP)));
        sorting_.add(cmpRelativePpSorting);
        sorting_.add(cmpRelativePpPrio);
        sorting_.add(new TextLabel(getMessages().getVal(HP)));
        sorting_.add(cmpHpSorting);
        sorting_.add(cmpHpPrio);
        sorting_.add(new TextLabel(getMessages().getVal(RELATIVE_HP)));
        sorting_.add(cmpRelativeHpSorting);
        sorting_.add(cmpRelativeHpPrio);
        sorting_.add(new TextLabel(getMessages().getVal(STATISTIC)));
        sorting_.add(cmpNbStatisticsSorting);
        sorting_.add(cmpNbStatisticsPrio);
        sorting_.add(new TextLabel(getMessages().getVal(STATUS)));
        sorting_.add(cmpNbStatusSorting);
        sorting_.add(cmpNbStatusPrio);
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
//        results.setLayout(new BoxLayout(results, BoxLayout.PAGE_AXIS));
        int side_ = getFacade().getMap().getSideLength();
        int nameWidth_ = getHeader().width(StringList.concat(getMessages().getVal(NAME),SPACES));
        int numberWidth_ = getHeader().width(StringList.concat(getMessages().getVal(NUMBER),SPACES));
        int width_ = side_+nameWidth_+numberWidth_;
        width_ += getHeader().width(StringList.concat(getMessages().getVal(PRICE),SPACES));
        if (width_ < getHeader().width(getMessages().getVal(DESCRIPTION))) {
            width_ = getHeader().width(getMessages().getVal(DESCRIPTION));
        }
        getHeader().addString(StringList.concat(getMessages().getVal(NAME),SPACES), side_);
        getHeader().addString(getMessages().getVal(DESCRIPTION), side_, Paginator.HEIGTH_CHARS);
        getHeader().addString(StringList.concat(getMessages().getVal(NUMBER),SPACES), side_+nameWidth_);
        getHeader().addString(StringList.concat(getMessages().getVal(PRICE),SPACES), side_+nameWidth_+numberWidth_);
        getHeader().setPreferredSize(new Dimension(width_, Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS));
        results.add(getHeader());
        _p.add(new ScrollPane(results));
        Panel bottom_ = Panel.newLineBox();
        getNbResults().setValue(getFacade().getNbResultsPerPageHealingItem());
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
        getFacade().setNbResultsPerPageHealingItem(int_);
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
        getFacade().changePageHealingItem(getPages().getSelectedIndex());
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void changeDeltaPage() {
        String text_ = getDelta().getText();
        if (text_.isEmpty()) {
            getFacade().setDeltaHealingItem(1);
            return;
        }
        int nbDelta_ = Numbers.parseInt(text_);
        if (nbDelta_ <= 0) {
            return;
        }
        getFacade().setDeltaHealingItem(nbDelta_);
    }

    public void refreshLang() {
        initMessages(ACCESS_HEALING_ITEM);
        modeName.refresh(order, getMessagesSearchMode());
        modeDescription.refresh(order, getMessagesSearchMode());
        relativeHpCheck.setText(getMessages().getVal(RELATIVE_HP));
        //relativeHp.refresh(getFacade().getData().getTranslatedBooleans().getVal(Constants.getLanguage()));
        relativePp.refresh(getFacade().getTranslatedBooleansCurLanguage());
        String lg_ = getMain().getLanguageKey();
        statis.refresh(getFacade().getData().getTranslatedStatistics().getVal(lg_));
        healMove.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpDescriptionSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNumberSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
    }

    @Override
    public void search() {
        setInfos();
        getFacade().searchPokemonHealingItem();
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }

    @Override
    public void newSearch() {
        setInfos();
        getFacade().newSearchHealingItem();
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }

    private void setInfos() {
        getFacade().setContentOfNameHealingItem(convertStringField(name.getText()));
        getFacade().setContentOfDescriptionHealingItem(convertStringField(description.getText()));
        getFacade().setContentOfStatusHealingItem(convertStringField(status.getText()));
        SearchingMode s_;
        s_ = modeName.getCurrent();
        getFacade().setSearchModeNameHealingItem(s_);
        s_ = modeStatus.getCurrent();
        getFacade().setSearchModeStatusHealingItem(s_);
        s_ = modeDescription.getCurrent();
        getFacade().setSearchModeDescriptionHealingItem(s_);
        if (relativeHpCheck.isSelected()) {
            getFacade().setRelativeRateHpHealingItem(SelectedBoolean.YES);
        } else {
            getFacade().setRelativeRateHpHealingItem(SelectedBoolean.YES_AND_NO);
        }
        getFacade().setRelativeRatePpHealingItem(relativePp.getCurrent());
        getFacade().setHealOneMoveHealingItem(healMove.getCurrent());
        getFacade().setKoHealingItem(healFromKo.getCurrent());
        getFacade().setStatisticHealingItem(statis.getCurrent());
        getFacade().setMinHpHealingItem(convertRateField(minHp.getText()));
        getFacade().setMaxHpHealingItem(convertRateField(maxHp.getText()));
        getFacade().setMinRateHpHealingItem(convertRateField(minHpRate.getText()));
        getFacade().setMaxRateHpHealingItem(convertRateField(maxHpRate.getText()));
        getFacade().setMinPpHealingItem(convertLongNumberField(minPp.getText()));
        getFacade().setMaxPpHealingItem(convertLongNumberField(maxPp.getText()));
        getFacade().setMinPriceHealingItem(convertLongNumberField(minPrice.getText()));
        getFacade().setMaxPriceHealingItem(convertLongNumberField(maxPrice.getText()));
        getFacade().setMinNumberHealingItem(convertLgIntField(minNumber.getText()));
        getFacade().setMaxNumberHealingItem(convertLgIntField(maxNumber.getText()));
        getFacade().setCmpNameIncreasingHealingItem(cmpNameSorting.getCurrent());
        getFacade().setCmpDescriptionIncreasingHealingItem(cmpDescriptionSorting.getCurrent());
        getFacade().setCmpPriceIncreasingHealingItem(cmpPriceSorting.getCurrent());
        getFacade().setCmpNumberIncreasingHealingItem(cmpNumberSorting.getCurrent());
        getFacade().setCmpPpIncreasingHealingItem(cmpPpSorting.getCurrent());
        getFacade().setCmpRelativeRatePpIncreasingHealingItem(cmpRelativePpSorting.getCurrent());
        getFacade().setCmpRateHpIncreasingHealingItem(cmpHpSorting.getCurrent());
        getFacade().setCmpRelativeRateHpIncreasingHealingItem(cmpRelativeHpSorting.getCurrent());
        getFacade().setCmpStatisticsIncreasingHealingItem(cmpNbStatisticsSorting.getCurrent());
        getFacade().setCmpNbHealedStatusIncreasingHealingItem(cmpNbStatusSorting.getCurrent());
        getFacade().setCmpNamePriorityHealingItem(cmpNamePrio.getCurrent());
        getFacade().setCmpDescriptionPriorityHealingItem(cmpDescriptionPrio.getCurrent());
        getFacade().setCmpPricePriorityHealingItem(cmpPricePrio.getCurrent());
        getFacade().setCmpNumberPriorityHealingItem(cmpNumberPrio.getCurrent());
        getFacade().setCmpPpPriorityHealingItem(cmpPpPrio.getCurrent());
        getFacade().setCmpRelativeRatePpPriorityHealingItem(cmpRelativePpPrio.getCurrent());
        getFacade().setCmpRateHpPriorityHealingItem(cmpHpPrio.getCurrent());
        getFacade().setCmpRelativeRateHpPriorityHealingItem(cmpRelativeHpPrio.getCurrent());
        getFacade().setCmpStatisticsPriorityHealingItem(cmpNbStatisticsPrio.getCurrent());
        getFacade().setCmpNbHealedStatusPriorityHealingItem(cmpNbStatusPrio.getCurrent());
    }

    @Override
    public void check(int _line) {
        getFacade().checkLineHealingItem(_line);
        check(_line, getFacade().getLineHealingItem());
//        int count_ = results.getComponentCount();
//        for (int i = CustList.SECOND_INDEX; i < count_; i++) {
//            SelectableLabel l_ = (SelectableLabel) results.getComponent(i);
//            l_.setSelected(false);
//        }
//        if (getFacade().getLineHealingItem() != CustList.INDEX_NOT_FOUND_ELT) {
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
        int side_ = getFacade().getMap().getSideLength();
        getHeader().addString(StringList.concat(getMessages().getVal(NAME),SPACES), side_);
        getHeader().addString(getMessages().getVal(DESCRIPTION), side_, Paginator.HEIGTH_CHARS);


        CustList<SortingHealingItem> rendered_ = getFacade().getRenderedHealingItem();
        CustList<HealingItemLabel> list_ = new CustList<HealingItemLabel>();
        int thirdColumn_ = CustList.SIZE_EMPTY;
        int fourthColumn_ = getHeader().width(StringList.concat(getMessages().getVal(NAME),SPACES));
        int fifthColumn_ = getHeader().width(StringList.concat(getMessages().getVal(NUMBER),SPACES));
        //item.getName()
        //item.getItemClass()
        //item.getHp().toString()
        int nb_ = rendered_.size();
        String lg_ = getMain().getLanguageKey();
        for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
            HealingItemLabel l_ = new HealingItemLabel(rendered_.get(i));
            l_.initMessages(lg_);
            l_.addMouseListener(new PaginatorEvent(this,i));
            int th_ = l_.getThirdColumnWidth();
            if (th_ > thirdColumn_) {
                thirdColumn_ = th_;
            }
//            int thTwo_ = l_.getFontMetrics(l_.getFont()).stringWidth(rendered_.get(i).getName());
            int thTwo_ = th_;
            if (thTwo_ > fourthColumn_) {
                fourthColumn_ = thTwo_;
            }
            int thThree_ = l_.getFourthColumnWidth();
            if (thThree_ > fifthColumn_) {
                fifthColumn_ = thThree_;
            }
            list_.add(l_);
        }
        getHeader().addString(StringList.concat(getMessages().getVal(NUMBER),SPACES), side_+fourthColumn_);
        getHeader().addString(StringList.concat(getMessages().getVal(PRICE),SPACES), side_+fourthColumn_+fifthColumn_);
        for (HealingItemLabel l: list_) {
            l.setImagesResults(getFacade(), thirdColumn_, fourthColumn_, fifthColumn_);
        }
//        results.add(new JLabel(getMessages().getVal(ITEM)));
        results.add(getHeader());
        for (HealingItemLabel l: list_) {
            l.repaintLabel();
            results.add(l);
            getResultsLabels().add(l);
        }
    }
    private void changePages() {
        setAdding(true);
        getPages().removeAllItems();
        int nbPages_ = getFacade().pagesHealingItem();
        for (int i = CustList.FIRST_INDEX; i < nbPages_; i++) {
            getPages().addItem(i);
        }
        getEnd().setTextAndSize(Integer.toString(nbPages_ - 1));
        setAdding(false);
    }
    private void changeNav() {
        changeNav(getFacade().enabledPreviousHealingItem(), getFacade().enabledNextHealingItem(), getFacade().pagesHealingItem(), getFacade().getNumberPageHealingItem());
    }

    @Override
    public void begin() {
        getFacade().beginHealingItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void previousDelta() {
        getFacade().previousDeltaHealingItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void previous() {
        getFacade().previousHealingItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void next() {
        getFacade().nextHealingItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void nextDelta() {
        getFacade().nextDeltaHealingItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void end() {
        getFacade().endHealingItem();
        changeNav();
        refreshResults();
        getWindow().pack();
    }
}
