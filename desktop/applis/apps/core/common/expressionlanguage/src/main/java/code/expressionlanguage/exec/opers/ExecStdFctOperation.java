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
import code.util.CustList;
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
        Argument res_ = getArgument(previous_,_nodes, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(stdFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stackCall);
        MethodId methodId_ = stdFctContent.getClassMethodId().getConstraints();
        String classNameFound_ = stdFctContent.getClassMethodId().getClassName();
        Argument prev_;
        if (!stdFctContent.isStaticMethod()) {
            Struct argPrev_ = _previous.getStruct();
            prev_ = new Argument(ExecTemplates.getParent(0, classNameFound_, argPrev_, _conf, _stackCall));
            if (_conf.callsOrException(_stackCall)) {
                return new Argument();
            }
        } else {
            prev_ = new Argument();
        }
        return callStd(_conf.getExiting(),_conf, classNameFound_, methodId_, prev_, getArgs(_nodes), _stackCall);
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes) {
        return fectchArgs(_nodes, stdFctContent.getLastType(), stdFctContent.getNaturalVararg()).getArguments();
    }

}
