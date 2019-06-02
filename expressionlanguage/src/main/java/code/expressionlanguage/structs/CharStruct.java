package code.expressionlanguage.structs;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;

public final class CharStruct extends NumberStruct {

    private final char value;

    public CharStruct(char _value) {
        value = _value;
    }


    @Override
    public StringStruct getDisplayedString(Analyzable _an) {
        return new StringStruct(Character.toString(value));
    }

    @Override
    public String getClassName(ExecutableCode _context) {
        return _context.getStandards().getAliasCharacter();
    }

    public char getChar() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public short shortValue() {
        return (short) value;
    }

    @Override
    public byte byteValue() {
        return (byte)value;
    }
}
