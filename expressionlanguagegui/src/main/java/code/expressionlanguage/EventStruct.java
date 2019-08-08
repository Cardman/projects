package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;

import java.awt.event.*;

public final class EventStruct implements FieldableStruct,EnumerableStruct,
        ActionListener,Runnable,MouseListener,WindowListener {

    private final String className;

    private final ObjectMap<ClassField,Struct> fields;

    private final Struct parent;

    private String name;
    private int ordinal;
    private final ContextEl original;
    public EventStruct(ContextEl _original, String _className,
                       String _name, int _ordinal,
                       ObjectMap<ClassField,Struct> _fields, Struct _parent) {
        original = _original;
        name = _name;
        ordinal = _ordinal;
        className = _className;
        fields = _fields;
        parent = _parent;
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
    public Struct getStruct(ClassField _classField) {
        return fields.getVal(_classField);
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return fields;
    }

    @Override
    public void setStruct(ClassField _classField, Struct _value) {
        fields.set(_classField,_value);
    }

    @Override
    public Struct getParent() {
        return parent;
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
        ActionEventStruct a_ = new ActionEventStruct(e,actEv_);
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
    public void windowOpened(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowOpened();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowClosing();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowClosed();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowIconified();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowDeiconified();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowActivated(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowActivated();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        String actEv_ = ((LgNamesGui) original.getStandards()).getAliasWindowEvent();
        WindowEventStruct a_ = new WindowEventStruct(e,actEv_);
        GuiContextEl r_ = newCtx();
        CustList<Argument> args_ = new CustList<Argument>(new Argument(a_));
        String actPerf_ = ((LgNamesGui) original.getStandards()).getAliasWindowDeactivated();
        String actList_ = ((LgNamesGui) original.getStandards()).getAliasWindowListener();
        invoke(r_,actList_,actPerf_,new StringList(actEv_),args_);
    }

    private void invoke(GuiContextEl _r, String _typeName,String _methName, StringList _argTypes, CustList<Argument> _args) {
        MethodId id_ = new MethodId(MethodModifier.ABSTRACT, _methName, _argTypes);
        GeneType type_ = _r.getClassBody(_typeName);
        String base_ = Templates.getIdFromAllTypes(className);
        ClassMethodId mId_ = TypeUtil.getConcreteMethodsToCall(type_, id_, _r).getVal(base_);
        if (mId_ == null) {
            _r.getCustInit().removeThreadFromList();
            return;
        }
        Argument arg_ = new Argument();
        arg_.setStruct(this);
        ProcessMethod.calculateArgument(arg_, mId_.getClassName(), mId_.getConstraints(), _args, _r,null);
        _r.getCustInit().prExc(_r);
    }
    private GuiContextEl newCtx() {
        Thread thread_ = Thread.currentThread();
        GuiContextEl r_ = new GuiContextEl(original);
        r_.getCustInit().initAlive(thread_);
        StringBuilder dtPart_ = new StringBuilder();
        dtPart_.append(LgNamesUtils.getDateTimeText("_", "_", "_"));
        dtPart_.append("__");
        dtPart_.append(r_.getCustInit().increment());
        dtPart_.append(".txt");
        r_.getCustInit().putNewCustTreadIdDate(thread_, dtPart_.toString());
        return r_;
    }
}
