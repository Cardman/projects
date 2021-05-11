package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.blocks.UniqueRootedBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.util.CustList;

public final class SuperInvokingConstructor extends AbstractInvokingConstructor {

    public SuperInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    AnaClassArgumentMatching getFrom(AnalyzedPageEl _page) {
        RootBlock clBody_ = _page.getGlobalType();
        if (!(clBody_ instanceof UniqueRootedBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorSuperClassEnumSingleton());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return null;
        }
        String superClass_ = "";
        CustList<AnaFormattedRootBlock> genericClasses_ = clBody_.getAllGenericClassesInfo();
        if (genericClasses_.size() > 1) {
            setType(genericClasses_.get(1).getRootBlock());
            superClass_ = genericClasses_.get(1).getFormatted();
        }
        if (getType() == null) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorNoSuperClassEnum());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        }
        return new AnaClassArgumentMatching(superClass_);
    }

}
