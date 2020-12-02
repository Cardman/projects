package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.*;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation {

    private ExecInstancingCommonContent instancingCommonContent;
    private ExecInstancingStdContent instancingStdContent;

    private final ExecTypeFunction pair;
    public RendStandardInstancingOperation(ExecTypeFunction _pair, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_content, _intermediateDottedOperation);
        pair = _pair;
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
    }

    public RendStandardInstancingOperation(ExecRootBlock _rootBlock, ExecOperationContent _content, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_content,false);
        pair = new ExecTypeFunction(_rootBlock,null);
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = RendDynOperationNode.processCall(getArgument(previous_, _nodes, _conf, _context), _context).getValue();
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all,
                                Configuration _conf, ContextEl _context) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_ = instancingCommonContent.getClassName();
        String lastType_ = ExecTemplates.quickFormat(pair.getType(),className_, instancingCommonContent.getLastType());
        return ExecInvokingOperation.instancePrepareCust(_context, className_, pair, _previous, fectchArgs(_conf, _all,lastType_,instancingCommonContent.getNaturalVararg()), instancingStdContent.getFieldName(), instancingStdContent.getBlockIndex());
    }

}
