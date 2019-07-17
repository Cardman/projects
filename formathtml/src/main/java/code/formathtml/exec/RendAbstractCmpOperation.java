package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.CmpOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;

public class RendAbstractCmpOperation extends RendMethodOperation implements RendCalculableOperation {

    private boolean stringCompare;
    private String op;

    public RendAbstractCmpOperation(CmpOperation _a) {
        super(_a);
        stringCompare = _a.isStringCompare();
        op = _a.getOp();
    }

    public final boolean isStringCompare() {
        return stringCompare;
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendDynOperationNode opOne_ = chidren_.first();
        RendDynOperationNode opTwo_ = chidren_.last();
        Argument first_ = opOne_.getArgument();
        Argument second_ = opTwo_.getArgument();
        Argument arg_ = calculateCommon(first_, second_);
        setSimpleArgument(arg_, _conf);
    }
    private Argument calculateCommon(Argument _one, Argument _two) {
        String op_ = getOp().trim();
        if (stringCompare) {
            return CmpOperation.calculateCommonStr(_one, _two, op_);
        }
        return CmpOperation.calculateCommonNb(_one, _two, op_);
    }

    public String getOp() {
        return op;
    }

}
