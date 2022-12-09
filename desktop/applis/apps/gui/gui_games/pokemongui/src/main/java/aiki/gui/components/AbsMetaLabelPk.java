package aiki.gui.components;

import code.gui.AbsPaintableLabel;
import code.gui.events.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.core.NumberUtil;

public abstract class AbsMetaLabelPk {
    private final AbsPaintableLabel paintableLabel;
    private final AbsCompoFactory factPk;
    protected AbsMetaLabelPk(AbsCompoFactory _compoFactory) {
        factPk = _compoFactory;
        paintableLabel = _compoFactory.newAbsPaintableLabel();
    }

    public static void repaintChildren(CustList<AbsMetaLabelPk> _panel, AbstractImageFactory _fact) {
        for (AbsMetaLabelPk c: _panel) {
            paintPk(_fact,c);
        }
    }

    public static void paintPk(AbstractImageFactory _fact, AbsMetaLabelPk _pk) {
        int w_ = _pk.getWidth();
        int h_ = _pk.getHeight();
        if (NumberUtil.signum(w_) + NumberUtil.signum(h_) <= 1) {
            AbstractImage img_ = _fact.newImageArgb(1, 1);
            img_.setFont(_pk.getPaintableLabel());
            _pk.paintComponent(img_);
            _pk.setIcon(_fact,img_);
            return;
        }
        AbstractImage img_ = _fact.newImageArgb(w_, h_);
        img_.setFont(_pk.getPaintableLabel());
        _pk.paintComponent(img_);
        _pk.setIcon(_fact,img_);
    }
    public AbsPaintableLabel getPaintableLabel() {
        return paintableLabel;
    }
    public abstract void paintComponent(AbstractImage _g2);

    public void setIcon(AbstractImageFactory _fact, AbstractImage _icon){
        getPaintableLabel().setIcon(_fact, _icon);
    }

    public int getWidth(){
        return getPaintableLabel().getPreferredSizeValue().getWidth();
    }
    public int getHeight(){
        return getPaintableLabel().getPreferredSizeValue().getHeight();
    }
    public int heightFont(){
        return getPaintableLabel().heightFont();
    }

    public int stringWidth(String _string){
        return factPk.stringWidth(getMetaFont(),_string);
    }

    public void setPreferredSize(MetaDimension _dim) {
        getPaintableLabel().setPreferredSize(_dim);
    }

    public void addMouseListener(AbsMouseListenerWithoutClick _mouseListener) {
        getPaintableLabel().addMouseListener(_mouseListener);
    }
    public void addMouseListener(AbsMouseListener _mouseListener) {
        getPaintableLabel().addMouseListener(_mouseListener);
    }
    public void addMouseListener(AbsMouseListenerWithoutClickPr _mouseListener) {
        getPaintableLabel().addMouseListener(_mouseListener);
    }

    public void addMouseListener(AbsMouseListenerIntRel _mouseListener) {
        getPaintableLabel().addMouseListener(_mouseListener);
    }

    public void addKeyListener(AbsKeyListenerPress _l) {
        getPaintableLabel().addKeyListener(_l);
    }
    public void setToolTipText(String _title) {
        getPaintableLabel().setToolTipText(_title);
    }

    public MetaFont getMetaFont() {
        return getPaintableLabel().getMetaFont();
    }
    public int getFontSize() {
        return getPaintableLabel().getMetaFont().getRealSize();
    }

    public void setFocusable(boolean _focusable){
        getPaintableLabel().setFocusable(_focusable);
    }
    public void setVisible(boolean _visible) {
        getPaintableLabel().setVisible(_visible);
    }

    public void setFont(String _name, int _style, int _size) {
        getPaintableLabel().setFont(_name, _style, _size);
    }


}
