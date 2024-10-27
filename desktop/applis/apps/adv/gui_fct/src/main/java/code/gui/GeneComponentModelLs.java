package code.gui;

import code.gui.initialize.*;
import code.util.*;

public abstract class GeneComponentModelLs<T> extends GeneComponentModelEltCommon<T> {

    protected GeneComponentModelLs(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<T> _rend, CustList<T> _elts) {
        super(_c, _rend, _elts);
    }

    public AbsCustComponent gene() {
        return buildLs();
    }

    public AbsCustComponent geneCommon(CustList<T> _d) {
        AbsCustComponent a_ = buildLs();
        setupValue(getSelect(),_d);
        getSelect().events();
        getSelect().revalidate();
        return a_;
    }

    protected void setupValue(DefScrollCustomGraphicList<T> _t, CustList<T> _v) {
        Ints ind_ = new Ints();
        for (T s: _v) {
            int res_ = indexOf(s);
            if (res_ >= 0) {
                ind_.add(res_);
            }
        }
        _t.select(ind_);
    }
    protected abstract int indexOf(T _t);

    protected CustList<T> tryRet() {
        Ints sel_ = getSelect().getSelectedIndexes();
        return tryRet(sel_);
    }
    protected CustList<T> tryRet(Ints _sel) {
        CustList<T> elts_ = new CustList<T>();
        for (int i:_sel) {
            elts_.add(getElements().get(i));
        }
        return elts_;
    }

}
