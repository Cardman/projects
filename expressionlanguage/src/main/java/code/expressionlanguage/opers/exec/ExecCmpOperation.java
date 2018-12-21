package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.CmpOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCmpOperation extends ExecAbstractCmpOperation {

    public ExecCmpOperation(CmpOperation _cmp) {
        super(_cmp);
    }

    @Override
    public Argument calculateCmp(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode opOne_ = chidren_.first();
        ExecOperationNode opTwo_ = chidren_.last();
        Argument first_ = getArgument(_nodes,opOne_);
        Argument second_ = getArgument(_nodes,opTwo_);
        Argument arg_ = calculateCommon(first_, second_);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }
    @Override
    void quickCalculateNotNull(Analyzable _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode opOne_ = chidren_.first();
        ExecOperationNode opTwo_ = chidren_.last();
        Argument first_ = opOne_.getArgument();
        Argument second_ = opTwo_.getArgument();
        Argument arg_ = calculateCommon(first_, second_);
        setSimpleArgumentAna(arg_, _conf);
    }

    private Argument calculateCommon(Argument _one, Argument _two) {
        boolean complement_ = false;
        String op_ = getOp().trim();
        String useOp_ = op_;
        if (StringList.quickEq(op_, LOWER_EQ)) {
            complement_ = true;
            useOp_ = GREATER;
        } else if (StringList.quickEq(op_, GREATER_EQ)) {
            complement_ = true;
            useOp_ = LOWER;
        }
        BooleanStruct arg_;
        if (StringList.quickEq(useOp_, LOWER)) {
            arg_ = NumberStruct.quickCalculateLower(_one.getStruct(), isStringCompare(), _two.getStruct());
        } else {
            arg_ = NumberStruct.quickCalculateGreater(_one.getStruct(), isStringCompare(), _two.getStruct());
        }
        Boolean b_ = arg_.getInstance();
        if (complement_) {
            b_ = !b_;
        }
        return new Argument(new BooleanStruct(b_));
    }
}
