package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.ReversibleConversion;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.stds.LgNames;
import code.util.*;

public final class SemiAffectationOperation extends AbstractUnaryOperation  {
    private SettableElResult settable;
    private AnaOperatorContent operatorContent;
    private boolean post;
    private ClassMethodId classMethodId;
    private int rootNumber = -1;
    private int memberNumber = -1;
    private ClassMethodId converterFrom;
    private int rootNumberFrom = -1;
    private int memberNumberFrom = -1;
    private ClassMethodId converterTo;
    private int rootNumberTo = -1;
    private int memberNumberTo = -1;

    public SemiAffectationOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op, boolean _post) {
        super(_index, _indexChild, _m, _op);
        post = _post;
        operatorContent = new AnaOperatorContent();
        operatorContent.setOper(_op.getOperators().firstValue());
        operatorContent.setOpOffset(_op.getOperators().firstKey());
    }

    @Override
    public void analyzeUnary(AnalyzedPageEl _page) {
        OperationNode leftEl_ = getFirstChild();
        LgNames stds_ = _page.getStandards();
        settable = AffectationOperation.tryGetSettable(this);
        if (!(settable instanceof OperationNode)) {
            setRelativeOffsetPossibleAnalyzable(leftEl_.getIndexInEl(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //operator
            un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                    operatorContent.getOper());
            _page.getLocalizer().addError(un_);
            getErrs().add(un_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (settable instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)settable;
            StringMap<Boolean> fieldsAfterLast_ = _page.getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(cst_, fieldsAfterLast_, _page)) {
                setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _page);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //field name len
                un_.buildError(_page.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _page.getLocalizer().addError(un_);
                getErrs().add(un_.getBuiltError());
            }
        }
        setResultClass(AnaClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(settable.getResultClass(), _page), _page.getPrimitiveTypes()));
        settable.setVariable(false);
        IntTreeMap< String> ops_ = getOperations().getOperators();
        String op_ = ops_.firstValue();
        ClassMethodIdReturn cl_ = getIncrDecrOperatorOrMethod(this,leftEl_, op_, _page);
        if (cl_ != null) {
            String foundClass_ = cl_.getRealClass();
            MethodId id_ = cl_.getRealId();
            rootNumber = cl_.getRootNumber();
            memberNumber = cl_.getMemberNumber();
            classMethodId = new ClassMethodId(foundClass_,id_);
            return;
        }
        AnaClassArgumentMatching clMatchLeft_ = leftEl_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        if (!AnaTypeUtil.isPureNumberClass(clMatchLeft_, _page)) {
            ReversibleConversion reversibleConversion_ = tryGetPair(clMatchLeft_, _page);
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
                mapping_.setParam(_page.getStandards().getAliasLong());
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //operator
                cast_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        StringList.join(clMatchLeft_.getNames(),"&"));
                _page.getLocalizer().addError(cast_);
                getErrs().add(cast_.getBuiltError());
            }
            return;
        }
        clMatchLeft_.setUnwrapObject(AnaTypeUtil.toPrimitive(clMatchLeft_, _page), _page.getPrimitiveTypes());
    }

    public boolean isPost() {
        return post;
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
        return operatorContent.getOpOffset();
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

    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }
}
