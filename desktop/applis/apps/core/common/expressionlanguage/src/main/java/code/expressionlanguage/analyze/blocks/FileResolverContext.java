package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.TokenCheckerContext;
import code.expressionlanguage.analyze.files.DefaultAccess;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.PrimitiveTypes;

public final class FileResolverContext {
    private final TokenCheckerContext tok;
    private final MethodAccessKind stat;
    private final DefaultAccess def;

    public FileResolverContext(KeyWords _k, MethodAccessKind _s, DefaultAccess _defaultAccess, PrimitiveTypes _primTypes, String _aliasVoid) {
        tok = new TokenCheckerContext(_k,_primTypes,_aliasVoid);
        this.stat = _s;
        def = _defaultAccess;
    }

    public TokenCheckerContext getTok() {
        return tok;
    }

    public KeyWords getKeys() {
        return getTok().getKeys();
    }

    public MethodAccessKind getStat() {
        return stat;
    }

    public DefaultAccess getDef() {
        return def;
    }

    public PrimitiveTypes getPrims() {
        return getTok().getPrims();
    }

    public String getAlVoid() {
        return getTok().getAlVoid();
    }
}
