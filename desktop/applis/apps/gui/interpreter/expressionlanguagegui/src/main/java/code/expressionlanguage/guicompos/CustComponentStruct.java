package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.structs.*;
import code.gui.CustComponent;
import code.util.CustList;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public abstract class CustComponentStruct extends WithoutParentIdStruct implements Struct {

    private Struct parentComponent = NullStruct.NULL_VALUE;
    private final CustList<CustComponentStruct> children = new CustList<CustComponentStruct>();
    private final String className;
    private Struct paintEvent = NullStruct.NULL_VALUE;

    protected CustComponentStruct(String _className) {
        className = _className;
    }
    public static void invokeLater(RunnableContextEl _run, Struct _r) {
        if (_r instanceof Runnable) {
            if (_run.getExecutingOptions().isInvokeDirect()) {
                _run.getThreadFactory().newStartedThread((Runnable) _r);
            } else {
                CustComponent.invokeLater((Runnable) _r);
            }

        }
    }
    public static void invokeRunnable(RunnableContextEl _run,Runnable _r) {
        if (_r != null) {
            if (_run.getExecutingOptions().isInvokeDirect()) {
                _run.getThreadFactory().newStartedThread(_r);
            } else {
                CustComponent.invokeLater(_r);
            }
        }
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }
    protected Struct getParentComponent() {
        return parentComponent;
    }
    protected void setParentComponent(CustComponentStruct _parent) {
        parentComponent = _parent;
    }
    protected void setNullParentComponent() {
        parentComponent = NullStruct.NULL_VALUE;
    }

    public void setLineBorder(Struct _color) {
        if (!(_color instanceof ColorStruct)) {
            return;
        }
        getComponent().setLineBorder(((ColorStruct)_color).getColor());
    }

    public void setLineBorder(Struct _color, Struct _thick) {
        if (!(_color instanceof ColorStruct)) {
            return;
        }
        getComponent().setLineBorder(((ColorStruct)_color).getColor(),((NumberStruct)_thick).intStruct());
    }

    public void setTitledBorder(Struct _title) {
        if (!(_title instanceof StringStruct)) {
            return;
        }
        getComponent().setTitledBorder(((StringStruct)_title).getInstance());
    }

    public void setLoweredBorder() {
        getComponent().setLoweredBorder();
    }

    public void setRaisedBorder() {
        getComponent().setRaisedBorder();
    }

    public Struct getToolTipText() {
        String t_ = getVisibleComponent().getToolTipText();
        if (t_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(t_);
    }

    public void setToolTipText(Struct _title) {
        if (_title instanceof StringStruct) {
            getVisibleComponent().setToolTipText(((StringStruct)_title).getInstance());
        } else {
            getVisibleComponent().setToolTipText(null);
        }
    }

    public BooleanStruct isFocusable() {
        return BooleanStruct.of(getVisibleComponent().isFocusable());
    }
    public void setFocusable(Struct _focusable) {
        getVisibleComponent().setFocusable(BooleanStruct.isTrue(_focusable));
    }
    public BooleanStruct isOpaque() {
        return BooleanStruct.of(getVisibleComponent().isOpaque());
    }

    public void setOpaque(Struct _b) {
        getVisibleComponent().setOpaque(BooleanStruct.isTrue(_b));
    }

    public IntStruct getXcoords() {
        return new IntStruct(getComponent().getXcoords());
    }

    public IntStruct getYcoords() {
        return new IntStruct(getComponent().getYcoords());
    }
    public void setLocation(Struct _x, Struct _y) {
        getComponent().setLocation(((NumberStruct)_x).intStruct(),((NumberStruct)_y).intStruct());
    }

    public void setBackground(Struct _color) {
        if (!(_color instanceof ColorStruct)) {
            return;
        }
        getVisibleComponent().setBackground(((ColorStruct)_color).getColor());
    }
    public Struct getBackground() {
        Color c_ = getVisibleComponent().getBackground();
        if (c_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new ColorStruct(c_);
    }

    public void setForeground(Struct _color) {
        if (!(_color instanceof ColorStruct)) {
            return;
        }
        getVisibleComponent().setForeground(((ColorStruct)_color).getColor());
    }
    public Struct getForeground() {
        Color c_ = getVisibleComponent().getForeground();
        if (c_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new ColorStruct(c_);
    }

    public FontStruct getFont() {
        return new FontStruct(getComponent().getFont());
    }
    public void setFont(Struct _font) {
        if (!(_font instanceof FontStruct)) {
            getComponent().setFont(null);
        } else {
            getComponent().setFont(((FontStruct)_font).getFont());
        }
    }

    public void requestFocus() {
        getVisibleComponent().requestFocus();
    }
    public void addMouse(Struct _mouseListener) {
        if (_mouseListener instanceof MouseListener) {
            getVisibleComponent().addMouseListener((MouseListener) _mouseListener);
        }
        if (_mouseListener instanceof MouseMotionListener) {
            getVisibleComponent().addMouseMotionListener((MouseMotionListener) _mouseListener);
        }
    }
    public void addWheel(Struct _wheelListener) {
        if (_wheelListener instanceof MouseWheelListener) {
            getVisibleComponent().addMouseWheelListener((MouseWheelListener) _wheelListener);
        }
    }
    public void addKeyListener(Struct _l) {
        if (_l instanceof KeyListener) {
            getVisibleComponent().addKeyListener((KeyListener)_l);
        }

    }
    public void removeMouse(Struct _mouseListener) {
        if (_mouseListener instanceof MouseListener) {
            getVisibleComponent().removeMouseListener((MouseListener) _mouseListener);
        }
        if (_mouseListener instanceof MouseMotionListener) {
            getVisibleComponent().removeMouseMotionListener((MouseMotionListener) _mouseListener);
        }
    }
    public void removeWheel(Struct _wheelListener) {
        if (_wheelListener instanceof MouseWheelListener) {
            getVisibleComponent().removeMouseWheelListener((MouseWheelListener) _wheelListener);
        }
    }
    public void removeKeyListener(Struct _l) {
        if (_l instanceof KeyListener) {
            getVisibleComponent().removeKeyListener((KeyListener)_l);
        }

    }
    public ArrayStruct getMouses(ContextEl _ctx) {
        String aliasMouseListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasMouseListener();
        MouseListener[] mouseListeners_ = getVisibleComponent().getMouseListeners();
        MouseMotionListener[] mouseMotionListeners_ = getVisibleComponent().getMouseMotionListeners();
        ArrayStruct arr_ = new ArrayStruct(mouseListeners_.length+mouseMotionListeners_.length, StringExpUtil.getPrettyArrayType(aliasMouseListener_));
        int lenBase_ = mouseListeners_.length;
        for (int i = 0; i < lenBase_; i++) {
            if (mouseListeners_[i] instanceof Struct) {
                arr_.set(i,(Struct)mouseListeners_[i]);
            }
        }
        int lenAdv_ = mouseMotionListeners_.length;
        for (int i = 0; i < lenAdv_; i++) {
            if (mouseMotionListeners_[i] instanceof Struct) {
                arr_.set(i+lenBase_,(Struct)mouseMotionListeners_[i]);
            }
        }
        return arr_;
    }
    public ArrayStruct getWheels(ContextEl _ctx) {
        String aliasWheelListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasWheelListener();
        MouseWheelListener[] mouseWheelListeners_ = getVisibleComponent().getMouseWheelListeners();
        ArrayStruct arr_ = new ArrayStruct(mouseWheelListeners_.length, StringExpUtil.getPrettyArrayType(aliasWheelListener_));
        int lenBase_ = mouseWheelListeners_.length;
        for (int i = 0; i < lenBase_; i++) {
            if (mouseWheelListeners_[i] instanceof Struct) {
                arr_.set(i,(Struct)mouseWheelListeners_[i]);
            }
        }
        return arr_;
    }
    public ArrayStruct getKeyListeners(ContextEl _ctx) {
        String aliasKeyListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasKeyListener();
        KeyListener[] keyListeners_ = getVisibleComponent().getKeyListeners();
        ArrayStruct arr_ = new ArrayStruct(keyListeners_.length, StringExpUtil.getPrettyArrayType(aliasKeyListener_));
        int lenBase_ = keyListeners_.length;
        for (int i = 0; i < lenBase_; i++) {
            if (keyListeners_[i] instanceof Struct) {
                arr_.set(i,(Struct)keyListeners_[i]);
            }
        }
        return arr_;
    }
    public int getWidth() {
        return getComponent().getWidth();
    }
    public int getHeight() {
        return getComponent().getHeight();
    }
    public Struct isVisible() {
        return BooleanStruct.of(getComponent().isVisible());
    }
    public void setVisible(Struct _b) {
        getComponent().setVisible(BooleanStruct.isTrue(_b));
    }
    protected CustComponent getVisibleComponent() {
        return getComponent();
    }
    protected abstract CustComponent getComponent();

    public CustList<CustComponentStruct> getChildren() {
        return children;
    }

    public Struct getPaintEvent() {
        return paintEvent;
    }
    public Struct getNext() {
        Struct parent_ = getParentComponent();
        if (!(parent_ instanceof PanelStruct)) {
            return NullStruct.NULL_VALUE;
        }
        CustList<CustComponentStruct> children_ = ((PanelStruct)parent_).getChildren();
        int count_ = children_.size();
        int i_ = 0;
        while (true) {
            if (children_.get(i_) == this) {
                if (i_ + 1 >= count_) {
                    return NullStruct.NULL_VALUE;
                }
                return children_.get(i_ + 1);
            }
            i_++;
        }
    }
    public void setPaintEvent(Struct _paintEvent) {
        this.paintEvent = _paintEvent;
    }

    public BooleanStruct isAutoscrolls(){
        return BooleanStruct.of(getComponent().isAutoscrolls());
    }
    public void setAutoscrolls(Struct _autoscrolls) {
        getComponent().setAutoscrolls(BooleanStruct.isTrue(_autoscrolls));
    }

    protected Struct getPreferredSize() {
        return new DimensionStruct(getComponent().getPreferredSize());
    }

    protected void setPreferredSize(Struct _d) {
        if (!(_d instanceof DimensionStruct)) {
            setPreferredSize((Dimension)null);
            return;
        }
        DimensionStruct d_ = (DimensionStruct)_d;
        setPreferredSize(d_.getDimension());
    }

    protected void setSize(Struct _d) {
        if (!(_d instanceof DimensionStruct)) {
            return;
        }
        DimensionStruct d_ = (DimensionStruct)_d;
        getComponent().setSize(d_.getDimension());
    }
    protected void setPreferredSize(Dimension _d) {
        getComponent().setPreferredSize(_d);
    }
}
