package aiki.gui.components;


import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.facade.PaginationPokemonPlayer;
import aiki.facade.enums.SelectedBoolean;
import aiki.gui.WindowAiki;
import aiki.gui.components.labels.PokemonLabel;
import aiki.gui.components.listeners.ChangedModeEvent;
import aiki.gui.listeners.PaginatorEvent;
import aiki.map.pokemon.enums.Gender;
import aiki.sml.GamesPk;
import aiki.sml.MessagesRenderPaginatorPk;
import aiki.util.SortingPokemonPlayer;
import code.gui.*;
import code.gui.images.MetaDimension;
import code.util.*;
import aiki.facade.enums.SearchingMode;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class PaginatorPokemon extends Paginator {

//    private static final String CST_NAME = "name";

//    private static final String CST_LEVEL = "level";

//    private static final String CST_ITEM = "item";

//    private static final String CST_WITH_ITEM = "withitem";

//    private static final String CST_ABILITY = "ability";

//    private static final String CST_GENDER = "gender";

//    private static final String CST_MOVES = "moves";

//    private static final String CST_EVOLUTIONS = "evolutions";

    private final AbsTextField name;

    private final AbsTextField ability;

    private final AbsTextField item;

    private final ComboBox<SelectedBoolean> withItem;

    private final AbsTextField moves;

    private final IdList<SearchingMode> order = new IdList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
    private final ComboBox<SearchingMode> modeName;

    private final ComboBox<SearchingMode> modeAbility;

    private final ComboBox<SearchingMode> modeItem;

    private final ComboBox<SearchingMode> modeMoves;

    private final AbsTextField minLevel = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxLevel = getMain().getCompoFactory().newTextField(16);

    private final ComboBox<Gender> gender;

    private final AbsTextField minPossEvos = getMain().getCompoFactory().newTextField(16);

    private final AbsTextField maxPossEvos = getMain().getCompoFactory().newTextField(16);

    private final AbsPanel results = getMain().getCompoFactory().newGrid(0,1);

    private final ComboBox<SelectedBoolean> cmpNameSorting;

    private final NumComboBox cmpNamePrio;

    private final ComboBox<SelectedBoolean> cmpAbilitySorting;

    private final NumComboBox cmpAbilityPrio;

    private final ComboBox<SelectedBoolean> cmpItemSorting;

    private final NumComboBox cmpItemPrio;

    private final ComboBox<SelectedBoolean> cmpLevelSorting;

    private final NumComboBox cmpLevelPrio;

    private final ComboBox<SelectedBoolean> cmpGenderSorting;

    private final NumComboBox cmpGenderPrio;

    private final ComboBox<SelectedBoolean> cmpPossEvosSorting;

    private final NumComboBox cmpPossEvosPrio;
    private final AbsButton detailButton;

    public PaginatorPokemon(WindowAiki _window, AbsPanel _p, ChangeableTitle _w, FacadeGame _d, AbsButton _detail) {
        super(_window, _p);
        detailButton = _detail;
        cmpNamePrio = new NumComboBox(_window.getFrames());
        cmpAbilityPrio = new NumComboBox(_window.getFrames());
        cmpItemPrio = new NumComboBox(_window.getFrames());
        cmpLevelPrio = new NumComboBox(_window.getFrames());
        cmpGenderPrio = new NumComboBox(_window.getFrames());
        cmpPossEvosPrio = new NumComboBox(_window.getFrames());
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
        modeAbility = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeAbility.refresh(order, getMessagesSearchMode());
        modeItem = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeItem.refresh(order, getMessagesSearchMode());
        withItem = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        withItem.refresh(getFacade().getTranslatedBooleansCurLanguage());
        withItem.setSelectedItem(SelectedBoolean.YES_AND_NO);
        withItem.getCombo().repaint();
        modeMoves = new ComboBox<SearchingMode>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        modeMoves.refresh(order, getMessagesSearchMode());
        gender = new ComboBox<Gender>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        gender.refresh(genders());
        cmpNameSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpAbilitySorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpAbilitySorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpItemSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpItemSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpLevelSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpLevelSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpGenderSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpGenderSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPossEvosSorting = new ComboBox<SelectedBoolean>(GuiBaseUtil.combo(_window.getImageFactory(),new StringList(), -1, getMain().getCompoFactory()));
        cmpPossEvosSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nb_ = PaginationPokemonPlayer.NB_COMPARATORS;
        cmpNamePrio.setItems(nb_ + 1);
        cmpAbilityPrio.setItems(nb_ + 1);
        cmpItemPrio.setItems(nb_ + 1);
        cmpLevelPrio.setItems(nb_ + 1);
        cmpGenderPrio.setItems(nb_ + 1);
        cmpPossEvosPrio.setItems(nb_ + 1);
        getFacade().setSearchModeNameFirstBox(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeAbilityFirstBox(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeItemFirstBox(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeMoveFirstBox(SearchingMode.WHOLE_STRING);
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
//                getFacade().setContentOfNameFirstBox(convertStringField(name.getText()));
//            }
//        });
        modeName.setListener(new ChangedModeEvent(modeName, nameAuto_));
//        modeName.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeName.getCurrent();
//                getFacade().setSearchModeNameFirstBox(s_);
//                AutoCompleteDocument.setMode(name, s_);
//            }
//        });
        StringList ab_ = new StringList();
        for (String a: getFacade().getData().getAbilities().getKeys()) {
            String abTr_ = getFacade().translateAbility(a);
            ab_.add(abTr_);
        }
        ability = getMain().getCompoFactory().newTextField(16);
        AutoCompleteDocument abilityAuto_ = new AutoCompleteDocument(ability, ab_, _window.getFrames());
//        ability.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfAbilityFirstBox(convertStringField(ability.getText()));
//            }
//        });
        modeAbility.setListener(new ChangedModeEvent(modeAbility, abilityAuto_));
//        modeAbility.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeAbility.getCurrent();
//                getFacade().setSearchModeAbilityFirstBox(s_);
//                AutoCompleteDocument.setMode(ability, s_);
//            }
//        });
        StringList it_ = new StringList();
        for (String i: getFacade().getData().getItems().getKeys()) {
            String abTr_ = getFacade().translateItem(i);
            it_.add(abTr_);
        }
        item = getMain().getCompoFactory().newTextField(16);
        AutoCompleteDocument itemAuto_ = new AutoCompleteDocument(item, it_, _window.getFrames());
//        item.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfItemFirstBox(convertStringField(item.getText()));
//            }
//        });
        modeItem.setListener(new ChangedModeEvent(modeItem, itemAuto_));
//        modeItem.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeItem.getCurrent();
//                getFacade().setSearchModeItemFirstBox(s_);
//                AutoCompleteDocument.setMode(item, s_);
//            }
//        });
//        withItem.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setWithItemFirstBox(withItem.getCurrent());
//            }
//        });
        StringList mv_ = new StringList();
        for (String m: getFacade().getData().getMoves().getKeys()) {
            String mvTr_ = getFacade().translateMove(m);
            mv_.add(mvTr_);
        }
        moves = getMain().getCompoFactory().newTextField(16);
        AutoCompleteDocument movesAuto_ = new AutoCompleteDocument(moves, mv_, _window.getFrames());
//        moves.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfMoveFirstBox(convertStringField(moves.getText()));
//            }
//        });
        modeMoves.setListener(new ChangedModeEvent(modeMoves, movesAuto_));
//        modeMoves.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                SearchingMode s_ = modeMoves.getCurrent();
//                getFacade().setSearchModeMoveFirstBox(s_);
//                AutoCompleteDocument.setMode(moves, s_);
//            }
//        });
//        minLevel.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinLevelFirstBox(convertShortNumberField(minLevel.getText()));
//            }
//        });
//        maxLevel.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxLevelFirstBox(convertShortNumberField(maxLevel.getText()));
//            }
//        });
//        gender.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setGenderFirstBox(gender.getCurrent());
//            }
//        });
//        minPossEvos.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMinNbPossEvolsFirstBox(convertShortNumberField(minPossEvos.getText()));
//            }
//        });
//        maxPossEvos.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setMaxNbPossEvolsFirstBox(convertShortNumberField(maxPossEvos.getText()));
//            }
//        });
//        cmpNameSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNameIncreasingFirstBox(cmpNameSorting.getCurrent());
//            }
//        });
//        cmpAbilitySorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpAbilityIncreasingFirstBox(cmpAbilitySorting.getCurrent());
//            }
//        });
//        cmpItemSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpItemIncreasingFirstBox(cmpItemSorting.getCurrent());
//            }
//        });
//        cmpLevelSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpLevelIncreasingFirstBox(cmpLevelSorting.getCurrent());
//            }
//        });
//        cmpGenderSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpGenderIncreasingFirstBox(cmpGenderSorting.getCurrent());
//            }
//        });
//        cmpPossEvosSorting.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPossEvosIncreasingFirstBox(cmpPossEvosSorting.getCurrent());
//            }
//        });
//        cmpNamePrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpNamePriorityFirstBox((Integer)cmpNamePrio.getSelectedItem());
//            }
//        });
//        cmpAbilityPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpAbilityPriorityFirstBox((Integer)cmpAbilityPrio.getSelectedItem());
//            }
//        });
//        cmpItemPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpItemPriorityFirstBox((Integer)cmpItemPrio.getSelectedItem());
//            }
//        });
//        cmpLevelPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpLevelPriorityFirstBox((Integer)cmpLevelPrio.getSelectedItem());
//            }
//        });
//        cmpGenderPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpGenderPriorityFirstBox((Integer)cmpGenderPrio.getSelectedItem());
//            }
//        });
//        cmpPossEvosPrio.addItemListener(new ItemListener(){
//            public void itemStateChanged(ItemEvent _e) {
//                getFacade().setCmpPossEvosPriorityFirstBox((Integer)cmpPossEvosPrio.getSelectedItem());
//            }
//        });
        AbsPanel search_;
        search_ = getMain().getCompoFactory().newGrid(0,3);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_NAME)));
        search_.add(name);
        search_.add(modeName.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_LEVEL)));
        search_.add(minLevel);
        search_.add(maxLevel);
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_GENDER)));
        search_.add(gender.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ABILITY)));
        search_.add(ability);
        search_.add(modeAbility.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ITEM)));
        search_.add(item);
        search_.add(modeItem.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_WITH_ITEM)));
        search_.add(withItem.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_MOVES)));
        search_.add(moves);
        search_.add(modeMoves.self());
        search_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_EVOLUTIONS)));
        search_.add(minPossEvos);
        search_.add(maxPossEvos);
        _p.add(search_);
        AbsPanel sorting_;
        sorting_ = getMain().getCompoFactory().newGrid(0,3);
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_NAME)));
        sorting_.add(cmpNameSorting.self());
        sorting_.add(cmpNamePrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_LEVEL)));
        sorting_.add(cmpLevelSorting.self());
        sorting_.add(cmpLevelPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_GENDER)));
        sorting_.add(cmpGenderSorting.self());
        sorting_.add(cmpGenderPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ABILITY)));
        sorting_.add(cmpAbilitySorting.self());
        sorting_.add(cmpAbilityPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ITEM)));
        sorting_.add(cmpItemSorting.self());
        sorting_.add(cmpItemPrio.self());
        sorting_.add(getMain().getCompoFactory().newPlainLabel(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_EVOLUTIONS)));
        sorting_.add(cmpPossEvosSorting.self());
        sorting_.add(cmpPossEvosPrio.self());
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
        String h_ = StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_NAME),SPACE);
        int side_ = getFacade().getMap().getSideLength();
