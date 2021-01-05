package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecCloneOperation extends ExecInvokingOperation {

    private final String methodName;

    public ExecCloneOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, String _methodName) {
        super(_opCont, _intermediateDottedOperation);
        methodName = _methodName;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        int off_ = StringUtil.getFirstPrintableCharIndex(methodName);
        setRelOffsetPossibleLastPage(off_, _stack);
        Argument res_ = cloneArray(previous_, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Argument cloneArray(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        Struct argPrev_ = _previous.getStruct();
        String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
        if (!(argPrev_ instanceof ArrayStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return new Argument();
        }
        //clone object
        ArrayStruct arr_ = (ArrayStruct) argPrev_;
        ArrayStruct copy_ = arr_.swallowCopy();
        _stackCall.getInitializingTypeInfos().addSensibleElementsFromClonedArray(arr_, copy_);
        return new Argument(copy_);
    }

}
