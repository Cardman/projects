package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        int off_ = StringUtil.getFirstPrintableCharIndex(getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _rendStack);
        int naturalVararg_ = getNaturalVararg();
        ExecFormattedRootBlock formattedType_ = instFctContent.getFormattedType();
        Argument prev_ = new Argument(ExecTemplates.getParent(getAnc(), previous_.getStruct(), _context, _rendStack.getStackCall()));
        Argument result_;
        if (_context.callsOrException(_rendStack.getStackCall())) {
            result_ = new Argument();
        } else {
            Struct pr_ = prev_.getStruct();
            String cl_ = pr_.getClassName(_context);
            String lastType_ = ExecTemplates.formatType(_context, pair.getType(), instFctContent.getLastType(), cl_);
            ExecOverrideInfo polymorph_ = ExecInvokingOperation.polymorphOrSuper(isStaticChoiceMethod(), _context, pr_, formattedType_, pair);
            ExecTypeFunction pair_ = polymorph_.getPair();
            ExecFormattedRootBlock classNameFound_ = polymorph_.getClassName();
            result_ = new MethodParamChecker(pair_, ExecInvokingOperation.fectchArgs(lastType_, naturalVararg_, null, _context, _rendStack.getStackCall(), buildInfos(_nodes)), MethodAccessKind.INSTANCE).checkParams(classNameFound_, prev_, null, _context, _rendStack.getStackCall());
        }
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(result_, _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

    public int getNaturalVararg() {
        return instFctContent.getNaturalVararg();
    }

    public int getAnc() {
        return instFctContent.getAnc();
    }

    public String getMethodName() {
        return instFctContent.getMethodName();
    }

    public boolean isStaticChoiceMethod() {
        return instFctContent.isStaticChoiceMethod();
    }

}
