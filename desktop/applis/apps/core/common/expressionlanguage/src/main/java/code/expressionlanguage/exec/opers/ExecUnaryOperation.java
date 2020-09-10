package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.UnaryOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.AliasNumber;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecUnaryOperation extends ExecAbstractUnaryOperation {
    private String oper;

    public ExecUnaryOperation(UnaryOperation _u) {
        super(_u);
        oper = _u.getOper();
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
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        ClassArgumentMatching to_ = getResultClass();
        return getArgument(_conf, _in, to_, oper);
    }

    public static Argument getArgument(ContextEl _conf, Argument _in, ClassArgumentMatching _to, String _oper) {
        Argument out_;
        if (StringList.quickEq(_oper, PLUS)) {
            out_ = new Argument(AliasNumber.idNumber(ClassArgumentMatching.convertToNumber(_in.getStruct()), _conf, _to));
        } else {
            out_ = new Argument(AliasNumber.opposite(ClassArgumentMatching.convertToNumber(_in.getStruct()), _conf, _to));
        }
        return out_;
    }
}
