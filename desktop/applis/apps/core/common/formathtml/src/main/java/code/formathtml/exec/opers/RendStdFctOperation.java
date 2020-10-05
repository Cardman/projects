package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStdFctContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendStdFctOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private ExecStdFctContent stdFctContent;

    public RendStdFctOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStdFctContent _stdFctContent) {
        super(_content, _intermediateDottedOperation);
        stdFctContent = _stdFctContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument argres_ = processCall(this, this, previous_,_nodes, _conf, null, _advStandards, _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
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
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        return _advStandards.getCommonFctArgument(this,_previous,_all,_conf, _context);
    }
}
