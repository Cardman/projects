package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStdFctContent;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendStdFctOperation extends RendInvokingOperation implements RendCalculableOperation {

    private final ExecStdFctContent stdFctContent;

    public RendStdFctOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStdFctContent _stdFctContent) {
        super(_content, _intermediateDottedOperation);
        stdFctContent = _stdFctContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(_advStandards.getCommonFctArgument(this, previous_, _nodes, _conf, _context, _stack, _rendStack), _context, _stack);
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
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

}
