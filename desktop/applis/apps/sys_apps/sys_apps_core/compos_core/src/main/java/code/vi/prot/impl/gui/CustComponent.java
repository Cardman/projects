package code.vi.prot.impl.gui;

import code.gui.AbsCustComponent;
import code.gui.AbsFocusListener;
import code.gui.GuiConstants;
import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.util.CustList;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringMap;
import code.vi.prot.impl.DefImage;
import code.vi.prot.impl.gui.events.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public abstract class CustComponent implements AbsCustComponent {

    public static final String NO_RICH_TEXT = "html.disable";
    private AbsCustComponent parent;
    private final IdMap<AbsFocusListener, WrFocusListener> mapFocus = new IdMap<AbsFocusListener, WrFocusListener>();
    private final IdMap<AbsKeyListener, WrKeyListener> mapKey = new IdMap<AbsKeyListener, WrKeyListener>();
    private final IdMap<AbsMouseListener, WrMouseListener> mapMouse = new IdMap<AbsMouseListener, WrMouseListener>();
    private final IdMap<AbsMouseListenerIntRel, WrMouseListenerRel> mapMouseRel = new IdMap<AbsMouseListenerIntRel, WrMouseListenerRel>();
    private final IdMap<AbsMouseMotionListener, WrMouseMotionListener> mapMouseMotion = new IdMap<AbsMouseMotionListener, WrMouseMotionListener>();
    private final IdMap<AbsMouseWheelListener, WrMouseWheelListener> mapMouseWheel = new IdMap<AbsMouseWheelListener, WrMouseWheelListener>();
    private final StringMap<AbsEnabledAction> actions = new StringMap<AbsEnabledAction>();
    private final IdList<AbsCustComponent> children = new IdList<AbsCustComponent>();
    private int backGroundValue;
    private int foreGroundValue;

    @Override
    public boolean isEnabled() {
        return getNatComponent().isEnabled();
    }

    public void setEnabled(boolean _b) {
        getNatComponent().setEnabled(_b);
    }
    public abstract JComponent getNatComponent();

    public boolean isAutoscrolls(){
        return getNatComponent().getAutoscrolls();
    }
    public void setAutoscrolls(boolean _autoscrolls) {
        getNatComponent().setAutoscrolls(_autoscrolls);
    }

    @Override
    public void addFocusListener(AbsFocusListener _mouseListener) {
        WrFocusListener wr_ = new WrFocusListener(_mouseListener);
        getNatComponent().addFocusListener(wr_);
        mapFocus.addEntry(_mouseListener,wr_);
    }

    @Override
    public void addFocusListenerMap(AbsFocusListener _mouseListener) {
        WrFocusListener wr_ = new WrFocusListener(_mouseListener);
        mapFocus.addEntry(_mouseListener,wr_);
    }

    @Override
    public void addMouseListener(AbsMouseListenerPresRel _mouseListener) {
        WrMouseListenerPresRel wr_ = new WrMouseListenerPresRel(_mouseListener);
        getNatComponent().addMouseListener(wr_);
    }

    @Override
    public void addMouseListener(AbsMouseListenerIntRel _mouseListener) {
        WrMouseListenerRel wr_ = new WrMouseListenerRel(_mouseListener);
        getNatComponent().addMouseListener(wr_);
        mapMouseRel.addEntry(_mouseListener,wr_);
    }

    @Override
    public void addMouseListener(AbsActionListenerAct _c, AbsMouseListenerIntRel _mouseListener) {
        WrMouseListenerRel wr_ = new WrMouseListenerRel(_c,_mouseListener);
        getNatComponent().addMouseListener(wr_);
        mapMouseRel.addEntry(_mouseListener,wr_);
    }

//    @Override
//    public void addMouseListener(AbsMouseListenerWithoutClickEnter _mouseListener) {
//        WrMouseListenerWithoutCl wr_ = new WrMouseListenerWithoutCl(_mouseListener);
//        getNatComponent().addMouseListener(wr_);
//    }

    @Override
    public void addMouseListener(AbsMouseListenerWithoutClickPr _mouseListener) {
        WrMouseListenerWithoutClPr wr_ = new WrMouseListenerWithoutClPr(_mouseListener);
        getNatComponent().addMouseListener(wr_);
    }

    @Override
    public void addMouseListener(AbsActionListenerAct _c, AbsMouseListenerWithoutClickEnter _mouseListener) {
        WrMouseListenerWithoutClEnter wr_ = new WrMouseListenerWithoutClEnter(_c,_mouseListener);
        getNatComponent().addMouseListener(wr_);
    }

    @Override
    public void addMouseListener(AbsMouseListenerEnt _mouseListener) {
        WrMouseListenerEnt wr_ = new WrMouseListenerEnt(_mouseListener);
        getNatComponent().addMouseListener(wr_);
    }

    public void addMouseListener(AbsMouseListener _mouseListener) {
        WrMouseListener wr_ = new WrMouseListener(_mouseListener);
        getNatComponent().addMouseListener(wr_);
        mapMouse.addEntry(_mouseListener,wr_);
    }

    @Override
    public void addMouseListenerMap(AbsMouseListener _mouseListener) {
        WrMouseListener wr_ = new WrMouseListener(_mouseListener);
        mapMouse.addEntry(_mouseListener,wr_);
    }

    public void addMouseMotionListener(AbsMouseMotionListener _mouseListener) {
        WrMouseMotionListener wr_ = new WrMouseMotionListener(_mouseListener);
        getNatComponent().addMouseMotionListener(wr_);
        mapMouseMotion.addEntry(_mouseListener,wr_);
    }

    @Override
    public void addMouseMotionListenerMap(AbsMouseMotionListener _mouseListener) {
        WrMouseMotionListener wr_ = new WrMouseMotionListener(_mouseListener);
        mapMouseMotion.addEntry(_mouseListener,wr_);
    }

    public void addMouseWheelListener(AbsMouseWheelListener _l) {
        WrMouseWheelListener wr_ = new WrMouseWheelListener(_l);
        getNatComponent().addMouseWheelListener(wr_);
        mapMouseWheel.addEntry(_l,wr_);
    }

    @Override
    public void addMouseWheelListenerMap(AbsMouseWheelListener _l) {
        WrMouseWheelListener wr_ = new WrMouseWheelListener(_l);
        mapMouseWheel.addEntry(_l,wr_);
    }

    public void addKeyListener(AbsKeyListener _l) {
        WrKeyListener wr_ = new WrKeyListener(_l);
        getNatComponent().addKeyListener(wr_);
        mapKey.addEntry(_l,wr_);
    }

    @Override
    public void addKeyListenerMap(AbsKeyListener _l) {
        WrKeyListener wr_ = new WrKeyListener(_l);
        mapKey.addEntry(_l,wr_);
    }

    @Override
    public void addKeyListener(AbsKeyListenerPress _l) {
        WrKeyListenerPress wr_ = new WrKeyListenerPress(_l);
        getNatComponent().addKeyListener(wr_);
    }

    @Override
    public void addKeyListener(AbsKeyListenerReleased _l) {
        WrKeyListenerRel wr_ = new WrKeyListenerRel(_l);
        getNatComponent().addKeyListener(wr_);
    }

    @Override
    public void removeFocusListener(AbsFocusListener _mouseListener) {
        WrFocusListener wr_ = mapFocus.getVal(_mouseListener);
        getNatComponent().removeFocusListener(wr_);
        mapFocus.removeKey(_mouseListener);
    }

    @Override
    public void removeFocusListenerMap(AbsFocusListener _mouseListener) {
        mapFocus.removeKey(_mouseListener);
    }

    public void removeMouseListener(AbsMouseListener _mouseListener) {
        WrMouseListener wr_ = mapMouse.getVal(_mouseListener);
        getNatComponent().removeMouseListener(wr_);
        mapMouse.removeKey(_mouseListener);
    }

    @Override
    public void removeMouseListenerMap(AbsMouseListener _mouseListener) {
        mapMouse.removeKey(_mouseListener);
    }

    @Override
    public void removeMouseListener(AbsMouseListenerIntRel _mouseListener) {
        WrMouseListenerRel wr_ = mapMouseRel.getVal(_mouseListener);
        getNatComponent().removeMouseListener(wr_);
        mapMouseRel.removeKey(_mouseListener);
    }

    public void removeMouseMotionListener(AbsMouseMotionListener _mouseListener) {
        WrMouseMotionListener wr_ = mapMouseMotion.getVal(_mouseListener);
        getNatComponent().removeMouseMotionListener(wr_);
        mapMouseMotion.removeKey(_mouseListener);
    }

    @Override
    public void removeMouseMotionListenerMap(AbsMouseMotionListener _mouseListener) {
        mapMouseMotion.removeKey(_mouseListener);
    }

    public void removeMouseWheelListener(AbsMouseWheelListener _l) {
        WrMouseWheelListener wr_ = mapMouseWheel.getVal(_l);
        getNatComponent().removeMouseWheelListener(wr_);
        mapMouseWheel.removeKey(_l);
    }

    @Override
    public void removeMouseWheelListenerMap(AbsMouseWheelListener _l) {
        mapMouseWheel.removeKey(_l);
    }

    public void removeKeyListener(AbsKeyListener _l) {
        WrKeyListener wr_ = mapKey.getVal(_l);
        getNatComponent().removeKeyListener(wr_);
        mapKey.removeKey(_l);
    }

    @Override
    public void removeKeyListenerMap(AbsKeyListener _l) {
        mapKey.removeKey(_l);
    }

    @Override
    public void registerKeyboardAction(AbsEnabledAction _action, int _a, int _b) {
        WrAbstractAction w_ = (WrAbstractAction)_action;
        getNatComponent().getActionMap().put(_a+","+_b,w_);
        getNatComponent().getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(_a,_b),_a+","+_b);
        actions.put(_a+","+_b,w_);
    }

    @Override
    public void registerKeyboardActionMap(AbsEnabledAction _action, int _a, int _b) {
        WrAbstractAction w_ = (WrAbstractAction)_action;
        actions.put(_a+","+_b,w_);
    }

    @Override
    public void unregisterKeyboardAction(int _a, int _b) {
        getNatComponent().getActionMap().remove(_a+","+_b);
        getNatComponent().getInputMap(JComponent.WHEN_FOCUSED).remove(KeyStroke.getKeyStroke(_a,_b));
        actions.removeKey(_a+","+_b);
    }

    @Override
    public void unregisterKeyboardActionMap(int _a, int _b) {
        actions.removeKey(_a+","+_b);
    }

    @Override
    public StringMap<AbsEnabledAction> getActionsMap() {
        return actions;
    }

    @Override
    public CustList<AbsFocusListener> getFocusListeners() {
        return mapFocus.getKeys();
    }

    public CustList<AbsMouseListener> getMouseListeners() {
        return mapMouse.getKeys();
    }

    @Override
    public CustList<AbsMouseListenerIntRel> getMouseListenersRel() {
        return mapMouseRel.getKeys();
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

    public boolean requestFocusInWindow() {
        return getNatComponent().requestFocusInWindow();
    }

    @Override
    public boolean isFocused() {
        return getNatComponent().isFocusOwner();
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

    public int heightFont(MetaFont _font) {
        return heightFont(ft(_font));
    }

    public int heightFont(Font _font) {
        return getNatComponent().getFontMetrics(_font).getHeight();
    }

    public int stringWidth(MetaFont _font, String _string) {
        return stringWidth(ft(_font),_string);
    }

    public int stringWidth(Font _font, String _string) {
        return getNatComponent().getFontMetrics(_font).stringWidth(_string);
    }

    @Override
    public MetaFont getMetaFont() {
        Font font_ = getFont();
        try {
            return new MetaFont(font_.getFamily(), GuiConstants.fontStyle(font_.isBold(), font_.isItalic()), font_.getSize());
        } catch (Exception e) {
            return null;
        }
    }

    public Font getFont() {
        return getNatComponent().getFont();
    }
    public void setFont(Font _font) {
        getNatComponent().setFont(_font);
    }

    @Override
    public void setNullFont() {
        getNatComponent().setFont(null);
    }

    @Override
    public void setFont(MetaFont _font) {
        setFont(ft(_font));
    }

    private static Font ft(MetaFont _font) {
        return new Font(_font.getFontFamily(), _font.getFont(), _font.getRealSize());
    }

    @Override
    public void setFont(String _name, int _style, int _size) {
        getNatComponent().setFont(new Font(_name,_style,_size));
    }

    public AbsCustComponent getParent() {
        return parent;
    }

    public void setParent(AbsCustComponent _parent) {
        parent = _parent;
    }
    public IdList<AbsCustComponent> getChildren() {
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

    @Override
    public void setHandCursor() {
        getNatComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public Dimension getSize() {
        return getNatComponent().getSize();
    }

    public void setSize(Dimension _dimension) {
        getNatComponent().setSize(_dimension);
    }

    @Override
    public MetaDimension getPreferredSizeValue() {
        Dimension preferredSize_ = getPreferredSize();
        return new MetaDimension(preferredSize_.width,preferredSize_.height);
    }

    public Dimension getPreferredSize() {
        return getNatComponent().getPreferredSize();
    }

    public void setPreferredSize(Dimension _dimension) {
        getNatComponent().setPreferredSize(_dimension);
    }

    @Override
    public void setPreferredSize(MetaDimension _dimension) {
        try {
            setPreferredSize(new Dimension(_dimension.getWidth(), _dimension.getHeight()));
        } catch (Exception e) {
            getNatComponent().setPreferredSize(null);
        }
    }

    @Override
    public void setSize(MetaDimension _dimension) {
        setSize(new Dimension(_dimension.getWidth(), _dimension.getHeight()));
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
        backGroundValue = _color.getRGB();
    }
    public Color getBackground() {
        return getNatComponent().getBackground();
    }

    @Override
    public void setLineBorder(int _color) {
        setLineBorder(DefImage.fullColor(_color));
    }

    @Override
    public void setLineBorder(int _color, int _thick) {
        setLineBorder(DefImage.fullColor(_color),_thick);
    }

    @Override
    public int getBackgroundValue() {
        return backGroundValue;
    }

    @Override
    public int getForegroundValue() {
        return foreGroundValue;
    }

    @Override
    public void setBackground(int _color) {
        setBackground(DefImage.fullColor(_color));
    }

    @Override
    public void setForeground(int _color) {
        setForeground(DefImage.fullColor(_color));
    }

    public void setForeground(Color _color) {
        getNatComponent().setForeground(_color);
        foreGroundValue = _color.getRGB();
    }
    public Color getForeground() {
        return getNatComponent().getForeground();
    }

    public String getToolTipText() {
        return getNatComponent().getToolTipText();
    }

    public void validate() {
        getNatComponent().validate();
    }

    public void revalidate() {
        getNatComponent().revalidate();
    }

    @Override
    public void recalculate() {
        getNatComponent().invalidate();
        getNatComponent().doLayout();
    }

    @Override
    public void top() {
        getNatComponent().setAlignmentY(Component.TOP_ALIGNMENT);
    }

    @Override
    public void bottom() {
        getNatComponent().setAlignmentY(Component.BOTTOM_ALIGNMENT);
    }

    @Override
    public void centerVert() {
        getNatComponent().setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void left() {
        getNatComponent().setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    @Override
    public void right() {
        getNatComponent().setAlignmentX(Component.RIGHT_ALIGNMENT);
    }

    @Override
    public void centerHoriz() {
        getNatComponent().setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    @Override
    public void disabledRichText(boolean _e) {
        getNatComponent().putClientProperty(NO_RICH_TEXT, _e);
    }
}
