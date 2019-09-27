package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
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
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithParentStruct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;

public final class RunnableStruct implements WithParentStruct, EnumerableStruct,Runnable {
    private ContextEl original;
    private Struct parent;

    private String className;

    private ObjectMap<ClassField, Struct> fields;

    private String name;
    private int ordinal;
    private final String parentClassName;
    RunnableStruct(ContextEl _original,String _className,
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
    public void run() {
        RunnableContextEl r_ = new RunnableContextEl(original);
        r_.setNumber(setupThread(r_));
        LgNames stds_ = r_.getStandards();
        String run_ = r_.getCustInit().getRunTask(stds_);
        String runnable_ = r_.getCustInit().getInterfaceTask(stds_);
        MethodId id_ = new MethodId(MethodModifier.ABSTRACT, run_, new StringList());
        GeneType type_ = r_.getClassBody(runnable_);
        String base_ = Templates.getIdFromAllTypes(className);
        ClassMethodId mId_ = TypeUtil.getConcreteMethodsToCall(type_, id_, r_).getVal(base_);
        Argument arg_ = new Argument();
        arg_.setStruct(this);
        invoke(arg_, mId_.getClassName(), mId_.getConstraints(), new CustList<Argument>(), r_,null);
    }
    public static Argument invoke(Argument _global, String _class, MethodId _method, CustList<Argument> _args, RunnableContextEl _cont, Argument _right) {
        Argument arg_ = ProcessMethod.calculateArgument(_global, _class, _method, _args, _cont, _right);
        _cont.getCustInit().prExc(_cont);
        return arg_;
    }
    public static long setupThread(RunnableContextEl _r) {
        long nb_ = _r.getCustInit().increment();
        StringBuilder dtPart_ = new StringBuilder();
        dtPart_.append(LgNamesUtils.getDateTimeText("_", "_", "_"));
        dtPart_.append("__");
        dtPart_.append(nb_);
        dtPart_.append(".txt");
        _r.setThread(Thread.currentThread());
        _r.getCustInit().putNewCustTreadIdDate(_r, dtPart_.toString());
        return nb_;
    }
}
