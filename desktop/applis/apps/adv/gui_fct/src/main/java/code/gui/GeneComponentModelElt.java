package code.gui;

import code.gui.initialize.*;
import code.util.*;

public abstract class GeneComponentModelElt<T> extends GeneComponentModelEltCommon<T> implements GeneComponentModel<T> {
    protected GeneComponentModelElt(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<T> _rend, CustList<T> _elts) {
        super(_c, _rend, _elts);
    }

    @Override
    public AbsCustComponent gene() {
        setSelect(new DefScrollCustomGraphicList<T>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(), getRender(), true));
        computeWidth(getRender());
        feed();
        return getSelect().getScrollPane();
    }

    @Override
    public AbsCustComponent gene(T _d) {
        setSelect(new DefScrollCustomGraphicList<T>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(), getRender(), true));
        computeWidth(getRender());
        feed();
        setupValue(getSelect(),_d);
        getSelect().events();
        getSelect().revalidate();
        return getSelect().getScrollPane();
    }

    protected abstract void setupValue(DefScrollCustomGraphicList<T> _t, T _v);

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

    private T tryRet(int _sel) {
        if (!getElements().isValidIndex(_sel)) {
            return defValue();
        }
        return getElements().get(_sel);
    }

    protected abstract T defValue();

}
