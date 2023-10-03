package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.AbsCustComponent;
import code.gui.GuiBaseUtil;
import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbstractLightProgramInfos;
import code.util.CustList;
import code.util.StringMap;


public abstract class CustComponentStruct extends WithoutParentIdStruct implements Struct {

    private Struct parentComponent = NullStruct.NULL_VALUE;
    private final CustList<CustComponentStruct> children = new CustList<CustComponentStruct>();
    private final String className;
    private Struct paintEvent = NullStruct.NULL_VALUE;
    private final StringMap<EnabledActionStruct> actions = new StringMap<EnabledActionStruct>();

    protected CustComponentStruct(String _className) {
        className = _className;
    }
    public static void invokeLater(RunnableContextEl _run, AbstractLightProgramInfos _frames, Struct _r) {
        if (_r instanceof Runnable) {
            if (_run.getExecutingOptions().isInvokeDirect()) {
                _run.getCurrentThreadFactory().newStartedThread((Runnable) _r);
            } else {
                GuiBaseUtil.invokeLater((Runnable) _r, _frames);
            }

        }
    }
//    public static void invokeRunnable(RunnableContextEl _run,Runnable _r) {
//        if (_r != null) {
//            if (_run.getExecutingOptions().isInvokeDirect()) {
//                _run.getCurrentThreadFactory().newStartedThread(_r);
//            } else {
//                FrameUtil.invokeLater(_r, null);
//            }
//        }
//    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }
    public Struct getParentComponent() {
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
        return new ColorStruct(getVisibleComponent().getBackgroundValue());
    }

    public void setForeground(Struct _color) {
        if (!(_color instanceof ColorStruct)) {
            return;
        }
        getVisibleComponent().setForeground(((ColorStruct)_color).getColor());
    }
    public Struct getForeground() {
        return new ColorStruct(getVisibleComponent().getForegroundValue());
    }

