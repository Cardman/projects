package code.minirts;

import code.gui.AbsPaintableLabel;
import code.gui.events.AbsMouseListenerWithoutClickPr;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.core.*;

public abstract class AbsMetaLabelRts {
    private final AbsPaintableLabel paintableLabel;
    protected AbsMetaLabelRts(AbsCompoFactory _compoFactory) {
        paintableLabel = _compoFactory.newAbsPaintableLabel();
    }

    public static void repaintChildren(CustList<AbsMetaLabelRts> _panel, AbstractImageFactory _fact) {
        for (AbsMetaLabelRts c: _panel) {
            paintRts(_fact,c);
        }
    }

    public static void paintRts(AbstractImageFactory _fact, AbsMetaLabelRts _rts) {
        int w_ = _rts.getWidth();
        int h_ = _rts.getHeight();
        AbstractImage img_ = _fact.newImageArgb(NumberUtil.max(w_,1), NumberUtil.max(h_,1));
        img_.setFont(_rts.getPaintableLabel().getMetaFont());
        _rts.paintComponent(img_);
        _rts.setIcon(_fact,img_);
    }
    public AbsPaintableLabel getPaintableLabel() {
        return paintableLabel;
    }

    public abstract void paintComponent(AbstractImage _g);

    public void setIcon(AbstractImageFactory _fact, AbstractImage _icon){
        getPaintableLabel().setIcon(_fact, _icon);
    }
    public int getWidth(){
        return getPaintableLabel().getWidth();
    }
    public int getHeight(){
        return getPaintableLabel().getHeight();
    }

    public void setPreferredSize(MetaDimension _dim) {
        getPaintableLabel().setPreferredSize(_dim);
    }

    public void setLocation(int _x, int _y) {
        getPaintableLabel().setLocation(_x,_y);
    }
    public void addMouseListener(AbsMouseListenerWithoutClickPr _mouseListener) {
        getPaintableLabel().addMouseListener(_mouseListener);
    }

    public void setOpaque(boolean _op) {
        getPaintableLabel().setOpaque(_op);
    }

    public void setSize(MetaDimension _dimension) {
        getPaintableLabel().setSize(_dimension);
    }

}
