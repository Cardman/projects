package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecValuesContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecValuesOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation {

    private final ExecValuesContent valuesContent;

    public ExecValuesOperation(ExecOperationContent _opCont, ExecValuesContent _valuesContent) {
        super(_opCont);
        valuesContent = _valuesContent;
    }


    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(valuesContent.getArgOffset(), _stack);
        Struct arg_ = IndirectCalledFctUtil.tryGetEnumValues(_conf.getExiting(), _conf, valuesContent.getRootBlock(), ClassCategory.ENUM, _stack);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

}
