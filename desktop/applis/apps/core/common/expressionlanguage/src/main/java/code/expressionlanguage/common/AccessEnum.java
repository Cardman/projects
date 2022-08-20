package code.expressionlanguage.common;

public enum AccessEnum {
    PUBLIC(0),PROTECTED(1),PACKAGE(2),PRIVATE(3);
    private final int level;

    AccessEnum(int _l) {
        this.level = _l;
    }

    public boolean isStrictMoreAccessibleThan(AccessEnum _a) {
        return level < _a.level;
    }
}
