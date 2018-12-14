package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.ObjectMap;
import code.util.Replacement;
import code.util.StringList;

public final class ReplacementStruct implements Struct {

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
        rep_.setOldString(((CharSequenceStruct)_args[0]).getInstance().toString());
        rep_.setNewString(((CharSequenceStruct)_args[1]).getInstance().toString());
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
            rp_.setNewString(_args[0], lgNames_, _res);
            return;
        }
        rp_.setOldString(_args[0], lgNames_, _res);
        
    }
    private void getOldString(ResultErrorStd _res) {
        _res.setResult(new StringStruct(instance.getOldString()));
    }

    private void setOldString(Struct _value, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_value instanceof StringStruct)) {
            _res.setError(nullPe_);
            return;
        }
        StringStruct str_ = (StringStruct)_value;
        instance.setOldString(str_.getInstance());
        _res.setResult(NullStruct.NULL_VALUE);
    }

    private void getNewString(ResultErrorStd _res) {
        _res.setResult(new StringStruct(instance.getNewString()));
    }

    private void setNewString(Struct _value, LgNames _stds, ResultErrorStd _res) {
        String nullPe_ = _stds.getAliasNullPe();
        if (!(_value instanceof StringStruct)) {
            _res.setError(nullPe_);
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
    public boolean isNull() {
        return false;
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
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public boolean sameReference(Struct _other) {
        if (!(_other instanceof ReplacementStruct)) {
            return false;
        }
        ReplacementStruct other_ = (ReplacementStruct) _other;
        return getInstance() == other_.getInstance();
    }

    @Override
    public boolean isArray() {
        return false;
    }
}
