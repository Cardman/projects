package code.mock;

import code.gui.AbsTxtComponent;
import code.gui.events.AbsAutoCompleteListener;
import code.gui.events.AbsCaretListener;
import code.gui.images.MetaPoint;
import code.util.CustList;
import code.util.IdList;
import code.util.core.NumberUtil;

public abstract class MockTxtComponent extends MockInput implements AbsTxtComponent {
    private final CustList<AbsAutoCompleteListener> autoCompleteListeners = new CustList<AbsAutoCompleteListener>();
    private final StringBuilder builder = new StringBuilder();
    private String selected = "";
    private int selectionStart;
    private int selectionEnd;
    private int caretColor;
    private int selectionColor;
    private int selectedTextColor;
    private final IdList<AbsCaretListener> listeners = new IdList<AbsCaretListener>();

    @Override
    public int getCaretPosition() {
        return getSelectionStart();
    }

    @Override
    public int getSelectionStart() {
        return NumberUtil.min(selectionStart,selectionEnd);
    }

    @Override
    public void setCaretPosition(int _position) {
        selectionStart = _position;
        caretUpdate();
    }

    @Override
    public int getSelectionEnd() {
        return NumberUtil.max(selectionStart,selectionEnd);
    }

    @Override
    public void moveCaretPosition(int _pos) {
        selectionEnd = _pos;
        caretUpdate();
    }

    public void insert(String _s, int _i) {
        builder.insert(_i,_s);
    }

    public void replaceRange(String _s, int _start, int _end) {
        builder.replace(_start,_end,_s);
    }

    public void replaceSelection(String _s) {
        builder.replace(selectionStart,selectionEnd,_s);
        selected = "";
    }

    public void append(String _s) {
        builder.append(_s);
    }

    public void setText(String _s) {
        int old_ = builder.length();
        builder.delete(0, old_);
        for (AbsAutoCompleteListener a: apply(old_)) {
            a.removeUpdate();
        }
        builder.append(_s);
        for (AbsAutoCompleteListener a: apply(_s.length())) {
            a.insertUpdate();
        }
    }
    private CustList<AbsAutoCompleteListener> apply(int _len) {
        if (_len > 0) {
            return autoCompleteListeners;
        }
        return new CustList<AbsAutoCompleteListener>();
    }

    public String getText() {
        return builder.toString();
    }

    public String getSelectedText() {
        return selected;
    }

    public void setSelectionStart(int _i) {
        select(_i,selectionEnd);
    }

    public void setSelectionEnd(int _i) {
        select(selectionStart,_i);
    }

    public void select(int _start, int _end) {
        int len_ = builder.length();
        int selectionStart_ = _start;
        int selectionEnd_ = _end;
        if (selectionStart_ < 0) {
            selectionStart_ = 0;
        }
        if (selectionStart_ > len_) {
            selectionStart_ = len_;
        }
        if (selectionEnd_ > len_) {
            selectionEnd_ = len_;
        }
        if (selectionEnd_ < selectionStart_) {
            selectionEnd_ = selectionStart_;
        }
        selected = builder.substring(selectionStart_, selectionEnd_);
        selectionStart = selectionStart_;
        selectionEnd = selectionEnd_;
        caretUpdate();
    }

    public void selectAll() {
        selectionStart = 0;
        selectionEnd = builder.length();
        selected = getText();
        caretUpdate();
    }

    private void caretUpdate() {
        for (AbsCaretListener a: listeners) {
            a.caretUpdate(selectionStart,selectionEnd);
        }
    }
    public int getCaretColor() {
        return caretColor;
    }

    public void setCaretColor(int _c) {
        this.caretColor = _c;
    }

    public int getSelectedTextColor() {
        return selectedTextColor;
    }

    public void setSelectedTextColor(int _c) {
        this.selectedTextColor = _c;
    }

    public int getSelectionColor() {
        return selectionColor;
    }

    public void setSelectionColor(int _c) {
        this.selectionColor = _c;
    }

    @Override
    public int viewToModel(MetaPoint _m) {
        return 0;
    }
    public StringBuilder getBuilder() {
        return builder;
    }

    @Override
    public void addCaretListener(AbsCaretListener _listener) {
        listeners.add(_listener);
    }

    @Override
    public void removeCaretListener(AbsCaretListener _listener) {
        listeners.removeObj(_listener);
    }

    @Override
    public void addAutoComplete(AbsAutoCompleteListener _l) {
        autoCompleteListeners.add(_l);
    }

    public CustList<AbsAutoCompleteListener> getAutoCompleteListeners() {
        return autoCompleteListeners;
    }

    @Override
    public CustList<AbsCaretListener> getCaretListeners() {
        return listeners;
    }
}
