package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.UnexpectedTypeOperationError;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;

public final class DimensionArrayInstancing extends
        AbstractArrayInstancingOperation {
    private int countArrayDims;

    public DimensionArrayInstancing(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        countArrayDims = getOperations().getCountArrays();
    }

    @Override
    public void analyze(Analyzable _conf) {
        CustList<OperationNode> chidren_ = getChildrenNodes();
        String m_ = getMethodName();
        int off_ = StringList.getFirstPrintableCharIndex(m_);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        setClassName(_conf.getStandards().getAliasObject());
        KeyWords keyWords_ = _conf.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        String className_ = m_.trim().substring(new_.length());
        className_ = className_.trim();
        className_ = _conf.resolveCorrectType(className_);
        for (OperationNode o: chidren_) {
            setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+off_, _conf);
            if (!o.getResultClass().isNumericInt(_conf)) {
                ClassArgumentMatching cl_ = o.getResultClass();
                UnexpectedTypeOperationError un_ = new UnexpectedTypeOperationError();
                un_.setIndexFile(_conf.getCurrentLocationIndex());
                un_.setFileName(_conf.getCurrentFileName());
                un_.setExpectedResult(_conf.getStandards().getAliasPrimInteger());
                un_.setOperands(cl_);
                _conf.getClasses().addError(un_);
            }
            o.getResultClass().setUnwrapObject(_conf.getStandards().getAliasPrimInteger());
            o.cancelArgument();
        }
        setClassName(className_);
        setResultClass(new ClassArgumentMatching(PrimitiveTypeUtil.getPrettyArrayType(className_, chidren_.size()+countArrayDims)));
    }

    public int getCountArrayDims() {
        return countArrayDims;
    }

}
