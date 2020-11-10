package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendFctOperation extends RendInvokingOperation implements RendCalculableOperation {

    private ExecInstFctContent instFctContent;

    private ExecTypeFunction pair;
    public RendFctOperation(ExecTypeFunction _pair, ExecOperationContent _content, ExecInstFctContent _instFctContent, boolean _intermediateDottedOperation) {
        super(_content, _intermediateDottedOperation);
        instFctContent = _instFctContent;
        pair= _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = RendDynOperationNode.processCall(getArgument(previous_, _nodes, _conf, _context), _context);
        setSimpleArgument(argres_,_conf,_nodes, _context);
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, ContextEl _context) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringUtil.getFirstPrintableCharIndex(getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_;
        String lastType_ = getLastType();
        int naturalVararg_ = getNaturalVararg();
        String classNameFound_;
        classNameFound_ = instFctContent.getClassName();
        Argument prev_ = new Argument(ExecTemplates.getParent(getAnc(), classNameFound_, _previous.getStruct(), _context));
        if (_context.callsOrException()) {
            return new Argument();
        }
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        Struct pr_ = prev_.getStruct();
        String cl_ = pr_.getClassName(_context);
        String clGen_ = ExecTemplates.getSuperGeneric(cl_, base_, _context);
        lastType_ = ExecTemplates.quickFormat(pair.getType(), clGen_, lastType_);
        firstArgs_ = RendInvokingOperation.listArguments(chidren_, naturalVararg_, lastType_, first_);
        ExecOverrideInfo polymorph_ =  ExecInvokingOperation.polymorphOrSuper(isStaticChoiceMethod(), _context,pr_,classNameFound_,pair);
        ExecTypeFunction pair_ = polymorph_.getPair();
        classNameFound_ = polymorph_.getClassName();
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, classNameFound_, pair_, prev_,null, firstArgs_, null, MethodAccessKind.INSTANCE, "");
    }

    public int getNaturalVararg() {
        return instFctContent.getNaturalVararg();
    }

    public int getAnc() {
        return instFctContent.getAnc();
    }

    public String getLastType() {
        return instFctContent.getLastType();
    }

    public String getMethodName() {
        return instFctContent.getMethodName();
    }

    public boolean isStaticChoiceMethod() {
        return instFctContent.isStaticChoiceMethod();
    }

}
