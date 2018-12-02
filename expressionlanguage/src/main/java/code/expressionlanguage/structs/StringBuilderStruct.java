package code.expressionlanguage.structs;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.util.ObjectMap;

public final class StringBuilderStruct extends CharSequenceStruct {

    private final StringBuilder instance;

    public StringBuilderStruct(StringBuilder _instance) {
        instance = _instance;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return _contextEl.getStandards().getAliasStringBuilder();
    }

    @Override
    public StringBuilder getInstance() {
        return instance;
    }

    @Override
    public ObjectMap<ClassField, Struct> getFields() {
        return null;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public void toStringStruct(LgNames _stds, ResultErrorStd _res) {
        _res.setResult(new StringStruct(instance.toString()));
    }
}
