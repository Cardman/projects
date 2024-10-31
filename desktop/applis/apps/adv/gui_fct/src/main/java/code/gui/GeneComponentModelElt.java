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
        for (T e: getElements()) {
            select.add(e);
        }
        select.repaint();
    }
    protected abstract AbsStringScrollCustomCombo<T> buildSelect();

    protected abstract void setupValue(AbsStringScrollCustomCombo<T> _t, T _v);

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
        if (!getElements().isValidIndex(_sel)) {
            return _d;
        }
        return getElements().get(_sel);
    }

    protected abstract T defValue();

}
