package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.util.CustomFoundConstructor;
import code.expressionlanguage.calls.util.InstancingStep;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.CustList;
import code.util.StringList;

public final class CurrentInvokingConstructor extends AbstractInvokingConstructor {

    public CurrentInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(Analyzable _conf) {
        String clCurName_ = _conf.getGlobalClass();
        return new ClassArgumentMatching(clCurName_);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();

        Argument arg_ = _conf.getOperationPageEl().getGlobalArgument();
        String clCurName_ = arg_.getObjectClassName(_conf.getContextEl());
        String gl_ = _conf.getOperationPageEl().getGlobalClass();
        gl_ = Templates.getIdFromAllTypes(gl_);
        String base_ = Templates.getIdFromAllTypes(gl_);
        gl_ = Templates.getFullTypeByBases(clCurName_, gl_, _conf);
        CustList<Argument> firstArgs_;
        String calledCtor_ = base_;
        String calledCtorTemp_ = gl_;
        String lastType_ = getLastType();
        lastType_ = Templates.quickFormat(gl_, lastType_, _conf);
        int natvararg_ = getNaturalVararg();
        ConstructorId ctorId_ = getConstId();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments, _conf);
        StringList params_ = new StringList();
        String classFormat_ = calledCtor_;
        classFormat_ = Templates.getFullTypeByBases(clCurName_, classFormat_, _conf);
        if (classFormat_ == null) {
            _conf.setException(new ErrorStruct(new CustomError(_conf.joinPages()),cast_));
            Argument a_ = new Argument();
            return a_;
        }
        int j_ = 0;
        for (String c: ctorId_.getParametersTypes()) {
            String c_ = c;
            c_ = Templates.quickFormat(classFormat_, c_, _conf);
            if (j_ + 1 == ctorId_.getParametersTypes().size() && ctorId_.isVararg()) {
                c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
            }
            params_.add(c_);
            j_++;
        }
        processArgs(_conf, firstArgs_, params_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            Argument a_ = new Argument();
            return a_;
        }
        _conf.getContextEl().setCallCtor(new CustomFoundConstructor(calledCtorTemp_, EMPTY_STRING, -1, ctorId_, arg_, firstArgs_, InstancingStep.USING_THIS));
        return Argument.createVoid();
    }

}
