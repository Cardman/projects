package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.util.CustomFoundConstructor;
import code.expressionlanguage.calls.util.InstancingStep;
import code.expressionlanguage.opers.CurrentInvokingConstructor;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;

public final class ExecCurrentInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecCurrentInvokingConstructor(CurrentInvokingConstructor _current) {
        super(_current);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);

        Argument arg_ = _conf.getOperationPageEl().getGlobalArgument();
        String clCurName_ = arg_.getObjectClassName(_conf.getContextEl());
        String gl_ = _conf.getOperationPageEl().getGlobalClass();
        gl_ = Templates.getIdFromAllTypes(gl_);
        gl_ = Templates.getFullTypeByBases(clCurName_, gl_, _conf);
        CustList<Argument> firstArgs_;
        String calledCtorTemp_ = gl_;
        String lastType_ = getLastType();
        lastType_ = Templates.quickFormat(gl_, lastType_, _conf);
        int natvararg_ = getNaturalVararg();
        ConstructorId ctorId_ = getConstId();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments, _conf);
        _conf.getContextEl().setCallCtor(new CustomFoundConstructor(calledCtorTemp_, EMPTY_STRING, -1, ctorId_, arg_, firstArgs_, InstancingStep.USING_THIS));
        return Argument.createVoid();
    }

}
