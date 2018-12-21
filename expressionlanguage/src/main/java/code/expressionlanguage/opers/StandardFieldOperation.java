package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.errors.custom.StaticAccessError;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.text.OperationsSequence;
import code.util.CustList;

public final class StandardFieldOperation extends
        SettableAbstractFieldOperation {

    public StandardFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(Analyzable _conf) {
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching cl_;
        if (isIntermediateDottedOperation()) {
            cl_ = getPreviousResultClass();
        } else {
            String look_ = _conf.getLookLocalClass();
            if (look_.isEmpty()) {
                cl_ = new ClassArgumentMatching(_conf.getGlobalClass());
            } else {
                cl_ = new ClassArgumentMatching(look_);
            }
        }
        if (cl_ == null || cl_.isUndefined()) {
            StaticAccessError static_ = new StaticAccessError();
            static_.setFileName(_conf.getCurrentFileName());
            static_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().addError(static_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return null;
        }
        return cl_;
    }

    @Override
    String getFieldName(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        return str_;
    }

    @Override
    boolean isBaseAccess(Analyzable _an) {
        return true;
    }

    @Override
    boolean isSuperAccess(Analyzable _an) {
        return true;
    }

}
