package aiki.gui.components;
import java.awt.Window;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import code.gui.LabelButton;
import code.gui.NumComboBox;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.exceptions.FormatException;
import code.stream.ExtractFromFiles;
import code.util.CustList;
import code.util.EnumMap;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.pagination.SearchingMode;
import aiki.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.components.labels.Header;
import aiki.gui.components.labels.SelectableLabel;
import aiki.gui.components.listeners.BeginEvent;
import aiki.gui.components.listeners.EndEvent;
import aiki.gui.components.listeners.NextDeltaEvent;
import aiki.gui.components.listeners.NextEvent;
import aiki.gui.components.listeners.PreviousDeltaEvent;
import aiki.gui.components.listeners.PreviousEvent;

public abstract class Paginator extends JPanel{

    public static final int HEIGTH_CHARS = 10;

    public static final String SPACE = " ";

    public static final String TAB = "\t";

    protected static final int FIRST_PIXEL = 0;

    protected static final String SEARCH = "search";

    protected static final String NEW_SEARCH = "newsearch";

    protected static final String END = "end";

    protected static final String POKEMON = "Pokemon";

    protected static final String SPACES = SPACE + SPACE;
    protected static final String ACCESS_EGG = "aiki.gui.components.PaginatorEgg";
    protected static final String ACCESS_HEALING_ITEM = "aiki.gui.components.PaginatorHealingItem";
    protected static final String ACCESS_ITEM = "aiki.gui.components.PaginatorItem";
    protected static final String ACCESS_MOVE = "aiki.gui.components.PaginatorMove";
    protected static final String ACCESS_POKEMON = "aiki.gui.components.PaginatorPokemon";
    private static final String ACCESS = "aiki.gui.components.Paginator";
    private static final String ACCESS_SEARCH = "util.pagination.SearchingMode";

    private static final String BEGIN = "0";
    private static final String PREVIOUS_DELTA = "<<";
    private static final String PREVIOUS = "<";
    private static final String NEXT = ">";
    private static final String NEXT_DELTA = ">>";

    private Window window;

    private FacadeGame facade;

    private boolean adding;

    private StringMap<String> messages = new StringMap<String>();

    private EnumMap<SearchingMode,String> messagesSearchMode = new EnumMap<SearchingMode,String>();

    private Header header;

    private JTextField delta = new JTextField(4);

    private JSpinner nbResults = new JSpinner();

    private NumComboBox pages = new NumComboBox();

    private CustList<SelectableLabel> resultsLabels = new CustList<SelectableLabel>();

    private LabelButton begin;

    private LabelButton previousDelta;

    private LabelButton previous;

    private LabelButton next;

    private LabelButton nextDelta;

    private LabelButton end;

    public Paginator(String _access) {
        initMessages(_access);
        header = new Header();
        begin = new LabelButton(BEGIN);
        begin.addMouseListener(new BeginEvent(this));
        previousDelta = new LabelButton(PREVIOUS_DELTA);
        previousDelta.addMouseListener(new PreviousDeltaEvent(this));
        previous = new LabelButton(PREVIOUS);
        previous.addMouseListener(new PreviousEvent(this));
        next = new LabelButton(NEXT);
        next.addMouseListener(new NextEvent(this));
        nextDelta = new LabelButton(NEXT_DELTA);
        nextDelta.addMouseListener(new NextDeltaEvent(this));
        end = new LabelButton(messages.getVal(END));
        end.addMouseListener(new EndEvent(this));
    }

    protected void initMessages(String _access) {
        messages = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), ACCESS);
        messages.putAllMap(ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), _access));
        StringMap<String> map_ = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(), ACCESS_SEARCH);
        messagesSearchMode.clear();
        for (String k: map_.getKeys()) {
            messagesSearchMode.put(SearchingMode.valueOf(k), map_.getVal(k));
        }
    }

    protected static String convertStringField(String _text) {
        if (_text.isEmpty()) {
            return null;
        }
        return _text;
    }

    protected static Integer convertNumberField(String _text) {
        try {
            return Integer.parseInt(_text);
        }catch(NumberFormatException _0){
            return null;
        }
    }

    protected static Short convertShortNumberField(String _text) {
        try {
            return Short.parseShort(_text);
        }catch(NumberFormatException _0){
            return null;
        }
    }

    protected static Long convertLongNumberField(String _text) {
        try {
            return Long.parseLong(_text);
        }catch(NumberFormatException _0){
            return null;
        }
    }

    protected static Rate convertRateField(String _text) {
        try {
            return new Rate(_text);
        }catch(FormatException _0){
            return null;
        }
    }

    protected static LgInt convertLgIntField(String _text) {
        try {
            return new LgInt(_text);
        }catch(FormatException _0){
            return null;
        }
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
        if (_currentLine != CustList.INDEX_NOT_FOUND_ELT) {
            SelectableLabel l_ = resultsLabels.get(_index);
            l_.setSelected(true);
        }
        for (SelectableLabel s: resultsLabels) {
            s.repaint();
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
        previousDelta.setEnabledLabel(_nbPages > CustList.FIRST_INDEX);
        nextDelta.setEnabledLabel(_nbPages > CustList.FIRST_INDEX);
        begin.setEnabledLabel(_nbPages > CustList.FIRST_INDEX);
        end.setEnabledLabel(_nbPages > CustList.FIRST_INDEX);
        adding = true;
        try {
            pages.setSelectedIndex(_noPage);
        } catch (RuntimeException _0) {
            pages.setSelectedIndex(CustList.INDEX_NOT_FOUND_ELT);
        }
        adding = false;
    }

    protected Window getWindow() {
        return window;
    }

    protected void setWindow(Window _window) {
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

    protected JTextField getDelta() {
        return delta;
    }

    protected JSpinner getNbResults() {
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
}
