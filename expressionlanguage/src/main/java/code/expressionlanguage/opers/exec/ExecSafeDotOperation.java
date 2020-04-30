package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.SafeDotOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
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
        Argument a_ = getArgument(_nodes,o_);
        if (a_.isNull()) {
            a_ = new Argument(ClassArgumentMatching.convert(getResultClass(),NullStruct.NULL_VALUE,_conf));
            setQuickConvertSimpleArgument(a_, _conf, _nodes);
            return;
        }
        calculateDot(_nodes,_conf);
    }

}
