package code.expressionlanguage.common;

public enum AccessEnum {
    PUBLIC,PROTECTED,PACKAGE,PRIVATE;
    public boolean isStrictMoreAccessibleThan(AccessEnum _a) {
        return ordinal() < _a.ordinal();
    }
}
