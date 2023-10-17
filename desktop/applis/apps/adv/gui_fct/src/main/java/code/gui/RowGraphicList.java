package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

public final class RowGraphicList<T> {
    private T info;
    private RowGraphicList<T> previous;
    private RowGraphicList<T> next;
    private final AbsPreparedLabel label;
    private boolean selected;
    private boolean focused;
    private boolean anchored;
    private boolean dirty;

    public RowGraphicList(AbsPreparedLabel _label,T _i) {
        label = _label;
        info = _i;
    }

    public void refresh(AbstractImageFactory _imgFact, AbstractImage _img) {
        label.setIcon(_imgFact, _img);
        dirty = false;
    }

    public boolean select(boolean _s) {
        if (selected != _s) {
            dirty = true;
            selected = _s;
            return true;
        }
        return false;
    }

    public boolean focus(boolean _s) {
        if (focused != _s) {
            dirty = true;
            focused = _s;
            return true;
        }
        return false;
    }

    public boolean anchor(boolean _s) {
        if (anchored != _s) {
            dirty = true;
            anchored = _s;
            return true;
        }
        return false;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isFocused() {
        return focused;
    }

    public boolean isAnchored() {
        return anchored;
    }

    public boolean isDirty() {
        return dirty;
    }

    public AbsPreparedLabel getLabel() {
        return label;
    }

    public RowGraphicList<T> getNext() {
        return next;
    }

    public void setNext(RowGraphicList<T> _n) {
        this.next = _n;
    }

    public RowGraphicList<T> getPrevious() {
        return previous;
    }

    public void setPrevious(RowGraphicList<T> _p) {
        this.previous = _p;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T _i) {
        this.info = _i;
    }
}
