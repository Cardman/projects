package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCloneOperation extends ExecInvokingOperation {

    private String methodName;

    public ExecCloneOperation(ExecOperationContent _opCont, boolean _intermediateDottedOperation, String _methodName) {
        super(_opCont, _intermediateDottedOperation);
        methodName = _methodName;
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
        return cloneArray(_previous, _conf);
    }

    public static Argument cloneArray(Argument _previous, ContextEl _conf) {
        Struct argPrev_ = _previous.getStruct();
        String npe_ = _conf.getStandards().getAliasNullPe();
        if (!(argPrev_ instanceof ArrayStruct)) {
            _conf.setException(new ErrorStruct(_conf,npe_));
            return new Argument();
        }
        //clone object
        ArrayStruct arr_ = (ArrayStruct) argPrev_;
        ArrayStruct copy_ = arr_.swallowCopy();
        _conf.getInitializingTypeInfos().addSensibleElementsFromClonedArray(arr_, copy_);
        return new Argument(copy_);
    }

}
