package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;

public final class CustCellRenderString implements AbsCustCellRenderGene<String> {
    private final AbsCompoFactory compoFactory;
    private final AbstractImageFactory imageFactory;
    public CustCellRenderString(AbsCompoFactory _compo, AbstractImageFactory _img) {
        compoFactory = _compo;
        imageFactory = _img;
    }
    @Override
    public AbstractImage getListCellRendererComponent(int _index, String _info, boolean _isSelected, boolean _cellHasFocus, AbsPreparedLabel _lab, ColorsGroupList _colors) {
        AbstractImage img_ = imageFactory.newImageRgb(compoFactory.stringWidth(_lab.getMetaFont(), _info), _lab.heightFont() + 2, _lab);
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
        img_.drawLine(0,img_.getHeight()-1,img_.getWidth(),img_.getHeight()-1);
        return img_;
    }
}
