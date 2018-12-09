package code.expressionlanguage.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;

public final class CustomFoundConstructor {

    private final String className;

    private final String fieldName;
    private final int childIndex;

    private final ConstructorId id;

    private final Argument currentObject;

    private final CustList<Argument> arguments;

    private final InstancingStep instanceStep;

    public CustomFoundConstructor(String _className, String _fieldName, int _childIndex,
            ConstructorId _id, Argument _currentObject, CustList<Argument> _arguments, InstancingStep _instance) {
        className = _className;
        fieldName = _fieldName;
        childIndex = _childIndex;
        id = _id;
        currentObject = _currentObject;
        arguments = _arguments;
        instanceStep = _instance;
    }

    public CallConstructor getCall() {
        CallConstructor call_ = new CallConstructor();
        call_.setFieldName(fieldName);
        call_.setChildIndex(childIndex);
        call_.setArgument(currentObject);
        call_.setId(id);
        return call_;
    }

    public String getClassName() {
        return className;
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
}
