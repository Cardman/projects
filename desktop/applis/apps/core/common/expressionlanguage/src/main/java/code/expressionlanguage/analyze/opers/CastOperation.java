package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaTypeCheckContent;

public final class CastOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {

    private final String originalClassName;
    private final AnaTypeCheckContent typeCheckContent;
    private final String extractType;
    private int beginType;
    private AnaResultPartType partOffsets;
    private boolean found;
    public CastOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        typeCheckContent = new AnaTypeCheckContent(getOperators().firstKey());
        typeCheckContent.setClassName(getOperators().firstValue());
        originalClassName = typeCheckContent.getClassName();
        extractType = _op.getExtractType();
        if (!extractType.isEmpty()) {
            typeCheckContent.setClassName(extractType);
            partOffsets = _op.getPartOffsets();
            found = true;
        }
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ typeCheckContent.getOffset(), _page);
        if (extractType.isEmpty()) {
            beginType = typeCheckContent.getClassName().indexOf(PAR_LEFT) + 1;
            String res_ = typeCheckContent.getClassName().substring(beginType, typeCheckContent.getClassName().lastIndexOf(PAR_RIGHT));
            AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(typeCheckContent.getClassName().indexOf(PAR_LEFT) + 1 + StringExpUtil.getOffset(res_), res_.trim(), _page);
            if (resType_.isOk()) {
                res_ = resType_.getResult();
            } else {
                res_ = "";
            }
            typeCheckContent.setClassName(res_);
            partOffsets = resType_;
        }
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ typeCheckContent.getOffset(),_page);
        typeCheckContent.setClassName(checkExactType(beginType, typeCheckContent.getClassName(), originalClassName, _page));
        setResultClass(new AnaClassArgumentMatching(typeCheckContent.getClassName(), _page.getPrimitiveTypes()));
        if (AnaTypeUtil.isPrimitive(typeCheckContent.getClassName(), _page)) {
            getFirstChild().getResultClass().setUnwrapObject(typeCheckContent.getClassName(), _page.getPrimitiveTypes());
        }
    }

    private static String checkExactType(int _loc, String _in, String _orig, AnalyzedPageEl _page) {
        if (!_in.isEmpty()) {
            return _in;
        }
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFile(_page.getCurrentFile());
        un_.setIndexFile(_page,_loc);
        //original type len
        un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                _orig);
        _page.getLocalizer().addError(un_);
        return _page.getAliasObject();
    }


    public String getClassName() {
        return typeCheckContent.getClassName();
    }

    public int getOffset() {
        return typeCheckContent.getOffset();
    }

    public AnaTypeCheckContent getTypeCheckContent() {
        return typeCheckContent;
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

    public boolean isFound() {
        return found;
    }

}
