package code.expressionlanguage.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.methods.AnnotationMethodBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.util.*;
import code.util.StringList;

public final class AssocationOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {

    private String fieldName;

    private int offset;
    private int delta;
    private String annotation = EMPTY_STRING;

    public AssocationOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op, String _fieldName) {
        super(_index, _indexChild, _m, _op);
        delta = StringList.getFirstPrintableCharIndex(_fieldName);
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
    public void preAnalyze(ContextEl _conf) {
        MethodOperation mOp_ = getParent();
        AnnotationInstanceOperation par_ = (AnnotationInstanceOperation) mOp_;
        String annotationClass_ = par_.getClassName();
        GeneType type_ = _conf.getClassBody(annotationClass_);
        if (type_ instanceof Block) {
            annotation = annotationClass_;
        }
    }
    @Override
    public void analyzeUnary(ContextEl _conf) {
        MethodOperation mOp_ = getParent();
        AnnotationInstanceOperation par_ = (AnnotationInstanceOperation) mOp_;
        String annotationClass_ = par_.getClassName();
        GeneType type_ = _conf.getClassBody(annotationClass_);
        if (type_ instanceof Block) {
            Block ann_ = (Block) type_;
            boolean ok_ = false;
            for (Block b: Classes.getDirectChildren(ann_)) {
                if (!(b instanceof AnnotationMethodBlock)) {
                    continue;
                }
                AnnotationMethodBlock a_ = (AnnotationMethodBlock) b;
                if (StringList.quickEq(a_.getName(), fieldName)) {
                    ok_ = true;
                    break;
                }
            }
            if (!ok_) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //fieldName len
                cast_.buildError(_conf.getAnalysisMessages().getUndefinedAccessibleField(),
                        fieldName,
                        annotationClass_);
                _conf.getAnalyzing().getLocalizer().addError(cast_);
            }
        }
        setResultClass(getFirstChild().getResultClass());
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        Argument arg_ = getFirstChild().getArgument();
        setSimpleArgumentAna(arg_, _conf);
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
}
