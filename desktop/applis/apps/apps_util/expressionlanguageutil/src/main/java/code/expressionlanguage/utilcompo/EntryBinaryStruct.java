package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.*;

public final class EntryBinaryStruct extends WithoutParentIdStruct implements Struct {
    private StringStruct name;
    private ArrayStruct binary;
    private long time;
    EntryBinaryStruct(Struct _name, Struct _binary, String _arrType) {
        name = getString(_name);
        binary = getArray(_binary,_arrType);
    }
    EntryBinaryStruct(Struct _name, ArrayStruct _binary) {
        name = getString(_name);
        binary = _binary;
    }
    private static StringStruct getString(Struct _str) {
        if (_str instanceof StringStruct) {
            return (StringStruct) _str;
        }
        return new StringStruct("");
    }
    private static ArrayStruct getArray(Struct _str, String _arrType) {
        if (_str instanceof ArrayStruct) {
            return (ArrayStruct) _str;
        }
        return new ArrayStruct(0,_arrType);
    }

    public Struct getLongTime() {
        return new LongStruct(getTime());
    }

    public void setLongTime(Struct _str) {
        time = NumParsers.convertToNumber(_str).longStruct();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long _time) {
        time = _time;
    }

    public StringStruct getName() {
        return name;
    }

    public ArrayStruct getBinary() {
        return binary;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return ((LgNamesWithNewAliases)_contextEl.getStandards()).getCustAliases().getAliasEntryBinary();
    }

}
