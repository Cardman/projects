package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStdFctContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecStdFctOperation extends ExecInvokingOperation {

    private final ExecStdFctContent stdFctContent;

    public ExecStdFctOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStdFctContent _stdFctContent) {
        super(_opCont, _intermediateDottedOperation);
        stdFctContent = _stdFctContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = StringUtil.getFirstPrintableCharIndex(stdFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        MethodId methodId_ = stdFctContent.getClassMethodId().getConstraints();
        String classNameFound_ = stdFctContent.getClassMethodId().getClassName();
        Argument prev_;
        Argument res_ = null;
        if (!stdFctContent.isStaticMethod()) {
            Struct argPrev_ = previous_.getStruct();
            prev_ = new Argument(ExecTemplates.getParent(0, classNameFound_, argPrev_, _conf, _stack));
            if (_conf.callsOrException(_stack)) {
                res_ = new Argument();
            }
        } else {
            prev_ = new Argument();
        }
        if (res_ == null) {
            res_ = callStd(_conf.getExiting(), _conf, classNameFound_, methodId_, prev_, fectchArgs(_nodes, stdFctContent.getLastType(), stdFctContent.getNaturalVararg()).getArguments(), _stack);
        }
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
