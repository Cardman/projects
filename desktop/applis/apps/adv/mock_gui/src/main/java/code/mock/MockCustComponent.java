package code.mock;

import code.gui.*;
import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.util.CustList;
import code.util.IdList;
import code.util.StringMap;
import code.util.core.NumberUtil;

public abstract class MockCustComponent implements AbsCustComponent {
    private String toolTipText = "";
    private MockPosition vertical = MockPosition.CENTER;
    private MockPosition horizontal = MockPosition.CENTER;
    private AbsCustComponent parent;
    private MetaFont metaFont = new MetaFont("",1,1);
    private boolean autoscrolls;
    private boolean visible = true;
    private boolean accessible = true;
    private boolean focusable = true;
    private boolean focused;
    private boolean opaque;
    private int xcoords;
    private int ycoords;
    private int width;
    private int height;
    private boolean prefSizeSet;
    private MetaDimension prefSize;
    private int foreground;
    private int background;
    private String border="";
    private final IdList<AbsCustComponent> children = new IdList<AbsCustComponent>();
    private final IdList<AbsFocusListener> focusListeners = new IdList<AbsFocusListener>();
    private final IdList<AbsMouseListener> mouseListeners = new IdList<AbsMouseListener>();
    private final IdList<AbsMouseListenerPresRel> mousePresRelListeners = new IdList<AbsMouseListenerPresRel>();
    private final IdList<AbsMouseListenerIntRel> mouseIntRelListeners = new IdList<AbsMouseListenerIntRel>();
    private final IdList<AbsMouseListenerEnt> mouseEntListeners = new IdList<AbsMouseListenerEnt>();
    private final IdList<AbsMouseListenerWithoutClick> mouseWithoutClickListeners = new IdList<AbsMouseListenerWithoutClick>();
    private final IdList<AbsMouseListenerWithoutClickPr> mouseWithoutClickPrListeners = new IdList<AbsMouseListenerWithoutClickPr>();
    private final IdList<AbsMouseMotionListener> mouseMotionListeners = new IdList<AbsMouseMotionListener>();
    private final IdList<AbsMouseWheelListener> mouseWheelListeners = new IdList<AbsMouseWheelListener>();
    private final IdList<AbsKeyListener> keyListeners = new IdList<AbsKeyListener>();
    private final IdList<AbsKeyListenerPress> keyPressListeners = new IdList<AbsKeyListenerPress>();
    private final IdList<AbsKeyListenerReleased> keyReleasedListeners = new IdList<AbsKeyListenerReleased>();
    private final StringMap<AbsEnabledAction> actions = new StringMap<AbsEnabledAction>();
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean _en) {
        this.enabled = _en;
    }

    @Override
    public boolean isAutoscrolls() {
        return autoscrolls;
    }

    @Override
    public void setAutoscrolls(boolean _b) {
        autoscrolls = _b;
    }

    @Override
    public void addFocusListener(AbsFocusListener _l) {
        addFocusListenerMap(_l);
    }

    @Override
    public void addFocusListenerMap(AbsFocusListener _l) {
        focusListeners.add(_l);
    }

    @Override
    public void addMouseListener(AbsMouseListener _l) {
        addMouseListenerMap(_l);
    }

    @Override
    public void addMouseListenerMap(AbsMouseListener _l) {
        mouseListeners.add(_l);
    }

    @Override
    public void addMouseListener(AbsMouseListenerPresRel _l) {
        mousePresRelListeners.add(_l);
    }

    @Override
    public void addMouseListener(AbsMouseListenerIntRel _l) {
        mouseIntRelListeners.add(_l);
    }

    @Override
    public void addMouseListener(AbsMouseListenerEnt _l) {
        mouseEntListeners.add(_l);
    }

    @Override
    public void addMouseListener(AbsMouseListenerWithoutClick _l) {
        mouseWithoutClickListeners.add(_l);
    }

    @Override
    public void addMouseListener(AbsMouseListenerWithoutClickPr _l) {
        mouseWithoutClickPrListeners.add(_l);
    }

    @Override
    public void addMouseMotionListener(AbsMouseMotionListener _l) {
        addMouseMotionListenerMap(_l);
    }

    @Override
    public void addMouseMotionListenerMap(AbsMouseMotionListener _l) {
        mouseMotionListeners.add(_l);
    }

    @Override
    public void addMouseWheelListener(AbsMouseWheelListener _l) {
        addMouseWheelListenerMap(_l);
    }

    @Override
    public void addMouseWheelListenerMap(AbsMouseWheelListener _l) {
        mouseWheelListeners.add(_l);
    }

    @Override
    public void addKeyListener(AbsKeyListener _l) {
        addKeyListenerMap(_l);
    }

    @Override
    public void addKeyListenerMap(AbsKeyListener _l) {
        keyListeners.add(_l);
    }

    @Override
    public void addKeyListener(AbsKeyListenerPress _l) {
        keyPressListeners.add(_l);
    }

    @Override
    public void addKeyListener(AbsKeyListenerReleased _l) {
        keyReleasedListeners.add(_l);
    }

    @Override
    public void removeFocusListener(AbsFocusListener _l) {
        removeFocusListenerMap(_l);
    }

    @Override
    public void removeFocusListenerMap(AbsFocusListener _l) {
        focusListeners.removeObj(_l);
    }

    @Override
    public void removeMouseListener(AbsMouseListener _l) {
        removeMouseListenerMap(_l);
    }

    @Override
    public void removeMouseListenerMap(AbsMouseListener _l) {
        mouseListeners.removeObj(_l);
    }

    @Override
    public void removeMouseListener(AbsMouseListenerIntRel _l) {
        mouseIntRelListeners.removeObj(_l);
    }

    @Override
    public void removeMouseMotionListener(AbsMouseMotionListener _l) {
        removeMouseMotionListenerMap(_l);
    }

    @Override
    public void removeMouseMotionListenerMap(AbsMouseMotionListener _l) {
        mouseMotionListeners.removeObj(_l);
    }

    @Override
    public void removeMouseWheelListener(AbsMouseWheelListener _l) {
        removeMouseWheelListenerMap(_l);
    }

    @Override
    public void removeMouseWheelListenerMap(AbsMouseWheelListener _l) {
        mouseWheelListeners.removeObj(_l);
    }

    @Override
    public void removeKeyListener(AbsKeyListener _l) {
        removeKeyListenerMap(_l);
    }

    @Override
    public void removeKeyListenerMap(AbsKeyListener _l) {
        keyListeners.removeObj(_l);
    }

    public IdList<AbsMouseListenerWithoutClick> getMouseWithoutClickListeners() {
        return mouseWithoutClickListeners;
    }

    public IdList<AbsMouseListenerWithoutClickPr> getMouseWithoutClickPrListeners() {
        return mouseWithoutClickPrListeners;
    }

    public IdList<AbsMouseListenerEnt> getMouseEntListeners() {
        return mouseEntListeners;
    }

    public IdList<AbsMouseListenerPresRel> getMousePresRelListeners() {
        return mousePresRelListeners;
    }

    public IdList<AbsMouseListenerIntRel> getMouseIntRelListeners() {
        return mouseIntRelListeners;
    }

    public IdList<AbsKeyListenerPress> getKeyPressListeners() {
        return keyPressListeners;
    }

    public IdList<AbsKeyListenerReleased> getKeyReleasedListeners() {
        return keyReleasedListeners;
    }

    @Override
    public IdList<AbsFocusListener> getFocusListeners() {
        return focusListeners;
    }

    @Override
    public CustList<AbsMouseListener> getMouseListeners() {
        return mouseListeners;
    }

    @Override
    public CustList<AbsMouseListenerIntRel> getMouseListenersRel() {
        return getMouseIntRelListeners();
    }

    @Override
    public CustList<AbsMouseMotionListener> getMouseMotionListeners() {
        return mouseMotionListeners;
    }

    @Override
    public CustList<AbsMouseWheelListener> getMouseWheelListeners() {
        return mouseWheelListeners;
    }

    @Override
    public CustList<AbsKeyListener> getKeyListeners() {
        return keyListeners;
    }

    @Override
    public void registerKeyboardAction(AbsEnabledAction _action, int _a, int _b) {
        registerKeyboardActionMap(_action, _a, _b);
    }

    @Override
    public void registerKeyboardActionMap(AbsEnabledAction _action, int _a, int _b) {
        actions.put(_a+","+_b,_action);
    }

    @Override
    public void unregisterKeyboardAction(int _a, int _b) {
        unregisterKeyboardActionMap(_a, _b);
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
    public boolean requestFocusInWindow() {
        if (isFocusable()) {
            accessible = true;
            setFocused(true);
            return true;
        }
        setFocused(false);
        return false;
    }

    @Override
    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean _f) {
        this.focused = _f;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean _b) {
        visible = _b;
    }
    public CustList<MockCustComponent> getAccessible() {
        CustList<MockCustComponent> acc_ = new CustList<MockCustComponent>();
        if (!isDeepAccessible()) {
            return acc_;
        }
        for (AbsCustComponent m: getChildren()) {
            if (((MockCustComponent)m).isAccessible()) {
                acc_.add((MockCustComponent) m);
            }
        }
        return acc_;
    }
    public IdList<AbsCustComponent> getTreeAccessible() {
        IdList<AbsCustComponent> acc_ = new IdList<AbsCustComponent>();
        if (!isDeepAccessible()) {
            return acc_;
        }
        MockCustComponent current_ = this;
        while (current_ != null) {
            AbsCustComponent child_ = current_.child(0);
            if (!specialInput(current_) && child_ instanceof MockCustComponent) {
                current_ = (MockCustComponent) child_;
                continue;
            }
            if (current_.isDeepAccessible()&& candidate(current_)) {
                acc_.add(current_);
            }
            while (current_ != null) {
                AbsCustComponent next_ = current_.next();
                if (next_ instanceof MockCustComponent) {
                    current_ = (MockCustComponent) next_;
                    break;
                }
                current_ = current_.parent(this);
            }
        }
        return acc_;
    }

    private boolean candidate(MockCustComponent _current) {
        return !(_current instanceof AbsContainer) && labelWithEvent(_current) || specialInput(_current);
    }

    private boolean specialInput(MockCustComponent _current) {
        return _current instanceof MockPanel && ((MockPanel) _current).getLayout() == MockLayout.LEAF || _current instanceof MockScrollPane && ((MockScrollPane) _current).isLeafCompo();
    }

    private boolean labelWithEvent(MockCustComponent _current) {
        return _current instanceof MockInput||_current instanceof MockTreeGui||_current instanceof MockTableGui || _current.eventCount() > 0;
    }
    private int eventCount() {
        return mouseListeners.size()+mousePresRelListeners.size()+mouseIntRelListeners.size()+mouseEntListeners.size()+mouseWithoutClickListeners.size()+mouseWithoutClickPrListeners.size()
                +mouseMotionListeners.size()+mouseWheelListeners.size()+keyListeners.size()+keyPressListeners.size()+keyReleasedListeners.size();
    }
    private MockCustComponent parent(MockCustComponent _root) {
        AbsCustComponent par_ = getParent();
        if (!(par_ instanceof MockCustComponent)) {
            return null;
        }
        if (par_ == _root) {
            return null;
        }
        return (MockCustComponent) par_;
    }
    private AbsCustComponent next() {
        AbsCustComponent par_ = getParent();
        if (!(par_ instanceof MockCustComponent)) {
            return null;
        }
        return ((MockCustComponent)par_).child(((MockCustComponent)par_).children.indexOfObj(this)+1);
    }
    private AbsCustComponent child(int _index) {
        if (getChildren().isValidIndex(_index)) {
            return getChildren().get(_index);
        }
        return null;
    }
    public CustList<MockCustComponent> getVisible() {
        CustList<MockCustComponent> acc_ = new CustList<MockCustComponent>();
        if (!isDeepVisible()) {
            return acc_;
        }
        for (AbsCustComponent m: getChildren()) {
            if (m.isVisible()) {
                acc_.add((MockCustComponent) m);
            }
        }
        return acc_;
    }
    public boolean isDeepAccessible() {
        AbsCustComponent current_ = this;
        while (current_ instanceof MockCustComponent) {
            if (!((MockCustComponent)current_).isAccessible()) {
                return false;
            }
            current_ = current_.getParent();
        }
        return true;
    }
    public boolean isDeepVisible() {
        AbsCustComponent current_ = this;
        while (current_ != null) {
            if (!current_.isVisible()) {
                return false;
            }
            current_ = current_.getParent();
        }
        return true;
    }

    public boolean isAccessible() {
        if (getHeight() * getWidth() == 0) {
            return false;
        }
        if (!isVisible()) {
            return false;
        }
        return accessible;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public int heightFont() {
        return heightFont(metaFont);
    }

    public int heightFont(MetaFont _font) {
        return heightFontStr(_font);
    }

    public static int heightFontStr(MetaFont _font) {
        if (_font == null) {
            return 1;
        }
        return _font.getRealSize();
    }

    public int stringWidth(MetaFont _font, String _s) {
        return strWidth(_font, _s);
    }

    public static int strWidth(MetaFont _font, String _s) {
        if (_font == null) {
            return _s.length();
        }
        return _s.length()*_font.getRealSize();
    }

    @Override
    public MetaFont getMetaFont() {
        return metaFont;
    }

    @Override
    public void setNullFont() {
        metaFont = null;
    }

    @Override
    public void setFont(MetaFont _f) {
        metaFont = _f;
    }

    @Override
    public void setFont(String _s, int _style, int _size) {
        setFont(new MetaFont(_s,_style,_size));
    }

    @Override
    public AbsCustComponent getParent() {
        return parent;
    }

    @Override
    public CustList<AbsCustComponent> getChildren() {
        return children;
    }

    @Override
    public void setParent(AbsCustComponent _p) {
        parent = _p;
    }

    @Override
    public void setLineBorder(int _color) {
        border = "line "+_color;
    }

    @Override
    public void setLineBorder(int _color, int _thick) {
        border = "line "+_color+" "+_thick;
    }

    @Override
    public void setTitledBorder(String _title) {
        border = "title "+_title;
    }

    @Override
    public void setLoweredBorder() {
        border = "lower";
    }

    @Override
    public void setRaisedBorder() {
        border = "raised";
    }

    @Override
    public void setToolTipText(String _title) {
        toolTipText = _title;
    }

    @Override
    public void disabledRichText(boolean _e) {
        setHandCursor();
    }

    @Override
    public void setHandCursor() {
        setFont(getMetaFont());
    }

    @Override
    public void setSize(MetaDimension _d) {
        height = _d.getHeight();
        width = _d.getWidth();
    }

    @Override
    public MetaDimension getPreferredSizeValue() {
        if (prefSizeSet) {
            return prefSize;
        }
        if (!isDeepVisible()) {
            MetaDimension metaDimension_ = new MetaDimension(0, 0);
            prefSize = metaDimension_;
            prefSizeSet = true;
            return metaDimension_;
        }
        MetaDimension metaDimension_ = new MetaDimension(NumberUtil.max(1, width), NumberUtil.max(1, height));
        prefSize = metaDimension_;
        prefSizeSet = true;
        return metaDimension_;
    }

    @Override
    public void setPreferredSize(MetaDimension _d) {
        if (_d == null) {
            prefSizeSet = false;
        } else {
            prefSize = _d;
            prefSizeSet = true;
        }
    }

    @Override
    public boolean isFocusable() {
        return focusable;
    }

    @Override
    public void setFocusable(boolean _f) {
        focusable = _f;
        if (!_f) {
            accessible = false;
        }
    }

    @Override
    public boolean isOpaque() {
        return opaque;
    }

    @Override
    public void setOpaque(boolean _b) {
        opaque = _b;
    }

    @Override
    public int getXcoords() {
        return xcoords;
    }

    @Override
    public int getYcoords() {
        return ycoords;
    }

    @Override
    public void setLocation(int _x, int _y) {
        xcoords = _x;
        ycoords = _y;
    }

    @Override
    public void setBackground(int _color) {
        background = _color;
    }

    @Override
    public void setBackground(AbsCustComponent _c) {
        setBackground(_c.getBackgroundValue());
    }

    @Override
    public int getBackgroundValue() {
        return background;
    }

    @Override
    public void setForeground(int _color) {
        foreground = _color;
    }

    @Override
    public void setForeground(AbsCustComponent _c) {
        setForeground(_c.getForegroundValue());
    }

    @Override
    public int getForegroundValue() {
        return foreground;
    }

    @Override
    public String getToolTipText() {
        return toolTipText;
    }

    @Override
    public void validate() {
        setPreferredSize(getPreferredSizeValue());
    }

    @Override
    public void revalidate() {
        setPreferredSize(getPreferredSizeValue());
    }

    @Override
    public void recalculate() {
        AbsCustComponent parent_ = getParent();
        if (parent_ instanceof MockPanel && ((MockPanel)parent_).getLayout() == MockLayout.ABSOLUTE) {
            return;
        }
        if (parent_ instanceof MockCustComponent) {
            setSize(new MetaDimension(parent_.getWidth(), parent_.getHeight()));
        } else {
            setSize(getPreferredSizeValue());
        }
    }

    @Override
    public void top() {
        vertical = MockPosition.LEFT;
    }

    @Override
    public void bottom() {
        vertical = MockPosition.RIGHT;
    }

    @Override
    public void centerVert() {
        vertical = MockPosition.CENTER;
    }

    @Override
    public void left() {
        horizontal = MockPosition.LEFT;
    }

    @Override
    public void right() {
        horizontal = MockPosition.RIGHT;
    }

    @Override
    public void centerHoriz() {
        horizontal = MockPosition.CENTER;
    }

    public String getBorder() {
        return border;
    }

    public MockPosition getHorizontal() {
        return horizontal;
    }

    public MockPosition getVertical() {
        return vertical;
    }
}
