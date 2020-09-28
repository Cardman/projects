package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStdFctContent;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendStdFctOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private ExecStdFctContent stdFctContent;

    public RendStdFctOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStdFctContent _stdFctContent) {
        super(_content, _intermediateDottedOperation);
        stdFctContent = _stdFctContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument argres_ = processCall(this, this, previous_,_nodes, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    public ClassMethodId getClassMethodId() {
        return stdFctContent.getClassMethodId();
    }

    public int getNaturalVararg() {
        return stdFctContent.getNaturalVararg();
    }

    public String getLastType() {
        return stdFctContent.getLastType();
    }

    public String getMethodName() {
        return stdFctContent.getMethodName();
    }

    public boolean isStaticMethod() {
        return stdFctContent.isStaticMethod();
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        return _conf.getAdvStandards().getCommonFctArgument(this,_previous,_all,_conf);
    }
}
