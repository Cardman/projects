package code.mock;

import code.gui.events.*;
import code.util.*;
import code.util.core.*;

public final class MockCaret {
    private int dot;
    private int mark;
    private final CustList<AbsCaretListener> listeners;
    private final MockTxtComponent textComponent;

    public MockCaret(CustList<AbsCaretListener> _l, MockTxtComponent _t) {
        this.listeners = _l;
        this.textComponent = _t;
    }

    void updateInsert(String _s, int _i) {
        int length_ = _s.length();
        int newDot_ = dot;
        boolean changed_ = false;
        if (newDot_ >= _i) {
            newDot_ += length_;
            changed_ = true;
        }
        int newMark_ = mark;
        if (newMark_ >= _i) {
            newMark_ += length_;
            changed_ = true;
        }

        if (changed_) {
            if (newMark_ == newDot_) {
                setDot(newDot_);
            } else {
                setDot(newMark_);
                moveDot(newDot_);
            }
        }
    }

    void updateRemove(int _len, int _i) {
        int newDot_ = dot;
        if (newDot_ >= _i + _len) {
            newDot_ -= _len;
        } else {
            newDot_ = NumberUtil.min(_i,newDot_);
        }
        int newMark_ = mark;
        if (newMark_ >= _i + _len) {
            newMark_ -= _len;
        } else {
            newMark_ = NumberUtil.min(_i,newMark_);
        }

        if (newMark_ == newDot_) {
            setDot(newDot_);
        } else {
            setDot(newMark_);
            moveDot(newDot_);
        }
    }
    void changeCaretPosition(int _d) {


        // notify listeners at the caret moved
        this.dot = _d;
        caretUpdate();

    }

    public void moveDot(int _d) {

        if (!textComponent.isEnabled()) {
            // don't allow selection on disabled components.
            setDot(_d);
            return;
        }
        if (_d != this.dot) {
            handleMoveDot(_d);
        }
    }

    void handleMoveDot(int _d) {
        changeCaretPosition(_d);
    }
    public void setDot(int _d) {

        handleSetDot(_d);
    }

    void handleSetDot(int _d) {
        // move dot, if it changed
        int dot_ = _d;
        dot_ = Math.min(dot_, textComponent.getBuilder().length());
        dot_ = Math.max(dot_, 0);


        mark = dot_;
        if (this.dot != dot_) {
            changeCaretPosition(dot_);
        }
    }

    public int getDot() {
        return dot;
    }

    public int getMark() {
        return mark;
    }

    private void caretUpdate() {
        for (AbsCaretListener a: listeners) {
            a.caretUpdate(dot,mark);
        }
    }
}