//        h_ += getMessages().getVal(STEPS)+SPACE;
//        h_ += getMessages().getVal(REMAIN_STEPS);
        //getHeader().addString(h_, FIRST_PIXEL);
        getHeader().addString(h_, side_);
        h_ = StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ABILITY),SPACE);
        getHeader().addString(h_, side_, Paginator.HEIGTH_CHARS);
        int secondCol_ = getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_NAME));
        secondCol_ = NumberUtil.max(secondCol_,getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ABILITY)));
//        if (secondCol_ < getHeader().width(getMessages().getVal(CST_ABILITY))) {
//            secondCol_ = getHeader().width(getMessages().getVal(CST_ABILITY));
//        }
        secondCol_ += side_;
        h_ = StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_LEVEL),SPACE);
        getHeader().addString(h_, secondCol_);
        h_ = StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_GENDER),SPACE);
        getHeader().addString(h_, secondCol_, Paginator.HEIGTH_CHARS);
        int thirdCol_ = getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_LEVEL));
        thirdCol_ = NumberUtil.max(thirdCol_,getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_GENDER)));
//        if (thirdCol_ < getHeader().width(getMessages().getVal(CST_GENDER))) {
//            thirdCol_ = getHeader().width(getMessages().getVal(CST_GENDER));
//        }
        h_ = getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ITEM);
        getHeader().addString(h_, secondCol_+thirdCol_);
        int w_ = getHeader().width(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ITEM));
        getHeader().setPreferredSize(new MetaDimension(w_+secondCol_+thirdCol_, HEIGTH_CHARS+HEIGTH_CHARS));
        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
        results.add(getHeader().getPaintableLabel());
        _p.add(getMain().getCompoFactory().newAbsScrollPane(results));
        getNbResults().setValue(getFacade().getNbResultsPerPageFirstBox());
        detailButton.setEnabled(false);
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

    private IdMap<Gender, String> genders() {
        IdMap<Gender, String> id_ = new IdMap<Gender, String>();
        id_.addEntry(Gender.NONE,"");
        id_.addAllEntries(getFacade().getTranslatedGendersCurLanguage());
        return id_;
    }

    @Override
    protected StringMap<String> messagesInitSpec() {
        return GamesPk.getPaginatorPkContentTr(GamesPk.getAppliTr(getMain().getFrames().currentLg())).getMapping();
    }

    @Override
    public void changeNbResults() {
        int int_ = getNbResults().getValue();
//        if (int_ <= 0) {
//            return;
//        }
        getFacade().setNbResultsPerPageFirstBox(int_);
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
        getFacade().changePageFirstBox(getPages().getSelectedIndex());
        appendResults();
        getWindow().pack();
    }

    @Override
    public void changeDeltaPage() {
        String text_ = getDelta().getText();
//        if (text_.isEmpty()) {
//            getFacade().setDeltaFirstBox(1);
//            return;
//        }
//        int nbDelta_ = NumberUtil.parseInt(text_);
//        if (nbDelta_ <= 0) {
//            return;
//        }
//        getFacade().setDeltaFirstBox(nbDelta_);
        getFacade().setDeltaFirstBox(getFacade().getFirstPaginationPk().adj(text_));
    }

    public void refreshLang() {
        initMessages();
        gender.refresh(genders());
        modeName.refresh(order, getMessagesSearchMode());
        modeAbility.refresh(order, getMessagesSearchMode());
        modeItem.refresh(order, getMessagesSearchMode());
        withItem.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpAbilitySorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpItemSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpLevelSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpGenderSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPossEvosSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
    }

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
        getFacade().searchPokemonFirstBox();
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
        getFacade().newSearchPokemonFirstBox();
        appendResults();
