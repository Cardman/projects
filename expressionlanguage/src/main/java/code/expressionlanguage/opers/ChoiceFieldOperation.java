package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.Templates;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;

public final class ChoiceFieldOperation extends
        SettableAbstractFieldOperation {

    public ChoiceFieldOperation(int _indexInEl, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
    }

    @Override
    ClassArgumentMatching getFrom(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        LgNames stds_ = _conf.getStandards();
        String className_ = originalStr_.substring(0,originalStr_.lastIndexOf(PAR_RIGHT));
        int lenPref_ = className_.indexOf(PAR_LEFT)+1;
        className_ = className_.substring(lenPref_);
        className_ = StringList.removeAllSpaces(className_);
        if (className_.contains(Templates.TEMPLATE_BEGIN)) {
            if (!checkCorrect(_conf, className_, true, lenPref_)) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return null;
            }
        } else {
            if (!checkExistBase(_conf, false, className_, true, lenPref_)) {
                setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
                return null;
            }
        }
        return new ClassArgumentMatching(className_);
    }

    @Override
    String getFieldName(Analyzable _conf) {
        OperationsSequence op_ = getOperations();
        String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
        String str_ = originalStr_.trim();
        str_ = StringList.removeAllSpaces(str_);
        StringList classMethod_ = StringList.splitChars(str_, PAR_RIGHT);
        return classMethod_.last();
    }

    @Override
    boolean isBaseAccess(Analyzable _an) {
        return true;
    }

    @Override
    boolean isSuperAccess(Analyzable _an) {
        return false;
    }

}
