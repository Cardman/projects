package code.expressionlanguage;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.WithParentStruct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.CustList;
import code.util.StringList;

public abstract class LaunchableStruct implements WithParentStruct, EnumerableStruct, Runnable {

    private final String className;

    private final CustList<ClassFieldStruct> fields;
    private final CommonExecutionInfos executionInfos;
    private final StringList args;

    private Struct parent;

    private final String name;
    private final int ordinal;
    private final String parentClassName;

    protected LaunchableStruct(RunnableContextEl _original, String _className,
                       String _name, int _ordinal,
                       CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName) {
        name = _name;
        ordinal = _ordinal;
        className = _className;
        fields = _fields;
        parent = _parent;
        parentClassName = _parentClassName;
        executionInfos = _original.getExecutionInfos();
        args = _original.getArgs();
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
    public long randCode() {
        return NumParsers.randCode(className);
    }

    public CommonExecutionInfos getExecutionInfos() {
        return executionInfos;
    }

    public StringList getArgs() {
        return args;
    }
}
