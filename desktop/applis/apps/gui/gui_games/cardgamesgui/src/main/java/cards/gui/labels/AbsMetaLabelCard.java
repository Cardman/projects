package cards.gui.labels;

import code.gui.AbsPaintableLabel;
import code.gui.events.AbsMouseListenerIntRel;
import code.gui.events.AbsMouseListenerWithoutClick;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;

public abstract class AbsMetaLabelCard {
    private final AbsPaintableLabel paintableLabel;
    protected AbsMetaLabelCard(AbsCompoFactory _compoFactory) {
        paintableLabel = _compoFactory.newAbsPaintableLabel();
    }

    public static void repaintChildren(CustList<AbsMetaLabelCard> _panel, AbstractImageFactory _fact) {
        for (AbsMetaLabelCard c: _panel) {
            paintCard(_fact,c);
        }
    }

    public static void paintCard(AbstractImageFactory _fact, AbsMetaLabelCard _carte) {
        int w_ = _carte.getWidth();
        int h_ = _carte.getHeight();
        AbstractImage img_ = _fact.newImageArgb(w_, h_);
        img_.setFont(_carte.getPaintableLabel());
        _carte.paintComponent(img_);
        _carte.setIcon(_fact,img_);
    }

    public abstract void paintComponent(AbstractImage _g2);

    public AbsPaintableLabel getPaintableLabel() {
        return paintableLabel;
    }

    public void setIcon(AbstractImageFactory _fact, AbstractImage _icon){
        getPaintableLabel().setIcon(_fact, _icon);
    }
    public void setVerticalAlignment(int _alignment){
        getPaintableLabel().setVerticalAlignment(_alignment);
    }
    public void setHorizontalAlignment(int _alignment){
        getPaintableLabel().setHorizontalAlignment(_alignment);
    }
    public int getWidth(){
        return getPaintableLabel().getWidth();
    }
    public int getHeight(){
        return getPaintableLabel().getHeight();
    }
    public int heightFont(){
        return getPaintableLabel().heightFont();
    }

    public int stringWidth(String _string){
        return getPaintableLabel().stringWidth(_string);
    }

    public void setPreferredSize(MetaDimension _dim) {
        getPaintableLabel().setPreferredSize(_dim);
    }

    public void setBackground(int _color) {
        getPaintableLabel().setBackground(_color);
    }

    public void setForeground(int _color) {
        getPaintableLabel().setForeground(_color);
    }

    public void setLocation(int _x, int _y) {
        getPaintableLabel().setLocation(_x,_y);
    }
    public void addMouseListener(AbsMouseListenerWithoutClick _mouseListener) {
        getPaintableLabel().addMouseListener(_mouseListener);
    }

    public void addMouseListener(AbsMouseListenerIntRel _mouseListener) {
        getPaintableLabel().addMouseListener(_mouseListener);
    }
    public void setVisible(boolean _visible) {
        getPaintableLabel().setVisible(_visible);
    }
    public void setSize(MetaDimension _dimension) {
        getPaintableLabel().setSize(_dimension);
    }

}
