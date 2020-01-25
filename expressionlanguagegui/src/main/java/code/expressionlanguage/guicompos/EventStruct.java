package code.expressionlanguage.guicompos;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.utilcompo.RunnableStruct;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.gui.ListSelection;
import code.gui.SelectionInfo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectMap;
import code.util.StringList;

import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.*;

public final class EventStruct implements WithParentStruct,EnumerableStruct,
        ActionListener,Runnable,MouseListener,WindowListener,ListSelection,
        KeyListener,ChangeListener,TreeSelectionListener,ListSelectionListener,
        MouseMotionListener,MouseWheelListener{

    private final String className;

    private final ObjectMap<ClassField,Struct> fields;

    private Struct parent;

    private String name;
    private int ordinal;
    private final ContextEl original;
    private final String parentClassName;
    public EventStruct(ContextEl _original, String _className,
                       String _name, int _ordinal,
                       ObjectMap<ClassField,Struct> _fields, Struct _parent) {
        original = _original;
        name = _name;
        ordinal = _ordinal;
        className = _className;
        fields = _fields;
        parent = _parent;
        parentClassName = _parent.getClassName(_original);
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
    public EntryCust<ClassField, Struct> getEntryStruct(ClassField _classField) {
        return fields.getEntryByKey(_classField);
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
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
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasActionEvent();
        ActionEventStruct a_ = new ActionEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasActionPerformed();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasActionListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void run() {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        LgNames stds_ = r_.getStandards();
        String run_ = r_.getCustInit().getRunTask(stds_);
        String runnable_ = r_.getCustInit().getInterfaceTask(stds_);
        invoke(r_,runnable_,run_,new StringList(),args_);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasMouseClicked();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasMouseListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasMousePressed();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasMouseListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasMouseReleased();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasMouseListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasMouseEntered();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasMouseListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasMouseExited();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasMouseListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasMouseDragged();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasMouseListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
   }

    @Override
    public void mouseMoved(MouseEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasMouseEvent();
        MouseEventStruct a_ = new MouseEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasMouseMoved();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasMouseListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWheelEvent();
        MouseWheelEventStruct a_ = new MouseWheelEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWheelMove();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWheelListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }
    @Override
    public void windowOpened(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowOpened();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowClosing();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowClosed();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowIconified();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowDeiconified();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowActivated(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowActivated();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowDeactivated();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        String ind_ = original.getStandards().getAliasPrimInteger();
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasValueChanged();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasListSelection();
        invoke(r_,actList_,actPerf_,new StringList(ind_,ind_),args_);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(e,actEv_,e.getKeyChar(),e.getKeyCode());
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasKeyPressed();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasKeyListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(e,actEv_,e.getKeyChar());
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasKeyTyped();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasKeyListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasKeyEvent();
        KeyEventStruct a_ = new KeyEventStruct(e,actEv_,e.getKeyChar(),e.getKeyCode());
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasKeyReleased();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasKeyListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasStateChanged();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasChangeListener();
        invoke(r_,actList_,actPerf_,new StringList(),args_);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasTreeNode();
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>();
        Object sel_ = e.getPath().getLastPathComponent();
        if (sel_ instanceof DefaultMutableTreeNode) {
            TreeNodeStruct arg_ = new TreeNodeStruct((DefaultMutableTreeNode) sel_);
            args_.add(new Argument(arg_));
        } else {
            args_.add(new Argument());
        }
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasTreeListenerValueChanged();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasTreeListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void valueChanged(ListSelectionEvent _e) {
        String ind_ = original.getStandards().getAliasPrimInteger();
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(new IntStruct(_e.getFirstIndex())),new Argument(new IntStruct(_e.getLastIndex())));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasTableValueTableChanged();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasTableListener();
        invoke(r_,actList_,actPerf_,new StringList(ind_,ind_),args_);
    }
    private void invoke(GuiContextEl _r, String _typeName,String _methName, StringList _argTypes, CustList<Argument> _args) {
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, _methName, _argTypes);
        GeneType type_ = _r.getClassBody(_typeName);
        String base_ = Templates.getIdFromAllTypes(className);
        ClassMethodId mId_ = TypeUtil.getConcreteMethodsToCall(type_, id_, _r).getVal(base_);
        if (mId_ == null) {
            _r.getCustInit().removeThreadFromList(_r);
            return;
        }
        Argument arg_ = new Argument();
        arg_.setStruct(this);
        RunnableStruct.invoke(arg_, mId_.getClassName(), mId_.getConstraints(), _args, _r,null);
    }
    private GuiContextEl newCtx() {
        GuiContextEl r_ = new GuiContextEl(original);
        r_.setNumber(RunnableStruct.setupThread(r_));
        return r_;
    }

}
