package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStdFctContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecStdFctOperation extends ExecSettableCallFctOperation {

    private final ExecStdFctContent stdFctContent;

    public ExecStdFctOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecStdFctContent _stdFctContent, ExecArrContent _arrContent) {
        super(_opCont, _intermediateDottedOperation,_arrContent);
        stdFctContent = _stdFctContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = StringUtil.getFirstPrintableCharIndex(stdFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        Argument res_ = prep(_conf, _stack, previous_, buildInfos(_nodes), stdFctContent);
        setResult(res_, _conf, _nodes, _stack);
    }

    public static Argument prep(ContextEl _conf, StackCall _stack, Argument _previous, CustList<ExecOperationInfo> _infos, ExecStdFctContent _stdFctContent) {
        MethodId methodId_ = _stdFctContent.getClassMethodId().getConstraints();
        String classNameFound_ = _stdFctContent.getClassMethodId().getClassName();
        Argument prev_;
        Argument res_ = null;
        if (!_stdFctContent.isStaticMethod()) {
            Struct argPrev_ = _previous.getStruct();
            prev_ = new Argument(ExecTemplates.getParent(0, argPrev_, _conf, _stack));
            if (_conf.callsOrException(_stack)) {
                res_ = new Argument();
            }
        } else {
            prev_ = new Argument();
        }
        if (res_ == null) {
            res_ = callStd(_conf.getExiting(), _conf, classNameFound_, methodId_, prev_, fectchArgs(_stdFctContent.getLastType(), _stdFctContent.getNaturalVararg(),null, _conf, _stack, _infos), _stack);
        }
        return res_;
    }

}
