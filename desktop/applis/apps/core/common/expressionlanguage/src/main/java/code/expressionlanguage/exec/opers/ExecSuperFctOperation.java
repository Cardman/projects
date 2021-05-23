package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecSuperFctOperation extends ExecSettableCallFctOperation {

    private final ExecInstFctContent instFctContent;
    private final ExecTypeFunction pair;
    public ExecSuperFctOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent, ExecArrContent _execArr) {
        super(_opCont, _intermediateDottedOperation, _execArr);
        instFctContent = _instFctContent;
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = StringUtil.getFirstPrintableCharIndex(instFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        ExecFormattedRootBlock formattedType_ = instFctContent.getFormattedType();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), previous_.getStruct(), _conf, _stack));
        Argument res_;
        if (_conf.callsOrException(_stack)) {
            res_ = new Argument();
        } else {
            Struct pr_ = prev_.getStruct();
            res_ = new MethodParamChecker(pair, fetchFormattedArgs(_conf,_stack, pr_, pair.getType(), instFctContent, null, buildInfos(_nodes)), MethodAccessKind.INSTANCE).checkParams(formattedType_, prev_, null, _conf, _stack);
        }
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }


}
