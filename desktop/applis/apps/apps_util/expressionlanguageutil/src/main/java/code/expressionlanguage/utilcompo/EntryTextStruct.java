package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;

public final class EntryTextStruct extends WithoutParentIdStruct implements Struct {
    private StringStruct name;
    private StringStruct text;
    private long time;
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

    public Struct getLongTime() {
        return new LongStruct(time);
    }

    public void setLongTime(Struct _str) {
        time = NumParsers.convertToNumber(_str).longStruct();
    }
    public StringStruct getName() {
        return name;
    }

    public StringStruct getText() {
        return text;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesWithNewAliases)_contextEl.getStandards()).getCustAliases().getAliasEntryText();
    }


}
