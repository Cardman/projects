package code.expressionlanguage.structs;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;

public final class CharStruct extends AbsRelativeNumberStruct {

    private final char value;

    public CharStruct(char _value) {
        value = _value;
    }


    @Override
    public StringStruct getDisplayedString(ContextEl _an) {
        return new StringStruct(Character.toString(value));
    }

    @Override
    public StringStruct getDisplayedString(AnalyzedPageEl _an) {
        return new StringStruct(Character.toString(value));
    }

    @Override
    public String getClassName(ContextEl _context) {
        return _context.getStandards().getContent().getNbAlias().getAliasCharacter();
    }

    public char getChar() {
        return value;
    }

    @Override
    public long longStruct() {
        return value;
    }

    @Override
    public int intStruct() {
        return value;
    }

    @Override
    public short shortStruct() {
        return (short) value;
    }

    @Override
    public byte byteStruct() {
        return (byte)value;
    }
}
