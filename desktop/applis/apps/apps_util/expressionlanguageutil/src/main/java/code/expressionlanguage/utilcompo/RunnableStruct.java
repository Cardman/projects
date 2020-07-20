package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.exec.ProcessMethod;
import code.expressionlanguage.exec.ReflectingType;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithParentStruct;
import code.util.CustList;
import code.util.StringList;

public final class RunnableStruct implements WithParentStruct, EnumerableStruct,Runnable {
    private ContextEl original;
    private Struct parent;

    private String className;

    private CustList<ClassFieldStruct> fields;

    private String name;
    private int ordinal;
    private final String parentClassName;
    RunnableStruct(ContextEl _original,String _className,
                      String _name, int _ordinal,
                   CustList<ClassFieldStruct> _fields, Struct _parent) {
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
    public void run() {
        RunnableContextEl r_ = new RunnableContextEl(original);
        setupThread(r_);
        LgNames stds_ = r_.getStandards();
        String run_ = ((LgNamesUtils)stds_).getAliasRun();
        String runnable_ = ((LgNamesUtils)stds_).getAliasRunnable();
        invoke(this,r_,runnable_,run_,new StringList(),new CustList<Argument>());
    }
    public static void invoke(Struct _instance,RunnableContextEl _r, String _typeName, String _methName, StringList _argTypes, CustList<Argument> _args) {
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, _methName, _argTypes);
        String idType_ = StringExpUtil.getIdFromAllTypes(_typeName);
        GeneType type_ = _r.getClassBody(idType_);
        if (!(type_ instanceof ExecRootBlock)) {
            _r.getCustInit().removeThreadFromList(_r);
            return;
        }
        String base_ = StringExpUtil.getIdFromAllTypes(_instance.getClassName(_r));
        ExecRootBlock ex_ = (ExecRootBlock) type_;
        ClassMethodId mId_ = ex_.getRedirections().getVal(new ClassMethodId(idType_,id_),base_);
        if (mId_ == null) {
            _r.getCustInit().removeThreadFromList(_r);
            return;
        }
        Argument arg_ = new Argument();
        arg_.setStruct(_instance);
        RunnableStruct.invoke(arg_, mId_.getClassName(), mId_.getConstraints(), _args, _r,null);
    }
    public static Argument invoke(Argument _global, String _class, MethodId _method, CustList<Argument> _args, RunnableContextEl _cont, Argument _right) {
        Argument arg_ = ProcessMethod.calculateArgument(_global, _class, _method, _args, _cont, _right);
        _cont.getCustInit().prExc(_cont);
        return arg_;
    }
    public static Argument reflect(Argument _global, CustList<Argument> _args, RunnableContextEl _cont, ReflectingType _reflect, boolean _lambda) {
        Argument arg_ = ProcessMethod.reflectArgument(_global, _args,_cont,_reflect, _lambda);
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
        _r.getCustInit().putNewCustTreadIdDate(_r, dtPart_.toString());
        return nb_;
    }
}
