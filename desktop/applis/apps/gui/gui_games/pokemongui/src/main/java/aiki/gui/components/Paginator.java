package aiki.gui.components;

import aiki.sml.DocumentReaderAikiCoreUtil;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.components.labels.Header;
import aiki.gui.components.labels.SelectableLabel;
import aiki.gui.components.listeners.BeginEvent;
import aiki.gui.components.listeners.EndEvent;
import aiki.gui.components.listeners.NextDeltaEvent;
import aiki.gui.components.listeners.NextEvent;
import aiki.gui.components.listeners.PreviousDeltaEvent;
import aiki.gui.components.listeners.PreviousEvent;
import code.gui.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.sml.stream.ExtractFromFiles;
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
    private static final String ACCESS_SEARCH = "util.pagination.searchingmode";

    private static final String CST_BEGIN = "0";
    private static final String CST_PREVIOUS_DELTA = "<<";
    private static final String CST_PREVIOUS = "<";
    private static final String CST_NEXT = ">";
    private static final String CST_NEXT_DELTA = ">>";

    private ChangeableTitle window;

    private final Panel container;

    private FacadeGame facade;

    private boolean adding;

    private StringMap<String> messages = new StringMap<String>();

    private final EnumMap<SearchingMode,String> messagesSearchMode = new EnumMap<SearchingMode,String>();

    private final Header header;

    private final TextField delta = new TextField(4);

    private final Spinner nbResults = new Spinner(0,Integer.MIN_VALUE,Integer.MAX_VALUE,1);

    private final NumComboBox pages;

    private final CustList<SelectableLabel> resultsLabels = new CustList<SelectableLabel>();

    private final LabelButton begin;

    private final LabelButton previousDelta;

    private final LabelButton previous;

    private final LabelButton next;

    private final LabelButton nextDelta;

    private final LabelButton end;

    private final MainWindow main;

    public Paginator(MainWindow _window, String _access, Panel _dest) {
        main = _window;
        pages = new NumComboBox(_window.getFrames().getGeneComboBox());
        container = _dest;
        initMessages(_access);
        header = new Header();
        begin = new LabelButton(CST_BEGIN);
        begin.addMouseListener(new BeginEvent(this));
        previousDelta = new LabelButton(CST_PREVIOUS_DELTA);
        previousDelta.addMouseListener(new PreviousDeltaEvent(this));
        previous = new LabelButton(CST_PREVIOUS);
        previous.addMouseListener(new PreviousEvent(this));
        next = new LabelButton(CST_NEXT);
        next.addMouseListener(new NextEvent(this));
        nextDelta = new LabelButton(CST_NEXT_DELTA);
        nextDelta.addMouseListener(new NextDeltaEvent(this));
        end = new LabelButton(messages.getVal(CST_END));
        end.addMouseListener(new EndEvent(this));
    }

    public MainWindow getMain() {
        return main;
    }

    protected void initMessages(String _access) {
        String lg_ = main.getLanguageKey();
        messages = MainWindow.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, lg_, ACCESS);
        messages.putAllMap(MainWindow.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, lg_, _access));
        StringMap<String> map_ = MainWindow.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, lg_, ACCESS_SEARCH);
        messagesSearchMode.clear();
        for (String k: map_.getKeys()) {
            messagesSearchMode.put(DocumentReaderAikiCoreUtil.getSearchingModeByName(k), map_.getVal(k));
        }
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
            s.repaintLabel();
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

    protected void changeNav(boolean _enabledPrevious, boolean _enabledNext, int _nbPages, int _noPage) {
        previous.setEnabledLabel(_enabledPrevious);
        next.setEnabledLabel(_enabledNext);
        previousDelta.setEnabledLabel(_nbPages > IndexConstants.FIRST_INDEX);
        nextDelta.setEnabledLabel(_nbPages > IndexConstants.FIRST_INDEX);
        begin.setEnabledLabel(_nbPages > IndexConstants.FIRST_INDEX);
        end.setEnabledLabel(_nbPages > IndexConstants.FIRST_INDEX);
        adding = true;
        pages.selectItem(_noPage);
        adding = false;
    }

    protected ChangeableTitle getWindow() {
        return window;
    }

    protected void setWindow(ChangeableTitle _window) {
        window = _window;
    }

    protected FacadeGame getFacade() {
        return facade;
    }

    protected void setFacade(FacadeGame _facade) {
        facade = _facade;
    }

    protected boolean isAdding() {
        return adding;
    }

    protected void setAdding(boolean _adding) {
        adding = _adding;
    }

    protected StringMap<String> getMessages() {
        return messages;
    }

    protected EnumMap<SearchingMode,String> getMessagesSearchMode() {
        return messagesSearchMode;
    }

    protected Header getHeader() {
        return header;
    }

    protected TextField getDelta() {
        return delta;
    }

    protected Spinner getNbResults() {
        return nbResults;
    }

    protected NumComboBox getPages() {
        return pages;
    }

    protected LabelButton getBegin() {
        return begin;
    }

    protected LabelButton getPreviousDelta() {
        return previousDelta;
    }

    protected LabelButton getPrevious() {
        return previous;
    }

    protected LabelButton getNext() {
        return next;
    }

    protected LabelButton getNextDelta() {
        return nextDelta;
    }

    protected LabelButton getEnd() {
        return end;
    }

    protected CustList<SelectableLabel> getResultsLabels() {
        return resultsLabels;
    }

    public Panel getContainer() {
        return container;
    }
}
