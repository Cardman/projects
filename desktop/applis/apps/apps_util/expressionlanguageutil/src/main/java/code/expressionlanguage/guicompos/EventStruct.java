package code.expressionlanguage.guicompos;

import code.expressionlanguage.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.*;
import code.gui.events.*;
import code.threads.IntCallable;
import code.util.*;

public final class EventStruct extends LaunchableStruct implements
        AbsAdvActionListener,Runnable, AbsMouseListener, AbsWindowListener,ListSelection,
        AbsKeyListener,AbsFocusListener,AbsChangeListener,AbsShortListTree,AbsListSelectionListener,
        AbsMouseMotionListener, AbsMouseWheelListener, StructCellRender, IntCallable<Struct> {

    public EventStruct(ContextEl _original, String _className,
                       CustList<ClassFieldStruct> _fields, LambdaStruct _f, ExecNamedFunctionBlock _n) {
        super(_original, _className, _fields, _n, _f);
    }
    public EventStruct(ContextEl _original, String _className,
                       String _name, int _ordinal,
                       CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName) {
        super(_original, _className, _name, _ordinal, _fields, _parent, _parentClassName);
    }

    public static Struct invoke(Struct _instance, ContextEl _r, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _method, ArgumentListCall _argList) {
        StackCall st_ = StackCall.newInstance(InitPhase.NOTHING, _r);
        return ArgumentListCall.toStr(Argument.getNullableValue(invoke(_instance, _r, new ExecTypeFunction(_rootBlock,_method), st_, _argList)));
    }

    public static Argument invoke(Struct _global, ContextEl _cont, ExecTypeFunction _pair, StackCall _stackCall, ArgumentListCall _argList) {
        ExecTemplates.prepare(_cont,_stackCall,_global, _pair, _argList.getArguments());
        Argument arg_ = ProcessMethod.tryCalculate(_cont,_stackCall,null);
//        CallingState cs_ = _stackCall.getCallingState();
//        if (cs_ != null) {
//            arg_ = ProcessMethod.calculate(cs_, _cont, _stackCall).getValue();
//        } else {
//            arg_ = null;
//        }
        String err_ = _cont.errorMessage(_stackCall);
        if (err_ != null) {
            return null;
        }
        return arg_;
    }

    public static long setupThread(RunnableContextEl _r) {
        long nb_ = _r.getCustInit().increment();
        StringBuilder dtPart_ = new StringBuilder();
        dtPart_.append(CustAliases.getDateTimeText(_r.getCurrentThreadFactory()));
        dtPart_.append("__");
        dtPart_.append(nb_);
        dtPart_.append(".txt");
        _r.getCustInit().putNewCustTreadIdDate(_r, dtPart_.toString());
        return nb_;
    }

    @Override
    public void action(AbsCtrlKeyState _state, String _command) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasActionEvent();
        ActionEventStruct a_ = new ActionEventStruct(actEv_,_state,_command);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getActionListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getActionPerformed(),args_);
    }

    @Override
    public void run() {
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        invoke(r_, ((LgNamesWithNewAliases) r_.getStandards()).getExecContent().getExecutingBlocks().getRunnableType(), ((LgNamesWithNewAliases) r_.getStandards()).getExecContent().getExecutingBlocks().getRunMethod(),args_);
    }

    @Override
    public Struct call() {
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        return invoke(r_, ((LgNamesWithNewAliases) r_.getStandards()).getExecContent().getExecutingBlocks().getCallableType(), ((LgNamesWithNewAliases) r_.getStandards()).getExecContent().getExecutingBlocks().getCallMethod(),args_);
    }

    @Override
    public void mouseClicked(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseClicked(),args_);
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseEntered(),args_);
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseExited(),args_);
    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMousePressed(),args_);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseReleased(),args_);
    }

    @Override
    public void mouseDragged(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseDragged(),args_);
    }

    @Override
    public void mouseMoved(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getMouseMoved(),args_);
    }

    @Override
    public void mouseWheelMoved(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons, AbsMouseWheel _wheel) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasWheelEvent();
        MouseWheelEventStruct a_ = new MouseWheelEventStruct(actEv_, _location, _keyState, _buttons, _wheel);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWheelListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWheelMove(),args_);
    }

    @Override
    public void windowOpened() {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowOpened(),args_);
    }

    @Override
    public void windowClosing() {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowClosing(),args_);
    }

    @Override
    public void windowClosed() {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowClosed(),args_);
    }

    @Override
    public void windowIconified() {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowIconified(),args_);
    }

    @Override
    public void windowDeiconified() {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowDeiconified(),args_);
    }

    @Override
    public void windowActivated() {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowActivated(),args_);
    }

    @Override
    public void windowDeactivated() {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getWindowDeactivated(),args_);
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())),new Argument(BooleanStruct.of(_e.isMethodAction())));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getListSelection(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getValueChanged(),args_);
    }

    @Override
    public void keyPressed(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_keyState,actEv_,_keyChar,_keyCode);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyPressed(),args_);
    }

    @Override
    public void keyTyped(AbsCtrlKeyState _keyState, char _keyChar) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_keyState,actEv_,_keyChar);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyTyped(),args_);
    }

    @Override
    public void keyReleased(AbsCtrlKeyState _keyState, char _keyChar, int _keyCode) {
        String actEv_ = ((LgNamesGui) getExecutionInfos().getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_keyState,actEv_,_keyChar,_keyCode);
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getKeyReleased(),args_);
    }

    @Override
    public void focusGained() {
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getFocusListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getFocusGained(),args_);
    }

    @Override
    public void focusLost() {
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getFocusListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getFocusLost(),args_);
    }

    @Override
    public void stateChanged() {
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getChangeListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getStateChanged(),args_);
    }

    @Override
    public void valueChanged(AbstractMutableTreeNodeCore<String> _e) {
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        Struct arg_ = TreeNodeStruct.nodeOrNull(_e);
        args_.add(new Argument(arg_));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getTreeListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getTreeListenerValueChanged(),args_);
    }

    @Override
    public void valueChanged(int _first, int _last) {
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_first)),new Argument(new IntStruct(_last)));
        invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getTableListener(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getTableValueTableChanged(),args_);
    }

    @Override
    public Struct generateImg(Struct _index, Struct _info, Struct _isSelected, Struct _cellHasFocus, Struct _cellIsAnchored, Struct _lab, Struct _compo) {
        ContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        args_.add(new Argument(_index));
        args_.add(new Argument(_info));
        args_.add(new Argument(_isSelected));
        args_.add(new Argument(_cellHasFocus));
        args_.add(new Argument(_cellIsAnchored));
        args_.add(new Argument(_lab));
        args_.add(new Argument(_compo));
        return invoke(r_, ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getCellRender(), ((LgNamesGui) r_.getStandards()).getGuiExecutingBlocks().getCellRenderGenerate(),args_);
    }

    private Struct invoke(ContextEl _r, ExecRootBlock _typeName, ExecNamedFunctionBlock _methName, CustList<Argument> _args) {
        ArgumentListCall argList_ = ArgumentListCall.wrapCall(_args);
        return invoke(this,_r,_typeName,_methName, argList_);
    }
    private ContextEl newCtx() {
        return getOriginal().copy(this);
    }

}