//        refreshResults();
//        changePages();
//        changeNav();
//        getWindow().pack();
    }

    private void setInfos() {
        getFacade().setContentOfNameFirstBox(convertStringField(name.getText()));
        getFacade().setContentOfAbilityFirstBox(convertStringField(ability.getText()));
        getFacade().setWithItemFirstBox(withItem.getCurrent());
        getFacade().setContentOfItemFirstBox(convertStringField(item.getText()));
        getFacade().setContentOfMoveFirstBox(convertStringField(moves.getText()));
        SearchingMode s_;
        s_ = modeName.getCurrent();
        getFacade().setSearchModeNameFirstBox(s_);
        s_ = modeAbility.getCurrent();
        getFacade().setSearchModeAbilityFirstBox(s_);
        s_ = modeItem.getCurrent();
        getFacade().setSearchModeItemFirstBox(s_);
        s_ = modeMoves.getCurrent();
        getFacade().setSearchModeMoveFirstBox(s_);
        getFacade().setMinLevelFirstBox(convertLongNumberField(minLevel.getText()));
        getFacade().setMaxLevelFirstBox(convertLongNumberField(maxLevel.getText()));
        getFacade().setGenderFirstBox(gender.getCurrent());
        getFacade().setMinNbPossEvolsFirstBox(convertLongNumberField(minPossEvos.getText()));
        getFacade().setMaxNbPossEvolsFirstBox(convertLongNumberField(maxPossEvos.getText()));
        getFacade().setCmpNameIncreasingFirstBox(cmpNameSorting.getCurrent());
        getFacade().setCmpAbilityIncreasingFirstBox(cmpAbilitySorting.getCurrent());
        getFacade().setCmpItemIncreasingFirstBox(cmpItemSorting.getCurrent());
        getFacade().setCmpLevelIncreasingFirstBox(cmpLevelSorting.getCurrent());
        getFacade().setCmpGenderIncreasingFirstBox(cmpGenderSorting.getCurrent());
        getFacade().setCmpPossEvosIncreasingFirstBox(cmpPossEvosSorting.getCurrent());
        getFacade().setCmpNamePriorityFirstBox(cmpNamePrio.getCurrent());
        getFacade().setCmpAbilityPriorityFirstBox(cmpAbilityPrio.getCurrent());
        getFacade().setCmpItemPriorityFirstBox(cmpItemPrio.getCurrent());
        getFacade().setCmpLevelPriorityFirstBox(cmpLevelPrio.getCurrent());
        getFacade().setCmpGenderPriorityFirstBox(cmpGenderPrio.getCurrent());
        getFacade().setCmpPossEvosPriorityFirstBox(cmpPossEvosPrio.getCurrent());
    }

    @Override
    public void check(int _line) {
        getFacade().checkLinePokemonFirstBox(_line);
        int lineFirstBox_ = getFacade().getLineFirstBox();
        check(_line, lineFirstBox_);
        detailButton.setEnabled(lineFirstBox_ != IndexConstants.INDEX_NOT_FOUND_ELT);
//        int count_ = results.getComponentCount();
//        for (int i = CustList.SECOND_INDEX; i < count_; i++) {
//            SelectableLabel l_ = (SelectableLabel) results.getComponent(i);
//            l_.setSelected(false);
//        }
//        if (getFacade().getLineFirstBox() != CustList.INDEX_NOT_FOUND_ELT) {
//            SelectableLabel l_ = (SelectableLabel) results.getComponent(_line + 1);
//            l_.setSelected(true);
//        }
//        for (int i = CustList.SECOND_INDEX; i < count_; i++) {
//            SelectableLabel l_ = (SelectableLabel) results.getComponent(i);
//            l_.repaint();
//        }
    }
    public void refreshResults() {
        detailButton.setEnabled(false);
        getFacade().setLinePokemonFirstBox(IndexConstants.INDEX_NOT_FOUND_ELT);
        results.removeAll();
        getHeader().clearStrings();
        getResultsLabels().clear();
        //results.add(header);
        CustList<SortingPokemonPlayer> rendered_ = getFacade().getRenderedFirstBox();
        CustList<PokemonLabel> list_ = new CustList<PokemonLabel>();
        //Header header_ = new Header();
        int nb_ = rendered_.size();
        for (int i = IndexConstants.FIRST_INDEX; i < nb_; i++) {
            PokemonLabel l_ = new PokemonLabel(rendered_.get(i), getMain().getCompoFactory());
            l_.setImagesResults(getMain().getImageFactory(), getFacade(),getMain().getTileRender());
            l_.refresh(genders());
            l_.addMouseListener(new PaginatorEvent(this,i));
            //l_.setPreferredSize(new Dimension(100,10));
            list_.add(l_);
        }
        String h_ = getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_NAME);
        int side_ = getFacade().getMap().getSideLength();
