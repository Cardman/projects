package aiki.gui.components;


import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.facade.PaginationHealingItem;
import aiki.fight.enums.Statistic;
import aiki.gui.WindowAiki;
import aiki.gui.components.labels.HealingItemLabel;
import aiki.gui.components.listeners.ChangedModeEvent;
import aiki.gui.listeners.PaginatorEvent;
import aiki.sml.MessagesPkGame;
import aiki.sml.MessagesRenderPaginatorHealingItem;
import aiki.util.SortingHealingItem;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.util.*;
import aiki.facade.enums.SearchingMode;
import aiki.facade.enums.SelectedBoolean;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class PaginatorHealingItem extends PaginatorCommonItem {

//    private static final String CST_NAME = "name";
//
//    private static final String CST_DESCRIPTION = "description";
//
//    private static final String CST_STATUS = "status";
//
//    private static final String HP = "hp";
//
//    private static final String HP_RATE = "hpRate";
//
//    private static final String PP = "pp";
//
//    private static final String PRICE = "price";
//
//    private static final String RELATIVE_HP = "relativeHp";
//
//    private static final String RELATIVE_PP = "relativePp";
//
//    private static final String HEAL_MOVE = "healMove";
//
//    private static final String STATISTIC = "statistic";
//
//    private static final String HEAL_KO = "healKo";

    //private static final String ITEM = "item";

//    private static final String NUMBER = "number";

//    private final AbsTextField name;

//    private final AbsTextField description = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField status;

    private final AbsCustCheckBox relativeHpCheck;

//    private ComboBox<SelectedBoolean> relativeHp;

    private final AbsTextField minHp = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxHp = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField minHpRate = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxHpRate = getMain().getCompoFactory().newTextField(16);

    private final ComboBox<SelectedBoolean> relativePp;

    private final AbsTextField minPp = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxPp = getMain().getCompoFactory().newTextField(16);

    private final ComboBox<SelectedBoolean> healMove;

    private final ComboBox<Statistic> statis;

    private final ComboBox<SelectedBoolean> healFromKo;

//    private final AbsTextField minPrice = getMain().getCompoFactory().newTextField(16);

//    private final AbsTextField maxPrice = getMain().getCompoFactory().newTextField(16);

//    private final AbsTextField minNumber = getMain().getCompoFactory().newTextField(16);

//    private final AbsTextField maxNumber = getMain().getCompoFactory().newTextField(16);

//    private final IdList<SearchingMode> order = new IdList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
//    private final ComboBox<SearchingMode> modeName;

//    private final ComboBox<SearchingMode> modeDescription;

    private final ComboBox<SearchingMode> modeStatus;

//    private final AbsPanel results = getMain().getCompoFactory().newGrid(0,1);

//    private final ComboBox<SelectedBoolean> cmpNameSorting;

//    private final NumComboBox cmpNamePrio;

//    private final ComboBox<SelectedBoolean> cmpDescriptionSorting;

//    private final NumComboBox cmpDescriptionPrio;

//    private final ComboBox<SelectedBoolean> cmpPriceSorting;

//    private final NumComboBox cmpPricePrio;

//    private final ComboBox<SelectedBoolean> cmpNumberSorting;

//    private final NumComboBox cmpNumberPrio;

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

    public PaginatorHealingItem(WindowAiki _window, AbsPanel _p, AbsCommonFrame _w, FacadeGame _d) {
        super(_window, _p,_w,_d);
//        cmpNamePrio = new NumComboBox(_window.getFrames());
//        cmpDescriptionPrio = new NumComboBox(_window.getFrames());
        cmpHpPrio = new NumComboBox(_window.getFrames());
        cmpNbStatisticsPrio = new NumComboBox(_window.getFrames());
        cmpNbStatusPrio = new NumComboBox(_window.getFrames());
//        cmpNumberPrio = new NumComboBox(_window.getFrames());
//        cmpPricePrio = new NumComboBox(_window.getFrames());
        cmpPpPrio = new NumComboBox(_window.getFrames());
        cmpRelativeHpPrio = new NumComboBox(_window.getFrames());
        cmpRelativePpPrio = new NumComboBox(_window.getFrames());
        setWindow(_w);
        setFacade(_d);
//        order.add(SearchingMode.WHOLE_STRING);
//        order.add(SearchingMode.SUBSTRING);
//        order.add(SearchingMode.META_CHARACTER);
//        order.add(SearchingMode.BEGIN);
//        order.add(SearchingMode.END);
//        order.add(SearchingMode.MATCH_SPACE);
//        modeName = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
//        modeName.refresh(order, getMessagesSearchMode());
//        modeDescription = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
//        modeDescription.refresh(order, getMessagesSearchMode());
        modeStatus = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeStatus.refresh(getOrder(), getMessagesSearchMode());
        relativeHpCheck = getMain().getCompoFactory().newCustCheckBox();
        relativeHpCheck.setText(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.RELATIVE_HP));
//        relativeHp = new ComboBox<SelectedBoolean>();
//        relativeHp.setWithDefaultValue(false);
//        relativeHp.refresh(getFacade().getData().getTranslatedBooleans().getVal(Constants.getLanguage()));
        relativePp = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        relativePp.refresh(getFacade().getTranslatedBooleansCurLanguage());
        relativePp.setSelectedItem(SelectedBoolean.YES_AND_NO);
        relativePp.getCombo().repaint();
        healMove = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        healMove.refresh(getFacade().getTranslatedBooleansCurLanguage());
        healMove.setSelectedItem(SelectedBoolean.YES_AND_NO);
        healMove.getCombo().repaint();
        healFromKo = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        healFromKo.refresh(getFacade().getTranslatedBooleansCurLanguage());
        healFromKo.setSelectedItem(SelectedBoolean.YES_AND_NO);
        healFromKo.getCombo().repaint();
        statis = new ComboBox<Statistic>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        statis.refresh(stats(getMain().getFrames().getLanguage()));
//        cmpNameSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
//        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpDescriptionSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
//        cmpDescriptionSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpPriceSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
//        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpNumberSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
//        cmpNumberSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPpSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpPpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpRelativePpSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpRelativePpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpHpSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpHpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpRelativeHpSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpRelativeHpSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNbStatisticsSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpNbStatisticsSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNbStatusSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpNbStatusSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nb_ = PaginationHealingItem.NB_COMPARATORS;
//        cmpNamePrio.setItems(nb_+1);
//        cmpDescriptionPrio.setItems(nb_+1);
//        cmpPricePrio.setItems(nb_+1);
//        cmpNumberPrio.setItems(nb_+1);
        cmpPpPrio.setItems(nb_+1);
        cmpRelativePpPrio.setItems(nb_+1);
        cmpHpPrio.setItems(nb_+1);
        cmpRelativeHpPrio.setItems(nb_+1);
        cmpNbStatisticsPrio.setItems(nb_+1);
        cmpNbStatusPrio.setItems(nb_+1);
        getFacade().setSearchModeNameHealingItem(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeDescriptionHealingItem(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeStatusHealingItem(SearchingMode.WHOLE_STRING);
//        StringList it_ = new StringList();
//        for (String i: getFacade().getData().getItems().getKeys()) {
//            String abTr_ = getFacade().translateItem(i);
//            it_.add(abTr_);
//        }
//        name = getMain().getCompoFactory().newTextField(16);
//        AutoCompleteDocument nameAuto_ = new AutoCompleteDocument(name, it_, _window.getFrames());
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
        status = getMain().getCompoFactory().newTextField(16);
        AutoCompleteDocument statusAuto_ = new AutoCompleteDocument(status, st_, _window.getFrames());
//        status.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfStatusHealingItem(convertStringField(status.getText()));
//            }
//        });
//        modeName.setListener(new ChangedModeEvent(modeName, nameAuto_));
//        modeName.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeName.getCurrent();
//                getFacade().setSearchModeNameHealingItem(s_);
//                AutoCompleteDocument.setMode(name, s_);
//            }
//        });
        modeStatus.setListener(new ChangedModeEvent(modeStatus, statusAuto_));
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
//        AbsPanel search_;
//        search_ = getMain().getCompoFactory().newGrid(0,3);
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_NAME)));
//        search_.add(name);
//        search_.add(modeName.self());
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_DESCRIPTION)));
//        search_.add(description);
//        search_.add(modeDescription.self());
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_STATUS)));
//        search_.add(status);
//        search_.add(modeStatus.self());
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.HP)));
//        search_.add(minHp);
//        search_.add(maxHp);
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.HP_RATE)));
//        search_.add(minHpRate);
//        search_.add(maxHpRate);
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PP)));
//        search_.add(minPp);
//        search_.add(maxPp);
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PRICE)));
//        search_.add(minPrice);
//        search_.add(maxPrice);
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.NUMBER)));
//        search_.add(minNumber);
//        search_.add(maxNumber);
//        search_.add(new JLabel(getMessages().getVal(RELATIVE_HP)));
//        search_.add(relativeHp);
//        search_.add(relativeHpCheck);
//        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
//        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.RELATIVE_PP)));
//        search_.add(relativePp.self());
//        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.HEAL_MOVE)));
//        search_.add(healMove.self());
//        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.HEAL_KO)));
//        search_.add(healFromKo.self());
//        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
//        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.STATISTIC)));
//        search_.add(statis.self());
//        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
//        _p.add(search_);
//        AbsPanel sorting_;
//        sorting_ = getMain().getCompoFactory().newGrid(0,3);
//        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_NAME)));
//        sorting_.add(cmpNameSorting.self());
//        sorting_.add(cmpNamePrio.self());
//        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_DESCRIPTION)));
//        sorting_.add(cmpDescriptionSorting.self());
//        sorting_.add(cmpDescriptionPrio.self());
//        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PRICE)));
//        sorting_.add(cmpPriceSorting.self());
//        sorting_.add(cmpPricePrio.self());
//        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.NUMBER)));
//        sorting_.add(cmpNumberSorting.self());
//        sorting_.add(cmpNumberPrio.self());
//        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PP)));
//        sorting_.add(cmpPpSorting.self());
//        sorting_.add(cmpPpPrio.self());
//        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.RELATIVE_PP)));
//        sorting_.add(cmpRelativePpSorting.self());
//        sorting_.add(cmpRelativePpPrio.self());
//        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.HP)));
//        sorting_.add(cmpHpSorting.self());
//        sorting_.add(cmpHpPrio.self());
//        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.RELATIVE_HP)));
//        sorting_.add(cmpRelativeHpSorting.self());
//        sorting_.add(cmpRelativeHpPrio.self());
//        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.STATISTIC)));
//        sorting_.add(cmpNbStatisticsSorting.self());
//        sorting_.add(cmpNbStatisticsPrio.self());
//        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_STATUS)));
//        sorting_.add(cmpNbStatusSorting.self());
//        sorting_.add(cmpNbStatusPrio.self());
//        _p.add(sorting_);
//        beginBuild(_p);
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
//        int side_ = getFacade().getMap().getSideLength();
//        int nameWidth_ = getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_NAME),SPACES));
//        int numberWidth_ = getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.NUMBER),SPACES));
//        int width_ = side_+nameWidth_+numberWidth_;
//        width_ += getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PRICE),SPACES));
//        width_ = NumberUtil.max(width_, getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_DESCRIPTION)));
//        if (width_ < getHeader().width(getMessages().getVal(CST_DESCRIPTION))) {
//            width_ = getHeader().width(getMessages().getVal(CST_DESCRIPTION));
//        }
//        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_NAME),SPACES), side_);
//        getHeader().addString(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_DESCRIPTION), side_, Paginator.HEIGTH_CHARS);
//        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.NUMBER),SPACES), side_+nameWidth_);
//        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PRICE),SPACES), side_+nameWidth_+numberWidth_);
//        getHeader().setPreferredSize(new MetaDimension(width_, Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS));
//        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
//        results.add(getHeader().getPaintableLabel());
//        _p.add(getMain().getCompoFactory().newAbsScrollPane(results));
//        getNbResults().setValue(getFacade().getNbResultsPerPageHealingItem());
//        finishBuild(_p);
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
        joinGui(_p);
    }

    @Override
    protected void joinGui(AbsPanel _p) {
        AbsPanel search_;
        search_ = getMain().getCompoFactory().newGrid(0,3);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_NAME)));
        search_.add(getName());
        search_.add(getModeName().self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_DESCRIPTION)));
        search_.add(getDescription());
        search_.add(getModeDescription().self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_STATUS)));
        search_.add(status);
        search_.add(modeStatus.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.HP)));
        search_.add(minHp);
        search_.add(maxHp);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.HP_RATE)));
        search_.add(minHpRate);
        search_.add(maxHpRate);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PP)));
        search_.add(minPp);
        search_.add(maxPp);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PRICE)));
        search_.add(getMinPrice());
        search_.add(getMaxPrice());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.NUMBER)));
        search_.add(getMinNumber());
        search_.add(getMaxNumber());
