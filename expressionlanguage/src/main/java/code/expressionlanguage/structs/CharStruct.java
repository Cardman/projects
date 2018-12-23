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

    @Override
    public Number getInstance() {
        return (int)value;
    }

    public char getChar() {
        return value;
    }

}
