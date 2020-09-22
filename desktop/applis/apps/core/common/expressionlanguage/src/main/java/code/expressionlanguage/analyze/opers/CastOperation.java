package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.structs.*;
import code.util.CustList;

public final class CastOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {

    private String originalClassName;
    private String className;
    private int offset;
    private int beginType;
    private CustList<PartOffset> partOffsets;
    private boolean found;
    public CastOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        offset = getOperations().getOperators().firstKey();
        className = getOperations().getOperators().firstValue();
        originalClassName = className;
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _page);
        String ext_ = getOperations().getExtractType();
        if (!ext_.isEmpty()) {
            className = ext_;
            partOffsets = getOperations().getPartOffsets();
            found = true;
        } else {
            beginType = className.indexOf(PAR_LEFT) + 1;
            String res_ = className.substring(beginType, className.lastIndexOf(PAR_RIGHT));
            if (res_.trim().isEmpty()) {
                int rc_ = _page.getLocalizer().getCurrentLocationIndex() + className.indexOf(PAR_RIGHT);
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
                className = EMPTY_STRING;
                partOffsets = partOffsets_;
                return;
            }
            CustList<PartOffset> currentParts_ = _page.getCurrentParts();
            res_ = ResolvingImportTypes.resolveCorrectTypeWithoutErrors(className.indexOf(PAR_LEFT)+1,res_,true, currentParts_, _page);
            if (!res_.isEmpty()) {
                className = res_;
            } else {
                className = EMPTY_STRING;
            }
            partOffsets = new CustList<PartOffset>(currentParts_);
        }
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset,_page);
        className = ValidatorStandard.checkExactType(beginType, className,originalClassName, _page);
        setResultClass(new AnaClassArgumentMatching(className, _page.getStandards()));
        if (AnaTypeUtil.isPrimitive(className, _page)) {
            getFirstChild().getResultClass().setUnwrapObject(className, _page.getStandards());
        }
    }

    public String getClassName() {
        return className;
    }

    public int getOffset() {
        return offset;
    }

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public boolean isFound() {
        return found;
    }
}
