package code.gui;

import code.gui.images.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public abstract class GeneComponentModelEltCommon<T> {
    private final AbstractProgramInfos compoFactory;
    private DefScrollCustomGraphicList<T> select;
    private final CustCellRenderGeneStrImpl<T> render;
    private final CustList<T> elements;
    protected GeneComponentModelEltCommon(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<T> _rend, CustList<T> _elts) {
        this.compoFactory = _c;
        render = _rend;
        elements = _elts;
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
        for (T e: elements) {
            select.add(e);
        }
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

    public AbstractProgramInfos getCompoFactory() {
        return compoFactory;
    }

    public CustList<T> getElements() {
        return elements;
    }
}
