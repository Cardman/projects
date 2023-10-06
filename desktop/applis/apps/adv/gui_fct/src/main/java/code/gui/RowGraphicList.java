package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;

public final class RowGraphicList<T> {
    private T info;
    private RowGraphicList<T> previous;
    private RowGraphicList<T> next;
    private final AbsPreparedLabel label;
    private boolean selected;
    private boolean focused;
    private boolean dirty;
    public RowGraphicList(ScrollCustomGraphicList<T> _list,T _i, int _index, AbsCompoFactory _compo, AbstractImageFactory _imgFact, AbsCustCellRenderGene<T> _g, ColorsGroupList _cs) {
        label = _compo.newPreparedLabel(_imgFact.newImageRgb(1,1));
        label.setFont(_list.getElements().getMetaFont());
        info = _i;
        updated(_index,_imgFact,_g,_i,_cs);
    }
    public void update(int _index, T _info, AbstractImageFactory _imgFact, AbsCustCellRenderGene<T> _g, ColorsGroupList _cs) {
        info = _info;
        updated(_index, _imgFact, _g, _info,_cs);
    }
    public void select(int _index, AbstractImageFactory _imgFact, AbsCustCellRenderGene<T> _g, ColorsGroupList _cs) {
        if (!dirty) {
            return;
        }
        updated(_index, _imgFact, _g, info,_cs);
    }
    public void forceRefresh(int _index, AbstractImageFactory _imgFact, AbsCustCellRenderGene<T> _g, ColorsGroupList _cs) {
        updated(_index, _imgFact, _g, info,_cs);
    }
    private void updated(int _index, AbstractImageFactory _imgFact, AbsCustCellRenderGene<T> _g, T _inf, ColorsGroupList _cs) {
        AbstractImage img_ = _g.getListCellRendererComponent(_index, _inf, selected, focused, label,_cs);
        label.setIcon(_imgFact, img_);
        dirty = false;
    }

    public void select(boolean _s) {
        if (selected != _s) {
            dirty = true;
        }
        selected = _s;
    }

    public void focus(boolean _s) {
        if (focused != _s) {
            dirty = true;
        }
        focused = _s;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isFocused() {
        return focused;
    }

    public AbsCustComponent getLabel() {
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
}
