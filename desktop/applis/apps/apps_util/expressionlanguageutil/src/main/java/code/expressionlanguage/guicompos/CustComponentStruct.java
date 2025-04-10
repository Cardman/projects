package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.gui.AbsCustComponent;
import code.gui.AbsFocusListener;
import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.gui.images.MetaFont;
import code.gui.initialize.AbstractLightProgramInfos;
import code.util.CustList;
import code.util.IdList;
import code.util.StringMap;


public abstract class CustComponentStruct extends WithoutParentIdStruct implements Struct {

    private Struct parentComponent = NullStruct.NULL_VALUE;
    private final IdList<CustComponentStruct> children = new IdList<CustComponentStruct>();
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
                _frames.getCompoFactory().invokeNow((Runnable) _r);
            }

        }
    }
    public Struct isEnabled(){
        return BooleanStruct.of(getComponent().isEnabled());
    }
    public void setEnabled(Struct _enabled){
        getComponent().setEnabled(BooleanStruct.isTrue(_enabled));
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

    protected boolean kept(CustComponentStruct _comp) {
        if (this == _comp) {
            return true;
        }
        CustComponentStruct curThis_ = this;
        while (true) {
            Struct par_ = curThis_.getParentComponent();
            if (par_ == _comp){
                return true;
            }
            if (!(par_ instanceof CustComponentStruct)) {
                break;
            }
            curThis_ = (CustComponentStruct) par_;
        }
        Struct direct_ = _comp.getParentComponent();
        if (direct_ instanceof ContainerStruct) {
            ((ContainerStruct)direct_).move(_comp);
        }
        return false;
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
    public void addMouse(Struct _mouseListener, StackCall _stackCall) {
        if (_mouseListener instanceof AbsMouseListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                getVisibleComponent().addMouseListenerMap((AbsMouseListener) _mouseListener);
            } else {
                getVisibleComponent().addMouseListener((AbsMouseListener) _mouseListener);
            }
        }
        if (_mouseListener instanceof AbsMouseMotionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                getVisibleComponent().addMouseMotionListenerMap((AbsMouseMotionListener) _mouseListener);
            } else {
                getVisibleComponent().addMouseMotionListener((AbsMouseMotionListener) _mouseListener);
            }
        }
    }
    public void addWheel(Struct _wheelListener, StackCall _stackCall) {
        if (_wheelListener instanceof AbsMouseWheelListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                getVisibleComponent().addMouseWheelListenerMap((AbsMouseWheelListener) _wheelListener);
            } else {
                getVisibleComponent().addMouseWheelListener((AbsMouseWheelListener) _wheelListener);
            }
        }
    }
    public void addKeyListener(Struct _l, StackCall _stackCall) {
        if (_l instanceof AbsKeyListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                getVisibleComponent().addKeyListenerMap((AbsKeyListener)_l);
            } else {
                getVisibleComponent().addKeyListener((AbsKeyListener)_l);
            }
        }

    }

    public void addFocusListener(Struct _l, StackCall _stackCall) {
        if (_l instanceof AbsFocusListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                getVisibleComponent().addFocusListenerMap((AbsFocusListener)_l);
            } else {
                getVisibleComponent().addFocusListener((AbsFocusListener)_l);
            }
        }
    }
    public void registerKeyboardAction(Struct _action, Struct _a, Struct _b, StackCall _stackCall) {
        int first_ = ((NumberStruct) _a).intStruct();
        int second_ = ((NumberStruct) _b).intStruct();
        if (_stackCall.getStopper().getLogger() != null) {
            if (_action instanceof EnabledActionStruct) {
                actions.put(first_+","+second_,(EnabledActionStruct)_action);
            } else {
                actions.removeKey(first_+","+second_);
            }
            return;
        }
        if (_action instanceof EnabledActionStruct) {
            getVisibleComponent().registerKeyboardAction(((EnabledActionStruct) _action).getController(),first_,second_);
            actions.put(first_+","+second_,(EnabledActionStruct)_action);
        } else {
            getVisibleComponent().unregisterKeyboardAction(first_,second_);
            actions.removeKey(first_+","+second_);
        }
    }
    public void removeMouse(Struct _mouseListener, StackCall _stackCall) {
        if (_mouseListener instanceof AbsMouseListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                getVisibleComponent().removeMouseListenerMap((AbsMouseListener) _mouseListener);
            } else {
                getVisibleComponent().removeMouseListener((AbsMouseListener) _mouseListener);
            }
        }
        if (_mouseListener instanceof AbsMouseMotionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                getVisibleComponent().removeMouseMotionListenerMap((AbsMouseMotionListener) _mouseListener);
            } else {
                getVisibleComponent().removeMouseMotionListener((AbsMouseMotionListener) _mouseListener);
            }
        }
    }
    public void removeWheel(Struct _wheelListener, StackCall _stackCall) {
        if (_wheelListener instanceof AbsMouseWheelListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                getVisibleComponent().removeMouseWheelListenerMap((AbsMouseWheelListener) _wheelListener);
            } else {
                getVisibleComponent().removeMouseWheelListener((AbsMouseWheelListener) _wheelListener);
            }
        }
    }
    public void removeKeyListener(Struct _l, StackCall _stackCall) {
        if (_l instanceof AbsKeyListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                getVisibleComponent().removeKeyListenerMap((AbsKeyListener)_l);
            } else {
                getVisibleComponent().removeKeyListener((AbsKeyListener)_l);
            }
        }

    }

    public void removeFocusListener(Struct _l, StackCall _stackCall) {
        if (_l instanceof AbsFocusListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                getVisibleComponent().removeFocusListenerMap((AbsFocusListener)_l);
            } else {
                getVisibleComponent().removeFocusListener((AbsFocusListener)_l);
            }
        }

    }
    public void unregisterKeyboardAction(Struct _a, Struct _b, StackCall _stackCall) {
        int first_ = ((NumberStruct) _a).intStruct();
        int second_ = ((NumberStruct) _b).intStruct();
        if (_stackCall.getStopper().getLogger() == null) {
            getVisibleComponent().unregisterKeyboardAction(first_,second_);
        }
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
    public ArrayStruct getFocusListeners(ContextEl _ctx) {
        String aliasFocusListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasFocusListener();
        CustList<AbsFocusListener> focusListeners_ = getVisibleComponent().getFocusListeners();
        CustList<Struct> res_ = new CustList<Struct>();
        int lenBase_ = focusListeners_.size();
        for (int i = 0; i < lenBase_; i++) {
            if (focusListeners_.get(i) instanceof Struct) {
                res_.add((Struct)focusListeners_.get(i));
            }
        }
        return nulls(aliasFocusListener_, res_);
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

    public StringMap<EnabledActionStruct> getActions() {
        return actions;
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
    public IdList<CustComponentStruct> getChildren() {
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

    public void recalculate() {
        getComponent().recalculate();
    }
}
