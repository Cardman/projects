package code.gui;

import code.gui.initialize.*;
import code.util.*;

public abstract class GeneComponentModelLs<T> extends GeneComponentModelEltCommon<T> implements GeneComponentModel<CustList<T>> {

    protected GeneComponentModelLs(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<T> _rend, CustList<T> _elts) {
        super(_c, _rend, _elts);
    }

    @Override
    public AbsCustComponent gene() {
        setSelect(new DefScrollCustomGraphicList<T>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(), getRender(), false));
        computeWidth(getRender());
        feed();
        return getSelect().getScrollPane();
    }

    @Override
    public AbsCustComponent gene(CustList<T> _d) {
        setSelect(new DefScrollCustomGraphicList<T>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(), getRender(), false));
        computeWidth(getRender());
        feed();
        setupValue(getSelect(),_d);
        getSelect().events();
        getSelect().revalidate();
        return getSelect().getScrollPane();
    }

    protected abstract void setupValue(DefScrollCustomGraphicList<T> _t, CustList<T> _v);

    @Override
    public CustList<T> value() {
        Ints sel_ = getSelect().getSelectedIndexes();
        return tryRet(sel_);
    }

    @Override
    public CustList<T> value(CustList<T> _v) {
        Ints sel_ = getSelect().getSelectedIndexes();
        setupValue(getSelect(),_v);
        return tryRet(sel_);
    }

    private CustList<T> tryRet(Ints _sel) {
        CustList<T> elts_ = new CustList<T>();
        for (int i:_sel) {
            elts_.add(getElements().get(i));
        }
        return elts_;
    }

}
