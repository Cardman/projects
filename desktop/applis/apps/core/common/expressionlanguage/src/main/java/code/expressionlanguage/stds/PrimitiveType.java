package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.common.AnaInheritedType;
import code.expressionlanguage.common.InheritedType;
import code.util.StringList;
import code.util.StringMap;

public final class PrimitiveType implements InheritedType,AnaInheritedType {

    private final String name;

    private final String wrapper;

    private final String directUpperPrimitiveInherit;

    private final boolean nb;

    private final byte castNb;

    public PrimitiveType(String _name, String _wrapper,
                         String _directUpperPrimitiveInherit, boolean _nb, byte _castNb) {
        name = _name;
        wrapper = _wrapper;
        directUpperPrimitiveInherit = _directUpperPrimitiveInherit;
        nb = _nb;
        castNb = _castNb;
    }

    public String getWrapper() {
        return wrapper;
    }

    public String getDirectUpperPrimitiveInherit() {
        return directUpperPrimitiveInherit;
    }
    public StringList getAllSuperType(ContextEl _cont) {
        StringList all_ = new StringList();
        LgNames stds_ = _cont.getStandards();
        return getAllSuperType(all_, stds_.getPrimitiveTypes(), stds_.getContent().getNbAlias().getAliasNumber(), stds_.getContent().getCoreNames().getAliasObject());
    }
    public StringList getAllSuperType(AnalyzedPageEl _cont) {
        StringList all_ = new StringList();
        return getAllSuperType(all_, _cont.getPrimitiveTypes(), _cont.getAliasNumber(), _cont.getAliasObject());
    }

    public StringList getAllSuperType(StringList all_, StringMap<PrimitiveType> _primitiveTypes, String _aliasNumber, String _aliasObject) {
        String name_ = name;
        while (!name_.isEmpty()) {
            PrimitiveType prim_ = _primitiveTypes.getVal(name_);
            all_.add(prim_.name);
            all_.add(prim_.wrapper);
            name_ = prim_.getDirectUpperPrimitiveInherit();
        }
        if (nb) {
            all_.add(_aliasNumber);
        }
        all_.add(_aliasObject);
        return all_;
    }

    @Override
    public boolean isSubTypeOf(String _fullName, ContextEl _an) {
        return StringList.contains(getAllSuperType(_an),_fullName);
    }

    @Override
    public boolean isSubTypeOf(String _fullName, AnalyzedPageEl _an) {
        return StringList.contains(getAllSuperType(_an),_fullName);
    }

    public byte getCastNb() {
        return castNb;
    }
}
