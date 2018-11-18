package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.CustomFoundConstructor;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.InstancingStep;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class SuperInvokingConstructor extends AbstractInvokingConstructor {

    public SuperInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(Analyzable _conf) {
        Classes classes_ = _conf.getClasses();
        String clCurName_ = _conf.getGlobalClass();
        String base_ = Templates.getIdFromAllTypes(clCurName_);
        UniqueRootedBlock unique_ =(UniqueRootedBlock) classes_.getClassBody(base_);
        String superClass_ = Templates.quickFormat(clCurName_, unique_.getImportedDirectGenericSuperClass(), _conf);
        return new ClassArgumentMatching(superClass_);
    }

    @Override
    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        Classes classes_ = _conf.getClasses();
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
        UniqueRootedBlock unique_ =(UniqueRootedBlock) classes_.getClassBody(base_);
        CustList<Argument> firstArgs_;
        String calledCtor_ = base_;
        String calledCtorTemp_ = gl_;
        String superClass_ = Templates.quickFormat(gl_, unique_.getImportedDirectGenericSuperClass(), _conf);
        String superClassBase_ = Templates.getIdFromAllTypes(superClass_);
        String lastType_ = getLastType();
        lastType_ = Templates.quickFormat(superClass_, lastType_, _conf);
        int natvararg_ = getNaturalVararg();
        ConstructorId ctorId_ = getConstId();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, _arguments, _conf);
        calledCtor_ = superClassBase_;
        calledCtorTemp_ = superClass_;
        StringList params_ = new StringList();
        String classFormat_ = calledCtor_;
        classFormat_ = Templates.getFullTypeByBases(clCurName_, classFormat_, _conf);
        if (classFormat_ == null) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
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
        _conf.getContextEl().setCallCtor(new CustomFoundConstructor(calledCtorTemp_, EMPTY_STRING, -1, ctorId_, arg_, firstArgs_, InstancingStep.USING_SUPER));
        return Argument.createVoid();
    }

}
