package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ExplicitOperatorOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.util.CustList;
import code.util.IdMap;

public final class ExecExplicitOperatorOperation extends ExecInvokingOperation {

    private String lastType;

    private int naturalVararg;

    private ClassMethodId classMethodId;
    private int offsetOper;
    public ExecExplicitOperatorOperation(ExplicitOperatorOperation _fct) {
        super(_fct);
        classMethodId = _fct.getClassMethodId();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        offsetOper = _fct.getOffsetOper();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        MethodId id_ = classMethodId.getConstraints();
        CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType, arguments_, _conf);
        Argument prev_ = new Argument();
        callPrepare(new DefaultExiting(_conf),_conf, "", id_, prev_, firstArgs_, null);
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public int getOffsetOper() {
        return offsetOper;
    }
}
