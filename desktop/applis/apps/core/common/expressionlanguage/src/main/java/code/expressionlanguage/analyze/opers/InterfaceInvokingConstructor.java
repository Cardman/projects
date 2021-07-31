package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvedIdType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;

import code.expressionlanguage.functionid.ConstructorId;
import code.util.CustList;
import code.util.StringList;

public final class InterfaceInvokingConstructor extends AbstractInvokingConstructor {

    private final CustList<AnaResultPartType> partOffsets = new CustList<AnaResultPartType>();
    public InterfaceInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    AnaClassArgumentMatching getFrom(AnalyzedPageEl _page) {
        String cl_ = getMethodName();
        int leftPar_ = cl_.indexOf(PAR_LEFT) + 1;
        cl_ = cl_.substring(leftPar_, cl_.lastIndexOf(PAR_RIGHT));
        ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(leftPar_, cl_, _page);
        cl_ = resolvedIdType_.getFullName();
        partOffsets.addAllElts(resolvedIdType_.getDels());
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
        AnaFormattedRootBlock clCurType_ = _page.getGlobalType();
        AnaFormattedRootBlock superClass_ = AnaInherits.getFormattedOverridingFullTypeByBases(clCurType_, candidate_);
        if (superClass_ == null) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntFromSuperInt());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            return null;
        }
        setType(superClass_);
        return new AnaClassArgumentMatching(superClass_.getFormatted());
    }

    @Override
    void checkPosition(AnalyzedPageEl _page) {
        AbsBk curBlock_ = _page.getCurrentBlock();
        Line curLine_ = (Line)curBlock_;
        BracedBlock br_ = curBlock_.getParent();
        if (br_.getParent() instanceof InterfaceBlock) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(curLine_.getFile().getFileName());
            call_.setIndexFile(getFullIndexInEl()+ curLine_.getExpressionOffset());
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntNotFromInt());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        }
        AbsBk f_ = br_.getFirstChild();
        if (f_ != curBlock_) {
            StringList previousInts_ = new StringList();
            if (f_ instanceof Line){
                ConstructorId cid_ = ((Line)f_).getCallInts();
                if (cid_ != null) {
                    String cl_ = cid_.getName();
                    cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                    previousInts_.add(cl_);
                } else {
                    previousInts_.add("");
                }
            }
            while (true) {
                AbsBk n_ = f_.getNextSibling();
                if (n_ == curBlock_) {
                    if (!(f_ instanceof Line)) {
                        //error
                        FoundErrorInterpret call_ = new FoundErrorInterpret();
                        call_.setFileName(curLine_.getFile().getFileName());
                        call_.setIndexFile(getFullIndexInEl()+ curLine_.getExpressionOffset());
                        //key word len
                        call_.buildError(_page.getAnalysisMessages().getCallCtorIntAfterSuperThis());
                        _page.getLocalizer().addError(call_);
                        addErr(call_.getBuiltError());
                    } else {
                        if (!((Line)f_).isCallFromCtorToCtor()|| ((Line)f_).isCallThis()) {
                            //error
                            FoundErrorInterpret call_ = new FoundErrorInterpret();
                            call_.setFileName(curLine_.getFile().getFileName());
                            call_.setIndexFile(getFullIndexInEl()+ curLine_.getExpressionOffset());
                            //key word len
                            call_.buildError(_page.getAnalysisMessages().getCallCtorIntAfterSuperThis());
                            _page.getLocalizer().addError(call_);
                            addErr(call_.getBuiltError());
                        }
                    }
                    break;
                }
                if (n_ instanceof Line){
                    ConstructorId cid_ = ((Line)n_).getCallInts();
                    if (cid_ != null) {
                        String cl_ = cid_.getName();
                        cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                        checkInherits(previousInts_, n_, cl_, _page);
                        previousInts_.add(cl_);
                    } else {
                        previousInts_.add("");
                    }
                }
                f_ = n_;
            }
            ConstructorId cid_ = getConstId();
            if (cid_ != null) {
                String cl_ = cid_.getName();
                cl_ = StringExpUtil.getIdFromAllTypes(cl_);
                checkInherits(previousInts_, curBlock_, cl_, _page);
            }
        }
    
    }

    private void checkInherits(StringList _previousInts, AbsBk _n, String _cl, AnalyzedPageEl _page) {
        if (!_previousInts.isEmpty()) {
            String sup_ = _previousInts.last();
            RootBlock supType_ = _page.getAnaClassBody(sup_);
            if (supType_ != null && supType_.isSubTypeOf(_cl, _page)) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFileName(_n.getFile().getFileName());
                undef_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //current type len
                undef_.buildError(_page.getAnalysisMessages().getCallCtorIntInherits(),
                        sup_,
                        _cl
                );
                _page.getLocalizer().addError(undef_);
                addErr(undef_.getBuiltError());
            }
        }
    }

    public CustList<AnaResultPartType> getPartOffsets() {
        return partOffsets;
    }
}
