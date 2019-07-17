package code.formathtml.exec;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.IdOperation;
import code.util.CustList;

public final class RendIdOperation extends RendAbstractUnaryOperation {

    public RendIdOperation(IdOperation _i) {
        super(_i);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setSimpleArgument(chidren_.first().getArgument(), _conf);
    }
}
