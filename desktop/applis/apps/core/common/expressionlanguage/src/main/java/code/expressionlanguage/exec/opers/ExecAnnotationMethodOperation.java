package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.FctOperation;
import code.expressionlanguage.analyze.opers.InvokingOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.structs.AnnotationStruct;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringList;

public final class ExecAnnotationMethodOperation extends ExecInvokingOperation {

    private String methodName;

    private String classMethodId;

    protected ExecAnnotationMethodOperation(InvokingOperation _inv, AbstractCallFctOperation _fct) {
        super(_inv);
        methodName = _fct.getMethodName();
        classMethodId = _fct.getClassMethodId().getConstraints().getName();
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(Argument _previous, ContextEl _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        return getAnnotation(_previous,classMethodId, _conf);
    }

    public static Argument getAnnotation(Argument _previous, String _name, ContextEl _conf) {
        Struct argPrev_ = _previous.getStruct();
        String npe_ = _conf.getStandards().getAliasNullPe();
        if (!(argPrev_ instanceof AnnotationStruct)) {
            _conf.setException(new ErrorStruct(_conf,npe_));
            return new Argument();
        }
        String clName_ = _previous.getObjectClassName(_conf);
        Struct ret_ = getInstanceField(clName_, _name,_previous,_conf).getStruct();
        Argument a_ = new Argument();
        if (ret_ instanceof ArrayStruct) {
            ArrayStruct orig_ = (ArrayStruct) ret_;
            a_.setStruct(orig_.swallowCopy());
        } else {
            a_.setStruct(ret_);
        }
        return a_;
    }

}
