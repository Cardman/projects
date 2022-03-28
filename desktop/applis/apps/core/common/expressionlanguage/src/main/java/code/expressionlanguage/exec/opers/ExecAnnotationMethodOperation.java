package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
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
        int off_ = StringUtil.getFirstPrintableCharIndex(callFctAnnotContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _stack);
        Argument res_ = getAnnotation(previous_, callFctAnnotContent.getClassMethodId(), _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Argument getAnnotation(Argument _previous, String _name, ContextEl _conf, StackCall _stackCall) {
        Struct argPrev_ = _previous.getStruct();
        String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
        if (!(argPrev_ instanceof AnnotationStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, npe_, _stackCall)));
            return new Argument();
        }
        String clName_ = argPrev_.getClassName(_conf);
        Struct ret_ = ExecFieldTemplates.getInstanceField(_previous,_conf, _stackCall, new ClassField(clName_, _name)).getStruct();
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
