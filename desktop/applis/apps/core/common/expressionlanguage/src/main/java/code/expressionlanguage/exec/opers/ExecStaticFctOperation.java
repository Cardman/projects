package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.MethodParamChecker;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecStaticFctOperation extends ExecSettableCallFctOperation {

    private final ExecStaticFctContent staticFctContent;

    private final ExecTypeFunction pair;
    private final ExecFormattedRootBlock formattedType;

    public ExecStaticFctOperation(ExecTypeFunction _pair, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        staticFctContent = _staticFctContent;
        pair = _pair;
        formattedType = _staticFctContent.getFormattedType();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        int off_ = StringUtil.getFirstPrintableCharIndex(staticFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        ExecFormattedRootBlock classNameFound_ = ClassMethodId.formatType(formattedType, staticFctContent.getKind(), _stack);
        Argument res_;
        if (_conf.getExiting().hasToExit(_stack, classNameFound_.getFormatted())) {
            res_ = Argument.createVoid();
        } else {
            Argument prev_ = new Argument();
            String lastType_ = ClassMethodId.formatType(classNameFound_, staticFctContent.getLastType(), staticFctContent.getKind());
            res_ = new MethodParamChecker(pair, fectchArgs(_nodes, lastType_, staticFctContent.getNaturalVararg(), null), staticFctContent.getKind()).checkParams(classNameFound_, prev_, null, _conf, _stack);
        }
        setResult(res_, _conf, _nodes, _stack);
    }


}
