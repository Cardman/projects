package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ClassCategory;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecValuesContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendValuesOperation extends RendLeafOperation implements RendCalculableOperation,RendCallable {

    private ExecValuesContent valuesContent;

    public RendValuesOperation(ExecOperationContent _content, ExecValuesContent _valuesContent) {
        super(_content);
        valuesContent = _valuesContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument argres_ = processCall(this, this, Argument.createVoid(),_nodes, _conf, null, _advStandards, _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    Argument getCommonArgument(Configuration _conf, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ valuesContent.getArgOffset(), _conf);
        return ExecInvokingOperation.tryGetEnumValues(_context.getExiting(), _context, valuesContent.getRootBlock(),ClassCategory.ENUM);
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        return getCommonArgument(_conf, _context);
    }
}
