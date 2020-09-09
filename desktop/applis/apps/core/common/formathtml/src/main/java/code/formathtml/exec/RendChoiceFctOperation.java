package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ChoiceFctOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendChoiceFctOperation extends RendInvokingOperation implements RendCalculableOperation, RendCallable {

    private String methodName;

    private String className;

    private String lastType;

    private int naturalVararg;
    private int anc;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public RendChoiceFctOperation(ChoiceFctOperation _choice, ContextEl _context, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_choice);
        methodName = _choice.getMethodName();
        className = ExecOperationNode.getType(_context,_choice.getClassMethodId());
        lastType = _choice.getLastType();
        naturalVararg = _choice.getNaturalVararg();
        anc = _choice.getAnc();
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
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_;
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        classNameFound_ = className;
        prev_.setStruct(ExecTemplates.getParent(anc, classNameFound_, _previous.getStruct(), _conf.getContext()));
        if (_conf.getContext().hasException()) {
            Argument a_ = new Argument();
            return a_;
        }
        String argClassName_ = prev_.getObjectClassName(_conf.getContext());
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf.getContext());
        lastType_ = ExecTemplates.quickFormat(rootBlock,fullClassNameFound_, lastType_);
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, first_);
        return ExecInvokingOperation.callPrepare(new AdvancedExiting(_conf),_conf.getContext(), classNameFound_,rootBlock, prev_, firstArgs_, null,named,MethodAccessKind.INSTANCE, "");
    }

}
