package code.gui;

import code.gui.events.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.util.CustList;

public class PreparedLabelSample implements AbsPreparedLabel{
    @Override
    public boolean isAutoscrolls() {
        return false;
    }

    @Override
    public void setAutoscrolls(boolean _autoscrolls) {

    }

    @Override
    public void addMouseListener(AbsMouseListener _mouseListener) {

    }

    @Override
    public void addMouseListener(AbsMouseListenerPresRel _mouseListener) {

    }

    @Override
    public void addMouseListener(AbsMouseListenerIntRel _mouseListener) {

    }

    @Override
    public void addMouseListener(AbsMouseListenerEnt _mouseListener) {

    }

    @Override
    public void addMouseListener(AbsMouseListenerCl _mouseListener) {

    }

    @Override
    public void addMouseListener(AbsMouseListenerWithoutClick _mouseListener) {

    }

    @Override
    public void addMouseListener(AbsMouseListenerWithoutClickPr _mouseListener) {

    }

    @Override
    public void addMouseListener(AbsMouseListenerEer _mouseListener) {

    }

    @Override
    public void addMouseMotionListener(AbsMouseMotionListener _mouseListener) {

    }

    @Override
    public void addMouseWheelListener(AbsMouseWheelListener _l) {

    }

    @Override
    public void addKeyListener(AbsKeyListener _l) {

    }

    @Override
    public void addKeyListener(AbsKeyListenerPress _l) {

    }

    @Override
    public void addKeyListener(AbsKeyListenerReleased _l) {

    }

    @Override
    public void removeMouseListener(AbsMouseListener _mouseListener) {

    }

    @Override
    public void removeMouseMotionListener(AbsMouseMotionListener _mouseListener) {

    }

    @Override
    public void removeMouseWheelListener(AbsMouseWheelListener _l) {

    }

    @Override
    public void removeKeyListener(AbsKeyListener _l) {

    }

    @Override
    public CustList<AbsMouseListener> getMouseListeners() {
        return null;
    }

    @Override
    public CustList<AbsMouseMotionListener> getMouseMotionListeners() {
        return null;
    }

    @Override
    public CustList<AbsMouseWheelListener> getMouseWheelListeners() {
        return null;
    }

    @Override
    public CustList<AbsKeyListener> getKeyListeners() {
        return null;
    }

    @Override
    public void requestFocus() {

    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public void setVisible(boolean _b) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int heightFont() {
        return 0;
    }

    @Override
    public int heightFont(MetaFont _font) {
        return 0;
    }

    @Override
    public int stringWidth(String _string) {
        return 0;
    }

    @Override
    public int stringWidth(MetaFont _font, String _string) {
        return 0;
    }

    @Override
    public MetaFont getMetaFont() {
        return null;
    }

    @Override
    public void setNullFont() {

    }

    @Override
    public void setFont(MetaFont _font) {

    }

    @Override
    public void setFont(String _name, int _style, int _size) {

    }

    @Override
    public AbsCustComponent getParent() {
        return null;
    }

    @Override
    public CustList<AbsCustComponent> getChildren() {
        return null;
    }

    @Override
    public void setParent(AbsCustComponent _parent) {

    }

    @Override
    public void setLineBorder(int _color) {

    }

    @Override
    public void setLineBorder(int _color, int _thick) {

    }

    @Override
    public void setTitledBorder(String _title) {

    }

    @Override
    public void setLoweredBorder() {

    }

    @Override
    public void setRaisedBorder() {

    }

    @Override
    public void setToolTipText(String _title) {

    }

    @Override
    public void setHandCursor() {

    }

    @Override
    public void setSize(MetaDimension _dimension) {

    }

    @Override
    public MetaDimension getPreferredSizeValue() {
        return null;
    }

    @Override
    public void setPreferredSize(MetaDimension _dimension) {

    }

    @Override
    public boolean isFocusable() {
        return false;
    }

    @Override
    public void setFocusable(boolean _focusable) {

    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public void setOpaque(boolean _b) {

    }

    @Override
    public int getXcoords() {
        return 0;
    }

    @Override
    public int getYcoords() {
        return 0;
    }

    @Override
    public void setLocation(int _x, int _y) {

    }

    @Override
    public void setBackground(int _color) {

    }

    @Override
    public void setBackground(AbsCustComponent _other) {

    }

    @Override
    public int getBackgroundValue() {
        return 0;
    }

    @Override
    public void setForeground(int _color) {

    }

    @Override
    public void setForeground(AbsCustComponent _other) {

    }

    @Override
    public int getForegroundValue() {
        return 0;
    }

    @Override
    public String getToolTipText() {
        return null;
    }

    @Override
    public void validate() {

    }

    @Override
    public void revalidate() {

    }

    @Override
    public void recalculate() {

    }

    @Override
    public void top() {

    }

    @Override
    public void bottom() {

    }

    @Override
    public void centerVert() {

    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void centerHoriz() {

    }

    @Override
    public void setIcon(AbstractImageFactory _fact, AbstractImage _icon) {

    }
}
