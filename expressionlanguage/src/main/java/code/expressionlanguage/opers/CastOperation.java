package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ErrorType;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.exec.ParentOperable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.*;

public final class CastOperation extends AbstractUnaryOperation {

    private String className;
    private int offset;
    public CastOperation(int _index, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculateChildren() {
        offset = getOperations().getOperators().firstKey();
        className = getOperations().getOperators().firstValue();
        IntTreeMap< String> vs_ = getOperations().getValues();
        getChildren().putAllMap(vs_);
    }

    @Override
    public void analyzeUnary(Analyzable _conf) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offset, _conf);
        String ext_ = getOperations().getExtractType();
        if (!ext_.isEmpty()) {
            className = ext_;
        } else {
            String res_ = className.substring(className.indexOf(PAR_LEFT)+1, className.lastIndexOf(PAR_RIGHT));
            res_ = _conf.resolveCorrectType(res_);
            className = res_;
        }
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
}
