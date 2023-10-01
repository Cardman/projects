package code.gui;

import code.expressionlanguage.structs.Struct;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.NumberUtil;


public final class DefaultCellRenderStr extends CustCellRender<Struct> {

    private int maxWidthStr;

    private AbsPreparedLabel labelStr;
    private String textStr;
    private boolean selectedStr;
    private final SpecSelectionCtx spec;
    private final AbstractImageFactory fact;
    private final AbsCompoFactory compo;

    public DefaultCellRenderStr(SpecSelectionCtx _spec, AbstractImageFactory _fact, AbsCompoFactory _compoFactory) {
        spec = _spec;
        fact = _fact;
        compo = _compoFactory;
    }

    @Override
    public AbsCustCellRender fwd() {
        setList(getListGr().getList());
        return this;
    }

    @Override
    public void getListCellRendererComponent(AbsPreparedLabel _currentLab,
                                             int _index, boolean _isSelected, boolean _cellHasFocus) {
        textStr = spec.convertStr(get(_index));
        labelStr = _currentLab;
        maxWidthStr = NumberUtil.max(maxWidthStr, compo.stringWidth(labelStr.getMetaFont(),textStr));
        selectedStr = _isSelected;
    }
    public int getMaxWidth() {
        return maxWidthStr;
    }

    public void setMaxWidth(int _maxWidth) {
        maxWidthStr = _maxWidth;
    }

    @Override
    public AbstractImageFactory getImageFactory() {
        return fact;
    }
    @Override
    public int getWidth() {
        return maxWidthStr;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        FrameUtil.paintLabel(getMaxWidth(),_g, labelStr, textStr, selectedStr,compo);
    }
    @Override
    public int getHeight() {
        return compo.heightFont(labelStr.getMetaFont());
    }
}
