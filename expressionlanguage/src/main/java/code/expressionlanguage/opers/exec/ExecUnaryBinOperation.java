package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.UnaryBinOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.NumberStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecUnaryBinOperation extends ExecAbstractUnaryOperation {

    public ExecUnaryBinOperation(UnaryBinOperation _u) {
        super(_u);
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode op_ = chidren_.first();
        Argument arg_ = getArgument(_nodes,op_);
        Argument a_ = getArgument(_conf, arg_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = chidren_.first().getArgument();
        Argument out_ = new Argument();
        if (arg_.isNull()) {
            return;
        }
        ClassArgumentMatching res_ = getResultClass();
        out_.setStruct(NumberStruct.negBinNumber((NumberStruct)arg_.getStruct(), _conf, res_));
        setSimpleArgumentAna(out_, _conf);
    }

    Argument getArgument(ExecutableCode _conf,
            Argument _in) {
        Argument out_ = new Argument();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        ClassArgumentMatching res_ = getResultClass();
        out_.setStruct(NumberStruct.negBinNumber((NumberStruct)_in.getStruct(), _conf, res_));
        return out_;
    }
}
