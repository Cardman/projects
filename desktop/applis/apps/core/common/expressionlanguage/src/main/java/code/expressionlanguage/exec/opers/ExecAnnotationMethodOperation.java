package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
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

    private ExecCallFctAnnotContent callFctAnnotContent;

    public ExecAnnotationMethodOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecCallFctAnnotContent _callFctAnnotContent) {
        super(_opCont, _intermediateDottedOperation);
        callFctAnnotContent = _callFctAnnotContent;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(Argument _previous, ContextEl _conf) {
        int off_ = StringUtil.getFirstPrintableCharIndex(callFctAnnotContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _conf);
        return getAnnotation(_previous, callFctAnnotContent.getClassMethodId(), _conf);
    }

    public static Argument getAnnotation(Argument _previous, String _name, ContextEl _conf) {
        Struct argPrev_ = _previous.getStruct();
        String npe_ = _conf.getStandards().getContent().getCoreNames().getAliasNullPe();
        if (!(argPrev_ instanceof AnnotationStruct)) {
            _conf.setCallingState(new ErrorStruct(_conf,npe_));
            return new Argument();
        }
        String clName_ = argPrev_.getClassName(_conf);
        Struct ret_ = ExecTemplates.getInstanceField(clName_, _name,_previous,_conf).getStruct();
        Argument a_;
        if (ret_ instanceof ArrayStruct) {
            ArrayStruct orig_ = (ArrayStruct) ret_;
            a_ = new Argument(orig_.swallowCopy());
        } else {
            a_ = new Argument(ret_);
        }
        return a_;
    }

}
