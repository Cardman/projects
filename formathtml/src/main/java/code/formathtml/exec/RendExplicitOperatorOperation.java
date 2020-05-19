package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ExplicitOperatorOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendExplicitOperatorOperation extends RendInvokingOperation implements RendCalculableOperation {

    private String lastType;

    private int naturalVararg;

    private ClassMethodId classMethodId;
    private int offsetOper;
    public RendExplicitOperatorOperation(ExplicitOperatorOperation _fct) {
        super(_fct);
        classMethodId = _fct.getClassMethodId();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        offsetOper = _fct.getOffsetOper();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offsetOper, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        MethodId id_ = classMethodId.getConstraints();
        CustList<Argument> firstArgs_ = listArguments(chidren_, naturalVararg, lastType, arguments_, _conf);
        Argument prev_ = new Argument();
        Argument res_ = ExecInvokingOperation.callPrepare(new AdvancedExiting(_conf),_conf.getContext(), "", id_, prev_, firstArgs_, null);
        processCall(_nodes,_conf,res_);
    }
}
