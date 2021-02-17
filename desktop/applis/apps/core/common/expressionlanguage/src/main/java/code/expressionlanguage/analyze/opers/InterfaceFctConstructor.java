package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.InterfaceBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.util.CustList;

public final class InterfaceFctConstructor extends AbstractInvokingConstructor {
    private String className = EMPTY_STRING;

    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    public InterfaceFctConstructor(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    AnaClassArgumentMatching getFrom(AnalyzedPageEl _page) {
        int index_ = getIndexChild();
        if (index_ <= 0) {
            return null;
        }
        String cl_ = getMethodName();
        int leftPar_ = cl_.indexOf(PAR_LEFT) + 1;
        cl_ = cl_.substring(leftPar_, cl_.lastIndexOf(PAR_RIGHT));
        cl_ = ResolvingTypes.resolveAccessibleIdType(leftPar_,cl_, _page);
        partOffsets.addAllElts(_page.getCurrentParts());
        RootBlock candidate_ = _page.getAnaClassBody(cl_);
        if (!(candidate_ instanceof InterfaceBlock)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
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
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return null;
        }
        String idCl_ = StringExpUtil.getIdFromAllTypes(className_);
        RootBlock sub_ = _page.getAnaClassBody(idCl_);
        if (!(sub_ instanceof InterfaceBlock)|| !sub_.withoutInstance()) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return null;
        }
        String superClass_ = AnaInherits.getOverridingFullTypeByBases(sub_,className_, cl_, _page);
        if (superClass_.isEmpty()) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return null;
        }
        setType(candidate_);
        return new AnaClassArgumentMatching(superClass_);
    }

    @Override
    void checkPositionBasis(AnalyzedPageEl _page) {
        int index_ = getIndexChild();
        if (index_ <= 0) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(getFullIndexInEl());
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorEnd());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        }
    }

    public String getClassName() {
        return className;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }
}