//        h_ += getMessages().getVal(STEPS)+SPACE;
//        h_ += getMessages().getVal(REMAIN_STEPS);
        //getHeader().addString(h_, FIRST_PIXEL);
        getHeader().addString(StringUtil.concat(h_,SPACE), side_);
        h_ = getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ABILITY);
        getHeader().addString(StringUtil.concat(h_,SPACE), side_, Paginator.HEIGTH_CHARS);
        //int maxPixName_ = header_.getFontMetrics(header_.getFont()).stringWidth(getMessages().getVal(NAME));
        int maxPixName_ = getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_NAME),SPACE));
        for (PokemonLabel l: list_) {
            int value_ = l.stringWidth(StringUtil.concat(l.getPokemon().getName(),SPACE));
            maxPixName_ = NumberUtil.max(maxPixName_,value_);
//            if (value_ > maxPixName_) {
//                maxPixName_ = value_;
//            }
        }
        //setNameCoord
        //header_.addString(getMessages().getVal(NAME), 0);
//        int maxPixAbility_ = header_.getFontMetrics(header_.getFont()).stringWidth(getMessages().getVal(ABILITY));
        int maxPixAbility_ = getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ABILITY),SPACE));
        for (PokemonLabel l: list_) {
            int value_ = l.stringWidth(StringUtil.concat(l.getPokemon().getAbility(),SPACE));
            maxPixAbility_ = NumberUtil.max(maxPixAbility_,value_);
//            if (value_ > maxPixAbility_) {
//                maxPixAbility_ = value_;
//            }
        }
        int thirdColumn_ = IndexConstants.SIZE_EMPTY;
        for (PokemonLabel l: list_) {
            int value_ = l.getThirdColumnWidth();
            thirdColumn_ = NumberUtil.max(thirdColumn_,value_);
//            if (value_ > thirdColumn_) {
//                thirdColumn_ = value_;
//            }
        }
        int secondCol_ = NumberUtil.max(maxPixAbility_, maxPixName_);
        h_ = getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_LEVEL);
        getHeader().addString(StringUtil.concat(h_,SPACE), secondCol_ + side_);
        h_ = getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_GENDER);
        getHeader().addString(StringUtil.concat(h_,SPACE), secondCol_ + side_, Paginator.HEIGTH_CHARS);
        int thirdCol_ = NumberUtil.max(getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_GENDER),SPACE)),getHeader().width(StringUtil.concat(getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_LEVEL),SPACE)));
