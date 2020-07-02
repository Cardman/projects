package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.util.CustList;

public final class CastOperation extends AbstractUnaryOperation implements PreAnalyzableOperation {

    private String originalClassName;
    private String className;
    private int offset;
    private int beginType;
    private CustList<PartOffset> partOffsets;
    public CastOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        offset = getOperations().getOperators().firstKey();
        className = getOperations().getOperators().firstValue();
        originalClassName = className;
    }

    @Override
    public void preAnalyze(ContextEl _an) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _an);
        String ext_ = getOperations().getExtractType();
        if (!ext_.isEmpty()) {
            className = ext_;
            partOffsets = getOperations().getPartOffsets();
        } else {
            beginType = className.indexOf(PAR_LEFT) + 1;
            String res_ = className.substring(beginType, className.lastIndexOf(PAR_RIGHT));
            if (res_.trim().isEmpty()) {
                className = EMPTY_STRING;
                int rc_ = _an.getAnalyzing().getLocalizer().getCurrentLocationIndex() + className.indexOf(PAR_LEFT)+1;
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(_an.getAnalyzing().getLocalizer().getCurrentFileName());
                un_.setIndexFile(rc_);
                //_in len
                un_.buildError(_an.getAnalysisMessages().getEmptyType());
                CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
                String err_ = un_.getBuiltError();
                String pref_ = "<a title=\""+err_+"\" class=\"e\">";
                partOffsets_.add(new PartOffset(pref_,rc_));
                partOffsets_.add(new PartOffset("</a>",rc_+1));
                partOffsets = partOffsets_;
                return;
            }
            CustList<PartOffset> currentParts_ = _an.getAnalyzing().getCurrentParts();
            res_ = ResolvingImportTypes.resolveCorrectTypeWithoutErrors(_an,className.indexOf(PAR_LEFT)+1,res_,true, currentParts_);
            if (!res_.isEmpty()) {
                className = res_;
            } else {
                className = EMPTY_STRING;
            }
            partOffsets = new CustList<PartOffset>(currentParts_);
        }
    }

    @Override
    public void analyzeUnary(ContextEl _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        className = ValidatorStandard.checkExactType(_conf,beginType, className,originalClassName);
        setResultClass(new ClassArgumentMatching(className));
        if (PrimitiveTypeUtil.isPrimitive(className, _conf)) {
            getFirstChild().getResultClass().setUnwrapObject(className);
            Argument arg_ = getFirstChild().getArgument();
            checkNull(arg_,_conf);
        }
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        tryGetArg(this,_conf, className);
    }

    public static void tryGetArg(MethodOperation _current, ContextEl _conf, String _className) {
        CustList<OperationNode> chidren_ = _current.getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (OperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument objArg_ = arguments_.first();
        if (_className.contains("#")) {
            return;
        }
        if (ExecTemplates.safeObject(_className,objArg_,_conf) != ErrorType.NOTHING) {
            return;
        }
        _current.setSimpleArgumentAna(objArg_, _conf);
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
}
