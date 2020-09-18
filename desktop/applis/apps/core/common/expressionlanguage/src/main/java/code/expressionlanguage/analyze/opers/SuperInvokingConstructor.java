package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.blocks.UniqueRootedBlock;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;

public final class SuperInvokingConstructor extends AbstractInvokingConstructor {

    public SuperInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    AnaClassArgumentMatching getFrom(ContextEl _conf) {
        String clCurName_ = _conf.getAnalyzing().getGlobalClass();
        String base_ = StringExpUtil.getIdFromAllTypes(clCurName_);
        RootBlock clBody_ = _conf.getAnalyzing().getAnaClassBody(base_);
        if (!(clBody_ instanceof UniqueRootedBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            call_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //key word len
            call_.buildError(_conf.getAnalyzing().getAnalysisMessages().getCallCtorSuperClassEnumSingleton());
            _conf.getAnalyzing().getLocalizer().addError(call_);
            getErrs().add(call_.getBuiltError());
            return null;
        }
        UniqueRootedBlock unique_ =(UniqueRootedBlock) clBody_;
        String superClass_ = AnaTemplates.quickFormat(clBody_,clCurName_, unique_.getImportedDirectGenericSuperClass(), _conf);
        return new AnaClassArgumentMatching(superClass_);
    }

}
