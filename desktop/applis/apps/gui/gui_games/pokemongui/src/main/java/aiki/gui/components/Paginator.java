package aiki.gui.components;

import aiki.gui.components.listeners.*;
import aiki.sml.GamesPk;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.labels.Header;
import aiki.gui.components.labels.SelectableLabel;
import code.gui.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.sml.util.TranslationsLg;
import code.util.*;
import aiki.facade.enums.SearchingMode;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;

public abstract class Paginator {

    public static final int HEIGTH_CHARS = 10;

    public static final String SPACE = " ";

    public static final String TAB = "\t";

    protected static final int FIRST_PIXEL = 0;

    protected static final String SEARCH = "search";

    protected static final String NEW_SEARCH = "newsearch";

    protected static final String CST_END = "end";

    protected static final String POKEMON = "Pokemon";

    protected static final String SPACES = SPACE+SPACE;
    protected static final String ACCESS_EGG = "aiki.gui.components.paginatoregg";
    protected static final String ACCESS_HEALING_ITEM = "aiki.gui.components.paginatorhealingitem";
    protected static final String ACCESS_ITEM = "aiki.gui.components.paginatoritem";
    protected static final String ACCESS_MOVE = "aiki.gui.components.paginatormove";
    protected static final String ACCESS_POKEMON = "aiki.gui.components.paginatorpokemon";
    private static final String ACCESS = "aiki.gui.components.paginator";

    private static final String CST_BEGIN = "0";
    private static final String CST_PREVIOUS_DELTA = "<<";
    private static final String CST_PREVIOUS = "<";
    private static final String CST_NEXT = ">";
    private static final String CST_NEXT_DELTA = ">>";

    private ChangeableTitle window;

    private final AbsPanel container;

    private FacadeGame facade;

//    private boolean adding;

    private StringMap<String> messages = new StringMap<String>();

    private final IdMap<SearchingMode,String> messagesSearchMode = new IdMap<SearchingMode,String>();

    private final Header header;

    private final AbsTextField delta;

    private final AbsSpinner nbResults;

    private final NumComboBox pages;

    private final CustList<SelectableLabel> resultsLabels = new CustList<SelectableLabel>();

    private final AbsButton begin;

    private final AbsButton previousDelta;

    private final AbsButton previous;

    private final AbsButton next;

    private final AbsButton nextDelta;

    private final AbsButton end;

    private final WindowAiki main;
    private AbsButton searchButton;
    private AbsButton newSearchButton;

