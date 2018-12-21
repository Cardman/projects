package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.NumericOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustNumericOperation extends ExecNumericOperation {

    private ClassMethodId classMethodId;

    public ExecCustNumericOperation(NumericOperation _n) {
        super(_n);
        classMethodId = _n.getClassMethodId();
    }
    @Override
    public final Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_, _conf);
        String classNameFound_ = classMethodId.getClassName();
        MethodId id_ = classMethodId.getConstraints();
        _conf.getContextEl().setCallMethod(new CustomFoundMethod(Argument.createVoid(), classNameFound_, id_, firstArgs_));
        return Argument.createVoid();
    }


}
