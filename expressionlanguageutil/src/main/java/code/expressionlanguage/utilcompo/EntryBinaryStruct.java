package code.expressionlanguage.utilcompo;

import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.structs.StringStruct;

public final class EntryBinaryStruct implements Struct {
    private StringStruct name;
    private ArrayStruct binary;
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
        return new ArrayStruct(new Struct[0],_arrType);
    }

    public StringStruct getName() {
        return name;
    }

    public ArrayStruct getBinary() {
        return binary;
    }

    @Override
    public Struct getParent() {
        return NullStruct.NULL_VALUE;
    }

    @Override
    public String getClassName(ExecutableCode _contextEl) {
        return ((LgNamesUtils)_contextEl.getStandards()).getAliasEntryBinary();
    }

    @Override
    public boolean sameReference(Struct _other) {
        return this == _other;
    }
}
