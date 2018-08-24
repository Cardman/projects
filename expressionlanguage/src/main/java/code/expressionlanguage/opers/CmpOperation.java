package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class CmpOperation extends AbstractCmpOperation {

    public CmpOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }


    @Override
    public Argument calculateCmp(IdMap<OperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode opOne_ = chidren_.first();
        OperationNode opTwo_ = chidren_.last();
        Argument first_ = _nodes.getVal(opOne_).getArgument();
        Argument second_ = _nodes.getVal(opTwo_).getArgument();
        Argument arg_ = calculateCommon(first_, second_);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    @Override
    void quickCalculateNotNull(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode opOne_ = chidren_.first();
        OperationNode opTwo_ = chidren_.last();
        Argument first_ = opOne_.getArgument();
        Argument second_ = opTwo_.getArgument();
        Argument arg_ = calculateCommon(first_, second_);
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    public void calculateCmp(ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        OperationNode opOne_ = chidren_.first();
        OperationNode opTwo_ = chidren_.last();
        Argument first_ = opOne_.getArgument();
        Argument second_ = opTwo_.getArgument();
        Argument arg_ = calculateCommon(first_, second_);
        setSimpleArgument(arg_, _conf);
    }

    private Argument calculateCommon(Argument _one, Argument _two) {
        boolean complement_ = false;
        String op_ = getOperations().getOperators().values().first().trim();
        String useOp_ = op_;
        if (StringList.quickEq(op_, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringList.quickEq(op_, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        Argument arg_;
        if (StringList.quickEq(useOp_, LOWER)) {
            arg_ = calculateLower(_one, isStringCompare(), _two);
        } else {
            arg_ = calculateGreater(_one, isStringCompare(), _two);
        }
        Boolean b_ = (Boolean) arg_.getObject();
        if (complement_) {
            b_ = !b_;
            arg_.setObject(b_);
        }
        return arg_;
    }
}
