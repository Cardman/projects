package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecChoiceFctOperation extends ExecSettableCallFctOperation {

    private final ExecInstFctContent instFctContent;
    private final ExecTypeFunction pair;

    public ExecChoiceFctOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        instFctContent = _instFctContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = StringUtil.getFirstPrintableCharIndex(instFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        String classNameFound_ = getClassName();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), classNameFound_, previous_.getStruct(), _conf, _stack));
        Argument res_;
        if (_conf.callsOrException(_stack)) {
            res_ = new Argument();
        } else {
            res_ = callPrepare(_conf.getExiting(), _conf, classNameFound_, pair, prev_, null, fetchFormattedArgs(_nodes, _conf, prev_.getStruct(), getClassName(), pair.getType(), instFctContent.getLastType(), instFctContent.getNaturalVararg()), null, MethodAccessKind.INSTANCE, "", _stack);
        }
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public String getClassName() {
        return instFctContent.getClassName();
    }

}
