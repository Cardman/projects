package code.gui;

import code.expressionlanguage.structs.Struct;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;


public final class DefaultCellRenderStr extends CustCellRender<Struct> {

    private int maxWidthStr;

    private AbsPreparedLabel labelStr;
    private String textStr;
    private boolean selectedStr;
    private final SpecSelectionCtx spec;
    private final AbstractImageFactory fact;

    public DefaultCellRenderStr(SpecSelectionCtx _spec,AbstractImageFactory _fact) {
        spec = _spec;
        fact = _fact;
    }
    @Override
    public void getListCellRendererComponent(AbsPreparedLabel _currentLab,
                                             int _index, boolean _isSelected, boolean _cellHasFocus) {
        textStr = spec.convertStr(get(_index));
        labelStr = _currentLab;
        maxWidthStr = Math.max(maxWidthStr, labelStr.stringWidth(textStr));
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
        FrameUtil.paintLabel(getMaxWidth(),_g, labelStr, textStr, selectedStr);
    }
    @Override
    public int getHeight() {
        return labelStr.heightFont();
    }
}
