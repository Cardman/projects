package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.NamedCalledFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.util.core.StringUtil;

public final class AssocationOperation extends AbstractUnaryOperation {

    private final String fieldName;

    private final int delta;
    private final int offEq;
    private String errAff = EMPTY_STRING;
    private AnaTypeFct function;

    public AssocationOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op, String _fieldName) {
        super(_index, _indexChild, _m, _op);
        delta = StringUtil.getFirstPrintableCharIndex(_fieldName);
        offEq = getOperators().firstKey();
        fieldName = _fieldName.trim();
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        MethodOperation mOp_ = getParent();
        AnnotationInstanceArobaseOperation par_ = (AnnotationInstanceArobaseOperation) mOp_;
        String annotationClass_ = par_.getClassName();
        RootBlock type_ = _page.getAnaClassBody(annotationClass_);
        if (type_ != null) {
            boolean ok_ = false;
            for (NamedCalledFunctionBlock b: type_.getValidMethods()) {
                if (StringUtil.quickEq(b.getName(), fieldName)) {
                    function = new AnaTypeFct();
                    function.setType(type_);
                    function.setFunction(b);
                    ok_ = true;
                    break;
                }
            }
            if (!ok_) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page);
                //fieldName len
                cast_.buildError(_page.getAnalysisMessages().getUndefinedAccessibleField(),
                        fieldName,
                        annotationClass_);
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
            }
        }
        setResultClass(getFirstChild().getResultClass());
    }

    public AnaTypeFct getFunction() {
        return function;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getSum() {
        return delta;
    }

    public String getErrAff() {
        return errAff;
    }

    void setErrAff(String _errAff) {
        errAff = _errAff;
    }

    public int getOffEq() {
        return offEq;
    }
}
