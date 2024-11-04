package code.gui;

import code.gui.images.MetaFont;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class GeneComponentModelLs<T> extends GeneComponentModelEltCommon<T> implements GeneComponentModelStr {
    private DefScrollCustomGraphicList<T> select;
    private final DefCustCellRenderGeneImpl<T> render;

    protected GeneComponentModelLs(AbstractProgramInfos _c, DefCustCellRenderGeneImpl<T> _rend, AbsMap<T,String> _elts) {
        super(_c, _elts);
        render = _rend;
    }

    protected AbsCustComponent buildLs() {
        setSelect(new DefScrollCustomGraphicList<T>(getCompoFactory().getCompoFactory(), getCompoFactory().getImageFactory(), getRender(), false));
        computeWidth(getRender());
        feed();
        return getSelect().getScrollPane();
    }

    protected void computeWidth(DefCustCellRenderGeneImpl<T> _r) {
        int w_ = 0;
        MetaFont metaFont_ = getSelect().getElements().getMetaFont();
        for (T e: getElements().getKeys()) {
            w_ = NumberUtil.max(getCompoFactory().getCompoFactory().stringWidth(metaFont_, StringUtil.nullToEmpty(_r.getMessages().getVal(e))),w_);
        }
        _r.setMaxWidth(w_+2);
    }

    protected void feed() {
        for (T e: getElements().getKeys()) {
            select.add(e);
        }
        select.events();
        select.revalidate();
    }

    @Override
    public void reset() {
        if (select == null) {
            return;
        }
        CustList<T> selected_ = tryRet();
        getElements().reset();
        select.clear();
        for (T e: getElements().getKeys()) {
            select.add(e);
        }
        setupValue(selected_);
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
    protected int indexOf(T _t) {
        return getElements().indexOfEntry(_t);
    }

    public CustList<T> tryRet() {
        Ints sel_ = getSelect().getSelectedIndexes();
        return tryRet(sel_);
    }
    protected CustList<T> tryRet(Ints _sel) {
        CustList<T> elts_ = new CustList<T>();
        CustList<T> ls_ = getSelect().getList();
        for (int i:_sel) {
            elts_.add(ls_.get(i));
        }
        return elts_;
    }

    public DefCustCellRenderGeneImpl<T> getRender() {
        return render;
    }
    public DefScrollCustomGraphicList<T> getSelect() {
        return select;
    }

    public void setSelect(DefScrollCustomGraphicList<T> _s) {
        this.select = _s;
    }

}
