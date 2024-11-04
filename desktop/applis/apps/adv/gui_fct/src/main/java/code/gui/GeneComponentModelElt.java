package code.gui;

import code.gui.initialize.*;
import code.util.*;

public abstract class GeneComponentModelElt<T> extends GeneComponentModelEltCommon<T> implements GeneComponentModel<T>,GeneComponentModelStr {
    private final AbsMap<T, String> messages;
    private AbsStringScrollCustomCombo<T> select;
    protected GeneComponentModelElt(AbstractProgramInfos _c, AbsMap<T,String> _elts, DefCustCellRenderGeneImpl<T> _rend) {
        super(_c, _elts);
        messages = _rend.getMessages();
    }

    @Override
    public AbsCustComponent gene() {
        select = buildSelect();
        feed();
        return getSelect().getElements();
    }

    public AbsPanel geneEnum(T _d) {
        select = buildSelect();
        feed();
        setupValue(_d);
        return getSelect().getElements();
    }

    public void setupValue(T _d) {
        setupValue(getSelect(), _d);
    }

    protected void feed() {
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
        T selected_ = tryRet(defValue());
        getElements().reset();
        select.clear();
        for (T e: getElements().getKeys()) {
            select.add(e);
        }
        setupValue(selected_);
    }

    protected AbsStringScrollCustomCombo<T> buildSelect() {
        return new EnumScrollCustomCombo<T>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(), messages);
    }
    protected void setupValue(AbsStringScrollCustomCombo<T> _t, T _v) {
        _t.select(getElements().indexOfEntry(_v));
        _t.repaint();
    }

    @Override
    public T value() {
        return tryRet(defValue());
    }

    public T tryRet(T _d) {
        int sel_ = getSelect().getSelectedIndex();
        return tryRet(sel_, _d);
    }

    @Override
    public T value(T _v) {
        int sel_ = getSelect().getSelectedIndex();
        setupValue(getSelect(),_v);
        return tryRet(sel_, defValue());
    }

    public AbsStringScrollCustomCombo<T> getSelect() {
        return select;
    }

    private T tryRet(int _sel, T _d) {
        CustList<T> ls_ = select.getList().getList();
        if (!ls_.isValidIndex(_sel)) {
            return _d;
        }
        return ls_.get(_sel);
    }

    protected abstract T defValue();

}
