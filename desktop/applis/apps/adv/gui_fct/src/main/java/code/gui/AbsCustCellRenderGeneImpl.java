package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.NumberUtil;

public abstract class AbsCustCellRenderGeneImpl<T> implements AbsCustCellRenderGene<T> {
    private final AbsCompoFactory compoFactory;
    private final AbstractImageFactory imageFactory;
    private int maxWidth;
    protected AbsCustCellRenderGeneImpl(AbsCompoFactory _compo, AbstractImageFactory _img) {
        compoFactory = _compo;
        imageFactory = _img;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int _m) {
        this.maxWidth = _m;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, T _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        String message_ = convert(_info);
        AbstractImage img_ = imageFactory.newImageRgb(NumberUtil.max(NumberUtil.max(getMaxWidth(), compoFactory.stringWidth(_lab, message_)),1), compoFactory.heightFont(_lab) + 2);
        img_.setFont(_lab);
        CustCellRenderString.select(_isSelected,_colors,img_);
        img_.drawString(message_,0,img_.getHeight()-2);
        return img_;
    }

    protected abstract String convert(T _info);
}
