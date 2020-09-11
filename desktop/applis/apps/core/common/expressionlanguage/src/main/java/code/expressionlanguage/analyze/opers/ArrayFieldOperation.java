package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.instr.OperationsSequence;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class ArrayFieldOperation extends AbstractFieldOperation {

    public ArrayFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    public void analyze(ContextEl _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+relativeOff_, _conf);
        AnalyzedPageEl page_ = _conf.getAnalyzing();
        LgNames stds_ = page_.getStandards();
        ClassArgumentMatching cl_ = getPreviousResultClass();
        String aliasLength_ = page_.getStandards().getAliasLength();
        if (StringList.quickEq(str_, aliasLength_)) {
            Argument arg_ = getPreviousArgument();
            checkNull(arg_,_conf);
            setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
            return;
        }
        FoundErrorInterpret und_ = new FoundErrorInterpret();
        und_.setFileName(page_.getLocalizer().getCurrentFileName());
        und_.setIndexFile(page_.getLocalizer().getCurrentLocationIndex());
        //str_ len
        und_.buildError(_conf.getAnalysisMessages().getUndefinedAccessibleField(),
                str_,
                StringList.join(cl_.getNames(), "&"));
        page_.getLocalizer().addError(und_);
        getErrs().add(und_.getBuiltError());
        setResultClass(new ClassArgumentMatching(stds_.getAliasPrimInteger()));
    }

}
