package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.UnaryOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.NumberStruct;
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

    @Override
    public void quickCalculate(Analyzable _conf) {
        UnaryOperation.tryGetArg(this,null,oper,_conf);
    }

    Argument getArgument(ExecutableCode _conf,
            Argument _in) {
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        ClassArgumentMatching to_ = getResultClass();
        return getArgument(_conf, _in, to_, oper);
    }

    public static Argument getArgument(ExecutableCode _conf, Argument _in, ClassArgumentMatching _to, String _oper) {
        Argument out_ = new Argument();
        if (StringList.quickEq(_oper, PLUS)) {
            out_.setStruct(NumberStruct.idNumber(ClassArgumentMatching.convertToNumber(_in.getStruct()), _conf, _to));
        } else {
            out_.setStruct(NumberStruct.opposite(ClassArgumentMatching.convertToNumber(_in.getStruct()), _conf, _to));
        }
        return out_;
    }
}
