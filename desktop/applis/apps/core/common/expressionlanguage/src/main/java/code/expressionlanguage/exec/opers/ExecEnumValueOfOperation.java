package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.EnumValueOfOperation;
import code.util.IdMap;

public final class ExecEnumValueOfOperation extends ExecAbstractUnaryOperation {

    private String className;
    private int argOffset;
    private ExecRootBlock rootBlock;

    public ExecEnumValueOfOperation(EnumValueOfOperation _e, ContextEl _cont) {
        super(_e);
        className = _e.getClassName();
        argOffset = _e.getArgOffset();
        rootBlock = fetchType(_e,_cont);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        ExecOperationNode first_ = getFirstChild();
        Argument a_ = getArgument(_nodes,first_);
        Argument arg_ = getCommonArgument(a_, _conf);
        setSimpleArgument(arg_, _conf, _nodes);
    }
    Argument getCommonArgument(Argument _argument, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+argOffset, _conf);
        return ExecInvokingOperation.getEnumValue(new DefaultExiting(_conf),className,rootBlock, _argument, _conf);
    }

}
