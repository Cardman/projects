package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.ReversibleConversion;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.stds.LgNames;
import code.util.*;

public final class SemiAffectationOperation extends AbstractUnaryOperation  {
    private SettableElResult settable;
    private boolean post;
    private String oper;
    private ClassMethodId classMethodId;
    private int rootNumber = -1;
    private int memberNumber = -1;
    private ClassMethodId converterFrom;
    private int rootNumberFrom = -1;
    private int memberNumberFrom = -1;
    private ClassMethodId converterTo;
    private int rootNumberTo = -1;
    private int memberNumberTo = -1;

    private int opOffset;

    public SemiAffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op, boolean _post) {
        super(_index, _indexChild, _m, _op);
        post = _post;
        oper = _op.getOperators().firstValue();
        opOffset = _op.getOperators().firstKey();
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        OperationNode leftEl_ = getFirstChild();
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        settable = AffectationOperation.tryGetSettable(this);
        if (!(settable instanceof OperationNode)) {
            setRelativeOffsetPossibleAnalyzable(leftEl_.getIndexInEl(), _conf);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(page_.getLocalizer().getCurrentFileName());
            un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
            //operator
            un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedAffect(),
                    oper);
            page_.getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        OperationNode set_ = (OperationNode) settable;
        if (settable instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)settable;
            StringMap<Boolean> fieldsAfterLast_ = page_.getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(_conf, cst_, fieldsAfterLast_)) {
                setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _conf);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(page_.getLocalizer().getCurrentFileName());
                un_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //field name len
                un_.buildError(_conf.getAnalyzing().getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                page_.getLocalizer().addError(un_);
                getErrs().add(un_.getBuiltError());
            }
        }
        setResultClass(AnaClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(settable.getResultClass(),page_),page_.getStandards()));
        settable.setVariable(false);
        IntTreeMap< String> ops_ = getOperations().getOperators();
        String op_ = ops_.firstValue();
        ClassMethodIdReturn cl_ = getIncrDecrOperatorOrMethod(this,leftEl_, op_, _conf);
        if (cl_ != null) {
            String foundClass_ = cl_.getRealClass();
            MethodId id_ = cl_.getRealId();
            rootNumber = cl_.getRootNumber();
            memberNumber = cl_.getMemberNumber();
            classMethodId = new ClassMethodId(foundClass_,id_);
            return;
        }
        AnaClassArgumentMatching clMatchLeft_ = leftEl_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        if (!AnaTypeUtil.isPureNumberClass(clMatchLeft_, page_)) {
            ReversibleConversion reversibleConversion_ = tryGetPair(_conf, clMatchLeft_);
            if (reversibleConversion_ != null) {
                converterFrom = reversibleConversion_.getFrom();
                rootNumberFrom = reversibleConversion_.getRootNumberFrom();
                memberNumberFrom = reversibleConversion_.getMemberNumberFrom();
                converterTo = reversibleConversion_.getTo();
                rootNumberTo = reversibleConversion_.getRootNumberTo();
                memberNumberTo = reversibleConversion_.getMemberNumberTo();
            } else {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(clMatchLeft_);
                mapping_.setParam(page_.getStandards().getAliasLong());
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(page_.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
                //operator
                cast_.buildError(_conf.getAnalyzing().getAnalysisMessages().getUnexpectedType(),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                page_.getLocalizer().addError(cast_);
                getErrs().add(cast_.getBuiltError());
            }
            return;
        }
        clMatchLeft_.setUnwrapObject(AnaTypeUtil.toPrimitive(clMatchLeft_, page_),page_.getStandards());
        leftEl_.quickCancel();
    }

    public boolean isPost() {
        return post;
    }

    public String getOper() {
        return oper;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public ClassMethodId getConverterFrom() {
        return converterFrom;
    }

    public ClassMethodId getConverterTo() {
        return converterTo;
    }

    public int getOpOffset() {
        return opOffset;
    }

    public SettableElResult getSettable() {
        return settable;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public int getRootNumberFrom() {
        return rootNumberFrom;
    }

    public int getMemberNumberFrom() {
        return memberNumberFrom;
    }

    public int getRootNumberTo() {
        return rootNumberTo;
    }

    public int getMemberNumberTo() {
        return memberNumberTo;
    }
}
