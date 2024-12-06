package code.mock;

import code.gui.AbsTxtComponent;
import code.gui.events.AbsAutoCompleteListener;
import code.gui.events.AbsCaretListener;
import code.gui.images.MetaPoint;
import code.gui.images.MetaRect;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;
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
    private boolean editable = true;
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

    @Override
    public int insert(String _s, int _i) {
        builder.insert(_i,_s);
        applyInsert(_s, _i);
        return 1;
    }

    @Override
    public int remove(int _off, int _len) {
        builder.delete(_off, _off+_len);
        applyRemove(_len,_off);
        return 1;
    }

    public void replaceRange(String _s, int _start, int _end) {
        builder.replace(_start,_end,_s);
    }

    @Override
    public int enterAction() {
        replaceSelection("\n");
        return 0;
    }

    @Override
    public int downAction() {
        MetaRect r_ = modelToView(getCaretPosition());
        int next_ = viewToModel(new MetaPoint(r_.getPoint().getXcoord(), r_.getPoint().getYcoord() + heightFont()));
        select(next_,next_);
        return 0;
    }

    @Override
    public int upAction() {
        MetaRect r_ = modelToView(getCaretPosition());
        int next_ = viewToModel(new MetaPoint(r_.getPoint().getXcoord(), r_.getPoint().getYcoord() - heightFont()));
        select(next_,next_);
        return 0;
    }

    @Override
    public int leftAction() {
        int next_ = getCaretPosition() - 1;
        select(next_,next_);
        return 0;
    }

    @Override
    public int rightAction() {
        int next_ = getCaretPosition() + 1;
        select(next_,next_);
        return 0;
    }

    public void replaceSelection(String _s) {
        if (this instanceof MockTextPane && !isEditable()) {
            return;
        }
        builder.delete(getSelectionStart(),getSelectionEnd());
        applyRemove(getSelectionEnd()-getSelectionStart(), getSelectionStart());
        selectionEnd = getSelectionStart();
        builder.insert(getSelectionStart(),_s);
        applyInsert(_s, getSelectionStart());
        selected = "";
        selectionStart += _s.length();
        selectionEnd += _s.length();
    }

    public void append(String _s) {
        builder.append(_s);
    }

    public void setText(String _s) {
        int old_ = builder.length();
        builder.delete(0, old_);
        applyRemove(old_, 0);
        builder.append(_s);
        applyInsert(_s, 0);
    }

    protected void applyChange(int _s) {
        for (AbsAutoCompleteListener a: apply(_s)) {
            a.changedUpdate();
        }
    }

    private void applyInsert(String _s, int _off) {
        for (AbsAutoCompleteListener a: apply(_s.length())) {
            a.insertUpdate(_off, _s.length());
        }
    }

    private void applyRemove(int _old, int _off) {
        for (AbsAutoCompleteListener a: apply(_old)) {
            a.removeUpdate(_off, _old);
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
        select(_i,getSelectionEnd());
    }

    public void setSelectionEnd(int _i) {
        select(getSelectionStart(),_i);
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
        int y_ = _m.getYcoord();
        if (y_ < 0) {
            return -1;
        }
        int cy_ = y_ / NumberUtil.max(1,heightFont());
        int x_ = _m.getXcoord();
        if (x_ < 0) {
            return -1;
        }
        int cx_ = x_ / NumberUtil.max(1,heightFont());
        int index_ = 0;
        int row_ = 0;
        int scan_ = 0;
        while (index_ >= 0) {
            int next_ = builder.indexOf("\n",index_);
            if (row_ == cy_) {
                int limit_ = min(next_);
                if (cx_ <= limit_ - index_) {
                    return scan_ + cx_;
                }
                return -1;
            }
            if (next_ < 0) {
                index_=-1;
            } else {
                scan_ += next_ + 1 - index_;
                index_ = next_ + 1;
                row_++;
            }
        }
        return -1;
    }
    private int min(int _next) {
        if (_next < 0) {
            return builder.length()-1;
        }
        return _next;
    }

    @Override
    public MetaRect modelToView(int _index) {
        int f_ = heightFont();
        Ints imp_ = lineFeeds();
        int len_ = imp_.size();
        int row_ = 0;
        while (row_ < len_) {
            if (_index <= imp_.get(row_)) {
                break;
            }
            row_++;
        }
        if (!imp_.isValidIndex(row_-1)) {
            return new MetaRect(0,0,0,0);
        }
        int begin_ = imp_.get(row_ - 1)+1;
        if (_index >= builder.length()) {
            return new MetaRect(0,0,0,0);
        }
        return new MetaRect(f_*(_index - begin_),f_*(row_-1),f_, f_);
    }
    private Ints lineFeeds() {
        Ints lf_ = new Ints();
        lf_.add(-1);
        int len_ = builder.length();
        int i_ = 0;
        while (i_ < len_) {
            char ch_ = builder.charAt(i_);
            if (ch_ == '\n') {
                lf_.add(i_);
            }
            i_++;
        }
        return lf_;
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

    @Override
    public boolean isEditable() {
        return editable;
    }

    @Override
    public void setEditable(boolean _ed) {
        this.editable = _ed;
        visibleCaret();
    }

    @Override
    public void visibleCaret() {
        setEnabled(isEnabled());
    }
}
