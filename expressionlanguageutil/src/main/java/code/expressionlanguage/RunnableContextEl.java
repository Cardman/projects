package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;

public final class RunnableContextEl extends ContextEl implements FieldableStruct, EnumerableStruct,Runnable {

    private CustInitializer custInit;
    private Struct parent;

    private String className;

    private ObjectMap<ClassField, Struct> fields;

    private String name;
    private int ordinal;

    RunnableContextEl(ContextEl _context, String _className,
            String _name, int _ordinal,
            ObjectMap<ClassField,Struct> _fields, Struct _parent) {
        setClasses(_context.getClasses());
        setOptions(_context.getOptions());
        setExecuting(_context.getExecuting());
        setStandards(_context.getStandards());
        setTabWidth(_context.getTabWidth());
        setStackOverFlow(_context.getStackOverFlow());
        setMemoryError(_context.getMemoryError());
        setKeyWords(_context.getKeyWords());
        setThrowing(_context.getThrowing());
        setInterrupt(_context.getInterrupt());
        custInit = (CustInitializer) _context.getInit();
        name = _name;
        ordinal = _ordinal;
        className = _className;
        fields = _fields;
        parent = _parent;
    }
    @Override
    public void initError() {
        setMemoryError(new ErrorStruct(this, getStandards().getAliasError()));
    }
    @Override
    public CustInitializer getInit() {
        return custInit;
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
    public ObjectMap<ClassField, Struct> getFields() {
        return fields;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public void run() {
        LgNames stds_ = getStandards();
        Classes cls_ = getClasses();
        String run_ = custInit.getRunTask(stds_);
        String runnable_ = custInit.getInterfaceTask(stds_);
        MethodId id_ = new MethodId(MethodModifier.ABSTRACT, run_, new StringList());
        GeneType type_ = cls_.getClassBody(runnable_);
        String base_ = Templates.getIdFromAllTypes(className);
        ClassMethodId mId_ = TypeUtil.getConcreteMethodsToCall(type_, id_, this).getVal(base_);
        Argument arg_ = new Argument();
        arg_.setStruct(this);
        ProcessMethod.calculateArgument(arg_, mId_.getClassName(), mId_.getConstraints(), new CustList<Argument>(), this);
        custInit.prExc(this);
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
    public void setStruct(ClassField _classField, Struct _value) {
        fields.set(_classField,_value);
    }

}
