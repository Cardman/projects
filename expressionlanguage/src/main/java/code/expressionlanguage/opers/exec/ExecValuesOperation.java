package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ValuesOperation;
import code.util.IdMap;

public final class ExecValuesOperation extends ExecVariableLeafOperation {

    private String className;
    private int argOffset;

    public ExecValuesOperation(ValuesOperation _v) {
        super(_v);
        className = _v.getClassName();
        argOffset = _v.getArgOffset();
    }


    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        Argument arg_ = getCommonArgument(_conf);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    Argument getCommonArgument(ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+argOffset, _conf);
        return ExecInvokingOperation.getEnumValues(className, _conf);
    }

}
