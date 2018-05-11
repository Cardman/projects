package code.expressionlanguage.opers;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.BadFormatPathError;
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
        String str_ = originalStr_.trim();
        LgNames stds_ = _conf.getStandards();
        str_ = StringList.removeAllSpaces(str_);
        StringList classMethod_ = StringList.splitStrings(str_, STATIC_CALL);
        if (classMethod_.size() != 2) {
            BadFormatPathError badFormat_ = new BadFormatPathError();
            badFormat_.setPath(str_);
            badFormat_.setFileName(_conf.getCurrentFileName());
            badFormat_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badFormat_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return null;
        }
        String className_ = classMethod_.first();
        if (!className_.startsWith(CLASS_CHOICE_PREF)) {
            BadFormatPathError badFormat_ = new BadFormatPathError();
            badFormat_.setPath(str_);
            badFormat_.setFileName(_conf.getCurrentFileName());
            badFormat_.setRc(_conf.getCurrentLocation());
            _conf.getClasses().getErrorsDet().add(badFormat_);
            setResultClass(new ClassArgumentMatching(stds_.getAliasObject()));
            return null;
        }
        int lenPref_ = CLASS_CHOICE_PREF.length();
        className_ = className_.substring(lenPref_);
        className_ = StringList.removeAllSpaces(className_);
        className_ = className_.replace(EXTERN_CLASS, DOT_VAR);
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
        StringList classMethod_ = StringList.splitStrings(str_, STATIC_CALL);
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
