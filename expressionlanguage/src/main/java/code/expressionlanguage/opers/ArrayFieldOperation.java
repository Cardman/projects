package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class ArrayFieldOperation extends AbstractFieldOperation {

    public ArrayFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching cl_ = getPreviousResultClass();
        String aliasLength_ = _conf.getStandards().getAliasLength();
        if (StringList.quickEq(str_, aliasLength_)) {
            Argument arg_ = getPreviousArgument();
            checkNull(arg_,_conf);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
            return;
        }
        FoundErrorInterpret und_ = new FoundErrorInterpret();
        und_.setFileName(_conf.getCurrentFileName());
        und_.setIndexFile(_conf.getCurrentLocationIndex());
        //str_ len
        und_.buildError(_conf.getContextEl().getAnalysisMessages().getUndefinedAccessibleField(),
                str_,
                StringList.join(cl_.getNames(), "&"));
        _conf.addError(und_);
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
    }

}
