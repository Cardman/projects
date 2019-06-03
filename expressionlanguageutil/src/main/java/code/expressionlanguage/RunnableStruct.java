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

public final class RunnableStruct implements FieldableStruct, EnumerableStruct,Runnable {
    private RunnableContextEl original;
    private Struct parent;

    private String className;

    private ObjectMap<ClassField, Struct> fields;

    private String name;
    private int ordinal;

    RunnableStruct(ContextEl _original,String _className,
                      String _name, int _ordinal,
                      ObjectMap<ClassField,Struct> _fields, Struct _parent) {
        original = (RunnableContextEl) _original;
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
    public void run() {
        Thread thread_ = Thread.currentThread();
        RunnableContextEl r_ = new RunnableContextEl(original);
        r_.getInit().initAlive(thread_);
        StringBuilder dtPart_ = new StringBuilder();
        dtPart_.append(LgNamesUtils.getDateTimeText("_", "_", "_"));
        dtPart_.append("__");
        dtPart_.append(r_.getInit().increment());
        dtPart_.append(".txt");
        r_.getInit().putNewCustTreadIdDate(thread_, dtPart_.toString());
        LgNames stds_ = r_.getStandards();
        String run_ = r_.getInit().getRunTask(stds_);
        String runnable_ = r_.getInit().getInterfaceTask(stds_);
        MethodId id_ = new MethodId(MethodModifier.ABSTRACT, run_, new StringList());
        GeneType type_ = r_.getClassBody(runnable_);
        String base_ = Templates.getIdFromAllTypes(className);
        ClassMethodId mId_ = TypeUtil.getConcreteMethodsToCall(type_, id_, r_).getVal(base_);
        Argument arg_ = new Argument();
        arg_.setStruct(this);
        ProcessMethod.calculateArgument(arg_, mId_.getClassName(), mId_.getConstraints(), new CustList<Argument>(), r_);
        r_.getInit().prExc(r_);
    }
}
