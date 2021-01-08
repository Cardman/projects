package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class ExecRefTernaryOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private final int offsetLocal;

    public ExecRefTernaryOperation(ExecOperationContent _opCont, int _offsetLocal) {
        super(_opCont);
        offsetLocal = _offsetLocal;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(offsetLocal, _stack);
        AbstractWrapper res_ = getWrapper(_nodes);
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
        pair_.setWrapper(res_);
        setQuickNoConvertSimpleArgument(Argument.createVoid(),_conf,_nodes, _stack);
    }

    private AbstractWrapper getWrapper(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        AbstractWrapper arg_;
        if (BooleanStruct.isTrue(ExecTemplates.getArgumentPair(_nodes,ExecTemplates.getNode(getChildrenNodes(),0)).getArgument().getStruct())) {
            arg_ = ExecTemplates.getArgumentPair(_nodes,ExecTemplates.getNode(getChildrenNodes(),1)).getWrapper();
        } else {
            arg_ = ExecTemplates.getArgumentPair(_nodes,ExecTemplates.getNode(getChildrenNodes(),2)).getWrapper();
        }
        return arg_;
    }
}
