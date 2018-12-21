package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.CmpOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCmpOperation extends ExecReflectableOpering {

    private ClassMethodId classMethodId;

    public ExecCmpOperation(CmpOperation _a) {
        super(_a);
        classMethodId = _a.getClassMethodId();
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
        String classNameFound_ = classMethodId.getClassName();
        MethodId id_ = classMethodId.getConstraints();
        _conf.getContextEl().setCallMethod(new CustomFoundMethod(Argument.createVoid(), classNameFound_, id_, firstArgs_));
        return Argument.createVoid();
    }

}
