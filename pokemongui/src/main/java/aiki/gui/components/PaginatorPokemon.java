package aiki.gui.components;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.labels.PokemonLabel;
import aiki.gui.components.listeners.ChangedDeltaPageEvent;
import aiki.gui.components.listeners.ChangedModeEvent;
import aiki.gui.components.listeners.ChangedNbResultsEvent;
import aiki.gui.components.listeners.ChangedPageEvent;
import aiki.gui.components.listeners.NewSearchEvent;
import aiki.gui.components.listeners.SearchEvent;
import aiki.gui.listeners.PaginatorEvent;
import aiki.util.SortingPokemonPlayer;
import code.gui.AutoCompleteDocument;
import code.gui.ChangeableTitle;
import code.gui.LabelButton;
import code.gui.NumComboBox;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.util.CustList;
import code.util.EnumList;
import code.util.EqList;
import code.util.StringList;
import code.util.pagination.SearchingMode;

public final class PaginatorPokemon extends Paginator {

    private static final String NAME = "name";

    private static final String LEVEL = "level";

    private static final String ITEM = "item";

    private static final String WITH_ITEM = "withitem";

    private static final String ABILITY = "ability";

    private static final String GENDER = "gender";

    private static final String MOVES = "moves";

    private static final String EVOLUTIONS = "evolutions";

    private JTextField name;

    private JTextField ability;

    private JTextField item;

    private ComboBoxSelectedBool withItem;

    private JTextField moves;

    private EnumList<SearchingMode> order = new EnumList<SearchingMode>();

    //private JComboBoxSearchingMode modeFirstName = new JComboBoxSearchingMode();
    private ComboBoxSearchingMode modeName;

    private ComboBoxSearchingMode modeAbility;

    private ComboBoxSearchingMode modeItem;

    private ComboBoxSearchingMode modeMoves;

    private JTextField minLevel = new JTextField(16);

    private JTextField maxLevel = new JTextField(16);

    private ComboBoxGender gender;

    private JTextField minPossEvos = new JTextField(16);

    private JTextField maxPossEvos = new JTextField(16);

    private Panel results = new Panel();

    private ComboBoxSelectedBool cmpNameSorting;

    private NumComboBox cmpNamePrio = new NumComboBox();

    private ComboBoxSelectedBool cmpAbilitySorting;

    private NumComboBox cmpAbilityPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpItemSorting;

    private NumComboBox cmpItemPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpLevelSorting;

    private NumComboBox cmpLevelPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpGenderSorting;

    private NumComboBox cmpGenderPrio = new NumComboBox();

    private ComboBoxSelectedBool cmpPossEvosSorting;

    private NumComboBox cmpPossEvosPrio = new NumComboBox();

