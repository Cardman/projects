package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ConstructorId;
import code.util.StringList;

public final class InterfaceInvokingConstructor extends AbstractInvokingConstructor {

    public InterfaceInvokingConstructor(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    AnaClassArgumentMatching getFrom(AnalyzedPageEl _page) {
        InterfaceBlock candidate_ = tryGetAsInterface(_page);
        if (candidate_ == null) {
            return null;
        }
        AnaFormattedRootBlock clCurType_ = _page.getGlobalType();
        return superType(_page, candidate_, clCurType_);
    }

    @Override
    void checkPosition(AnalyzedPageEl _page) {
        AbsBk curBlock_ = _page.getCurrentBlock();
        Line curLine_ = (Line)curBlock_;
        BracedBlock br_ = curBlock_.getParent();
        if (br_.getParent() instanceof InterfaceBlock) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(curLine_.getFile());
            call_.setIndexFile(_page);
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
                feed(_page,previousInts_,f_,cid_);
            }
            while (true) {
                AbsBk n_ = f_.getNextSibling();
                if (n_ == curBlock_) {
                    possibleErrPos(_page, curLine_, f_);
                    ConstructorId cid_ = getConstId();
                    feed(_page,previousInts_, n_, cid_);
                    break;
                }
                if (n_ instanceof Line){
                    ConstructorId cid_ = ((Line)n_).getCallInts();
                    feed(_page, previousInts_, n_, cid_);
                }
                f_ = n_;
            }
        }
    
    }

    private void feed(AnalyzedPageEl _page, StringList _previousInts, AbsBk _bk, ConstructorId _cid) {
        if (_cid != null) {
            String cl_ = _cid.getName();
            cl_ = StringExpUtil.getIdFromAllTypes(cl_);
            checkInherits(_previousInts, _bk, cl_, _page);
            _previousInts.add(cl_);
        } else {
            _previousInts.add("");
        }
    }

    private void possibleErrPos(AnalyzedPageEl _page, Line _curLine, AbsBk _f) {
        if (!(_f instanceof Line)||!((Line) _f).isCallFromCtorToCtor() || ((Line) _f).isCallThis()) {
            //error
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(_curLine.getFile());
            call_.setIndexFile(_page);
            //key word len
            call_.buildError(_page.getAnalysisMessages().getCallCtorIntAfterSuperThis());
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        }
    }

    private void checkInherits(StringList _previousInts, AbsBk _n, String _cl, AnalyzedPageEl _page) {
        if (!_previousInts.isEmpty()) {
            String sup_ = _previousInts.last();
            RootBlock supType_ = _page.getAnaClassBody(sup_);
            if (supType_ != null && supType_.isSubTypeOf(_cl, _page)) {
                FoundErrorInterpret undef_;
                undef_ = new FoundErrorInterpret();
                undef_.setFile(_n.getFile());
                undef_.setIndexFile(_page);
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

}
