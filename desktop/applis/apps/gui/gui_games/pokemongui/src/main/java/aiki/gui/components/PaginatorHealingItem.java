package aiki.gui.components;
import java.awt.Dimension;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.facade.PaginationHealingItem;
import aiki.fight.enums.Statistic;
import aiki.gui.WindowAiki;
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
import code.util.StringList;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class PaginatorHealingItem extends Paginator {

    private static final String CST_NAME = "name";

    private static final String CST_DESCRIPTION = "description";

    private static final String CST_STATUS = "status";

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

    private final TextField name;
    private final AutoCompleteDocument nameAuto;

    private final TextField description = new TextField(16);

    private final TextField status;
    private final AutoCompleteDocument statusAuto;

    private final CustCheckBox relativeHpCheck;

//    private ComboBox<SelectedBoolean> relativeHp;

    private final TextField minHp = new TextField(16);

    private final TextField maxHp = new TextField(16);

    private final TextField minHpRate = new TextField(16);

    private final TextField maxHpRate = new TextField(16);

    private final ComboBox<SelectedBoolean> relativePp;

    private final TextField minPp = new TextField(16);

    private final TextField maxPp = new TextField(16);

    private final ComboBox<SelectedBoolean> healMove;

    private final ComboBox<Statistic> statis;

    private final ComboBox<SelectedBoolean> healFromKo;

    private final TextField minPrice = new TextField(16);

    private final TextField maxPrice = new TextField(16);

    private final TextField minNumber = new TextField(16);

    private final TextField maxNumber = new TextField(16);

    private final EnumList<SearchingMode> order = new EnumList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
    private final ComboBox<SearchingMode> modeName;

    private final ComboBox<SearchingMode> modeDescription;

    private final ComboBox<SearchingMode> modeStatus;

    private final Panel results = Panel.newGrid(0,1);

    private final ComboBox<SelectedBoolean> cmpNameSorting;

    private final NumComboBox cmpNamePrio;

    private final ComboBox<SelectedBoolean> cmpDescriptionSorting;

    private final NumComboBox cmpDescriptionPrio;

    private final ComboBox<SelectedBoolean> cmpPriceSorting;

    private final NumComboBox cmpPricePrio;

    private final ComboBox<SelectedBoolean> cmpNumberSorting;

    private final NumComboBox cmpNumberPrio;

    private final ComboBox<SelectedBoolean> cmpPpSorting;

    private final NumComboBox cmpPpPrio;

    private final ComboBox<SelectedBoolean> cmpRelativePpSorting;

    private final NumComboBox cmpRelativePpPrio;

    private final ComboBox<SelectedBoolean> cmpHpSorting;

    private final NumComboBox cmpHpPrio;

    private final ComboBox<SelectedBoolean> cmpRelativeHpSorting;

    private final NumComboBox cmpRelativeHpPrio;

    private final ComboBox<SelectedBoolean> cmpNbStatisticsSorting;

    private final NumComboBox cmpNbStatisticsPrio;

    private final ComboBox<SelectedBoolean> cmpNbStatusSorting;

    private final NumComboBox cmpNbStatusPrio;

    public PaginatorHealingItem(WindowAiki _window, Panel _p, ChangeableTitle _w, FacadeGame _d) {
        super(_window, ACCESS_HEALING_ITEM,_p);
        cmpNamePrio = new NumComboBox(_window.getImageFactory(),_window.getFrames().getGeneComboBox());
        cmpDescriptionPrio = new NumComboBox(_window.getImageFactory(),_window.getFrames().getGeneComboBox());
        cmpHpPrio = new NumComboBox(_window.getImageFactory(),_window.getFrames().getGeneComboBox());
        cmpNbStatisticsPrio = new NumComboBox(_window.getImageFactory(),_window.getFrames().getGeneComboBox());
        cmpNbStatusPrio = new NumComboBox(_window.getImageFactory(),_window.getFrames().getGeneComboBox());
        cmpNumberPrio = new NumComboBox(_window.getImageFactory(),_window.getFrames().getGeneComboBox());
        cmpPricePrio = new NumComboBox(_window.getImageFactory(),_window.getFrames().getGeneComboBox());
        cmpPpPrio = new NumComboBox(_window.getImageFactory(),_window.getFrames().getGeneComboBox());
        cmpRelativeHpPrio = new NumComboBox(_window.getImageFactory(),_window.getFrames().getGeneComboBox());
        cmpRelativePpPrio = new NumComboBox(_window.getImageFactory(),_window.getFrames().getGeneComboBox());
        setWindow(_w);
        setFacade(_d);
        order.add(SearchingMode.WHOLE_STRING);
        order.add(SearchingMode.SUBSTRING);
        order.add(SearchingMode.META_CHARACTER);
        order.add(SearchingMode.BEGIN);
        order.add(SearchingMode.END);
        order.add(SearchingMode.MATCH_SPACE);
        modeName = new ComboBox<SearchingMode>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        modeName.setWithDefaultValue(false);
        modeName.refresh(order, getMessagesSearchMode());
        modeDescription = new ComboBox<SearchingMode>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        modeDescription.setWithDefaultValue(false);
        modeDescription.refresh(order, getMessagesSearchMode());
        modeStatus = new ComboBox<SearchingMode>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        modeStatus.setWithDefaultValue(false);
        modeStatus.refresh(order, getMessagesSearchMode());
        relativeHpCheck = new CustCheckBox();
        relativeHpCheck.setText(getMessages().getVal(RELATIVE_HP));
//        relativeHp = new ComboBox<SelectedBoolean>();
//        relativeHp.setWithDefaultValue(false);
//        relativeHp.refresh(getFacade().getData().getTranslatedBooleans().getVal(Constants.getLanguage()));
        relativePp = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        relativePp.setWithDefaultValue(false);
        relativePp.refresh(getFacade().getTranslatedBooleansCurLanguage());
        healMove = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        healMove.setWithDefaultValue(false);
        healMove.refresh(getFacade().getTranslatedBooleansCurLanguage());
        healFromKo = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        healFromKo.setWithDefaultValue(false);
        healFromKo.refresh(getFacade().getTranslatedBooleansCurLanguage());
        statis = new ComboBox<Statistic>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        statis.setWithDefaultValue(true);
        String lg_ = getMain().getLanguageKey();
        statis.refresh(getFacade().getData().getTranslatedStatistics().getVal(lg_));
        cmpNameSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        cmpNameSorting.setWithDefaultValue(false);
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpDescriptionSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        cmpDescriptionSorting.setWithDefaultValue(false);
        cmpDescriptionSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPriceSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        cmpPriceSorting.setWithDefaultValue(false);
        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNumberSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        cmpNumberSorting.setWithDefaultValue(false);
        cmpNumberSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPpSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        cmpPpSorting.setWithDefaultValue(false);
        cmpPpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpRelativePpSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        cmpRelativePpSorting.setWithDefaultValue(false);
        cmpRelativePpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpHpSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        cmpHpSorting.setWithDefaultValue(false);
        cmpHpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpRelativeHpSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        cmpRelativeHpSorting.setWithDefaultValue(false);
        cmpRelativeHpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNbStatisticsSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        cmpNbStatisticsSorting.setWithDefaultValue(false);
        cmpNbStatisticsSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNbStatusSorting = new ComboBox<SelectedBoolean>(_window.getFrames().getGeneComboBox().createCombo(_window.getImageFactory(),new StringList(), -1));
        cmpNbStatusSorting.setWithDefaultValue(false);
        cmpNbStatusSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nb_ = PaginationHealingItem.NB_COMPARATORS;
        for (int i = IndexConstants.FIRST_INDEX; i <= nb_; i++) {
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
        nameAuto = new AutoCompleteDocument(name,it_, getWindow(),_window.getFrames());
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
        statusAuto = new AutoCompleteDocument(status,st_, getWindow(),_window.getFrames());
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
        search_.add(new TextLabel(getMessages().getVal(CST_NAME)));
        search_.add(name);
        search_.add(modeName.self());
        search_.add(new TextLabel(getMessages().getVal(CST_DESCRIPTION)));
        search_.add(description);
        search_.add(modeDescription.self());
        search_.add(new TextLabel(getMessages().getVal(CST_STATUS)));
        search_.add(status);
        search_.add(modeStatus.self());
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
        search_.add(relativePp.self());
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        search_.add(new TextLabel(getMessages().getVal(HEAL_MOVE)));
        search_.add(healMove.self());
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        search_.add(new TextLabel(getMessages().getVal(HEAL_KO)));
        search_.add(healFromKo.self());
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        search_.add(new TextLabel(getMessages().getVal(STATISTIC)));
        search_.add(statis.self());
        search_.add(new TextLabel(DataBase.EMPTY_STRING));
        _p.add(search_);
        Panel sorting_;
        sorting_ = Panel.newGrid(0,3);
        sorting_.add(new TextLabel(getMessages().getVal(CST_NAME)));
        sorting_.add(cmpNameSorting.self());
        sorting_.add(cmpNamePrio.self());
        sorting_.add(new TextLabel(getMessages().getVal(CST_DESCRIPTION)));
        sorting_.add(cmpDescriptionSorting.self());
        sorting_.add(cmpDescriptionPrio.self());
        sorting_.add(new TextLabel(getMessages().getVal(PRICE)));
        sorting_.add(cmpPriceSorting.self());
        sorting_.add(cmpPricePrio.self());
        sorting_.add(new TextLabel(getMessages().getVal(NUMBER)));
        sorting_.add(cmpNumberSorting.self());
        sorting_.add(cmpNumberPrio.self());
        sorting_.add(new TextLabel(getMessages().getVal(PP)));
        sorting_.add(cmpPpSorting.self());
        sorting_.add(cmpPpPrio.self());
        sorting_.add(new TextLabel(getMessages().getVal(RELATIVE_PP)));
        sorting_.add(cmpRelativePpSorting.self());
        sorting_.add(cmpRelativePpPrio.self());
        sorting_.add(new TextLabel(getMessages().getVal(HP)));
        sorting_.add(cmpHpSorting.self());
        sorting_.add(cmpHpPrio.self());
        sorting_.add(new TextLabel(getMessages().getVal(RELATIVE_HP)));
        sorting_.add(cmpRelativeHpSorting.self());
        sorting_.add(cmpRelativeHpPrio.self());
        sorting_.add(new TextLabel(getMessages().getVal(STATISTIC)));
        sorting_.add(cmpNbStatisticsSorting.self());
        sorting_.add(cmpNbStatisticsPrio.self());
        sorting_.add(new TextLabel(getMessages().getVal(CST_STATUS)));
        sorting_.add(cmpNbStatusSorting.self());
        sorting_.add(cmpNbStatusPrio.self());
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
        int nameWidth_ = getHeader().width(StringUtil.concat(getMessages().getVal(CST_NAME),SPACES));
        int numberWidth_ = getHeader().width(StringUtil.concat(getMessages().getVal(NUMBER),SPACES));
        int width_ = side_+nameWidth_+numberWidth_;
        width_ += getHeader().width(StringUtil.concat(getMessages().getVal(PRICE),SPACES));
        if (width_ < getHeader().width(getMessages().getVal(CST_DESCRIPTION))) {
            width_ = getHeader().width(getMessages().getVal(CST_DESCRIPTION));
        }
        getHeader().addString(StringUtil.concat(getMessages().getVal(CST_NAME),SPACES), side_);
        getHeader().addString(getMessages().getVal(CST_DESCRIPTION), side_, Paginator.HEIGTH_CHARS);
        getHeader().addString(StringUtil.concat(getMessages().getVal(NUMBER),SPACES), side_+nameWidth_);
        getHeader().addString(StringUtil.concat(getMessages().getVal(PRICE),SPACES), side_+nameWidth_+numberWidth_);
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
        int nbDelta_ = NumberUtil.parseInt(text_);
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
        getHeader().addString(StringUtil.concat(getMessages().getVal(CST_NAME),SPACES), side_);
        getHeader().addString(getMessages().getVal(CST_DESCRIPTION), side_, Paginator.HEIGTH_CHARS);


        CustList<SortingHealingItem> rendered_ = getFacade().getRenderedHealingItem();
        CustList<HealingItemLabel> list_ = new CustList<HealingItemLabel>();
        int thirdColumn_ = IndexConstants.SIZE_EMPTY;
        int fourthColumn_ = getHeader().width(StringUtil.concat(getMessages().getVal(CST_NAME),SPACES));
        int fifthColumn_ = getHeader().width(StringUtil.concat(getMessages().getVal(NUMBER),SPACES));
        //item.getName()
        //item.getItemClass()
        //item.getHp().toString()
        int nb_ = rendered_.size();
        String lg_ = getMain().getLanguageKey();
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
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
        getHeader().addString(StringUtil.concat(getMessages().getVal(NUMBER),SPACES), side_+fourthColumn_);
        getHeader().addString(StringUtil.concat(getMessages().getVal(PRICE),SPACES), side_+fourthColumn_+fifthColumn_);
        for (HealingItemLabel l: list_) {
            l.setImagesResults(getMain().getImageFactory(),getFacade(), thirdColumn_, fourthColumn_, fifthColumn_);
        }
//        results.add(new JLabel(getMessages().getVal(ITEM)));
        results.add(getHeader());
        for (HealingItemLabel l: list_) {
            l.repaintLabel(getMain().getImageFactory());
            results.add(l);
            getResultsLabels().add(l);
        }
    }
    private void changePages() {
        setAdding(true);
        getPages().removeAllItems();
        int nbPages_ = getFacade().pagesHealingItem();
        for (int i = IndexConstants.FIRST_INDEX; i < nbPages_; i++) {
            getPages().addItem(i);
        }
        getEnd().setTextAndSize(Long.toString(nbPages_ - 1L));
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
