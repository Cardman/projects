package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.stds.LgNames;
import code.util.*;

public final class SemiAffectationOperation extends AbstractUnaryOperation  {
    private SettableElResult settable;
    private boolean post;
    private String oper;
    private ClassMethodId classMethodId;

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
        LgNames stds_ = _conf.getStandards();
        settable = AffectationOperation.tryGetSettable(this);
        if (settable == null) {
            setRelativeOffsetPossibleAnalyzable(leftEl_.getIndexInEl(), _conf);
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //operator
            un_.buildError(_conf.getAnalysisMessages().getUnexpectedAffect(),
                    oper);
            _conf.getAnalyzing().getLocalizer().addError(un_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return;
        }
        if (settable instanceof SettableAbstractFieldOperation) {
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation)settable;
            StringMap<Boolean> fieldsAfterLast_ = _conf.getAnalyzing().getDeclaredAssignments();
            if (ElUtil.checkFinalFieldReadOnly(_conf, cst_, fieldsAfterLast_)) {
                setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _conf);
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //field name len
                un_.buildError(_conf.getAnalysisMessages().getFinalField(),
                        cst_.getFieldName());
                _conf.getAnalyzing().getLocalizer().addError(un_);
            }
        }
        setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.toPrimitive(settable.getResultClass(),_conf)));
        settable.setVariable(false);
        IntTreeMap< String> ops_ = getOperations().getOperators();
        String op_ = ops_.firstValue();
        ClassMethodId cl_ = getUnaryOperatorOrMethod(this,leftEl_.getResultClass(), op_, _conf);
        if (cl_ != null) {
            classMethodId = cl_;
            Mapping map_ = new Mapping();
            map_.setArg(getResultClass());
            map_.setParam(settable.getResultClass());
            if (!AnaTemplates.isCorrectOrNumbers(map_, _conf)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
                //oper len
                cast_.buildError(_conf.getAnalysisMessages().getBadImplicitCast(),
                        StringList.join(getResultClass().getNames(),"&"),
                        StringList.join(settable.getResultClass().getNames(),"&"));
                _conf.getAnalyzing().getLocalizer().addError(cast_);
            }
            return;
        }
        ClassArgumentMatching clMatchLeft_ = leftEl_.getResultClass();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _conf);
        if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _conf)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(clMatchLeft_);
            mapping_.setParam(_conf.getStandards().getAliasLong());
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_conf.getAnalyzing().getLocalizer().getCurrentFileName());
            cast_.setIndexFile(_conf.getAnalyzing().getLocalizer().getCurrentLocationIndex());
            //operator
            cast_.buildError(_conf.getAnalysisMessages().getUnexpectedType(),
                    StringList.join(clMatchLeft_.getNames(),"&"));
            _conf.getAnalyzing().getLocalizer().addError(cast_);
            getErrs().add(cast_.getBuiltError());
            return;
        }
        clMatchLeft_.setUnwrapObject(PrimitiveTypeUtil.toPrimitive(clMatchLeft_, _conf));
        leftEl_.cancelArgument();
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

    public int getOpOffset() {
        return opOffset;
    }

    public SettableElResult getSettable() {
        return settable;
    }
}
