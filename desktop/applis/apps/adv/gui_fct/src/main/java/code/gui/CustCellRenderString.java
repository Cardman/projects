package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.NumberUtil;

public final class CustCellRenderString implements AbsCustCellRenderGene<String> {
    private final AbsCompoFactory compoFactory;
    private final AbstractImageFactory imageFactory;
    private int maxWidth;
    public CustCellRenderString(AbsCompoFactory _compo, AbstractImageFactory _img) {
        compoFactory = _compo;
        imageFactory = _img;
        setMaxWidth(0);
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int _m) {
        this.maxWidth = _m;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, String _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        AbstractImage img_ = imageFactory.newImageRgb(NumberUtil.max(NumberUtil.max(compoFactory.stringWidth(_lab, _info), getMaxWidth()),1), compoFactory.heightFont(_lab) + 2);
        img_.setFont(_lab);
        select(_isSelected, _colors, img_);
        img_.drawString(_info,0,img_.getHeight()-2);
        if (_cellHasFocus) {
            img_.drawLine(0,0,img_.getWidth(),0);
            img_.drawLine(0,img_.getHeight()-1,img_.getWidth(),img_.getHeight()-1);
        }
        if (_cellIsAnchored) {
            img_.drawLine(0,0,0,img_.getHeight());
            img_.drawLine(img_.getWidth()-1,0,img_.getWidth()-1,img_.getHeight());
        }
        return img_;
    }

    static void select(boolean _isSelected, ColorsGroupList _colors, AbstractImage _img) {
        if (_isSelected) {
            _img.setColor(_colors.getBgSel());
            _img.fillRect(0,0, _img.getWidth(), _img.getHeight());
            _img.setColor(_colors.getFgSel());
        } else {
            _img.setColor(_colors.getBg());
            _img.fillRect(0,0, _img.getWidth(), _img.getHeight());
            _img.setColor(_colors.getFg());
        }
    }
}
