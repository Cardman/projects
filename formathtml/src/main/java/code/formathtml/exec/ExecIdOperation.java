package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.IdOperation;
import code.util.CustList;

public final class ExecIdOperation extends ExecAbstractUnaryOperation {

    public ExecIdOperation(IdOperation _i) {
        super(_i);
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        setSimpleArgumentAna(chidren_.first().getArgument(), _conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        setSimpleArgument(chidren_.first().getArgument(), _conf);
    }
}
