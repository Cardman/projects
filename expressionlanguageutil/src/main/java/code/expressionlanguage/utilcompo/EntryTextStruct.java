package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;

public final class EntryTextStruct implements Struct {
    private StringStruct name;
    private StringStruct text;
    EntryTextStruct(Struct _name, Struct _text) {
        name = getString(_name);
        text = getString(_text);
    }
    private static StringStruct getString(Struct _str) {
        if (_str instanceof StringStruct) {
            return (StringStruct) _str;
        }
        return new StringStruct("");
    }

    public StringStruct getName() {
        return name;
    }

    public StringStruct getText() {
        return text;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getAliasEntryText();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }

}
