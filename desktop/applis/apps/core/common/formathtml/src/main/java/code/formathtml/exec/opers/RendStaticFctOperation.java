package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendStaticFctOperation extends RendInvokingOperation implements RendCalculableOperation {

    private ExecStaticFctContent staticFctContent;

    private ExecTypeFunction pair;
    public RendStaticFctOperation(ExecTypeFunction _pair, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent) {
        super(_content, _intermediateDottedOperation);
        staticFctContent = _staticFctContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument argres_ = RendDynOperationNode.processCall(getArgument(_nodes, _conf, _context), _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, ContextEl _context) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String lastType_ = staticFctContent.getLastType();
        int naturalVararg_ = staticFctContent.getNaturalVararg();
        Argument prev_ = new Argument();
        String classNameFound_ = staticFctContent.getClassName();
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, classNameFound_, pair, prev_,null, fectchArgs(_all,lastType_,naturalVararg_), null, staticFctContent.getKind(), "");
    }
}
