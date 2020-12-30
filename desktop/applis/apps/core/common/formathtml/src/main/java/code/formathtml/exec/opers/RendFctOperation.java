package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendFctOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecInstFctContent instFctContent;

    private final ExecTypeFunction pair;
    public RendFctOperation(ExecTypeFunction _pair, ExecOperationContent _content, ExecInstFctContent _instFctContent, boolean _intermediateDottedOperation, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        instFctContent = _instFctContent;
        pair= _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(getArgument(previous_, _nodes, _context, _stack, _rendStack), _context, _stack);
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStackCall);
        String lastType_ = getLastType();
        int naturalVararg_ = getNaturalVararg();
        String classNameFound_;
        classNameFound_ = instFctContent.getClassName();
        Argument prev_ = new Argument(ExecTemplates.getParent(getAnc(), classNameFound_, _previous.getStruct(), _context, _stackCall));
        if (_context.callsOrException(_stackCall)) {
            return new Argument();
        }
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        Struct pr_ = prev_.getStruct();
        String cl_ = pr_.getClassName(_context);
        String clGen_ = ExecTemplates.getSuperGeneric(cl_, base_, _context);
        lastType_ = ExecTemplates.quickFormat(pair.getType(), clGen_, lastType_);
        ExecOverrideInfo polymorph_ =  ExecInvokingOperation.polymorphOrSuper(isStaticChoiceMethod(), _context,pr_,classNameFound_,pair);
        ExecTypeFunction pair_ = polymorph_.getPair();
        classNameFound_ = polymorph_.getClassName();
        return ExecInvokingOperation.callPrepare(_context.getExiting(), _context, classNameFound_, pair_, prev_,null, fectchArgs(_all,lastType_,naturalVararg_, _rendStackCall), null, MethodAccessKind.INSTANCE, "", _stackCall);
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