//        if (thirdCol_ < getHeader().width(StringUtil.concat(getMessages().getVal(CST_GENDER),SPACE))) {
//            thirdCol_ = getHeader().width(StringUtil.concat(getMessages().getVal(CST_GENDER),SPACE));
//        }
        h_ = getMessagesSpec().getVal(MessagesRenderPaginatorPk.CST_ITEM);
        getHeader().addString(h_, thirdCol_+secondCol_ + side_);
        for (PokemonLabel l: list_) {
            l.setNameCoord(secondCol_, thirdColumn_);
        }
        //header_.addString(getMessages().getVal(ABILITY), maxPixName_);
//        int maxPixItem_ = header_.getFontMetrics(header_.getFont()).stringWidth(getMessages().getVal(ITEM));
//        for (PokemonLabel l: list_) {
//            l.setAbilityCoord(maxPixName_);
//            int value_ = l.getFontMetrics(l.getFont()).stringWidth(l.getPokemon().getItem());
//            if (value_ > maxPixItem_) {
//                maxPixItem_ = value_;
//            }
//        }
//        header_.addString(getMessages().getVal(ITEM), maxPixName_ + maxPixAbility_);
//        int maxPixLevel_ = header_.getFontMetrics(header_.getFont()).stringWidth(getMessages().getVal(LEVEL));
//        for (PokemonLabel l: list_) {
//            l.setItemCoord(maxPixName_ + maxPixAbility_);
//            int value_ = l.getFontMetrics(l.getFont()).stringWidth(Integer.toString(l.getPokemon().getLevel()));
//            if (value_ > maxPixLevel_) {
//                maxPixLevel_ = value_;
//            }
//        }
//        header_.addString(getMessages().getVal(LEVEL), maxPixName_ + maxPixAbility_ + maxPixItem_);
//        int maxPixGender_ = header_.getFontMetrics(header_.getFont()).stringWidth(getMessages().getVal(GENDER));
//        for (PokemonLabel l: list_) {
//            l.setLevelCoord(maxPixName_ + maxPixAbility_ + maxPixItem_);
//            String str_ = getFacade().getData().getTranslatedGenders().getVal(Constants.getLanguage()).getVal(l.getPokemon().getGender());
//            int value_ = l.getFontMetrics(l.getFont()).stringWidth(str_);
//            if (value_ > maxPixGender_) {
//                maxPixGender_ = value_;
//            }
//        }
//        header_.addString(getMessages().getVal(GENDER), maxPixName_ + maxPixAbility_ + maxPixItem_ + maxPixLevel_);
        //header_.setPreferredSize(new Dimension(maxPixName_ + maxPixAbility_ + maxPixItem_ + maxPixLevel_ + maxPixGender_,10));
