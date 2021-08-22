package code.gui;

import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

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
    protected abstract JComponent getComponent();
    public static void invokeLater(Runnable _r) {
        SwingUtilities.invokeLater(_r);
    }

    public boolean isAutoscrolls(){
        return getComponent().getAutoscrolls();
    }
    public void setAutoscrolls(boolean _autoscrolls) {
        getComponent().setAutoscrolls(_autoscrolls);
    }

    public void addMouseListener(AbsMouseListener _mouseListener) {
        WrMouseListener wr_ = new WrMouseListener(_mouseListener);
        getComponent().addMouseListener(wr_);
        mapMouse.addEntry(_mouseListener,wr_);
    }

    public void addMouseMotionListener(AbsMouseMotionListener _mouseListener) {
        WrMouseMotionListener wr_ = new WrMouseMotionListener(_mouseListener);
        getComponent().addMouseMotionListener(wr_);
        mapMouseMotion.addEntry(_mouseListener,wr_);
    }

    public void addMouseWheelListener(AbsMouseWheelListener _l) {
        WrMouseWheelListener wr_ = new WrMouseWheelListener(_l);
        getComponent().addMouseWheelListener(wr_);
        mapMouseWheel.addEntry(_l,wr_);
    }
    public void addKeyListener(KeyListener _l) {
        getComponent().addKeyListener(_l);
    }
    public void addKeyListener(AbsKeyListener _l) {
        WrKeyListener wr_ = new WrKeyListener(_l);
        getComponent().addKeyListener(wr_);
        mapKey.addEntry(_l,wr_);
    }
    public void removeMouseListener(AbsMouseListener _mouseListener) {
        WrMouseListener wr_ = mapMouse.getVal(_mouseListener);
        getComponent().removeMouseListener(wr_);
        mapMouse.removeKey(_mouseListener);
    }

    public void removeMouseMotionListener(AbsMouseMotionListener _mouseListener) {
        WrMouseMotionListener wr_ = mapMouseMotion.getVal(_mouseListener);
        getComponent().removeMouseMotionListener(wr_);
        mapMouseMotion.removeKey(_mouseListener);
    }

    public void removeMouseWheelListener(AbsMouseWheelListener _l) {
        WrMouseWheelListener wr_ = mapMouseWheel.getVal(_l);
        getComponent().removeMouseWheelListener(wr_);
        mapMouseWheel.removeKey(_l);
    }
    public void removeKeyListener(KeyListener _l) {
        getComponent().removeKeyListener(_l);
    }
    public void removeKeyListener(AbsKeyListener _l) {
        WrKeyListener wr_ = mapKey.getVal(_l);
        getComponent().removeKeyListener(wr_);
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
        getComponent().requestFocus();
    }
    public boolean isVisible() {
        return getComponent().isVisible();
    }
    public void setVisible(boolean _b) {
        getComponent().setVisible(_b);
    }
    public int getWidth() {
        return getComponent().getWidth();
    }
    public int getHeight() {
        return getComponent().getHeight();
    }

    public int heightFont() {
        return heightFont(getComponent().getFont());
    }

    public int heightFont(Font _font) {
        return getComponent().getFontMetrics(_font).getHeight();
    }

    public int stringWidth(String _string) {
        return stringWidth(getComponent().getFont(),_string);
    }

    public int stringWidth(Font _font,String _string) {
        return getComponent().getFontMetrics(_font).stringWidth(_string);
    }
    public Font getFont() {
        return getComponent().getFont();
    }
    public void setFont(Font _font) {
        getComponent().setFont(_font);
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
        getComponent().setBorder(BorderFactory.createLineBorder(_color,1));
    }

    public void setLineBorder(Color _color, int _thick) {
        getComponent().setBorder(BorderFactory.createLineBorder(_color,_thick));
    }

    public void setTitledBorder(String _title) {
        getComponent().setBorder(BorderFactory.createTitledBorder(_title));
    }

    public void setLoweredBorder() {
        getComponent().setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }

    public void setRaisedBorder() {
        getComponent().setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }

    public void setToolTipText(String _title) {
        getComponent().setToolTipText(_title);
    }

    public void setCursor(Cursor _cursor) {
        getComponent().setCursor(_cursor);
    }

    public Dimension getSize() {
        return getComponent().getSize();
    }

    public void setSize(Dimension _dimension) {
        getComponent().setSize(_dimension);
    }

    public Dimension getPreferredSize() {
        return getComponent().getPreferredSize();
    }

    public void setPreferredSize(Dimension _dimension) {
        getComponent().setPreferredSize(_dimension);
    }

    public boolean isFocusable() {
        return getComponent().isFocusable();
    }
    public void setFocusable(boolean _focusable) {
        getComponent().setFocusable(_focusable);
    }
    public boolean isOpaque() {
        return getComponent().isOpaque();
    }

    public void setOpaque(boolean _b) {
        getComponent().setOpaque(_b);
    }

    public int getXcoords() {
        return getComponent().getX();
    }

    public int getYcoords() {
        return getComponent().getY();
    }
    public void setLocation(int _x, int _y) {
        getComponent().setLocation(_x,_y);
    }

    public void setBackground(Color _color) {
        getComponent().setBackground(_color);
    }
    public Color getBackground() {
        return getComponent().getBackground();
    }

    public void setForeground(Color _color) {
        getComponent().setForeground(_color);
    }
    public Color getForeground() {
        return getComponent().getForeground();
    }

    public String getToolTipText() {
        return getComponent().getToolTipText();
    }

    public void removeFocusListener(FocusListener _auto) {
        getComponent().removeFocusListener(_auto);
    }
    public void addFocusListener(FocusListener _auto) {
        getComponent().addFocusListener(_auto);
    }

    public Point getLocationOnScreen() {
        return getComponent().getLocationOnScreen();
    }

    public void addComponentListener(ComponentListener _compo) {
        getComponent().addComponentListener(_compo);
    }
    public void validate() {
        getComponent().validate();
    }

    public void revalidate() {
        getComponent().revalidate();
    }

}
