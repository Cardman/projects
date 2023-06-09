package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;

import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.*;
import code.gui.events.*;
import code.util.*;

public final class EventFunctionalInstance extends LaunchableFunctionalStruct implements
        AbsAdvActionListener,Runnable, AbsMouseListener, AbsWindowListener,ListSelection,
        AbsKeyListener,AbsChangeListener,AbsShortListTree,AbsListSelectionListener,
        AbsMouseMotionListener, AbsMouseWheelListener,FieldableStruct {

    public EventFunctionalInstance(String _className, LambdaStruct _functional,
                                   CustList<ClassFieldStruct> _fields, RunnableContextEl _contextEl, ExecNamedFunctionBlock _named) {
        super(_className, _functional, _fields, _contextEl, _named);
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), args_);
    }

    @Override
    public void action(AbsCtrlKeyState _state, String _command) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasActionEvent();
        ActionEventStruct a_ = new ActionEventStruct(actEv_,_state,_command);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), args_);
    }

    @Override
    public void keyTyped(AbsCtrlKeyState _keyState, char _keyChar) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_keyState,actEv_,_keyChar);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), args_);
    }

    @Override
    public void keyPressed(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        key(_keyState, _keyChar, _keyCode);
    }

    @Override
    public void keyReleased(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        key(_keyState, _keyChar, _keyCode);
    }

    private void key(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_keyState, actEv_, _keyChar, _keyCode);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), args_);
    }

    @Override
    public void mouseDragged(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mouseMoved(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    private void mouseFct(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), args_);
    }

    @Override
    public void mouseWheelMoved(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons, AbsMouseWheel _wheel) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasWheelEvent();
        MouseWheelEventStruct a_ = new MouseWheelEventStruct(actEv_, _location, _keyState, _buttons, _wheel);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), args_);
    }

    @Override
    public void windowOpened() {
        window();
    }

    @Override
    public void windowIconified() {
        window();
    }

    @Override
    public void windowDeiconified() {
        window();
    }

    @Override
    public void windowDeactivated() {
        window();
    }

    @Override
    public void windowClosing() {
        window();
    }

    @Override
    public void windowClosed() {
        window();
    }

    @Override
    public void windowActivated() {
        window();
    }

    private void window() {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), args_);
    }

    @Override
    public void run() {
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), new CustList<Argument>());
    }

    @Override
    public void stateChanged() {
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), new CustList<Argument>());
    }

    @Override
    public void valueChanged(int _first, int _last) {
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_first)),new Argument(new IntStruct(_last)));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), args_);
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore _e) {
        CustList<Argument> args_ = new CustList<Argument>();
        Struct arg_ = TreeNodeStruct.nodeOrNull(_e);
        args_.add(new Argument(arg_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(getInterrupt(),this, getExecutionInfos(), getArgs()), getFunctional(), args_);
    }
}
