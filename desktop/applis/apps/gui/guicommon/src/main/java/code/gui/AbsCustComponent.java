package code.gui;

import code.gui.events.AbsKeyListener;
import code.gui.events.AbsMouseListener;
import code.gui.events.AbsMouseMotionListener;
import code.gui.events.AbsMouseWheelListener;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.util.CustList;

public interface AbsCustComponent {
    boolean isAutoscrolls();
    void setAutoscrolls(boolean _autoscrolls);
    void addMouseListener(AbsMouseListener _mouseListener);
    void addMouseMotionListener(AbsMouseMotionListener _mouseListener);
    void addMouseWheelListener(AbsMouseWheelListener _l);
    void addKeyListener(AbsKeyListener _l);
    void removeMouseListener(AbsMouseListener _mouseListener);
    void removeMouseMotionListener(AbsMouseMotionListener _mouseListener);
    void removeMouseWheelListener(AbsMouseWheelListener _l);
    void removeKeyListener(AbsKeyListener _l);
    CustList<AbsMouseListener> getMouseListeners();
    CustList<AbsMouseMotionListener> getMouseMotionListeners();
    CustList<AbsMouseWheelListener> getMouseWheelListeners();
    CustList<AbsKeyListener> getKeyListeners();
    void requestFocus();
    boolean isVisible();
    void setVisible(boolean _b);
    int getWidth();
    int getHeight();
    int heightFont();
    int heightFont(MetaFont _font);
    int stringWidth(String _string);
    int stringWidth(MetaFont _font,String _string);
    MetaFont getMetaFont();
    void setNullFont();
    void setFont(MetaFont _font);
    void setFont(String _name, int _style, int _size);
    AbsCustComponent getParent();
    CustList<AbsCustComponent> getChildren();
    void setParent(AbsCustComponent _parent);
    void setLineBorder(int _color);
    void setLineBorder(int _color, int _thick);
    void setTitledBorder(String _title);
    void setLoweredBorder();
    void setRaisedBorder();
    void setToolTipText(String _title);
    void setCursor(int _wCurs, int _hCurs, int[] _pixels);
    void setCursor(int _nb);
    void setSize(MetaDimension _dimension);
    MetaDimension getPreferredSizeValue();
    void setPreferredSize(MetaDimension _dimension);
    boolean isFocusable();
    void setFocusable(boolean _focusable);
    boolean isOpaque();
    void setOpaque(boolean _b);
    int getXcoords();
    int getYcoords();
    void setLocation(int _x, int _y);
    void setBackground(int _color);
    void setBackground(AbsCustComponent _other);
    int getBackgroundValue();
    void setForeground(int _color);
    void setForeground(AbsCustComponent _other);
    int getForegroundValue();
    String getToolTipText();
    void validate();
    void revalidate();
}
