package code.expressionlanguage.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.SafeDotOperation;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecSafeDotOperation extends ExecAbstractDotOperation {

    public ExecSafeDotOperation(SafeDotOperation _d) {
        super(_d);
    }


    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode o_ = chidren_.first();
        ExecOperationNode l_ = chidren_.last();
        Argument a_ = getArgument(_nodes,o_);
        if (a_.isNull()&&!(l_ instanceof ExecAbstractLambdaOperation)) {
            a_ = new Argument(ExecClassArgumentMatching.convert(_conf.getLastPage(), NullStruct.NULL_VALUE,_conf, getResultClass().getNames()));
            setQuickConvertSimpleArgument(a_, _conf, _nodes);
            return;
        }
        calculateDot(_nodes,_conf);
    }

}
