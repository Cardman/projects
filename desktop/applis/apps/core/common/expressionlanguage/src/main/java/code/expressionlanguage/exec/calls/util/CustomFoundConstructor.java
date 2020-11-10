package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.Parameters;

public final class CustomFoundConstructor implements CallingState {

    private final String className;
    private final ExecRootBlock type;

    private final String fieldName;
    private final int childIndex;

    private final ExecMemberCallingsBlock id;

    private final Argument currentObject;

    private final Parameters arguments;

    private final InstancingStep instanceStep;

    public CustomFoundConstructor(String _className,
                                  ExecRootBlock _type,
                                  String _fieldName, int _childIndex,
                                  ExecMemberCallingsBlock _id, Argument _currentObject, Parameters _arguments, InstancingStep _instance) {
        className = _className;
        type = _type;
        fieldName = _fieldName;
        childIndex = _childIndex;
        id = _id;
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

    public ExecRootBlock getType() {
        return type;
    }

    public String getClassName() {
        return className;
    }

    public ExecMemberCallingsBlock getId() {
        return id;
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
