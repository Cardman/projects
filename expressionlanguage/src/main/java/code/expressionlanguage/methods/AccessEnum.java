package code.expressionlanguage.methods;

public enum AccessEnum {
    PUBLIC,PROTECTED,PACKAGE,PRIVATE;
    public boolean isStrictMoreAccessibleThan(AccessEnum _a) {
        if (this == _a) {
            return false;
        }
        if (this == PUBLIC) {
            return true;
        }
        if (this == PROTECTED) {
            if (_a == PACKAGE) {
                return true;
            }
            if (_a == PRIVATE) {
                return true;
            }
            return false;
        }
        if (this == PACKAGE) {
            if (_a == PRIVATE) {
                return true;
            }
            return false;
        }
        return false;
    }
}