//        search_.add(new JLabel(getMessages().getVal(RELATIVE_HP)));
//        search_.add(relativeHp);
        search_.add(relativeHpCheck);
        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.RELATIVE_PP)));
        search_.add(relativePp.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.HEAL_MOVE)));
        search_.add(healMove.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.HEAL_KO)));
        search_.add(healFromKo.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.STATISTIC)));
        search_.add(statis.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        _p.add(search_);
        AbsPanel sorting_;
        sorting_ = getMain().getCompoFactory().newGrid(0,3);
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_NAME)));
        sorting_.add(getCmpNameSorting().self());
        sorting_.add(getCmpNamePrio().self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_DESCRIPTION)));
        sorting_.add(getCmpDescriptionSorting().self());
        sorting_.add(getCmpDescriptionPrio().self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PRICE)));
        sorting_.add(getCmpPriceSorting().self());
        sorting_.add(getCmpPricePrio().self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.NUMBER)));
        sorting_.add(getCmpNumberSorting().self());
        sorting_.add(getCmpNumberPrio().self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PP)));
        sorting_.add(cmpPpSorting.self());
        sorting_.add(cmpPpPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.RELATIVE_PP)));
        sorting_.add(cmpRelativePpSorting.self());
        sorting_.add(cmpRelativePpPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.HP)));
        sorting_.add(cmpHpSorting.self());
        sorting_.add(cmpHpPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.RELATIVE_HP)));
        sorting_.add(cmpRelativeHpSorting.self());
        sorting_.add(cmpRelativeHpPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.STATISTIC)));
        sorting_.add(cmpNbStatisticsSorting.self());
        sorting_.add(cmpNbStatisticsPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_STATUS)));
        sorting_.add(cmpNbStatusSorting.self());
        sorting_.add(cmpNbStatusPrio.self());
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
        int side_ = getFacade().getMap().getSideLength();
        int nameWidth_ = getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_NAME),SPACES));
        int numberWidth_ = getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.NUMBER),SPACES));
        int width_ = side_+nameWidth_+numberWidth_;
        width_ += getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PRICE),SPACES));
        width_ = NumberUtil.max(width_, getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_DESCRIPTION)));
