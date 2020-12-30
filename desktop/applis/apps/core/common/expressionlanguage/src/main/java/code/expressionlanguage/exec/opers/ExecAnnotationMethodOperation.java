package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecCallFctAnnotContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.AnnotationStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecAnnotationMethodOperation extends ExecInvokingOperation {

    private final ExecCallFctAnnotContent callFctAnnotContent;

    public ExecAnnotationMethodOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecCallFctAnnotContent _callFctAnnotContent) {
        super(_opCont, _intermediateDottedOperation);
        callFctAnnotContent = _callFctAnnotContent;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        Argument res_ = getArgument(previous_, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    Argument getArgument(Argument _previous, ContextEl _conf, StackCall _stackCall) {
        int off_ = StringUtil.getFirstPrintableCharIndex(callFctAnnotContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stackCall);
        return getAnnotation(_previous, callFctAnnotContent.getClassMethodId(), _conf, _stackCall);
    }

    public static Argument getAnnotation(Argument _previous, String _name, ContextEl _conf, StackCall _stackCall) {
        Struct argPrev_ = _previous.getStruct();
        String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
        if (!(argPrev_ instanceof AnnotationStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return new Argument();
        }
        String clName_ = argPrev_.getClassName(_conf);
        Struct ret_ = ExecTemplates.getInstanceField(clName_, _name,_previous,_conf, _stackCall).getStruct();
        return swallowCopy(ret_);
    }

    public static Argument swallowCopy(Struct _ret) {
        Argument a_;
        if (_ret instanceof ArrayStruct) {
            ArrayStruct orig_ = (ArrayStruct) _ret;
            a_ = new Argument(orig_.swallowCopy());
        } else {
            a_ = new Argument(_ret);
        }
        return a_;
    }

}
