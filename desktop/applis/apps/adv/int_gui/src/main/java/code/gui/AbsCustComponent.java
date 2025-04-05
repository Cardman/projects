package code.gui;

import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.util.CustList;
import code.util.StringMap;

public interface AbsCustComponent {
    boolean isEnabled();

    void setEnabled(boolean _value);

    boolean isAutoscrolls();
    void setAutoscrolls(boolean _autoscrolls);
    void addFocusListener(AbsFocusListener _mouseListener);
    void addFocusListenerMap(AbsFocusListener _mouseListener);
    void addMouseListener(AbsMouseListener _mouseListener);
    void addMouseListenerMap(AbsMouseListener _mouseListener);

    void addMouseListener(AbsMouseListenerPresRel _mouseListener);
    void addMouseListener(AbsMouseListenerIntRel _mouseListener);
    void addMouseListener(AbsActionListenerAct _c,AbsMouseListenerIntRel _mouseListener);
    void addMouseListener(AbsActionListenerAct _c,AbsMouseListenerWithoutClickEnter _mouseListener);
    void addMouseListener(AbsMouseListenerEnt _mouseListener);
    void addMouseListener(AbsMouseListenerWithoutClickPr _mouseListener);
    void addMouseMotionListener(AbsMouseMotionListener _mouseListener);
    void addMouseMotionListenerMap(AbsMouseMotionListener _mouseListener);
    void addMouseWheelListener(AbsMouseWheelListener _l);
    void addMouseWheelListenerMap(AbsMouseWheelListener _l);
    void addKeyListener(AbsKeyListener _l);
    void addKeyListenerMap(AbsKeyListener _l);
    void addKeyListener(AbsKeyListenerPress _l);
    void addKeyListener(AbsKeyListenerReleased _l);
    void removeFocusListener(AbsFocusListener _mouseListener);
    void removeFocusListenerMap(AbsFocusListener _mouseListener);
    void removeMouseListener(AbsMouseListener _mouseListener);
    void removeMouseListenerMap(AbsMouseListener _mouseListener);
    void removeMouseListener(AbsMouseListenerIntRel _mouseListener);
    void removeMouseMotionListener(AbsMouseMotionListener _mouseListener);
    void removeMouseMotionListenerMap(AbsMouseMotionListener _mouseListener);
    void removeMouseWheelListener(AbsMouseWheelListener _l);
    void removeMouseWheelListenerMap(AbsMouseWheelListener _l);
    void removeKeyListener(AbsKeyListener _l);
    void removeKeyListenerMap(AbsKeyListener _l);
    CustList<AbsFocusListener> getFocusListeners();
    CustList<AbsMouseListener> getMouseListeners();
    CustList<AbsMouseListenerIntRel> getMouseListenersRel();
    CustList<AbsMouseMotionListener> getMouseMotionListeners();
    CustList<AbsMouseWheelListener> getMouseWheelListeners();
    CustList<AbsKeyListener> getKeyListeners();
    void registerKeyboardAction(AbsEnabledAction _action, int _a, int _b);
    void registerKeyboardActionMap(AbsEnabledAction _action, int _a, int _b);
    void unregisterKeyboardAction(int _a, int _b);
    void unregisterKeyboardActionMap(int _a, int _b);

    StringMap<AbsEnabledAction> getActionsMap();


    boolean requestFocusInWindow();
    boolean isFocused();
    boolean isVisible();
    void setVisible(boolean _b);
    int getWidth();
    int getHeight();

    MetaFont getMetaFont();
    void setNullFont();
    void setFont(MetaFont _font);
    void setFont(String _name, int _style, int _size);
    void setLineBorder(int _color);
    void setLineBorder(int _color, int _thick);
    void setTitledBorder(String _title);
    void setLoweredBorder();
    void setRaisedBorder();
    void setToolTipText(String _title);
    void setHandCursor();

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
    int getBackgroundValue();
    void setForeground(int _color);
    int getForegroundValue();
    String getToolTipText();
    void validate();
    void revalidate();
    void recalculate();
    void top();
    void bottom();
    void centerVert();
    void left();
    void right();
    void centerHoriz();
    void disabledRichText(boolean _d);
}
