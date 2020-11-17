package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnnotationMethodBlock;
import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.util.*;
import code.util.core.StringUtil;

public final class AssocationOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {

    private String fieldName;

    private int offset;
    private int delta;
    private int offEq;
    private String annotation = EMPTY_STRING;
    private String errAff = EMPTY_STRING;

    public AssocationOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op, String _fieldName) {
        super(_index, _indexChild, _m, _op);
        delta = StringUtil.getFirstPrintableCharIndex(_fieldName);
        offEq = getOperations().getOperators().firstKey();
        fieldName = _fieldName.trim();
    }

    @Override
    void calculateChildren() {
        IntTreeMap< String> vs_ = getOperations().getValues();
        offset = vs_.firstKey();
        vs_.removeKey(vs_.firstKey());
        getChildren().putAllMap(vs_);
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        MethodOperation mOp_ = getParent();
        AnnotationInstanceOperation par_ = (AnnotationInstanceOperation) mOp_;
        String annotationClass_ = par_.getClassName();
        RootBlock type_ = _page.getAnaClassBody(annotationClass_);
        if (type_ != null) {
            annotation = annotationClass_;
        }
    }
    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        MethodOperation mOp_ = getParent();
        AnnotationInstanceOperation par_ = (AnnotationInstanceOperation) mOp_;
        String annotationClass_ = par_.getClassName();
        RootBlock type_ = _page.getAnaClassBody(annotationClass_);
        if (type_ != null) {
            boolean ok_ = false;
            for (Block b: ClassesUtil.getDirectChildren(type_)) {
                if (!(b instanceof AnnotationMethodBlock)) {
                    continue;
                }
                AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
                if (StringUtil.quickEq(a_.getName(), fieldName)) {
                    ok_ = true;
                    break;
                }
            }
            if (!ok_) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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

    public String getFieldName() {
        return fieldName;
    }

    public int getSum() {
        return offset+delta;
    }

    public String getAnnotation() {
        return annotation;
    }

    public String getErrAff() {
        return errAff;
    }

    public void setErrAff(String _errAff) {
        errAff = _errAff;
    }

    public int getOffEq() {
        return offEq;
    }
}
