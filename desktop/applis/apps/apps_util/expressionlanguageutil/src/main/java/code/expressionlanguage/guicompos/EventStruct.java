package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.gui.*;
import code.gui.events.*;
import code.util.CustList;
import code.util.StringList;

public final class EventStruct implements WithParentStruct,EnumerableStruct,
        AbsAdvActionListener,Runnable, AbsMouseListener, AbsWindowListener,ListSelection,
        AbsKeyListener,AbsChangeListener,AbsShortListTree,AbsListSelectionListener,
        AbsMouseMotionListener, AbsMouseWheelListener{

    private final String className;

    private final CustList<ClassFieldStruct> fields;
    private final CommonExecutionInfos executionInfos;
    private final StringList args;

    private Struct parent;

    private final String name;
    private final int ordinal;
    private final String parentClassName;

    public EventStruct(RunnableContextEl _original, String _className,
                       String _name, int _ordinal,
                       CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName) {
        name = _name;
        ordinal = _ordinal;
        className = _className;
        fields = _fields;
        parent = _parent;
        parentClassName = _parentClassName;
        executionInfos = _original.getExecutionInfos();
        args = _original.getArgs();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }

    @Override
    public Struct getParent() {
        return parent;
    }

    @Override
    public String getParentClassName() {
        return parentClassName;
    }

    @Override
    public void setParent(Struct _parent) {
        parent = _parent;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
    @Override
    public long randCode() {
        return NumParsers.randCode(className);
    }

    @Override
    public void action(AbsCtrlKeyState _state, String _command) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasActionEvent();
        ActionEventStruct a_ = new ActionEventStruct(actEv_,_state,_command);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getActionListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getActionPerformed(),args_);
    }

    @Override
    public void run() {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        invoke(r_, ((LgNamesWithNewAliases) r_.getStandards()).getExecutingBlocks().getRunnableType(), ((LgNamesWithNewAliases) r_.getStandards()).getExecutingBlocks().getRunMethod(),args_);
    }

    @Override
    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseClicked(),args_);
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseEntered(),args_);
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseExited(),args_);
    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMousePressed(),args_);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseReleased(),args_);
    }

    @Override
    public void mouseDragged(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseDragged(),args_);
    }

    @Override
    public void mouseMoved(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseMoved(),args_);
    }

    @Override
    public void mouseWheelMoved(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons, AbsMouseWheel _wheel) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWheelEvent();
        MouseWheelEventStruct a_ = new MouseWheelEventStruct(actEv_, _location, _keyState, _buttons, _wheel);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWheelListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWheelMove(),args_);
    }

    @Override
    public void windowOpened() {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowOpened(),args_);
    }

    @Override
    public void windowClosing() {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowClosing(),args_);
    }

    @Override
    public void windowClosed() {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowClosed(),args_);
    }

    @Override
    public void windowIconified() {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowIconified(),args_);
    }

    @Override
    public void windowDeiconified() {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowDeiconified(),args_);
    }

    @Override
    public void windowActivated() {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowActivated(),args_);
    }

    @Override
    public void windowDeactivated() {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowDeactivated(),args_);
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getListSelection(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getValueChanged(),args_);
    }

    @Override
    public void keyPressed(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_keyState,actEv_,_keyChar,_keyCode);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyPressed(),args_);
    }

    @Override
    public void keyTyped(AbsCtrlKeyState _keyState, char _keyChar) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_keyState,actEv_,_keyChar);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyTyped(),args_);
    }

    @Override
    public void keyReleased(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_keyState,actEv_,_keyChar,_keyCode);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyReleased(),args_);
    }

    @Override
    public void stateChanged() {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getChangeListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getStateChanged(),args_);
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _e) {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        Struct arg_ = TreeNodeStruct.nodeOrNull(_e);
        args_.add(new Argument(arg_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getTreeListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getTreeListenerValueChanged(),args_);
    }

    @Override
    public void valueChanged(int _first, int _last) {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_first)),new Argument(new IntStruct(_last)));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getTableListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getTableValueTableChanged(),args_);
    }

    private void invoke(RunnableContextEl _r, ExecRootBlock _typeName, ExecNamedFunctionBlock _methName, CustList<Argument> _args) {
        ArgumentListCall argList_ = ArgumentListCall.wrapCall(_args);
        RunnableStruct.invoke(this,_r,_typeName,_methName, argList_);
    }
    private GuiContextEl newCtx() {
        GuiContextEl r_ = new GuiContextEl(InitPhase.NOTHING, executionInfos, args);
        RunnableStruct.setupThread(r_);
        return r_;
    }

}