//        if (width_ < getHeader().width(getMessages().getVal(CST_DESCRIPTION))) {
//            width_ = getHeader().width(getMessages().getVal(CST_DESCRIPTION));
//        }
        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_NAME),SPACES), side_);
        getHeader().addString(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_DESCRIPTION), side_, Paginator.HEIGTH_CHARS);
        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.NUMBER),SPACES), side_+nameWidth_);
        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PRICE),SPACES), side_+nameWidth_+numberWidth_);
        getHeader().setPreferredSize(new MetaDimension(width_, Paginator.HEIGTH_CHARS + Paginator.HEIGTH_CHARS));
        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
        getResults().add(getHeader().getPaintableLabel());
        _p.add(getMain().getCompoFactory().newAbsScrollPane(getResults()));
        getNbResults().setValue(getFacade().getNbResultsPerPageHealingItem());
        finishBuild(_p);
    }

    private IdMap<Statistic, String> stats(String _lg) {
        IdMap<Statistic,String> id_ = new IdMap<Statistic, String>();
        id_.addEntry(Statistic.NOTHING,"");
        id_.addAllEntries(getFacade().getData().getTranslatedStatistics().getVal(_lg));
        return id_;
    }

    @Override
    protected StringMap<String> messagesInitSpec() {
        return MessagesPkGame.getPaginatorHealItContentTr(MessagesPkGame.getAppliTr(getMain().getFrames().currentLg())).getMapping();
    }

    @Override
    public void changeNbResults() {
        int int_ = getNbResults().getValue();
//        if (int_ <= 0) {
//            return;
//        }
        getFacade().setNbResultsPerPageHealingItem(int_);
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
        getFacade().changePageHealingItem(getPages().getSelectedIndex());
//        refreshResults();
        appendResults();
        getWindow().pack();
    }

    @Override
    public void changeDeltaPage() {
        String text_ = getDelta().getText();
//        if (text_.isEmpty()) {
//            getFacade().setDeltaHealingItem(1);
//            return;
//        }
//        int nbDelta_ = NumberUtil.parseInt(text_);
//        if (nbDelta_ <= 0) {
//            return;
//        }
//        getFacade().setDeltaHealingItem(nbDelta_);
        getFacade().setDeltaHealingItem(getFacade().getPaginationHealingItem().adj(text_));
    }

