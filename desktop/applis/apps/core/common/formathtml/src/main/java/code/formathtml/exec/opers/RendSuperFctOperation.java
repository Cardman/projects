package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendSuperFctOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private ExecInstFctContent instFctContent;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public RendSuperFctOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent) {
        super(_content, _intermediateDottedOperation);
        instFctContent = _instFctContent;
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = processCall(this, this, previous_,_nodes, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(instFctContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_;
        String lastType_ = instFctContent.getLastType();
        int naturalVararg_ = instFctContent.getNaturalVararg();
        String classNameFound_;
        classNameFound_ = instFctContent.getClassName();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), classNameFound_, _previous.getStruct(), _conf.getContext()));
        if (_conf.getContext().hasException()) {
            return new Argument();
        }
        ContextEl _context = _conf.getContext();
        String argClassName_ = prev_.getStruct().getClassName(_context);
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf.getContext());
        lastType_ = ExecTemplates.quickFormat(rootBlock,fullClassNameFound_, lastType_);
        CustList<Argument> first_ = listNamedArguments(_all, chidren_).getArguments();
        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, first_);
        return ExecInvokingOperation.callPrepare(_conf.getContext().getExiting(),_conf.getContext(), classNameFound_,rootBlock, prev_, firstArgs_, null,named, MethodAccessKind.INSTANCE, "");
    }
}
