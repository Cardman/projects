package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class ExecTernaryOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private int offsetLocal;

    public ExecTernaryOperation(ExecOperationContent _opCont, int _offsetLocal) {
        super(_opCont);
        offsetLocal = _offsetLocal;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        setRelOffsetPossibleLastPage(offsetLocal, _conf);
        return getArgument(_arguments);
    }

    public static Argument getArgument(CustList<Argument> _arguments) {
        Argument arg_;
        if (BooleanStruct.isTrue(ExecTemplates.getFirstArgument(_arguments).getStruct())) {
            arg_ = ExecTemplates.getArgument(_arguments,IndexConstants.SECOND_INDEX);
        } else {
            arg_ = ExecTemplates.getLastArgument(_arguments);
        }
        return arg_;
    }
}
