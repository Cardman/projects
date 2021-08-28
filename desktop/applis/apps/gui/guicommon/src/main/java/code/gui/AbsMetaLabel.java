package code.gui;

import code.gui.events.AbsKeyListener;
import code.gui.events.AbsMouseListener;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaFont;
import code.gui.initialize.AbsCompoFactory;


import java.awt.Dimension;

public abstract class AbsMetaLabel {
    private final AbsPaintableLabel paintableLabel;
    protected AbsMetaLabel(AbsCompoFactory _compoFactory) {
        paintableLabel = _compoFactory.newAbsPaintableLabel(this);
    }
    public abstract void paintComponent(AbstractImage _g);

    public void repaintLabel(AbstractImageFactory _fact){
        paintableLabel.repaintLabel(_fact);
    }
    public void requestFocusInWindow(){
        paintableLabel.requestFocusInWindow();
    }

    public void setEmptyIcon(){
        paintableLabel.setEmptyIcon();
    }
    public void setIcon(AbstractImageFactory _fact, AbstractImage _icon){
        paintableLabel.setIcon(_fact, _icon);
    }
    public void setVerticalAlignment(int _alignment){
        paintableLabel.setVerticalAlignment(_alignment);
    }
    public void setHorizontalAlignment(int _alignment){
        paintableLabel.setHorizontalAlignment(_alignment);
    }
    public AbsCustComponent getPaintableLabel() {
        return paintableLabel;
    }
    public int getWidth(){
        return paintableLabel.getWidth();
    }
    public int getHeight(){
        return paintableLabel.getHeight();
    }
    public int heightFont(){
        return paintableLabel.heightFont();
    }

    public int stringWidth(String _string){
        return paintableLabel.stringWidth(_string);
    }

    public void setPreferredSize(Dimension _dim) {
        paintableLabel.setPreferredSize(_dim);
    }

    public void setBackground(int _color) {
        paintableLabel.setBackground(_color);
    }

    public int getBackgroundValue() {
        return paintableLabel.getBackgroundValue();
    }

    public void setForeground(int _color) {
        paintableLabel.setForeground(_color);
    }

    public int getForegroundValue() {
        return paintableLabel.getForegroundValue();
    }
    public void setLocation(int _x, int _y) {
        paintableLabel.setLocation(_x,_y);
    }
    public void addMouseListener(AbsMouseListener _mouseListener) {
        paintableLabel.addMouseListener(_mouseListener);
    }
    public void addKeyListener(AbsKeyListener _l) {
        paintableLabel.addKeyListener(_l);
    }
    public void setToolTipText(String _title) {
        paintableLabel.setToolTipText(_title);
    }

    public MetaFont getMetaFont() {
        return paintableLabel.getMetaFont();
    }
    public int getFontSize() {
        return paintableLabel.getMetaFont().getRealSize();
    }

    public void requestFocus(){
        paintableLabel.requestFocus();
    }
    public void setFocusable(boolean _focusable){
        paintableLabel.setFocusable(_focusable);
    }
    public void setVisible(boolean _visible) {
        paintableLabel.setVisible(_visible);
    }

    public void setFont(String _name, int _style, int _size) {
        paintableLabel.setFont(_name, _style, _size);
    }

    public void setOpaque(boolean _op) {
        paintableLabel.setOpaque(_op);
    }

    public void setSize(Dimension _dimension) {
        paintableLabel.setSize(_dimension);
    }

}
