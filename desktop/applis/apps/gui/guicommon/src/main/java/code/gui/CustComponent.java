package code.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import code.gui.events.*;
import code.util.CustList;
import code.util.IdMap;

public abstract class CustComponent {

    private CustComponent parent;
    private final IdMap<AbsKeyListener, WrKeyListener> mapKey = new IdMap<AbsKeyListener, WrKeyListener>();
    private final IdMap<AbsMouseListener, WrMouseListener> mapMouse = new IdMap<AbsMouseListener, WrMouseListener>();
    private final IdMap<AbsMouseMotionListener, WrMouseMotionListener> mapMouseMotion = new IdMap<AbsMouseMotionListener, WrMouseMotionListener>();
    private final IdMap<AbsMouseWheelListener, WrMouseWheelListener> mapMouseWheel = new IdMap<AbsMouseWheelListener, WrMouseWheelListener>();
    private final CustList<CustComponent> children = new CustList<CustComponent>();
    protected abstract JComponent getNatComponent();
    public static void invokeLater(Runnable _r) {
        SwingUtilities.invokeLater(_r);
    }

    public boolean isAutoscrolls(){
        return getNatComponent().getAutoscrolls();
    }
    public void setAutoscrolls(boolean _autoscrolls) {
        getNatComponent().setAutoscrolls(_autoscrolls);
    }

    public void addMouseListener(AbsMouseListener _mouseListener) {
        WrMouseListener wr_ = new WrMouseListener(_mouseListener);
        getNatComponent().addMouseListener(wr_);
        mapMouse.addEntry(_mouseListener,wr_);
    }

    public void addMouseMotionListener(AbsMouseMotionListener _mouseListener) {
        WrMouseMotionListener wr_ = new WrMouseMotionListener(_mouseListener);
        getNatComponent().addMouseMotionListener(wr_);
        mapMouseMotion.addEntry(_mouseListener,wr_);
    }

    public void addMouseWheelListener(AbsMouseWheelListener _l) {
        WrMouseWheelListener wr_ = new WrMouseWheelListener(_l);
        getNatComponent().addMouseWheelListener(wr_);
        mapMouseWheel.addEntry(_l,wr_);
    }

    public void addKeyListener(AbsKeyListener _l) {
        WrKeyListener wr_ = new WrKeyListener(_l);
        getNatComponent().addKeyListener(wr_);
        mapKey.addEntry(_l,wr_);
    }
    public void removeMouseListener(AbsMouseListener _mouseListener) {
        WrMouseListener wr_ = mapMouse.getVal(_mouseListener);
        getNatComponent().removeMouseListener(wr_);
        mapMouse.removeKey(_mouseListener);
    }

    public void removeMouseMotionListener(AbsMouseMotionListener _mouseListener) {
        WrMouseMotionListener wr_ = mapMouseMotion.getVal(_mouseListener);
        getNatComponent().removeMouseMotionListener(wr_);
        mapMouseMotion.removeKey(_mouseListener);
    }

    public void removeMouseWheelListener(AbsMouseWheelListener _l) {
        WrMouseWheelListener wr_ = mapMouseWheel.getVal(_l);
        getNatComponent().removeMouseWheelListener(wr_);
        mapMouseWheel.removeKey(_l);
    }

    public void removeKeyListener(AbsKeyListener _l) {
        WrKeyListener wr_ = mapKey.getVal(_l);
        getNatComponent().removeKeyListener(wr_);
        mapKey.removeKey(_l);
    }

    public CustList<AbsMouseListener> getMouseListeners() {
        return mapMouse.getKeys();
    }

    public CustList<AbsMouseMotionListener> getMouseMotionListeners() {
        return mapMouseMotion.getKeys();
    }

    public CustList<AbsMouseWheelListener> getMouseWheelListeners() {
        return mapMouseWheel.getKeys();
    }

    public CustList<AbsKeyListener> getKeyListeners() {
        return mapKey.getKeys();
    }

    public void requestFocus() {
        getNatComponent().requestFocus();
    }
    public boolean isVisible() {
        return getNatComponent().isVisible();
    }
    public void setVisible(boolean _b) {
        getNatComponent().setVisible(_b);
    }
    public int getWidth() {
        return getNatComponent().getWidth();
    }
    public int getHeight() {
        return getNatComponent().getHeight();
    }

    public int heightFont() {
        return heightFont(getNatComponent().getFont());
    }

    public int heightFont(Font _font) {
        return getNatComponent().getFontMetrics(_font).getHeight();
    }

    public int stringWidth(String _string) {
        return stringWidth(getNatComponent().getFont(),_string);
    }

    public int stringWidth(Font _font,String _string) {
        return getNatComponent().getFontMetrics(_font).stringWidth(_string);
    }
    public Font getFont() {
        return getNatComponent().getFont();
    }
    public void setFont(Font _font) {
        getNatComponent().setFont(_font);
    }
    public CustComponent getParent() {
        return parent;
    }
    public void setParent(CustComponent _parent) {
        parent = _parent;
    }
    public CustList<CustComponent> getChildren() {
        return children;
    }

    public void setLineBorder(Color _color) {
        getNatComponent().setBorder(BorderFactory.createLineBorder(_color,1));
    }

    public void setLineBorder(Color _color, int _thick) {
        getNatComponent().setBorder(BorderFactory.createLineBorder(_color,_thick));
    }

    public void setTitledBorder(String _title) {
        getNatComponent().setBorder(BorderFactory.createTitledBorder(_title));
    }

    public void setLoweredBorder() {
        getNatComponent().setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }

    public void setRaisedBorder() {
        getNatComponent().setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }

    public void setToolTipText(String _title) {
        getNatComponent().setToolTipText(_title);
    }

    public void setCursor(Cursor _cursor) {
        getNatComponent().setCursor(_cursor);
    }

    public Dimension getSize() {
        return getNatComponent().getSize();
    }

    public void setSize(Dimension _dimension) {
        getNatComponent().setSize(_dimension);
    }

    public Dimension getPreferredSize() {
        return getNatComponent().getPreferredSize();
    }

    public void setPreferredSize(Dimension _dimension) {
        getNatComponent().setPreferredSize(_dimension);
    }

    public boolean isFocusable() {
        return getNatComponent().isFocusable();
    }
    public void setFocusable(boolean _focusable) {
        getNatComponent().setFocusable(_focusable);
    }
    public boolean isOpaque() {
        return getNatComponent().isOpaque();
    }

    public void setOpaque(boolean _b) {
        getNatComponent().setOpaque(_b);
    }

    public int getXcoords() {
        return getNatComponent().getX();
    }

    public int getYcoords() {
        return getNatComponent().getY();
    }
    public void setLocation(int _x, int _y) {
        getNatComponent().setLocation(_x,_y);
    }

    public void setBackground(Color _color) {
        getNatComponent().setBackground(_color);
    }
    public Color getBackground() {
        return getNatComponent().getBackground();
    }

    public void setForeground(Color _color) {
        getNatComponent().setForeground(_color);
    }
    public Color getForeground() {
        return getNatComponent().getForeground();
    }

    public String getToolTipText() {
        return getNatComponent().getToolTipText();
    }

    public Point getLocationOnScreen() {
        return getNatComponent().getLocationOnScreen();
    }

    public void validate() {
        getNatComponent().validate();
    }

    public void revalidate() {
        getNatComponent().revalidate();
    }

}