    public PaginatorPokemon(MainWindow _window, ChangeableTitle _w, FacadeGame _d) {
        super(_window, ACCESS_POKEMON);
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
        modeAbility = new ComboBoxSearchingMode();
        modeAbility.setWithDefaultValue(false);
        modeAbility.refresh(order, getMessagesSearchMode());
        modeItem = new ComboBoxSearchingMode();
        modeItem.setWithDefaultValue(false);
        modeItem.refresh(order, getMessagesSearchMode());
        withItem = new ComboBoxSelectedBool();
        withItem.setWithDefaultValue(false);
        withItem.refresh(getFacade().getTranslatedBooleansCurLanguage());
        modeMoves = new ComboBoxSearchingMode();
        modeMoves.setWithDefaultValue(false);
        modeMoves.refresh(order, getMessagesSearchMode());
        gender = new ComboBoxGender();
        gender.setWithDefaultValue(true);
        gender.refresh(getFacade().getTranslatedGendersCurLanguage());
        cmpNameSorting = new ComboBoxSelectedBool();
        cmpNameSorting.setWithDefaultValue(false);
        cmpNameSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpAbilitySorting = new ComboBoxSelectedBool();
        cmpAbilitySorting.setWithDefaultValue(false);
        cmpAbilitySorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpItemSorting = new ComboBoxSelectedBool();
        cmpItemSorting.setWithDefaultValue(false);
        cmpItemSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpLevelSorting = new ComboBoxSelectedBool();
        cmpLevelSorting.setWithDefaultValue(false);
        cmpLevelSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpGenderSorting = new ComboBoxSelectedBool();
        cmpGenderSorting.setWithDefaultValue(false);
        cmpGenderSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        cmpPossEvosSorting = new ComboBoxSelectedBool();
        cmpPossEvosSorting.setWithDefaultValue(false);
        cmpPossEvosSorting.refresh(getFacade().getTranslatedBooleansCurLanguage());
        int nb_ = getFacade().getNbComparatorsFirstBox();
        for (int i = CustList.FIRST_INDEX; i <= nb_; i++) {
            cmpNamePrio.addItem(i);
            cmpAbilityPrio.addItem(i);
            cmpItemPrio.addItem(i);
            cmpLevelPrio.addItem(i);
            cmpGenderPrio.addItem(i);
            cmpPossEvosPrio.addItem(i);
        }
        getFacade().setSearchModeNameFirstBox(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeAbilityFirstBox(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeItemFirstBox(SearchingMode.WHOLE_STRING);
        getFacade().setSearchModeMoveFirstBox(SearchingMode.WHOLE_STRING);
        setLayout(new BoxLayout(getComponent(),BoxLayout.PAGE_AXIS));
        StringList pk_ = new StringList();
        for (String p: getFacade().getData().getPokedex().getKeys()) {
            String pkTr_ = getFacade().translatePokemon(p);
            pk_.add(pkTr_);
        }
        name = AutoCompleteDocument.createAutoCompleteTextField(pk_, 16);
//        name.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfNameFirstBox(convertStringField(name.getText()));
//            }
//        });
        modeName.setListener(new ChangedModeEvent(modeName, name));
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
        ability = AutoCompleteDocument.createAutoCompleteTextField(ab_, 16);
//        ability.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfAbilityFirstBox(convertStringField(ability.getText()));
//            }
//        });
        modeAbility.setListener(new ChangedModeEvent(modeAbility, ability));
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
        item = AutoCompleteDocument.createAutoCompleteTextField(it_, 16);
//        item.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfItemFirstBox(convertStringField(item.getText()));
//            }
//        });
        modeItem.setListener(new ChangedModeEvent(modeItem, item));
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
        moves = AutoCompleteDocument.createAutoCompleteTextField(mv_, 16);
//        moves.getDocument().addDocumentListener(new DocumentAdaptater() {
//
//            public void updateText() {
//                getFacade().setContentOfMoveFirstBox(convertStringField(moves.getText()));
//            }
//        });
        modeMoves.setListener(new ChangedModeEvent(modeMoves, moves));
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
        Panel search_;
        search_ = new Panel(new GridLayout(0,3));
        search_.add(new JLabel(getMessages().getVal(NAME)));
        search_.add(name);
        search_.add(modeName);
        search_.add(new JLabel(getMessages().getVal(LEVEL)));
        search_.add(minLevel);
        search_.add(maxLevel);
        search_.add(new JLabel(getMessages().getVal(GENDER)));
        search_.add(gender);
        search_.add(new JLabel(DataBase.EMPTY_STRING));
        search_.add(new JLabel(getMessages().getVal(ABILITY)));
        search_.add(ability);
        search_.add(modeAbility);
        search_.add(new JLabel(getMessages().getVal(ITEM)));
        search_.add(item);
        search_.add(modeItem);
        search_.add(new JLabel(getMessages().getVal(WITH_ITEM)));
        search_.add(withItem);
        search_.add(new JLabel(DataBase.EMPTY_STRING));
        search_.add(new JLabel(getMessages().getVal(MOVES)));
        search_.add(moves);
        search_.add(modeMoves);
        search_.add(new JLabel(getMessages().getVal(EVOLUTIONS)));
        search_.add(minPossEvos);
        search_.add(maxPossEvos);
        add(search_);
        Panel sorting_;
        sorting_ = new Panel(new GridLayout(0,3));
        sorting_.add(new JLabel(getMessages().getVal(NAME)));
        sorting_.add(cmpNameSorting);
        sorting_.add(cmpNamePrio);
        sorting_.add(new JLabel(getMessages().getVal(LEVEL)));
        sorting_.add(cmpLevelSorting);
        sorting_.add(cmpLevelPrio);
        sorting_.add(new JLabel(getMessages().getVal(GENDER)));
        sorting_.add(cmpGenderSorting);
        sorting_.add(cmpGenderPrio);
        sorting_.add(new JLabel(getMessages().getVal(ABILITY)));
        sorting_.add(cmpAbilitySorting);
        sorting_.add(cmpAbilityPrio);
        sorting_.add(new JLabel(getMessages().getVal(ITEM)));
        sorting_.add(cmpItemSorting);
        sorting_.add(cmpItemPrio);
        sorting_.add(new JLabel(getMessages().getVal(EVOLUTIONS)));
        sorting_.add(cmpPossEvosSorting);
        sorting_.add(cmpPossEvosPrio);
        add(sorting_);
        Panel top_;
        top_ = new Panel();
        LabelButton button_;
        button_ = new LabelButton(getMessages().getVal(SEARCH));
        button_.addMouseListener(new SearchEvent(this));
        top_.add(button_);
        button_ = new LabelButton(getMessages().getVal(NEW_SEARCH));
        button_.addMouseListener(new NewSearchEvent(this));
        top_.add(button_);
        add(top_);
//        results.setLayout(new BoxLayout(results, BoxLayout.PAGE_AXIS));
        results.setLayout(new GridLayout(0, 1));
        String h_ = StringList.concat(getMessages().getVal(NAME),SPACE);
        int side_ = getFacade().getMap().getSideLength();
//        h_ += getMessages().getVal(STEPS)+SPACE;
//        h_ += getMessages().getVal(REMAIN_STEPS);
        //getHeader().addString(h_, FIRST_PIXEL);
        getHeader().addString(h_, side_);
        h_ = StringList.concat(getMessages().getVal(ABILITY),SPACE);
        getHeader().addString(h_, side_, Paginator.HEIGTH_CHARS);
        int secondCol_ = getHeader().width(getMessages().getVal(NAME));
        if (secondCol_ < getHeader().width(getMessages().getVal(ABILITY))) {
            secondCol_ = getHeader().width(getMessages().getVal(ABILITY));
        }
        secondCol_ += side_;
        h_ = StringList.concat(getMessages().getVal(LEVEL),SPACE);
        getHeader().addString(h_, secondCol_);
        h_ = StringList.concat(getMessages().getVal(GENDER),SPACE);
        getHeader().addString(h_, secondCol_, Paginator.HEIGTH_CHARS);
        int thirdCol_ = getHeader().width(getMessages().getVal(LEVEL));
        if (thirdCol_ < getHeader().width(getMessages().getVal(GENDER))) {
            thirdCol_ = getHeader().width(getMessages().getVal(GENDER));
        }
        h_ = getMessages().getVal(ITEM);
        getHeader().addString(h_, secondCol_+thirdCol_);
        int w_ = getHeader().width(getMessages().getVal(ITEM));
        getHeader().setPreferredSize(new Dimension(w_+secondCol_+thirdCol_, HEIGTH_CHARS+HEIGTH_CHARS));
        results.add(getHeader());
        add(new ScrollPane(results));
        Panel bottom_ = new Panel();
        getNbResults().setValue(getFacade().getNbResultsPerPageFirstBox());
        getNbResults().addChangeListener(new ChangedNbResultsEvent(this));
        bottom_.add(getNbResults());
        getPages().setListener(new ChangedPageEvent(this));
        getDelta().getDocument().addDocumentListener(new ChangedDeltaPageEvent(this));
        bottom_.add(getBegin());
        bottom_.add(getPreviousDelta());
        bottom_.add(getPrevious());
        bottom_.add(getPages());
        bottom_.add(getNext());
        bottom_.add(getNextDelta());
        bottom_.add(getEnd());
        bottom_.add(getDelta());
        add(bottom_);
        changeNav();
    }

    @Override
    public void changeNbResults() {
        int int_ = (Integer)getNbResults().getValue();
        if (int_ <= 0) {
            return;
        }
        getFacade().setNbResultsPerPageFirstBox(int_);
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
        getFacade().changePageFirstBox(getPages().getSelectedIndex());
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void changeDeltaPage() {
        try {
            int nbDelta_ = Integer.parseInt(getDelta().getText());
            if (nbDelta_ <= 0) {
                return;
            }
            getFacade().setDeltaFirstBox(nbDelta_);
//        }catch(Exception _e){
        }catch(NumberFormatException _0){
            getFacade().setDeltaFirstBox(1);
        }
    }

    public void refreshLang() {
        initMessages(ACCESS_POKEMON);
        gender.refresh(getFacade().getTranslatedGendersCurLanguage());
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
        getFacade().searchPokemonFirstBox();
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
        getFacade().newSearchPokemonFirstBox();
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
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
        getFacade().setMinLevelFirstBox(convertShortNumberField(minLevel.getText()));
        getFacade().setMaxLevelFirstBox(convertShortNumberField(maxLevel.getText()));
        getFacade().setGenderFirstBox(gender.getCurrent());
        getFacade().setMinNbPossEvolsFirstBox(convertShortNumberField(minPossEvos.getText()));
        getFacade().setMaxNbPossEvolsFirstBox(convertShortNumberField(maxPossEvos.getText()));
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
        check(_line, getFacade().getLineFirstBox());
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
    private void refreshResults() {
        results.removeAll();
        getHeader().clearStrings();
        getResultsLabels().clear();
        //results.add(header);
        CustList<SortingPokemonPlayer> rendered_ = getFacade().getRenderedFirstBox();
        CustList<PokemonLabel> list_ = new CustList<PokemonLabel>();
        //Header header_ = new Header();
        int nb_ = rendered_.size();
        for (int i = CustList.FIRST_INDEX; i < nb_; i++) {
            PokemonLabel l_ = new PokemonLabel(rendered_.get(i));
            l_.setImagesResults(getFacade());
            l_.refresh(getFacade().getTranslatedGendersCurLanguage());
            l_.addMouseListener(new PaginatorEvent(this,i));
            //l_.setPreferredSize(new Dimension(100,10));
            list_.add(l_);
        }
        String h_ = getMessages().getVal(NAME);
        int side_ = getFacade().getMap().getSideLength();
//        h_ += getMessages().getVal(STEPS)+SPACE;
//        h_ += getMessages().getVal(REMAIN_STEPS);
        //getHeader().addString(h_, FIRST_PIXEL);
        getHeader().addString(StringList.concat(h_,SPACE), side_);
        h_ = getMessages().getVal(ABILITY);
        getHeader().addString(StringList.concat(h_,SPACE), side_, Paginator.HEIGTH_CHARS);
        //int maxPixName_ = header_.getFontMetrics(header_.getFont()).stringWidth(getMessages().getVal(NAME));
        int maxPixName_ = getHeader().width(StringList.concat(getMessages().getVal(NAME),SPACE));
        for (PokemonLabel l: list_) {
            int value_ = l.getFontMetrics(l.getFont()).stringWidth(StringList.concat(l.getPokemon().getName(),SPACE));
            if (value_ > maxPixName_) {
                maxPixName_ = value_;
            }
        }
        //setNameCoord
        //header_.addString(getMessages().getVal(NAME), 0);
//        int maxPixAbility_ = header_.getFontMetrics(header_.getFont()).stringWidth(getMessages().getVal(ABILITY));
        int maxPixAbility_ = getHeader().width(StringList.concat(getMessages().getVal(ABILITY),SPACE));
        for (PokemonLabel l: list_) {
            int value_ = l.getFontMetrics(l.getFont()).stringWidth(StringList.concat(l.getPokemon().getAbility(),SPACE));
            if (value_ > maxPixAbility_) {
                maxPixAbility_ = value_;
            }
        }
        int thirdColumn_ = CustList.SIZE_EMPTY;
        for (PokemonLabel l: list_) {
            int value_ = l.getThirdColumnWidth();
            if (value_ > thirdColumn_) {
                thirdColumn_ = value_;
            }
        }
        int secondCol_ = Math.max(maxPixAbility_, maxPixName_);
        h_ = getMessages().getVal(LEVEL);
        getHeader().addString(StringList.concat(h_,SPACE), secondCol_ + side_);
        h_ = getMessages().getVal(GENDER);
        getHeader().addString(StringList.concat(h_,SPACE), secondCol_ + side_, Paginator.HEIGTH_CHARS);
        int thirdCol_ = getHeader().width(StringList.concat(getMessages().getVal(LEVEL),SPACE));
        if (thirdCol_ < getHeader().width(StringList.concat(getMessages().getVal(GENDER),SPACE))) {
            thirdCol_ = getHeader().width(StringList.concat(getMessages().getVal(GENDER),SPACE));
        }
        h_ = getMessages().getVal(ITEM);
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
        results.add(getHeader());
        for (PokemonLabel l: list_) {
            l.repaint();
            //l.setGenderCoord(maxPixName_ + maxPixAbility_ + maxPixItem_ + maxPixItem_);
            //l.setPreferredSize(new Dimension(maxPixName_ + maxPixAbility_ + maxPixItem_ + maxPixLevel_ + maxPixGender_,10));
            results.add(l);
            getResultsLabels().add(l);
        }
    }
    private void changePages() {
        setAdding(true);
        getPages().removeAllItems();
        int nbPages_ = getFacade().pagesPk();
        for (int i = CustList.FIRST_INDEX; i < nbPages_; i++) {
            getPages().addItem(i);
        }
        getEnd().setTextAndSize(Integer.toString(nbPages_ - 1));
        setAdding(false);
    }
    private void changeNav() {
        changeNav(getFacade().enabledPreviousFirstBox(), getFacade().enabledNextFirstBox(), getFacade().pagesPk(), getFacade().getNumberPageFirstBox());
//        previous.setEnabled(getFacade().enabledPreviousFirstBox());
//        next.setEnabled(getFacade().enabledNextFirstBox());
//        previousDelta.setEnabled(getFacade().pagesPk() > CustList.FIRST_INDEX);
//        nextDelta.setEnabled(getFacade().pagesPk() > CustList.FIRST_INDEX);
//        begin.setEnabled(getFacade().pagesPk() > CustList.FIRST_INDEX);
//        end.setEnabled(getFacade().pagesPk() > CustList.FIRST_INDEX);
//        adding = true;
//        try {
//            pages.setSelectedIndex(getFacade().getNumberPageFirstBox());
//        } catch (Exception e_) {
//            pages.setSelectedIndex(CustList.INDEX_NOT_FOUND_ELT);
//        }
//        adding = false;
    }

    @Override
    public void begin() {
        getFacade().beginFirstBox();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void previousDelta() {
        getFacade().previousDeltaFirstBox();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void previous() {
        getFacade().previousFirstBox();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void next() {
        getFacade().nextFirstBox();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void nextDelta() {
        getFacade().nextDeltaFirstBox();
        changeNav();
        refreshResults();
        getWindow().pack();
    }

    @Override
    public void end() {
        getFacade().endFirstBox();
        changeNav();
        refreshResults();
        getWindow().pack();
    }
}
