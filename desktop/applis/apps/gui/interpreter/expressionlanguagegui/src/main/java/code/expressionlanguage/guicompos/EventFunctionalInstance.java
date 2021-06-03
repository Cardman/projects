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
import code.expressionlanguage.common.NumParsers;
import code.gui.ListSelection;
import code.gui.SelectionInfo;
import code.util.CustList;

import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.*;

public final class EventFunctionalInstance extends AbstractFunctionalInstanceImpl implements
        ActionListener,Runnable,MouseListener,WindowListener,ListSelection,
        KeyListener,ChangeListener,TreeSelectionListener,ListSelectionListener,
        MouseMotionListener,MouseWheelListener,FieldableStruct {

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
    public void actionPerformed(ActionEvent _e) {
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
    public void mouseClicked(MouseEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(_e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void mousePressed(MouseEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(_e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(_e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void mouseEntered(MouseEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(_e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void mouseExited(MouseEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(_e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void mouseDragged(MouseEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(_e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void mouseMoved(MouseEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(_e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(InitPhase.NOTHING, executionInfos), getFunctional(), args_);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent _e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getGuiAliases().getAliasWheelEvent();
        MouseWheelEventStruct a_ = new MouseWheelEventStruct(_e,actEv_);
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
    public void valueChanged(TreeSelectionEvent _e) {
        CustList<Argument> args_ = new CustList<Argument>();
        Object sel_ = _e.getPath().getLastPathComponent();
        if (sel_ instanceof DefaultMutableTreeNode) {
            TreeNodeStruct arg_ = new TreeNodeStruct((DefaultMutableTreeNode) sel_);
            args_.add(new Argument(arg_));
        } else {
            args_.add(new Argument());
        }
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
