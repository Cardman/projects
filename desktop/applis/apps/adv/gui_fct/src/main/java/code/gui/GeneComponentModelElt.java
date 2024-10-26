package code.gui;

import code.gui.images.MetaFont;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class GeneComponentModelElt<T> implements GeneComponentModel<T> {
    private final AbstractProgramInfos compoFactory;
    private DefScrollCustomGraphicList<T> selectUniq;
    private final CustCellRenderGeneStrImpl<T> render;
    private final CustList<T> elements;
    protected GeneComponentModelElt(AbstractProgramInfos _c, CustCellRenderGeneStrImpl<T> _rend, CustList<T> _elts) {
        this.compoFactory = _c;
        render = _rend;
        elements = _elts;
    }

    @Override
    public AbsCustComponent gene() {
        selectUniq = new DefScrollCustomGraphicList<T>(compoFactory.getCompoFactory(), compoFactory.getImageFactory(), render,true);
        computeWidth(render);
        feed();
        return selectUniq.getScrollPane();
    }

    @Override
    public AbsCustComponent gene(T _d) {
        selectUniq = new DefScrollCustomGraphicList<T>(compoFactory.getCompoFactory(), compoFactory.getImageFactory(), render,true);
        computeWidth(render);
        feed();
        setupValue(selectUniq,_d);
        selectUniq.events();
        selectUniq.revalidate();
        return selectUniq.getScrollPane();
    }

    private void feed() {
        for (T e: elements) {
            selectUniq.add(e);
        }
    }
    protected abstract void setupValue(DefScrollCustomGraphicList<T> _t, T _v);

    protected void computeWidth(CustCellRenderGeneStrImpl<T> _r) {
        int w_ = 0;
        MetaFont metaFont_ = getSelectUniq().getElements().getMetaFont();
        for (T e: getElements()) {
            w_ = NumberUtil.max(getCompoFactory().getCompoFactory().stringWidth(metaFont_, StringUtil.nullToEmpty(_r.getMessages().getVal(e))),w_);
        }
        _r.setWidth(w_+2);
    }
    @Override
    public T value() {
        int sel_ = selectUniq.getSelectedIndex();
        return tryRet(sel_);
    }

    @Override
    public T value(T _v) {
        int sel_ = selectUniq.getSelectedIndex();
        setupValue(selectUniq,_v);
        return tryRet(sel_);
    }

    public DefScrollCustomGraphicList<T> getSelectUniq() {
        return selectUniq;
    }

    private T tryRet(int _sel) {
        if (!elements.isValidIndex(_sel)) {
            return defValue();
        }
        return elements.get(_sel);
    }

    protected abstract T defValue();

    public CustList<T> getElements() {
        return elements;
    }

    public AbstractProgramInfos getCompoFactory() {
        return compoFactory;
    }
}
