package code.expressionlanguage.stds;

import code.expressionlanguage.Analyzable;
import code.util.StringList;
import code.util.StringMap;

public final class PrimitiveType {

    private final String name;

    private final String wrapper;

    private final String directUpperPrimitiveCast;

    private final String directUpperPrimitiveInherit;

    private final boolean nb;

    public PrimitiveType(String _name, String _wrapper,
            String _directUpperPrimitiveCast, String _directUpperPrimitiveInherit, boolean _nb) {
        name = _name;
        wrapper = _wrapper;
        directUpperPrimitiveCast = _directUpperPrimitiveCast;
        directUpperPrimitiveInherit = _directUpperPrimitiveInherit;
        nb = _nb;
    }

    public String getName() {
        return name;
    }
    public String getWrapper() {
        return wrapper;
    }
    public String getDirectUpperPrimitiveCast() {
        return directUpperPrimitiveCast;
    }
    public String getDirectUpperPrimitiveInherit() {
        return directUpperPrimitiveInherit;
    }
    public StringList getAllSuperType(Analyzable _cont) {
        StringList all_ = new StringList();
        LgNames stds_ = _cont.getStandards();
        StringMap<PrimitiveType> p_ = stds_.getPrimitiveTypes();
        String name_ = name;
        while (!name_.isEmpty()) {
            PrimitiveType prim_ = p_.getVal(name_);
            all_.add(prim_.name);
            all_.add(prim_.wrapper);
            name_ = prim_.getDirectUpperPrimitiveInherit();
        }
        if (nb) {
            all_.add(stds_.getAliasNumber());
        }
        all_.add(stds_.getAliasObject());
        return all_;
    }
    public StringList getAllPrimSuperType(Analyzable _cont) {
        StringList all_ = new StringList();
        LgNames stds_ = _cont.getStandards();
        StringMap<PrimitiveType> p_ = stds_.getPrimitiveTypes();
        String name_ = name;
        while (!name_.isEmpty()) {
            PrimitiveType prim_ = p_.getVal(name_);
            all_.add(prim_.name);
            all_.add(prim_.wrapper);
            name_ = prim_.getDirectUpperPrimitiveCast();
        }
        if (nb) {
            all_.add(stds_.getAliasNumber());
        }
        all_.add(stds_.getAliasObject());
        return all_;
    }
}
