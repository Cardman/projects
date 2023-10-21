package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.NumberUtil;

public final class AdaptCustCellRenderString implements AbsCustCellRenderGene<String> {
    private final AbsCompoFactory compoFactory;
    private final AbstractImageFactory imageFactory;
    private int maxWidth;
    public AdaptCustCellRenderString(AbsCompoFactory _compo, AbstractImageFactory _img) {
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
    public AbstractImage getListCellRendererComponent(int _index, String _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        AbstractImage img_ = imageFactory.newImageRgb(NumberUtil.max(NumberUtil.max(getMaxWidth(), compoFactory.stringWidth(_lab, _info)),1), compoFactory.heightFont(_lab) + 2);
        img_.setFont(_lab);
        if (_isSelected) {
            img_.setColor(_colors.getBgSel());
            img_.fillRect(0,0,img_.getWidth(),img_.getHeight());
            img_.setColor(_colors.getFgSel());
        } else {
            img_.setColor(_colors.getBg());
            img_.fillRect(0,0,img_.getWidth(),img_.getHeight());
            img_.setColor(_colors.getFg());
        }
        img_.drawString(_info,0,img_.getHeight()-2);
        return img_;
    }
}
