package code.expressionlanguage.exceptions;
import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.CallConstructor;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.FctConstraints;
import code.util.CustList;
import code.util.StringList;

public class CustomFoundConstructorException extends RuntimeException {

    private final String className;

    private final String fieldName;

    private final FctConstraints id;

    private final Argument currentObject;

    private final CustList<Argument> arguments;

    private final InstancingStep instanceStep;
    private final StringList called;

    public CustomFoundConstructorException(String _className, String _fieldName, StringList _calledConstructors,
            FctConstraints _id, Argument _currentObject, CustList<Argument> _arguments, InstancingStep _instance) {
        className = _className;
        fieldName = _fieldName;
        id = _id;
        currentObject = _currentObject;
        called = _calledConstructors;
        arguments = _arguments;
        instanceStep = _instance;
    }

    public CallConstructor getCall() {
        CallConstructor call_ = new CallConstructor();
        call_.setFieldName(fieldName);
        call_.setArgument(currentObject);
        call_.getCalledConstructors().addAllElts(called);
        call_.setId(id);
        call_.setInstancingStep(instanceStep);
        return call_;
    }

    public String getClassName() {
        return className;
    }

    public FctConstraints getId() {
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
