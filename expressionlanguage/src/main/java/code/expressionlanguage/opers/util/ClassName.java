package code.expressionlanguage.opers.util;
import code.util.StringList;
import code.util.ints.Equallable;

public final class ClassName implements Equallable<ClassName> {

    private final String name;

    private final boolean vararg;

    public ClassName(String _name, boolean _vararg) {
        name = _name;
        vararg = _vararg;
    }

    @Override
    public boolean eq(ClassName _obj) {
        if (!StringList.quickEq(name, _obj.name)) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public boolean isVararg() {
        return vararg;
    }
}
