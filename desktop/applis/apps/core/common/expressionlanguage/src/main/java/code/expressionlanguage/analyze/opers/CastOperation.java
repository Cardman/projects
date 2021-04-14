package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fwd.opers.AnaTypeCheckContent;
import code.util.CustList;

public final class CastOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {

    private final String originalClassName;
    private final AnaTypeCheckContent typeCheckContent;
    private int beginType;
    private CustList<PartOffset> partOffsets;
    private boolean found;
    public CastOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        typeCheckContent = new AnaTypeCheckContent(getOperations().getOperators().firstKey());
        typeCheckContent.setClassName(getOperations().getOperators().firstValue());
        originalClassName = typeCheckContent.getClassName();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ typeCheckContent.getOffset(), _page);
        String ext_ = getOperations().getExtractType();
        if (!ext_.isEmpty()) {
            typeCheckContent.setClassName(ext_);
            partOffsets = getOperations().getPartOffsets();
            found = true;
        } else {
            beginType = typeCheckContent.getClassName().indexOf(PAR_LEFT) + 1;
            String res_ = typeCheckContent.getClassName().substring(beginType, typeCheckContent.getClassName().lastIndexOf(PAR_RIGHT));
            if (res_.trim().isEmpty()) {
                int rc_ = _page.getLocalizer().getCurrentLocationIndex() + typeCheckContent.getClassName().indexOf(PAR_RIGHT);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(rc_);
                //_in len
                un_.buildError(_page.getAnalysisMessages().getEmptyType());
                CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
                String err_ = un_.getBuiltError();
                String pref_ = "<a title=\""+err_+"\" class=\"e\">";
                partOffsets_.add(new PartOffset(pref_,rc_));
                partOffsets_.add(new PartOffset("</a>",rc_+1));
                typeCheckContent.setClassName(EMPTY_STRING);
                partOffsets = partOffsets_;
                return;
            }
            CustList<PartOffset> currentParts_ = _page.getCurrentParts();
            res_ = ResolvingTypes.resolveCorrectTypeWithoutErrors(typeCheckContent.getClassName().indexOf(PAR_LEFT)+1+ StringExpUtil.getOffset(res_),res_.trim(),true, currentParts_, _page);
            if (!res_.isEmpty()) {
                typeCheckContent.setClassName(res_);
            } else {
                typeCheckContent.setClassName(EMPTY_STRING);
            }
            partOffsets = new CustList<PartOffset>(currentParts_);
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
        int rc_ = _page.getLocalizer().getCurrentLocationIndex() + _loc;
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFileName(_page.getLocalizer().getCurrentFileName());
        un_.setIndexFile(rc_);
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

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public boolean isFound() {
        return found;
    }
}
