package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ExplicitOperation;
import code.expressionlanguage.opers.exec.ExecExplicitOperation;
import code.expressionlanguage.opers.util.MethodId;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendExplicitOperation extends RendAbstractUnaryOperation {
    private String className;
    private int offset;
    private MethodId castOpId;
    public RendExplicitOperation(ExplicitOperation _a) {
        super(_a);
        className = _a.getClassName();
        offset = _a.getOffset();
        castOpId = _a.getCastOpId();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = ExecExplicitOperation.prepare(false,castOpId,arguments_,className,_conf,false);
        processCall(_nodes,_conf,argres_);
    }
}
