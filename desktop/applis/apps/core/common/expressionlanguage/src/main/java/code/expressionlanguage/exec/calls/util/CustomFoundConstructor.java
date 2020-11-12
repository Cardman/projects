package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;

public final class CustomFoundConstructor implements CallingState {

    private final String className;
    private final ExecTypeFunction pair;

    private final String fieldName;
    private final int childIndex;

    private final Argument currentObject;

    private final Parameters arguments;

    private final InstancingStep instanceStep;

    public CustomFoundConstructor(String _className,
                                  ExecTypeFunction _pair,
                                  String _fieldName, int _childIndex,
                                  Argument _currentObject, Parameters _arguments, InstancingStep _instance) {
        className = _className;
        pair = _pair;
        fieldName = _fieldName;
        childIndex = _childIndex;
        currentObject = _currentObject;
        arguments = _arguments;
        instanceStep = _instance;
    }

    @Override
    public AbstractPageEl processAfterOperation(ContextEl _context) {
        return ExecutingUtil.createInstancing(_context,this);
    }

    public int getChildIndex() {
        return childIndex;
    }

    public String getFieldName() {
        return fieldName;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

    public String getClassName() {
        return className;
    }

    public Argument getCurrentObject() {
        return currentObject;
    }

    public Parameters getArguments() {
        return arguments;
    }

    public InstancingStep getInstanceStep() {
        return instanceStep;
    }
}
