package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.Replacement;
import code.util.StringList;

public final class ReplacementStruct implements RealInstanceStruct {

    private final Replacement instance;

    public ReplacementStruct(Replacement _instance) {
        instance = _instance;
    }

    public static void instantiate(ResultErrorStd _res, ConstructorId _id, Struct... _args) {
        if (_id.getParametersTypes().size() == 0) {
            _res.setResult(new ReplacementStruct(new Replacement()));
            return;
        }
        Replacement rep_ = new Replacement();
        if (_args[0] instanceof CharSequenceStruct) {
            rep_.setOldString(((CharSequenceStruct)_args[0]).getInstance().toString());
        }
        if (_args[1] instanceof CharSequenceStruct) {
            rep_.setNewString(((CharSequenceStruct)_args[1]).getInstance().toString());
        }
        _res.setResult(new ReplacementStruct(rep_));
    }

    public static void calculate(Analyzable _cont, ResultErrorStd _res, ClassMethodId _method, Struct _struct, Struct... _args) {
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        ReplacementStruct rp_ = (ReplacementStruct)_struct;
        if (StringList.quickEq(name_, lgNames_.getAliasGetNewString())) {
            rp_.getNewString(_res);
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasGetOldString())) {
            rp_.getOldString(_res);
            return;
        }
        ContextEl cont_ = _cont.getContextEl();
        if (cont_.isInitEnums() && cont_.isContainedSensibleFields(rp_)) {
            cont_.failInitEnums();
            return;
        }
        if (StringList.quickEq(name_, lgNames_.getAliasSetNewString())) {
            rp_.setNewString(_args[0], _res);
            return;
        }
        rp_.setOldString(_args[0], _res);
        
    }
    private void getOldString(ResultErrorStd _res) {
        String oldStr_ = instance.getOldString();
        if (oldStr_ == null) {
            _res.setResult(NullStruct.NULL_VALUE);
            return;
        }
        _res.setResult(new StringStruct(oldStr_));
    }

    private void setOldString(Struct _value, ResultErrorStd _res) {
        if (!(_value instanceof StringStruct)) {
            instance.setOldString(null);
            _res.setResult(NullStruct.NULL_VALUE);
            return;
        }
        StringStruct str_ = (StringStruct)_value;
        instance.setOldString(str_.getInstance());
        _res.setResult(NullStruct.NULL_VALUE);
    }

    private void getNewString(ResultErrorStd _res) {
        String newStr_ = instance.getNewString();
        if (newStr_ == null) {
            _res.setResult(NullStruct.NULL_VALUE);
            return;
        }
        _res.setResult(new StringStruct(newStr_));
    }

    private void setNewString(Struct _value, ResultErrorStd _res) {
        if (!(_value instanceof StringStruct)) {
            instance.setNewString(null);
            _res.setResult(NullStruct.NULL_VALUE);
            return;
        }
        StringStruct str_ = (StringStruct)_value;
        instance.setNewString(str_.getInstance());
        _res.setResult(NullStruct.NULL_VALUE);
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasReplacement();
    }

    @Override
    public Replacement getInstance() {
        return instance;
    }


    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ReplacementStruct)) {
            return false;
        }
        ReplacementStruct other_ = (ReplacementStruct) _other;
        return getInstance() == other_.getInstance();
    }

}
