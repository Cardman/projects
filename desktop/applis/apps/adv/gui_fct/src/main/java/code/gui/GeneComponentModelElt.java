package code.gui;

import code.gui.initialize.*;
import code.util.*;

public final class GeneComponentModelElt<T> extends GeneComponentModelEltCommon<T> implements GeneComponentModelStr {
    private final AbsMap<T, String> messages;
    private AbsStringScrollCustomCombo<T> select;
    private final AbsDefValue<T> defValue;

    public GeneComponentModelElt(AbstractProgramInfos _c, AbsMap<T, String> _messages) {
        this(_c, _messages, new DefCustCellRenderGeneImpl<T>(_c.getCompoFactory(), _c.getImageFactory(), _messages), new NullDefValue<T>());
    }
    public GeneComponentModelElt(AbstractProgramInfos _c, AbsMap<T, String> _messages, AbsDefValue<T> _abs) {
        this(_c, _messages, new DefCustCellRenderGeneImpl<T>(_c.getCompoFactory(), _c.getImageFactory(), _messages), _abs);
    }
    public GeneComponentModelElt(AbstractProgramInfos _c, AbsMap<T,String> _elts, DefCustCellRenderGeneImpl<T> _rend, AbsDefValue<T> _abs) {
        super(_c, _elts);
        messages = _rend.getMessages();
        defValue = _abs;
    }

    public AbsPanel geneEnum() {
        select = buildSelect();
        feed();
        setupValue(defValue.defValue());
        return getSelect().getElements();
    }

    public void setupValue(T _d) {
        setupValue(getSelect(), _d);
    }

    public void feed() {
        for (T e: getElements().getKeys()) {
            select.add(e);
        }
        select.repaint();
    }

    @Override
    public void reset() {
        if (select == null) {
            return;
        }
        T selected_ = tryRet();
        updateVal(selected_);
    }

    public void updateVal(T _selected) {
        getElements().reset();
        select.clear();
        for (T e: getElements().getKeys()) {
            select.add(e);
        }
        setupValue(_selected);
    }

    public AbsStringScrollCustomCombo<T> buildSelect() {
        return new EnumScrollCustomCombo<T>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(), messages);
    }
    public void setupValue(AbsStringScrollCustomCombo<T> _t, T _v) {
        int index_ = getElements().indexOfEntry(_v);
        if (index_ < 0) {
            return;
        }
        _t.select(index_);
        _t.repaint();
    }

    public T value() {
        return valueElt();
    }

    public T valueElt() {
        return tryRet();
    }

    public T tryRet() {
        int sel_ = getSelect().getSelectedIndex();
        return tryRet(sel_);
    }

    public void value(T _v) {
        valueElt(_v);
    }

    public void valueElt(T _v) {
        int sel_ = getSelect().getSelectedIndex();
        setupValue(getSelect(), _v);
        tryRet(sel_);
    }

    public AbsStringScrollCustomCombo<T> getSelect() {
        return select;
    }

    private T tryRet(int _sel) {
        CustList<T> ls_ = select.getList().getList();
        if (!ls_.isValidIndex(_sel)) {
            return defValue.defValue();
        }
        return ls_.get(_sel);
    }

}
