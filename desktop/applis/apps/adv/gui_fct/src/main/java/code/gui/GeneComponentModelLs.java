package code.gui;

import code.gui.images.MetaFont;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class GeneComponentModelLs<T> extends GeneComponentModelEltCommon<T> {
    private DefScrollCustomGraphicList<T> select;
    private final CustCellRenderGeneStrImpl<T> render;

    protected GeneComponentModelLs(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<T> _rend, CustList<T> _elts) {
        super(_c, _elts);
        render = _rend;
    }

    protected AbsCustComponent buildLs() {
        setSelect(new DefScrollCustomGraphicList<T>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(), getRender(), false));
        computeWidth(getRender());
        feed();
        return getSelect().getScrollPane();
    }

    protected void computeWidth(CustCellRenderGeneStrImpl<T> _r) {
        int w_ = 0;
        MetaFont metaFont_ = getSelect().getElements().getMetaFont();
        for (T e: getElements()) {
            w_ = NumberUtil.max(getCompoFactory().getCompoFactory().stringWidth(metaFont_, StringUtil.nullToEmpty(_r.getMessages().getVal(e))),w_);
        }
        _r.setWidth(w_+2);
    }

    protected void feed() {
        for (T e: getElements()) {
            select.add(e);
        }
        select.events();
        select.revalidate();
    }
    public AbsCustComponent gene() {
        return buildLs();
    }

    public AbsCustComponent geneCommon(CustList<T> _d) {
        AbsCustComponent a_ = buildLs();
        setupValue(_d);
        return a_;
    }

    public void setupValue(CustList<T> _d) {
        setupValue(getSelect(), _d);
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
        _t.events();
        _t.revalidate();
    }
    protected abstract int indexOf(T _t);

    public CustList<T> tryRet() {
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

    public CustCellRenderGeneStrImpl<T> getRender() {
        return render;
    }
    public DefScrollCustomGraphicList<T> getSelect() {
        return select;
    }

    public void setSelect(DefScrollCustomGraphicList<T> _s) {
        this.select = _s;
    }

}
