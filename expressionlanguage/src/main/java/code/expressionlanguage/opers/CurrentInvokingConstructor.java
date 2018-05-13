package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ArgumentCall;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.InvokingConstructor;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
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
    ArgumentCall getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        int off_ = getOffsetOper();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCast();

        Argument arg_ = _conf.getOperationPageEl().getGlobalArgument();
        String clCurName_ = arg_.getObjectClassName(_conf.getContextEl());
        String gl_ = _conf.getOperationPageEl().getGlobalClass();
        gl_ = StringList.getAllTypes(gl_).first();
        String base_ = StringList.getAllTypes(gl_).first();
        gl_ = Templates.getFullTypeByBases(clCurName_, gl_, _conf);
        CustList<Argument> firstArgs_;
        String calledCtor_ = base_;
        String calledCtorTemp_ = gl_;
        String lastType_ = getLastType();
        lastType_ = Templates.format(gl_, lastType_, _conf);
        int natvararg_ = getNaturalVararg();
        ConstructorId ctorId_ = getConstId();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments, _conf);
        StringList params_ = new StringList();
        String classFormat_ = calledCtor_;
        classFormat_ = Templates.getFullTypeByBases(clCurName_, classFormat_, _conf);
        if (classFormat_ == null) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
            Argument a_ = new Argument();
            return ArgumentCall.newArgument(a_);
        }
        int j_ = 0;
        for (String c: ctorId_.getParametersTypes()) {
            String c_ = c;
            c_ = Templates.format(classFormat_, c_, _conf);
            if (j_ + 1 == ctorId_.getParametersTypes().size() && ctorId_.isVararg()) {
                c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
            }
            params_.add(c_);
            j_++;
        }
        processArgs(_conf, firstArgs_, params_);
        if (_conf.getException() != null) {
            Argument a_ = new Argument();
            return ArgumentCall.newArgument(a_);
        }
        InvokingConstructor inv_ = new InvokingConstructor(calledCtorTemp_, EMPTY_STRING, -1, ctorId_, arg_, firstArgs_, InstancingStep.USING_THIS);
        return ArgumentCall.newCall(inv_);
    }

}
