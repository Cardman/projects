package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.expressionlanguage.structs.*;
import code.gui.ListSelection;
import code.gui.SelectionInfo;
import code.util.CustList;

import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.*;

public final class EventStruct implements WithParentStruct,EnumerableStruct,
        ActionListener,Runnable,MouseListener,WindowListener,ListSelection,
        KeyListener,ChangeListener,TreeSelectionListener,ListSelectionListener,
        MouseMotionListener,MouseWheelListener{

    private final String className;

    private final CustList<ClassFieldStruct> fields;
    private final CommonExecutionInfos executionInfos;

    private Struct parent;

    private String name;
    private int ordinal;
    private final String parentClassName;

    public EventStruct(ContextEl _original, String _className,
                       String _name, int _ordinal,
                       CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName) {
        name = _name;
        ordinal = _ordinal;
        className = _className;
        fields = _fields;
        parent = _parent;
        parentClassName = _parentClassName;
        executionInfos = _original.getExecutionInfos();
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
    public void actionPerformed(ActionEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasActionEvent();
        ActionEventStruct a_ = new ActionEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getActionListener(),r_.getActionPerformed(),args_);
    }

    @Override
    public void run() {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        invoke(r_,r_.getRunnableType(),r_.getExecuteMethod(),args_);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getMouseListener(),r_.getMouseClicked(),args_);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getMouseListener(),r_.getMousePressed(),args_);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getMouseListener(),r_.getMouseReleased(),args_);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getMouseListener(),r_.getMouseEntered(),args_);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getMouseListener(),r_.getMouseExited(),args_);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getMouseListener(),r_.getMouseDragged(),args_);
   }

    @Override
    public void mouseMoved(MouseEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getMouseListener(),r_.getMouseMoved(),args_);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasWheelEvent();
        MouseWheelEventStruct a_ = new MouseWheelEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getWheelListener(),r_.getWheelMove(),args_);
    }
    @Override
    public void windowOpened(WindowEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getWindowListener(),r_.getWindowOpened(),args_);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getWindowListener(),r_.getWindowClosing(),args_);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getWindowListener(),r_.getWindowClosed(),args_);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getWindowListener(),r_.getWindowIconified(),args_);
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getWindowListener(),r_.getWindowDeiconified(),args_);
    }

    @Override
    public void windowActivated(WindowEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getWindowListener(),r_.getWindowActivated(),args_);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getWindowListener(),r_.getWindowDeactivated(),args_);
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())));
        invoke(r_,r_.getListSelection(),r_.getValueChanged(),args_);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(e,actEv_,e.getKeyChar(),e.getKeyCode());
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getKeyListener(),r_.getKeyPressed(),args_);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(e,actEv_,e.getKeyChar());
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getKeyListener(),r_.getKeyTyped(),args_);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String actEv_ = ((LgNamesGui) executionInfos.getStandards()).getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(e,actEv_,e.getKeyChar(),e.getKeyCode());
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        invoke(r_,r_.getKeyListener(),r_.getKeyReleased(),args_);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        invoke(r_,r_.getChangeListener(),r_.getStateChanged(),args_);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        Object sel_ = e.getPath().getLastPathComponent();
        if (sel_ instanceof DefaultMutableTreeNode) {
            TreeNodeStruct arg_ = new TreeNodeStruct((DefaultMutableTreeNode) sel_);
            args_.add(new Argument(arg_));
        } else {
            args_.add(new Argument());
        }
        invoke(r_,r_.getTreeListener(),r_.getTreeListenerValueChanged(),args_);
    }

    @Override
    public void valueChanged(ListSelectionEvent _e) {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())));
        invoke(r_,r_.getTableListener(),r_.getTableValueTableChanged(),args_);
    }

    private void invoke(RunnableContextEl _r, ExecRootBlock _typeName, ExecNamedFunctionBlock _methName, CustList<Argument> _args) {
        RunnableStruct.invoke(this,_r,_typeName,_methName,_args);
    }
    private GuiContextEl newCtx() {
        GuiContextEl r_ = new GuiContextEl(InitPhase.NOTHING, executionInfos);
        RunnableStruct.setupThread(r_);
        return r_;
    }

}
