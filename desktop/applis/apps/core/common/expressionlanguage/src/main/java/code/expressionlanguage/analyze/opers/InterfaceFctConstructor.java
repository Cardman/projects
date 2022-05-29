package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.InterfaceBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.StringExpUtil;

public final class InterfaceFctConstructor extends AbstractInvokingConstructor {
    private String className = EMPTY_STRING;
    public InterfaceFctConstructor(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    AnaClassArgumentMatching getFrom(AnalyzedPageEl _page) {
        int index_ = getIndexChild();
        if (index_ <= 0) {
            return null;
        }
        InterfaceBlock candidate_ = tryGetAsInterface(_page);
        if (candidate_ == null) {
            return null;
        }
        OperationNode par_ = getParent();
        String className_ = EMPTY_STRING;
        if (par_.getParent() instanceof CastOperation) {
            className_ = ((CastOperation)par_.getParent()).getClassName();
        }
        className = className_;
        if (!StringExpUtil.customCast(className_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(_page.getCurrentFile());
            call_.setIndexFile(_page);
            //type len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return null;
        }
        AnaFormattedRootBlock subType_ = new AnaFormattedRootBlock(_page, className_);
        RootBlock sub_ = subType_.getRootBlock();
        if (!(sub_ instanceof InterfaceBlock)|| !sub_.withoutInstance()) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(_page.getCurrentFile());
            call_.setIndexFile(_page);
            //type len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return null;
        }
        return superType(_page,candidate_,subType_);
    }

    @Override
    void checkPositionBasis(AnalyzedPageEl _page) {
        int index_ = getIndexChild();
        if (index_ <= 0) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(_page.getCurrentFile());
            call_.setIndexFile(_page);
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorEnd());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        }
    }

    public String getClassName() {
        return className;
    }

}
