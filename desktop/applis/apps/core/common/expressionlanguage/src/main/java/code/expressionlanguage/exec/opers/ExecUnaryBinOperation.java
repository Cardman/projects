package code.expressionlanguage.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.UnaryBinOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NumberStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecUnaryBinOperation extends ExecAbstractUnaryOperation {

    public ExecUnaryBinOperation(UnaryBinOperation _u) {
        super(_u);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode op_ = chidren_.first();
        Argument arg_ = getArgument(_nodes,op_);
        Argument a_ = getArgument(_conf, arg_);
        setSimpleArgument(a_, _conf, _nodes);
    }

    Argument getArgument(ContextEl _conf,
            Argument _in) {
        Argument out_ = new Argument();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        ClassArgumentMatching res_ = getResultClass();
        out_.setStruct(NumberStruct.negBinNumber(ClassArgumentMatching.convertToNumber(_in.getStruct()), _conf, res_));
        return out_;
    }
}
