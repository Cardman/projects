package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;

public final class CustomFoundConstructor implements CallingState {

    private final String className;
    private final ExecRootBlock type;

    private final String fieldName;
    private final int childIndex;

    private final ExecNamedFunctionBlock id;

    private final Argument currentObject;

    private final CustList<Argument> arguments;

    private final InstancingStep instanceStep;

    public CustomFoundConstructor(String _className,
                                  ExecRootBlock _type,
                                  String _fieldName, int _childIndex,
                                  ExecNamedFunctionBlock _id, Argument _currentObject, CustList<Argument> _arguments, InstancingStep _instance) {
        className = _className;
        type = _type;
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
        call_.setArgument(getCurrentObject());
        call_.setId(getId());
        return call_;
    }

    public ExecRootBlock getType() {
        return type;
    }

    public String getClassName() {
        return className;
    }

    public ExecNamedFunctionBlock getId() {
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
