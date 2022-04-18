package code.gui;

import code.gui.events.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;


public abstract class AbsMetaLabelCom {

    public void repaintLabel(AbstractImageFactory _fact){
        getPaintableLabel().repaintLabel(_fact);
    }
    public void requestFocusInWindow(){
        getPaintableLabel().requestFocusInWindow();
    }

    public void setEmptyIcon(){
        getPaintableLabel().setEmptyIcon();
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
    public abstract AbsPaintableLabel getPaintableLabel();
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

    public void requestFocus(){
        getPaintableLabel().requestFocus();
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

    public void setOpaque(boolean _op) {
        getPaintableLabel().setOpaque(_op);
    }

    public void setSize(MetaDimension _dimension) {
        getPaintableLabel().setSize(_dimension);
    }

}
