package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.util.CustList;

public abstract class AbsFieldableStruct implements WithParentStruct, EnumerableStruct, AbstractFunctionalInstance {

    private final String className;

    private final CustList<ClassFieldStruct> fields;

    private Struct parent;

    private final String parentClassName;
    private final int ordinal;

    private final String name;
    private final ExecNamedFunctionBlock named;
    private final LambdaStruct functional;
    protected AbsFieldableStruct(String _className,
                           CustList<ClassFieldStruct> _fields, Struct _parent, String _parentClassName,
                                 int _ordinal, String _name) {
        this.fields = _fields;
        this.className = _className;
        this.parent = _parent;
        this.parentClassName = _parentClassName;
        this.ordinal = _ordinal;
        this.name = _name;
        this.named = null;
        this.functional = null;
    }
    protected AbsFieldableStruct(String _className,
                                 CustList<ClassFieldStruct> _fields, ExecNamedFunctionBlock _n, LambdaStruct _f) {
        this.fields = _fields;
        this.className = _className;
        this.parent = NullStruct.NULL_VALUE;
        this.parentClassName = "";
        this.ordinal = -1;
        this.name = "";
        this.named = _n;
        this.functional = _f;
    }

    @Override
    public ExecNamedFunctionBlock getNamed() {
        return named;
    }

    @Override
    public LambdaStruct getFunctional() {
        return functional;
    }

    @Override
    public String getParentClassName() {
        return parentClassName;
    }

    @Override
    public Struct getParent() {
        return parent;
    }

    @Override
    public void setParent(Struct _parent) {
        parent = _parent;
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public long randCode() {
        return NumParsers.randCode(getClassName());
    }
    @Override
    public ClassFieldStruct getEntryStruct(ClassField _classField) {
        return ClassFieldStruct.getPair(fields,_classField);
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return getClassName();
    }

    public String getClassName() {
        return className;
    }

    @Override
    public CustList<ClassFieldStruct> getFields() {
        return fields;
    }

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String getName() {
        return name;
    }

}
