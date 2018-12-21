package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.StaticAccessThisError;
import code.expressionlanguage.opers.LeafOperation;
import code.expressionlanguage.opers.MethodOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.text.OperationsSequence;
import code.util.CustList;
import code.util.StringList;

public final class InternGlobalOperation extends LeafOperation {
    private int off;

    public InternGlobalOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        int relativeOff_ = _op.getOffset();
        String originalStr_ = _op.getValues().getValue(CustList.FIRST_INDEX);
        off = StringList.getFirstPrintableCharIndex(originalStr_)+relativeOff_;
    }

    @Override
    public void analyze(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        int relativeOff_ = op_.getOffset();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        int off_ = StringList.getFirstPrintableCharIndex(originalStr_) + relativeOff_;
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        String arg_ = _conf.getInternGlobalClass();
        if (arg_ == null) {
            arg_ = stds_.getAliasObject();
        }
        if (_conf.isStaticContext()) {
            StaticAccessThisError static_ = new StaticAccessThisError();
            static_.setClassName(arg_);
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(static_);
        }
        setResultClass(new ClassArgumentMatching(arg_));
    }

    @Override
    public void analyzeAssignmentAfter(Analyzable _conf) {
    }

    public int getOff() {
        return off;
    }
}