//    public void refreshLang() {
//        initMessages();
//        modeName.refresh(order, getMessagesSearchMode());
//        modeDescription.refresh(order, getMessagesSearchMode());
//        relativeHpCheck.setText(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.RELATIVE_HP));
//        //relativeHp.refresh(getFacade().getData().getTranslatedBooleans().getVal(Constants.getLanguage()));
//        relativePp.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        String lg_ = getMain().getLanguageKey();
//        statis.refresh(stats(lg_));
//        healMove.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpDescriptionSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpPriceSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//        cmpNumberSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
//    }

    @Override
    public void search() {
        setInfos();
        getFacade().searchPokemonHealingItem();
        appendResults();
//        refreshResults();
//        changePages();
//        changeNav();
//        getWindow().pack();
    }

    @Override
    public void newSearch() {
        setInfos();
        getFacade().newSearchHealingItem();
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

    private void setInfos() {
        getFacade().setContentOfNameHealingItem(convertStringField(getName().getText()));
        getFacade().setContentOfDescriptionHealingItem(convertStringField(getDescription().getText()));
        getFacade().setContentOfStatusHealingItem(convertStringField(status.getText()));
        SearchingMode s_;
        s_ = getModeName().getCurrent();
        getFacade().setSearchModeNameHealingItem(s_);
        s_ = modeStatus.getCurrent();
        getFacade().setSearchModeStatusHealingItem(s_);
        s_ = getModeDescription().getCurrent();
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
        getFacade().setMinPriceHealingItem(convertLongNumberField(getMinPrice().getText()));
        getFacade().setMaxPriceHealingItem(convertLongNumberField(getMaxPrice().getText()));
        getFacade().setMinNumberHealingItem(convertLgIntField(getMinNumber().getText()));
        getFacade().setMaxNumberHealingItem(convertLgIntField(getMaxNumber().getText()));
        getFacade().setCmpNameIncreasingHealingItem(getCmpNameSorting().getCurrent());
        getFacade().setCmpDescriptionIncreasingHealingItem(getCmpDescriptionSorting().getCurrent());
        getFacade().setCmpPriceIncreasingHealingItem(getCmpPriceSorting().getCurrent());
        getFacade().setCmpNumberIncreasingHealingItem(getCmpNumberSorting().getCurrent());
        getFacade().setCmpPpIncreasingHealingItem(cmpPpSorting.getCurrent());
        getFacade().setCmpRelativeRatePpIncreasingHealingItem(cmpRelativePpSorting.getCurrent());
        getFacade().setCmpRateHpIncreasingHealingItem(cmpHpSorting.getCurrent());
        getFacade().setCmpRelativeRateHpIncreasingHealingItem(cmpRelativeHpSorting.getCurrent());
        getFacade().setCmpStatisticsIncreasingHealingItem(cmpNbStatisticsSorting.getCurrent());
        getFacade().setCmpNbHealedStatusIncreasingHealingItem(cmpNbStatusSorting.getCurrent());
        getFacade().setCmpNamePriorityHealingItem(getCmpNamePrio().getCurrent());
        getFacade().setCmpDescriptionPriorityHealingItem(getCmpDescriptionPrio().getCurrent());
        getFacade().setCmpPricePriorityHealingItem(getCmpPricePrio().getCurrent());
        getFacade().setCmpNumberPriorityHealingItem(getCmpNumberPrio().getCurrent());
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
    public void refreshResults() {
        getFacade().setLineHealingItem(IndexConstants.INDEX_NOT_FOUND_ELT);
        getResults().removeAll();
        getResultsLabels().clear();
        getHeader().clearStrings();
        int side_ = getFacade().getMap().getSideLength();
        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_NAME),SPACES), side_);
        getHeader().addString(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_DESCRIPTION), side_, Paginator.HEIGTH_CHARS);


        CustList<SortingHealingItem> rendered_ = getFacade().getRenderedHealingItem();
        CustList<HealingItemLabel> list_ = new CustList<HealingItemLabel>();
        int thirdColumn_ = IndexConstants.SIZE_EMPTY;
        int fourthColumn_ = getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.CST_NAME),SPACES));
        int fifthColumn_ = getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.NUMBER),SPACES));
        //item.getName()
        //item.getItemClass()
        //item.getHp().toString()
        int nb_ = rendered_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            HealingItemLabel l_ = new HealingItemLabel(rendered_.get(i), getMain().getCompoFactory());
            l_.initMessages(getMessagesSpec());
            l_.addMouseListener(new PaginatorEvent(this,i));
            int th_ = l_.getThirdColumnWidth();
