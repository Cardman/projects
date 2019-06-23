package code.formathtml.util;

import code.bean.translator.Translator;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;

public final class TranslatorStruct implements RealInstanceStruct {

    private Translator translator;

    public TranslatorStruct(Translator _translator) {
        translator = _translator;
    }
    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return translator.getClassName();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

    @Override
    public Object getInstance() {
        return translator;
    }

}
