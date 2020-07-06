package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.blocks.ExecUniqueRootedBlock;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.inherits.ClassArgumentMatching;

public final class SuperInvokingConstructor extends AbstractInvokingConstructor {

    public SuperInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(ContextEl _conf) {
        Classes classes_ = _conf.getClasses();
        String clCurName_ = _conf.getAnalyzing().getGlobalClass();
        String base_ = StringExpUtil.getIdFromAllTypes(clCurName_);
        ExecRootBlock clBody_ = classes_.getClassBody(base_);
        if (!(clBody_ instanceof ExecUniqueRootedBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            call_.buildError(_conf.getAnalysisMessages().getCallCtorSuperClassEnumSingleton());
            _conf.getAnalyzing().getLocalizer().addError(call_);
            getErrs().add(call_.getBuiltError());
            return null;
        }
        ExecUniqueRootedBlock unique_ =(ExecUniqueRootedBlock) clBody_;
        String superClass_ = Templates.quickFormat(clCurName_, unique_.getImportedDirectGenericSuperClass(), _conf);
        return new ClassArgumentMatching(superClass_);
    }

}
