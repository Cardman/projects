package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
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
    AnaClassArgumentMatching getFrom(AnalyzedPageEl _page) {
        String clCurName_ = _page.getGlobalClass();
        String base_ = StringExpUtil.getIdFromAllTypes(clCurName_);
        RootBlock clBody_ = _page.getAnaClassBody(base_);
        if (!(clBody_ instanceof UniqueRootedBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorSuperClassEnumSingleton());
            _page.getLocalizer().addError(call_);
            getErrs().add(call_.getBuiltError());
            return null;
        }
        UniqueRootedBlock unique_ =(UniqueRootedBlock) clBody_;
        String superClass_ = AnaTemplates.quickFormat(clBody_,clCurName_, unique_.getImportedDirectGenericSuperClass());
        return new AnaClassArgumentMatching(superClass_);
    }

}
