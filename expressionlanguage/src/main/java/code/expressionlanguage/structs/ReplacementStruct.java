package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.Replacement;
import code.util.StringList;

public final class ReplacementStruct implements Struct {

    private final Replacement instance;

    public ReplacementStruct(Replacement _instance) {
        instance = _instance;
    }

    public static void instantiate(ResultErrorStd _res, Struct... _args) {
        Replacement rep_ = new Replacement();
        if (_args[0] instanceof CharSequenceStruct) {
            rep_.setOldString(((CharSequenceStruct)_args[0]).toStringInstance());
        }
        if (_args[1] instanceof CharSequenceStruct) {
            rep_.setNewString(((CharSequenceStruct)_args[1]).toStringInstance());
        }
        _res.setResult(new ReplacementStruct(rep_));
    }

    public static void calculate(Analyzable _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct) {
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        ReplacementStruct rp_ = (ReplacementStruct)_struct;
        if (StringList.quickEq(name_, lgNames_.getAliasGetNewString())) {
            rp_.getNewString(_res);
            return;
        }
        rp_.getOldString(_res);
        
    }
    private void getOldString(ResultErrorStd _res) {
        String oldStr_ = instance.getOldString();
        if (oldStr_ == null) {
            _res.setResult(NullStruct.NULL_VALUE);
            return;
        }
        _res.setResult(new StringStruct(oldStr_));
    }

    private void getNewString(ResultErrorStd _res) {
        String newStr_ = instance.getNewString();
        if (newStr_ == null) {
            _res.setResult(NullStruct.NULL_VALUE);
            return;
        }
        _res.setResult(new StringStruct(newStr_));
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
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
        ReplacementStruct other_ = (ReplacementStruct) _other;
        if (!StringList.quickEq(instance.getOldString(),other_.instance.getOldString())) {
            return false;
        }
        return StringList.quickEq(instance.getNewString(),other_.instance.getNewString());
    }

}