//        results.add(new JLabel(POKEMON));
        AbsMetaLabelPk.paintPk(getMain().getImageFactory(), getHeader());
        results.add(getHeader().getPaintableLabel());
        for (PokemonLabel l: list_) {
            AbsMetaLabelPk.paintPk(getMain().getImageFactory(), l);
            //l.setGenderCoord(maxPixName_ + maxPixAbility_ + maxPixItem_ + maxPixItem_);
            //l.setPreferredSize(new Dimension(maxPixName_ + maxPixAbility_ + maxPixItem_ + maxPixLevel_ + maxPixGender_,10));
            results.add(l.getPaintableLabel());
            getResultsLabels().add(l);
        }
    }
    public void changePages() {
//        setAdding(true);
        int nbPages_ = getFacade().pagesPk();
        getPages().setItems(nbPages_);
        getEnd().setText(Long.toString(nbPages_ - 1L));
//        setAdding(false);
    }
    public void changeNav() {
        changeNav(getFacade().enabledPreviousFirstBox(), getFacade().enabledNextFirstBox(), getFacade().pagesPk(), getFacade().getNumberPageFirstBox());
    }

    @Override
    public void begin() {
        getFacade().beginFirstBox();
        appendResults();
    }

    @Override
    public void previousDelta() {
        getFacade().previousDeltaFirstBox();
        appendResults();
    }

    @Override
    public void previous() {
        getFacade().previousFirstBox();
        appendResults();
    }

    @Override
    public void next() {
        getFacade().nextFirstBox();
        appendResults();
    }

    @Override
    public void nextDelta() {
        getFacade().nextDeltaFirstBox();
        appendResults();
    }

    @Override
    public void end() {
        getFacade().endFirstBox();
        appendResults();
    }

    public AbsTextField getName() {
        return name;
    }

    public AbsTextField getAbility() {
        return ability;
    }

    public AbsTextField getItem() {
        return item;
    }

    public ComboBox<SelectedBoolean> getWithItem() {
        return withItem;
    }

    public AbsTextField getMoves() {
        return moves;
    }

    public ComboBox<SearchingMode> getModeName() {
        return modeName;
    }

    public ComboBox<SearchingMode> getModeAbility() {
        return modeAbility;
    }

    public ComboBox<SearchingMode> getModeItem() {
        return modeItem;
    }

    public ComboBox<SearchingMode> getModeMoves() {
        return modeMoves;
    }

    public AbsTextField getMinLevel() {
        return minLevel;
    }

    public AbsTextField getMaxLevel() {
        return maxLevel;
    }

    public ComboBox<Gender> getGender() {
        return gender;
    }

    public AbsTextField getMinPossEvos() {
        return minPossEvos;
    }

    public AbsTextField getMaxPossEvos() {
        return maxPossEvos;
    }

    public ComboBox<SelectedBoolean> getCmpNameSorting() {
        return cmpNameSorting;
    }

    public NumComboBox getCmpNamePrio() {
        return cmpNamePrio;
    }

    public ComboBox<SelectedBoolean> getCmpAbilitySorting() {
        return cmpAbilitySorting;
    }

    public NumComboBox getCmpAbilityPrio() {
        return cmpAbilityPrio;
    }

    public ComboBox<SelectedBoolean> getCmpItemSorting() {
        return cmpItemSorting;
    }

    public NumComboBox getCmpItemPrio() {
        return cmpItemPrio;
    }

    public ComboBox<SelectedBoolean> getCmpLevelSorting() {
        return cmpLevelSorting;
    }

    public NumComboBox getCmpLevelPrio() {
        return cmpLevelPrio;
    }

    public ComboBox<SelectedBoolean> getCmpGenderSorting() {
        return cmpGenderSorting;
    }

    public NumComboBox getCmpGenderPrio() {
        return cmpGenderPrio;
    }

    public ComboBox<SelectedBoolean> getCmpPossEvosSorting() {
        return cmpPossEvosSorting;
    }

    public NumComboBox getCmpPossEvosPrio() {
        return cmpPossEvosPrio;
    }

    public AbsButton getDetailButton() {
        return detailButton;
    }
}
