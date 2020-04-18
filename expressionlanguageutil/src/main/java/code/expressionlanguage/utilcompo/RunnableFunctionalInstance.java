package code.expressionlanguage.utilcompo;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.CustomReflectMethod;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.structs.AbstractFunctionalInstance;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.ObjectMap;

public final class RunnableFunctionalInstance implements AbstractFunctionalInstance, Runnable,
        FieldableStruct {

    private final String className;

    private Struct functional;
    private ContextEl original;

    private final ObjectMap<ClassField,Struct> fields;

    public RunnableFunctionalInstance(String _className,
                                      ObjectMap<ClassField,Struct> _fields, ContextEl _contextEl) {
        className = _className;
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
    public String getClassName(ExecutableCode _contextEl) {
        return className;
    }

    @Override
    public Struct getFunctional() {
        return functional;
    }

    @Override
    public void setFunctional(Struct _functional) {
        functional = _functional;
    }

    @Override
    public void run() {
        callMethod(new RunnableContextEl(original), functional, new CustList<Argument>());
    }

    public static void callMethod(RunnableContextEl _localThread, Struct _functional, CustList<Argument> _arguments) {
        RunnableStruct.setupThread(_localThread);
        ExecInvokingOperation.prepareCallDyn(new Argument(_functional), _arguments, _localThread);
        if (_localThread.getCallingState() instanceof CustomReflectMethod) {
            CustomReflectMethod ref_ = (CustomReflectMethod) _localThread.getCallingState();
            RunnableStruct.reflect(ref_.getGl(), ref_.getArguments(),_localThread, ref_.getReflect(), ref_.isLambda());
        } else {
            _localThread.getCustInit().prExc(_localThread);
        }
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
