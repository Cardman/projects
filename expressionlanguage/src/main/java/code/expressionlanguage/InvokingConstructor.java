package code.expressionlanguage;

import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;
import code.util.StringList;

public class InvokingConstructor {

    private final String className;

    private final String fieldName;
    private final int ordinal;

    private final ConstructorId id;

    private final Argument currentObject;

    private final CustList<Argument> arguments;

    private final InstancingStep instanceStep;
    private final StringList called;
    public InvokingConstructor(String _className, String _fieldName,
            int _ordinal,
            ConstructorId _id, Argument _currentObject,
            CustList<Argument> _arguments, InstancingStep _instanceStep,
            StringList _called) {
        className = _className;
        fieldName = _fieldName;
        ordinal = _ordinal;
        id = _id;
        currentObject = _currentObject;
        arguments = _arguments;
        instanceStep = _instanceStep;
        called = _called;
    }
    public String getClassName() {
        return className;
    }
    public String getFieldName() {
        return fieldName;
    }
    public int getOrdinal() {
        return ordinal;
    }
    public ConstructorId getId() {
        return id;
    }
    public Argument getCurrentObject() {
        return currentObject;
    }
    public CustList<Argument> getArguments() {
        return arguments;
    }
    public InstancingStep getInstanceStep() {
        return instanceStep;
    }
    public StringList getCalled() {
        return called;
    }
}
