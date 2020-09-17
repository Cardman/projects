package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.Replacement;
import code.util.StringList;

public final class ReplacementStruct extends WithoutParentStruct implements Struct {

    private final Replacement instance;

    public ReplacementStruct(Replacement _instance) {
        instance = _instance;
    }

    public static void instantiate(ResultErrorStd _res, Struct... _args) {
        Replacement rep_ = getReplacement(_args);
        _res.setResult(new ReplacementStruct(rep_));
    }

    public static Struct instantiate(Struct... _args) {
        Replacement rep_ = getReplacement(_args);
        return(new ReplacementStruct(rep_));
    }

    private static Replacement getReplacement(Struct[] _args) {
        Replacement rep_ = new Replacement();
        if (_args[0] instanceof CharSequenceStruct) {
            rep_.setOldString(NumParsers.getCharSeq(_args[0]).toStringInstance());
        }
        if (_args[1] instanceof CharSequenceStruct) {
            rep_.setNewString(NumParsers.getCharSeq(_args[1]).toStringInstance());
        }
        return rep_;
    }

    public static void calculate(ContextEl _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct) {
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        ReplacementStruct rp_ = NumParsers.getReplacement(_struct);
        if (StringList.quickEq(name_, lgNames_.getAliasGetNewString())) {
            rp_.getNewString(_res);
            return;
        }
        rp_.getOldString(_res);
        
    }

    private void getOldString(ResultErrorStd _res) {
        String oldStr_ = instance.getOldString();
        _res.setResult(Argument.wrapStr(oldStr_));
    }

    private void getNewString(ResultErrorStd _res) {
        String newStr_ = instance.getNewString();
        _res.setResult(Argument.wrapStr(newStr_));
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return _contextEl.getStandards().getAliasReplacement();
    }

    public Replacement getInstance() {
        return instance;
    }


    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ReplacementStruct)) {
            return false;
        }
        ReplacementStruct other_ = NumParsers.getReplacement(_other);
        if (!StringList.quickEq(instance.getOldString(),other_.instance.getOldString())) {
            return false;
        }
        return StringList.quickEq(instance.getNewString(),other_.instance.getNewString());
    }

}
