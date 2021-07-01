package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

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
        int off_ = instFctContent.getMethodName();
        setRelOffsetPossibleLastPage(off_, _stack);
        Argument res_ = prep(_conf, _stack, previous_, buildInfos(_nodes), instFctContent, pair);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Argument prep(ContextEl _conf, StackCall _stack, Argument _previous, CustList<ExecOperationInfo> _infos, ExecInstFctContent _instFctContent, ExecTypeFunction _pair) {
        ExecFormattedRootBlock formattedType_ = _instFctContent.getFormattedType();
        Argument prev_ = new Argument(ExecTemplates.getParent(_instFctContent.getAnc(), _previous.getStruct(), _conf, _stack));
        Argument res_;
        if (_conf.callsOrException(_stack)) {
            res_ = new Argument();
        } else {
            res_ = new MethodParamChecker(_pair, fetchFormattedArgs(_conf, _stack, prev_.getStruct(), _pair.getType(), _instFctContent, null, _infos), MethodAccessKind.INSTANCE).checkParams(formattedType_, prev_, null, _conf, _stack);
        }
        return res_;
    }

}
