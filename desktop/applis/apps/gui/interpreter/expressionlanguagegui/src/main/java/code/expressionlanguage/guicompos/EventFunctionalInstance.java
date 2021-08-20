package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;

import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.RunnableFunctionalInstance;
import code.gui.*;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListener;
import code.gui.events.AbsMouseMotionListener;
import code.gui.events.AbsMouseWheelListener;
import code.util.CustList;

import javax.swing.event.*;
import java.awt.event.*;

public final class EventFunctionalInstance extends AbstractFunctionalInstanceImpl implements
        AbsActionListener,Runnable, AbsMouseListener,WindowListener,ListSelection,
        KeyListener,ChangeListener,AbsShortListTree,ListSelectionListener,
        AbsMouseMotionListener, AbsMouseWheelListener,FieldableStruct {

    private final CustList<ClassFieldStruct> fields;
    private final CommonExecutionInfos executionInfos;

    public EventFunctionalInstance(String _className, LambdaStruct _functional,
                                   CustList<ClassFieldStruct> _fields, ContextEl _contextEl, ExecNamedFunctionBlock _named) {
        super(_className, _functional, _named);
        fields = _fields;
        executionInfos = _contextEl.getExecutionInfos();
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void action() {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasActionEvent();
        ActionEventStruct a_ = new ActionEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void keyTyped(KeyEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_e,actEv_,_e.getKeyChar());
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void keyPressed(KeyEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_e,actEv_,_e.getKeyChar(),_e.getKeyCode());
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void keyReleased(KeyEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(_e,actEv_,_e.getKeyChar(),_e.getKeyCode());
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void mouseDragged(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mousePressed(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mouseExited(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mouseEntered(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mouseClicked(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    @Override
    public void mouseMoved(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        mouseFct(_location, _keyState, _buttons);
    }

    private void mouseFct(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(actEv_, _location, _keyState, _buttons);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void mouseWheelMoved(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons, AbsMouseWheel _wheel) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWheelEvent();
        MouseWheelEventStruct a_ = new MouseWheelEventStruct(actEv_, _location, _keyState, _buttons, _wheel);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void windowOpened(WindowEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void windowClosing(WindowEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void windowClosed(WindowEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void windowIconified(WindowEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void windowDeiconified(WindowEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void windowActivated(WindowEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void windowDeactivated(WindowEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void run() {
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), new CustList<Argument>());
    }

    @Override
    public void stateChanged(ChangeEvent _e) {
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), new CustList<Argument>());
    }

    @Override
    public void valueChanged(ListSelectionEvent _e) {
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void valueChanged(AbstractMutableTreeNode _e) {
        CustList<Argument> args_ = new CustList<Argument>();
        TreeNodeStruct arg_ = new TreeNodeStruct(_e);
        args_.add(new Argument(arg_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }
}
