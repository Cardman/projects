package code.expressionlanguage.guicompos;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.structs.*;
import code.gui.CustComponent;
import code.util.CustList;

import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public abstract class CustComponentStruct implements Struct {

    private Struct parentComponent = NullStruct.NULL_VALUE;
    private CustList<CustComponentStruct> children = new CustList<CustComponentStruct>();
    private String className;
    private Struct paintEvent = NullStruct.NULL_VALUE;

    protected CustComponentStruct(String _className) {
        className = _className;
    }
    public static void invokeLater(RunnableContextEl _run, Struct _r) {
        if (_r instanceof EventStruct) {
            if (_run.getExecutingOptions().isInvokeDirect()) {
                new Thread((EventStruct) _r).start();
            } else {
                SwingUtilities.invokeLater((EventStruct) _r);
            }

        }
    }
    public static void invokeRunnable(RunnableContextEl _run,Runnable _r) {
        if (_r != null) {
            if (_run.getExecutingOptions().isInvokeDirect()) {
                new Thread(_r).start();
            } else {
                SwingUtilities.invokeLater(_r);
            }
        }
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
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
        return new BooleanStruct(getVisibleComponent().isFocusable());
    }
    public void setFocusable(Struct _focusable) {
        getVisibleComponent().setFocusable(((BooleanStruct)_focusable).getInstance());
    }
    public BooleanStruct isOpaque() {
        return new BooleanStruct(getVisibleComponent().isOpaque());
    }

    public void setOpaque(Struct _b) {
        getVisibleComponent().setOpaque(((BooleanStruct)_b).getInstance());
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
    public int getWidth() {
        return getComponent().getWidth();
    }
    public int getHeight() {
        return getComponent().getHeight();
    }
    public Struct isVisible() {
        return new BooleanStruct(getComponent().isVisible());
    }
    public void setVisible(Struct _b) {
        getComponent().setVisible(((BooleanStruct)_b).getInstance());
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
    public void setPaintEvent(Struct paintEvent) {
        this.paintEvent = paintEvent;
    }

    public BooleanStruct isAutoscrolls(){
        return new BooleanStruct(getComponent().isAutoscrolls());
    }
    public void setAutoscrolls(Struct _autoscrolls) {
        getComponent().setAutoscrolls(((BooleanStruct)_autoscrolls).getInstance());
    }
    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
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
