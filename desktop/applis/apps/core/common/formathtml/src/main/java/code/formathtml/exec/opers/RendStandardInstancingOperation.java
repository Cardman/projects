package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.*;
import code.formathtml.Configuration;
import code.formathtml.exec.RendArgumentList;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendStandardInstancingOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private ExecInstancingCommonContent instancingCommonContent;
    private ExecInstancingStdContent instancingStdContent;

    private ExecRootBlock rootBlock;
    private ExecNamedFunctionBlock ctor;
    public RendStandardInstancingOperation(ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_content, _intermediateDottedOperation);
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
        rootBlock = _rootBlock;
        ctor = _ctor;
    }

    public RendStandardInstancingOperation(ExecRootBlock _rootBlock, ExecOperationContent _content, ExecInstancingCommonContent _instancingCommonContent, ExecInstancingStdContent _instancingStdContent) {
        super(_content,false);
        instancingCommonContent = _instancingCommonContent;
        instancingStdContent = _instancingStdContent;
        rootBlock = _rootBlock;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = processCall(this, this, previous_,_nodes, _conf, null, _advStandards, _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all,
                                Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String className_;
        className_ = instancingCommonContent.getClassName();
        String lastType_ = ExecTemplates.quickFormat(rootBlock,className_, instancingCommonContent.getLastType());
        RendArgumentList args_ = listNamedArguments(_all, chidren_);
        CustList<Argument> first_ = args_.getArguments();
        CustList<RendDynOperationNode> filter_ = args_.getFilter();
        CustList<Argument> firstArgs_ = listArguments(filter_, instancingCommonContent.getNaturalVararg(), lastType_, first_);
        return ExecInvokingOperation.instancePrepareFormat(_context, className_,rootBlock,ctor, _previous, firstArgs_, instancingStdContent.getFieldName(), instancingStdContent.getBlockIndex());
    }

}