    public Struct getFont() {
        MetaFont metaFont_ = getComponent().getMetaFont();
        if (metaFont_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return new FontStruct(metaFont_);
    }
    public void setFont(Struct _font) {
        if (!(_font instanceof FontStruct)) {
            getComponent().setNullFont();
        } else {
            getComponent().setFont(((FontStruct)_font).getFont());
        }
    }

    public boolean requestFocus() {
        return getVisibleComponent().requestFocusInWindow();
    }

    public boolean isFocused() {
        return getVisibleComponent().isFocused();
    }
    public void addMouse(Struct _mouseListener) {
        if (_mouseListener instanceof AbsMouseListener) {
            getVisibleComponent().addMouseListener((AbsMouseListener) _mouseListener);
        }
        if (_mouseListener instanceof AbsMouseMotionListener) {
            getVisibleComponent().addMouseMotionListener((AbsMouseMotionListener) _mouseListener);
        }
    }
    public void addWheel(Struct _wheelListener) {
        if (_wheelListener instanceof AbsMouseWheelListener) {
            getVisibleComponent().addMouseWheelListener((AbsMouseWheelListener) _wheelListener);
        }
    }
    public void addKeyListener(Struct _l) {
        if (_l instanceof AbsKeyListener) {
            getVisibleComponent().addKeyListener((AbsKeyListener)_l);
        }

    }
    public void registerKeyboardAction(Struct _action, Struct _a, Struct _b) {
        int first_ = ((NumberStruct) _a).intStruct();
        int second_ = ((NumberStruct) _b).intStruct();
        if (_action instanceof EnabledActionStruct) {
            getVisibleComponent().registerKeyboardAction(((EnabledActionStruct) _action).getController(),first_,second_);
            actions.put(first_+","+second_,(EnabledActionStruct)_action);
        } else {
            getVisibleComponent().unregisterKeyboardAction(first_,second_);
            actions.removeKey(first_+","+second_);
        }
    }
    public void removeMouse(Struct _mouseListener) {
        if (_mouseListener instanceof AbsMouseListener) {
            getVisibleComponent().removeMouseListener((AbsMouseListener) _mouseListener);
        }
        if (_mouseListener instanceof AbsMouseMotionListener) {
            getVisibleComponent().removeMouseMotionListener((AbsMouseMotionListener) _mouseListener);
        }
    }
    public void removeWheel(Struct _wheelListener) {
        if (_wheelListener instanceof AbsMouseWheelListener) {
            getVisibleComponent().removeMouseWheelListener((AbsMouseWheelListener) _wheelListener);
        }
    }
    public void removeKeyListener(Struct _l) {
        if (_l instanceof AbsKeyListener) {
            getVisibleComponent().removeKeyListener((AbsKeyListener)_l);
        }

    }

    public void unregisterKeyboardAction(Struct _a, Struct _b) {
        int first_ = ((NumberStruct) _a).intStruct();
        int second_ = ((NumberStruct) _b).intStruct();
        getVisibleComponent().unregisterKeyboardAction(first_,second_);
        actions.removeKey(first_+","+second_);
    }
    public ArrayStruct getMouses(ContextEl _ctx) {
        String aliasMouseListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasMouseListener();
        CustList<AbsMouseListener> mouseListeners_ = getVisibleComponent().getMouseListeners();
        CustList<AbsMouseMotionListener> mouseMotionListeners_ = getVisibleComponent().getMouseMotionListeners();
        CustList<Struct> res_ = new CustList<Struct>();
        int lenBase_ = mouseListeners_.size();
        for (int i = 0; i < lenBase_; i++) {
            if (mouseListeners_.get(i) instanceof Struct) {
                res_.add((Struct)mouseListeners_.get(i));
            }
        }
        int lenAdv_ = mouseMotionListeners_.size();
        for (int i = 0; i < lenAdv_; i++) {
            if (mouseMotionListeners_.get(i) instanceof Struct) {
                res_.add((Struct)mouseMotionListeners_.get(i));
            }
        }
        return nulls(aliasMouseListener_, res_);
    }
    public ArrayStruct getWheels(ContextEl _ctx) {
        String aliasWheelListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasWheelListener();
        CustList<AbsMouseWheelListener> mouseWheelListeners_ = getVisibleComponent().getMouseWheelListeners();
        CustList<Struct> res_ = new CustList<Struct>();
        int lenBase_ = mouseWheelListeners_.size();
        for (int i = 0; i < lenBase_; i++) {
            if (mouseWheelListeners_.get(i) instanceof Struct) {
                res_.add((Struct)mouseWheelListeners_.get(i));
            }
        }
        return nulls(aliasWheelListener_, res_);
    }
    public ArrayStruct getKeyListeners(ContextEl _ctx) {
        String aliasKeyListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasKeyListener();
        CustList<AbsKeyListener> keyListeners_ = getVisibleComponent().getKeyListeners();
        CustList<Struct> res_ = new CustList<Struct>();
        int lenBase_ = keyListeners_.size();
        for (int i = 0; i < lenBase_; i++) {
            if (keyListeners_.get(i) instanceof Struct) {
                res_.add((Struct)keyListeners_.get(i));
            }
        }
        return nulls(aliasKeyListener_, res_);
    }
    public ArrayStruct getCommands(ContextEl _ctx) {
        String aliasCommand_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasCommand();
        CustList<Struct> res_ = new CustList<Struct>();
        int lenBase_ = actions.size();
        for (int i = 0; i < lenBase_; i++) {
            CommandStruct c_ = new CommandStruct(aliasCommand_);
            c_.setBinding(new StringStruct(actions.getKey(i)));
            c_.setAction(actions.getValue(i));
            res_.add(c_);
        }
        return nulls(aliasCommand_, res_);
    }

    public static ArrayStruct nulls(String _cl, CustList<Struct> _ls) {
        int len_ = _ls.size();
        ArrayStruct arr_ = new ArrayStruct(len_, StringExpUtil.getPrettyArrayType(_cl));
        for (int i = 0; i < len_; i++) {
            arr_.set(i, _ls.get(i));
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
    protected AbsCustComponent getVisibleComponent() {
        return getComponent();
    }
    protected abstract AbsCustComponent getComponent();

    public void top(){
        getComponent().top();
    }
    public void bottom(){
        getComponent().bottom();
    }
    public void centerVert(){
        getComponent().centerVert();
    }
    public void left(){
        getComponent().left();
    }
    public void right(){
        getComponent().right();
    }
    public void centerHoriz(){
        getComponent().centerHoriz();
    }
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

    public Struct getPreferredSize() {
        return new DimensionStruct(getComponent().getPreferredSizeValue());
    }

    public void setPreferredSize(Struct _d) {
        if (!(_d instanceof DimensionStruct)) {
            setPreferredSize((MetaDimension)null);
            return;
        }
        DimensionStruct d_ = (DimensionStruct)_d;
        setPreferredSize(d_.getDimension());
    }

    public void setSize(Struct _d) {
        if (!(_d instanceof DimensionStruct)) {
            return;
        }
        DimensionStruct d_ = (DimensionStruct)_d;
        getComponent().setSize(d_.getDimension());
    }

    protected void setPreferredSize(MetaDimension _d) {
        getComponent().setPreferredSize(_d);
    }
}
