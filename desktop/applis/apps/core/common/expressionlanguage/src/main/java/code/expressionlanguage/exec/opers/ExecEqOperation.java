package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.EqOperation;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecEqOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private String oper;
    public ExecEqOperation(EqOperation _e) {
        super(_e);
        oper = _e.getOper();
    }

    private static boolean calculateEq(Argument _a, Argument _b) {
        return _a.getStruct().sameReference(_b.getStruct());
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode opOne_ = chidren_.first();
        ExecOperationNode opTwo_ = chidren_.last();
        Argument first_ = getArgument(_nodes,opOne_);
        Argument second_ = getArgument(_nodes,opTwo_);
        boolean complement_ = false;
        String op_ = oper.trim();
        if (StringList.quickEq(op_, DIFF)) {
            complement_ = true;
        }
        boolean b_ = calculateEq(first_, second_);
        if (complement_) {
            b_ = !b_;
        }
        Argument arg_ = new Argument(BooleanStruct.of(b_));
        setSimpleArgument(arg_, _conf, _nodes);
    }

}
