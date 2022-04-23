package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.maths.litteralcom.StrTypes;

public abstract class LeafOperation extends OperationNode {

    private final int offset;
    private final String value;

    protected LeafOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m);
        offset = _op.getOffset();
        value = StrTypes.value(str(_op.getValues()),0);
    }
    private static StrTypes str(StrTypes _elts) {
        if (_elts == null) {
            return new StrTypes();
        }
        return _elts;
    }

    void checkClassAccess(String _glClass, String _classStr, AnalyzedPageEl _page) {
        RootBlock r_ = _page.getAnaClassBody(_classStr);
        String curClassBase_ = StringExpUtil.getIdFromAllTypes(_glClass);
        Accessed a_;
        if (r_ != null) {
            a_ = new Accessed(r_.getAccess(), r_.getPackageName(), r_);
        } else {
            a_ = new Accessed(AccessEnum.PUBLIC,"", null);
        }
        if (!ContextUtil.canAccessType(curClassBase_, a_, _page)) {
            FoundErrorInterpret badAccess_ = new FoundErrorInterpret();
            badAccess_.setIndexFile(_page);
            badAccess_.setFile(_page.getCurrentFile());
            //className len
            badAccess_.buildError(_page.getAnalysisMessages().getInaccessibleType(),
                    _classStr,
                    curClassBase_);
            _page.getLocalizer().addError(badAccess_);
            addErr(badAccess_.getBuiltError());
        }
    }

    public String getValue() {
        return value;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public final OperationNode getFirstChild() {
        return null;
    }
}
