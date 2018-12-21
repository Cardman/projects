package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AbstractCmpOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecAbstractCmpOperation extends ExecReflectableOpering {

    private boolean stringCompare;
    private ClassMethodId classMethodId;
    private String op;

    public ExecAbstractCmpOperation(AbstractCmpOperation _a) {
        super(_a);
        stringCompare = _a.isStringCompare();
        classMethodId = _a.getClassMethodId();
        op = _a.getOp();
    }

    public final boolean isStringCompare() {
        return stringCompare;
    }

    @Override
    public final void quickCalculate(Analyzable _conf) {
        if (classMethodId != null || !_conf.isOkNumOp()) {
            return;
        }
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument first_ = chidren_.first().getArgument();
        if (first_.isNull()) {
            return;
        }
        Argument second_ = chidren_.last().getArgument();
        if (second_.isNull()) {
            return;
        }
        quickCalculateNotNull(_conf);
    }

    abstract void quickCalculateNotNull(Analyzable _conf);

    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        if (classMethodId != null) {
            CustList<ExecOperationNode> chidren_ = getChildrenNodes();
            CustList<Argument> arguments_ = getArguments(_nodes, this);
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
            String classNameFound_ = classMethodId.getClassName();
            MethodId id_ = classMethodId.getConstraints();
            _conf.getContextEl().setCallMethod(new CustomFoundMethod(Argument.createVoid(), classNameFound_, id_, firstArgs_));
            return Argument.createVoid();
        }
        return calculateCmp(_nodes, _conf);
    }
    abstract Argument calculateCmp(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf);

    public String getOp() {
        return op;
    }

}
