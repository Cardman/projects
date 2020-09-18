package code.expressionlanguage.analyze.inherits;

import code.util.StringList;

public final class ResultTernary {

    private final StringList types;
    private final byte castPrim;
    private final boolean unwrapFirst;
    private final boolean unwrapSecond;
    public ResultTernary(StringList _types, byte _castPrim, boolean _unwrapFirst,
            boolean _unwrapSecond) {
        types = _types;
        castPrim = _castPrim;
        unwrapFirst = _unwrapFirst;
        unwrapSecond = _unwrapSecond;
    }
    public static ResultTernary noUnwrap(StringList _types) {
        return new ResultTernary(_types,(byte)-1,false,false);
    }
    public static ResultTernary unwrapLeft(StringList _types, byte _cast) {
        return new ResultTernary(_types,_cast,true,false);
    }
    public static ResultTernary unwrapRight(StringList _types, byte _cast) {
        return new ResultTernary(_types,_cast,false,true);
    }
    public static ResultTernary unwrapBoth(StringList _types, byte _cast) {
        return new ResultTernary(_types,_cast,true,true);
    }
    public StringList getTypes() {
        return types;
    }

    public byte getCastPrim() {
        return castPrim;
    }

    public boolean isUnwrapFirst() {
        return unwrapFirst;
    }
    public boolean isUnwrapSecond() {
        return unwrapSecond;
    }

}