//            if (th_ > thirdColumn_) {
//                thirdColumn_ = th_;
//            }
//            int thTwo_ = l_.getFontMetrics(l_.getFont()).stringWidth(rendered_.get(i).getName());
            thirdColumn_ = NumberUtil.max(thirdColumn_, th_);
            fourthColumn_ = NumberUtil.max(fourthColumn_, th_);
//            if (thTwo_ > fourthColumn_) {
//                fourthColumn_ = thTwo_;
//            }
//            int thThree_ = l_.getFourthColumnWidth();
            fifthColumn_ = NumberUtil.max(fifthColumn_,l_.getFourthColumnWidth());
//            if (thThree_ > fifthColumn_) {
//                fifthColumn_ = thThree_;
//            }
            list_.add(l_);
        }
        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.NUMBER),SPACES), side_+fourthColumn_);
        getHeader().addString(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorHealingItem.PRICE),SPACES), side_+fourthColumn_+fifthColumn_);
        for (HealingItemLabel l: list_) {
            l.setImagesResults(getMain().getImageFactory(),getFacade(), thirdColumn_, fourthColumn_, fifthColumn_,getMain().getTileRender());
        }
//        results.add(new JLabel(getMessages().getVal(ITEM)));
        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
        getResults().add(getHeader().getPaintableLabel());
        for (HealingItemLabel l: list_) {
            AbsMetaLabelPk.paintPk(getMain().getImageFactory(), l);
            getResults().add(l.getPaintableLabel());
            getResultsLabels().add(l);
        }
    }
    public void changePages() {
//        setAdding(true);
        int nbPages_ = getFacade().pagesHealingItem();
        getPages().setItems(nbPages_);
        getEnd().setText(Long.toString(nbPages_ - 1L));
//        setAdding(false);
    }
    public void changeNav() {
        changeNav(getFacade().enabledPreviousHealingItem(), getFacade().enabledNextHealingItem(), getFacade().pagesHealingItem(), getFacade().getNumberPageHealingItem());
    }

    @Override
    public void begin() {
        getFacade().beginHealingItem();
        appendResults();
    }

    @Override
    public void previousDelta() {
        getFacade().previousDeltaHealingItem();
        appendResults();
    }

    @Override
    public void previous() {
        getFacade().previousHealingItem();
        appendResults();
    }

    @Override
    public void next() {
        getFacade().nextHealingItem();
        appendResults();
    }

    @Override
    public void nextDelta() {
        getFacade().nextDeltaHealingItem();
        appendResults();
    }

    @Override
    public void end() {
        getFacade().endHealingItem();
        appendResults();
    }

    public AbsTextField getStatus() {
        return status;
    }

    public AbsCustCheckBox getRelativeHpCheck() {
        return relativeHpCheck;
    }

    public AbsTextField getMinHp() {
        return minHp;
    }

    public AbsTextField getMaxHp() {
        return maxHp;
    }

    public AbsTextField getMinHpRate() {
        return minHpRate;
    }

    public AbsTextField getMaxHpRate() {
        return maxHpRate;
    }

    public ComboBox<SelectedBoolean> getRelativePp() {
        return relativePp;
    }

    public AbsTextField getMinPp() {
        return minPp;
    }

    public AbsTextField getMaxPp() {
        return maxPp;
    }

    public ComboBox<SelectedBoolean> getHealMove() {
        return healMove;
    }

    public ComboBox<Statistic> getStatis() {
        return statis;
    }

    public ComboBox<SelectedBoolean> getHealFromKo() {
        return healFromKo;
    }

    public ComboBox<SearchingMode> getModeStatus() {
        return modeStatus;
    }

    public ComboBox<SelectedBoolean> getCmpPpSorting() {
        return cmpPpSorting;
    }

    public NumComboBox getCmpPpPrio() {
        return cmpPpPrio;
    }

    public ComboBox<SelectedBoolean> getCmpRelativePpSorting() {
        return cmpRelativePpSorting;
    }

    public NumComboBox getCmpRelativePpPrio() {
        return cmpRelativePpPrio;
    }

    public ComboBox<SelectedBoolean> getCmpHpSorting() {
        return cmpHpSorting;
    }

    public NumComboBox getCmpHpPrio() {
        return cmpHpPrio;
    }

    public ComboBox<SelectedBoolean> getCmpRelativeHpSorting() {
        return cmpRelativeHpSorting;
    }

    public NumComboBox getCmpRelativeHpPrio() {
        return cmpRelativeHpPrio;
    }

    public ComboBox<SelectedBoolean> getCmpNbStatisticsSorting() {
        return cmpNbStatisticsSorting;
    }

    public NumComboBox getCmpNbStatisticsPrio() {
        return cmpNbStatisticsPrio;
    }

    public ComboBox<SelectedBoolean> getCmpNbStatusSorting() {
        return cmpNbStatusSorting;
    }

    public NumComboBox getCmpNbStatusPrio() {
        return cmpNbStatusPrio;
    }
}
