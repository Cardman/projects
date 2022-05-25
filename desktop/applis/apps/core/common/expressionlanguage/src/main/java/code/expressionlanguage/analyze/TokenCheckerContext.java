package code.expressionlanguage.analyze;

import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;

public final class TokenCheckerContext {
    private final KeyWords keys;
    private final PrimitiveTypes prims;
    private final String alVoid;

    public TokenCheckerContext(KeyWords _k, PrimitiveTypes _primTypes, String _aliasVoid) {
        this.keys = _k;
        prims = _primTypes;
        alVoid = _aliasVoid;
    }

    public String getAlVoid() {
        return alVoid;
    }

    public PrimitiveTypes getPrims() {
        return prims;
    }

    public KeyWords getKeys() {
        return keys;
    }
}
