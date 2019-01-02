package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.EnumValueOfOperation;
import code.util.IdMap;

public final class ExecEnumValueOfOperation extends ExecReflectableOpering {

    private String className;
    private int argOffset;

    public ExecEnumValueOfOperation(EnumValueOfOperation _e) {
        super(_e);
        className = _e.getClassName();
        argOffset = _e.getArgOffset();
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        ExecOperationNode first_ = getFirstChild();
        Argument a_ = getArgument(_nodes,first_);
        Argument arg_ = getCommonArgument(a_, _conf);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    Argument getCommonArgument(Argument _argument, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+argOffset, _conf);
        return ExecInvokingOperation.getEnumValue(className, _argument, _conf);
    }

}