    protected Paginator(WindowAiki _window, String _access, AbsPanel _dest) {
        main = _window;
        delta =_window.getCompoFactory().newTextField(4);
        nbResults = _window.getCompoFactory().newSpinner(1,1,Integer.MAX_VALUE,1);
        pages = new NumComboBox(_window.getFrames());
        container = _dest;
        initMessages(_access);
        header = new Header(_window.getCompoFactory());
        begin = _window.getCompoFactory().newPlainButton(CST_BEGIN);
        begin.addActionListener(new BeginEvent(this));
        previousDelta = _window.getCompoFactory().newPlainButton(CST_PREVIOUS_DELTA);
        previousDelta.addActionListener(new PreviousDeltaEvent(this));
        previous = _window.getCompoFactory().newPlainButton(CST_PREVIOUS);
        previous.addActionListener(new PreviousEvent(this));
        next = _window.getCompoFactory().newPlainButton(CST_NEXT);
        next.addActionListener(new NextEvent(this));
        nextDelta = _window.getCompoFactory().newPlainButton(CST_NEXT_DELTA);
        nextDelta.addActionListener(new NextDeltaEvent(this));
        end = _window.getCompoFactory().newPlainButton(messages.getVal(CST_END));
        end.addActionListener(new EndEvent(this));
    }
    public void beginBuild(AbsPanel _p) {
        AbsPanel top_;
        top_ = getMain().getCompoFactory().newLineBox();
        searchButton = getMain().getCompoFactory().newPlainButton(getMessages().getVal(SEARCH));
        searchButton.addActionListener(new SearchEvent(this));
        top_.add(searchButton);
        newSearchButton = getMain().getCompoFactory().newPlainButton(getMessages().getVal(NEW_SEARCH));
        newSearchButton.addActionListener(new NewSearchEvent(this));
        top_.add(newSearchButton);
        _p.add(top_);
    }
    public void finishBuild(AbsPanel _p) {
        AbsPanel bottom_ = getMain().getCompoFactory().newLineBox();
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
//
//    public static SearchingMode getSearchingModeByName(String _env) {
//        for (SearchingMode e: SearchingMode.values()) {
//            if (StringUtil.quickEq(e.name(), _env)) {
//                return e;
//            }
//        }
//        return SearchingMode.WHOLE_STRING;
//    }

    public WindowAiki getMain() {
        return main;
    }

    protected void initMessages(String _access) {
        String lg_ = main.getLanguageKey();
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, lg_, ACCESS);
        messages.putAllMap(WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, lg_, _access));
//        StringMap<String> map_ = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, lg_, ACCESS_SEARCH);
        StringMap<String> map_ = file(main.getFrames().currentLg());
        messagesSearchMode.clear();
        for (EntryCust<String, String> k: map_.entryList()) {
            messagesSearchMode.addEntry(SearchingMode.getSearchingModeByName(k.getKey()), k.getValue());
        }
    }

    public static StringMap<String> file(TranslationsLg _lg) {
        return GamesPk.getPaginatorContentTr(GamesPk.getAppliTr(_lg)).getMapping();
    }
    protected static String convertStringField(String _text) {
        if (_text.isEmpty()) {
            return null;
        }
        return _text;
    }

    protected static Long convertLongNumberField(String _text) {
        if (_text.isEmpty()) {
            return null;
        }
        return NumberUtil.parseLongZero(_text);
    }

    protected static Rate convertRateField(String _text) {
        if (!Rate.isValid(_text)) {
            return null;
        }
        return new Rate(_text);
    }

    protected static LgInt convertLgIntField(String _text) {
        if (!LgInt.isValid(_text)) {
            return null;
        }
        return new LgInt(_text);
    }

    public abstract void check(int _index);

    protected void check(int _index, int _currentLine) {
        for (SelectableLabel s: resultsLabels) {
            s.setSelected(false);
        }
//        if (getFacade().getLineEgg() != CustList.INDEX_NOT_FOUND_ELT) {
//            SelectableLabel l_ = resultsLabels.get(_index);
//            l_.setSelected(true);
//        }
        if (_currentLine != IndexConstants.INDEX_NOT_FOUND_ELT) {
            SelectableLabel l_ = resultsLabels.get(_index);
            l_.setSelected(true);
        }
        for (SelectableLabel s: resultsLabels) {
            AbsMetaLabelPk.paintPk(getMain().getImageFactory(), s);
        }
    }

    public abstract void begin();

    public abstract void previousDelta();

    public abstract void previous();

    public abstract void next();

    public abstract void nextDelta();

    public abstract void end();

    public abstract void search();

    public abstract void newSearch();

    public abstract void changeNbResults();

    public abstract void changePageNumber();

    public abstract void changeDeltaPage();
    public void appendResults() {
        refreshResults();
        changePages();
        changeNav();
        getWindow().pack();
    }
    public abstract void refreshResults();
    public abstract void changePages();
    public abstract void changeNav();
    protected void changeNav(boolean _enabledPrevious, boolean _enabledNext, int _nbPages, int _noPage) {
        previous.setEnabled(_enabledPrevious);
        next.setEnabled(_enabledNext);
        previousDelta.setEnabled(_nbPages > IndexConstants.FIRST_INDEX);
        nextDelta.setEnabled(_nbPages > IndexConstants.FIRST_INDEX);
        begin.setEnabled(_nbPages > IndexConstants.FIRST_INDEX);
        end.setEnabled(_nbPages > IndexConstants.FIRST_INDEX);
//        adding = true;
        pages.getCombo().setEnabled(_nbPages > IndexConstants.FIRST_INDEX);
        pages.selectItem(_noPage);
//        adding = false;
    }

    public ChangeableTitle getWindow() {
        return window;
    }

    public void setWindow(ChangeableTitle _window) {
        window = _window;
    }

    protected FacadeGame getFacade() {
        return facade;
    }

    protected void setFacade(FacadeGame _facade) {
        facade = _facade;
    }
//
//    protected boolean isAdding() {
//        return adding;
//    }
//
//    protected void setAdding(boolean _adding) {
//        adding = _adding;
//    }

    protected StringMap<String> getMessages() {
        return messages;
    }

    protected IdMap<SearchingMode,String> getMessagesSearchMode() {
        return messagesSearchMode;
    }

    public Header getHeader() {
        return header;
    }

    public AbsButton getSearchButton() {
        return searchButton;
    }

    public AbsButton getNewSearchButton() {
        return newSearchButton;
    }

    public AbsTextField getDelta() {
        return delta;
    }

    public AbsSpinner getNbResults() {
        return nbResults;
    }

    public NumComboBox getPages() {
        return pages;
    }

    public AbsButton getBegin() {
        return begin;
    }

    public AbsButton getPreviousDelta() {
        return previousDelta;
    }

    public AbsButton getPrevious() {
        return previous;
    }

    public AbsButton getNext() {
        return next;
    }

    public AbsButton getNextDelta() {
        return nextDelta;
    }

    public AbsButton getEnd() {
        return end;
    }

    public CustList<SelectableLabel> getResultsLabels() {
        return resultsLabels;
    }

    public AbsPanel getContainer() {
        return container;
    }
}
