package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.ClassMethodIdMemberIdTypeFct;
import code.expressionlanguage.analyze.opers.util.ReversibleConversion;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaOperatorContent;
import code.expressionlanguage.linkage.ExportCst;
import code.maths.litteralcom.StrTypes;
import code.util.*;
import code.util.core.StringUtil;

public final class SemiAffectationOperation extends AbstractUnaryOperation  {
    private SettableElResult settable;
    private final AnaOperatorContent operatorContent;
    private final boolean post;
    private final ClassMethodIdMemberIdTypeFct fct = new ClassMethodIdMemberIdTypeFct();
    private final ClassMethodIdMemberIdTypeFct convFrom = new ClassMethodIdMemberIdTypeFct();
    private final ClassMethodIdMemberIdTypeFct convTo = new ClassMethodIdMemberIdTypeFct();

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
        settable = AffectationOperation.tryGetCastSettable(this);
        if (!isLeftValue(settable)) {
            setRelativeOffsetPossibleAnalyzable(leftEl_.getIndexInEl(), _page);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //operator
            un_.buildError(_page.getAnalysisMessages().getUnexpectedAffect(),
                    operatorContent.getOper());
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (settable instanceof SettableFieldOperation) {
            SettableFieldOperation cst_ = (SettableFieldOperation)settable;
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
                addErr(un_.getBuiltError());
            }
        }
        setResultClass(AnaClassArgumentMatching.copy(AnaTypeUtil.toPrimitive(settable.getResultClass(), _page), _page.getPrimitiveTypes()));
        settable.setVariable(false);
        StrTypes ops_ = getOperations().getOperators();
        String op_ = ops_.firstValue();
        ClassMethodIdReturn cl_ = getIncrDecrOperatorOrMethod(this,leftEl_,settable, op_, _page);
        if (cl_ != null) {
            fct.infos(cl_);
            return;
        }
        AnaClassArgumentMatching clMatchLeft_ = leftEl_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ops_.firstKey(), _page);
        if (!AnaTypeUtil.isPureNumberClass(clMatchLeft_, _page)) {
            ReversibleConversion reversibleConversion_ = tryGetPair(clMatchLeft_, _page);
            if (reversibleConversion_ != null) {
                convFrom.infos(reversibleConversion_.getFrom());
                convTo.infos(reversibleConversion_.getTo());
            } else {
                Mapping mapping_ = new Mapping();
                mapping_.setArg(clMatchLeft_);
                mapping_.setParam(_page.getAliasLong());
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //operator
                cast_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        StringUtil.join(clMatchLeft_.getNames(), ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
            }
            return;
        }
        addErrIfNotSettable(operatorContent.getOper(),AffectationOperation.getFirstToBeAnalyzed(this),_page);
        clMatchLeft_.setUnwrapObject(AnaTypeUtil.toPrimitive(clMatchLeft_, _page), _page.getPrimitiveTypes());
    }

    public boolean isPost() {
        return post;
    }

    public ClassMethodIdMemberIdTypeFct getFct() {
        return fct;
    }
    public ClassMethodIdMemberIdTypeFct getConvFrom() {
        return convFrom;
    }

    public ClassMethodIdMemberIdTypeFct getConvTo() {
        return convTo;
    }

    public int getOpOffset() {
        return operatorContent.getOpOffset();
    }

    public SettableElResult getSettable() {
        return settable;
    }

    public AnaTypeFct getFunctionFrom() {
        return convFrom.getFunction();
    }

    public AnaTypeFct getFunctionTo() {
        return convTo.getFunction();
    }

    public AnaOperatorContent getOperatorContent() {
        return operatorContent;
    }
}
