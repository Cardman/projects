package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.RunnableFunctionalInstance;
import code.gui.ListSelection;
import code.gui.SelectionInfo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectMap;

import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.*;

public final class EventFunctionalInstance implements AbstractFunctionalInstance,
        ActionListener,Runnable,MouseListener,WindowListener,ListSelection,
        KeyListener,ChangeListener,TreeSelectionListener,ListSelectionListener,
        MouseMotionListener,MouseWheelListener,FieldableStruct {

    private final String className;

    private final LambdaStruct functional;
    private final ContextEl original;

    private final ObjectMap<ClassField,Struct> fields;
    public EventFunctionalInstance(String _className,LambdaStruct _functional,
                                   ObjectMap<ClassField,Struct> _fields,ContextEl _contextEl) {
        className = _className;
        functional = _functional;
        original = _contextEl;
        fields = _fields;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

    @Override
    public LambdaStruct getFunctional() {
        return functional;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasActionEvent();
        ActionEventStruct a_ = new ActionEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(e,actEv_,e.getKeyChar());
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(e,actEv_,e.getKeyChar(),e.getKeyCode());
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(e,actEv_,e.getKeyChar(),e.getKeyCode());
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWheelEvent();
        MouseWheelEventStruct a_ = new MouseWheelEventStruct(e,actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void windowActivated(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void run() {
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, new CustList<Argument>());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, new CustList<Argument>());
    }

    @Override
    public void valueChanged(ListSelectionEvent _e) {
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())));
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        CustList<Argument> args_ = new CustList<Argument>();
        Object sel_ = e.getPath().getLastPathComponent();
        if (sel_ instanceof DefaultMutableTreeNode) {
            TreeNodeStruct arg_ = new TreeNodeStruct((DefaultMutableTreeNode) sel_);
            args_.add(new Argument(arg_));
        } else {
            args_.add(new Argument());
        }
        RunnableFunctionalInstance.callMethod(new GuiContextEl(original), functional, args_);
    }

    @Override
    public EntryCust<ClassField, Struct> getEntryStruct(ClassField _classField) {
        return fields.getEntryByKey(_classField);
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return fields;
    }
}
