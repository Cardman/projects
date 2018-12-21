package code.expressionlanguage.opers;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.util.CustList;
import code.util.StringList;

public final class CmpOperation extends AbstractCmpOperation {

    public CmpOperation(int _index,
            int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
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
