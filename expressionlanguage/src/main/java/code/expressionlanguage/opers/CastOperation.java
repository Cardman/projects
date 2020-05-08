package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ErrorType;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.types.ResolvingImportTypes;
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
    public void preAnalyze(Analyzable _an) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _an);
        String ext_ = getOperations().getExtractType();
        if (!ext_.isEmpty()) {
            className = ext_;
            partOffsets = getOperations().getPartOffsets();
        } else {
            beginType = className.indexOf(PAR_LEFT) + 1;
            String res_ = className.substring(beginType, className.lastIndexOf(PAR_RIGHT));
            res_ = ResolvingImportTypes.resolveCorrectTypeWithoutErrors(_an,className.indexOf(PAR_LEFT)+1,res_,true);
            if (!res_.isEmpty()) {
                className = res_;
                partOffsets = new CustList<PartOffset>(_an.getContextEl().getCoverage().getCurrentParts());
            } else {
                className = EMPTY_STRING;
                partOffsets = new CustList<PartOffset>();
            }
        }
    }

    @Override
    public void analyzeUnary(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        className = _conf.getStandards().checkExactType(_conf,beginType, className,originalClassName);
        setResultClass(new ClassArgumentMatching(className));
        if (PrimitiveTypeUtil.isPrimitive(className, _conf)) {
            getFirstChild().getResultClass().setUnwrapObject(className);
            Argument arg_ = getFirstChild().getArgument();
            checkNull(arg_,_conf);
        }
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        tryGetArg(this,_conf, className);
    }

    public static void tryGetArg(ParentOperable _current, Analyzable _conf, String _className) {
        CustList<Operable> chidren_ = _current.getChildrenOperable();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (Operable o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument objArg_ = arguments_.first();
        if (_className.contains("#")) {
            return;
        }
        if (Templates.safeObject(_className,objArg_,_conf) != ErrorType.NOTHING) {
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
