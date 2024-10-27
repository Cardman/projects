package code.gui;

import code.gui.initialize.*;
import code.util.*;

public abstract class GeneComponentModelElt<T> extends GeneComponentModelEltCommon<T> implements GeneComponentModel<T> {
    private AbsStringScrollCustomCombo<T> select;
    protected GeneComponentModelElt(AbstractProgramInfos _c, CustList<T> _elts) {
        super(_c, _elts);
    }

    @Override
    public AbsCustComponent gene() {
        select = buildSelect();
        feed();
        return getSelect().getElements();
    }

    @Override
    public AbsCustComponent gene(T _d) {
        select = buildSelect();
        feed();
        setupValue(getSelect(),_d);
        return getSelect().getElements();
    }

    protected void feed() {
        for (T e: getElements()) {
            select.add(e);
        }
        select.repaint();
    }
    protected abstract AbsStringScrollCustomCombo<T> buildSelect();

    protected abstract void setupValue(AbsStringScrollCustomCombo<T> _t, T _v);

    @Override
    public T value() {
        int sel_ = getSelect().getSelectedIndex();
        return tryRet(sel_);
    }

    @Override
    public T value(T _v) {
        int sel_ = getSelect().getSelectedIndex();
        setupValue(getSelect(),_v);
        return tryRet(sel_);
    }

    public AbsStringScrollCustomCombo<T> getSelect() {
        return select;
    }

    private T tryRet(int _sel) {
        if (!getElements().isValidIndex(_sel)) {
            return defValue();
        }
        return getElements().get(_sel);
    }

    protected abstract T defValue();

}
