package code.expressionlanguage.opers.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.NullSafeOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecNullSafeOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {
    public ExecNullSafeOperation(Operable _m) {
        super(_m);
    }

    @Override
    public void tryCalculateNode(ContextEl _conf) {
        NullSafeOperation.tryGetResult(_conf,this);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode first_ = chidren_.first();
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (abs_ != NullStruct.NULL_VALUE) {
            f_ = new Argument(ClassArgumentMatching.convert(_conf.getLastPage(),getResultClass(),abs_,_conf));
            setQuickConvertSimpleArgument(f_, _conf, _nodes);
            return;
        }
        ExecOperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = getArgument(_nodes,last_);
        a_ = new Argument(ClassArgumentMatching.convert(_conf.getLastPage(),getResultClass(),a_.getStruct(),_conf));
        setSimpleArgument(a_, _conf, _nodes);

    }
}
